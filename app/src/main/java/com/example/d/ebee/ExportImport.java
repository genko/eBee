package com.example.d.ebee;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import importExport.Export;
import logik.EBEEAblauf;

/**
 * Created by D on 17.12.2017.
 */

public class ExportImport extends AppCompatActivity {

    private EBEEAblauf ebeeAblauf;
    private String xml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);

        this.ebeeAblauf = (EBEEAblauf) getApplicationContext();

        //Titel dynamisch generieren
        setTitle("Export");

        Button b = (Button) findViewById(R.id.export);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    export(ebeeAblauf.getApplicationContext(), v);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void export(Context context, View v) throws IOException {
        Export export = new Export(ebeeAblauf.getVoelkerListe(), ebeeAblauf.getInventar(),
                ebeeAblauf.getBehandlungsListe(), ebeeAblauf.getBeobachtungListe(),
                ebeeAblauf.getErnteListe(), ebeeAblauf.getArbeitsvorgangliste(),
                ebeeAblauf.getEinstellungen());
        this.xml = export.xmlExport();

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        this.onRequestPermissionsResult(1,null,null);

        Toast toast = Toast.makeText(getApplicationContext(), "Datei erstellt", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();


        HomeChange(v);


        File filelocation = new File("/sdcard/EBEE/ebee.xml");
        Uri path = Uri.fromFile(filelocation);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{this.ebeeAblauf.getEinstellungen().getEMailAdresse()});
        i.putExtra(Intent.EXTRA_SUBJECT, "Daten Export EBEE");
        i.putExtra(Intent.EXTRA_TEXT   , "Hier sind die Daten deiner EBEE App");
        i.putExtra(Intent.EXTRA_STREAM , path);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

/*
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EBEE";

        try {
            new File(path).mkdir();
            File file = new File(path+ "ebee.xml");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write((xml.getBytes()));
        }  catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }  catch(IOException ex) {
            ex.printStackTrace();
        }
        /*
        File dir = new File(path);
        dir.mkdirs();
        File file = new File(path, "ebee.xml");


        FileOutputStream fos = new FileOutputStream(file);
        fos = openFileOutput("ebee.xml", Context.MODE_PRIVATE);

        fos.write(xml.getBytes());
        fos.close();


        File file = new File(xml, "ebeeExport.xml");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent .setType("vnd.android.cursor.dir/email");

        emailIntent .putExtra(Intent.EXTRA_EMAIL,"d.cantz@hotmail.de");
        // the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, file);
        // the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");



        startActivity(Intent.createChooser(emailIntent , "Send email..."));
        */


    }

    public void HomeChange(View view)
    {
        startActivity(new Intent(ExportImport.this, HomeScreen.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        File ebeeDirectory = new File("/sdcard/EBEE/");
        // have the object build the directory structure, if needed.
        ebeeDirectory.mkdirs();

        File ebeeXML = new File("/sdcard/EBEE/ebee.xml");

        try {
            ebeeXML.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(ebeeXML);
            fileOutputStream.write(this.xml.getBytes());
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
