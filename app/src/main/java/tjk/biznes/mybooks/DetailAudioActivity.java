package tjk.biznes.mybooks;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DetailAudioActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private TextView playerPosition, playerDuration, title, descTitle;
    private SeekBar seekBar;
    private ImageView btnRew, btnPlay, btnPause, btnFf, btnNext, btnPrev, btnRepeat, btnDownload, btnDownloadDone;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int mp3;
    private int currentIndex = 0;
    private Boolean repeatFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_audio);

        playerPosition = findViewById(R.id.player_position);
        title = findViewById(R.id.textView4);
        descTitle = findViewById(R.id.descTitle);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnRew = findViewById(R.id.btn_rew);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnFf = findViewById(R.id.btn_ff);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnRepeat = findViewById(R.id.btn_repeat);
        btnDownload = findViewById(R.id.btn_download);
        btnDownloadDone = findViewById(R.id.btn_download_done);

        ArrayList<Integer> songs = new ArrayList<>();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mediaPlayer.start();
            }
        });
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatFlag) {
                    btnRepeat.setImageResource(R.drawable.ic_repeat);
                } else {
                    btnRepeat.setImageResource(R.drawable.ic_repeat_one);
                }
                repeatFlag = !repeatFlag;
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDownload.setVisibility(View.GONE);
                btnDownloadDone.setVisibility(View.VISIBLE);
                startDownloaded("https://firebasestorage.googleapis.com/v0/b/my-books-16cdc.appspot.com/o/foreword.mp3?alt=media&token=395daa3f-a6ed-4c89-8102-7d75a040c3c5", "foreword.mp3");
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title.setText(bundle.getInt("title"));
            descTitle.setText(bundle.getInt("descTitle"));
            title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            descTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            title.setSelected(true);
            descTitle.setSelected(true);
            mp3 = bundle.getInt("mp3");
        }

        mediaPlayer = MediaPlayer.create(this, mp3);
        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayer.getDuration();
        String sDuration = convertFormat(duration);
        playerDuration.setText(sDuration);
        btnPlay.setOnClickListener(v -> {
            File file = new File(getCacheDir(), "foreword.mp3");
            MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
            if (file.exists()) {
                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);
            } else {
                Toast.makeText(getApplicationContext(), "Сначало надо скачать аудио файл", Toast.LENGTH_SHORT).show();
            }
        });


        btnPause.setOnClickListener(v -> {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
            mediaPlayer.pause();
            handler.removeCallbacks(runnable);
        });

        btnFf.setOnClickListener(v -> {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int duration1 = mediaPlayer.getDuration();
            if (mediaPlayer.isPlaying() && duration1 != currentPosition) {
                currentPosition = currentPosition + 5000;
                playerPosition.setText(convertFormat(currentPosition));
                mediaPlayer.seekTo(currentPosition);
            }
        });

        btnRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (mediaPlayer.isPlaying() && currentPosition > 500) {
                    currentPosition = currentPosition - 500;
                    playerPosition.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }

                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration)
                , TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    private void startDownloaded(String url, String name) {
        File localFile;
        try {
            localFile = new File(
                    getCacheDir(), name
            );
            localFile.createNewFile();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorageRef = storage.getReferenceFromUrl(url);
            mStorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                }
            }); {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAudioFromCache(String name) {
        File file = new File(getCacheDir(), name);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }


}