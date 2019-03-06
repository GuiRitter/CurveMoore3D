package io.github.guiritter.curve_moore_3d;

import static io.github.guiritter.curve_moore_3d.GrayCode.grayCode3ToInt;

/**
 * Point in 3D space and {@link java.lang.Long long} precision.
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
     * Index of the third dimension in the 3D space.
     */
    public final int Z = 2;

    public final long z;

    /**
     * Mirrors a point on the given axes
     * @param axis one of {@link io.github.guiritter.Point#X X}, {@link io.github.guiritter.Point#Y Y} or {@link io.github.guiritter.Point#Z Z}
     * @return a new instance mirrored on the given axis
     */
    private Point mirror(int axis) {
        switch (axis) {
            case X: return new Point(-x, y, z);
            case Y: return new Point(x, -y, z);
            case Z: return new Point(x, y, -z);
            default: return this;
        }
    }

    /**
     * Rotates a point around the given axes.
     * @param axis one of {@link io.github.guiritter.Point#X X}, {@link io.github.guiritter.Point#Y Y} or {@link io.github.guiritter.Point#Z Z}
     * @param direction if {@link Boolean#TRUE true}, counterclockwise; otherwise, clockwise
     * @return a new instance rotated around the given axis
     */
    private Point rotate(int axis, boolean direction) {
        switch (axis) {
            case X:
                if (direction) {
                    return new Point(x, -z, y);
                } else {
                    return new Point(x, z, -y);
                }
            case Y:
                if (direction) {
                    return new Point(z, y, -x);
                } else {
                    return new Point(-z, y, x);
                }
            case Z:
                if (direction) {
                    return new Point(-y, x, z);
                } else {
                    return new Point(y, -x, z);
                }
            default: return this;
        }
    }

    /**
     * Transforms a point to generate the current order curve, according to the gray code value.
     * @param grayCodeValue indicates which transformation needs to be performed
     * @return a new instance transformed according to its section
     */
    public final Point transformCurrent(Boolean[] grayCodeValue) {
        switch (grayCode3ToInt(grayCodeValue)) {
            case 0: return rotate(Y, true);
            case 1: return rotate(X, false);
            case 2: return rotate(X, false);
            case 3: return mirror(X).rotate(X, false).mirror(Y);
            case 4: return mirror(X).rotate(X, true).mirror(Y);
            case 5: return rotate(X, true);
            case 6: return rotate(X, true);
            case 7: return rotate(Y, false);
            default: return this;
        }
    }

    /**
     * Transforms a point to generate the next order curve, according to the gray code value.
     * @param grayCodeValue indicates which transformation needs to be performed
     * @return a new instance transformed according to its section
     */
    public final Point transformNext(Boolean[] grayCodeValue) {
        switch (grayCode3ToInt(grayCodeValue)) {
            case 0: return rotate(Y, false).mirror(X);
            case 1: return rotate(X, true).mirror(Y);
            case 2: return this;
            case 3: return rotate(X, false).mirror(X);
            case 4: return rotate(X, true).mirror(X);
            case 5: return this;
            case 6: return rotate(X, false).mirror(Y);
            case 7: return rotate(Y, true).mirror(X);
            default: return this;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", x, y, z);
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
         y + ((direction[Y] ? 1 : (-1)) * amount),
         z + ((direction[Z] ? 1 : (-1)) * amount));
    }

    public Point(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
