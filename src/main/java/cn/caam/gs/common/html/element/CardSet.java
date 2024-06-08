package cn.caam.gs.common.html.element;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssClassType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardSet {
    
    private String id;
    private String bodyId;
    @Default
    private Integer width = 0;
    private Integer height;
    private String title;
    private String[] contexts;
    private CssClassType classType; 
    private CardSetType outPutType;
    
    public String html() {
        if(outPutType == CardSetType.BORDER_TITLE_NO_SCROLL) {
            return getBorder(false, false);
        }else if(outPutType == CardSetType.BORDER_TITLE_SCROLL) {
            return getBorder(true, false);
        }else if(outPutType == CardSetType.BORDER_NO_TITLE_NO_SCROLL) {
            return getBorderNoTitle(false, false);
        }else if(outPutType == CardSetType.BORDER_NO_TITLE_SCROLL) {
            return getBorderNoTitle(true, false);
        }else if(outPutType == CardSetType.BG_TITLE_NO_SCROLL) {
            return getBorder(false, true);
        }else if(outPutType == CardSetType.BG_TITLE_SCROLL) {
            return getBorder(true, true);
        }else if(outPutType == CardSetType.BG_NO_TITLE_NO_SCROLL) {
            return getBorderNoTitle(false, true);
        }else if(outPutType == CardSetType.BG_FOR_TAB) {
            return getBorderNoTitleForTab(false, true);
        }else if(outPutType == CardSetType.BORDER_FOR_TAB) {
            return getBorderNoTitleForTab(false, false);
        }else {
            return getBorderNoTitle(true, true);
        }
    }
    
    
    private String getBorder(boolean scrollable, boolean bgStyle) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='card "+ (bgStyle ? "bg" : "border") + "-"+classType.getKey()+" mb-3 mx-0 card-radius' ");
        if (width > 0) {
            sb.append(" style='min-width: "+width+"px;max-width: "+width+"px;");
        } else {
            sb.append(" style='");
        }
        if(scrollable) {
            sb.append(" max-height:"+height+"px; min-height:"+height+"px;'>");
        }else {
            sb.append(" max-height:100%; min-height:100%;'>");
        }
        sb.append("<div class='card-header'>"+title+"</div>");
        sb.append("<div ");
        if(!StringUtils.isEmpty(bodyId)) {
            sb.append(" id='"+bodyId+"' ");
        }
        sb.append(" class='card-body ");
        if(scrollable) {
            sb.append("div-scrollable ");
        }
        sb.append("'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getBorderNoTitle(boolean scrollable, boolean bgStyle) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='card " + (bgStyle ? "bg" : "border") + "-"+classType.getKey()+" mb-3 card-radius-all' ");
        
        if (width > 0) {
            sb.append(" style='min-width: "+width+"px;max-width: "+width+"px;");
        } else {
            sb.append(" style='");
        }
        if(scrollable) {
            sb.append(" max-height:"+height+"px; min-height:"+height+"px;'>");
        }else {
            sb.append(" max-height:100%; min-height:100%;'>");
        }
        sb.append("<div ");
        if(!StringUtils.isEmpty(bodyId)) {
            sb.append(" id='"+bodyId+"' ");
        }
        sb.append(" class='card-body ");
        if(scrollable) {
            sb.append("div-scrollable ");
        }
        sb.append("'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getBorderNoTitleForTab(boolean scrollable, boolean bgStyle) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='card " + (bgStyle ? "bg" : "border") + "-"+classType.getKey()+" mb-3 card-radius-2' ");
        
        if (width > 0) {
            sb.append(" style='min-width: "+width+"px;max-width: "+width+"px;");
        } else {
            sb.append(" style='");
        }
        if(scrollable) {
            sb.append(" max-height:"+height+"px; min-height:"+height+"px;'>");
        }else {
            sb.append(" max-height:100%; min-height:100%;'>");
        }
        sb.append("<div ");
        if(!org.apache.commons.lang3.StringUtils.isEmpty(bodyId)) {
            sb.append(" id='"+bodyId+"' ");
        }
        sb.append(" class='card-body ");
        if(scrollable) {
            sb.append("div-scrollable ");
        }
        sb.append("'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum CardSetType {

        BORDER_TITLE_NO_SCROLL        (1),
        BORDER_TITLE_SCROLL            (2),
        BORDER_NO_TITLE_NO_SCROLL    (3),
        BORDER_NO_TITLE_SCROLL        (4),
        BG_TITLE_NO_SCROLL            (5),
        BG_TITLE_SCROLL                (6),
        BG_NO_TITLE_NO_SCROLL        (7),
        BG_NO_TITLE_SCROLL            (8),
        BORDER_FOR_TAB                (9),
        BG_FOR_TAB                    (10),
        ;
        
        /** type. */
        private int key;

        private CardSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CardSetType[] list() {
            return CardSetType.values();
        }
        
        public static CardSetType keyOf(int key) {
            for(CardSetType type : CardSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
