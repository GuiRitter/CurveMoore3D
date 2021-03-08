package io.github.guiritter.curve_moore._2d;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GrayCode {

    public static final List<Boolean[]> grayCode;

    /**
     * Converts a boolean array representing a length 3 gray code value to its integer representation.
     * @param value index zero is the least significant bit
     * @return value between 0 and 7 (inclusive)
     */
    public static final int grayCode2ToInt(Boolean[] value) {
        Boolean[] testValue;
        for (int i = 0; i < 4; i++) {
            testValue = grayCode.get(i);
            if (Objects.equals(value[0], testValue[0])
             && Objects.equals(value[1], testValue[1])) {
                return i;
            }
        }
        return -1;
    }

    static {
        grayCode = Arrays.asList(
         new Boolean[]{false, false},
         new Boolean[]{true, false},
         new Boolean[]{true, true},
         new Boolean[]{false, true}
        );
    }
}
