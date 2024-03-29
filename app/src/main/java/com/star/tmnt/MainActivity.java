package com.star.tmnt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String RESULT = "RESULT";

    private MediaPlayer mMediaPlayer;

    private String mResultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mResultString = parent.getSelectedItem().toString();
                setResult(mResultString);
                setImageView(mResultString);
                setInfo(mResultString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(RESULT, mResultString);
                startActivity(intent);
            }
        });

        mMediaPlayer = MediaPlayer.create(this, R.raw.tmnt_theme);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        mMediaPlayer.pause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }

        super.onDestroy();
    }

    private void setResult(String result) {
        TextView resultTextView = (TextView) findViewById(R.id.textView);
        resultTextView.setText("You chose " + result);
    }

    private void setImageView(String result) {
        String[] turtles = getResources().getStringArray(R.array.turtles);
        String[] images = getResources().getStringArray(R.array.images);
        for (int i = 0; i < turtles.length; i++) {
            if (turtles[i].equals(result)) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(getResources().getIdentifier(
                        images[i], "mipmap", getPackageName()));
                return;
            }
        }
    }

    private void setInfo(String result) {
        String[] turtles = getResources().getStringArray(R.array.turtles);
        String[] infos = getResources().getStringArray(R.array.infos);
        for (int i = 0; i < turtles.length; i++) {
            if (turtles[i].equals(result)) {
                TextView infoTextView = (TextView) findViewById(R.id.turtle_info);
                infoTextView.setText(getResources().getIdentifier(
                        infos[i], "string", getPackageName()));
                return;
            }
        }
    }

}
