package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import inventar.Inventar;
import logik.Volk;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleInventar extends Tabelle {

    private static final String TABLE_NAME = "INVENTAR";
    private static final String INVENTAR_ID = "I_ID";
    private static final String INVENTAR_ZARGEN = "I_ZAREGEN";
    private static final String INVENTAR_RAHMEN = "I_RAHMEN";
    private static final String INVENTAR_MITTELWAENDE = "I_MITELWAENDE";
    private static final String INVENTAR_ABSPERRGITTER = "I_ABSPERRGITTER";
    private static final String INVENTAR_FLUGLOCHKEIL = "I_FLUGLOCHKEIL";
    private static final String INVENTAR_WINDEL = "I_WINDEL";
    private static final String INVENTAR_AMEISENSAEURE = "I_AMEISENSAEURE";
    private static final String INVENTAR_OXALSAEURE = "I_OXALSAEURE";
    private static final String INVENTAR_MILCHSAEURE = "I_MILCHSAEURE";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                                                    INVENTAR_ID + " int, " +
                                                    INVENTAR_ZARGEN + " int, " +
                                                    INVENTAR_RAHMEN + " int, " +
                                                    INVENTAR_MITTELWAENDE + " int, " +
                                                    INVENTAR_ABSPERRGITTER + " int, " +
                                                    INVENTAR_FLUGLOCHKEIL + " int, " +
                                                    INVENTAR_WINDEL + " int, " +
                                                    INVENTAR_AMEISENSAEURE + " double, " +
                                                    INVENTAR_OXALSAEURE + " double, " +
                                                    INVENTAR_MILCHSAEURE + " double " +
                                                    ");" ;

    }

    public static String getSQLDropTable() {

        //DROP TABLE INVENTAR;

        return "DROP TABLE " + TABLE_NAME + ";";


    }

    public void insertInventar(Inventar inventar, SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            INVENTAR_ID + ", " +
                                                            INVENTAR_ZARGEN + ", " +
                                                            INVENTAR_RAHMEN + ", " +
                                                            INVENTAR_MITTELWAENDE + ", " +
                                                            INVENTAR_ABSPERRGITTER + ", " +
                                                            INVENTAR_FLUGLOCHKEIL + ", " +
                                                            INVENTAR_WINDEL + ", " +
                                                            INVENTAR_AMEISENSAEURE + ", " +
                                                            INVENTAR_OXALSAEURE + ", " +
                                                            INVENTAR_MILCHSAEURE + " " +
                                                            ") " +
                        "VALUES (" +
                                    String.valueOf(inventar.getInventarId()) + ", " +
                                    String.valueOf(inventar.getZargen()) + ", " +
                                    String.valueOf(inventar.getRahmen()) + ", " +
                                    String.valueOf(inventar.getMittelwaende()) + ", " +
                                    String.valueOf(inventar.getAbsperrgitter()) + ", " +
                                    String.valueOf(inventar.getFluglochkeil()) + ", " +
                                    String.valueOf(inventar.getWindel()) + ", " +
                                    String.valueOf(inventar.getAmeisensaeure()) + ", " +
                                    String.valueOf(inventar.getOxalsaeure()) + ", " +
                                    String.valueOf(inventar.getMilchsaeure()) +
                                    ");" ;

        db.execSQL(query);

    }

    public void updateInventar(Inventar inventar, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " +    INVENTAR_ZARGEN + " = " + String.valueOf(inventar.getZargen()) + ", " +
                                    INVENTAR_RAHMEN + " = " + String.valueOf(inventar.getRahmen()) + ", " +
                                    INVENTAR_MITTELWAENDE + " = " + String.valueOf(inventar.getMittelwaende()) + ", " +
                                    INVENTAR_ABSPERRGITTER + " = " + String.valueOf(inventar.getAbsperrgitter()) + ", " +
                                    INVENTAR_FLUGLOCHKEIL + " = " + String.valueOf(inventar.getFluglochkeil()) + ", " +
                                    INVENTAR_WINDEL + " = " + String.valueOf(inventar.getWindel()) + ", " +
                                    INVENTAR_AMEISENSAEURE + " = " +String.valueOf(inventar.getAmeisensaeure()) + ", " +
                                    INVENTAR_OXALSAEURE + " = " + String.valueOf(inventar.getOxalsaeure()) + ", " +
                                    INVENTAR_MILCHSAEURE + " = " + String.valueOf(inventar.getMilchsaeure()) + "" +
                        " WHERE " + INVENTAR_ID + " = " + String.valueOf(inventar.getInventarId()) + ";";

        database.execSQL(query);

    }

    public Inventar getInventar(SQLiteDatabase database){

        Inventar inventar = new Inventar(1);

        //Invertar Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int inventarId = cursor.getColumnIndex(INVENTAR_ID);
        int inventarZargen = cursor.getColumnIndex(INVENTAR_ZARGEN);
        int inventarRahmen = cursor.getColumnIndex(INVENTAR_RAHMEN);
        int inventarMittelwaende = cursor.getColumnIndex(INVENTAR_MITTELWAENDE);
        int inventarAbsperrgitter = cursor.getColumnIndex(INVENTAR_ABSPERRGITTER);
        int inventarFluglochkeil = cursor.getColumnIndex(INVENTAR_FLUGLOCHKEIL);
        int inventarWindel = cursor.getColumnIndex(INVENTAR_WINDEL);
        int inventarAmeisensaeure = cursor.getColumnIndex(INVENTAR_AMEISENSAEURE);
        int inventarOxalsaeure = cursor.getColumnIndex(INVENTAR_OXALSAEURE);
        int inventarMilchsaeure = cursor.getColumnIndex(INVENTAR_MILCHSAEURE);

        if(cursor.getCount() != 0) {

            cursor.moveToNext();

            inventar.setInventarId(cursor.getInt(inventarId));
            inventar.setZargen(cursor.getInt(inventarZargen));
            inventar.setRahmen(cursor.getInt(inventarRahmen));
            inventar.setMittelwaende(cursor.getInt(inventarMittelwaende));
            inventar.setAbsperrgitter(cursor.getInt(inventarAbsperrgitter));
            inventar.setFluglochkeil(cursor.getInt(inventarFluglochkeil));
            inventar.setWindel(cursor.getInt(inventarWindel));
            inventar.setAmeisensaeure(cursor.getFloat(inventarAmeisensaeure));
            inventar.setOxalsaeure(cursor.getFloat(inventarOxalsaeure));
            inventar.setMilchsaeure(cursor.getFloat(inventarMilchsaeure));

            //flags setzen
            inventar.setIsInDatabase(true);
            inventar.setDataHasChanged(false);

        }

        return inventar;

    }

    public int getInventarAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME ;

        Cursor cursor = database.rawQuery(query, null);

        return cursor.getCount();

    }
}
