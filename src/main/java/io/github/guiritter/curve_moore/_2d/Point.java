package io.github.guiritter.curve_moore._2d;

import static io.github.guiritter.curve_moore._2d.GrayCode.grayCode2ToInt;

/**
 * Point in 2D space and {@link java.lang.Long long} precision.
 * @author GuiR
 */
public final class Point {

    /**
     * Index of the first dimension in the 3D space.
     */
    public final int X = 0;

    public final long x;

    /**
     * Index of the second dimension in the 3D space.
     */
    public final int Y = 1;

    public final long y;


    /**
     * Mirrors a point on the given axes
     * @param axis one of {@link io.github.guiritter.Point#X X} or {@link io.github.guiritter.Point#Y Y}.
     * @return a new instance mirrored on the given axis
     */
    private Point mirror(int axis) {
        switch (axis) {
            case X: return new Point(x, -y);
            case Y: return new Point(-x, y);
            default: return this;
        }
    }

    /**
     * Rotates a point around the given axes.
     * @param axis one of {@link io.github.guiritter.Point#X X} or {@link io.github.guiritter.Point#Y Y}.
     * @param direction if {@link Boolean#TRUE true}, counterclockwise; otherwise, clockwise
     * @return a new instance rotated around the given axis
     */
    private Point rotate(boolean direction) {
        if (direction) {
            return new Point(-y, x);
        } else {
            return new Point(y, -x);
        }
    }

    /**
     * Transforms a point to generate the current order curve, according to the gray code value.
     * @param grayCodeValue indicates which transformation needs to be performed
     * @return a new instance transformed according to its section
     */
    public final Point transformCurrent(Boolean[] grayCodeValue) {
        switch (grayCode2ToInt(grayCodeValue)) {
            case 0: return rotate(false);
            case 1: return rotate(false);
            case 2: return rotate(true);
            case 3: return rotate(true);
            default: return this;
        }
    }

    /**
     * Transforms a point to generate the next order curve, according to the gray code value.
     * @param grayCodeValue indicates which transformation needs to be performed
     * @return a new instance transformed according to its section
     */
    public final Point transformNext(Boolean[] grayCodeValue) {
        switch (grayCode2ToInt(grayCodeValue)) {
            case 0: return rotate(false).mirror(X);
            case 1: return this;
            case 2: return this;
            case 3: return rotate(true).mirror(X);
            default: return this;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }

    /**
     * Translates a point by a given amount in all axes, with the given direction per axis.
     * @param direction for every axis, if it's positive, it's a translation towards positive infinity; otherwise, it's a translation towards negative infinity
     * @param amount magnitude of translation
     * @return a new instance translated by the given amount towards the given direction in each axis
     */
    public final Point translate(Boolean[] direction, long amount) {
        return new Point(
         x + ((direction[X] ? 1 : (-1)) * amount),
         y + ((direction[Y] ? 1 : (-1)) * amount));
    }

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
