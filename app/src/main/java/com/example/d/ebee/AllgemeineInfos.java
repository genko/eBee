package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import logik.EBEEAblauf;

public class AllgemeineInfos extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allgemeine_infos);

        //Spinner ardessieren
        Spinner spinner1 = (Spinner)findViewById(R.id.allgemine_Infos_VolkstypDropdown);


        //Adapter erstellen
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Volkstyp , android.R.layout.simple_spinner_item);


        //Layout definieren
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Adapter mit Spinner verknüpfen
        spinner1.setAdapter(adapter1);



        //Überschrift zeigt Völkername
        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();
        //this.setSelectedVolk();

        //Spinner selektion abgleich mit eingegebenem Volktyp
        int spinnerPositionVolktyp = adapter1.getPosition(ebeeAblauf.getSelektiertesVolk().getVolkTyp());
        spinner1.setSelection(spinnerPositionVolktyp);


        //überprüfung ob Königinvorhanden
        CheckBox checkBox =(CheckBox) findViewById(R.id.allgemeine_infos_KöniginJaNein);
        if(ebeeAblauf.getSelektiertesVolk().getKönigin())//liefert true wenn vorhanden, sonst false
            checkBox.setChecked(true);
        else checkBox.setChecked(false);

        //Menge des Honigs anzeigen
        EditText mengeHonig = (EditText) findViewById(R.id.allgemine_Infos_editText_SummeHonig);

        mengeHonig.setText(String.valueOf(this.ebeeAblauf.getSelektiertesVolk().getMengeHonig()));


        //Fluglochkeil anzeigen
        TextView fluglochKeil = (TextView) findViewById(R.id.allgemeineInfos_textView_Volktyp);

        fluglochKeil.setText(this.ebeeAblauf.getSelektiertesVolk().getFluglochkeil());


        //Notiz anzeigen

        EditText notiz = (EditText) findViewById(R.id.allgemeine_infos_notizen);

        if(ebeeAblauf.getSelektiertesVolk().getNotiz() != null){

            notiz.setText(ebeeAblauf.getSelektiertesVolk().getNotiz());

        }
        else if (ebeeAblauf.getSelektiertesVolk().getNotiz() == null){
            notiz.setText("");
        }

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());


        //Icon setzen
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_allginfos);

    }

    public void safeAndBackToVolk(View view){

        //Volkstyp setzen
        Spinner volkstypSpinner = (Spinner) findViewById(R.id.allgemine_Infos_VolkstypDropdown);

        ebeeAblauf.getSelektiertesVolk().setvolkTyp(volkstypSpinner.getSelectedItem().toString());

        //Königin setzen
        CheckBox koeniginCheckbox = (CheckBox) findViewById(R.id.allgemeine_infos_KöniginJaNein);

        ebeeAblauf.getSelektiertesVolk().setKönigin(koeniginCheckbox.isChecked());

        //Menge des Honigs setzen
        EditText mengeHonig = (EditText) findViewById(R.id.allgemine_Infos_editText_SummeHonig);

        this.ebeeAblauf.getSelektiertesVolk().setMengeHonig(Double.parseDouble(mengeHonig.getText().toString()));

        //Notizen setzen
        EditText notiz = (EditText) findViewById(R.id.allgemeine_infos_notizen);

        ebeeAblauf.getSelektiertesVolk().setNotiz(notiz.getText().toString());

        //Volk
        ebeeAblauf.getSelektiertesVolk().setTimestampModify(EBEEAblauf.createTimestamp());
        //Flag für Volk setzen
        ebeeAblauf.getSelektiertesVolk().setDataHasChanged(true);
        //update Database
        ebeeAblauf.listToDatabase();

        //screen wechseln
        Intent intent = new Intent(AllgemeineInfos.this,Volk.class);

        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.infos_toast_updateInfo), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        startActivity(intent);


    }

}
