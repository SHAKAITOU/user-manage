package cn.caam.gs.common.html.element;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.SpanSet.SpanSetType;
import cn.caam.gs.common.util.UtilConstants;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelContextSet {

	

	
	private int labelWidth; 
	private String id; 
	private String spanId; 
	private String labelName; 
	private String context; 
	private CssClassType contextType; 
	private CssAlignType contextAlign; 
	private String footHtml;
	private LabelContextSetType outPutType;
	
	public String html() {
		if(outPutType == LabelContextSetType.NARROW) {
			return getNarrow();
		}else if(outPutType == LabelContextSetType.CUSTOMIZE) {
			return getCustomize();
		}else if(outPutType == LabelContextSetType.NARROW_CUSTOMIZE) {
			return getNarrowCustomize();
		}else if(outPutType == LabelContextSetType.WITH_FOOT) {
			return getWithFoot();
		}else if(outPutType == LabelContextSetType.LARGE) {
			return getLargeWithFoot();
		}else {
			return get();
		}
	}
	
	private String getCustomize() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		//label[
		
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		sb.append("<span id='"+id+"' class='item-input-label'>"+labelName+"</span>");
		sb.append("</div>");
		//]
		//context[
		sb.append(StringUtils.isEmpty(context) ? UtilConstants.HTML_SPACE : context);
		//]
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getNarrowCustomize() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		//label[
		
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		sb.append("<span id='"+id+"' class='item-label'>"+labelName+"</span>");
		sb.append("</div>");
		//]
		//context[
		sb.append(StringUtils.isEmpty(context) ? UtilConstants.HTML_SPACE : context);
		//]
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getNarrow() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		//label[
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		SpanSet spanLabel = SpanSet.builder().outPutType(SpanSetType.NARROW_SIMPLE)
								.id(id)
								.context(labelName)
								.build();
		sb.append(spanLabel.html());
		sb.append("</div>");
		//]
		//context[
		SpanSet spanContext = SpanSet.builder().outPutType(SpanSetType.NARROW_BADGE)
								.id(spanId)
								.context(context)
								.type(contextType)
								.align(contextAlign)
								.formCtrol(true)
								.build();
		sb.append(spanContext.html());
		//]
		sb.append("</div>");
		return sb.toString();
	}
	
	private String get() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		sb.append("<span id='"+id+"' class='item-input-label'>"+labelName+"</span>");
		sb.append("</div>");
		SpanSet spanContext = SpanSet.builder().outPutType(SpanSetType.BADGE)
								.id(spanId)
								.context(context)
								.type(contextType)
								.align(contextAlign)
								.formCtrol(true)
								.build();
		sb.append(spanContext.html());
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getWithFoot() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		sb.append("<span id='"+id+"' class='item-input-label'>"+labelName+"</span>");
		sb.append("</div>");
		SpanSet spanContext = SpanSet.builder().outPutType(SpanSetType.BADGE)
								.id(spanId)
								.context(context)
								.type(contextType)
								.align(contextAlign)
								.formCtrol(true)
								.build();
		sb.append(spanContext.html());
		if(StringUtils.isNotBlank(footHtml)) {
			sb.append("<div class='input-group-append'>");
			sb.append("<span class='td-t2d input-group-text text-center'>&nbsp;&nbsp;&nbsp;");
			sb.append(footHtml);
			sb.append("</span>");
			sb.append("</div>");
		}
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getLargeWithFoot() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='input-group'>");
		sb.append("<div class='td-t2d input-group-append' ");
		if(labelWidth > 0) {
			sb.append(" style='width:"+labelWidth+"px;'");
		}
		sb.append(">");
		sb.append("<span id='"+id+"' class='item-input-label-lg'>"+labelName+"</span>");
		sb.append("</div>");
		SpanSet spanContext = SpanSet.builder().outPutType(SpanSetType.LARGE_BADGE)
								.id(spanId)
								.context(context)
								.type(contextType)
								.align(contextAlign)
								.formCtrol(true)
								.build();
		sb.append(spanContext.html());
		if(StringUtils.isNotBlank(footHtml)) {
			sb.append("<div class='input-group-append'>");
			sb.append("<span class='td-t2d input-group-text text-center item-input-label-lg'>&nbsp;&nbsp;&nbsp;");
			sb.append(footHtml);
			sb.append("</span>");
			sb.append("</div>");
		}
		sb.append("</div>");
		return sb.toString();
	}
	
	public enum LabelContextSetType {

		NORMAL 				(1),
		NARROW 				(2),
		CUSTOMIZE 			(3),
		NARROW_CUSTOMIZE 	(4),
		WITH_FOOT 			(5),
		LARGE				(6),
		;
		
	    /** type. */
	    private int key;

	    private LabelContextSetType(int key) {
	        this.key = key;
	    }

	    public int getKey() {
	        return key;
	    }

	    public LabelContextSetType[] list() {
	    	return LabelContextSetType.values();
	    }
	    
	    public static LabelContextSetType keyOf(int key) {
	    	for(LabelContextSetType type : LabelContextSetType.values()) {
	    		if(key == type.getKey()) {
	    			return type;
	    		}
	    	}
	    	
	    	return null;
	    }
	}
}
