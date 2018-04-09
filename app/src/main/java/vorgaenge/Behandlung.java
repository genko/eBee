package vorgaenge;

import logik.EBEEAblauf;
import logik.Volk;

/**
 * Created by maxionderon on 23.11.17.
 */

public class Behandlung extends Vorgaenge {

    //ID der Behandlung
    private int behandlungId;

    //Attribute der Behandlung
    private String artDerBehandlung;
    private float menge;
    private boolean windelEingesetzt;
    private String text;
    private boolean isActiv;

    //Timestamp
    private long timestampFinish;



    public Behandlung(int behandlungId, logik.Volk volk){

        super(volk);

        this.behandlungId = behandlungId;

        this.artDerBehandlung = "";
        this.menge = 0f;
        this.windelEingesetzt = false;
        this.text = "";
        this.isActiv = true;

        this.timestampFinish = EBEEAblauf.createTimestamp();

    }

    //get-Methoden
    public int getBehandlungId() {

        return this.behandlungId;

    }

    public String getArtDerBehandlung(){

        return this.artDerBehandlung;

    }

    public float getMenge(){

        return this.menge;

    }

    public boolean getWindelEingesetzt(){

        return this.windelEingesetzt;

    }

    public String getText() {

        return this.text;

    }

    public boolean getIsActiv() {

        return this.isActiv;

    }

    public long getTimestampFinish() {

        return this.timestampFinish;

    }


    //setMethoden
    public void setBehandlungId(int behandlungId){

        this.behandlungId = behandlungId;

    }

    public void setArtDerBehandlung(String artDerBehandlung) {

        this.artDerBehandlung = artDerBehandlung;

    }

    public void setMenge(float menge){

        this.menge = menge;

    }

    public void setWindelEingesetzt(boolean windelEingesetzt) {

        this.windelEingesetzt = windelEingesetzt;

    }

    public void setText(String text){

        this.text = text;

    }

    public void setIsActiv(boolean isActiv) {

        this.isActiv = isActiv;

    }

    public void setTimestampFinish(long timestampFinish) {

        this.timestampFinish = timestampFinish;

    }

    //Behandlung Methoden

    @Override
    public String toString() {

        String s =  "Art der Behandlung: " + this.getArtDerBehandlung() + "\n" +
                    "Beginn: " + EBEEAblauf.createDateStringFromTimestamp(this.getTimestampCreate()) + "\n" +
                    "Ende: " + EBEEAblauf.createDateStringFromTimestamp(this.getTimestampFinish()) ;

        return s;

    }

}
