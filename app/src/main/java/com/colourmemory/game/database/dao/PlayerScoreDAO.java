package com.colourmemory.game.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.colourmemory.game.database.entity.PlayerScoreEntity;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
import static com.colourmemory.game.database.DatabaseConstants.PLAYER_NAME;
import static com.colourmemory.game.database.DatabaseConstants.TABLE_NAME;
import static com.colourmemory.game.database.DatabaseConstants.TIMESTAMP;

@Dao
public interface PlayerScoreDAO {


    /*  get all players score    */
    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY " + TIMESTAMP + " DESC")
    List<PlayerScoreEntity> getAllPlayersScore();

    /*  insert player score    */
    @Insert(onConflict = REPLACE)
    void insertPlayerScore(PlayerScoreEntity entity);

    /*   check player name is unique or not   */
    @Query("SELECT " + TIMESTAMP +" FROM " + TABLE_NAME +
            " WHERE " + PLAYER_NAME + " == :name")
    long checkPlayerNameUniqueness(String name);

}
