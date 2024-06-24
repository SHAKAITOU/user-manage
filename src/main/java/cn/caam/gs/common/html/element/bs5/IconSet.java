package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IconSet {

    private IconSetType type;
    private IconSetCss css;
    @Default
    private IconType iconType = IconType.SIMPLE;
    private String id;
    
    public String html() {
        if (css == null) {
            return getIcon(type.getKey());
        } else {
            return getIcon(type.getKey(), css.getKey());
        }
    }
    
    private String getIcon(String iconName) {
        StringBuffer sb = new StringBuffer();
        
        sb.append("<i "
                + (Objects.nonNull(id) ? "id='" + id + "'" : "" )
                + "class='" + iconName
                + " " + (iconType.key == IconType.LINK.getKey() ? "a-a2" : "")
                +"'>");
        sb.append("</i>");
        
        return sb.toString();
    }
    
    private String getIcon(String iconName, String cssName) {
        StringBuffer sb = new StringBuffer();
        
        sb.append("<span class='" + cssName
                + " " + (iconType.key == IconType.LINK.getKey() ? "a-a2" : "")
                + "'>");
        sb.append("<i "
                + (Objects.nonNull(id) ? "id='" + id + "'" : "" )
                + " class='"+iconName+"'>");
        sb.append("</i>");
        sb.append("</span>");
        
        return sb.toString();
    }
    

    public enum IconType {

        SIMPLE      (1),
        LINK        (2),
        ;
        
        /** type. */
        private int key;

        private IconType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public IconType[] list() {
            return IconType.values();
        }
        
        public static IconType keyOf(int key) {
            for(IconType type : IconType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
    
    public enum IconSetType {
        /**  */
        SIGN_IN                ("fa fa-sign-in-alt"),
        /**  */
        SIGN_OUT            ("fa fa-sign-out-alt"),
        /**  */
        INDEX_MARK            ("fa fa-list-ol"),
        /** 拼图 */
        PUZZLE_PIECE        ("fas fa-puzzle-piece"),
        /** ＋ */
        PLUS                ("fas fa-plus"),
        /** 实心圆＋ */
        PLUS_CIRCLE            ("fa fa-plus-circle"),
        /** ー */
        MINUS                ("fas fa-minus"),
        
        MINUS_CIRCLE        ("fa fa-minus-circle"),
        /** 实心圆● */
        CIRCLE                ("fas fa-circle"),
        /** bar */
        BAR                    ("fa fa-bars"),
        /** 詳細 */
        DETAIL                ("fa fa-bars"),
        
        FILE                  ("fa fa-file"),
        
        /** 用户头 */
        USER                ("far fa-user"),
        /** group */
        GROUP                ("fa fa-users"),
        /** 管理员头 */
        ADMIN                ("fa fa-user-circle"),
        /** 大楼 */
        BUILDING            ("fas fa-building"),
        /** 店铺 */
        STORE                ("fas fa-store"),
        /** 时钟 */
        CLOCK                ("fa fa-clock"),
        /**  */
        DATABASE                ("fa fa-database"),
        
        EDIT                 ("fa fa-pencil"),
        /** 日历 */
        CALENDAR            ("far fa-calendar-alt"),
        /** 喇叭 */
        BULLHORN            ("fas fa-bullhorn"),
        /** 盒子 */
        ARCHIVE                ("fas fa-archive"),
        /** 购物车 */
        RETAIL_STORE        ("fas fa-shopping-cart"),
        /**  */
        RESTAURANT            ("fas fa-utensils"),
        /** 表 */
        TABLE                ("fa fa-list-alt"),
        /** 主题 */
        THEMA                ("fa fa-bookmark"),
        /** 警告标 */
        WARNING                ("fa fa-exclamation-circle"),
        /** 信息标 */
        INFO                ("fa fa-info-circle"),
        /** 错号关闭 */
        CLOSE                ("fa fa-times-circle"),

        /** 对号 */
        CHECK                ("fa fa-check-circle"),
        /** 设定 */
        SETTING                ("fa fa-cog"),
        /** 检索 */
        SEARCH                ("fa fa-search"),
        /** クリア */
        CLEAR                ("fa fa-trash"),
        /** send */
        SEND                ("fa fa-paper-plane"),
        /** select */
        SELECT                ("fa fa-reply"),
        /** → */
        TO_RIGHT            ("fa fa-arrow-right"),
        /** ← */
        TO_LEFT                ("fa fa-arrow-left"),
        
        TO_UP                 ("fa fa-arrow-up"),
        TO_DOWN                 ("fa fa-arrow-down"),
        /** delete */
        DELETE                ("fa fa-trash"),
        
        /** chart */
        CHART                ("fa fa-map"),
        
        LOCK                ("fa fa-lock"),
        
        APPROVED            ("fa fa-gavel"),
        
        PRINT                ("fa fa-print"),
        
        EYE                    ("fa fa-eye"),
        
        EYE_SLASH              ("fa fa-eye-slash"),
        
        LINK                ("fa fa-link"),
        
        MAIL                ("fa fa-envelope"),
        /** 购物篮 */
        BASKET                ("fa fa-shopping-basket"),
        /** 商品标签 */
        TAGS                ("fa fa-tags"),
        /** 分類 */
        CATEGORY            ("fa fa-th"),
        /** 履歴 */
        HISTORY                ("fa fa-history"),
        
        CART_PLUS            ("fa fa-cart-plus"),
        
        SORT_NUMERIC        ("fa fa-sort-numeric-up"),
        
        MAP                    ("fa fa-map-marker"),
        
        FILTER                ("fa fa-filter"),
        
        THUMBS_UP            ("fa fa-thumbs-up"),
        
        DOWNLOAD            ("fa fa-download"),
        
        UPLOAD                ("fa fa-upload"),
        
        CREDIT                ("fa fa-credit-card"),
        
        REFRESH                ("fa fa-retweet"),
        
        CALCULATOR            ("fa fa-calculator"),
        
        BOOK            ("fa fa-book"),
        
        CAMERA            ("fa fa-camera"),
        //向上箭头
        CHEVRON_UP        ("fa fa-chevron-up"),
        //向下箭头
        CHEVRON_DOWN        ("fa fa-chevron-down"),
        
        //小手放去
        HAND_RIGHT            ("fa fa-hand-point-right"),
        
        TO_STACK          ("fas fa-stack-overflow"),
        //小手放去
        STAMP            ("fa fa-stamp"),
        ;
        
        /** type. */
        private String key;

        private IconSetType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public IconSetType[] list() {
            return IconSetType.values();
        }
        
        public static IconSetType keyOf(String key) {
            for(IconSetType type : IconSetType.values()) {
                if(key.equals(type.getKey())) {
                    return type;
                }
            }
            
            return null;
        }
    }
    
    public enum IconSetCss {

        TOMATO_12        ("tomato12"),
        TOMATO_8        ("tomato8"),
        DARK_CYAN_12    ("darkcyan12"),
        WHITE_12        ("white12"),
        WHITE_10        ("white10"),
        NOMAL_14        ("nomal14"),
        NOMAL_12        ("nomal12"),
        NOMAL_10        ("nomal10"),
        NOMAL_8         ("nomal8"),
        ;
        
        /** type. */
        private String key;

        private IconSetCss(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public IconSetCss[] list() {
            return IconSetCss.values();
        }
        
        public static IconSetCss keyOf(String key) {
            for(IconSetCss type : IconSetCss.values()) {
                if(key.equals(type.getKey())) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
