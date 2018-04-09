package tagebuch;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.ebee.R;

import logik.EBEEAblauf;
import vorgaenge.Arbeitsvorgang;

/**
 * Created by maxionderon on 23.11.17.
 */

public class TagebuchArbeitsvorgang extends TagebuchListenElement {

    private Arbeitsvorgang arbeitsvorgang;

    public TagebuchArbeitsvorgang(Arbeitsvorgang arbeitsvorgang) {

        super(arbeitsvorgang.getTimestampCreate());

        this.arbeitsvorgang = arbeitsvorgang;

    }




    @Override
    public String toString() {

        String s =  "Arbeitsvorgang vom: " + EBEEAblauf.createDateStringFromTimestamp(this.arbeitsvorgang.getTimestampCreate()) +
                    this.getFluglochkeilString() +
                    this.getAbsperrgitterString() +
                    this.getZargenString() +
                    this.getWabenString() +
                    this.getDrohnenrahmenString() ;

        return s;

    }

    private String getFluglochkeilString() {

        String s = "";

        if(this.arbeitsvorgang.getFluglochkeilWechsel() == true) {

            s = "\n" + "Fluglochkeil wurde gewechselt -> " + this.arbeitsvorgang.getFluglochkeil() ;

        }

        return s;

    }

    private String getAbsperrgitterString() {

        String s = "" ;

        if(this.arbeitsvorgang.getAbsperrgitterWechsel() == true) {

            if(this.arbeitsvorgang.getAbsperrgitter() == true) {

                s = "\n" + "Absperrgitter wurde hinzugefügt" ;

            } else {

                s = "\n" + "Absperrgitter wurde entfernt" ;

            }

        }

        return s;

    }

    private String getZargenString() {

        String s = "";

        if( this.arbeitsvorgang.getAnzahlZargen() < 0 ) {

            s = "\n" + "Es wurden " + (this.arbeitsvorgang.getAnzahlZargen()*-1) + " Zargen entfernt" ;

        }

        if( this.arbeitsvorgang.getAnzahlZargen() > 0 ) {

            s = "\n" + "Es wurden " + (this.arbeitsvorgang.getAnzahlZargen()) + " Zargen hinzugefügt" ;

        }

        return s;

    }

    private String getWabenString() {

        String s = "";

        if( this.arbeitsvorgang.getAnzahlWaben() < 0 ) {

            s = "\n" + "Es wurden " + (this.arbeitsvorgang.getAnzahlWaben()*-1) + " Waben entfernt" ;

        }

        if( this.arbeitsvorgang.getAnzahlWaben() > 0 ) {

            s = "\n" + "Es wurden " + (this.arbeitsvorgang.getAnzahlWaben()) + " Waben hinzugefügt" ;

        }

        return s;

    }

    private String getDrohnenrahmenString() {

        String s = "";

        if ( this.arbeitsvorgang.getDrohnenrahmenWechsel() == true) {

            s = "\n" + EBEEAblauf.calcAnzahlDrohnenrahmen(this.arbeitsvorgang.getDrohnenrahmen()) + " Drohnenrahmen";

        }

        return s;

    }

    @Override
    public TextView getTextView(Context context) {

        TextView textView = new TextView(context);

        textView.setText(this.toString());

        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        textView.setPadding(10,10,10,10);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(context, R.color.tagebuch_colorArbeitsvorgang));
        gd.setCornerRadius(45);
        gd.setStroke(5, ContextCompat.getColor(context, R.color.tagebuch_colorBorder));

        textView.setBackground(gd);

        return textView;

    }
}
