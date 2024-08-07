package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelNumberSet {
    
    public static final int SIMPLE             = 1;
    public static final int WITH_LABEL         = 2;
    public static final int WITH_LABEL_FOOT    = 3;
    public static final int FOR_TABLE        = 4;
    
    private int labelWidth;
    private String labelName;
    private String id;
    private String name;
    private String value;
    private int min;
    private int max;
    private String step;
    private String footHtml;
    private LabelNumberSetType outPutType;
    private String placeholder;
    @Default
    private boolean notBlank = false;
    @Default
    private boolean integerOnly = false;
    private String maxLength;
    
    public String html() {
        if(outPutType == LabelNumberSetType.WITH_LABEL) {
            return getWithLabel();
        }else if(outPutType == LabelNumberSetType.WITH_LABEL_FOOT) {
            return getWithLabelFoot();
        }else if(outPutType == LabelNumberSetType.FOR_TABLE) {
            return getForTableList();
        }else if(outPutType == LabelNumberSetType.LARGE) {
            return getLargeWithLabelFoot();
        }else if(outPutType == LabelNumberSetType.WITH_FOOT) {
            return getWithFoot();
        }else {
            return get();
        }
    }
    
    //number
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:"+labelWidth+"px;'>");
        sb.append("<span class='item-input-label'>"+labelName+"</span>");
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</div>");
        sb.append(get());
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getWithLabelFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</div>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append("<span class='td-t2d input-group-text text-center'>&nbsp;&nbsp;&nbsp;");
        sb.append(footHtml);
        sb.append("</span>");
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getWithFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append("<span class='td-t2d input-group-text text-center'>&nbsp;&nbsp;&nbsp;");
        sb.append(footHtml);
        sb.append("</span>");
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getLargeWithLabelFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label-lg'>" + labelName + "</span>");
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</div>");
        sb.append(getLarge());
        if(StringUtils.isNotBlank(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append("<span class='td-t2d input-group-text text-center item-input-label-lg'>");
            sb.append(footHtml);
            sb.append("</span>");
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        if (integerOnly) {
        	sb.append("<input type='text' oninput=\"value=value.replace(/[^\\d]/g,'')\"");
        }else {
        	sb.append("<input type='number' ");
        }
        sb.append(" min='" + min + "' ");
        sb.append(" max='" + max + "' ");
        sb.append(" step='" + step + "' ");
        sb.append(" class='form-control item-input text-right' ");
        if (!StringUtils.isEmpty(placeholder)) {
            sb.append(" placeholder='(" + placeholder + ")' ");
        }
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "'/>");
        return sb.toString();
    }
    
    private String getLarge() {
        StringBuffer sb = new StringBuffer();

        sb.append("<input type='number'");
        sb.append(" min='" + min + "' ");
        sb.append(" max='" + max + "' ");
        sb.append(" step='" + step + "' ");
        sb.append(" class='form-control item-input-lg text-right' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "'/>");
        return sb.toString();
    }
    
    private String getForTableList() {
        StringBuffer sb = new StringBuffer();

        if (integerOnly) {
        	sb.append("<input type='text' oninput=\"value=value.replace(/[^\\d]/g,'')\"");
        }else {
        	sb.append("<input type='number' ");
        }
        sb.append(" style='height:40px;' ");
        sb.append(" min=" + min + " ");
        sb.append(" max=" + max + " ");
        sb.append(" step='" + step + "' ");
        if (maxLength != null) {
        	sb.append(" maxLength='" + maxLength + "' ");
        }
        sb.append(" class='form-control item-input text-right' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "'/>");
        return sb.toString();
    }
    
    public enum LabelNumberSetType {

        SIMPLE                 (1),
        WITH_LABEL            (2),
        WITH_LABEL_FOOT        (3),
        FOR_TABLE            (4),
        LARGE                (5),
        WITH_FOOT            (6),
        ;
        
        /** type. */
        private int key;

        private LabelNumberSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelNumberSetType[] list() {
            return LabelNumberSetType.values();
        }
        
        public static LabelNumberSetType keyOf(int key) {
            for(LabelNumberSetType type : LabelNumberSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
