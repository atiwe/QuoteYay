package se.mau.ai0405.p3;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Controller class to the {@link GameFragment} class.
 *
 * @author Anton Tiwe
 * @author Hampus Hansson
 */
public class GameController implements View.OnTouchListener {
    private GameFragment fragmentGame;
    private ImageFragment fragmentImage;
    private boolean questionAnswered;
    private boolean rightAnswer;
    private boolean clickedBack;
    private Thread testAPI;
    private Quote tvQuote;
    private MainActivity activity;
    private int scoreCounter;
    private HighScoreDataBase database;
    private Controller controller;

    /**
     * Constructor that initializes components and sets a GameFragment as current view.
     *
     * @param controller Controller
     * @param database Database that saves the score
     * @param activity The window of the application
     */
    public GameController(Controller controller, HighScoreDataBase database,
                          MainActivity activity) {
        this.activity = activity;
        this.controller = controller;
        this.database = database;

        fragmentGame = new GameFragment();
        fragmentGame.setController(this);

        activity.setFragment(fragmentGame, true);

        fragmentImage = new ImageFragment();
        fragmentImage.setController(this);

        testAPI = new TestAPI(this);
        testAPI.start();
    }

    /**
     * Checks if answer is correct. If correct, it increases the score with 1 and changes fragment
     * to ImageFragment. If incorrect, it puts the score in database. Disables all buttons.
     *
     * @param button The button that the user clicked
     */
    public void checkAnswer(Button button) {
        Button rightButton = null;
        questionAnswered = true;
        Button[] allbuttons = fragmentGame.getButtons();
        for (Button allbutton : allbuttons) {
            if (allbutton.getText().toString().equals(tvQuote.getCorrectAuthor())) {
                rightButton = allbutton;
                break;
            }
        }
        if (button.getText().toString().equals(tvQuote.getCorrectAuthor())) {
            fragmentGame.setButtonRightAnswer(button);
            fragmentGame.setTvQuote("Luck!");
            rightAnswer = true;
            scoreCounter++;
            activity.setFragment(fragmentImage,true);
            new WikipediaImageGenerator(this).startThread(tvQuote.getCorrectAuthor());
        } else {
            fragmentGame.setButtonWrongAnswer(button);
            fragmentGame.setButtonRightAnswer(rightButton);
            fragmentGame.setTvQuote("You suck!");
            rightAnswer = false;
            Score score = new Score(controller.getName(), scoreCounter);
            database.scoreDao().insertScore(score);
        }
        fragmentGame.disableButtons();
    }

    /**
     * Gets called when time is out. Disables buttons and sets the {@link TextView} containing
     * the quote to "Time Out".
     */
    private void timeOut() {
        fragmentGame.disableButtons();
        questionAnswered = true;
        rightAnswer = false;
        fragmentGame.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentGame.setTvQuote("Time Out!");
            }
        });
    }

    /**
     * Starts a thread that makes the progress bar go down by one in intervals.
     */
    private void startTimer() {
        Thread thread = new ProgressTimer();
        thread.start();
    }

    /**
     * Starts a new TestAPI thread to get a new question and enables the buttons.
     */
    private void newQuestion() {
        testAPI = new TestAPI(this);
        testAPI.start();
        fragmentGame.enableButtons();
    }

    /**
     * Sets new text to the quote {@link TextView} and calls a method to start the timer.
     *
     * @param quote Quote object that has a String to be put in the {@link TextView}
     *              containing the quote
     */
    public void newQuote(Quote quote) {
        this.tvQuote = quote;
        fragmentGame.setQuote(quote);
        startTimer();
    }

    /**
     * Sets a new Drawable in the {@link ImageFragment} object.
     *
     * @param drawable Image to put in the ImageFragment object
     */
    public void setImageInFragment(final Drawable drawable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragmentImage.setImage(drawable);
            }
        });
    }

    /**
     * Runs a runnable object on the main thread.
     *
     * @param runnable Runnable object to run
     */
    private static void runOnUiThread(Runnable runnable) {
        final Handler UIHandler = new Handler(Looper.getMainLooper());
        UIHandler.post(runnable);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (questionAnswered) {
            questionAnswered = false;

            if (rightAnswer) {
                activity.onBackPressed();
                newQuestion();
            } else if (!rightAnswer && !clickedBack) {
                activity.onBackPressed();
                clickedBack = true;
            }
        }
        return true;
    }

    /**
     * Inner class that reduces the progress bar with one every 200 milliseconds.
     * Calls the timeOut method if the progress bar gets value 0.
     */
    private class ProgressTimer extends Thread {
        int progress = 100;

        public void run() {
            while (progress >= 0 && !questionAnswered) {
                fragmentGame.setProgressBar(progress);
                progress--;
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!questionAnswered) {
                timeOut();
            }
        }
    }
}
