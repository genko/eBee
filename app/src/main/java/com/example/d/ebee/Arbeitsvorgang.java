package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import logik.EBEEAblauf;

public class Arbeitsvorgang extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbeitsvorgang);

        ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Spinner adressieren
        Spinner spinner1 = (Spinner)findViewById(R.id.arbeitsvorgang_fluglochkeilAuswahl);

        //Adapter erstellen
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Fluglochkeil , android.R.layout.simple_spinner_item);

        //Layout anpassen
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adapter mit Spinner verknüpfen
        spinner1.setAdapter(adapter1);

        //Fluglochkeil mit datenbankwert abgleichen
        int spinnerFluglochkeil = adapter1.getPosition(ebeeAblauf.getSelektiertesVolk().getFluglochkeil());
        spinner1.setSelection(spinnerFluglochkeil);

        //init absperrgitter
        Switch absperrgitter = (Switch) findViewById(R.id.switch1);
        absperrgitter.setChecked(this.ebeeAblauf.getSelektiertesVolk().getAbsperrgitter());

        //init Check box
        initAllCheckBoxDrohenenrahmen();

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        //Icon setzen
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_arbeitsvorgang);
    }

    public void incrementWaben(View v) {
        TextView textfeld = (TextView) findViewById(R.id.anzWaben);

        Integer zahl = Integer.parseInt(textfeld.getText().toString());

        if(zahl <= 20)
            zahl += 1;

        textfeld.setText(String.valueOf(zahl));
    }

    public void incrementZargen(View v) {
        TextView textfeldWaben = (TextView) findViewById(R.id.anzWaben);
        TextView textfeldZargen = (TextView) findViewById(R.id.anzZargen);

        Integer wabenanzahl = Integer.parseInt(textfeldWaben.getText().toString());
        Integer zargenanzahl = Integer.parseInt(textfeldZargen.getText().toString());

        if((zargenanzahl <= 2) && (wabenanzahl <= 20)) {
            zargenanzahl += 1;
            wabenanzahl += 10;
        }

        textfeldWaben.setText(String.valueOf(wabenanzahl));
        textfeldZargen.setText(String.valueOf(zargenanzahl));
    }


    public void decrementZargen(View v) {
        TextView textfeldZargen = (TextView) findViewById(R.id.anzZargen);
        TextView textfeldWaben = (TextView) findViewById(R.id.anzWaben);

        Integer Zargenanzahl = Integer.parseInt(textfeldZargen.getText().toString());
        Integer Wabenanzahl = Integer.parseInt(textfeldWaben.getText().toString());

        if ((Zargenanzahl >= -2) && (Wabenanzahl >= -20)) {
            Zargenanzahl -= 1;
            Wabenanzahl -= 10;
        }

        textfeldZargen.setText(String.valueOf(Zargenanzahl));
        textfeldWaben.setText(String.valueOf(Wabenanzahl));
    }

    public void decrementWaben(View v) {
        TextView textfeld = (TextView) findViewById(R.id.anzWaben);

        Integer zahl = Integer.parseInt(textfeld.getText().toString());

        if(zahl >= -20)
            zahl -= 1;

        textfeld.setText(String.valueOf(zahl));
    }
    public void VolkChangeArbeitsvorgang(View view)
    {
        startActivity(new Intent(Arbeitsvorgang.this, Volk.class));
    }

    public void onClick(View v){
        vorgaenge.Arbeitsvorgang arbeitsvorgang = new vorgaenge.Arbeitsvorgang(this.ebeeAblauf.getArbeitsvorgangAnzahl() + 1, ebeeAblauf.getSelektiertesVolk());

        TextView zargen = (TextView)findViewById(R.id.anzZargen);
        Switch absperrgitter = (Switch)findViewById(R.id.switch1);
        CheckBox box1 = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox box2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox box3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox box4 = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox box5 = (CheckBox)findViewById(R.id.checkBox5);
        CheckBox box6 = (CheckBox)findViewById(R.id.checkBox6);
        CheckBox box7 = (CheckBox)findViewById(R.id.checkBox7);
        CheckBox box8 = (CheckBox)findViewById(R.id.checkBox8);
        CheckBox box9 = (CheckBox)findViewById(R.id.checkBox9);
        CheckBox box10 = (CheckBox)findViewById(R.id.checkBox10);
        TextView waben = (TextView)findViewById(R.id.anzWaben);
        Spinner spinner = (Spinner)findViewById(R.id.arbeitsvorgang_fluglochkeilAuswahl);

        int anzZargen = Integer.parseInt(zargen.getText().toString());
        boolean valueAbsperrgitter = absperrgitter.isChecked();
        boolean valueBox1 = box1.isChecked();
        boolean valueBox2 = box2.isChecked();
        boolean valueBox3 = box3.isChecked();
        boolean valueBox4 = box4.isChecked();
        boolean valueBox5 = box5.isChecked();
        boolean valueBox6 = box6.isChecked();
        boolean valueBox7 = box7.isChecked();
        boolean valueBox8 = box8.isChecked();
        boolean valueBox9 = box9.isChecked();
        boolean valueBox10 = box10.isChecked();
        int anzWaben = Integer.parseInt(waben.getText().toString());
        String selektiertesItem = spinner.getSelectedItem().toString();

        arbeitsvorgang.setAnzahlZargen(anzZargen);

        arbeitsvorgang.setAbsperrgitter(valueAbsperrgitter);

        //absperrgitter check
        arbeitsvorgang.setAbsperrgitterWechsel(this.compareAbsperrgitter(this.ebeeAblauf.getSelektiertesVolk(), arbeitsvorgang));


        arbeitsvorgang.setDrohnenramenAt(0, valueBox1);
        arbeitsvorgang.setDrohnenramenAt(1, valueBox2);
        arbeitsvorgang.setDrohnenramenAt(2, valueBox3);
        arbeitsvorgang.setDrohnenramenAt(3, valueBox4);
        arbeitsvorgang.setDrohnenramenAt(4, valueBox5);
        arbeitsvorgang.setDrohnenramenAt(5, valueBox6);
        arbeitsvorgang.setDrohnenramenAt(6, valueBox7);
        arbeitsvorgang.setDrohnenramenAt(7, valueBox8);
        arbeitsvorgang.setDrohnenramenAt(8, valueBox9);
        arbeitsvorgang.setDrohnenramenAt(9, valueBox10);

        //DrohnenrahmenCheck
        arbeitsvorgang.setDrohnenrahmenWechsel(this.compareDrohnenrahmen(this.ebeeAblauf.getSelektiertesVolk(), arbeitsvorgang));


        arbeitsvorgang.setAnzahlWaben(anzWaben);

        arbeitsvorgang.setFluglochkeil(selektiertesItem);

        //fluglochkeil check
        arbeitsvorgang.setFluglochkeilWechsel(this.compareFluglochkeil(this.ebeeAblauf.getSelektiertesVolk(), arbeitsvorgang));

        calcChangeVolk(arbeitsvorgang);

        //flags setzen
        arbeitsvorgang.setIsInDatabase(false);

        this.ebeeAblauf.addArbeitsvorgang(arbeitsvorgang);
        this.ebeeAblauf.listToDatabase();

        Toast toast = Toast.makeText(getApplicationContext(), "Arbeitsvorgang wird gespeichert", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        VolkChangeArbeitsvorgang(v);

    }

    private void calcChangeVolk(vorgaenge.Arbeitsvorgang arbeitsvorgang) {

        //volk holen
        logik.Volk volk = this.ebeeAblauf.getSelektiertesVolk();

        //volk anpassen
        volk.setAnzahlZargen( volk.getAnzahlZargen() + arbeitsvorgang.getAnzahlZargen() );
        volk.setAnzahlWaben( volk.getAnzahlWaben() + arbeitsvorgang.getAnzahlWaben() );
        volk.setAbsperrgitter( arbeitsvorgang.getAbsperrgitter() );

        //Drohnenrahmen noch setzen
        for( int i = 0 ; i != arbeitsvorgang.getDrohnenrahmen().size() ; i = i + 1 ) {

            volk.setPosDrohnenrahmenAt(i,arbeitsvorgang.getDrohnenrahmen().get(i));

        }

        volk.setFluglochkeil( arbeitsvorgang.getFluglochkeil() );

        //flag setzen
        volk.setDataHasChanged(true);

    }

    private boolean compareFluglochkeil(logik.Volk volk, vorgaenge.Arbeitsvorgang arbeitsvorgang) {
        //gibt true zurück fals die werte unterschiedlich sind

        if(!volk.getFluglochkeil().equals(arbeitsvorgang.getFluglochkeil())) {

            return true;

        } else {
            //false wenn sie sich nicht unterscheiden
            return false;

        }

    }

    private boolean compareAbsperrgitter(logik.Volk volk, vorgaenge.Arbeitsvorgang arbeitsvorgang) {
        //gibt true zurück fals die werte unterschiedlich sind

        if( volk.getAbsperrgitter() != arbeitsvorgang.getAbsperrgitter() ) {

            return true;

        } else {
            //false wenn sie sich nicht unterscheiden
            return false;

        }

    }

    private boolean compareDrohnenrahmen(logik.Volk volk, vorgaenge.Arbeitsvorgang arbeitsvorgang) {

        //gibt true zurück fals die werte unterschiedlich sind

        for(int i = 0; i != arbeitsvorgang.getDrohnenrahmen().size() ; i = i + 1) {

            if(arbeitsvorgang.getDrohenrahmenAt(i) != volk.getPosDrohnenrahmen(i)) {

                return true;

            }

        }

        return false;

    }

    private void initAllCheckBoxDrohenenrahmen() {

         initCheckBoxDrohnenrahmen(R.id.checkBox1, 0);
         initCheckBoxDrohnenrahmen(R.id.checkBox2, 1);
         initCheckBoxDrohnenrahmen(R.id.checkBox3, 2);
         initCheckBoxDrohnenrahmen(R.id.checkBox4, 3);
         initCheckBoxDrohnenrahmen(R.id.checkBox5, 4);
         initCheckBoxDrohnenrahmen(R.id.checkBox6, 5);
         initCheckBoxDrohnenrahmen(R.id.checkBox7, 6);
         initCheckBoxDrohnenrahmen(R.id.checkBox8, 7);
         initCheckBoxDrohnenrahmen(R.id.checkBox9, 8);
         initCheckBoxDrohnenrahmen(R.id.checkBox10, 9);

    }


    private void initCheckBoxDrohnenrahmen(int id, int index) {

        CheckBox checkBox = (CheckBox) findViewById(id);

        checkBox.setChecked(this.ebeeAblauf.getSelektiertesVolk().getPosDrohnenrahmen(index));

    }


}