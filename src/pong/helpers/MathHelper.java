package pong.helpers;

/**
 * Created by patrick.christensen on 4/12/2017.
 */
public class MathHelper {
    public static int limitValue(int value, int lowerBound, int upperBound){
        value = Math.max(lowerBound, value);
        value = Math.min(upperBound, value);
        return value;
    }
}
