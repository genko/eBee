package datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import inventar.Inventar;
import logik.Einstellungen;
import logik.Volk;
import vorgaenge.Arbeitsvorgang;

/**
 * Created by maxionderon on 05.11.17.
 * Tabelle Volk / Inventar eingerichtet
 * Datenbank EBEE.db Version 1
 *
 */

public class Datenbank extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="EBEE.db";

    //Tabellen
    private TabelleVolk tabelleVolk;
    private TabelleInventar tabelleInventar;
    private TabelleBehandlung tabelleBehandlung;
    private TabelleBeobachtung tabelleBeobachtung;
    private TabelleTeilbeobachtung tabelleTeilbeobachtung;
    private TabelleArbeitsvorgang tabelleArbeitsvorgang;
    private TabelleErnten tabelleErnten;
    private TabelleEinstellungen tabelleEinstellungen;

    public Datenbank(Context context){

        super(context, DATABASE_NAME, null , DATABASE_VERSION);

        this.tabelleVolk = new TabelleVolk();
        this.tabelleInventar = new TabelleInventar();
        this.tabelleBehandlung = new TabelleBehandlung();
        this.tabelleBeobachtung = new TabelleBeobachtung();
        this.tabelleTeilbeobachtung = new TabelleTeilbeobachtung();
        this.tabelleArbeitsvorgang = new TabelleArbeitsvorgang();
        this.tabelleErnten = new TabelleErnten();
        this.tabelleEinstellungen = new TabelleEinstellungen();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabelle Volk erstellen
        db.execSQL(TabelleVolk.getSQLCreateTable());
        //Tabelle Inventar erstellen
        db.execSQL(TabelleInventar.getSQLCreateTable());
        //Tabelle Behandlung erstellen
        db.execSQL(TabelleBehandlung.getSQLCreateTable());
        //Tabelle Beobachtung
        db.execSQL(TabelleBeobachtung.getSQLCreateTable());
        //Tabelle Teilbeobachtung
        db.execSQL(TabelleTeilbeobachtung.getSQLCreateTable());
        //Tabelle Arbeitsvorgang
        db.execSQL(TabelleArbeitsvorgang.getSQLCreateTable());
        //Tabelle Ernten
        db.execSQL(TabelleErnten.getSQLCreateTable());
        //Tabelle Einstellungen
        db.execSQL(TabelleEinstellungen.getSQLCreateTable());
        //Tabelle Inventar

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(TabelleVolk.getSQLDropTable());
        db.execSQL(TabelleInventar.getSQLDropTable());
        db.execSQL(TabelleBehandlung.getSQLDropTable());
        db.execSQL(TabelleBeobachtung.getSQLDropTable());
        db.execSQL(TabelleTeilbeobachtung.getSQLDropTable());
        db.execSQL(TabelleArbeitsvorgang.getSQLDropTable());
        db.execSQL(TabelleErnten.getSQLDropTable());
        db.execSQL(TabelleEinstellungen.getSQLDropTable());
        onCreate(db);

    }

    //Volk Methoden ANFANG-----

    public void insertVolk(Volk volk) {

        this.tabelleVolk.insertVolk(volk, getWritableDatabase());

    }

    public void updateVolk(Volk volk) {

        this.tabelleVolk.updateVolk(volk,getWritableDatabase());

    }

    public ArrayList<Volk> getVolkList(){

        return this.tabelleVolk.getVolkList(getReadableDatabase());

    }

    public int getVoelkerAnzahl(){

        return this.tabelleVolk.getVoelkerAnzahl(getReadableDatabase());

    }

    //Volk Methoden ENDE-----

    //Inventar Methoden ANFANG-----
    public void insertInventar(Inventar inventar) {

        this.tabelleInventar.insertInventar(inventar, getWritableDatabase());

    }

    public void updateInventar(Inventar inventar) {

        this.tabelleInventar.updateInventar(inventar, getWritableDatabase());

    }

    public Inventar getInventar() {

        return this.tabelleInventar.getInventar(getReadableDatabase());

    }

    public int getInventarAnzahl() {

        return this.tabelleInventar.getInventarAnzahl(getReadableDatabase());

    }
    //Inventar Methoden ENDE-----

    //Behandlung Methoden ANFANG----

    public void insertBehandlung(vorgaenge.Behandlung behandlung) {

        this.tabelleBehandlung.insertBehandlung(behandlung,getWritableDatabase());

    }

    public void updateBehandlung(vorgaenge.Behandlung behandlung) {

        this.tabelleBehandlung.updateBehandlung(behandlung, getWritableDatabase());

    }

    public ArrayList<vorgaenge.Behandlung> getBehandlungList(ArrayList<Volk> volkList) {

        return this.tabelleBehandlung.getBehandlungkList(getReadableDatabase(), volkList);

    }

    public int getBehandlungAnzahl() {

        return this.tabelleBehandlung.getBehandlungAnzahl(getReadableDatabase());

    }

    //Behandlung Methoden ENDE----

    //Beobachtung Methoden Anfang----

    public void insertBeobachtung(vorgaenge.Beobachtung beobachtung) {

        this.tabelleBeobachtung.insertBeobachtung(beobachtung, getWritableDatabase());

    }

    public void updateBeobachtung(vorgaenge.Beobachtung beobachtung) {

        this.tabelleBeobachtung.updateBeobachtung(beobachtung, getWritableDatabase());

    }

    public ArrayList<vorgaenge.Beobachtung> getBeobachtungList(ArrayList<Volk> volkList) {

        return this.tabelleBeobachtung.getBeobachtungList(getWritableDatabase(), volkList, this.tabelleTeilbeobachtung);

    }

    public int getBeobachtungAnzahl() {

        return this.tabelleBeobachtung.getBehandlungAnzahl(getWritableDatabase());

    }

    //Beobachtung Methoden ENDE----

    //TeilBeobachtung Methoden ANFANG----

    public void insertTeilBeobachtung(vorgaenge.TeilBeobachtung teilBeobachtung) {

        this.tabelleTeilbeobachtung.insertTeilBeobachtung(teilBeobachtung, getWritableDatabase());

    }

    public void updateTeilBeobachtung(vorgaenge.TeilBeobachtung teilBeobachtung) {

        this.tabelleTeilbeobachtung.updateTeilBeobachtung(teilBeobachtung, getWritableDatabase());

    }

    public void insertTeilBeobachtungListIntoBeobachtung(vorgaenge.Beobachtung beobachtung) {

        this.tabelleTeilbeobachtung.insertTeilBeobachtungListIntoBeobachtung(getReadableDatabase(), beobachtung);
    }

    public int getTeilBeobachtungAnzahl() {

        return this.tabelleTeilbeobachtung.getTeilBehandlungAnzahl(getReadableDatabase());

    }

    //TeilBeobachtung Methoden ENDE----

    //Arbeitsvorgang Methoden ANFANG----
    public void insertArbeitsvorgang(Arbeitsvorgang arbeitsvorgang) {

        this.tabelleArbeitsvorgang.insertArbeitsvorgang(arbeitsvorgang, getWritableDatabase());

    }

    public void updateArbeitsvorgang(Arbeitsvorgang arbeitsvorgang) {

        this.tabelleArbeitsvorgang.updateArbeitsvorgang(arbeitsvorgang, getWritableDatabase());

    }

    public ArrayList<Arbeitsvorgang> getArbeitsvorgaengeList(ArrayList<Volk> list) {

        return this.tabelleArbeitsvorgang.getArbeitsvorgangList(getReadableDatabase(), list);

    }

    public int getArbeitsvorgaengeAnzahl() {

        return this.tabelleArbeitsvorgang.getArbeitsvorgangAnzahl(getReadableDatabase());

    }

    //Arbeitsvorgang Methoden ENDE----

    //Ernten Methoden ANFANG----

    public void insertErnte(vorgaenge.Ernte ernte) {

        this.tabelleErnten.insertErnte(ernte, getWritableDatabase());

    }

    public void updateErnte(vorgaenge.Ernte ernte) {

        this.tabelleErnten.updateErnte(ernte, getWritableDatabase());

    }

    public ArrayList<vorgaenge.Ernte> getErntenList(ArrayList<logik.Volk> list) {

        return this.tabelleErnten.getErntenList(getReadableDatabase(),list);

    }

    public int getErntenAnzahl() {

        return this.tabelleErnten.getErntenAnzahl(getReadableDatabase());
    }

    //Ernten Methoden ENDE----

    //Einstellungen Methoden ANFANG----
    public void insertEinstellungen(Einstellungen einstellungen) {

        this.tabelleEinstellungen.insertEinstellungen(einstellungen, getWritableDatabase());

    }

    public void updateEinstellungen(Einstellungen einstellungen) {

        this.tabelleEinstellungen.updateEinstellungen(einstellungen, getWritableDatabase());

    }

    public Einstellungen getEinstellungen() {

        return this.tabelleEinstellungen.getEinstellungen(getReadableDatabase());

    }

    public int getEinstellungenAnzahl() {

        return this.tabelleEinstellungen.getEinstellungenAnzahl(getReadableDatabase());

    }
    //Einstellungen Methoden ENDE----

}
