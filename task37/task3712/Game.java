package com.javarush.task.task37.task3712;

/**
 * Created by Igor on 06.06.2017.
 */
public abstract class Game {

    public abstract void prepareForTheGame();

    public abstract void playGame();
    public abstract void congratulateWinner();

    public void run(){
        this.prepareForTheGame();
        this.playGame();
        this.congratulateWinner();
    }
}
