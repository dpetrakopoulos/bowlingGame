package com.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpetrakopoulos on 16/1/2018.
 */
public class Game {

    private static final int NEW_GAME_FRAMES = 10;
    private final Bonus bonus = new Bonus(this);
    private int remainingFrames;
    private int rollsPerFrame;
    private List<Integer> pins;

    public Game(){
        this.remainingFrames = NEW_GAME_FRAMES;
        this.pins = new ArrayList<>();
    }

    public String getBonus() {
        return bonus.getBonus();
    }

    public int remainingFrames(){
        return this.remainingFrames;
    }

    public void roll(int numberOfPinsDown) throws InvalidPinsArgumentException{
        if(pinsIsInvalid(numberOfPinsDown)){
            throw new InvalidPinsArgumentException();
        }

        if (checkIfGameIsOver()) return;
        pins.add(numberOfPinsDown);

        rollsPerFrame++;

        bonus.giveBonuses(numberOfPinsDown);
    }

    private boolean checkIfGameIsOver() {
        if(gameIsOver()){
            score();
            return true;
        }
        return false;
    }

    protected void adjustGameSettings() {
        reduceNumberOfFrames();
        resetRollsPerFrame();
    }

    protected boolean isFrame() {
        return rollsPerFrame == 2;
    }

    protected boolean allPinsDownOnTwoRolls() {
        return score() == 10;
    }

    private void resetRollsPerFrame() {
        rollsPerFrame = 0;
    }

    private void reduceNumberOfFrames() {
        remainingFrames--;
    }

    protected boolean allPinsDownOnFirstRoll(int numberOfPinsDown) {
        return rollsPerFrame == 1 && numberOfPinsDown == 10;
    }

    private boolean pinsIsInvalid(int numberOfPinsDown) {
        return numberOfPinsDown < 0 || numberOfPinsDown > 10;
    }

    private boolean gameIsOver() {
        return remainingFrames == 0;
    }

    public int score() {
        return this.pins.stream().mapToInt(Integer::intValue).sum();
    }

    public static class InvalidPinsArgumentException extends RuntimeException{
    }
}
