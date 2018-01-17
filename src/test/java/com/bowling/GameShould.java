package com.bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/*Bowling Rules

  The game consists of 10 frames. In each frame the player has two rolls to knock down 10 pins.
  The score for the frame is the total number of pins knocked down, plus bonuses for strikes and spares.

  A spare is when the player knocks down all 10 pins in two rolls. The bonus for that frame is the number of pins knocked down by the next roll.

  A strike is when the player knocks down all 10 pins on his first roll. The frame is then completed with a single roll. The bonus for that frame is the value of the next two rolls.

  In the tenth frame a player who rolls a spare or strike is allowed to roll the extra balls to complete the frame. However no more than three balls can be rolled in tenth frame.

  Requirements

  Write a class Game that has two methods

  void roll(int) is called each time the player rolls a ball. The argument is the number of pins knocked down.
  int score() returns the total score for that game.*/

public class GameShould {
    private Game game;
    private Frame frame;

    @Before
    public void setUp(){
        game = new Game();
        frame = new Frame();
    }

    @Test
    public void haveTenFramesOnStart(){
        assertThat(game.remainingFrames()).isEqualTo(10);
    }

    @Test
    public void haveTwoRollsPerFrame(){
        frame.setRollsPerFrame(2);
        assertThat(frame.isFrame()).isEqualTo(true);
    }

    @Test
    public void haveScoreEqualToZeroOnStart(){
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    public void reduceFrameSizeWhenPlayerPlays(){
        game.roll(0);
        game.roll(3);

        assertThat(game.remainingFrames()).isEqualTo(9);
    }

    @Test
    public void haveScoreZeroWhenNoPinsAreKnockedDown() {
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    public void haveScoreEqualToPinsKnockedDownOnFirstRoll(){
        game.roll(4);

        assertThat(game.score()).isEqualTo(4);
    }

    @Test
    public void calculateScoreForFirstFrame() {
        game.roll(1);
        game.roll(7);

        assertThat(game.score()).isEqualTo(8);
    }

    @Test
    public void finishOnTwentiethRoll() {
        for (int i=0; i< 21; i++) {
            game.roll(1);
        }
        assertThat(game.checkIfGameIsOver()).isEqualTo(true);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test(expected=Game.InvalidPinsArgumentException.class)
    public void throwExceptionWhenPinsDownIsNegative(){
        game.roll(-1);
    }

    @Test(expected=Game.InvalidPinsArgumentException.class)
    public void throwExceptionWhenPinsDownIsGreaterThanTen(){
        game.roll(11);
    }

    @Test
    public void giveASpareBonusWhenAllTenPinsDownInTwoRolls(){
        game.roll(5);
        game.roll(5);
        assertThat(game.getBonus()).isEqualToIgnoringCase("spare");
    }

    @Test
    public void giveAStrikeBonusWhenAllTenPinsDownOnFirstRoll(){
        game.roll(10);
        assertThat(game.getBonus()).isEqualToIgnoringCase("strike");
    }

}
