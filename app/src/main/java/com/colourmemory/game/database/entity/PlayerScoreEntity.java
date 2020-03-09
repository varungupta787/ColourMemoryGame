package com.colourmemory.game.database.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.colourmemory.game.database.DatabaseConstants.ID;
import static com.colourmemory.game.database.DatabaseConstants.PLAYER_NAME;
import static com.colourmemory.game.database.DatabaseConstants.PLAYER_SCORE;
import static com.colourmemory.game.database.DatabaseConstants.TABLE_NAME;
import static com.colourmemory.game.database.DatabaseConstants.TIMESTAMP;

@Entity(tableName = TABLE_NAME)
public class PlayerScoreEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;

    @ColumnInfo(name = PLAYER_NAME)
    private String name;

    @ColumnInfo(name = PLAYER_SCORE)
    private long score;

    @ColumnInfo(name = TIMESTAMP)
    private long time;

    public PlayerScoreEntity(String name, long score) {
        this.name = name;
        this.score = score;
        this.time = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
