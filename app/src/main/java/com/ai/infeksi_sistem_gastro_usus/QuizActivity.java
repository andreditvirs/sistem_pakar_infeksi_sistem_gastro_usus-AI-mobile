package com.ai.infeksi_sistem_gastro_usus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView mScoreView, mQuestion;
    private ImageView mImageView;
    private Button mTrueButton, mFalseButton;

    private ArrayList<Integer> mArrayOfAnswer;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mArrayOfAnswer = new ArrayList<>();

//        mScoreView = findViewById(R.id.points);
        mImageView = findViewById(R.id.imageView);
        mQuestion = findViewById(R.id.question);
        mTrueButton = findViewById(R.id.trueButton);
        mFalseButton = findViewById(R.id.falseButton);

        updateQuestion();

        //logic for true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mArrayOfAnswer.add(mQuestionNumber);
                    // Perform this check before you update the question
                    if(mQuestionNumber == QuizBook.arrayOfQuestions.length){
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList("finalArrayOfAnswer", mArrayOfAnswer);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    }else{
                        updateQuestion();
                    }
            }
        });

        // logic for false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Perform this check before you update the question
                    if(mQuestionNumber == QuizBook.arrayOfQuestions.length){
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList("finalArrayOfAnswer", mArrayOfAnswer);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    }else{
                        updateQuestion();
                    }
            }
        });
    }

    private void updateQuestion(){
        mImageView.setImageResource(QuizBook.images[mQuestionNumber]);
        mQuestion.setText(QuizBook.arrayOfQuestions[mQuestionNumber]);
        mQuestionNumber++;
    }
}
