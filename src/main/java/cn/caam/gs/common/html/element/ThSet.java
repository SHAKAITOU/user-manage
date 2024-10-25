package cn.caam.gs.common.html.element;


import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.SortOrderType;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThSet {
    
    private int grids;
    private int height;
    private int width;
    private String[] contexts;
    private CssFontSizeType fontSize;
    private CssAlignType align;
    private ThSetType outPutType;
    private boolean sort;
    private String sortName;
    private String selectedSortName;
    private String selectedSortOrder;
    
    public String html() {
        if(outPutType == ThSetType.INDEX) {
            return getIndex();
        } else {
            return get();
        }
    }
    
    private String getIndex() {
        StringBuffer sb = new StringBuffer();
        sb.append("<th scope='col' class=' ");
        sb.append(IconSetCss.NOMAL_14.getKey());
        sb.append(" text-center ");
        if (width > 0) {
            sb.append("' width='"+width+"' ");
        } else {
            sb.append(" col-xs-"+grids+"' ");
        }
        sb.append(" height='"+(height == 0 ? GlobalConstants.TH_DEFAULT_HEIGHT : height)+"'");        
        sb.append(">");
        sb.append(setMiddleForCell(CssAlignType.CENTER, 
                IconSet.builder().type(IconSetType.INDEX_MARK).css(IconSetCss.NOMAL_14).build().html()));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<th class=' ");
        sb.append((Objects.nonNull(fontSize) ? fontSize.getKey(): CssFontSizeType.LABEL_12B.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        if (width > 0) {
            sb.append("' width='"+width+"' ");
        } else {
            sb.append(" col-xs-"+grids+"' ");
        }
        sb.append(" height='"+(height == 0 ? GlobalConstants.TH_DEFAULT_HEIGHT : height)+"'");
        sb.append(">");
        sb.append(setMiddleForCell(contexts));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String setMiddleForCell(String... contexts) {
        return setMiddleForCell((Objects.nonNull(align) ? align : CssAlignType.LEFT), contexts);
    }
    
    private String setMiddleForCell(CssAlignType cssAlignType, String context) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div style='display: table;height:100%;width:100%'>");
        sb.append("<div style='display: table-cell; vertical-align: middle;");
        sb.append(" text-align: "+cssAlignType.getKey()+";'>");
        sb.append(context);
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String setMiddleForCell(CssAlignType cssAlignType, String... contexts) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div style='display: table;height:100%;width:100%'>");
        sb.append("<div style='display: table-cell; vertical-align: middle;");
        sb.append(" text-align: "+cssAlignType.getKey()+";'>");
        for (String context : contexts) {
            sb.append(context);
            if (sort) {
	            sb.append("<span style='display: -ms-inline-flexbox; display: inline-flex; -ms-flex-direction: column; flex-direction: column; -ms-flex-align: center; align-items: center;'>");
	            
	            boolean isSortUpSelected = !Strings.isBlank(selectedSortName) && selectedSortName.equalsIgnoreCase(sortName) && SortOrderType.ASC.getKey().equalsIgnoreCase(selectedSortOrder);
	            boolean isSortDownSelected = !Strings.isBlank(selectedSortName) && (selectedSortName.equalsIgnoreCase(sortName) && SortOrderType.DESC.getKey().equalsIgnoreCase(selectedSortOrder));
	            
	            sb.append("<i class='fas fa-sort-up"+(isSortUpSelected ? "":" sort-object")+"' style='height: 3px;"+(isSortUpSelected ? "color: #8c8c8c;":" cursor: pointer;")+"' sort-name='"+sortName+"' sort-order='"+SortOrderType.ASC.getKey()+"'></i>");
	            sb.append("<i class='fas fa-sort-down"+(isSortDownSelected ? "":" sort-object")+"' style='height: 3px;"+(isSortDownSelected ? "color: #8c8c8c;":" cursor: pointer;")+"' sort-name='"+sortName+"' sort-order='"+SortOrderType.DESC.getKey()+"'></i>");
	            sb.append("</span>");
            }
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum ThSetType {

        INDEX                     (1),
        NORMAL                     (2),
        ;
        
        /** type. */
        private int key;

        private ThSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public ThSetType[] list() {
            return ThSetType.values();
        }
        
        public static ThSetType keyOf(int key) {
            for(ThSetType type : ThSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
