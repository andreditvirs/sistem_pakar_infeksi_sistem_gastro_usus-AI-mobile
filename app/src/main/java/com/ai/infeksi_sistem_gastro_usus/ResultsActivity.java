package com.ai.infeksi_sistem_gastro_usus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    TextView mKeracunanThreshold, mKeracuananLainnya, mTulisanMinimalDugaan, mNama;
    LinearLayout mRetry;
    ArrayList<Integer> finalArrayOfAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mKeracunanThreshold = findViewById(R.id.keracunanThreshold);
        mKeracuananLainnya = findViewById(R.id.keracunanLainnya);
        mNama = findViewById(R.id.tulisanNama);
        mTulisanMinimalDugaan = findViewById(R.id.tulisanMinimalDugaan);

        mRetry = findViewById(R.id.retry);

        Bundle bundle = getIntent().getExtras();
        
        finalArrayOfAnswer = new ArrayList<>();
        finalArrayOfAnswer = bundle.getIntegerArrayList("finalArrayOfAnswer");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nama = prefs.getString("nama", "xxx");
        int threshold = prefs.getInt("threshold", 0);

        mNama.setText("Saudara/i " + nama+" diduga mengalami");
        ArrayList<Float> listHasilKeracunan;
        listHasilKeracunan = Main.cekDiDatabase(finalArrayOfAnswer);
        Main.setKeracunanThreshold(listHasilKeracunan, threshold);

        mTulisanMinimalDugaan.setText("Keracunan di bawah Minimal Dugaan (Threshold : "+threshold+")");
        mKeracunanThreshold.setText(Main.getHasilKeracunanThreshold());
        mKeracuananLainnya.setText(Main.getHasilKeracunanLainnya(listHasilKeracunan));

        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, QuizActivity.class));
                ResultsActivity.this.finish();
            }
        });
    }
}
