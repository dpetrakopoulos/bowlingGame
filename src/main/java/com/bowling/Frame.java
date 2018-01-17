package com.bowling;

class Frame {
    private static final int NEW_GAME_FRAMES = 10;
    private int remainingFrames;
    private int rollsPerFrame;

    public static Frame Make(){
        return new Frame();
    }

    private Frame() {
        remainingFrames = NEW_GAME_FRAMES;
    }

    int getRollsPerFrame() {
        return rollsPerFrame;
    }

    void setRollsPerFrame(int rollsPerFrame) {
        this.rollsPerFrame = rollsPerFrame;
    }

    int getRemainingFrames() {
        return remainingFrames;
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