package vorgaenge;

import logik.Volk;

/**
 * Created by maxionderon on 28.11.17.
 */

public class Ernte extends Vorgaenge {


    //ErnteID für Tabelle
    private int ernteId;

    private int anzahlWaben;
    private String honigSorte;
    private double wassergehalt;
    private double menge;
    private String notiz;


    public Ernte(int ernteId, Volk volk) {

        super(volk);

        this.ernteId = ernteId;

    }

    //get Methoden

    public int getErnteId() {

        return this.ernteId;

    }

    public int getAnzahlWaben () {

        return this.anzahlWaben;

    }

    public String getHonigSorte() {

        return this.honigSorte;

    }

    public double getWassergehalt() {

        return this.wassergehalt;

    }

    public double getMenge() {

        return this.menge;

    }

    public String getNotiz() {

        return this.notiz;

    }

    //set Methoden

    public void setErnteId(int ernteId) {

        this.ernteId = ernteId;

    }

    public void setAnzahlWaben(int anzahlWaben) {

        this.anzahlWaben = anzahlWaben;

    }

    public void setHonigSorte(String honigSorte) {

        this.honigSorte = honigSorte;

    }

    public void setWassergehalt(double wassergehalt) {

        this.wassergehalt = wassergehalt;

    }

    public void setMenge(double menge) {

        this.menge = menge;

    }

    public void setNotiz(String notiz) {

        this.notiz = notiz;
        
    }

}
