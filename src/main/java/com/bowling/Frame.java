package com.bowling;

class Frame {
    private static final int NEW_GAME_FRAMES = 10;
    private int remainingFrames;
    private int rollsPerFrame;

    Frame() {
        this.remainingFrames = NEW_GAME_FRAMES;
    }

    int getRemainingFrames() {
        return remainingFrames;
    }

    int getRollsPerFrame() {
        return rollsPerFrame;
    }

    void setRollsPerFrame(int rollsPerFrame) {
        this.rollsPerFrame = rollsPerFrame;
    }

    int remainingFrames() {
        return this.remainingFrames;
    }

    void resetRollsPerFrame() {
        rollsPerFrame = 0;
    }

    void reduceNumberOfFrames() {
        remainingFrames--;
    }

    boolean isFrame() {
        return rollsPerFrame == 2;
    }

}