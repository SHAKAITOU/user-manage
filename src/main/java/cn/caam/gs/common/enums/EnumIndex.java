package cn.caam.gs.common.enums;

public class EnumIndex {
    private static int INDEX;
    
    public static int startIndex(int start) {
        INDEX = start;
        return INDEX;
    }
    
    public static int getNext() {
        INDEX++;
        return INDEX;
    }
}

