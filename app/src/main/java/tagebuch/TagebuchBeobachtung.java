package tagebuch;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.ebee.R;

import logik.EBEEAblauf;
import vorgaenge.Beobachtung;

/**
 * Created by maxionderon on 23.11.17.
 */

public class TagebuchBeobachtung extends TagebuchListenElement {

    private Beobachtung beobachtung;

    public TagebuchBeobachtung(Beobachtung beobachtung) {

        super(beobachtung.getTimestampCreate());

        this.beobachtung = beobachtung;

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
        gd.setColor(ContextCompat.getColor(context, R.color.tagebuch_colorBeobachtung));
        gd.setCornerRadius(45);
        gd.setStroke(5, ContextCompat.getColor(context, R.color.tagebuch_colorBorder));

        textView.setBackground(gd);

        return textView;

    }

    @Override
    public String toString() {

        String s = "Beobachtung vom " + EBEEAblauf.createDateStringFromTimestamp(this.beobachtung.getTimestampCreate()) + "\n" ;

        for ( int i = 0 ; i != this.beobachtung.getAnzahlTeilBeobachtungen() ; i = i +1 ) {

            s = s + "Teilbeobachtung " + (i+1) + "\n" +
                    this.beobachtung.getTeilBeaobachtung(i).getArtDerBeobachtung() +
                    getNotizIfSet(this.beobachtung.getTeilBeaobachtung(i).getNotiz()) ;

            if(i != this.beobachtung.getAnzahlTeilBeobachtungen() - 1) {

                s = s + "\n";

            }

        }

        return s;
    }

    private String getNotizIfSet(String teilbeobahtungNotiz) {

        if(teilbeobahtungNotiz.equals("")) {

            return "";

        } else {

            return "\n" + teilbeobahtungNotiz ;

        }

    }

}
