package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import org.wordpress.android.R;

public class SeekBarPreference extends Preference {
    private SeekBar mSeekBar;

    public SeekBarPreference(Context context) {
        super(context);
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);

        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.seekbar_pref, parent, false);
        int seekPosition = mSeekBar == null ? 0 : mSeekBar.getProgress();

        if (view != null) {
            mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_pref_bar);
            if (mSeekBar != null) {
                mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                           @Override
                           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                           }

                           @Override
                           public void onStartTrackingTouch(SeekBar seekBar) {
                           }

                           @Override
                           public void onStopTrackingTouch(SeekBar seekBar) {
                               int progress = seekBar.getProgress();

                               if (progress <= 33) {
                                   setSummary("Allow search engines to index this site");
                                   mSeekBar.setProgress(0);
                               } else if (progress <= 67) {
                                   setSummary("Discourage search engines from indexing this site");
                                   mSeekBar.setProgress(50);
                               } else {
                                   setSummary("I would like my site to be private, visible only to users I choose");
                                   mSeekBar.setProgress(100);
                               }
                           }
                       }
                );
                mSeekBar.setProgress(seekPosition);
            }
        }

        return view;
    }
}
