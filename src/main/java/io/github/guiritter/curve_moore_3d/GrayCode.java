package io.github.guiritter.curve_moore_3d;

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
    public static final int grayCode3ToInt(Boolean[] value) {
        Boolean[] testValue;
        for (int i = 0; i < 8; i++) {
            testValue = grayCode.get(i);
            if (Objects.equals(value[0], testValue[0])
             && Objects.equals(value[1], testValue[1])
             && Objects.equals(value[2], testValue[2])) {
                return i;
            }
        }
        return -1;
    }

    static {
        grayCode = Arrays.asList(
         new Boolean[]{false, false, false},
         new Boolean[]{true, false, false},
         new Boolean[]{true, true, false},
         new Boolean[]{false, true, false},
         new Boolean[]{false, true, true},
         new Boolean[]{true, true, true},
         new Boolean[]{true, false, true},
         new Boolean[]{false, false, true}
        );
    }
}
