package logik;

/**
 * Created by Vladi on 05.12.2017.
 */

public class Einstellungen {

    private int einstellungId;
    private String eMailAdresse;
    private boolean aufgeloesteVoelkerMitAnzeigen;

    //flags für Datenbank
    private boolean isInDatabase;
    private boolean dataHasChanged;

    public Einstellungen(int einstellungId) {

        this.einstellungId = einstellungId;
        this.eMailAdresse = "";
        this.aufgeloesteVoelkerMitAnzeigen = true;

        this.isInDatabase = false;
        this.dataHasChanged = false;

    }

    public int getEinstellungId() {

        return this.einstellungId;

    }

    public void setEinstellungId(int einstellungId) {

        this.einstellungId = einstellungId;

    }

    public String getEMailAdresse(){
        return this.eMailAdresse;
    }

    public void setEMailAdresse(String eMail){
        this.eMailAdresse= eMail;
    }

    public boolean getAufgelosteVoelkerAnzeigen(){
        return this.aufgeloesteVoelkerMitAnzeigen;
    }

    public void setAufgeloesteVoelkerAnzeigen(boolean Aufg){
        this.aufgeloesteVoelkerMitAnzeigen= Aufg;
    }

    public boolean getIsinDatabase() {

        return isInDatabase;
    }

    public boolean getDataHasChanged() {

        return this.dataHasChanged;

    }

    public void setIsInDatabase (boolean isInDatabase) {

        this.isInDatabase = isInDatabase;

    }

    public void setDataHasChanged (boolean dataHasChanged ) {

        this.dataHasChanged = dataHasChanged;

    }

}
