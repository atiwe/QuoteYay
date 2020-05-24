package se.mau.ai0405.p3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 *
 *
 * @author Anton Tiwe
 */
@Dao
public interface ScoreDao {
    /**
     *
     *
     * @param score = Score object to put in database
     */
    @Insert
    void insertScore(Score score);

    /**
     *
     *
     * @return a List with all Score objects in database ordered by the highest score
     */
    @Query("Select * from score_table ORDER BY score_points desc LIMIT 10")
    List<Score> getScores();

    /**
     *
     *
     * @return amount of scores in database
     */
    @Query("Select count(score_points) from score_table")
    int getSize();

    /**
     *
     *
     * @return the lowest score in database
     */
    @Query("Select min(score_points) from score_table")
    int getLowestScore();
}
