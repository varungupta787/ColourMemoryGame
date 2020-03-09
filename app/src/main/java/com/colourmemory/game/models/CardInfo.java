package com.colourmemory.game.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CardInfo implements Parcelable {

    private CardState cardState;
    private int number = -1;
    private int drawable;

    public CardInfo(int num, int drawableId) {
        number = num;
        cardState = CardState.DOWN;
        drawable = drawableId;
    }

    protected CardInfo(Parcel in) {
        number = in.readInt();
    }

    public static final Creator<CardInfo> CREATOR = new Creator<CardInfo>() {
        @Override
        public CardInfo createFromParcel(Parcel in) {
            return new CardInfo(in);
        }

        @Override
        public CardInfo[] newArray(int size) {
            return new CardInfo[size];
        }
    };

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
