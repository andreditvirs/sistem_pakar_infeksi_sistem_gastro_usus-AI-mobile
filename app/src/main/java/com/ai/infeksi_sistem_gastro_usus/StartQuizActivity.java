package com.ai.infeksi_sistem_gastro_usus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class StartQuizActivity extends AppCompatActivity {

    LinearLayout mStart;
    TextView mNama;
    SeekBar threshold;
    int progressChangedValue = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        mNama = findViewById(R.id.txtINama);

        threshold = findViewById(R.id.threshold);
        threshold.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(StartQuizActivity.this, "Threshold : " + progressChangedValue, Toast.LENGTH_SHORT).show();
            }
        });

        mStart = findViewById(R.id.startButton);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = mNama.getText().toString().trim();
                if(!TextUtils.isEmpty(nama)){
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(StartQuizActivity.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("nama", nama);
                    editor.putInt("threshold", progressChangedValue);
                    editor.commit();
                    startActivity(new Intent(StartQuizActivity.this, QuizActivity.class));
                }else{
                    mNama.requestFocus();
                    mNama.setError("Masukkan Nama Saudara/i/Pasien");
                }
            }
        });
    }
}
