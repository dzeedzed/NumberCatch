package me.dzed.numbercatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends Activity {

    private String tempAnswer = "";
    private String solution = "";

    private int totalAnswered = 0;
    private int totalCorrect = 0;
    private boolean correct = false;
    private boolean first = true;

    private TextView scoreView;
    private TextView answerView;
    private TextView solutionView;

    // Auxiliary buttons
    private ImageButton back;
    private ImageButton pause;

    private ImageButton zero;
    private ImageButton one;
    private ImageButton two;
    private ImageButton three;
    private ImageButton four;
    private ImageButton five;
    private ImageButton six;
    private ImageButton seven;
    private ImageButton eight;
    private ImageButton nine;
    private ImageButton dot;
    private ImageButton slash;

    private NumberGenerator numberGenerator;
    private TextToSpeech textToSpeech;
    private MyTextToSpeech myTextToSpeech;

    private long delay = 4000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Configure random answer generator
        numberGenerator = new NumberGenerator();
        numberGenerator.integer = true;

        // Configure utterance
        final Runnable utterRunnable = new Runnable() {
            @Override
            public void run() {
                // Check previous answer
                if (correct) {
                    correct = false;
                } else {
                    updateSolution();
                    if (!first) {
                        totalAnswered++;
                        updateScore();
                        tempAnswer = "";
                        answerView.setText(tempAnswer);
                    } else {
                        first = false;
                    }
                }

                // Generate solution each iteration
                solution = Integer.toString(numberGenerator.generateInt(1000, 0));
                myTextToSpeech.speakOut(solution);
            }
        };

        // Configure text to speech
        myTextToSpeech = MainActivity.myTextToSpeech;

        // Configure iteration/loop and interval between iterations
        UtteranceProgressListener listener = new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onDone(String s) {
                // delay is the interval between interations
                handler.postDelayed(utterRunnable, delay);
            }

            @Override
            public void onError(String s) {

            }
        };
        myTextToSpeech.setOnUtteranceProgressListener(listener);
        handler = new Handler();
        handler.postDelayed(utterRunnable, delay);




        scoreView = (TextView) findViewById(R.id.scoreView);
        answerView = (TextView) findViewById(R.id.answerView);
        solutionView = (TextView) findViewById(R.id.solutionView);
        solutionView.setText(String.format(solutionView.getText().toString(), ""));
        Util.setTextViewFont(getApplicationContext(), scoreView, Constants.FONT_PATH_JOSEFIN_REGULAR);
        Util.setTextViewFont(getApplicationContext(), answerView, Constants.FONT_PATH_JOSEFIN_REGULAR);
        Util.setTextViewFont(getApplicationContext(), solutionView, Constants.FONT_PATH_JOSEFIN_REGULAR);

        updateScore();

        // Configure input buttons
        zero = setInputButtonOnClick(R.id.zero, '0');
        one = setInputButtonOnClick(R.id.one, '1');
        two = setInputButtonOnClick(R.id.two, '2');
        three = setInputButtonOnClick(R.id.three, '3');
        four = setInputButtonOnClick(R.id.four, '4');
        five = setInputButtonOnClick(R.id.five, '5');
        six = setInputButtonOnClick(R.id.six, '6');
        seven = setInputButtonOnClick(R.id.seven, '7');
        eight = setInputButtonOnClick(R.id.eight, '8');
        nine = setInputButtonOnClick(R.id.nine, '9');
        dot = setInputButtonOnClick(R.id.dot, '.');
        slash = setInputButtonOnClick(R.id.slash, '/');

        // Configure auxiliary buttons
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private ImageButton setInputButtonOnClick(int buttonId, final char input) {
        ImageButton imageButton = (ImageButton) findViewById(buttonId);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final char inputChar = input;
                boolean canAdd = true;

                // Check for special cases
                // There can only be one dot and it cannot be the first char
                if (inputChar == '.' && (tempAnswer.contains("."))) {
                    canAdd = false;
                }
                if (inputChar == '.' && tempAnswer.equals("")) {
                    canAdd = false;
                }

                // There can only be one slash and it cannot be the first char
                if (inputChar == '/' && (tempAnswer.contains("/"))) {
                    canAdd = false;
                }
                if (inputChar == '/' && tempAnswer.equals("")) {
                    canAdd = false;
                }


                if (canAdd) {
                    tempAnswer += inputChar;
                    answerView.setText(tempAnswer);
                }

                // Check if answer matches solution
                if (tempAnswer.equals(solution)) {
                    correct = true;
                    updateSolution();

                    // update score
                    totalAnswered++;
                    totalCorrect++;
                    updateScore();

                    // Clear tempAnswer
                    tempAnswer = "";
                    answerView.setText(tempAnswer);
                }
            }
        });
        return imageButton;
    }

    // If back button pressed, terminate
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (handler == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        myTextToSpeech.stop();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
        finish();
    }

    public void updateScore() {
        String temp = getResources().getString(R.string.score);
        String scoreText = String.format(temp, totalCorrect, totalAnswered);
        scoreView.setText(scoreText);
    }

    public void updateSolution() {
        String temp = getResources().getString(R.string.solution);
        String solutionText = String.format(temp, solution);
        solutionView.setText(solutionText);
    }
}
