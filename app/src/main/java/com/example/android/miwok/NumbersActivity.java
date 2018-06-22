package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private ArrayList<Word> words;

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
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
                releaseMediaPlayer();
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

        Activities.adjustTabsViewListeners(this, R.id.numbers_tab);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ListView wordsListView = (ListView) findViewById(R.id.list);
        wordsListView.setBackgroundColor(getResources().getColor(R.color.category_numbers));

        words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.numbers_one), "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word(getString(R.string.numbers_two), "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word(getString(R.string.numbers_three), "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word(getString(R.string.numbers_four), "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word(getString(R.string.numbers_five), "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word(getString(R.string.numbers_six), "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word(getString(R.string.numbers_seven), "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(getString(R.string.numbers_eight), "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(getString(R.string.numbers_nine), "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(getString(R.string.numbers_ten), "na’aacha", R.drawable.number_ten, R.raw.number_ten));

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
