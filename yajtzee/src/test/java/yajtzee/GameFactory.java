package yajtzee;

public class GameFactory {

    public static Game create(int [] values){
        Die[] dice = new Die[values.length];
        for(int i = 0; i < dice.length; i++){
            dice[i] = new Die(values[i]);
        }
        return new Game(dice);
    }

}
