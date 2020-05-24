package se.mau.ai0405.p3;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Abstract class that extends RoomDatabase.
 *
 * @author Anton Tiwe
 */
@Database(entities = {Score.class}, version = 1, exportSchema = false)
public abstract class HighScoreDataBase extends RoomDatabase {
    public abstract ScoreDao scoreDao();
}
