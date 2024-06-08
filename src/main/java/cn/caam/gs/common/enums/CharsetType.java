package cn.caam.gs.common.enums;

public enum CharsetType {

    UTF_8(EnumIndex.startIndex(0), "UTF-8"),
    MS932(EnumIndex.getNext(), "MS932");
    
    /** type. */
    private int id;
    /** type. */
    private String name;

    
    private CharsetType(int id, String name) {
        this.id = id;
        this.name = name;

    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    
    public CharsetType idOf(int id) {
        for(CharsetType type : CharsetType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public CharsetType nameOf(String name) {
        for(CharsetType type : CharsetType.values()) {
            if(name.equals(type.toString())) {
                return type;
            }
        }
        
        return null;
    }
}
