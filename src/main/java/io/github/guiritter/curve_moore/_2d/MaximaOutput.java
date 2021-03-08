package io.github.guiritter.curve_moore._2d;

import java.util.List;

public class MaximaOutput {

    /**
     * Converts a number between 0 and 7 to a color.
     * @paZram value
     * @return
     */
    private static String intToColor(int value) {
        switch (value) {
            case 0: return "\"#ff8000\"";
            case 1: return "\"#00ff00\"";
            case 2: return "\"#0080ff\"";
            case 3: return "\"#ff00ff\"";
            default: return "black";
        }
    }

    /**
     * Generates code to visualize the curve in GNU Maxima.
     * @param pointCurrentList curve to plot in 3D lines
     * @return {@code draw} compatible plot
     */
    public static final String maximaOutput(List<Point> pointCurrentList) {
        StringBuilder builder = new StringBuilder("load(draw)$\ndraw2d(\ncolor=black,\nline_type=dots,\npoint_type=0,\npoints_joined=true,\nproportional_axes=xyz,\npoints(");
        StringBuilder xBuilder = new StringBuilder("[");
        StringBuilder yBuilder = new StringBuilder("[");
        pointCurrentList.forEach(point -> {
            xBuilder.append(point.x).append(",");
            yBuilder.append(point.y).append(",");
        });
        xBuilder.setLength(xBuilder.length() - 1);
        yBuilder.setLength(yBuilder.length() - 1);
        xBuilder.append("]");
        yBuilder.append("]");
        builder.append(xBuilder).append(",").append(yBuilder).append("),\nline_type=solid,\n");
        int subOrderLenght = pointCurrentList.size() / 4;
        for (int i = 0; i < 4; i++) {
            builder.append("color=").append(intToColor(i)).append(",\npoints(");
            xBuilder.setLength(0);
            yBuilder.setLength(0);
            xBuilder.append("[");
            yBuilder.append("[");
            pointCurrentList.subList(subOrderLenght * i, subOrderLenght * (i + 1)).forEach(point -> {
                xBuilder.append(point.x).append(",");
                yBuilder.append(point.y).append(",");
            });
            xBuilder.setLength(xBuilder.length() - 1);
            yBuilder.setLength(yBuilder.length() - 1);
            xBuilder.append("]");
            yBuilder.append("]");
            builder.append(xBuilder).append(",").append(yBuilder).append("),\n");
        }
        builder.setLength(builder.length() - 2);
        builder.append("\n)$");
        return builder.toString();
    }
}