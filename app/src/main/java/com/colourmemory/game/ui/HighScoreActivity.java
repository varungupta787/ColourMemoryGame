package com.colourmemory.game.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.colourmemory.R;
import com.colourmemory.game.database.PlayerScoreDatabase;
import com.colourmemory.game.database.entity.PlayerScoreEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class HighScoreActivity extends AppCompatActivity {

    private LinearLayout playerDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        playerDetailsContainer = findViewById(R.id.player_details_container);
        setData();
    }

    private void setData() {
        List<PlayerScoreEntity> scoreList = getAllPlayersScoreData();
        if (scoreList == null || scoreList.isEmpty()) {
            showNoDataAvailableMsg();
        } else {
            Map<Long, Long> scoreRankMap = getPlayersRankList(scoreList);
            addPlayerScoreData(scoreList, scoreRankMap);
        }
    }

    private void showNoDataAvailableMsg() {
        Toast.makeText(this, getString(R.string.no_record_msg), Toast.LENGTH_SHORT).show();
    }

    private List<PlayerScoreEntity> getAllPlayersScoreData() {
        return PlayerScoreDatabase.getAppDatabase().scoreDAO().getAllPlayersScore();
    }

    private void addPlayerScoreData(List<PlayerScoreEntity> scoreRecordList,
                                    Map<Long, Long> scoreRankMap) {

        for (PlayerScoreEntity scoreRecord : scoreRecordList) {

            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.score_item_layout, null);

            playerDetailsContainer.addView(view);

            TextView playerName = view.findViewById(R.id.player_name_tv);
            playerName.setText("   " +scoreRecord.getName().toUpperCase());

            TextView playerScore = view.findViewById(R.id.player_score_tv);
            playerScore.setText(String.valueOf(scoreRecord.getScore()));

            TextView playerRank = view.findViewById(R.id.player_rank_tv);
            Long rank = scoreRankMap.get(scoreRecord.getScore());
            playerRank.setText((rank != null) ? String.valueOf(rank) : " - ");
        }
    }

    private Map<Long, Long> getPlayersRankList(List<PlayerScoreEntity> scoreList) {

        HashMap<Long, Long> rankMap = new HashMap<>();

        //remove duplicate score
        Set<Long> uniqueScoreList = new TreeSet<>();
        for (PlayerScoreEntity scoreRecord : scoreList) {
            uniqueScoreList.add(scoreRecord.getScore());
        }

        ArrayList<Long> list = new ArrayList<>();
        list.addAll(uniqueScoreList);
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            rankMap.put(list.get(i), i + 1L);
        }
        return rankMap;
    }
}
