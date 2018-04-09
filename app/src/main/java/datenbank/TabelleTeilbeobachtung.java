package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Beobachtung;
import vorgaenge.TeilBeobachtung;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleTeilbeobachtung extends Tabelle {

    /*
    //referenz als Fremdschlüssel
    private Beobachtung beobachtung;

    //Unterbeobachtungs ID
    private int teilBeobachtungID;

    //Attribute
    private String artDerBeobachtung;
    private String notiz;
     */

    private static final String TABLE_NAME = "TEILBEOBACHTUNG";
    private static final String TEILBEOBACHTUNG_ID = "TBE_ID";
    private static final String TEILBEOBACHTUNG_BEOBACHTUNG_ID = "TBE_BE_ID";
    private static final String TEILBEOBACHTUNG_ARTDERBEOBACHTUNG = "TBE_ARTBEOBACHTUNG";
    private static final String TEILBEOBACHTUNG_NOTIZ = "TBE_NOTIZ";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                TEILBEOBACHTUNG_ID + " int, " +
                TEILBEOBACHTUNG_BEOBACHTUNG_ID + " int, " +
                TEILBEOBACHTUNG_ARTDERBEOBACHTUNG + " varchar(255), " +
                TEILBEOBACHTUNG_NOTIZ + " text" +
                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertTeilBeobachtung(vorgaenge.TeilBeobachtung teilBeobachtung , SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            TEILBEOBACHTUNG_ID + ", " +
                                                            TEILBEOBACHTUNG_BEOBACHTUNG_ID + ", " +
                                                            TEILBEOBACHTUNG_ARTDERBEOBACHTUNG + ", " +
                                                            TEILBEOBACHTUNG_NOTIZ + " " +
                                                            ") " +
                        "VALUES (" +
                                    ((Integer) teilBeobachtung.getTeilBeobachtungID()).toString() + ", " +
                                    ((Integer) teilBeobachtung.getBeobachtungId()).toString() + ", " +
                                    "'" + teilBeobachtung.getArtDerBeobachtung() + "', " +
                                    "'" + teilBeobachtung.getNotiz() + "' " +
                        ");";

        db.execSQL(query);

    }

    public void updateTeilBeobachtung(vorgaenge.TeilBeobachtung teilBeobachtung, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " +    TEILBEOBACHTUNG_ARTDERBEOBACHTUNG + " = '" + teilBeobachtung.getArtDerBeobachtung() + "', " +
                                    TEILBEOBACHTUNG_NOTIZ + " = '" + teilBeobachtung.getNotiz() + "'; " +
                        " WHERE " + TEILBEOBACHTUNG_BEOBACHTUNG_ID + " = " + teilBeobachtung.getTeilBeobachtungID() + ";";

        database.execSQL(query);

    }

    public void insertTeilBeobachtungListIntoBeobachtung(SQLiteDatabase database, vorgaenge.Beobachtung beobachtung){

        ArrayList<vorgaenge.TeilBeobachtung> list = new ArrayList<vorgaenge.TeilBeobachtung>();

        //Volk Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + TEILBEOBACHTUNG_BEOBACHTUNG_ID + " = " + beobachtung.getBeobachtungId() + ";";

        Cursor cursor = database.rawQuery(query, null);

        int teilBeobachtungId = cursor.getColumnIndex(TEILBEOBACHTUNG_ID);
        int teilBeobachtungBeobachtungId = cursor.getColumnIndex(TEILBEOBACHTUNG_BEOBACHTUNG_ID);
        int teilBeobachtungArtDerBeobachtung = cursor.getColumnIndex(TEILBEOBACHTUNG_ARTDERBEOBACHTUNG);
        int teilBeobachtungNotiz = cursor.getColumnIndex(TEILBEOBACHTUNG_NOTIZ);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            TeilBeobachtung teilBeobachtung = new TeilBeobachtung(beobachtung);

            teilBeobachtung.setTeilBeobachtungID(cursor.getInt(teilBeobachtungId));
            teilBeobachtung.setArtDerBeobachtung(cursor.getString(teilBeobachtungArtDerBeobachtung));
            teilBeobachtung.setNotiz(cursor.getString(teilBeobachtungNotiz));

            //flags setzen
            teilBeobachtung.setIsInDatabase(true);
            teilBeobachtung.setDataHasChanged(false);

            list.add(teilBeobachtung);

        }

        beobachtung.setTeilBeobachtungList(list);

    }

    public int getTeilBehandlungAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();

    }



}
