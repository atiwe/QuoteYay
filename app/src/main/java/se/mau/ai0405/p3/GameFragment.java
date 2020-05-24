package se.mau.ai0405.p3;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

/**
 * Fragment class for the game UI.
 *
 * @author Anton Tiwe
 * @author Hampus Hansson
 */
public class GameFragment extends Fragment {
    private TextView tvQuote;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private ProgressBar pbTimer;
    private GameController controller;
    private LinkedList<String> quotes;
    private Random random;
    private Button[] buttons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        initializeComponents(view);
        view.setOnTouchListener(controller);

        return view;
    }

    /**
     * Sets text, color and visibility on the buttons and sets the progress bar to 100.
     */
    public void newQuestion() {
        btn1.setText(quotes.remove(random.nextInt(4)));
        btn2.setText(quotes.remove(random.nextInt(3)));
        btn3.setText(quotes.remove(random.nextInt(2)));
        btn4.setText(quotes.removeFirst());
        btn1.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.darkPurple), PorterDuff.Mode.SRC));
        btn2.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.darkPurple), PorterDuff.Mode.SRC));
        btn3.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.darkPurple), PorterDuff.Mode.SRC));
        btn4.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.darkPurple), PorterDuff.Mode.SRC));
        pbTimer.setProgress(100);
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
    }

    /**
     * Sets controller for the fragment.
     *
     * @param controller The controller object
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the UI components and sets listeners on the buttons.
     *
     * @param view The view in which the components are initialized
     */
    private void initializeComponents(View view) {
        tvQuote = (TextView) view.findViewById(R.id.tvQuote);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        pbTimer = (ProgressBar) view.findViewById(R.id.pbTimer);
        random = new Random();
        btn1.setOnClickListener(new ButtonListener());
        btn2.setOnClickListener(new ButtonListener());
        btn3.setOnClickListener(new ButtonListener());
        btn4.setOnClickListener(new ButtonListener());
        buttons = new Button[]{btn1, btn2, btn3, btn4};
    }

    /**
     * Returns the buttons in the UI.
     *
     * @return Array of buttons from the UI
     */
    public Button[] getButtons() {
        return buttons;
    }

    /**
     * Enables the buttons.
     */
    public void enableButtons() {
        for (Button button : buttons) {
            button.setClickable(true);
        }
    }

    /**
     * Disables the buttons.
     */
    public void disableButtons() {
        for (Button button : buttons) {
            button.setClickable(false);
        }
    }

    /**
     * Runs a Runnable object on the main thread.
     *
     * @param runnable Runnable object to run
     */
    public static void runOnUiThread(Runnable runnable) {
        final Handler UIHandler = new Handler(Looper.getMainLooper());
        UIHandler.post(runnable);
    }

    /**
     * Changes the text on the {@link TextView} containing the quote.
     *
     * @param quote The text to be set
     */
    public void setTvQuote(String quote) {
        this.tvQuote.setText(quote);
    }

    /**
     * Changes a button's background color to red.
     *
     * @param button The button to change color on
     */
    public void setButtonWrongAnswer(Button button) {
        button.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                Color.RED, PorterDuff.Mode.SRC));
    }

    /**
     * Changes a button's background color to green.
     *
     * @param button The button to change color on.
     */
    public void setButtonRightAnswer(Button button) {
        button.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                Color.GREEN, PorterDuff.Mode.SRC));
    }

    /**
     * Sets new text on the quote {@link TextView} and the buttons.
     * Calls a method to get a new question.
     *
     * @param quote The quote to be set
     */
    public void setQuote(final Quote quote) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quotes = new LinkedList<>();
                quotes.add(quote.getCorrectAuthor());
                quotes.add(quote.getFakeAuthor1());
                quotes.add(quote.getFakeAuthor2());
                quotes.add(quote.getFakeAuthor3());
                setTvQuote(quote.getQuote1());
                newQuestion();
            }
        });
    }

    /**
     * Listener class for the buttons. Sends the clicked button to the GameController
     * to check the answer.
     */
    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn1:
                    controller.checkAnswer(btn1);
                    break;

                case R.id.btn2:
                    controller.checkAnswer(btn2);
                    break;

                case R.id.btn3:
                    controller.checkAnswer(btn3);
                    break;

                case R.id.btn4:
                    controller.checkAnswer(btn4);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Sets the progressbar to a specific value.
     *
     * @param progress The value to change the progress bar to
     */
    public void setProgressBar(int progress) {
        pbTimer.setProgress(progress);
    }
}
