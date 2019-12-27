package com.tukeping.tools;

/**
 * @author tukeping
 * @date 2019/12/26
 **/
public final class Prints {

    private final static int SEC = 1000;
    private final static int MIN = 60 * SEC;

    private Prints() {
    }

    public static String prettyPrint(long millis) {
        if (millis >= MIN) {
            long min = millis / (MIN);
            long sec = (millis - (min * MIN)) / SEC;
            long ms = millis - (min * MIN) - sec * SEC;
            return "cost: " + min + "m " + sec + "s " + ms + "ms";
        } else if (millis >= SEC) {
            long sec = millis / SEC;
            long ms = millis % SEC;
            return "cost: " + sec + "s " + ms + "ms";
        } else {
            return "cost: " + millis + "ms";
        }
    }

    public static String timeLimit(long cost, long millisLimit) {
        return "TimeLimit(" + millisLimit + "ms): " + Boolean.valueOf(cost > millisLimit).toString();
    }
}
