package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import logik.EBEEAblauf;
import vorgaenge.Ernte;

public class Ernten extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ernten);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        Spinner spinner1 = (Spinner) findViewById(R.id.ernte_Spinner_Honigsorte);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Honigsorte, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //Icon setzen
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_ernten);

    }

    public void volkScreenChange(View view) {

        if ( !this.inputIsValid() ) {

            this.toast(getString(R.string.ernten_toast_notValid));

        } else {

            this.saveErnte();

            this.toast(getString(R.string.ernten_toast_dokumentiert));

            startActivity(new Intent(Ernten.this, Volk.class));

        }

    }

    private void saveErnte() {

        Ernte ernte = new Ernte(ebeeAblauf.getErntenAnzahl(), ebeeAblauf.getSelektiertesVolk());

        EditText anzahlWaben = (EditText) findViewById(R.id.ernte_EditText_anzahlWaben);

        ernte.setAnzahlWaben(Integer.parseInt(anzahlWaben.getText().toString()));

        Spinner honigSorte = (Spinner) findViewById(R.id.ernte_Spinner_Honigsorte);

        ernte.setHonigSorte(honigSorte.getSelectedItem().toString());

        EditText wasserGehalt = (EditText) findViewById(R.id.ernten_EditText_wassergehalt);

        ernte.setWassergehalt(Integer.parseInt(wasserGehalt.getText().toString()));

        EditText menge = (EditText) findViewById(R.id.ernten_editText_Menge);

        ernte.setMenge(Double.parseDouble(menge.getText().toString()));

        //volk menge ändern
        this.ebeeAblauf.getSelektiertesVolk().setMengeHonig(this.ebeeAblauf.getSelektiertesVolk().getMengeHonig() + Double.parseDouble(menge.getText().toString()));
        //volk flag aendern
        this.ebeeAblauf.getSelektiertesVolk().setDataHasChanged(true);


        EditText notizen = (EditText) findViewById(R.id.ernten_EditText_notizen);

        ernte.setNotiz(notizen.getText().toString());

        //Flag setzen
        ernte.setIsInDatabase(false);

        this.ebeeAblauf.addErnte(ernte);

        this.ebeeAblauf.listToDatabase();

    }

    private void toast(String string) {

        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    private boolean inputIsValid() {

        boolean valid = true;

        EditText anzahlWaben = (EditText) findViewById(R.id.ernte_EditText_anzahlWaben);

        if(anzahlWaben.getText().toString().equals("")) {

            valid = false;

        }

        EditText wasserGehalt = (EditText) findViewById(R.id.ernten_EditText_wassergehalt);

        if(wasserGehalt.getText().toString().equals("")) {

            valid = false;

        }

        EditText menge = (EditText) findViewById(R.id.ernten_editText_Menge);

        if(menge.getText().toString().equals("")) {

            valid = false;

        }

        return valid;

    }
}