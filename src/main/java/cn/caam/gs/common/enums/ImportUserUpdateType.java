package cn.caam.gs.common.enums;

public enum ImportUserUpdateType {

    INSERT_ONLY(EnumIndex.startIndex(0), "1"),
    INSERT_UPDATE(EnumIndex.getNext(), "2");
    
    /** type. */
    private int id;
    /** type. */
    private String type;

    
    private ImportUserUpdateType(int id, String type) {
        this.id = id;
        this.type = type;

    }
    
    public int getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }

    
    public ImportUserUpdateType idOf(int id) {
        for(ImportUserUpdateType type : ImportUserUpdateType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public ImportUserUpdateType typeOf(String type) {
        for(ImportUserUpdateType updateType : ImportUserUpdateType.values()) {
            if(type.equals(type.toString())) {
                return updateType;
            }
        }
        
        return null;
    }
}
