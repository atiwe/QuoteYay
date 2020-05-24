package se.mau.ai0405.p3;

import android.arch.persistence.room.Room;
import android.widget.Toast;

import java.util.List;

/**
 * Controller class for the application. The class holds a {@link User} object
 * and connects the database for highscores, the fragments and the {@link MainActivity}.
 *
 * @author Hampus Hansson
 * @author Anton Tiwe
 */
public class Controller {
    private User user;
    private MainActivity activity;
    private HighscoreFragment fragmentHighscore;
    private StartFragment fragmentStart;
    private SetNameFragment fragmentSetName;
    private HighScoreDataBase database;

    /**
     * Constructor. Builds the highscore database, creates the fragments and sets
     * {@link StartFragment} as the start view.
     *
     * @param activity The activity in which the application is shown
     */
    public Controller(MainActivity activity) {
        this.activity = activity;
        database = Room.databaseBuilder(activity.getApplicationContext(), HighScoreDataBase.class,
                "databasename").allowMainThreadQueries().build();

        fragmentHighscore = new HighscoreFragment();
        fragmentStart = new StartFragment();
        fragmentSetName = new SetNameFragment();
        fragmentStart.setController(this);
        activity.setFragment(fragmentStart, false);
    }

    /**
     * This method is called when the "New Game" button in the {@link StartFragment}
     * is pressed. If the user has not set a username yet, a message will be shown telling
     * the user that no username was found. If a name has been set, an instance of
     * {@link GameController} will be started.
     */
    public void buttonNewGameClicked() {
        if (user == null) {
            Toast toast = Toast.makeText(activity, "No username found", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            new GameController(this, database, activity);
        }
    }

    /**
     * This method is called when the "Highscore" button in the {@link StartFragment}
     * is pressed. Changes the fragment in the main window and adds it to the backstack.
     */
    public void buttonHighscoreClicked() {
        fragmentHighscore.setController(this);
        activity.setFragment(fragmentHighscore, true);
    }

    /**
     * This method is called when the "Set Name" button in the {@link StartFragment}
     * is pressed. Changes the fragment in the main window and adds it to the backstack.
     */
    public void buttonSetNameClicked() {
        fragmentSetName.setController(this);
        activity.setFragment(fragmentSetName, true);
    }

    /**
     * This method is called when the "Save Name" button in the {@link SetNameFragment}
     * is pressed. If the field is empty or the name is longer than 25 characters,
     * a message will be shown. If no {@link User} object has been created, this method
     * will create one. If a {@link User} object already has been created, this method
     * will change the name in the created object. If the username is OK, the user will
     * see the {@link StartFragment} when "Save Name" is pressed.
     *
     * @param name The username
     */
    public void buttonSaveNameClicked(String name) {
        if (name.equals("")) {
            Toast toast = Toast.makeText(activity, "Field empty", Toast.LENGTH_SHORT);
            toast.show();
        } else if (name.length() > 25) {
            Toast toast = Toast.makeText(activity,
                    "Name can't contain more than 25 characters", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (user == null) {
                user = new User(name);
            } else {
                user.setName(name);
            }
            activity.onBackPressed();
        }
    }

    /**
     * Gets the name of the current user.
     *
     * @return Name of the user.
     */
    public String getName(){
        return user.getName();
    }

    /**
     * Gets the highscores in a {@link List<Score>} from the database.
     *
     * @return A list of the highscores
     */
    public List<Score> getHighscores() {
        return database.scoreDao().getScores();
    }
}
