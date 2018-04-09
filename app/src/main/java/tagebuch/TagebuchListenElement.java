package tagebuch;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by maxionderon on 23.11.17.
 */

public abstract class TagebuchListenElement implements Comparable {

    private long timestamp;

    public TagebuchListenElement(long timestamp) {

        this.timestamp = timestamp;

    }

    abstract public String toString();
    abstract public TextView getTextView(Context context);

    public long getTimestamp() {

        return timestamp;

    }

    @Override
    public int compareTo(@NonNull Object o) {

        return (int) (  ((TagebuchListenElement) o).getTimestamp() - this.getTimestamp() );

    }
}
