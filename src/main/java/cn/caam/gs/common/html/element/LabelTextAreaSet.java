package cn.caam.gs.common.html.element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelTextAreaSet {

	private int labelWidth; 
	private String labelName; 
	private String textId; 
	private String textName;
	private String textValue;
	private int rows;
	private LabelTextAreaSetType outPutType;
	
	public String html() {
		if(outPutType == LabelTextAreaSetType.WITH_LABEL) {
			return getWithLabel();
		}else {
			return get();
		}
	}
	
	private String get() {
		StringBuffer sb = new StringBuffer();
		sb.append("<textarea ");
		sb.append(" class='form-control'");
		sb.append(" id='" + textId + "' name='" + textName + "' rows='"+rows+"'>");
		sb.append(textValue);
		sb.append("</textarea>");
		return sb.toString();
	}
	
	private String getWithLabel() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
		sb.append("<span class='item-input-label'>" + labelName + "</span>");
		sb.append("</div>");
		sb.append(get());
		sb.append("</div>");
		return sb.toString();
	}

	public enum LabelTextAreaSetType {

		SIMPLE 			(1),
		WITH_LABEL	 	(2),
		;
		
	    /** type. */
	    private int key;

	    private LabelTextAreaSetType(int key) {
	        this.key = key;
	    }

	    public int getKey() {
	        return key;
	    }

	    public LabelTextAreaSetType[] list() {
	    	return LabelTextAreaSetType.values();
	    }
	    
	    public static LabelTextAreaSetType keyOf(int key) {
	    	for(LabelTextAreaSetType type : LabelTextAreaSetType.values()) {
	    		if(key == type.getKey()) {
	    			return type;
	    		}
	    	}
	    	
	    	return null;
	    }
	}
}
