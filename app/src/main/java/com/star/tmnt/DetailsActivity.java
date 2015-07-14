package com.star.tmnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String result = intent.getStringExtra(MainActivity.RESULT);

        setInfo(result);

    }

    private void setInfo(String result) {
        String[] turtles = getResources().getStringArray(R.array.turtles);
        String[] infos = getResources().getStringArray(R.array.infos);
        for (int i = 0; i < turtles.length; i++) {
            if (turtles[i].equals(result)) {
                TextView infoTextView = (TextView) findViewById(R.id.turtle_details);
                infoTextView.setText(getResources().getIdentifier(
                        infos[i], "string", getPackageName()));
                return;
            }
        }
    }
}
