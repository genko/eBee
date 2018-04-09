package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Behandlung;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleBehandlung extends Tabelle {

    private static final String TABLE_NAME = "BEHANDLUNG";
    private static final String BEHANDLUNG_ID = "B_ID";
    private static final String BEHANDLUNG_VOLK_ID = "B_V_ID";
    private static final String BEHANDLUNG_ART_DER_BEHANDLUNG = "B_ART_BEHANDLUNG";
    private static final String BEHANDLUNG_MENGE = "B_MENGE";
    private static final String BEHANDLUNG_WINDEL_EINGESETZT = "B_WINDEL_EINGESETZT";
    private static final String BEHANDLUNG_TEXT = "B_TEXT";
    private static final String BEHANDLUNG_ISACTIV = "B_ISACTIV";
    private static final String BEHANDLUNG_TIMESTAMP_CREATE = "B_TIMESTAMP_CREATE";
    private static final String BEHANDLUNG_TIMESTAMP_FINISH = "B_TIMESTAMP_FINISH";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                                                    BEHANDLUNG_ID + " int, " +
                                                    BEHANDLUNG_VOLK_ID + " int, " +
                                                    BEHANDLUNG_ART_DER_BEHANDLUNG + " varchar(255), " +
                                                    BEHANDLUNG_MENGE + " double, " +
                                                    BEHANDLUNG_WINDEL_EINGESETZT + " int, " +
                                                    BEHANDLUNG_TEXT + " text, " +
                                                    BEHANDLUNG_ISACTIV + " int, " +
                                                    BEHANDLUNG_TIMESTAMP_CREATE + " long, " +
                                                    BEHANDLUNG_TIMESTAMP_FINISH + " long " +
                                                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertBehandlung(vorgaenge.Behandlung behandlung , SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +

                                                            BEHANDLUNG_ID + ", " +
                                                            BEHANDLUNG_VOLK_ID + ", " +
                                                            BEHANDLUNG_ART_DER_BEHANDLUNG + ", " +
                                                            BEHANDLUNG_MENGE + ", " +
                                                            BEHANDLUNG_WINDEL_EINGESETZT + ", " +
                                                            BEHANDLUNG_TEXT + ", " +
                                                            BEHANDLUNG_ISACTIV + ", " +
                                                            BEHANDLUNG_TIMESTAMP_CREATE + ", " +
                                                            BEHANDLUNG_TIMESTAMP_FINISH + " " +
                                                            ") " +
                        "VALUES (" +
                                    ((Integer) behandlung.getBehandlungId()).toString() + ", " +
                                    ((Integer) behandlung.getVolkId()).toString() + ", " +
                                    "'" + behandlung.getArtDerBehandlung() + "', " +
                                    ((Float) behandlung.getMenge()).toString() + ", " +
                                    booleanToInt(behandlung.getWindelEingesetzt()) + ", " +
                                    "'" + behandlung.getText() + "', " +
                                    booleanToInt(behandlung.getIsActiv()) + ", " +
                                    behandlung.getTimestampCreate() + ", " +
                                    behandlung.getTimestampFinish() +
                                ");";

        db.execSQL(query);

    }

    public void updateBehandlung(Behandlung behandlung, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " +    BEHANDLUNG_ART_DER_BEHANDLUNG + " = '" + behandlung.getArtDerBehandlung() + "', " +
                                    BEHANDLUNG_MENGE + " = " + behandlung.getMenge() + ", " +
                                    BEHANDLUNG_WINDEL_EINGESETZT + " = " + booleanToInt(behandlung.getWindelEingesetzt()) + ", " +
                                    BEHANDLUNG_TEXT + " = '" + behandlung.getText() + "', " +
                                    BEHANDLUNG_ISACTIV + " = " + booleanToInt(behandlung.getIsActiv()) + ", " +
                                    BEHANDLUNG_TIMESTAMP_CREATE + " = " + behandlung.getTimestampCreate() + ", " +
                                    BEHANDLUNG_TIMESTAMP_FINISH + " = " + behandlung.getTimestampFinish() +
                        " WHERE " + BEHANDLUNG_ID + " = " + ((Integer) behandlung.getBehandlungId()).toString() + ";" ;

        database.execSQL(query);

    }

    public ArrayList<Behandlung> getBehandlungkList(SQLiteDatabase database, ArrayList<Volk> volkListe){

        ArrayList<Behandlung> list = new ArrayList<Behandlung>();

        //Volk Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int behandlungId = cursor.getColumnIndex(BEHANDLUNG_ID);
        int behandlungVolkId = cursor.getColumnIndex(BEHANDLUNG_VOLK_ID);
        int behandlungArtDerBehandlung = cursor.getColumnIndex(BEHANDLUNG_ART_DER_BEHANDLUNG);
        int behandlungMenge = cursor.getColumnIndex(BEHANDLUNG_MENGE);
        int behandlungWindelEingesetzt = cursor.getColumnIndex(BEHANDLUNG_WINDEL_EINGESETZT);
        int behandlungText = cursor.getColumnIndex(BEHANDLUNG_TEXT);
        int behandlungIsActiv = cursor.getColumnIndex(BEHANDLUNG_ISACTIV);
        int behandlungTimestampCreate = cursor.getColumnIndex(BEHANDLUNG_TIMESTAMP_CREATE);
        int behandlungTimestampFinish = cursor.getColumnIndex(BEHANDLUNG_TIMESTAMP_FINISH);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            for( int k = 0; k != volkListe.size(); k = k + 1) {

                if(volkListe.get(k).getVolkId() == cursor.getInt(behandlungVolkId)){

                    Behandlung behandlung = new Behandlung(i, volkListe.get(k));

                    behandlung.setBehandlungId(cursor.getInt(behandlungId));
                    behandlung.setArtDerBehandlung(cursor.getString(behandlungArtDerBehandlung));
                    behandlung.setMenge(cursor.getFloat(behandlungMenge));
                    behandlung.setWindelEingesetzt(intToBoolean(cursor.getInt(behandlungWindelEingesetzt)));
                    behandlung.setText(cursor.getString(behandlungText));
                    behandlung.setIsActiv(intToBoolean(cursor.getInt(behandlungIsActiv)));
                    behandlung.setTimestampCreate(cursor.getLong(behandlungTimestampCreate));
                    behandlung.setTimestampFinish(cursor.getLong(behandlungTimestampFinish));

                    //flags setzen
                    behandlung.setIsInDatabase(true);
                    behandlung.setDataHasChanged(false);

                    list.add(behandlung);

                }

            }

        }

        return list;

    }

    public int getBehandlungAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int test = cursor.getCount();

        return cursor.getCount();

    }

}
