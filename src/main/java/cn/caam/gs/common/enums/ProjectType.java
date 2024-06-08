package cn.caam.gs.common.enums;

public enum ProjectType {

	TAKARAKUJI(EnumIndex.startIndex(0), "takarakuji");
    
	/** type. */
    private int id;
    /** type. */
    private String name;

    
    private ProjectType(int id, String name) {
    	this.id = id;
        this.name = name;

    }
    
    public int getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }

    
    public ProjectType idOf(int id) {
    	for(ProjectType type : ProjectType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public ProjectType nameOf(String name) {
    	for(ProjectType type : ProjectType.values()) {
    		if(name.equals(type.toString())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
