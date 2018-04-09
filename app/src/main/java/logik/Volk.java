package logik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxionderon on 05.11.17.
 *
 * Attribute/Konstruktor angefangen
 */

public class Volk {

    //Klassen Attribute
    //private static int anzahlVoelker=0;


    //Objekt Attribute
    private enum Typen {WIRTSCHAFTSVOLK, JUNGVOLK, ABLEGER, AUFGELÖßT}

    //Allgemein
    private int volkId;
    private String volkName;
    private String standort;
    private long timestampCreate;
    private long timestampModify;
    private int anzahlZargen;
    private int anzahlWaben;
    private String volkTyp; //JUNGVOLK, WIRTSCHAFTSVOLK
    //Arbeitsvorgang
    private boolean königin = true;
    private boolean absperrgitter;
    private String fluglochkeil; //KEIN, GROSS, KLEIN
    private boolean windel;
    //private int drohnenrahmen;
    private ArrayList<Boolean> posDrohnenrahmen;
    private double mengeHonig;
    private String notiz;
    //Behandlung
    private boolean behandlung;

    //flags für Datenbank
    private boolean isInDatabase;
    private boolean dataHasChanged;

    //Konstruktor
    public Volk(int volkId) {

        //this.volkId = Volk.anzahlVoelker + 1 ;
        //Volk.anzahlVoelker = Volk.anzahlVoelker + 1;

        this.volkId = volkId + 1;

        //Default Name setzen
        this.volkName = "Volk " + ((Integer) this.volkId).toString();
        this.standort = "Keine Angabe";

        this.setTimestampCreate(EBEEAblauf.createTimestamp());
        this.setTimestampModify(this.getTimestampCreate());

        //Default setzen
        this.anzahlZargen = 0;
        this.fluglochkeil = "Kein";
        this.mengeHonig = 0;
        this.notiz = "";

        this.posDrohnenrahmen = new ArrayList<Boolean>();
        this.initPosDrohnenrahmen();

        //flag für db
        this.isInDatabase = false;
        this.dataHasChanged = false;

    }

    //Destruktor

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        //Volk.anzahlVoelker = Volk.anzahlVoelker - 1;
    }


    //get-Methoden

    public int getVolkId() {
        return volkId;
    }

    public String getVolkName() {
        return volkName;
    }

    public String getStandort() {

        return this.standort;

    }

    public long getTimestampCreate() {

        return this.timestampCreate;

    }

    public long getTimestampModify() {

        return this.timestampModify;

    }

    public int getAnzahlZargen() {
        return anzahlZargen;
    }

    public int getAnzahlWaben() {

        return this.anzahlWaben;

    }

    public String getVolkTyp() {
        return volkTyp;
    }

    public boolean getKönigin() {return königin; }

    public boolean getAbsperrgitter() {
        return absperrgitter;
    }

    public String getFluglochkeil() {
        return fluglochkeil;
    }

    public boolean getWindel() {
        return windel;
    }

    public int getDrohnenrahmen() { return this.calcAnzahlDrohnenrahmen(); }

    public double getMengeHonig() {

        return this.mengeHonig;

    }

    public boolean getPosDrohnenrahmen(int pos) {

        return this.posDrohnenrahmen.get(pos);

    }

    public ArrayList<Boolean> getPosDrohnenrahmenList(){

        return this.posDrohnenrahmen;

    }

    public String getNotiz(){

        return this.notiz;

    }

    public boolean getBehandlung() {
        return behandlung;
    }

    public boolean getIsinDatabase() {

        return isInDatabase;
    }

    public boolean getDataHasChanged() {

        return this.dataHasChanged;

    }

    //set-Methoden

    public void setVolkId(int volkId) {
        this.volkId = volkId;
    }

    public void setVolkName(String volkName) {
        this.volkName = volkName;
    }

    public void setStandort(String standort){

        this.standort = standort;

    }

    public void setTimestampCreate(long timestampCreate) {

        this.timestampCreate = timestampCreate;

    }

    public void setTimestampModify(long timestampModify) {

        this.timestampModify = timestampModify;

    }

    public void setAnzahlZargen(int anzahlZargen) {
        this.anzahlZargen = anzahlZargen;
    }

    public void setAnzahlWaben(int anzahlWaben) {

        this.anzahlWaben = anzahlWaben;

    }

    public void setvolkTyp(String volkTyp) {
        this.volkTyp = volkTyp;
    }

    public void setKönigin(boolean königin) { this.königin = königin; }

    public void setAbsperrgitter(boolean absperrgitter) {
        this.absperrgitter = absperrgitter;
    }

    public void setFluglochkeil (String fluglochkeil) {
        this.fluglochkeil = fluglochkeil;
    }

    public void setWindel (boolean windel) {
        this.windel = windel;
    }

    public void setMengeHonig(double mengeHonig) {

        this.mengeHonig = mengeHonig;

    }

    public void setNotiz(String notiz) {

        this.notiz = notiz;

    }

    public void setBehandlung (boolean behandlung) {
        this.behandlung = behandlung;
    }

    public void setIsInDatabase (boolean isInDatabase) {

        this.isInDatabase = isInDatabase;

    }

    public void setDataHasChanged (boolean dataHasChanged ) {

        this.dataHasChanged = dataHasChanged;

    }

    public void copyDrohnenRahmen(ArrayList<Boolean> drohenramhen) {

        this.posDrohnenrahmen.addAll(drohenramhen);

    }

    public void setPosDrohnenrahmenAt(int index, boolean b) {

        this.posDrohnenrahmen.add(index, b);

    }

    //toString Methode vorerst für listView
    @Override
    public String toString(){

        String s =  "| Volksname: " + this.volkName + " |" + "\n" +
                    "| Standort: " + this.standort + " |" + "\n" +
                    "| Typ: " + this.getVolkTyp() + " |" ;

        return s;

    }

    private void initPosDrohnenrahmen() {

        for(int i = 0 ; i != 10 ; i = i + 1) {

            this.posDrohnenrahmen.add(i,false);

        }

    }

    private int calcAnzahlDrohnenrahmen() {

        return EBEEAblauf.calcAnzahlDrohnenrahmen(this.posDrohnenrahmen);

    }

}
