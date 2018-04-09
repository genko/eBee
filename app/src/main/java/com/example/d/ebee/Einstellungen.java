package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import logik.EBEEAblauf;

public class Einstellungen extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();
        initEinstellungen();

        //Icon in die Actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_name);

    }

    private void initEinstellungen(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.Einstellungen_checkBox);
        EditText editEMail = (EditText) findViewById(R.id.Einstellungen_eMail);
        checkBox.setChecked(this.ebeeAblauf.getEinstellungen().getAufgelosteVoelkerAnzeigen());
        editEMail.setText(this.ebeeAblauf.getEinstellungen().getEMailAdresse());

    }

    private void saveEinstellungen(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.Einstellungen_checkBox);
        EditText editEMail = (EditText) findViewById(R.id.Einstellungen_eMail);
        ebeeAblauf.getEinstellungen().setAufgeloesteVoelkerAnzeigen(checkBox.isChecked());
        ebeeAblauf.getEinstellungen().setEMailAdresse(editEMail.getText().toString());

        //Flags für Datenbank setzen
        ebeeAblauf.getEinstellungen().setDataHasChanged(true);
        ebeeAblauf.listToDatabase();
    }

    private void toast() {

        Toast toast = Toast.makeText(getApplicationContext(), "Einstellungen werden gespeichert", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public void HomeChange(View view){
        saveEinstellungen();
        toast();
        startActivity(new Intent(Einstellungen.this, HomeScreen.class));
    }
}
