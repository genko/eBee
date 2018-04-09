package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import logik.EBEEAblauf;

public class VolkCreate extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;

    //VolkCreate Teil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volk_create);

        //Spinner adressieren
        Spinner spinner1 = (Spinner)findViewById(R.id.VolktypAuswahl);

        //Adapter erstellen
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Volkstyp , android.R.layout.simple_spinner_item);

        //Layout anpassen
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adapter mit Spinner verknüpfen
        spinner1.setAdapter(adapter1);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Icon in die Actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_plus);
    }

    public void volkChange(View view)
    {
        Intent intent = new Intent(VolkCreate.this, Volk.class);

        startActivity(intent);
    }

    public void toast() {
        Toast toast = Toast.makeText(getApplicationContext(), "Volk wurde erstellt", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    //Methode zum erstellen eines Volks

    public void createVolk(View view) {

        logik.Volk volk = new logik.Volk(ebeeAblauf.getVoelkerAnzahl());

        this.setVolkNameFromActivity(volk);
        this.setVolkStandortFromActivity(volk);
        this.setVolkTypFromActivity(volk);

        volk.setDataHasChanged(true);
        volk.setIsInDatabase(false);
        //Volk in Liste einfügen
        this.ebeeAblauf.addVolk(volk);
        //Liste in Database schreiben aktuallisieren
        this.ebeeAblauf.listToDatabase();

        //Selektiertes Volk setzen
        this.ebeeAblauf.setSelektiertesVolk(volk);

        toast();

        //wechsel in Volk screen
        this.volkChange(view);

    }

    private void setVolkNameFromActivity(logik.Volk volk){

        //hole den Namen
        EditText nameEingabe = (EditText) findViewById(R.id.NameEingabe);

        String volkName = nameEingabe.getText().toString();

        if(!volkName.equals("")){

            //setze den Namen
            volk.setVolkName(volkName);

        }


    }

    private void setVolkStandortFromActivity(logik.Volk volk) {

        //Standort holen
        EditText standortEingabe = (EditText) findViewById(R.id.volkCreate_EditText_Standort);

        String volkStandort = standortEingabe.getText().toString();

        if(!volkStandort.equals("")) {

            //standort setzen
            volk.setStandort(volkStandort);

        }

    }

    private void setVolkTypFromActivity(logik.Volk volk){

        //hole Volktyp
        Spinner volkTypAuswahl = (Spinner) findViewById(R.id.VolktypAuswahl);

        String volkTyp = volkTypAuswahl.getSelectedItem().toString();

        volk.setvolkTyp(volkTyp);

    }

}
