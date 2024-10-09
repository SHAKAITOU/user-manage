package cn.caam.gs.common.enums;

public enum AcceptFileType {

    IMAGE(EnumIndex.startIndex(0), ".jpg, .jpeg, .png"),
    PDF(EnumIndex.getNext(), ".pdf"),
	EXCEL(EnumIndex.getNext(), ".xlsx");
    
    /** type. */
    private int id;
    /** type. */
    private String accept;

    
    private AcceptFileType(int id, String accept) {
        this.id = id;
        this.accept = accept;

    }
    
    public int getId() {
        return id;
    }
    
    public String getAccept() {
        return accept;
    }

    
    public AcceptFileType idOf(int id) {
        for(AcceptFileType type : AcceptFileType.values()) {
            if(id == type.getId()) {
                return type;
            }
        }
        
        return null;
    }
    
    public AcceptFileType nameOf(String name) {
        for(AcceptFileType type : AcceptFileType.values()) {
            if(name.equals(type.toString())) {
                return type;
            }
        }
        
        return null;
    }
}
