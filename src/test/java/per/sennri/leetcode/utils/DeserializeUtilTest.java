package per.sennri.leetcode.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DeserializeUtilTest {

    @Test
    void getListNodeFromArray() {
        DeserializeUtil.getListNodeFromArray(new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    void getListNodeFromString() {
        DeserializeUtil.getListNodeFromString("[1,2,3,4,5,6]");
    }

    @Test
    void getIntArrayFromString() {
        DeserializeUtil.getIntArrayFromString("[1,2,3,4,5,6]");
    }

    @Test
    void getInt2DimArrayFromString() {
        DeserializeUtil.getInt2DimArrayFromString(
                "[[1,1,1,1,0,0,0,0]," +
                        "[1,1,1,1,0,0,0,0]," +
                        "[1,1,1,1,1,1,1,1]," +
                        "[1,1,1,1,1,1,1,1]," +
                        "[1,1,1,1,0,0,0,0]," +
                        "[1,1,1,1,0,0,0,0]," +
                        "[1,1,1,1,0,0,0,0]," +
                        "[1,1,1,1,0,0,0,0]]");
    }

    @Test
    void transformIntArrayToIntegerArray() {
        Integer[] test = DeserializeUtil.transformIntArrayToIntegerArray(new int[]{1,2,3});
        return;
    }

    @Test
    void transformIntArrayToList() {
        List<Integer> test = DeserializeUtil.transformIntArrayToList(new int[]{1,2,3,4,5});
        return;
    }

    @Test
    void transformListToIntegerArray() {
        Integer[] test = DeserializeUtil.transformListToIntegerArray(Arrays.asList(1,2,3,4,5));
        return;
    }

    @Test
    void transformListToIntArray() {
        int[] test = DeserializeUtil.transformListToIntArray(Arrays.asList(1,2,3,4,5));
        return;
    }
}