package com.example.extstudent.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<QuizQuestion> quizQuestionList = null;
    QuizQuestion currentQuestion = null;
    int currentQuestionNumber = 1;

    private int currentScore = 0;
    private int maxScore = 0;

    TextView textViewQuestionTitle = null;
    TextView textViewQuestion = null;
    TextView textViewScore = null;

    RadioGroup radioGroupQuestion = null;
    RadioButton radioButtonA = null;
    RadioButton radioButtonB = null;
    RadioButton radioButtonC = null;
    RadioButton radioButtonD = null;

    Button buttonSubmit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize widgets.
        this.textViewQuestionTitle = (TextView)findViewById(R.id.textViewQuestionTitle);    // The question title
        this.textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);              // The question asked.
        this.textViewScore = (TextView)findViewById(R.id.textViewScore);                    // The current score.

        // Intialize the radio buttons for question multiple choice answers.
        radioGroupQuestion = (RadioGroup)findViewById(R.id.radioGroupQuestion);             // Create a group for radio buttons.
        radioButtonA = (RadioButton)findViewById(R.id.radioButtonA);
        radioButtonB = (RadioButton)findViewById(R.id.radioButtonB);
        radioButtonC = (RadioButton)findViewById(R.id.radioButtonC);
        radioButtonD = (RadioButton)findViewById(R.id.radioButtonD);

        // Initialize the submit question answer along with the callback function.
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1: Call validateAnswer() to check if the current answer (provided by user) is correct (valid).
                // Step 2: Compare the current question number with the max score. If the case where the current question number
                //         is less than the max score (we have more questions to go.
                // Step 3: If we still have questions to ask: (1) Set the currentQuestion variable, (2) prompt the user with setQuestionView.
                //         and (3) remember to increment the currentQuestionNumber.
                // Step 4: If there are now questions left in the array, transition the user to the results activity.
                if(validateAnswer()) {
                    if(currentQuestionNumber < maxScore) {
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                        // You can set the reference from the quizQuestionList.
                        // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////

                        currentQuestion = quizQuestionList.get(currentQuestionNumber);
                        currentQuestionNumber++;
                        setQuestionView(currentQuestion);

                    }
                    else {
                        // No questions left to ask. Transition the user to the results page.

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Create a new intent that will transition to user to next ResultsActivity.
                        // HINT: We want to pass in some extra values for the results class to use. So use something like:
                        //       intent.putExtra(...);
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        Intent intent = new Intent(QuizActivity.this,
                                com.example.extstudent.quizapp.ResultsActivity.class);
                        intent.putExtra("Player score", currentScore);
                        intent.putExtra("Max score", maxScore);
                        startActivity(intent);

                    }
                }
            }
        });

        // Initiate all questions first.
        this.initQuestions();

        // Ask use the first question when this activity loads.
        this.setQuestionView(this.currentQuestion);
    }

    private void initQuestions() {
        // Create some questions to ask the questions.

        this.quizQuestionList = new ArrayList<QuizQuestion>();  // Initialize our question array.

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Create instances (using the new QuizQuestion()) of your questions.
        // You will have to call into QuizQuestion() setters to set the follow:
        // - The question to ask.
        // - Set choice options for A, B, C, and D.
        // - Set the correct answer (so that class knows which one is correct or not).
        // - Remember to add the object to our quizQuestionList array. Hint: Use .add(...) function here.
        // NOTE: No widgets should be set in this method.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        QuizQuestion q1 = new QuizQuestion();
        q1.setQuestion("What is the first name of the creator of this app?");
        q1.setChoiceA("Andrew");
        q1.setChoiceB("James");
        q1.setChoiceC("Thomas");
        q1.setChoiceD("Bryce");
        q1.setCorrectAnswer("Thomas");
        this.quizQuestionList.add(q1);

        QuizQuestion q2 = new QuizQuestion();
        q2.setQuestion("How many words were in the start page?");
        q2.setChoiceA("17");
        q2.setChoiceB("5");
        q2.setChoiceC("9");
        q2.setChoiceD("13");
        q2.setCorrectAnswer("13");
        this.quizQuestionList.add(q2);

        QuizQuestion q3 = new QuizQuestion();
        q3.setQuestion("What is the last name of the creator of this app?");
        q3.setChoiceA("Lee");
        q3.setChoiceB("Song");
        q3.setChoiceC("Han");
        q3.setChoiceD("Park");
        q3.setCorrectAnswer("Park");
        this.quizQuestionList.add(q3);

        QuizQuestion q4 = new QuizQuestion();
        q4.setQuestion("2 + 2 X 2");
        q4.setChoiceA("4");
        q4.setChoiceB("8");
        q4.setChoiceC("6");
        q4.setChoiceD("2");
        q4.setCorrectAnswer("6");
        this.quizQuestionList.add(q4);

        QuizQuestion q5 = new QuizQuestion();
        q5.setQuestion("What was the answer to question 3?");
        q5.setChoiceA("Park");
        q5.setChoiceB("Bryce");
        q5.setChoiceC("Thomas");
        q5.setChoiceD("Song");
        q5.setCorrectAnswer("Park");
        this.quizQuestionList.add(q5);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        this.currentQuestion = q1;

        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
        this.maxScore = this.quizQuestionList.size();
        this.currentScore = 0;
    }

    private void setQuestionView(QuizQuestion quizQuestion) {
        if(quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroupQuestion.clearCheck();

        // Loads the current question view.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set the UI view (all your widgets) with the current QuizQuestion passed in.
        // Hint: Use your getters from the QuizQuestion class to get the values stored there.
        // Set the following widget text:
        // - The question text (i.e. Question #1).
        // - Set the question to ask.
        // - Set all for radio button text.
        // - Set the score view with the current score (remeber to convert integer to string).
        //   Example: Score: 2
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        textViewQuestionTitle.setText("Question #" + Integer.toString(currentQuestionNumber));
        textViewQuestion.setText(currentQuestion.getQuestion());
        radioButtonA.setText(currentQuestion.getChoiceA());
        radioButtonB.setText(currentQuestion.getChoiceB());
        radioButtonC.setText(currentQuestion.getChoiceC());
        radioButtonD.setText(currentQuestion.getChoiceD());
        textViewScore.setText("Score: " + Integer.toString(currentScore));

    }

    private boolean validateAnswer() {
        // Validate the current answer selected.
        int selectedButtonId = this.radioGroupQuestion.getCheckedRadioButtonId();
        if(selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton)findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.isCorrectAnswer(answerSelectedStr)) {
                // Answer is correct.
                Log.d("ANSWER: ", "Correct");
                currentScore++;
            }
            else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true; // Allow to continue to next question.
        }
        else {
            // No answer selected.
            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
