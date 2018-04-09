package logik;

import android.app.Application;
import android.content.Context;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datenbank.Datenbank;
import inventar.Inventar;
import tagebuch.Tagebuch;
import vorgaenge.Arbeitsvorgang;
import vorgaenge.Behandlung;
import vorgaenge.Beobachtung;
import vorgaenge.Ernte;

/**
 * Created by maxionderon on 15.11.17.
 * besser als service und einfacher Service wird gelöscht
 * EBEEAblauf objekte
 */

public class EBEEAblauf extends Application {


    //Attribute
    //Datenbank
    Datenbank datenbank;
    //Inventar
    Inventar inventar;
    //Tagebuch
    Tagebuch tagebuch;
    //Einstellungen
    Einstellungen einstellungen;
    //ArrayListe für die Völker
    ArrayList<Volk> voelkerListe;
    //ArrayListe für Arbeitsvorgänge
    ArrayList<Arbeitsvorgang> arbeitsvorgangListe;
    //ArrayListe für Behandlungen
    ArrayList<Behandlung> behandlungListe;
    //ArrayListe für Beobachtung
    ArrayList<Beobachtung> beobachtungListe;
    //ArrayListe für Ernten
    ArrayList<Ernte> ernteListe;

    //selektiertes Volk
    Volk selektiertesVolk;

    //Konstruktor

    EBEEAblauf(){

        this.voelkerListe = new ArrayList<Volk>();
        this.arbeitsvorgangListe = new ArrayList<Arbeitsvorgang>();
        this.behandlungListe = new ArrayList<Behandlung>();
        this.beobachtungListe = new ArrayList<Beobachtung>();
        this.ernteListe = new ArrayList<Ernte>();
        this.datenbank = new Datenbank(this);
        this.tagebuch = new Tagebuch();
        this.selektiertesVolk = null;
        this.inventar = new Inventar(1);
        this.einstellungen = new Einstellungen(1);

    }

    //get-Methoden

    public Datenbank getDatenbank() {
        return datenbank;
    }

    public ArrayList<Volk> getVoelkerListe() {
        return voelkerListe;
    }

    public ArrayList<Beobachtung> getBeobachtungListe() {
        return beobachtungListe;
    }

    public ArrayList<Ernte> getErnteListe() {
        return ernteListe;
    }

    public ArrayList<Arbeitsvorgang> getArbeitsvorgangliste() {
        return arbeitsvorgangListe;
    }

    public Volk getSelektiertesVolk(){ return selektiertesVolk; }

    public Tagebuch getTagebuch() {
        return tagebuch;
    }

    public Inventar getInventar() {

        return this.inventar;

    }

    public Einstellungen getEinstellungen() { return einstellungen; }

    public ArrayList<Behandlung> getBehandlungsListe(){
        return this.behandlungListe;
    }

    public void setSelektiertesVolk(Volk volk) {

        this.selektiertesVolk = volk;

    }

    public void addVolk(Volk volk) {

        this.voelkerListe.add(volk);

    }

    public void addBeobachtung(Beobachtung beobachtung){

        this.beobachtungListe.add(beobachtung);

    }

    public void addArbeitsvorgang(Arbeitsvorgang arbeitsvorgang) {

        this.arbeitsvorgangListe.add(arbeitsvorgang);

    }

    public void addErnte(Ernte ernte) {

        this.ernteListe.add(ernte);

    }

    public void clearList(){

        this.voelkerListe.clear();

    }

    //schnitstelle für Datenbank --Anfang
    public void listToDatabase(){

        this.volkListToDatabase();
        this.behandlungListToDatabase();
        this.beobachtungListToDatabase();
        this.arbeitsvorgangListToDatabase();
        this.ernteListToDatabase();
        //einstellungen speichern
        this.einstellungenToDatabase();
        this.inventarToDatabase();

    }

    private void volkListToDatabase() {

        for( int i = 0; i != this.voelkerListe.size(); i = i + 1 ) {

            if(this.voelkerListe.get(i).getIsinDatabase() == false ) {
                //in Datenbank schreiben
                this.datenbank.insertVolk(this.voelkerListe.get(i));
                //Volk Flag setzen
                this.voelkerListe.get(i).setIsInDatabase(true);


            }else if (this.voelkerListe.get(i).getDataHasChanged() == true ){

                //update_methode muss noch geschrieben werden
                this.datenbank.updateVolk(this.voelkerListe.get(i));
                //Volk Flag setzen
                this.voelkerListe.get(i).setDataHasChanged(false);

            }
        }

    }

    private void behandlungListToDatabase() {

        for( int i = 0 ; i != this.behandlungListe.size() ; i = i + 1 ) {

            if(this.behandlungListe.get(i).getIsInDatabase() == false) {

                this.datenbank.insertBehandlung(this.behandlungListe.get(i));

                this.behandlungListe.get(i).setIsInDatabase(true);

            } else if (this.behandlungListe.get(i).getDataHasChanged() == true){

                this.datenbank.updateBehandlung(this.behandlungListe.get(i));

                this.behandlungListe.get(i).setDataHasChanged(false);

            }

        }

    }

    private void beobachtungListToDatabase() {

        for( int i = 0 ; i != this.beobachtungListe.size() ; i = i + 1 ) {

            if(this.beobachtungListe.get(i).getIsInDatabase() == false) {
                //Beobachtung in Datenbank
                this.datenbank.insertBeobachtung(this.beobachtungListe.get(i));
                //Flag setzen
                this.beobachtungListe.get(i).setIsInDatabase(true);
                //Teilbeobachtung Liste in Datenbank
                this.teilBeobachtungListToDatabase(this.beobachtungListe.get(i).getTeilBeobachtungList());

            } else if (this.beobachtungListe.get(i).getDataHasChanged() == true ) {
                //Beobachtung updaten
                this.datenbank.updateBeobachtung(this.beobachtungListe.get(i));
                //Flag setzen
                this.beobachtungListe.get(i).setDataHasChanged(false);
                //Teil Beobachtung Liste upaten
                this.teilBeobachtungListToDatabase(this.beobachtungListe.get(i).getTeilBeobachtungList());

            }

        }


    }

    private void teilBeobachtungListToDatabase(ArrayList<vorgaenge.TeilBeobachtung> teilBeobachtungList) {

        for( int i = 0 ; i != teilBeobachtungList.size() ; i = i + 1 ) {

            if(teilBeobachtungList.get(i).getIsInDatabase() == false ) {
                //TeilBeobachtung in Database
                this.datenbank.insertTeilBeobachtung(teilBeobachtungList.get(i));
                //flag setzen
                teilBeobachtungList.get(i).setIsInDatabase(true);

            } else if(teilBeobachtungList.get(i).getDataHasChanged() == true) {
                //Teilbeobachtung updaten
                this.datenbank.updateTeilBeobachtung(teilBeobachtungList.get(i));
                //flag setzen
                teilBeobachtungList.get(i).setDataHasChanged(false);

            }

        }

    }

    private void arbeitsvorgangListToDatabase() {

        for( int i = 0; i != this.arbeitsvorgangListe.size() ; i = i + 1 ) {

            if(this.arbeitsvorgangListe.get(i).getIsInDatabase() == false) {

                this.datenbank.insertArbeitsvorgang(this.arbeitsvorgangListe.get(i));
                //Flags setzen
                this.arbeitsvorgangListe.get(i).setIsInDatabase(true);

            } else if( this.arbeitsvorgangListe.get(i).getDataHasChanged() == true) {

                this.datenbank.updateArbeitsvorgang(this.arbeitsvorgangListe.get(i));

                this.arbeitsvorgangListe.get(i).setDataHasChanged(false);

            }

        }

    }

    private void ernteListToDatabase() {

        for( int i = 0 ; i != ernteListe.size() ; i = i + 1 ) {

            if(this.ernteListe.get(i).getIsInDatabase() == false) {

                this.datenbank.insertErnte(this.ernteListe.get(i));
                //Flags setzen
                this.ernteListe.get(i).setIsInDatabase(true);

            }else if (this.ernteListe.get(i).getDataHasChanged() == false) {

                this.datenbank.updateErnte(this.ernteListe.get(i));

                this.ernteListe.get(i).setDataHasChanged(false);

            }
        }

    }

    private void einstellungenToDatabase() {

        if(this.einstellungen.getIsinDatabase() == false) {

            this.datenbank.insertEinstellungen(this.einstellungen);

            //flag setzen
            this.einstellungen.setIsInDatabase(true);

        } else if ( this.einstellungen.getDataHasChanged() == true) {

            this.datenbank.updateEinstellungen(this.einstellungen);

            //flag setzen
            this.einstellungen.setDataHasChanged(false);

        }

    }

    private void inventarToDatabase() {

        if(this.inventar.getIsinDatabase() == false) {

            this.datenbank.insertInventar(this.inventar);

            this.inventar.setIsInDatabase(true);

        } else if( this.inventar.getDataHasChanged() == true ){

            this.datenbank.updateInventar(this.inventar);

            this.inventar.setDataHasChanged(false);

        }

    }

    public void createListfromDatabase(){

        this.voelkerListe = this.datenbank.getVolkList();
        this.behandlungListe = this.datenbank.getBehandlungList(this.voelkerListe);
        this.beobachtungListe = this.datenbank.getBeobachtungList(this.voelkerListe);
        this.arbeitsvorgangListe = this.datenbank.getArbeitsvorgaengeList(this.voelkerListe);
        this.ernteListe = this.datenbank.getErntenList(this.voelkerListe);
        //Einstellungen von Database
        this.einstellungen = this.datenbank.getEinstellungen();
        this.inventar = this.datenbank.getInventar();

    }

    public int getVoelkerAnzahl(){

        return this.datenbank.getVoelkerAnzahl();

    }

    public int getBehandlungAnzahl() {

        return this.datenbank.getBehandlungAnzahl();

    }

    public int getBeobachtungenAnzahl() {

        return this.datenbank.getBeobachtungAnzahl();

    }

    public int getTeilBeobachtungenAnzahl() {

        return this.datenbank.getTeilBeobachtungAnzahl();

    }

    public int getArbeitsvorgangAnzahl() {

        return this.datenbank.getArbeitsvorgaengeAnzahl();

    }

    public int getErntenAnzahl() {

        return this.datenbank.getErntenAnzahl();

    }
    //schnittstelle für Datenbank --Ende

    //schnittstelle für Tagebuch --Anfang
    public void initTagebuchAll(){

        this.initTagebuchVolk();
        this.initTagebuchArbeitsvorgang();
        this.initTagebuchBehandlung();
        this.initTagebuchBeobachtung();
        this.initTagebuchErnte();

    }

    public void initTagebuchArbeitsvorgang(){

        this.tagebuch.createTagebuchListeFromArbeitsvorgangListe(this.arbeitsvorgangListe, this.selektiertesVolk);

    }

    public void initTagebuchBehandlung(){

        this.tagebuch.createTagebuchListeFromBehandlungListe(this.behandlungListe, this.selektiertesVolk);

    }

    public void initTagebuchBeobachtung(){

        this.tagebuch.createTagebuchListeFromBeobachtung(this.beobachtungListe, this.selektiertesVolk);

    }

    public void initTagebuchVolk() {

        this.tagebuch.createTagebuchListeFromVolkListe(this.voelkerListe, this.selektiertesVolk);

    }

    public void initTagebuchErnte() {

        this.tagebuch.createTagebuchListFromErnte(this.ernteListe, this.selektiertesVolk);

    }

    public void clearTagebuch(){

        this.tagebuch.clearTagebuchListe();

    }
    //schnittstelle für Tagbuch --Ende

    //Schnittstelle für Behandlung --Anfang
    public void addBehandlung(Behandlung behandlung){

        this.behandlungListe.add(behandlung);

    }

    //Schnittstelle für Behandlung --Ende

    //schnitstelle für Inventar --Anfang

    //schnittstelle für Inventar --Ende



    //Timstamp Methode

    static public long createTimestamp() {

        Date date = new Date();

        return date.getTime();

    }

    static public String createDateStringFromTimestamp(long timestamp) {

        Date date = new Date(timestamp);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        return simpleDateFormat.format(date);

    }

    static public int calcAnzahlDrohnenrahmen(ArrayList<Boolean> list) {

        int anzDrohnenrahmen = 0;

        for( int i = 0 ; i != list.size() ; i = i + 1 ) {

            if(list.get(i) == true) {

                anzDrohnenrahmen = anzDrohnenrahmen + 1;

            }

        }

        return anzDrohnenrahmen;


    }





}

