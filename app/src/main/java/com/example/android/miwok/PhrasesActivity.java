package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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

        Activities.adjustTabsViewListeners(this, R.id.phrases_tab);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ListView wordsListView = (ListView) findViewById(R.id.list);
        wordsListView.setBackgroundColor(getResources().getColor(R.color.category_phrases));

        words=new ArrayList<Word>();
        words.add(new Word(getString(R.string.phrases_where_are_you_going), "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word(getString(R.string.phrases_what_is_your_name), "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word(getString(R.string.phrases_my_name_is), "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word(getString(R.string.phrases_how_are_you_feeling), "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word(getString(R.string.phrases_im_feeling_good), "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word(getString(R.string.phrases_are_you_coming), "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word(getString(R.string.phrases_yes_im_coming), "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word(getString(R.string.phrases_im_coming), "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word(getString(R.string.phrases_lets_go), "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word(getString(R.string.phrases_come_here), "әnni'nem", R.raw.phrase_come_here));

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
