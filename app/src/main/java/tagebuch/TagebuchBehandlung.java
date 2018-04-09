package tagebuch;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.ebee.R;

import logik.EBEEAblauf;
import vorgaenge.Behandlung;

/**
 * Created by maxionderon on 23.11.17.
 */

public class TagebuchBehandlung extends TagebuchListenElement {

    private Behandlung behandlung;

    public TagebuchBehandlung(Behandlung behandlung) {

        super(behandlung.getTimestampCreate());

        this.behandlung = behandlung;

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
        gd.setColor(ContextCompat.getColor(context, R.color.tagebuch_colorBehandlug));
        gd.setCornerRadius(45);
        gd.setStroke(5, ContextCompat.getColor(context, R.color.tagebuch_colorBorder));

        textView.setBackground(gd);

        return textView;

    }

    @Override
    public String toString() {

        String s =  "Behandlung vom " + EBEEAblauf.createDateStringFromTimestamp(this.behandlung.getTimestampCreate()) + "\n" +
                    getStringFinish(this.behandlung.getTimestampFinish()) + "\n" +
                    "Art :" + this.behandlung.getArtDerBehandlung() + "\n" +
                    "Menge: " + this.behandlung.getMenge() + " ml" +
                    getNotizIsSet(this.behandlung.getText());

        return s;

    }

    private String getStringFinish(long timestamp) {

        String s = "Behandlung " ;

        if( timestamp > EBEEAblauf.createTimestamp() ) {

            s = s + "wird am " +  EBEEAblauf.createDateStringFromTimestamp(timestamp) + " beendet";

        } else {

            s = s + "wurde am " + EBEEAblauf.createDateStringFromTimestamp(timestamp) + " beendet";

        }

        return s;

    }

    private String getNotizIsSet(String text) {

        if(text.equals("")) {

            return "";

        } else {

            return "\n" + "Notiz: " + text ;

        }

    }

}
