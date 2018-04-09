package com.example.d.ebee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import logik.EBEEAblauf;

public class Volk extends AppCompatActivity {


    private EBEEAblauf ebeeAblauf;
    //Activity Volk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volk);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();
        setTitle(ebeeAblauf.getSelektiertesVolk().getVolkName());

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(Volk.this,HomeScreen.class);

        startActivity(intent);

    }

    public void AllgInfosChange(View view)
    {
        startActivity(new Intent(Volk.this, AllgemeineInfos.class));
    }

    public void BehandlungChange(View view)
    {
        startActivity(new Intent(Volk.this, Behandlung.class));
    }

    public void BeobachtungChange(View view)
    {
        startActivity(new Intent(Volk.this, Beobachtung.class));
    }

    public void ErntenChange(View view)
    {
        startActivity(new Intent(Volk.this, Ernten.class));
    }

    public void ArbeitsvorgangChange(View view)
    {
        startActivity(new Intent(Volk.this, Arbeitsvorgang.class));
    }

    public void TagebuchChange(View view)
    {
        startActivity(new Intent(Volk.this, Tagebuch.class));
    }




}
