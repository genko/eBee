package tagebuch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.ebee.R;

import logik.EBEEAblauf;
import vorgaenge.Ernte;

/**
 * Created by maxionderon on 28.11.17.
 */

public class TagebuchErnte extends TagebuchListenElement {

    private Ernte ernte;

    public TagebuchErnte(vorgaenge.Ernte ernte) {

        super(ernte.getTimestampCreate());

        this.ernte = ernte;

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
        gd.setColor(ContextCompat.getColor(context, R.color.tagebuch_colorErnte));
        gd.setCornerRadius(45);
        gd.setStroke(5, ContextCompat.getColor(context, R.color.tagebuch_colorBorder));

        textView.setBackground(gd);

        return textView;
    }

    public String getNotizIfSet(String notiz) {

        String s;

        if(notiz.equals("")) {

            s = "";

        } else {

            s = "\n" + "Notiz: " + ernte.getNotiz();

        }

        return s;

    }

    @Override
    public String toString() {

        String s =  "Ernte vom " + EBEEAblauf.createDateStringFromTimestamp(ernte.getTimestampCreate()) + "\n" +
                    "Anzahl der Waben: " + ernte.getAnzahlWaben() + "\n" +
                    "Honigsorte: " + ernte.getHonigSorte() + "\n" +
                    "Wassergehalt: " + ernte.getWassergehalt() + "\n" +
                    "Menge: " + ernte.getMenge() + " kg" +
                    getNotizIfSet(ernte.getNotiz()) ;

        return s;

    }




}
