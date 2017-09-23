package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Андрей on 23.09.2017.
 */
public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for(int i=1; i<101; i++){
            this.move();
            this.print();
            Thread.sleep(200);
        }
    }
    public void print(){
        for(Horse horse : getHorses()){
            horse.print();
        }
        for(int i=0; i<10; i++){
            System.out.println();
        }
    }
    
    public void move(){
        for(Horse horse : getHorses()){
            horse.move();
        }
    }

    public Horse getWinner(){
        List<Horse> temp = getHorses();
        Horse res = temp.get(0);
        for(Horse h : temp){
            if(Double.compare(h.getDistance(), res.getDistance()) > 0){
                res = h;
            }
        }
        return res;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }



    public static void main(String[] args) throws InterruptedException {
        Horse horse1 = new Horse("horse1", 3, 0);
        Horse horse2 = new Horse("horse2", 3, 0);
        Horse horse3 = new Horse("horse3", 3, 0);
        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome.game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
