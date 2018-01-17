package com.bowling;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final Frame frame;
    private List<Integer> pins;
    private String givenBonus;

    Game(){
        pins = new ArrayList<>();
        frame = new Frame();
    }

    String getBonus() {
        return givenBonus;
    }

    int remainingFrames(){
        return frame.getRemainingFrames();
    }

    void roll(int numberOfPinsDown) throws InvalidPinsArgumentException{
        if(pinsIsInvalid(numberOfPinsDown)){
            throw new InvalidPinsArgumentException();
        }

        if (checkIfGameIsOver()) return;
        pins.add(numberOfPinsDown);

        frame.setRollsPerFrame(frame.getRollsPerFrame() + 1);

        giveBonuses(numberOfPinsDown);
    }

    private boolean checkIfGameIsOver() {
        if(gameIsOver()){
            score();
            return true;
        }
        return false;
    }

    private void adjustGameSettings() {
        frame.reduceNumberOfFrames();
        frame.resetRollsPerFrame();
    }

    private boolean allPinsDownOnTwoRolls() {
        return score() == 10;
    }

    private boolean allPinsDownOnFirstRoll(int numberOfPinsDown) {
        return frame.getRollsPerFrame() == 1 && numberOfPinsDown == 10;
    }

    private boolean pinsIsInvalid(int numberOfPinsDown) {
        return numberOfPinsDown < 0 || numberOfPinsDown > 10;
    }

    private boolean gameIsOver() {
        return frame.getRemainingFrames() == 0;
    }

    int score() {
        return pins.stream().mapToInt(Integer::intValue).sum();
    }

    private void giveBonuses(int numberOfPinsDown) {
        if (allPinsDownOnFirstRoll(numberOfPinsDown)) {
            givenBonus =  Bonus.STRIKE.toString();
        }

        if (frame.isFrame()) {
            if (allPinsDownOnTwoRolls()) {
                givenBonus = Bonus.SPARE.toString();
            }
            adjustGameSettings();
        }
    }

    static class InvalidPinsArgumentException extends RuntimeException{
    }

    enum Bonus{
        STRIKE, SPARE
    }
}
