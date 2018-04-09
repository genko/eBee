package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Behandlung;
import vorgaenge.Ernte;

/**
 * Created by maxionderon on 28.11.17.
 */

public class TabelleErnten extends Tabelle {

    /*
    private int anzahlWaben;
    private String honigSorte;
    private double wassergehalt;
    private double menge;
    private String notiz;
     */

    private static final String TABLE_NAME = "ERNTEN";
    private static final String ERNTEN_ID = "E_ID";
    private static final String ERNTEN_VOLK_ID = "E_V_ID";
    private static final String ERNTEN_ANZAHLWABEN = "E_ANZAHLWABEN";
    private static final String ERNTEN_HONIGSORTE = "E_HONIGSORTE";
    private static final String ERNTEN_WASSERGEHALT = "E_WASSERGEHALT";
    private static final String ERNTEN_MENGE = "E_MENGE";
    private static final String ERNTEN_TIMESTAMP = "E_TIMESTAMP";
    private static final String ERNTEN_NOTIZ = "E_NOTIZ";


    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                                                ERNTEN_ID + " int, " +
                                                ERNTEN_VOLK_ID + " int, " +
                                                ERNTEN_ANZAHLWABEN + " int, " +
                                                ERNTEN_HONIGSORTE + " varchar(255), " +
                                                ERNTEN_WASSERGEHALT + " double, " +
                                                ERNTEN_MENGE + " double, " +
                                                ERNTEN_TIMESTAMP + " long, " +
                                                ERNTEN_NOTIZ + " text " +
                                                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertErnte(vorgaenge.Ernte ernte , SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            ERNTEN_ID + ", " +
                                                            ERNTEN_VOLK_ID + ", " +
                                                            ERNTEN_ANZAHLWABEN + ", " +
                                                            ERNTEN_HONIGSORTE + ", " +
                                                            ERNTEN_WASSERGEHALT + ", " +
                                                            ERNTEN_MENGE + ", " +
                                                            ERNTEN_TIMESTAMP + ", " +
                                                            ERNTEN_NOTIZ + " " +
                                                            ") " +
                        "VALUES (" +
                                ((Integer) ernte.getErnteId()).toString() + ", " +
                                ((Integer) ernte.getVolkId()).toString() + ", " +
                                ((Integer) ernte.getAnzahlWaben()).toString() + ", " +
                                "'" + ernte.getHonigSorte() + "', " +
                                ((Double) ernte.getWassergehalt()).toString() + ", " +
                                ((Double) ernte.getMenge()).toString() + ", " +
                                String.valueOf(ernte.getTimestampCreate()) + ", " +
                                "'" + ernte.getNotiz() + "'" +
                                ");";

        db.execSQL(query);

    }

    public void updateErnte(Ernte ernte, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " +    ERNTEN_ANZAHLWABEN + " = " + ((Integer) ernte.getAnzahlWaben()).toString() + ", " +
                                    ERNTEN_HONIGSORTE + " = '" + ernte.getHonigSorte() + "', " +
                                    ERNTEN_WASSERGEHALT + " = " + ((Double) ernte.getWassergehalt()).toString() + ", " +
                                    ERNTEN_MENGE + " = " + ((Double) ernte.getMenge()).toString() + ", " +
                                    ERNTEN_TIMESTAMP + " = " + String.valueOf(ernte.getTimestampCreate()) + ", " +
                                    ERNTEN_NOTIZ + " = '" + ernte.getNotiz() + "'" +
                        " WHERE " + ERNTEN_ID + " = " + ((Integer) ernte.getErnteId()).toString() + ";" ;

        database.execSQL(query);

    }

    public ArrayList<Ernte> getErntenList(SQLiteDatabase database, ArrayList<Volk> volkListe){

        ArrayList<Ernte> list = new ArrayList<Ernte>();

        //Volk Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int erntenId = cursor.getColumnIndex(ERNTEN_ID);
        int erntenVolkId = cursor.getColumnIndex(ERNTEN_VOLK_ID);
        int erntenAnzahlWaben = cursor.getColumnIndex(ERNTEN_ANZAHLWABEN);
        int erntenHonigsorte = cursor.getColumnIndex(ERNTEN_HONIGSORTE);
        int erntenWassergehalt = cursor.getColumnIndex(ERNTEN_WASSERGEHALT);
        int erntenMenge = cursor.getColumnIndex(ERNTEN_MENGE);
        int ernteTimestamp = cursor.getColumnIndex(ERNTEN_TIMESTAMP);
        int erntenNotiz = cursor.getColumnIndex(ERNTEN_NOTIZ);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            for( int k = 0; k != volkListe.size(); k = k + 1) {

                if(volkListe.get(k).getVolkId() == cursor.getInt(erntenVolkId)){

                    Ernte ernte = new Ernte(i, volkListe.get(k));

                    ernte.setErnteId(cursor.getInt(erntenId));
                    ernte.setAnzahlWaben(cursor.getInt(erntenAnzahlWaben));
                    ernte.setHonigSorte(cursor.getString(erntenHonigsorte));
                    ernte.setWassergehalt(cursor.getDouble(erntenWassergehalt));
                    ernte.setMenge(cursor.getDouble(erntenMenge));
                    ernte.setTimestampCreate(cursor.getLong(ernteTimestamp));
                    ernte.setNotiz(cursor.getString(erntenNotiz));

                    //flags setzen
                    ernte.setIsInDatabase(true);
                    ernte.setDataHasChanged(false);

                    list.add(ernte);

                }

            }

        }

        return list;

    }

    public int getErntenAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        return cursor.getCount();

    }

}
