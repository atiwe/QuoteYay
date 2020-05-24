package se.mau.ai0405.p3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass showing the highscores in a top 10 list.
 *
 * @author Hampus Hansson
 * @author Anton Tiwe
 */
public class HighscoreFragment extends Fragment {
    private final int HIGHSCORE_LIST_LENGTH = 10;

    private Controller controller;

    private TextView[] textPlayers;
    private TextView[] textScores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highscore, container, false);

        initializeComponents(view);

        return view;
    }

    /**
     * Initializes the components and puts all the needed {@link TextView} instances
     * from the XML file in an array. It also fills up the list with scores from the
     * database.
     *
     * @param view The view in which the components should be initialized
     */
    private void initializeComponents(View view) {
        textPlayers = new TextView[HIGHSCORE_LIST_LENGTH];
        textScores = new TextView[HIGHSCORE_LIST_LENGTH];

        textPlayers[0] = view.findViewById(R.id.textPlayerOne);
        textPlayers[1] = view.findViewById(R.id.textPlayerTwo);
        textPlayers[2] = view.findViewById(R.id.textPlayerThree);
        textPlayers[3] = view.findViewById(R.id.textPlayerFour);
        textPlayers[4] = view.findViewById(R.id.textPlayerFive);
        textPlayers[5] = view.findViewById(R.id.textPlayerSix);
        textPlayers[6] = view.findViewById(R.id.textPlayerSeven);
        textPlayers[7] = view.findViewById(R.id.textPlayerEight);
        textPlayers[8] = view.findViewById(R.id.textPlayerNine);
        textPlayers[9] = view.findViewById(R.id.textPlayerTen);
        textScores[0] = view.findViewById(R.id.textScoreOne);
        textScores[1] = view.findViewById(R.id.textScoreTwo);
        textScores[2] = view.findViewById(R.id.textScoreThree);
        textScores[3] = view.findViewById(R.id.textScoreFour);
        textScores[4] = view.findViewById(R.id.textScoreFive);
        textScores[5] = view.findViewById(R.id.textScoreSix);
        textScores[6] = view.findViewById(R.id.textScoreSeven);
        textScores[7] = view.findViewById(R.id.textScoreEight);
        textScores[8] = view.findViewById(R.id.textScoreNine);
        textScores[9] = view.findViewById(R.id.textScoreTen);

        setHighscores(controller.getHighscores());
    }

    /**
     * Sets the controller for this fragment.
     *
     * @param controller The controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * This method puts highscores into the {@link TextView} objects in the fragment.
     *
     * @param scores The scores to be put into the fragment
     */
    private void setHighscores(List<Score> scores) {
        if(scores!=null){
            for (int i = 0; i < scores.size(); i++) {
                textPlayers[i].setText(scores.get(i).getName());
                textScores[i].setText("" + scores.get(i).getPoints());
            }
        }
    }
}
