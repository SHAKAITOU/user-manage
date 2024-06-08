package cn.caam.gs.manage.dbmaintenance.table;

public enum MySqlType {

	CHARACTER_VARYING 		("character varying"),
	INTEGER	 				("integer"),
	NUMERIC					("numeric"),
	TEXT					("text"),
	BLOB                    ("blob"),
	;
	
    /** type. */
    private String type;

    private MySqlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public MySqlType[] list() {
    	return MySqlType.values();
    }
    
    public static MySqlType typeOf(String typeName) {
    	for(MySqlType type : MySqlType.values()) {
    		if(typeName.equals(type.getType())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
