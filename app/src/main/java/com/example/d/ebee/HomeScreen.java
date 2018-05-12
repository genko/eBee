package com.example.d.ebee;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.bugsnag.android.Bugsnag;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import logik.EBEEAblauf;


public class HomeScreen extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bugsnag.init(this);

        setContentView(R.layout.activity_home_screen);
        setTitle("Home");
        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        initDatabase();

        //initVolkAuswahlListView();

        initStandortFilter();

        initFilterListener();

        //onItemSelectedListener einbauen
        ListView lv = (ListView) findViewById(R.id.homeScreen_listView_VolkAuswahl);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                logik.Volk volk = (logik.Volk) adapterView.getItemAtPosition(position);

                ebeeAblauf.setSelektiertesVolk(volk);

                volkChange(view);

            }
        });

        GradientDrawable gd = new GradientDrawable();
        //gd.setColor(ContextCompat.getColor(this, R.color.tagebuch_colorArbeitsvorgang));
        gd.setCornerRadius(0);
        gd.setStroke(5, ContextCompat.getColor(this, R.color.homeScreen_colorBorder));

        lv.setBackground(gd);

        //EBEE Icon in Actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_ebeetransparent);
    }

    @Override
    public void onResume() {
        super.onResume();

        initVolkAuswahlListView();
    }

    @Override
    public void onBackPressed(){

        //soll app schließen
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }


    public void ExpImpChange(View view)
    {
        startActivity(new Intent(HomeScreen.this, ExportImport.class));
    }

    public void AllgInfosChange(View view)
    {
        startActivity(new Intent(HomeScreen.this, AllgemeineInfos.class));
    }

    public void InventarChange(View view)
    {
        startActivity(new Intent(HomeScreen.this, Inventar.class));
    }

    public void VolkCreateChange(View view)
    {
        startActivity(new Intent(HomeScreen.this, VolkCreate.class));
    }

    public void volkChange(View view){

        Intent intent = new Intent(HomeScreen.this,Volk.class);
        startActivity(intent);
    }

    public void EinstellungenChange (View view){
        startActivity(new Intent(HomeScreen.this, Einstellungen.class));
    }



    private void initVolkAuswahlListView() {

        //ebeeAblauf.createTestVoelker();

        ListView listView = (ListView) findViewById(R.id.homeScreen_listView_VolkAuswahl);

        ArrayList<logik.Volk> List = ebeeAblauf.getVoelkerListe();

        ArrayList<logik.Volk> filterList = new ArrayList<>();

        if (ebeeAblauf.getEinstellungen().getAufgelosteVoelkerAnzeigen()==false){
            for(int i=0;i!=List.size();i++){
               if(!List.get(i).getVolkTyp().equals(getString(R.string.logik_Volkstyp_Aufgeloest)))
                filterList.add(List.get(i));
             }
        }
        else {
            filterList=List;
        }

        ArrayAdapter<logik.Volk> adapter = getVolkAdapter(filterList);

        listView.setAdapter(adapter);

    }

    private void initDatabase() {

        ebeeAblauf.createListfromDatabase();

    }

    private void initStandortFilter() {

        Set<String> standorte = new TreeSet<>();

        standorte.add(getString(R.string.home_spinner_auswahl_all));

        //Voelker Standorte in Menge speichern
        for(int i = 0 ; i != this.ebeeAblauf.getVoelkerListe().size() ; i = i + 1 ) {

            if(ebeeAblauf.getEinstellungen().getAufgelosteVoelkerAnzeigen() == true) {
                //Alle Völker in Database anzeigen
                standorte.add(this.ebeeAblauf.getVoelkerListe().get(i).getStandort());

            } else {

                if(!this.ebeeAblauf.getVoelkerListe().get(i).getVolkTyp().equals(getString(R.string.logik_Volkstyp_Aufgeloest))) {
                    //aufgelöste nicht anzeigen
                    standorte.add(this.ebeeAblauf.getVoelkerListe().get(i).getStandort());

                }

            }

        }

        Spinner spinner = (Spinner) findViewById(R.id.homeScreen_spinner_Standorte) ;

        String[] auswahl = standorte.toArray(new String[standorte.size()]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, auswahl);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);

    }

    private void initFilterListener() {

        Spinner spinner = (Spinner) findViewById(R.id.homeScreen_spinner_Standorte);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int k, long l) {

                if(!adapterView.getSelectedItem().toString().equals(getString(R.string.home_spinner_auswahl_all))){

                    ArrayList<logik.Volk> filterVolkList = new ArrayList<>();

                    for(int i = 0; i != ebeeAblauf.getVoelkerListe().size(); i = i + 1) {

                        if(adapterView.getSelectedItem().toString().equals(ebeeAblauf.getVoelkerListe().get(i).getStandort())) {

                            if(ebeeAblauf.getEinstellungen().getAufgelosteVoelkerAnzeigen() == true) {
                                //sämtliche völker am Standort anzeigen
                                filterVolkList.add(ebeeAblauf.getVoelkerListe().get(i));

                            } else {
                                //nicht aufgelöste Völker anzeigen
                                if(!ebeeAblauf.getVoelkerListe().get(i).getVolkTyp().equals(getString(R.string.logik_Volkstyp_Aufgeloest))) {

                                    filterVolkList.add(ebeeAblauf.getVoelkerListe().get(i));

                                }


                            }



                        }

                    }

                    ListView listView = (ListView) findViewById(R.id.homeScreen_listView_VolkAuswahl);

                    ArrayAdapter<logik.Volk> adapter = getVolkAdapter(filterVolkList);

                    listView.setAdapter(adapter);

                }else {

                    initVolkAuswahlListView();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private ArrayAdapter<logik.Volk> getVolkAdapter(ArrayList<logik.Volk> list) {

        ArrayAdapter<logik.Volk> adapter = new ArrayAdapter<logik.Volk>(this, android.R.layout.simple_expandable_list_item_1, list) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);

                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();

                // Set the height of the Item View

                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                return view;
            }

        };

        return adapter;

    }

}

