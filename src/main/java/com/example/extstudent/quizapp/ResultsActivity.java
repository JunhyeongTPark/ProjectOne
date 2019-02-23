package com.example.extstudent.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    int curScore = 0;
    int maxScore = 0;

    TextView textViewScore = null;
    TextView textViewResultDescription = null;

    Button buttonPlayAgain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Get the player score and max score from the previous Activity.
        // Set thse to curScore and maxScore respectively.
        // HINT: Remember that we stored these variables in .putExtra (from the question activity.
        //       To get this back try: getIntent().getIntExtra(..., 0)
        ///////////////////////////////////////////////////////////////////////////////////////////////////////




        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Display the score and score description for the user to see (set widgets text).
        // Hint: You can use the getPercentage() method provided to get the percentage correct value.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


        buttonPlayAgain = (Button)findViewById(R.id.buttonPlayAgain);
        this.buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////////////////////////////////////////////////////////////////////////
                // TO-DO: Make a play again button that will redirect the user back to the main activity.
                ////////////////////////////////////////////////////////////////////////////////////////
                Intent intent = new Intent(ResultsActivity.this,
                        com.example.extstudent.quizapp.MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private String getPercentage(int current, int total) {
        float fPercent = (float)current/(float)total;
        return Integer.toString((int)Math.ceil((fPercent) * 100)) + "%";
    }
}