package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Behandlung;
import vorgaenge.Beobachtung;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleBeobachtung extends Tabelle {

    /*
    //ID der Beobachtung
    private int beobachtungId;

    //Fremdschlüssel Referenz auf das Volk
    private Volk volk;

    //Attribute der Beobachtung
    ArrayList<TeilBeobachtung> teilBeobachtungList;

    //Timestamp
    private long timstampCreat;
     */

    private static final String TABLE_NAME = "BEOBACHTUNG";
    private static final String BEOBACHTUNG_ID = "BE_ID";
    private static final String BEOBACHTUNG_VOLK_ID = "BE_V_ID";
    private static final String BEOBACHTUNG_TIMESTAMP = "BE_TIMESTAMP";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                                                BEOBACHTUNG_ID + " int, " +
                                                BEOBACHTUNG_VOLK_ID + " int, " +
                                                BEOBACHTUNG_TIMESTAMP + " long " +
                                                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertBeobachtung(vorgaenge.Beobachtung beobachtung , SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            BEOBACHTUNG_ID + ", " +
                                                            BEOBACHTUNG_VOLK_ID + ", " +
                                                            BEOBACHTUNG_TIMESTAMP + " " +
                                                            ") " +
                        "VALUES (" +
                        ((Integer)beobachtung.getBeobachtungId()).toString() + ", " +
                        ((Integer) beobachtung.getVolkId()).toString() + ", " +
                        beobachtung.getTimestampCreate() +
                        ");";

        db.execSQL(query);

    }

    public void updateBeobachtung(vorgaenge.Beobachtung beobachtung, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " + BEOBACHTUNG_TIMESTAMP + " = " + beobachtung.getTimestampCreate() +
                        " WHERE " + BEOBACHTUNG_ID + " = " + beobachtung.getBeobachtungId() + ";";

        database.execSQL(query);

    }

    public ArrayList<vorgaenge.Beobachtung> getBeobachtungList(SQLiteDatabase database, ArrayList<Volk> volkListe, TabelleTeilbeobachtung tabelleTeilbeobachtung){

        ArrayList<Beobachtung> list = new ArrayList<Beobachtung>();

        //Volk Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int beobachtungId = cursor.getColumnIndex(BEOBACHTUNG_ID);
        int beobachtungVolkId = cursor.getColumnIndex(BEOBACHTUNG_VOLK_ID);
        int beobachtungTimestamp = cursor.getColumnIndex(BEOBACHTUNG_TIMESTAMP);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            for( int k = 0; k != volkListe.size(); k = k + 1) {

                if(volkListe.get(k).getVolkId() == cursor.getInt(beobachtungVolkId)){

                    Beobachtung beobachtung = new Beobachtung(i, volkListe.get(k));

                    beobachtung.setBeobachtungId(cursor.getInt(beobachtungId));
                    beobachtung.setTimestampCreate(cursor.getLong(beobachtungTimestamp));

                    //Teilbeobachtungen werden in beobachtung gesetzt
                    tabelleTeilbeobachtung.insertTeilBeobachtungListIntoBeobachtung(database, beobachtung);

                    beobachtung.setIsInDatabase(true);
                    beobachtung.setDataHasChanged(false);

                    list.add(beobachtung);

                }

            }

        }

        return list;

    }

    public int getBehandlungAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();

    }

}
