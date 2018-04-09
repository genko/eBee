package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import logik.*;
import logik.Volk;


public class Behandlung extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    private boolean behandlungIsActiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behandlung);
        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Titel dynamisch generieren
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

        initBehandlungListView();
        initBehandlungButton();

        //icon setzen
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_behandlung);
    }

    public void NeueBehandlungChange(View view)
    {

        if(this.behandlungIsActiv == false) {

            startActivity(new Intent(Behandlung.this, NeueBehandlung.class));

        } else {

            ListView listView = (ListView) findViewById(R.id.behandlung_listView);
            vorgaenge.Behandlung behandlung = (vorgaenge.Behandlung) listView.getAdapter().getItem(0);

            behandlung.setIsActiv(false);
            behandlung.setTimestampFinish(EBEEAblauf.createTimestamp());

            behandlung.setDataHasChanged(true);
            ebeeAblauf.listToDatabase();


            listView.setAdapter(null);
            initBehandlungListView();
            initBehandlungButton();
            toastCancel();
        }

    }

    private void initBehandlungListView() {

        ListView listView = (ListView) findViewById(R.id.behandlung_listView);

        ArrayList<vorgaenge.Behandlung> list = new ArrayList<>();

        for (int i =0; i!= ebeeAblauf.getBehandlungsListe().size(); i++) {
            if(ebeeAblauf.getSelektiertesVolk().getVolkId() == ebeeAblauf.getBehandlungsListe().get(i).getVolkId() && ebeeAblauf.getBehandlungsListe().get(i).getIsActiv() == true){
                list.add(ebeeAblauf.getBehandlungsListe().get(i));
            }
        }

        ArrayAdapter<vorgaenge.Behandlung> adapter = getVolkAdapter(list);

        listView.setAdapter(adapter);
    }

    private ArrayAdapter<vorgaenge.Behandlung> getVolkAdapter(ArrayList<vorgaenge.Behandlung> list) {

        ArrayAdapter<vorgaenge.Behandlung> adapter = new ArrayAdapter<vorgaenge.Behandlung>(this, android.R.layout.simple_expandable_list_item_1, list) {

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

    private void initBehandlungButton() {

        ListView listView = (ListView) findViewById(R.id.behandlung_listView);

        Button button = (Button) findViewById(R.id.behandlung_Button_NeueBehandlungOderAbbrechen);

        if(listView.getAdapter().isEmpty() == false) {

            this.behandlungIsActiv = true;
            button.setText(R.string.behandlung_behandlungBeenden);
            //button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_action_plus,0);


        } else {

            this.behandlungIsActiv = false;
            button.setText(R.string.behandlung_neueBehandlung);
            button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_action_plus,0);

        }

    }
    public void toastCancel() {
        Toast toastCancel = Toast.makeText(getApplicationContext(), R.string.behandlung_toast_cancel, Toast.LENGTH_SHORT);
        toastCancel.setGravity(Gravity.CENTER, 0, 0);
        toastCancel.show();
    }
}
