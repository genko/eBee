package datenbank;

/**
 * Created by maxionderon on 26.11.17.
 */

public abstract class Tabelle {

    //HilfsMethoden
    protected int booleanToInt(boolean b) {

        int rueck;

        if(b == true){
            rueck = 1;
        }else {
            rueck = 0;
        }

        return rueck;

    }

    protected boolean intToBoolean(int i){

        boolean rueck;

        if(i == 0){
            rueck = false;
        } else {
            rueck = true;
        }

        return rueck;

    }

}
