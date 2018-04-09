package com.example.d.ebee;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import logik.EBEEAblauf;

public class Inventar extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    private inventar.Inventar inventar;
    private boolean bearbeiten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventar);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();
        this.inventar = this.ebeeAblauf.getInventar();
        this.bearbeiten = false;

        //Icon in die Actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_inventar);

        this.initInventar();

    }

    @Override
    protected void onResume(){

        super.onResume();

        this.initInventar();
        this.bearbeiten = false;

    }


    public void bearbeitenOderSpeichern(View view) {

        if(this.bearbeiten == false) {

            this.bearbeiten = true;

            this.makeAllEditTextEditable();

            Button button = (Button) findViewById(R.id.inventar_button_BearbeitenOrSpeichern);
            button.setText(R.string.inventar_speichern);

            button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_action_save,0);

            this.toast(getString(R.string.inventar_toast_mody));

        } else {

            this.bearbeiten = false;

            this.makeAllEditTextUnEditable();

            Button button = (Button) findViewById(R.id.inventar_button_BearbeitenOrSpeichern);
            button.setText(R.string.inventar_bearbeiten);

            button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_action_edit,0);

            //EditTextInhaltSpeichern
            this.saveToInventar();
            //flag setzen
            this.inventar.setDataHasChanged(true);
            this.ebeeAblauf.listToDatabase();

            this.toast(getString(R.string.inventar_toast_save));

        }


    }

    private void initInventar() {

        this.setAllEditTexts();
        this.setAllTextViews();

    }

    private void setAllEditTexts() {

        //gegenstaende werden reused
        this.setEditText(R.id.inventar_editText_Absperrgitter_ImLager, String.valueOf(this.inventar.getAbsperrgitter() - this.inventar.calcAbsperrgitterInUse(this.ebeeAblauf.getVoelkerListe(), this) ));
        this.setEditText(R.id.inventar_editText_Fluglochkeil_ImLager, String.valueOf(this.inventar.getFluglochkeil() - this.inventar.calcFluglochkeilInUse(this.ebeeAblauf.getVoelkerListe(), this) ));
        this.setEditText(R.id.inventar_editText_Mittelwaende_ImLager, String.valueOf(this.inventar.getMittelwaende() - this.inventar.calcMittelwaendeInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setEditText(R.id.inventar_editText_Rahmen_ImLager, String.valueOf(this.inventar.getRahmen() - this.inventar.calcRahmenInUse(this.ebeeAblauf.getVoelkerListe(), this)  ));
        this.setEditText(R.id.inventar_editText_Windel_ImLager, String.valueOf(this.inventar.getWindel() - this.inventar.calcWindelInUse(this.ebeeAblauf.getVoelkerListe(), this) ));
        this.setEditText(R.id.inventar_editText_Zargen_ImLager, String.valueOf(this.inventar.getZargen() - this.inventar.calcZargenInUse(this.ebeeAblauf.getVoelkerListe(), this) ));

        //sauren werden verbraucht
        this.setEditText(R.id.inventar_editText_Ameisensaeure_ImLager, String.valueOf(this.inventar.getAmeisensaeure() - this.inventar.calcAmeisensaeureInUse(this.ebeeAblauf.getBehandlungsListe(),this)));
        this.setEditText(R.id.inventar_editText_Milchsaeure_ImLager, String.valueOf(this.inventar.getMilchsaeure() - this.inventar.calcMilchsaeureInUse(this.ebeeAblauf.getBehandlungsListe(),this)));
        this.setEditText(R.id.inventar_editText_Oxalsaeure_ImLager, String.valueOf(this.inventar.getOxalsaeure() - this.inventar.calcOxalsaeureInUse(this.ebeeAblauf.getBehandlungsListe(),this)));

    }

    private void setAllTextViews() {

        this.setTextView(R.id.inventar_textView_Absperrgitter_ImEinsatz, String.valueOf(this.inventar.calcAbsperrgitterInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setTextView(R.id.inventar_textView_Fluglochkeil_ImEinsatz, String.valueOf(this.inventar.calcFluglochkeilInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setTextView(R.id.inventar_textView_Mittelwaende_ImEinsatz, String.valueOf(this.inventar.calcMittelwaendeInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setTextView(R.id.inventar_textView_Rahmen_ImEinsatz, String.valueOf(this.inventar.calcRahmenInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setTextView(R.id.inventar_textView_Windel_ImEinsatz, String.valueOf(this.inventar.calcWindelInUse(this.ebeeAblauf.getVoelkerListe(), this)));
        this.setTextView(R.id.inventar_textView_Zargen_ImEinsatz, String.valueOf(this.inventar.calcZargenInUse(this.ebeeAblauf.getVoelkerListe(), this)));

        //saeuren
        this.setTextView(R.id.inventar_textView_Ameisensaeure_ImEinsatz, String.valueOf(this.inventar.calcAmeisensaeureInUse(this.ebeeAblauf.getBehandlungsListe(), this)));
        this.setTextView(R.id.inventar_textView_Milchsaeure_ImEinsatz, String.valueOf(this.inventar.calcMilchsaeureInUse(this.ebeeAblauf.getBehandlungsListe(), this)));
        this.setTextView(R.id.inventar_textView_Oxalsaeure_ImEinsatz, String.valueOf(this.inventar.calcOxalsaeureInUse(this.ebeeAblauf.getBehandlungsListe(), this)));


    }

    private void setEditText(int id, String text) {

        EditText editText = (EditText) findViewById(id);
        editText.setText(text);

    }

    private void setTextView(int id, String text) {

        TextView textView = (TextView) findViewById(id);
        textView.setText(text);

    }

    private void makeAllEditTextEditable() {

        this.makeEditTextEditable(R.id.inventar_editText_Zargen_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Windel_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Rahmen_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Oxalsaeure_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Mittelwaende_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Milchsaeure_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Fluglochkeil_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Ameisensaeure_ImLager);
        this.makeEditTextEditable(R.id.inventar_editText_Absperrgitter_ImLager);

    }

    private void makeAllEditTextUnEditable() {

        this.makeEditTextUnEditable(R.id.inventar_editText_Zargen_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Windel_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Rahmen_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Oxalsaeure_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Mittelwaende_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Milchsaeure_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Fluglochkeil_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Ameisensaeure_ImLager);
        this.makeEditTextUnEditable(R.id.inventar_editText_Absperrgitter_ImLager);

    }

    private void makeEditTextEditable(int id) {

        EditText editText = (EditText) findViewById(id);

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);

        editText.setBackgroundColor(ContextCompat.getColor(this, R.color.inventar_colorMody));

    }

    private void makeEditTextUnEditable(int id) {

        EditText editText = (EditText) findViewById(id);

        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);

        editText.setBackgroundColor(Color.TRANSPARENT);

    }

    private void saveToInventar() {

        this.inventar.setZargen(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Zargen_ImLager)) + this.inventar.calcZargenInUse(this.ebeeAblauf.getVoelkerListe(), this));
        this.inventar.setWindel(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Windel_ImLager)) + this.inventar.calcWindelInUse(this.ebeeAblauf.getVoelkerListe(), this));
        this.inventar.setRahmen(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Rahmen_ImLager)) + this.inventar.calcRahmenInUse(this.ebeeAblauf.getVoelkerListe(), this));
        this.inventar.setMittelwaende(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Mittelwaende_ImLager)) + this.inventar.calcMittelwaendeInUse(this.ebeeAblauf.getVoelkerListe(), this));
        this.inventar.setFluglochkeil(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Fluglochkeil_ImLager)) + this.inventar.calcFluglochkeilInUse(this.ebeeAblauf.getVoelkerListe(), this));
        this.inventar.setAbsperrgitter(Integer.parseInt(getStringFromEditText(R.id.inventar_editText_Absperrgitter_ImLager)) + this.inventar.calcAbsperrgitterInUse(this.ebeeAblauf.getVoelkerListe(), this));

        //Saeuren werden verbraucht
        this.inventar.setOxalsaeure(Float.parseFloat(getStringFromEditText(R.id.inventar_editText_Oxalsaeure_ImLager)));
        this.inventar.setMilchsaeure(Float.parseFloat(getStringFromEditText(R.id.inventar_editText_Milchsaeure_ImLager)));
        this.inventar.setAmeisensaeure(Float.parseFloat(getStringFromEditText(R.id.inventar_editText_Ameisensaeure_ImLager)));


    }

    private String getStringFromEditText(int id) {

        EditText editText = (EditText) findViewById(id);

        return editText.getText().toString();

    }

    private void toast(String string) {

        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

}
