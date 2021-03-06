package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private ArrayList<Word> words;

    private AdapterView.OnItemClickListener mOnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int postion, long id) {
            releaseMediaPlayer();
            if (mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(view.getContext(), words.get(postion).getAudioResourceID());
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                mMediaPlayer.start();
            }
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        Activities.adjustTabsViewListeners(this, R.id.family_tab);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ListView wordsListView = (ListView) findViewById(R.id.list);
        wordsListView.setBackgroundColor(getResources().getColor(R.color.category_family));

        words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.family_father), "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getString(R.string.family_mother), "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getString(R.string.family_son), "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getString(R.string.family_daughter), "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getString(R.string.family_older_brother), "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getString(R.string.family_younger_brother), "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getString(R.string.family_older_sister), "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getString(R.string.family_younger_sister), "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getString(R.string.family_grandmother), "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getString(R.string.family_grandfather), "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        wordsListView.setOnItemClickListener(mOnItemClickListener);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
