package com.example.d.ebee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import logik.EBEEAblauf;
import vorgaenge.*;
import vorgaenge.Behandlung;

public class Beobachtung extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beobachtung);

        ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Spinner adressieren
        Spinner spinner1 = (Spinner)findViewById(R.id.AuswahlBeobachtung);

        //Adapter erstellen
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Beobachtung , android.R.layout.simple_spinner_item);

        //Layout anpassen
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adapter mit Spinner verknüpfen
        spinner1.setAdapter(adapter1);

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        //Auswahl Spinner - Text ändern (OnClick Listener)
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();

                //Was wurde ausgewählt? + Text anpassen
                if(selectedItem.equals("Königin gesehen"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Königin gesehen");
                }
                else if(selectedItem.equals("Pollen eingetragen"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Pollen eingetragen");
                }
                else if(selectedItem.equals("Viel Flugverkehr"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Viel Flugverkehr");
                }
                else if(selectedItem.equals("Wenig Flugverkehr"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Wenig Flugverkehr");
                }
                else if(selectedItem.equals("Viel Futter"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Viel Futter");
                }
                else if(selectedItem.equals("Wenig Futter"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Wenig Futter");
                }
                else if(selectedItem.equals("Schwarmzellen"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Schwarmzellen");
                }
                else if(selectedItem.equals("Spielnäpfchen"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Spielnäpfchen");
                }
                else if(selectedItem.equals("Weisellos"))
                {
                    BeobachtungAnlegen(Beobachtung.this, "Weisellos");
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });

        //Icon setzen
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_beobachtung);
    }

    public void VolkChange(View view)
    {
        startActivity(new Intent(Beobachtung.this, Volk.class));
    }

    public void BeobachtungAnlegen(Context context, String text){

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.Beobachtung_linear_layout);

        TextView textView = new TextView(context);
        EditText editText = new EditText(context);

        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        editText.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setText(text);
        editText.setHint(getString(R.string.beobachtung_hint_notiz));

        linearLayout.addView(textView);
        linearLayout.addView(editText);
    }

    public void onClick(View v) {

        if(validCheck()==false) {

            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.beobachtung_valid_fail), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        } else {

            int beobachtungId = ebeeAblauf.getBeobachtungenAnzahl() + 1;

            vorgaenge.Beobachtung beobachtung = new vorgaenge.Beobachtung(beobachtungId, ebeeAblauf.getSelektiertesVolk());

            beobachtung.setTimestampCreate(EBEEAblauf.createTimestamp());

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.Beobachtung_linear_layout);

            int childcount = linearLayout.getChildCount();

            int teilBeobachtungId = ebeeAblauf.getTeilBeobachtungenAnzahl() + 1;

            for(int i = 0; i<childcount; i+=2){

                TeilBeobachtung teilBeobachtung = new TeilBeobachtung(beobachtung);

                teilBeobachtung.setArtDerBeobachtung(((TextView)linearLayout.getChildAt(i)).getText().toString());
                teilBeobachtung.setNotiz(((EditText)linearLayout.getChildAt(i+1)).getText().toString());
                teilBeobachtung.setTeilBeobachtungID(teilBeobachtungId);

                teilBeobachtung.setIsInDatabase(false);

                beobachtung.addTeilBeobachtung(teilBeobachtung);

                teilBeobachtungId = teilBeobachtungId + 1;

            }

            //flag setzen
            beobachtung.setIsInDatabase(false);

            ebeeAblauf.addBeobachtung(beobachtung);
            ebeeAblauf.listToDatabase();
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.beobachtung_valid_success), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            VolkChange(v);

        }



    }

    private boolean validCheck() {

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.Beobachtung_linear_layout);

        if(linearLayout.getChildCount() != 0) {

            return true;

        } else {

            return false;

        }

    }
}