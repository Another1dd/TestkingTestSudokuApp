package com.another1dd.testkingtestsudokuapp.timer;


import android.os.Handler;
import android.os.SystemClock;

import com.another1dd.testkingtestsudokuapp.gameEngine.TinyDB;

public abstract class Timer extends Handler {

    public Timer(long ival) {
        mTickInterval = ival;
        mIsRunning = false;
        mAccumTime = 0;
    }


    public void start() {
        if (mIsRunning)
            return;

        mIsRunning = true;

        long now = SystemClock.uptimeMillis();

        mLastLogTime = now;

        mNextTime = now;
        postAtTime(runner, mNextTime);
    }


    public void stop() {
        if (mIsRunning) {
            mIsRunning = false;
            long now = SystemClock.uptimeMillis();
            mAccumTime += now - mLastLogTime;
            mLastLogTime = now;
        }
    }


    public final void reset() {
        stop();
        mTickCount = 0;
        mAccumTime = 0;
    }


    public final boolean isRunning() {
        return mIsRunning;
    }


    public final long getTime() {
        return mAccumTime;
    }


    protected abstract boolean step(int count, long time);


    private void done() {
    }


    private final Runnable runner = new Runnable() {

        public final void run() {
            if (mIsRunning) {
                long now = SystemClock.uptimeMillis();


                mAccumTime += now - mLastLogTime;
                mLastLogTime = now;

                if (!step(mTickCount++, mAccumTime)) {

                    mNextTime += mTickInterval;
                    if (mNextTime <= now)
                        mNextTime += mTickInterval;
                    postAtTime(runner, mNextTime);
                } else {
                    mIsRunning = false;
                    done();
                }
            }
        }

    };


    public void saveState(TinyDB tinyDB) {
        if (mIsRunning) {
            long now = SystemClock.uptimeMillis();
            mAccumTime += now - mLastLogTime;
            mLastLogTime = now;
        }

        tinyDB.putLong("tickInterval", mTickInterval);
        tinyDB.putBoolean("isRunning", mIsRunning);
        tinyDB.putInt("tickCount", mTickCount);
        tinyDB.putLong("accumTime", mAccumTime);
    }


    public boolean restoreState(TinyDB map) {
        return restoreState(map, true);
    }


    private boolean restoreState(TinyDB map, boolean run) {
        mTickInterval = map.getLong("tickInterval", 0);
        mIsRunning = map.getBoolean("isRunning");
        mTickCount = map.getInt("tickCount");
        mAccumTime = map.getLong("accumTime", 0);
        mLastLogTime = SystemClock.uptimeMillis();

        if (mIsRunning) {
            if (run)
                start();
            else
                mIsRunning = false;
        }

        return true;
    }


    private long mTickInterval = 0;
    private boolean mIsRunning = false;
    private int mTickCount;
    private long mNextTime;
    private long mAccumTime;
    private long mLastLogTime;

}
