package com.bowling;

public class Bonus {
    private final Game game;
    String bonus;

    public Bonus(Game game) {
        this.game = game;
    }

    public String getBonus() {
        return bonus;
    }

    void giveBonuses(int numberOfPinsDown) {
        if (game.allPinsDownOnFirstRoll(numberOfPinsDown)) {
            bonus = "strike";
        }

        if (game.isFrame()) {
            if (game.allPinsDownOnTwoRolls()) {
                bonus = "spare";
            }
            game.adjustGameSettings();
        }
    }
}