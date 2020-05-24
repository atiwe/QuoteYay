package se.mau.ai0405.p3;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple fragment class for start screen.
 *
 * @author Sara Dalvig
 * @author Hampus Hansson
 */
public class StartFragment extends Fragment {
    private Controller controller;

    private Button buttonNewGame;
    private Button buttonSetName;
    private Button buttonHighscore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        initializeComponents(view);
        registerListeners();

        return view;
    }

    /**
     * Initializes the components of the fragment.
     *
     * @param view The view in which the components should be initialized.
     */
    private void initializeComponents(View view){
        buttonNewGame = view.findViewById(R.id.btnNewGame);
        buttonSetName = view.findViewById(R.id.btnNewPlayer);
        buttonHighscore = view.findViewById(R.id.btnHighScore);

        buttonNewGame.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.frog), PorterDuff.Mode.SRC));
        buttonSetName.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.frog), PorterDuff.Mode.SRC));
        buttonHighscore.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(
                getResources().getColor(R.color.frog), PorterDuff.Mode.SRC));
    }

    /**
     * Registers a listener for all the buttons in the fragment.
     */
    private void registerListeners() {
        ButtonListener listener = new ButtonListener();

        buttonNewGame.setOnClickListener(listener);
        buttonSetName.setOnClickListener(listener);
        buttonHighscore.setOnClickListener(listener);
    }

    /**
     * Sets the controller of the fragment.
     *
     * @param controller The controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Private listener class for the buttons in the fragment.
     */
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == buttonNewGame) {
                controller.buttonNewGameClicked();
            } else if (view == buttonHighscore) {
                controller.buttonHighscoreClicked();
            } else if (view == buttonSetName) {
                controller.buttonSetNameClicked();
            }
        }
    }

}
