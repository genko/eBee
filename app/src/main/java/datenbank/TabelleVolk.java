package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Volk;

/**
 * Created by maxionderon on 25.11.17.
 */

public class TabelleVolk extends Tabelle{

    private static final String TABLE_NAME = "VOLK";
    private static final String VOLK_ID = "V_ID";
    private static final String VOLK_NAME = "V_NAME";
    private static final String VOLK_ZARGEN = "V_NUM_ZARGEN";
    private static final String VOLK_TYP = "V_TYP";
    private static final String VOLK_ABSPERRGITTER = "V_AB_GITTER";
    private static final String VOLK_FLUGLOCHKEIL = "V_FLUGLOCHKEIL";
    private static final String VOLK_WINDEL = "V_WINDEL";
    private static final String VOLK_BEHANDLUNG = "V_BEHANDLUNG";
    private static final String VOLK_KOENIGIN = "V_KOENIGIN";
    private static final String VOLK_TIMESTAMP_CREATE = "V_TIMESTAMP_CREATE";
    private static final String VOLK_TIMESTAMP_MODIFY = "V_TIMESTAMP_MODIFY";
    private static final String VOLK_STANDORT = "V_STANDORT";
    private static final String VOLK_WABEN = "V_WABEN";
    private static final String VOLK_HONIGMENGE = "V_HONIGMENGE";
    private static final String VOLK_NOTIZ = "V_NOTIZ";
    private static final String VOLK_DROHNENRAHMEN_POS1 = "V_DROHENRAHMEN_POS1";
    private static final String VOLK_DROHNENRAHMEN_POS2 = "V_DROHENRAHMEN_POS2";
    private static final String VOLK_DROHNENRAHMEN_POS3 = "V_DROHENRAHMEN_POS3";
    private static final String VOLK_DROHNENRAHMEN_POS4 = "V_DROHENRAHMEN_POS4";
    private static final String VOLK_DROHNENRAHMEN_POS5 = "V_DROHENRAHMEN_POS5";
    private static final String VOLK_DROHNENRAHMEN_POS6 = "V_DROHENRAHMEN_POS6";
    private static final String VOLK_DROHNENRAHMEN_POS7 = "V_DROHENRAHMEN_POS7";
    private static final String VOLK_DROHNENRAHMEN_POS8 = "V_DROHENRAHMEN_POS8";
    private static final String VOLK_DROHNENRAHMEN_POS9 = "V_DROHENRAHMEN_POS9";
    private static final String VOLK_DROHNENRAHMEN_POS10 ="V_DROHENRAHMEN_POS10";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                VOLK_ID + " int, " +
                VOLK_NAME + " varchar(255), " +
                VOLK_ZARGEN + " int, " +
                VOLK_TYP + " varchar(255), " +
                VOLK_ABSPERRGITTER + " int, " +
                VOLK_FLUGLOCHKEIL + " varchar(255), " +
                VOLK_WINDEL + " int,  " +
                VOLK_BEHANDLUNG + " int, " +
                VOLK_KOENIGIN + " int, " +
                VOLK_TIMESTAMP_CREATE + " long, " +
                VOLK_TIMESTAMP_MODIFY + " long, " +
                VOLK_STANDORT + " varchar(255), " +
                VOLK_WABEN + " int, " +
                VOLK_HONIGMENGE + " double, " +
                VOLK_NOTIZ + " text, " +
                VOLK_DROHNENRAHMEN_POS1 + " int, " +
                VOLK_DROHNENRAHMEN_POS2 + " int, " +
                VOLK_DROHNENRAHMEN_POS3 + " int, " +
                VOLK_DROHNENRAHMEN_POS4 + " int, " +
                VOLK_DROHNENRAHMEN_POS5 + " int, " +
                VOLK_DROHNENRAHMEN_POS6 + " int, " +
                VOLK_DROHNENRAHMEN_POS7 + " int, " +
                VOLK_DROHNENRAHMEN_POS8 + " int, " +
                VOLK_DROHNENRAHMEN_POS9 + " int, " +
                VOLK_DROHNENRAHMEN_POS10 + " int " +
                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertVolk(logik.Volk volk, SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TabelleVolk.TABLE_NAME + "(" +
                                            TabelleVolk.VOLK_ID + ", " +
                                            TabelleVolk.VOLK_NAME + ", " +
                                            TabelleVolk.VOLK_ZARGEN + ", " +
                                            TabelleVolk.VOLK_TYP + ", " +
                                            TabelleVolk.VOLK_ABSPERRGITTER + ", " +
                                            TabelleVolk.VOLK_FLUGLOCHKEIL + ", " +
                                            TabelleVolk.VOLK_WINDEL + ", " +
                                            TabelleVolk.VOLK_BEHANDLUNG + ", " +
                                            TabelleVolk.VOLK_KOENIGIN + ", " +
                                            TabelleVolk.VOLK_TIMESTAMP_CREATE + ", " +
                                            TabelleVolk.VOLK_TIMESTAMP_MODIFY + ", " +
                                            TabelleVolk.VOLK_STANDORT + ", " +
                                            TabelleVolk.VOLK_WABEN + ", " +
                                            TabelleVolk.VOLK_HONIGMENGE + ", " +
                                            TabelleVolk.VOLK_NOTIZ + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS1 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS2 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS3 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS4 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS5 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS6 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS7 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS8 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS9 + ", " +
                                            TabelleVolk.VOLK_DROHNENRAHMEN_POS10 + " " +
                                            ") " +
                            "VALUES (" +
                                            ((Integer) volk.getVolkId()).toString() + ", '" +
                                            volk.getVolkName() + "', " +
                                            ((Integer) volk.getAnzahlZargen()) + ", '" +
                                            volk.getVolkTyp() + "', " +
                                            booleanToInt(volk.getAbsperrgitter()) + ", '" +
                                            volk.getFluglochkeil() + "', " +
                                            booleanToInt(volk.getWindel()) + ", " +
                                            booleanToInt(volk.getBehandlung()) + ", " +
                                            booleanToInt(volk.getKönigin()) + ", " +
                                            volk.getTimestampCreate() + ", " +
                                            volk.getTimestampModify() + ", " +
                                            "'" + volk.getStandort() + "', " +
                                            volk.getAnzahlWaben() + ", " +
                                            volk.getMengeHonig() + ", " +
                                            "'" + volk.getNotiz() + "', " +
                                            booleanToInt(volk.getPosDrohnenrahmen(0)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(1)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(2)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(3)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(4)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(5)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(6)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(7)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(8)) + ", " +
                                            booleanToInt(volk.getPosDrohnenrahmen(9)) +
                                            ");";

        db.execSQL(query);

    }

    public void updateVolk(Volk volk, SQLiteDatabase database) {

        String query =  "UPDATE " + TabelleVolk.TABLE_NAME + " " +
                        "SET " +    TabelleVolk.VOLK_NAME + " = '" + volk.getVolkName() + "', " +
                                    TabelleVolk.VOLK_ZARGEN + " = " + ((Integer) volk.getAnzahlZargen()) + ", " +
                                    TabelleVolk.VOLK_TYP + " = '" + volk.getVolkTyp() + "', " +
                                    TabelleVolk.VOLK_ABSPERRGITTER + " = " + booleanToInt(volk.getAbsperrgitter()) + ", " +
                                    TabelleVolk.VOLK_FLUGLOCHKEIL + " = '" + volk.getFluglochkeil() + "', " +
                                    TabelleVolk.VOLK_WINDEL + " = " + booleanToInt(volk.getWindel()) + ", " +
                                    TabelleVolk.VOLK_BEHANDLUNG + " = " + booleanToInt(volk.getBehandlung()) + ", " +
                                    TabelleVolk.VOLK_KOENIGIN + " = " + booleanToInt(volk.getKönigin()) + ", " +
                                    TabelleVolk.VOLK_TIMESTAMP_CREATE + " = " + volk.getTimestampCreate() + ", " +
                                    TabelleVolk.VOLK_TIMESTAMP_MODIFY + " = " + volk.getTimestampModify() + ", " +
                                    TabelleVolk.VOLK_STANDORT + " = '" + volk.getStandort() + "', " +
                                    TabelleVolk.VOLK_WABEN + " = " + volk.getAnzahlWaben() + ", " +
                                    TabelleVolk.VOLK_HONIGMENGE + " = " + volk.getMengeHonig() + ", " +
                                    TabelleVolk.VOLK_NOTIZ + " = '" + volk.getNotiz() + "', " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS1 + " = " + booleanToInt(volk.getPosDrohnenrahmen(0)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS2 + " = " + booleanToInt(volk.getPosDrohnenrahmen(1)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS3 + " = " + booleanToInt(volk.getPosDrohnenrahmen(2)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS4 + " = " + booleanToInt(volk.getPosDrohnenrahmen(3)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS5 + " = " + booleanToInt(volk.getPosDrohnenrahmen(4)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS6 + " = " + booleanToInt(volk.getPosDrohnenrahmen(5)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS7 + " = " + booleanToInt(volk.getPosDrohnenrahmen(6)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS8 + " = " + booleanToInt(volk.getPosDrohnenrahmen(7)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS9 + " = " + booleanToInt(volk.getPosDrohnenrahmen(8)) + ", " +
                                    TabelleVolk.VOLK_DROHNENRAHMEN_POS10 + " = " + booleanToInt(volk.getPosDrohnenrahmen(9)) +
                        " WHERE " + TabelleVolk.VOLK_ID + " = " + ((Integer) volk.getVolkId()).toString() + ";" ;

        database.execSQL(query);

    }

    public ArrayList<Volk> getVolkList(SQLiteDatabase database){

        ArrayList<Volk> list = new ArrayList<Volk>();

        //Volk Tabelle abfragen
        String query = "SELECT * FROM " + TabelleVolk.TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int volkID = cursor.getColumnIndex(TabelleVolk.VOLK_ID);
        int volkName = cursor.getColumnIndex(TabelleVolk.VOLK_NAME);
        int volkAnzahlZargen = cursor.getColumnIndex(TabelleVolk.VOLK_ZARGEN);
        int volkTyp = cursor.getColumnIndex(TabelleVolk.VOLK_TYP);
        int volkAbsperrgitter = cursor.getColumnIndex(TabelleVolk.VOLK_ABSPERRGITTER);
        int volkFluglochkeil = cursor.getColumnIndex(TabelleVolk.VOLK_FLUGLOCHKEIL);
        int volkWindel = cursor.getColumnIndex(TabelleVolk.VOLK_WINDEL);
        int volkBehandlung = cursor.getColumnIndex(TabelleVolk.VOLK_BEHANDLUNG);
        int volkKönigin = cursor.getColumnIndex(TabelleVolk.VOLK_KOENIGIN);
        int volkTimestampCreate = cursor.getColumnIndex(TabelleVolk.VOLK_TIMESTAMP_CREATE);
        int volkTimestampModify = cursor.getColumnIndex(TabelleVolk.VOLK_TIMESTAMP_MODIFY);
        int volkStandort = cursor.getColumnIndex(TabelleVolk.VOLK_STANDORT);
        int volkAnzahlWaben = cursor.getColumnIndex(TabelleVolk.VOLK_WABEN);
        int volkHonigMenge = cursor.getColumnIndex(TabelleVolk.VOLK_HONIGMENGE);
        int volkNotiz = cursor.getColumnIndex(TabelleVolk.VOLK_NOTIZ);
        int volkPosDrohnenrahmen_1 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS1);
        int volkPosDrohnenrahmen_2 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS2);
        int volkPosDrohnenrahmen_3 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS3);
        int volkPosDrohnenrahmen_4 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS4);
        int volkPosDrohnenrahmen_5 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS5);
        int volkPosDrohnenrahmen_6 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS6);
        int volkPosDrohnenrahmen_7 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS7);
        int volkPosDrohnenrahmen_8 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS8);
        int volkPosDrohnenrahmen_9 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS9);
        int volkPosDrohnenrahmen_10 = cursor.getColumnIndex(TabelleVolk.VOLK_DROHNENRAHMEN_POS10);

        for( int i = 0; i != cursor.getCount(); i = i + 1 ) {

            cursor.moveToNext();

            Volk volk = new Volk(i);
            volk.setVolkId(cursor.getInt(volkID));
            volk.setVolkName(cursor.getString(volkName));
            volk.setAnzahlZargen(cursor.getInt(volkAnzahlZargen));
            volk.setvolkTyp(cursor.getString(volkTyp));
            volk.setAbsperrgitter(intToBoolean(cursor.getInt(volkAbsperrgitter)));
            volk.setFluglochkeil(cursor.getString(volkFluglochkeil));
            volk.setWindel(intToBoolean(cursor.getInt(volkWindel)));
            volk.setBehandlung(intToBoolean(cursor.getInt(volkBehandlung)));
            volk.setKönigin(intToBoolean(cursor.getInt(volkKönigin)));
            volk.setTimestampCreate(cursor.getLong(volkTimestampCreate));
            volk.setTimestampModify(cursor.getLong(volkTimestampModify));
            volk.setStandort(cursor.getString(volkStandort));
            volk.setAnzahlWaben(cursor.getInt(volkAnzahlWaben));
            volk.setMengeHonig(cursor.getDouble(volkHonigMenge));
            volk.setNotiz(cursor.getString(volkNotiz));
            volk.setPosDrohnenrahmenAt(0, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_1)));
            volk.setPosDrohnenrahmenAt(1, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_2)));
            volk.setPosDrohnenrahmenAt(1, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_3)));
            volk.setPosDrohnenrahmenAt(3, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_4)));
            volk.setPosDrohnenrahmenAt(4, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_5)));
            volk.setPosDrohnenrahmenAt(5, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_6)));
            volk.setPosDrohnenrahmenAt(6, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_7)));
            volk.setPosDrohnenrahmenAt(7, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_8)));
            volk.setPosDrohnenrahmenAt(8, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_9)));
            volk.setPosDrohnenrahmenAt(9, intToBoolean(cursor.getInt(volkPosDrohnenrahmen_10)));

            //flags setzen
            volk.setIsInDatabase(true);
            volk.setDataHasChanged(false);

            list.add(volk);

        }

        return list;

    }

    public int getVoelkerAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TabelleVolk.TABLE_NAME ;

        Cursor cursor = database.rawQuery(query, null);

        return cursor.getCount();

    }

}
