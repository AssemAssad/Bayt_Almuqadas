package com.example.baytalmuqadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.adapters.AyaAdapters;
import com.example.baytalmuqadas.models.AyaModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AyaActivity extends AppCompatActivity {


    private List<AyaModel> ayaModelList;

    String id_aya;
    ImageButton play_btn;
    TextView start_time, total_time;
    SeekBar seek_bar;
    Handler handler = new Handler();
    private String str;
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aya);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getAyatFromApi();

        id_aya = getIntent().getStringExtra("number");


        mediaPlayer.reset();
        mediaPlayer.release();
        try {
            listenAudio();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getAyatFromApi() {

        ayaModelList = new ArrayList<>();
        String URL_AYA = "https://quranenc.com/api/translation/sura/english_saheeh/"+ getIntent().getStringExtra("number");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_AYA, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                int num_Aya = 0;
                try {
                    num_Aya = response.getJSONArray("result").length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < num_Aya;i++) {
                    AyaModel ayaModel = new AyaModel();
                    try {
                        ayaModel.setId(response.getJSONArray("result").getJSONObject(i).getString("aya"));
                        ayaModel.setArabic_text(response.getJSONArray("result").getJSONObject(i).getString("arabic_text"));
                        ayaModel.setTranslation(response.getJSONArray("result").getJSONObject(i).getString("translation"));
                        ayaModelList.add(ayaModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                RecyclerView recyclerview_aya =  findViewById(R.id.recyclerview_aya);
                recyclerview_aya.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview_aya.setHasFixedSize(true);
                recyclerview_aya.setAdapter(new AyaAdapters(ayaModelList));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void listenAudio() throws IOException {
        play_btn = findViewById(R.id.play_btn);
        start_time = findViewById(R.id.start_time);
        total_time = findViewById(R.id.total_time);
        seek_bar = findViewById(R.id.seek_bar);

        mediaPlayer = new MediaPlayer();
        seek_bar.setMax(100);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    play_btn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                } else {
                    mediaPlayer.start();
                    play_btn.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    updateSeekBar();
                }
            }
        });

        preparedMediaPlayer();

        seek_bar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                start_time.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                seek_bar.setSecondaryProgress(i);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                seek_bar.setProgress(0);
                play_btn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                start_time.setText("0:00");
                total_time.setText("0:00");
                mediaPlayer.reset();
                try {
                    preparedMediaPlayer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void preparedMediaPlayer() throws IOException {

        int id = Integer.parseInt(id_aya);
        if (id < 10) {
            str = "00" + id;
        } else if (id < 100) {
            str = "0" + id;
        } else if (id >= 100) {
            str = String.valueOf(id);
        }
        mediaPlayer.setDataSource("https://download.quranicaudio.com/quran/maher_256/" + str.trim() + ".mp3");
        mediaPlayer.prepare();
        total_time.setText(timeToMilliSecond(mediaPlayer.getDuration()));
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long curentDuration = mediaPlayer.getCurrentPosition();
            start_time.setText(timeToMilliSecond(curentDuration));
        }
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seek_bar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String timeToMilliSecond(long milliSecond) {
        String timerString = "";
        String secondString;

        int hours = (int) (milliSecond / (1000 * 60 * 60));
        int minutes = (int) (milliSecond % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((milliSecond % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            secondString = hours + ":";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = "" + second;
        }
        timerString = timerString + minutes + ":" + secondString;

        return timerString;
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            play_btn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            play_btn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            play_btn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onPause();
    }
}