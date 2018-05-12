package com.example.d.ebee;

import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import logik.EBEEAblauf;
import tagebuch.TagebuchListenElement;

public class Tagebuch extends AppCompatActivity {

    private logik.EBEEAblauf ebeeAblauf;
    private ArrayList<TextView> linearLayoutInhalt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagebuch);

        //Ablauf setzen
        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        this.linearLayoutInhalt=new ArrayList<TextView>();
        this.initSpinner();
        this.initFilterListener();

        //Icon setzen
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_tagebuch);

    }

    @Override
    public void onBackPressed(){

        //linearLayout clearen
        this.ebeeAblauf.clearTagebuch();

        Intent intent = new Intent(Tagebuch.this,Volk.class);

        startActivity(intent);

    }

    private void initLinearLayout(){

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tagebuch_LinearLayout_Eintraege);

        for(int i = 0 ; i != this.linearLayoutInhalt.size() ; i = i + 1 ) {

            linearLayout.addView(this.linearLayoutInhalt.get(i),i);

        }

    }

    private void buildLinearLayoutInhalt(){

        ArrayList<TagebuchListenElement> list = this.ebeeAblauf.getTagebuch().getTagebuchListe();

        Collections.sort(list);

        for(int i = 0 ; i != list.size() ; i = i + 1 ){

            TextView textView = list.get(i).getTextView(this);

            this.linearLayoutInhalt.add(textView);

        }

    }

    private void clearLinearLayout(){

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tagebuch_LinearLayout_Eintraege);

        linearLayout.removeAllViews();

    }

    private void initSpinner() {


        String[] auswahl = {getString(R.string.tagebuch_filterAuswahl_all),getString(R.string.tagebuch_filterAuswahl_arbeitsvorgang),getString(R.string.tagebuch_filterAuswahl_behandlung),getString(R.string.tagebuch_filterAuswahl_beobachtung),getString(R.string.tagebuch_filterAuswahl_volk),getString(R.string.tagebuch_filterAuswahl_ernte)};

        Spinner spinner = (Spinner) findViewById(R.id.tagebuch_Spinner_Filter);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,auswahl);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);

    }

    private void initFilterListener(){

        Spinner spinner = (Spinner) findViewById(R.id.tagebuch_Spinner_Filter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ebeeAblauf.clearTagebuch();
                linearLayoutInhalt.clear();
                clearLinearLayout();

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_all))){

                    ebeeAblauf.initTagebuchAll();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_arbeitsvorgang))){

                    ebeeAblauf.initTagebuchArbeitsvorgang();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_behandlung))){

                    ebeeAblauf.initTagebuchBehandlung();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_beobachtung))){

                    ebeeAblauf.initTagebuchBeobachtung();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_volk))){

                    ebeeAblauf.initTagebuchVolk();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

                if(adapterView.getSelectedItem().toString().equals(getString(R.string.tagebuch_filterAuswahl_ernte))) {

                    ebeeAblauf.initTagebuchErnte();
                    buildLinearLayoutInhalt();
                    initLinearLayout();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
