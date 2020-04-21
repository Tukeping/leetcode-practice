package com.tukeping.cs.puzzle;

/*
 * Solution of Josephus Problem using Bitwise Operation
 * Shifting the most-significant set bit of n to the
 * least significant bit will return the safe position.
 *
 * ====================== EXPLANATION ======================
 *
 * n (41) the number of people standing in the circle
 * n = 101001
 *
 * Left Shift n
 * (n<<1) = 1010010
 *
 * Flip the last bit
 * ((n<<1) | 1) = 1010011
 *
 * To Flip the First Set Bit
 * n = 101001
 *
 * n*2
 * Multiply n by 2
 * 41 x 2 = 82
 * 82 = 1010010
 *
 * Integer.highestOneBit(n*2)
 * Get highest set bit position
 * 64 = 1000000
 *
 * ~Integer.highestOneBit(n*2)
 * Taking compliment
 * ~64 = 0111111
 *
 * ~Integer.highestOneBit(n*2) & ((n<<1) | 1)
 * Bitwise And to copy bits exists in both operands.
 *      1010011
 *  &   0111111
 *  -----------
 *      0010011
 *  -----------
 *
 * 0010011 = 19
 */

/**
 * @author tukeping
 * @date 2020/4/17
 **/
public class JosephusProblem {

    public static int getSafePosition(int n) {
        // int n1 = ~Integer.highestOneBit(n*2);
        // int n2 = ((n<<1) | 1);
        // return n1 & n2;

        return ~Integer.highestOneBit(n * 2) & ((n << 1) | 1);
    }
}