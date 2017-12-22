/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yajtzee;

import java.util.Arrays;
import java.util.Stack;


public class Game {

    protected Die[] dies;
    protected int rolls = 0;//how many times score recorded,  max is 13 according to rules

    public Game(Die[] dice) {
        this.dies = dice;
    }

    protected Die[] getDies() {
        return dies;
    }

    public boolean isOver() {
        return rolls >= 13;
    }

    public void reset() {
        rolls = 0;
    }

    public int addSingle(int num) {
        int total = 0;
        for (Die die : dies) {
            if (die.getValue() == num) {
                total += num;
            }
        }
        return total;
    }

    public int addAll() {
        int total = 0;
        for (Die die : dies) {
            total += die.getValue();
        }
        return total;
    }

    public boolean isThreeOfKind() {
        return atLeastEqual(3);
    }

    public boolean isFourOfKind() {
        return atLeastEqual(4);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean isFullHouse() {
        if (isThreeOfKind()) {
            Stack[] stack = {new Stack(), new Stack(), new Stack(), new Stack(), new Stack(), new Stack()};
            for (int i = 0; i < dies.length; i++) {
                stack[dies[i].getValue() - 1].push(i);
            }
            for (Stack s : stack) {
                if (s.size() == 3) {
                    for (Stack t : stack) {
                        if (t.size() == 2) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isSmallStraight() {
        Arrays.sort(dies);
        int count = 1;
        int temp = dies[0].getValue();//sets to first value of array
        int value;
        for (int i = 1; i < dies.length; i++) {
            value = dies[i].getValue();
            if (value == temp + 1) {
                count++;
            }
            temp = value;
        }
        return count >= 4;
    }

    public boolean isLargeStraight() {
        Arrays.sort(dies);
        int count = 1;
        int temp = dies[0].getValue();//sets to first value of array
        int value;
        for (int i = 1; i < dies.length; i++) {
            value = dies[i].getValue();
            if (value == temp + 1) {
                count++;
            }
            temp = value;
        }

        return count >= 5;
    }

    public boolean isYajtzee() {
        return atLeastEqual(5);
    }

    private boolean atLeastEqual(int min) {
        int[] number = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < dies.length; i++) {
            if (++number[dies[i].getValue() - 1] >= min) {
                return true;
            }
        }
        return false;
    }
}
