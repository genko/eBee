package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Arbeitsvorgang;
import vorgaenge.Behandlung;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleArbeitsvorgang extends Tabelle {

    private static final String TABLE_NAME = "ARBEITSVORGANG";
    private static final String ARBEITSVORGANG_ID = "A_ID";
    private static final String ARBEITSVORGANG_VOLK_ID = "A_V_ID";
    private static final String ARBEITSVORGANG_ANZAHL_ZARGEN = "A_ANZAHL_ZARGEN";
    private static final String ARBEITSVORGANG_ABSPERRGITTER = "A_ABSPERRGITTER";
    private static final String ARBEITSVORGANG_ANZAHL_WABEN = "A_ANZAHL_WABEN";
    private static final String ARBEITSVORGANG_FLUGLOCHKEIL = "A_FLUGLOCHKEIL";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS1 = "A_DROHENRAHMEN_POS1";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS2 = "A_DROHENRAHMEN_POS2";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS3 = "A_DROHENRAHMEN_POS3";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS4 = "A_DROHENRAHMEN_POS4";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS5 = "A_DROHENRAHMEN_POS5";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS6 = "A_DROHENRAHMEN_POS6";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS7 = "A_DROHENRAHMEN_POS7";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS8 = "A_DROHENRAHMEN_POS8";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS9 = "A_DROHENRAHMEN_POS9";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_POS10 ="A_DROHENRAHMEN_POS10";
    private static final String ARBEITSVORGANG_TIMESTAMPCREATE = "A_TIMESTAMPCREATE";
    private static final String ARBEITSVORGANG_FLUGLOCHKEIL_WECHSEL = "A_FLUGLOCHKEIL_WECHSEL";
    private static final String ARBEITSVORGANG_ABSPERRGITTER_WECHSEL = "A_ABSPERRGITTER_WECHSEL";
    private static final String ARBEITSVORGANG_DROHNENRAHMEN_WECHSEL = "A_DROHNENRAHMEN_WECHSEL";


    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                                                    ARBEITSVORGANG_ID + " int, " +
                                                    ARBEITSVORGANG_VOLK_ID + " int, " +
                                                    ARBEITSVORGANG_ANZAHL_ZARGEN + " int, " +
                                                    ARBEITSVORGANG_ABSPERRGITTER + " int, " +
                                                    ARBEITSVORGANG_ANZAHL_WABEN + " int, " +
                                                    ARBEITSVORGANG_FLUGLOCHKEIL + " varchar(255), " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS1 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS2 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS3 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS4 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS5 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS6 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS7 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS8 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS9 + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_POS10 + " int, " +
                                                    ARBEITSVORGANG_TIMESTAMPCREATE + " long, " +
                                                    ARBEITSVORGANG_FLUGLOCHKEIL_WECHSEL + " int, " +
                                                    ARBEITSVORGANG_ABSPERRGITTER_WECHSEL + " int, " +
                                                    ARBEITSVORGANG_DROHNENRAHMEN_WECHSEL + " int " +
                                                    ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertArbeitsvorgang(vorgaenge.Arbeitsvorgang arbeitsvorgang , SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            ARBEITSVORGANG_ID + ", " +
                                                            ARBEITSVORGANG_VOLK_ID + ", " +
                                                            ARBEITSVORGANG_ANZAHL_ZARGEN + ", " +
                                                            ARBEITSVORGANG_ABSPERRGITTER + ", " +
                                                            ARBEITSVORGANG_ANZAHL_WABEN + ", " +
                                                            ARBEITSVORGANG_FLUGLOCHKEIL + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS1 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS2 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS3 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS4 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS5 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS6 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS7 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS8 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS9 + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_POS10 + ", " +
                                                            ARBEITSVORGANG_TIMESTAMPCREATE + ", " +
                                                            ARBEITSVORGANG_FLUGLOCHKEIL_WECHSEL + ", " +
                                                            ARBEITSVORGANG_ABSPERRGITTER_WECHSEL + ", " +
                                                            ARBEITSVORGANG_DROHNENRAHMEN_WECHSEL + "" +
                                                            ") " +
                           "VALUES (" +
                                        ((Integer) arbeitsvorgang.getArbeitsvorgangId()).toString() + ", " +
                                        ((Integer) arbeitsvorgang.getVolkId()).toString() + ", " +
                                        ((Integer) arbeitsvorgang.getAnzahlZargen()).toString() + ", " +
                                        booleanToInt(arbeitsvorgang.getAbsperrgitter()) + ", " +
                                        arbeitsvorgang.getAnzahlWaben() + ", " +
                                        "'" + arbeitsvorgang.getFluglochkeil() + "'" + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(0)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(1)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(2)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(3)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(4)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(5)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(6)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(7)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(8)) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohenrahmenAt(9)) + ", " +
                                        arbeitsvorgang.getTimestampCreate() + ", " +
                                        booleanToInt(arbeitsvorgang.getFluglochkeilWechsel()) + ", " +
                                        booleanToInt(arbeitsvorgang.getAbsperrgitterWechsel()) + ", " +
                                        booleanToInt(arbeitsvorgang.getDrohnenrahmenWechsel()) +
                                    ");";

        db.execSQL(query);

    }

    public void updateArbeitsvorgang(Arbeitsvorgang arbeitsvorgang, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " +    ARBEITSVORGANG_ANZAHL_ZARGEN + " = " + arbeitsvorgang.getAnzahlZargen() + ", " +
                        ARBEITSVORGANG_ABSPERRGITTER + " = " + booleanToInt(arbeitsvorgang.getAbsperrgitter()) + ", " +
                        ARBEITSVORGANG_ANZAHL_WABEN + " = " + arbeitsvorgang.getAnzahlWaben() + ", " +
                        ARBEITSVORGANG_FLUGLOCHKEIL + " = '" + arbeitsvorgang.getFluglochkeil() + "', " +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS1 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(0)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS2 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(1)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS3 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(2)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS4 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(3)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS5 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(4)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS6 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(5)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS7 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(6)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS8 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(7)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS9 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(8)) + ", "  +
                        ARBEITSVORGANG_DROHNENRAHMEN_POS10 + " = " + booleanToInt(arbeitsvorgang.getDrohenrahmenAt(9)) + ", " +
                        ARBEITSVORGANG_TIMESTAMPCREATE + " = " + arbeitsvorgang.getTimestampCreate() +
                        ARBEITSVORGANG_FLUGLOCHKEIL_WECHSEL + " = " + booleanToInt(arbeitsvorgang.getFluglochkeilWechsel()) + ", " +
                        ARBEITSVORGANG_ABSPERRGITTER_WECHSEL + " = " + booleanToInt(arbeitsvorgang.getAbsperrgitterWechsel()) + ", " +
                        ARBEITSVORGANG_DROHNENRAHMEN_WECHSEL + " = " + booleanToInt(arbeitsvorgang.getDrohnenrahmenWechsel()) +
                        " WHERE " + ARBEITSVORGANG_ID + " = " + arbeitsvorgang.getArbeitsvorgangId() + ";" ;

        database.execSQL(query);

    }

    public ArrayList<Arbeitsvorgang> getArbeitsvorgangList(SQLiteDatabase database, ArrayList<Volk> volkListe){

        ArrayList<Arbeitsvorgang> list = new ArrayList<Arbeitsvorgang>();

        //Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int arbeitsvorgangId = cursor.getColumnIndex(ARBEITSVORGANG_ID);
        int arbeitsvorgangVolkId = cursor.getColumnIndex(ARBEITSVORGANG_VOLK_ID);
        int arbeitsvorgangAnzahlZargen = cursor.getColumnIndex(ARBEITSVORGANG_ANZAHL_ZARGEN);
        int arbeitsvorgangAbsperrgitter = cursor.getColumnIndex(ARBEITSVORGANG_ABSPERRGITTER);
        int arbeitsvorgangAnzahlWaben = cursor.getColumnIndex(ARBEITSVORGANG_ANZAHL_WABEN);
        int arbeitsvorgangFluglochkeil = cursor.getColumnIndex(ARBEITSVORGANG_FLUGLOCHKEIL);
        int arbeitsvorgangDrohnenrahmenPos1 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS1);
        int arbeitsvorgangDrohnenrahmenPos2 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS2);
        int arbeitsvorgangDrohnenrahmenPos3 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS3);
        int arbeitsvorgangDrohnenrahmenPos4 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS4);
        int arbeitsvorgangDrohnenrahmenPos5 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS5);
        int arbeitsvorgangDrohnenrahmenPos6 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS6);
        int arbeitsvorgangDrohnenrahmenPos7 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS7);
        int arbeitsvorgangDrohnenrahmenPos8 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS8);
        int arbeitsvorgangDrohnenrahmenPos9 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS9);
        int arbeitsvorgangDrohnenrahmenPos10 = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_POS10);
        int arbeitsvorgangTimestampCreate = cursor.getColumnIndex(ARBEITSVORGANG_TIMESTAMPCREATE);
        int arbeitsvorgangFluglochkeilWechsel = cursor.getColumnIndex(ARBEITSVORGANG_FLUGLOCHKEIL_WECHSEL);
        int arbeitsvorgangAbsperrgitterWechsel = cursor.getColumnIndex(ARBEITSVORGANG_ABSPERRGITTER_WECHSEL);
        int arbeitsvorgangDrohnenrahmenWechsel = cursor.getColumnIndex(ARBEITSVORGANG_DROHNENRAHMEN_WECHSEL);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            for( int k = 0; k != volkListe.size(); k = k + 1) {

                if(volkListe.get(k).getVolkId() == cursor.getInt(arbeitsvorgangVolkId)){

                    Arbeitsvorgang arbeitsvorgang = new Arbeitsvorgang(0, volkListe.get(k));

                    arbeitsvorgang.setArbeitsvorgangID(cursor.getInt(arbeitsvorgangId));
                    arbeitsvorgang.setAnzahlZargen(cursor.getInt(arbeitsvorgangAnzahlZargen));
                    arbeitsvorgang.setAbsperrgitter(intToBoolean(cursor.getInt(arbeitsvorgangAbsperrgitter)));
                    arbeitsvorgang.setAnzahlWaben(cursor.getInt(arbeitsvorgangAnzahlWaben));
                    arbeitsvorgang.setFluglochkeil(cursor.getString(arbeitsvorgangFluglochkeil));
                    arbeitsvorgang.setDrohnenramenAt(0,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos1)));
                    arbeitsvorgang.setDrohnenramenAt(1,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos2)));
                    arbeitsvorgang.setDrohnenramenAt(2,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos3)));
                    arbeitsvorgang.setDrohnenramenAt(3,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos4)));
                    arbeitsvorgang.setDrohnenramenAt(4,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos5)));
                    arbeitsvorgang.setDrohnenramenAt(5,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos6)));
                    arbeitsvorgang.setDrohnenramenAt(6,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos7)));
                    arbeitsvorgang.setDrohnenramenAt(7,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos8)));
                    arbeitsvorgang.setDrohnenramenAt(8,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos9)));
                    arbeitsvorgang.setDrohnenramenAt(9,intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenPos10)));
                    arbeitsvorgang.setTimestampCreate(cursor.getLong(arbeitsvorgangTimestampCreate));
                    arbeitsvorgang.setFluglochkeilWechsel(intToBoolean(cursor.getInt(arbeitsvorgangFluglochkeilWechsel)));
                    arbeitsvorgang.setAbsperrgitterWechsel(intToBoolean(cursor.getInt(arbeitsvorgangAbsperrgitterWechsel)));
                    arbeitsvorgang.setDrohnenrahmenWechsel(intToBoolean(cursor.getInt(arbeitsvorgangDrohnenrahmenWechsel)));

                    //flags setzen
                    arbeitsvorgang.setIsInDatabase(true);
                    arbeitsvorgang.setDataHasChanged(false);

                    list.add(arbeitsvorgang);

                }

            }

        }

        return list;

    }

    public int getArbeitsvorgangAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        return cursor.getCount();

    }

}
