package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelTextAreaSet {

	private String labelName; 
	private String id; 
	private String name;
	private String value;
	private String placeholder;
    @Default
    private boolean notBlank = false;
    @Default
    private int maxlength = 0;
	private int rows;
    private CssFontSizeType fontSize;
	private LabelTextAreaSetType outPutType;
    private CssGridsType grids;
	
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
		sb.append(" class='form-control ");
		sb.append((Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
		sb.append(" id='" + id + "' name='" + name + "' rows='"+rows+"'");
        if (Objects.nonNull(placeholder)) {
            sb.append(" placeholder='" + placeholder + "'");
        }
        if (maxlength > 0) {
            sb.append(" maxlength='"+ maxlength + "'");
        }
        sb.append("style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "' ");
        sb.append(">");
		sb.append(value);
		sb.append("</textarea>");
		return sb.toString();
	}
	
	private String getWithLabel() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group mb-3'>");
		sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
		sb.append("</span>");
		sb.append(get());
		sb.append("</div>");
		return getContext(sb.toString());
	}
	
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if (Objects.nonNull(grids)) {
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            +" '>");
        } else {
            sb.append("class='col'>");
        }
        sb.append(coreStr);
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
