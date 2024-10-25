package cn.caam.gs.common.enums;

public enum SortOrderType {

	ASC(EnumIndex.startIndex(0), "ASC"),
	DESC(EnumIndex.getNext(), "DESC");
    
    /** type. */
    private int id;
    /** type. */
    private String key;

    
    private SortOrderType(int id, String key) {
        this.id = id;
        this.key = key;

    }
    
    public int getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }

    
    public SortOrderType idOf(int id) {
        for(SortOrderType type : SortOrderType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public SortOrderType keyOf(String key) {
        for(SortOrderType type : SortOrderType.values()) {
            if(type.getKey().equals(key)) {
                return type;
            }
        }
        
        return null;
    }
}
