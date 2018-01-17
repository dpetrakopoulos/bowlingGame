package com.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpetrakopoulos on 16/1/2018.
 */
public class Game {

    private static final int NEW_GAME_FRAMES = 10;
    private int remainingFrames;
    private int rollsPerFrame;
    private List<Integer> pins;
    private String bonus;

    public Game(){
        this.remainingFrames = NEW_GAME_FRAMES;
        this.pins = new ArrayList<>();
    }

    public String getBonus() {
        return bonus;
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

        giveBonuses(numberOfPinsDown);
    }

    private boolean checkIfGameIsOver() {
        if(gameIsOver()){
            score();
            return true;
        }
        return false;
    }

    private void giveBonuses(int numberOfPinsDown) {
        if(allPinsDownOnFirstRoll(numberOfPinsDown)){
            bonus = "strike";
        }

        if(isFrame()) {
            if(allPinsDownOnTwoRolls()){
                bonus = "spare";
            }
            adjustGameSettings();
        }
    }

    private void adjustGameSettings() {
        reduceNumberOfFrames();
        resetRollsPerFrame();
    }

    private boolean isFrame() {
        return rollsPerFrame == 2;
    }

    private boolean allPinsDownOnTwoRolls() {
        return score() == 10;
    }

    private void resetRollsPerFrame() {
        rollsPerFrame = 0;
    }

    private void reduceNumberOfFrames() {
        remainingFrames--;
    }

    private boolean allPinsDownOnFirstRoll(int numberOfPinsDown) {
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
