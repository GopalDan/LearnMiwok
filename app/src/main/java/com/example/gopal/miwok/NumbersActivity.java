package com.example.gopal.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class NumbersActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    private ImageView mPlayIcon;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                //Resume the audio
                mMediaPlayer.start();
            }
            else if(focusChange ==AudioManager.AUDIOFOCUS_LOSS){
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager =  (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mPlayIcon = (ImageView) findViewById(R.id.play_button);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One","Lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six","temmoka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven","kenekaka",R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight","kawinta",R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine","wo'e",R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten","na'aacha",R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this,words,R.color.number_catagory_color);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = words.get(i);

                //If music is playing release it before playing new song
                releaseMediaPlayer();

                 //Request audio focus for playback
                int result  = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result ==  AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //If we have audio focus now
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, currentWord.getmSongResourceId());
                    mMediaPlayer.start();

                    //Change play icon into pause icon
                   // mPlayIcon.setImageResource(R.drawable.ic_pause);


                    // This can be done via un-anonymous calling as well
                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });




//        LinearLayout rootView = findViewById(R.id.rootView);
//        for(int i=0;i<words.size();i++) {
//            TextView child = new TextView(this);
//            child.setText(words.get(i));
//            rootView.addView(child);
//        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
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

            //Change pause icon into play icon
           // mPlayIcon.setImageResource(R.drawable.play_icon);

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //Releasing audio focus
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
