package vorgaenge;

import java.util.ArrayList;

import logik.Volk;

/**
 * Created by maxionderon on 23.11.17.
 */

public class Beobachtung extends Vorgaenge {

    //ID der Beobachtung
    private int beobachtungId;

    //Attribute der Beobachtung
    ArrayList<TeilBeobachtung> teilBeobachtungList;

    //Konstruktor
    public Beobachtung(int beobachtungId, Volk volk){

        super(volk);

        this.beobachtungId = beobachtungId;

        this.teilBeobachtungList = new ArrayList<TeilBeobachtung>();

    }

    //set-Methoden
    public void setBeobachtungId(int beobachtungID){

        this.beobachtungId = beobachtungID;

    }

    public void setTeilBeobachtungList(ArrayList<TeilBeobachtung> teilBeobachtungList) {

        this.teilBeobachtungList = teilBeobachtungList;

    }

    //get-Methoden

    public int getBeobachtungId() {

        return this.beobachtungId;

    }

    public ArrayList<TeilBeobachtung> getTeilBeobachtungList () {

        return this.teilBeobachtungList;

    }

    public TeilBeobachtung getTeilBeaobachtung(int index) {

        return this.teilBeobachtungList.get(index);

    }

    public int getAnzahlTeilBeobachtungen() {

        return this.teilBeobachtungList.size();

    }

    //Beobachtung Methoden

    public void addTeilBeobachtung(TeilBeobachtung teilBeobachtung) {

        this.teilBeobachtungList.add(teilBeobachtung);

    }

}
