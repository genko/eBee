package inventar;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.d.ebee.Behandlung;
import com.example.d.ebee.R;

import java.util.ArrayList;

import logik.Volk;


/**
 * Created by maxionderon on 10.11.17.
 */

public class Inventar {

    int inventarId;

    //Attribute
    //Gegenstände
    private int zargen;
    private int rahmen;
    private int mittelwaende;
    private int absperrgitter;
    private int fluglochkeil;
    private int windel;
    //Säuren
    private float ameisensaeure;
    private float oxalsaeure;
    private float milchsaeure;

    //Flags für Datenbank
    private boolean isInDatabase;
    private boolean dataHasChanged;

    public Inventar(int id) {

        this.inventarId = id;

        this.zargen = 0;
        this.rahmen = 0;
        this.mittelwaende = 0;
        this.absperrgitter = 0;
        this.fluglochkeil = 0;
        this.windel = 0;

        this.ameisensaeure = 0f;
        this.oxalsaeure = 0f;
        this.milchsaeure = 0f;

        //flags setzen
        this.isInDatabase = false;
        this.dataHasChanged = false;

    }

    //get-Methoden

    public int getInventarId() {

        return this.inventarId;

    }

    public int getZargen() {

        return this.zargen;

    }

    public int getRahmen() {

        return this.rahmen;

    }

    public int getMittelwaende() {

        return this.mittelwaende;

    }

    public int getAbsperrgitter() {

        return this.absperrgitter;

    }

    public int getFluglochkeil() {

        return this.fluglochkeil;

    }

    public int getWindel() {

        return this.windel;

    }

    public float getAmeisensaeure() {

        return this.ameisensaeure;

    }

    public float getOxalsaeure() {

        return this.oxalsaeure;

    }

    public float getMilchsaeure() {

        return this.milchsaeure;

    }

    public boolean getIsinDatabase() {

        return isInDatabase;
    }

    public boolean getDataHasChanged() {

        return this.dataHasChanged;

    }

    //set-Methoden

    public void setInventarId(int inventarId) {

        this.inventarId = inventarId;

    }

    public void setZargen(int zargen) {

        this.zargen = zargen;

    }

    public void setRahmen(int rahmen) {

        this.rahmen = rahmen;

    }

    public void setMittelwaende(int mittelwaende) {

        this.mittelwaende = mittelwaende;

    }

    public void setAbsperrgitter(int absperrgitter) {

        this.absperrgitter = absperrgitter;

    }

    public void setFluglochkeil(int fluglochkeil) {

        this.fluglochkeil = fluglochkeil;

    }

    public void setWindel(int windel) {

        this.windel = windel;

    }

    public void setAmeisensaeure(float ameisensaeure) {

        this.ameisensaeure = ameisensaeure;

    }

    public void setOxalsaeure(float oxalsaeure) {

        this.oxalsaeure = oxalsaeure;

    }

    public void setMilchsaeure(float milchsaeure) {

        this.milchsaeure = milchsaeure;

    }

    public void setIsInDatabase (boolean isInDatabase) {

        this.isInDatabase = isInDatabase;

    }

    public void setDataHasChanged (boolean dataHasChanged ) {

        this.dataHasChanged = dataHasChanged;

    }

    //Methoden

    public int calcZargenInUse(ArrayList<Volk> list, Context context) {

        int zargenInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                zargenInUse = zargenInUse + list.get(i).getAnzahlZargen();

            }

        }

        return zargenInUse;

    }

    public int calcRahmenInUse(ArrayList<Volk> list, Context context) {
        //Rahmen sind Drohnenrahmen
        int rahmenInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                rahmenInUse = rahmenInUse + list.get(i).getDrohnenrahmen();

            }

        }

        return rahmenInUse;

    }

    public int calcMittelwaendeInUse(ArrayList<Volk> list, Context context) {
        //Mittelwaende sind Waben
        int mittelwaendeInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                mittelwaendeInUse = mittelwaendeInUse + list.get(i).getAnzahlWaben() ; //list.get(i).getDrohnenrahmen();

            }

        }

        return mittelwaendeInUse;

    }

    public int calcAbsperrgitterInUse(ArrayList<Volk> list, Context context) {

        int absperrgitterInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                if(list.get(i).getAbsperrgitter() == true) {

                    absperrgitterInUse = absperrgitterInUse + 1;

                }

            }

        }

        return absperrgitterInUse;

    }

    public int calcFluglochkeilInUse(ArrayList<Volk> list, Context context) {

        int fluglochkeilInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                if(!list.get(i).getFluglochkeil().equals("Kein")) {

                    fluglochkeilInUse = fluglochkeilInUse + 1;

                }

            }

        }

        return fluglochkeilInUse;

    }

    public int calcWindelInUse(ArrayList<Volk> list, Context context) {

        int windelInUse = 0;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(!list.get(i).getVolkTyp().equals(context.getString(R.string.logik_Volkstyp_Aufgeloest))) {

                if(list.get(i).getWindel() == true) {

                    windelInUse = windelInUse + 1;

                }

            }

        }

        return windelInUse;

    }

    public float calcAmeisensaeureInUse(ArrayList<vorgaenge.Behandlung> list, Context context) {

        float ameisensaeureInUse = 0f;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(list.get(i).getIsActiv() == true && list.get(i).getArtDerBehandlung().equals(context.getString(R.string.neueBehandlung_Behandlung_ameisensaure))) {

                ameisensaeureInUse = ameisensaeureInUse + list.get(i).getMenge();

            }

        }

        return ameisensaeureInUse;

    }

    public float calcOxalsaeureInUse(ArrayList<vorgaenge.Behandlung> list, Context context) {

        float oxalsaeureInUse = 0f;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(list.get(i).getIsActiv() == true && list.get(i).getArtDerBehandlung().equals(context.getString(R.string.neueBehandlung_Behandlung_oxalsaure))) {

                oxalsaeureInUse = oxalsaeureInUse + list.get(i).getMenge();

            }

        }

        return oxalsaeureInUse;

    }

    public float calcMilchsaeureInUse(ArrayList<vorgaenge.Behandlung> list, Context context) {

        float milchsaeureInUse = 0f;

        for(int i = 0 ; i != list.size() ; i = i + 1) {

            if(list.get(i).getIsActiv() == true && list.get(i).getArtDerBehandlung().equals(context.getString(R.string.neueBehandlung_Behandlung_milchsaure))) {

                milchsaeureInUse = milchsaeureInUse + list.get(i).getMenge();

            }

        }

        return milchsaeureInUse;

    }

}

