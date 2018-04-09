package vorgaenge;

import java.util.ArrayList;
import java.util.List;

import logik.Volk;

/**
 * Created by maxionderon on 23.11.17.
 */

public class Arbeitsvorgang extends Vorgaenge {

    //ID des Arbeitsvorgangs
    private int arbeitsvorgangId;

    //Attribute des Arbeitsvorgangs
    private int anzahlZargen;
    private boolean absperrgitter;
    private int anzahlWaben;
    private String fluglochkeil;
    private ArrayList<Boolean> drohnenrahmen;

    //flags für logik... (spätere rekonstruktion?)
    private boolean fluglochkeilWechsel;
    private boolean absperrgitterWechsel;
    private boolean drohnenrahmenWechsel;

    //Konstruktor
    public Arbeitsvorgang(int arbeitsvorgangid, Volk volk){

        super(volk);

        this.arbeitsvorgangId = arbeitsvorgangid;
        this.anzahlZargen = 0;
        this.absperrgitter = false;
        this.anzahlWaben = 0;
        this.fluglochkeil = "kein";
        this.drohnenrahmen = new ArrayList<Boolean>(10);

        this.fluglochkeilWechsel = false;
        this.absperrgitterWechsel = false;
        this.drohnenrahmenWechsel = false;

    }

    //getMethoden
    public int getArbeitsvorgangId(){

        return this.arbeitsvorgangId;

    }

    public int getAnzahlZargen() {

        return this.anzahlZargen;

    }

    public boolean getAbsperrgitter() {

        return this.absperrgitter;

    }

    public int getAnzahlWaben(){

        return this.anzahlWaben;

    }

    public String getFluglochkeil() {

        return this.fluglochkeil;

    }

    public boolean getDrohenrahmenAt(int i) {

        return this.drohnenrahmen.get(i);

    }

    public ArrayList<Boolean> getDrohnenrahmen() {

        return this.drohnenrahmen;

    }

    public boolean getFluglochkeilWechsel() {

        return this.fluglochkeilWechsel;

    }

    public boolean getAbsperrgitterWechsel() {

        return this.absperrgitterWechsel;

    }

    public boolean getDrohnenrahmenWechsel()  {

        return this.drohnenrahmenWechsel;

    }

    //setMethoden

    public void setArbeitsvorgangID(int arbeitsvorgangID){

        this.arbeitsvorgangId = arbeitsvorgangID;

    }

    public void setAnzahlZargen(int anzahlZargen) {

        this.anzahlZargen = anzahlZargen;

    }

    public void setAbsperrgitter(boolean absperrgitter) {

        this.absperrgitter = absperrgitter;

    }

    public void setAnzahlWaben(int anzahlWaben){

        this.anzahlWaben = anzahlWaben;

    }

    public void setFluglochkeil(String fluglochkeil) {

        this.fluglochkeil = fluglochkeil;

    }

    public void setDrohnenramenAt(int index, boolean value){

        //this.drohnenrahmen.set(index, value);

        this.drohnenrahmen.add(index, value);

    }

    public void setFluglochkeilWechsel(boolean fluglochkeilWechsel) {

        this.fluglochkeilWechsel = fluglochkeilWechsel;

    }

    public void setAbsperrgitterWechsel(boolean absperrgitterWechsel) {

        this.absperrgitterWechsel = absperrgitterWechsel;

    }

    public void setDrohnenrahmenWechsel(boolean drohnenrahmenWechsel) {

        this.drohnenrahmenWechsel = drohnenrahmenWechsel;

    }

    //Arbeitsvorgang Methoden

}
