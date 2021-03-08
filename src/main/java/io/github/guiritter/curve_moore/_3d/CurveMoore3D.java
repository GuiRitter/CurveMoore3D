package io.github.guiritter.curve_moore._3d;

import static io.github.guiritter.curve_moore._3d.GrayCode.grayCode;
import static io.github.guiritter.curve_moore._3d.MaximaOutput.maximaOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p><a href="https://en.wikipedia.org/wiki/Moore_curve">Space-filling Moore curve</a> in three dimensions.
 * <p>An order {@code N} curve is composed by a collection of order {@code N-1} curves, recursively, until the order {@code 0} curve is represented by a single point at {@code (0, 0, 0)}.
 * <p>When the next order N is generated, points are translated by {@code ±2^(N-1)}. Consequently, the bounding box will be {@code (2^N+1)-2} and every point will be at an odd integer.
 * <p>Order generation is only limited by external constraints.
 * <p>Traversal generates an ordered list of 3D points.
 * @author Guilherme Alan Ritter
 */
public class CurveMoore3D {

    public final long order;

    public final List<Point> pointCurrentList;

    public final List<Point> pointNextList;

    /**
     * Builds a curve with order {@code N+1} from a curve with order {@code N}.
     * @return an object that represents a curve with an order one unit higher that this object
     */
    public final CurveMoore3D nextOrder() {
        long translation = 1l << order; // 2 ^ order
        return new CurveMoore3D(order + 1,
         grayCode.stream().flatMap(value -> pointNextList.stream().map(point -> point.transformCurrent(value).translate(value, translation))).collect(Collectors.toList()),
         grayCode.stream().flatMap(value -> pointNextList.stream().map(point -> point.transformNext(value).translate(value, translation))).collect(Collectors.toList()));
    }

    /**
     * Builds an order zero curve, which is a curve with the lowest possible order, represented by a single point at the origin.
     * @return an object that represents a zero order curve
     */
    public static final CurveMoore3D orderZero() {
        return new CurveMoore3D(0l, Arrays.asList(new Point(0,0,0)));
    }

    private CurveMoore3D(Long order, List<Point> pointCurrentList) {
        this.order = order;
        this.pointCurrentList = pointCurrentList;
        this.pointNextList = pointCurrentList;
    }

    private CurveMoore3D(Long order, List<Point> pointCurrentList, List<Point> pointNextList) {
        this.order = order;
        this.pointCurrentList = pointCurrentList;
        this.pointNextList = pointNextList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CurveMoore3D curve = CurveMoore3D.orderZero();
        curve = curve.nextOrder().nextOrder().nextOrder();
//        curve.pointList.forEach(point -> System.out.println(point));
        System.out.println(maximaOutput(curve.pointCurrentList));
    }
}
