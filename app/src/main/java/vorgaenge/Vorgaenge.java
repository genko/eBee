package vorgaenge;

import logik.EBEEAblauf;
import logik.Volk;

/**
 * Created by maxionderon on 28.11.17.
 */

public abstract class Vorgaenge {

    //Volk als Fremdschlüssel
    private Volk volk;

    //Flags
    private boolean isInDatabase;
    private boolean dataHasChanged;

    private long timestampCreate;

    public Vorgaenge(Volk volk) {

        this.volk = volk;
        this.timestampCreate = EBEEAblauf.createTimestamp();

    }

    //get Methoden

    public int getVolkId() {

        return this.volk.getVolkId();

    }

    public boolean getIsInDatabase() {

        return this.isInDatabase;

    }

    public boolean getDataHasChanged() {

        return this.dataHasChanged;

    }

    public long getTimestampCreate() {

        return this.timestampCreate;

    }

    //set Methoden

    public void setIsInDatabase(boolean isInDatabase) {

        this.isInDatabase = isInDatabase;

    }

    public void setDataHasChanged(boolean dataHasChanged) {

        this.dataHasChanged = dataHasChanged;

    }

    public void setTimestampCreate(long timetampCreate) {

        this.timestampCreate = timetampCreate;

    }

}
