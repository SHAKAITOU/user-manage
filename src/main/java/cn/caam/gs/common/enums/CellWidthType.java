package cn.caam.gs.common.enums;

public enum CellWidthType {
    ONE(new int[]{12}),
    TWO_1_11(new int[]{1, 11}),
    TWO_2_10(new int[]{2, 10}),
    TWO_3_9(new int[]{3, 9}),
    TWO_4_8(new int[]{4, 8}),
    TWO_5_7(new int[]{5, 7}),
    TWO_6_6(new int[]{6, 6}),
    TWO_7_5(new int[]{7, 5}),
    TWO_8_4(new int[]{8, 4}),
    TWO_9_3(new int[]{9, 3}),
    TWO_10_2(new int[]{10, 2}),
    TWO_11_1(new int[]{11, 1}),
    THREE_1_2_9(new int[]{1, 2, 9}),
    THREE_1_3_8(new int[]{1, 3, 8}),
    THREE_1_4_7(new int[]{1, 4, 7}),
    THREE_1_5_6(new int[]{1, 5, 6}),
    THREE_1_6_5(new int[]{1, 6, 5}),
    THREE_1_7_4(new int[]{1, 7, 4}),
    THREE_1_8_3(new int[]{1, 8, 3}),
    THREE_1_9_2(new int[]{1, 9, 2}),
    THREE_1_10_1(new int[]{1, 10, 1}),
    THREE_2_4_6(new int[]{2, 4, 6}),
    THREE_3_4_5(new int[]{3, 4, 5}),
    THREE_4_6_2(new int[]{4, 6, 2}),
    THREE_4_4_4(new int[]{4, 4, 4}),
    THREE_4_1_7(new int[]{4, 1, 7}),
    THREE_5_3_4(new int[]{5, 3, 4}),
    THREE_5_4_3(new int[]{5, 4, 3}),
    THREE_5_6_1(new int[]{5, 6, 1}),
    THREE_6_3_3(new int[]{6, 3, 3}),
    THREE_6_1_5(new int[]{6, 1, 5}),
    THREE_6_2_4(new int[]{6, 2, 4}),
    THREE_6_4_2(new int[]{6, 4, 2}),
    THREE_7_1_4(new int[]{7, 1, 4}),
    THREE_7_2_3(new int[]{7, 2, 3}),
    THREE_7_3_2(new int[]{7, 3, 2}),
    THREE_8_2_2(new int[]{8, 2, 2}),
    THREE_3_3_6(new int[]{3, 3, 6}),
    THREE_3_6_3(new int[]{3, 6, 3}),
    FOUR_1_4_1_6(new int[]{1, 4, 1, 6}),
    FOUR_4_2_4_2(new int[]{4, 2, 4, 2}),
    FOUR_4_1_3_4(new int[]{4, 1, 3, 4}),
    FOUR_4_1_2_5(new int[]{4, 1, 2, 5}),
    FOUR_3_3_3_3(new int[]{3, 3, 3, 3}),
    FOUR_4_3_1_4(new int[]{4, 3, 1, 4}),
    FOUR_4_4_2_2(new int[]{4, 4, 2, 2}),
    FOUR_6_3_1_2(new int[]{6, 3, 1, 2}),
    FIVE_2_1_1_3_5(new int[]{2, 1, 1, 3, 5}),
    FIVE_5_1_1_2_3(new int[]{5, 1, 1, 2, 3}),
    SIX_4_1_1_4_1_1(new int[]{4, 1, 1, 4, 1, 1}),
    SIX_221115(new int[]{2, 2, 1, 1, 1, 5}),
    SEVEN_2212222(new int[]{2, 2, 1, 2, 2, 2, 2}),
    ;
    
    private int[] value;
    private CellWidthType(int[] value) {
        this.value = value;
    }
    
    public int[] getValue() {
        return this.value;
    }
}
