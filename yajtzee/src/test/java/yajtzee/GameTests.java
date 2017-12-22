
package yajtzee;

import org.junit.Assert;
import org.junit.Test;

public class GameTests {

    @Test
    public void test1(){
        Game game = GameFactory.create(new int[]{1, 1, 1, 4, 5});
        Assert.assertEquals(3, game.addSingle(1));
        Assert.assertEquals(12, game.addAll());
        Assert.assertEquals(true, game.isThreeOfKind());
        Assert.assertEquals(false, game.isFourOfKind());
        Assert.assertEquals(false, game.isFullHouse());
        Assert.assertEquals(false, game.isSmallStraight());
        Assert.assertEquals(false, game.isLargeStraight());
        Assert.assertEquals(false, game.isYajtzee());
    }

    @Test
    public void test2(){
        Game game = GameFactory.create(new int[]{1, 2, 3, 4, 5});
        Assert.assertEquals(1, game.addSingle(1));
        Assert.assertEquals(15, game.addAll());
        Assert.assertEquals(false, game.isThreeOfKind());
        Assert.assertEquals(false, game.isFourOfKind());
        Assert.assertEquals(false, game.isFullHouse());
        Assert.assertEquals(true, game.isSmallStraight());
        Assert.assertEquals(true, game.isLargeStraight());
        Assert.assertEquals(false, game.isYajtzee());
    }

    @Test
    public void test3(){
        Game game = GameFactory.create(new int[]{5, 3, 2, 4, 1});
        Assert.assertEquals(1, game.addSingle(1));
        Assert.assertEquals(15, game.addAll());
        Assert.assertEquals(false, game.isThreeOfKind());
        Assert.assertEquals(false, game.isFourOfKind());
        Assert.assertEquals(false, game.isFullHouse());
        Assert.assertEquals(true, game.isSmallStraight());
        Assert.assertEquals(true, game.isLargeStraight());
        Assert.assertEquals(false, game.isYajtzee());
    }

    @Test
    public void test4(){
        Game game = GameFactory.create(new int[]{4, 3, 2, 4, 1});
        Assert.assertEquals(1, game.addSingle(1));
        Assert.assertEquals(14, game.addAll());
        Assert.assertEquals(false, game.isThreeOfKind());
        Assert.assertEquals(false, game.isFourOfKind());
        Assert.assertEquals(false, game.isFullHouse());
        Assert.assertEquals(true, game.isSmallStraight());
        Assert.assertEquals(false, game.isLargeStraight());
        Assert.assertEquals(false, game.isYajtzee());
    }

    @Test
    public void test5(){
        Game game = GameFactory.create(new int[]{5, 5, 5, 5, 5});
        Assert.assertEquals(25, game.addSingle(5));
        Assert.assertEquals(25, game.addAll());
        Assert.assertEquals(true, game.isThreeOfKind());
        Assert.assertEquals(true, game.isFourOfKind());
        Assert.assertEquals(false, game.isFullHouse());
        Assert.assertEquals(false, game.isSmallStraight());
        Assert.assertEquals(false, game.isLargeStraight());
        Assert.assertEquals(true, game.isYajtzee());
    }


}