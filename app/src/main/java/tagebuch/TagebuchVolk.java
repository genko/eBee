package tagebuch;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.ebee.R;

import logik.EBEEAblauf;

/**
 * Created by maxionderon on 23.11.17.
 */

public class TagebuchVolk extends TagebuchListenElement {

    //Attribute
    private logik.Volk volk;

    //Konstruktor
    public TagebuchVolk( logik.Volk volk) {

        super(volk.getTimestampCreate());

        this.volk = volk;

    }

    public TextView getTextView(Context context) {

        TextView textView = new TextView(context);

        textView.setText(this.toString());

        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        textView.setPadding(10,10,10,10);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(context, R.color.tagebuch_colorVolk));
        gd.setCornerRadius(45);
        gd.setStroke(5, ContextCompat.getColor(context, R.color.tagebuch_colorBorder));

        textView.setBackground(gd);

        return textView;

    }


    @Override
    public String toString(){

        String s =  "Volk wurde am " + EBEEAblauf.createDateStringFromTimestamp(this.volk.getTimestampCreate()) + " erstellt" + "\n" +
                    "Volksname: " + volk.getVolkName() + "\n" +
                    "Volkstyp: " + volk.getVolkTyp() + "\n" +
                    "Standort: " + volk.getStandort() ;

        return s;

    }

}
