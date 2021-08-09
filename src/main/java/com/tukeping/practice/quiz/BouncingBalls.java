package com.tukeping.practice.quiz;

import com.tukeping.algs4.stdlib.StdDraw;

/**
 * @author tukeping
 * @date 2021/8/3
 **/
public class BouncingBalls {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            balls[i] = new Ball();
        }
        while (true) {
            StdDraw.clear();
            for (int i = 0; i < N; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }

    private static class Ball {
        private double rx, ry;
        private double vx, vy;
        private double radius;

        public Ball() {

        }

        public void move(double dt) {
            if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) {
                vx = -vx;
            }
            if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) {
                vy = -vy;
            }
            rx = rx + vx * dt;
            ry = ry + vy * dt;
        }

        public void draw() {
            StdDraw.filledCircle(rx, ry, radius);
        }
    }

    public static void main2(String[] args) {
        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        // initial values
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity
        double radius = 0.05;              // radius

        // main animation loop
        while (true) {

            // bounce off wall according to law of elastic collision
            if (Math.abs(rx + vx) > 1.0 - radius) vx = -vx;
            if (Math.abs(ry + vy) > 1.0 - radius) vy = -vy;

            // update position
            rx = rx + vx;
            ry = ry + vy;

            // clear the background
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledSquare(0, 0, 1.0);

            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, radius);

            // display and pause for 20 ms
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
