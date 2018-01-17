package com.bowling;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final Bonus bonus = new Bonus(this);
    private final Frame frame = new Frame();
    private List<Integer> pins;

    Game(){
        this.pins = new ArrayList<>();
    }

    String getBonus() {
        return bonus.getBonus();
    }

    int remainingFrames(){
        return frame.remainingFrames();
    }

    void roll(int numberOfPinsDown) throws InvalidPinsArgumentException{
        if(pinsIsInvalid(numberOfPinsDown)){
            throw new InvalidPinsArgumentException();
        }

        if (checkIfGameIsOver()) return;
        pins.add(numberOfPinsDown);

        frame.setRollsPerFrame(frame.getRollsPerFrame() + 1);

        bonus.giveBonuses(numberOfPinsDown);
    }

    private boolean checkIfGameIsOver() {
        if(gameIsOver()){
            score();
            return true;
        }
        return false;
    }

    void adjustGameSettings() {
        frame.reduceNumberOfFrames();
        frame.resetRollsPerFrame();
    }

    boolean isFrame() {
        return frame.getRollsPerFrame() == 2;
    }

    boolean allPinsDownOnTwoRolls() {
        return score() == 10;
    }

    boolean allPinsDownOnFirstRoll(int numberOfPinsDown) {
        return frame.getRollsPerFrame() == 1 && numberOfPinsDown == 10;
    }

    private boolean pinsIsInvalid(int numberOfPinsDown) {
        return numberOfPinsDown < 0 || numberOfPinsDown > 10;
    }

    private boolean gameIsOver() {
        return frame.getRemainingFrames() == 0;
    }

    int score() {
        return this.pins.stream().mapToInt(Integer::intValue).sum();
    }

    static class InvalidPinsArgumentException extends RuntimeException{
    }
}
