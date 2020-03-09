package com.colourmemory.game.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.colourmemory.R;
import com.colourmemory.game.database.PlayerScoreDatabase;
import com.colourmemory.game.database.entity.PlayerScoreEntity;
import com.colourmemory.game.models.CardInfo;
import com.colourmemory.game.models.CardState;
import com.colourmemory.game.models.Player;

import java.util.HashMap;

public class ColourMemoryGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView card4;
    private ImageView card5;
    private ImageView card6;
    private ImageView card7;
    private ImageView card8;

    private ImageView card9;
    private ImageView card10;
    private ImageView card11;
    private ImageView card12;
    private ImageView card13;
    private ImageView card14;
    private ImageView card15;
    private ImageView card16;

    private TextView scoreText;
    private TextView highScoreTextView;
    private Button playGameAgainButton;

    private CardInfo previouslySelectedCardInfo = null;
    private View previouslySelectedCardView = null;

    private HashMap<View, CardInfo> cardInfoMapping;

    private Player firstPlayer, secondPlayer;
    private boolean playerSwitchingBreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_memory_game);
        setViews();
        setListener();
        startGame();
    }

    private void startGame() {
        initGameBoardData();
        initializePlayerData();
        resetScoreBoard();
        disableResetGameButton();
    }

    private void initializePlayerData() {
        firstPlayer = new Player();
        secondPlayer = new Player();
    }

    private void setViews() {
        card1 = findViewById(R.id.card_1);
        card2 = findViewById(R.id.card_2);
        card3 = findViewById(R.id.card_3);
        card4 = findViewById(R.id.card_4);
        card5 = findViewById(R.id.card_5);
        card6 = findViewById(R.id.card_6);
        card7 = findViewById(R.id.card_7);
        card8 = findViewById(R.id.card_8);
        card9 = findViewById(R.id.card_9);
        card10 = findViewById(R.id.card_10);
        card11 = findViewById(R.id.card_11);
        card12 = findViewById(R.id.card_12);
        card13 = findViewById(R.id.card_13);
        card14 = findViewById(R.id.card_14);
        card15 = findViewById(R.id.card_15);
        card16 = findViewById(R.id.card_16);

        scoreText = findViewById(R.id.score_text);
        highScoreTextView = findViewById(R.id.high_score_tv);
        playGameAgainButton = findViewById(R.id.play_game_again_button);
    }

    private void setListener() {

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);
        highScoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        ColourMemoryGameActivity.this,
                        HighScoreActivity.class);
                startActivity(intent);
            }
        });

        playGameAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


    }

    private void initGameBoardData() {
        setCardsWithFaceDownImage();
        setCardInfoWithCards();
    }

    private void setCardInfoWithCards() {
        cardInfoMapping = new HashMap<>();
        cardInfoMapping.put(card1, new CardInfo(1, R.drawable.colour1));
        cardInfoMapping.put(card2, new CardInfo(2, R.drawable.colour2));
        cardInfoMapping.put(card3, new CardInfo(3, R.drawable.colour3));
        cardInfoMapping.put(card4, new CardInfo(4, R.drawable.colour4));
        cardInfoMapping.put(card5, new CardInfo(5, R.drawable.colour5));
        cardInfoMapping.put(card6, new CardInfo(6, R.drawable.colour6));
        cardInfoMapping.put(card7, new CardInfo(7, R.drawable.colour7));
        cardInfoMapping.put(card8, new CardInfo(8, R.drawable.colour8));

        cardInfoMapping.put(card9, new CardInfo(1, R.drawable.colour1));
        cardInfoMapping.put(card10, new CardInfo(2, R.drawable.colour2));
        cardInfoMapping.put(card11, new CardInfo(3, R.drawable.colour3));
        cardInfoMapping.put(card12, new CardInfo(4, R.drawable.colour4));
        cardInfoMapping.put(card13, new CardInfo(5, R.drawable.colour5));
        cardInfoMapping.put(card14, new CardInfo(6, R.drawable.colour6));
        cardInfoMapping.put(card15, new CardInfo(7, R.drawable.colour7));
        cardInfoMapping.put(card16, new CardInfo(8, R.drawable.colour8));
    }

    private void setCardsWithFaceDownImage() {
        card1.setImageResource(R.drawable.card_bg);
        card2.setImageResource(R.drawable.card_bg);
        card3.setImageResource(R.drawable.card_bg);
        card4.setImageResource(R.drawable.card_bg);
        card5.setImageResource(R.drawable.card_bg);
        card6.setImageResource(R.drawable.card_bg);
        card7.setImageResource(R.drawable.card_bg);
        card8.setImageResource(R.drawable.card_bg);

        card9.setImageResource(R.drawable.card_bg);
        card10.setImageResource(R.drawable.card_bg);
        card11.setImageResource(R.drawable.card_bg);
        card12.setImageResource(R.drawable.card_bg);
        card13.setImageResource(R.drawable.card_bg);
        card14.setImageResource(R.drawable.card_bg);
        card15.setImageResource(R.drawable.card_bg);
        card16.setImageResource(R.drawable.card_bg);
    }

    private void showSelectActiveCardMsg() {
        Toast.makeText(ColourMemoryGameActivity.this,
                getString(R.string.active_card_msg), Toast.LENGTH_SHORT).show();
    }

    private void showGameCompleteMsg() {
        Toast.makeText(ColourMemoryGameActivity.this,
                getString(R.string.gane_complete_msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        if (playerSwitchingBreak) {
            showPlayerSwitchingBreakMsg();
            return;
        }

        if (!isValidCard(view)) {
            showSelectActiveCardMsg();
            return;
        }

        CardInfo cardInfo = cardInfoMapping.get(view);

        checkForValidCardCombination(view, cardInfo);
    }

    private void showPlayerSwitchingBreakMsg() {
        Toast.makeText(ColourMemoryGameActivity.this,
                getString(R.string.wait_msg), Toast.LENGTH_SHORT).show();
    }

    private boolean isValidCard(View view) {
        if (cardInfoMapping == null || cardInfoMapping.get(view) == null)
            return false;

        CardInfo cardInfo = cardInfoMapping.get(view);
        if (cardInfo != null && cardInfo.getCardState() == CardState.DOWN)
            return true;

        return false;
    }

    private void setCardFaceToUp(View card, CardInfo cardInfo) {
        cardInfo.setCardState(CardState.UP);
        ((ImageView) card).setImageResource(cardInfo.getDrawable());
    }

    private void setCardFaceToDown(View card, CardInfo cardInfo) {
        cardInfo.setCardState(CardState.DOWN);
        ((ImageView) card).setImageResource(R.drawable.card_bg);
    }

    private void setCardToInactive(View card, CardInfo cardInfo) {
        ((ImageView) card).setImageResource(R.drawable.used_card);
        cardInfo.setCardState(CardState.INACTIVE);
    }

    private void addPlayerPoints() {
        if (firstPlayer.hasPlayed()) {
            secondPlayer.setScore(secondPlayer.getScore() + 2);
        } else {
            firstPlayer.setScore(firstPlayer.getScore() + 2);
        }
    }

    private void decreasePlayerPoints() {
        if (firstPlayer.hasPlayed()) {
            secondPlayer.setScore(secondPlayer.getScore() - 1);
        } else {
            firstPlayer.setScore(firstPlayer.getScore() - 1);

        }
    }

    private void togglePlayersPlayingStatus() {
        if (firstPlayer.hasPlayed()) {
            secondPlayer.setPlayingStatus(true);
            firstPlayer.setPlayingStatus(false);
        } else {
            secondPlayer.setPlayingStatus(false);
            firstPlayer.setPlayingStatus(true);
        }
    }

    private void updateScoreBoard() {
        String score = "Score \n " +
                "1st : " + firstPlayer.getScore() + "\n" +
                "2nd : " + secondPlayer.getScore();
        scoreText.setText(score);
    }

    private void resetScoreBoard() {
        String score = "Score \n " +
                "1st : 0" + "\n" +
                "2nd : 0";
        scoreText.setText(score);
    }

    private synchronized void checkForValidCardCombination(View currentCardView,
                                                           CardInfo currentCardInfo) {
        if (previouslySelectedCardInfo == null) {
            previouslySelectedCardInfo = currentCardInfo;
            previouslySelectedCardView = currentCardView;
            setCardFaceToUp(currentCardView, currentCardInfo);
        } else {
            setCardFaceToUp(currentCardView, currentCardInfo);
            if (previouslySelectedCardInfo.getNumber() == currentCardInfo.getNumber()) {
                addPlayerPoints();
                updateScoreBoard();
                takeBreakForPlayerSwitching(true, currentCardView, currentCardInfo);
            } else {
                decreasePlayerPoints();
                updateScoreBoard();
                takeBreakForPlayerSwitching(false, currentCardView, currentCardInfo);
            }
        }
    }

    private void checkForGameFinish() {
        if (isGameFinished()) {
            showGameCompleteMsg();
            playerFirstNameDialog();
        }
    }

    private void resetUsedCardInformation() {
        previouslySelectedCardInfo = null;
        previouslySelectedCardView = null;
    }

    private void takeBreakForPlayerSwitching(final boolean isSelectedCardsSame,
                                             final View currentCardView,
                                             final CardInfo currentCardInfo) {
        playerSwitchingBreak = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isSelectedCardsSame) {
                    processGameWithSameCardSelected(currentCardView, currentCardInfo);
                } else {
                    processGameWithDifferentCardSelected(currentCardView, currentCardInfo);
                }
                playerSwitchingBreak = false;
            }
        }, 1000);
    }

    private void processGameWithSameCardSelected(final View currentCardView,
                                                 final CardInfo currentCardInfo) {
        setCardToInactive(previouslySelectedCardView, previouslySelectedCardInfo);
        setCardToInactive(currentCardView, currentCardInfo);
        checkForGameFinish();
        resetUsedCardInformation();
        togglePlayersPlayingStatus();
    }

    private void processGameWithDifferentCardSelected(final View currentCardView,
                                                      final CardInfo currentCardInfo) {
        setCardFaceToDown(previouslySelectedCardView, previouslySelectedCardInfo);
        setCardFaceToDown(currentCardView, currentCardInfo);
        resetUsedCardInformation();
        togglePlayersPlayingStatus();
    }

    private boolean isGameFinished() {
        for (CardInfo cardInfo : cardInfoMapping.values()) {
            if (cardInfo.getCardState() != CardState.INACTIVE)
                return false;
        }
        return true;
    }

    private void playerFirstNameDialog() {
        final Dialog dialog = new Dialog(ColourMemoryGameActivity.this);
        dialog.setContentView(R.layout.player_name_dialog);
        dialog.setCancelable(false);


        RelativeLayout dialogContainer = dialog.findViewById(R.id.dialog_container);
        dialogContainer.setBackgroundColor(Color.parseColor("#32A3B9"));
        TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.firat_player_name));

        final EditText playerName = dialog.findViewById(R.id.player_name);

        Button doneButton = dialog.findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playerName.getText().toString().trim().toLowerCase();
                if (name == null || name.isEmpty()) {
                    showValidNameMsg();
                    return;
                }
                long value = PlayerScoreDatabase.getAppDatabase().scoreDAO().
                        checkPlayerNameUniqueness(name);
                if (value > 0) {
                    showDuplicateNameMsg();
                } else {
                    PlayerScoreEntity entity = new PlayerScoreEntity(name, firstPlayer.getScore());
                    PlayerScoreDatabase.getAppDatabase().scoreDAO().insertPlayerScore(entity);
                    dialog.dismiss();
                    playerSecondNameDialog();
                }
            }
        });
        dialog.show();
    }

    private void playerSecondNameDialog() {
        final Dialog dialog = new Dialog(ColourMemoryGameActivity.this);
        dialog.setContentView(R.layout.player_name_dialog);
        dialog.setCancelable(false);

        RelativeLayout dialogContainer = dialog.findViewById(R.id.dialog_container);
        dialogContainer.setBackgroundColor(Color.parseColor("#76C28A"));

        TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.second_player_name));

        final EditText playerName = dialog.findViewById(R.id.player_name);

        Button doneButton = dialog.findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playerName.getText().toString().trim().toLowerCase();
                if (name == null || name.isEmpty()) {
                    showValidNameMsg();
                    return;
                }
                long value = PlayerScoreDatabase.getAppDatabase().scoreDAO().
                        checkPlayerNameUniqueness(name);
                if (value > 0) {
                    showDuplicateNameMsg();
                } else {
                    PlayerScoreEntity entity = new PlayerScoreEntity(name, secondPlayer.getScore());
                    PlayerScoreDatabase.getAppDatabase().scoreDAO().insertPlayerScore(entity);
                    dialog.dismiss();
                    enableResetGameButton();
                }
            }
        });
        dialog.show();
    }

    private void resetGame() {
        startGame();
    }

    private void showValidNameMsg() {
        Toast.makeText(ColourMemoryGameActivity.this,
                getString(R.string.valid_name_msg), Toast.LENGTH_SHORT).show();
    }

    private void showDuplicateNameMsg() {
        Toast.makeText(ColourMemoryGameActivity.this,
                getString( R.string.duplicate_name_msg), Toast.LENGTH_SHORT).show();
    }

    private void enableResetGameButton() {
        playGameAgainButton.setVisibility(View.VISIBLE);
    }

    private void disableResetGameButton() {
        playGameAgainButton.setVisibility(View.GONE);
    }
}
