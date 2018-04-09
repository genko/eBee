package com.example.d.ebee;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import inventar.*;
import logik.EBEEAblauf;

public class NeueBehandlung extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    private vorgaenge.Behandlung behandlung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_behandlung);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        this.initArtBehandlungSpinner();
        this.initBehandlungsdauerSpinner();

    }

    public void behandlungBeginnen(View view) {

        if(isValid()==true) {

            this.behandlung = this.createBehandlung();

            if(this.behandlung != null) {

                Toast toast = Toast.makeText(getApplicationContext(), "Behandlung wird begonnen.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

                startActivity(new Intent(NeueBehandlung.this, Volk.class));

                addReminderInCalendar(this.behandlung);

            }

        } else {

            Toast toast = Toast.makeText(getApplicationContext(), "Eingabe nicht Valide", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }

    }

    private void initArtBehandlungSpinner() {

        String[] auswahl = {getString(R.string.neueBehandlung_Behandlung_auswahl),getString(R.string.neueBehandlung_Behandlung_ameisensaure),getString(R.string.neueBehandlung_Behandlung_milchsaure),getString(R.string.neueBehandlung_Behandlung_oxalsaure)};
        Spinner behandlungSpinner = (Spinner) findViewById(R.id.neueBehandlung_Spinner_Behandlungsart);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,auswahl);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        behandlungSpinner.setAdapter(spinnerArrayAdapter);

    }

    private void initBehandlungsdauerSpinner() {

        //Spinner adressieren
        Spinner spinner1 = (Spinner)findViewById(R.id.neueBehandlung_Spinner_Behandlungsdauer);

        //Adapter erstellen
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Behandlungsdauer , android.R.layout.simple_spinner_item);

        //Layout anpassen
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adapter mit Spinner verknüpfen
        spinner1.setAdapter(adapter1);

    }

    private vorgaenge.Behandlung createBehandlung() {

        vorgaenge.Behandlung behandlung = new vorgaenge.Behandlung(this.ebeeAblauf.getBehandlungAnzahl() + 1, this.ebeeAblauf.getSelektiertesVolk());

        Spinner behandlungSpinner = (Spinner) findViewById(R.id.neueBehandlung_Spinner_Behandlungsart);

        behandlung.setArtDerBehandlung(behandlungSpinner.getSelectedItem().toString());

        EditText mengeSaureML = (EditText) findViewById(R.id.neueBehandlung_EditText_MLSaeure);

        behandlung.setMenge(Float.parseFloat(mengeSaureML.getText().toString()));

        CheckBox windel = (CheckBox) findViewById(R.id.neueBehandlung_CheckBox_WindelEingesetzt);

        behandlung.setWindelEingesetzt(windel.isChecked());

        Spinner behandlungDauer = (Spinner)findViewById(R.id.neueBehandlung_Spinner_Behandlungsdauer);

        behandlung.setTimestampFinish( behandlung.getTimestampCreate() + (24 * 60 * 60 * 1000 * Long.parseLong(behandlungDauer.getSelectedItem().toString())) );

        EditText notiz = (EditText) findViewById(R.id.neueBehandlung_EditText_Notiz);

        behandlung.setText(notiz.getText().toString());

        useAcid(behandlung);

        behandlung.setIsInDatabase(false);

        this.ebeeAblauf.addBehandlung(behandlung);

        this.ebeeAblauf.listToDatabase();

        return behandlung;

    }

    /** Adds Events and Reminders in Calendar. */
    private void addReminderInCalendar(vorgaenge.Behandlung behandlung) {

        if(behandlung != null) {

            String volkname = ebeeAblauf.getSelektiertesVolk().getVolkName();

            Spinner spinner2 = (Spinner)findViewById(R.id.neueBehandlung_Spinner_Behandlungsdauer);

            String dauer = spinner2.getSelectedItem().toString();

            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, behandlung.getTimestampCreate())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, behandlung.getTimestampFinish())
                    .putExtra(CalendarContract.Events.TITLE, "EBEE Behandlung")
                    .putExtra(CalendarContract.Events.DESCRIPTION, "Volk: " + volkname + " wird mit " + behandlung.getMenge() + "ml " + behandlung.getArtDerBehandlung() + " für " + dauer + " Tage behandelt.")
                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
            startActivity(intent);

        }

    }

    private void useAcid(vorgaenge.Behandlung behandlung) {

        inventar.Inventar inventar = this.ebeeAblauf.getInventar();

        if(behandlung.getArtDerBehandlung().equals("Ameisensäure")) {

            inventar.setAmeisensaeure(inventar.getAmeisensaeure() - behandlung.getMenge());

        }

        if(behandlung.getArtDerBehandlung().equals("Oxalsäure")) {

            inventar.setOxalsaeure(inventar.getOxalsaeure() - behandlung.getMenge() );

        }

        if(behandlung.getArtDerBehandlung().equals("Milchsäure")) {

            inventar.setMilchsaeure(inventar.getMilchsaeure() - behandlung.getMenge() );

        }

        //inventar Flag setzen
        inventar.setDataHasChanged(true);


    }

    private boolean isValid() {

        Spinner behandlungSpinner = (Spinner) findViewById(R.id.neueBehandlung_Spinner_Behandlungsart);
        Spinner behandlungDauer = (Spinner)findViewById(R.id.neueBehandlung_Spinner_Behandlungsdauer);
        EditText mengeSaureML = (EditText) findViewById(R.id.neueBehandlung_EditText_MLSaeure);

        if(behandlungSpinner.getSelectedItem().toString().equals("Auswahl")){

            return false;

        } else if(behandlungDauer.getSelectedItem().toString().equals("0")) {

            return false;

        } else if(mengeSaureML.getText().toString().equals("")) {

            return false;

        } else {

            return true;

        }

    }

}
