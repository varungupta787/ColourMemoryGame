package com.colourmemory.game.database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.colourmemory.game.ColourMemoryApplication;
import com.colourmemory.game.database.dao.PlayerScoreDAO;
import com.colourmemory.game.database.entity.PlayerScoreEntity;

import static com.colourmemory.game.database.DatabaseConstants.DATABASE_NAME;

@Database(entities = {PlayerScoreEntity.class},
        version = 1, exportSchema = false)
public abstract class PlayerScoreDatabase extends RoomDatabase {
    private static PlayerScoreDatabase INSTANCE;

    public abstract PlayerScoreDAO scoreDAO();


    public static PlayerScoreDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(ColourMemoryApplication.getContext(), PlayerScoreDatabase.class, DATABASE_NAME)
                            // allow queries on the main thread.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
