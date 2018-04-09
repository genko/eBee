package datenbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import logik.Einstellungen;
import logik.Volk;
import vorgaenge.Arbeitsvorgang;

/**
 * Created by Vladi on 05.12.2017.
 */

public class TabelleEinstellungen extends Tabelle {
    private static final String TABLE_NAME = "EINSTELLUNGEN";
    private static final String EINSTELLUNGEN_ID = "E_ID";
    private static final String EINSTELLUNGEN_AUFGELOESTEVOELKERANZEIGEN = "E_AUGELOESTEVOELKER_ANZEIGEN";
    private static final String EINSTELLUNGEN_EMAIL_ADRESSE = "E_EMAIL_ADRESSE";

    public static String getSQLCreateTable(){

        return "CREATE TABLE " + TABLE_NAME + " ( " +
                EINSTELLUNGEN_ID + " int, " +
                EINSTELLUNGEN_AUFGELOESTEVOELKERANZEIGEN + " int, " +
                EINSTELLUNGEN_EMAIL_ADRESSE + " varchar(255) " +
                ");" ;

    }

    public static String getSQLDropTable() {

        return "DROP TABLE " + TABLE_NAME + ";";

    }

    public void insertEinstellungen(Einstellungen einstellungen, SQLiteDatabase db) {

        String query =  "INSERT INTO " +    TABLE_NAME + "(" +
                                                            EINSTELLUNGEN_ID + ", " +
                                                            EINSTELLUNGEN_AUFGELOESTEVOELKERANZEIGEN + ", " +
                                                            EINSTELLUNGEN_EMAIL_ADRESSE + " " +
                                                            ") " +
                        "VALUES (" +
                                    String.valueOf(einstellungen.getEinstellungId()) + ", " +
                                    String.valueOf(booleanToInt(einstellungen.getAufgelosteVoelkerAnzeigen())) + ", " +
                                    "'" + einstellungen.getEMailAdresse() + "'" +
                                    ");";

        db.execSQL(query);

    }

    public void updateEinstellungen(Einstellungen einstellungen, SQLiteDatabase database) {

        String query =  "UPDATE " + TABLE_NAME + " " +
                        "SET " + EINSTELLUNGEN_AUFGELOESTEVOELKERANZEIGEN + " = " + String.valueOf(booleanToInt(einstellungen.getAufgelosteVoelkerAnzeigen())) + ", " +
                        EINSTELLUNGEN_EMAIL_ADRESSE + " = '" + einstellungen.getEMailAdresse() + "'" +
                        " WHERE " + EINSTELLUNGEN_ID + " = " + einstellungen.getEinstellungId() + ";" ;

        database.execSQL(query);

    }

    public Einstellungen getEinstellungen(SQLiteDatabase database){

        Einstellungen einstellungen = new Einstellungen(1);

        //Tabelle abfragen
        String query = "SELECT * FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        int einstellungenId = cursor.getColumnIndex(EINSTELLUNGEN_ID);
        int einstellungenAufgeloesteVoelkerAnzeigen = cursor.getColumnIndex(EINSTELLUNGEN_AUFGELOESTEVOELKERANZEIGEN);
        int einstellungenEMailAdresse = cursor.getColumnIndex(EINSTELLUNGEN_EMAIL_ADRESSE);

        int zeige = cursor.getCount();

        if(cursor.getCount() != 0) {

            cursor.moveToNext();

            einstellungen.setEinstellungId(cursor.getInt(einstellungenId));
            einstellungen.setAufgeloesteVoelkerAnzeigen(intToBoolean(cursor.getInt(einstellungenAufgeloesteVoelkerAnzeigen)));
            einstellungen.setEMailAdresse(cursor.getString(einstellungenEMailAdresse));
            //flags setzen
            einstellungen.setIsInDatabase(true);
            einstellungen.setDataHasChanged(false);

        }

        return einstellungen;

    }

    public int getEinstellungenAnzahl(SQLiteDatabase database){

        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + ";";

        Cursor cursor = database.rawQuery(query, null);

        return cursor.getCount();

    }

}

