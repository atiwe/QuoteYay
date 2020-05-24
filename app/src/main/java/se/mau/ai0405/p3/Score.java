package se.mau.ai0405.p3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Class that contains attributes of a score - id, name and points.
 *
 * @author Anton Tiwe
 */
@Entity(tableName = "score_table")
public class Score {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "score_user_name")
    private String name;

    @ColumnInfo(name = "score_points")
    private int points;

    /**
     * Constructor.
     *
     * @param name Name of the user
     * @param points Points of user
     */
    public Score(String name, int points){
        this.name = name;
        this.points = points;
    }

    /**
     * Returns the name of the user.
     *
     * @return Name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of points of the score.
     *
     * @return Amount of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the ID of the score.
     *
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets an ID for the score.
     *
     * @param id The ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets name for the score.
     *
     * @param name Name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the amount of points for the score.
     *
     * @param points Amount of points
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
