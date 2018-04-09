package vorgaenge;

/**
 * Created by maxionderon on 24.11.17.
 */

public class TeilBeobachtung {

    //referenz als Fremdschlüssel
    private Beobachtung beobachtung;

    //Unterbeobachtungs ID
    private int teilBeobachtungID;

    //Attribute
    private String artDerBeobachtung;
    private String notiz;

    //Flags für Datenbank
    private boolean isInDatabase;
    private boolean dataHasChanged;

    //konstruktor
    public TeilBeobachtung(Beobachtung beobachtung){

        this.beobachtung = beobachtung;

    }

    //set-Methoden

    public void setTeilBeobachtungID(int teilBeobachtungID) {

        this.teilBeobachtungID = teilBeobachtungID;

    }

    public void setArtDerBeobachtung(String artDerBeobachtung){

        this.artDerBeobachtung = artDerBeobachtung;

    }

    public void setNotiz(String notiz) {

        this.notiz = notiz;

    }

    public void setIsInDatabase(boolean isInDatabase) {

        this.isInDatabase = isInDatabase;

    }

    public void setDataHasChanged(boolean dataHasChanged) {

        this.dataHasChanged = dataHasChanged;

    }

    //get-Methoden

    public int getTeilBeobachtungID(){

        return this.teilBeobachtungID;

    }

    public String getArtDerBeobachtung() {

        return this.artDerBeobachtung;

    }

    public String getNotiz() {

        return this.notiz;

    }

    public boolean getIsInDatabase() {

        return this.isInDatabase;

    }

    public boolean getDataHasChanged() {

        return this.dataHasChanged;

    }

    //Methoden

    public int getBeobachtungId() {

        return this.beobachtung.getBeobachtungId();

    }

}
