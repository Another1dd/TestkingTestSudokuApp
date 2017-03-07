package com.another1dd.testkingtestsudokuapp.timer;

import java.util.Formatter;

public class TimeFormat {
    private static final int TIME_99_99 = 99 * 99 * 1000;

    private StringBuilder mTimeText = new StringBuilder();

    private Formatter mGameTimeFormatter = new Formatter(mTimeText);


    public String format(long time) {
        mTimeText.setLength(0);
        if (time > TIME_99_99) {
            mGameTimeFormatter.format("%d:%02d", time / 60000, time / 1000 % 60);
        } else {
            mGameTimeFormatter.format("%02d:%02d", time / 60000, time / 1000 % 60);
        }
        return mTimeText.toString();
    }
}
