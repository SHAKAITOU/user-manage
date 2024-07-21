package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertiImg3DivSet {

    private String imgSrc;
    private String userId;
    private String registDate;
    private String validEndDate;
    private String qrImgDivId;
    private String name;
    private String sex;
    private String birth;
    private String employer;
    private String jobTitle;
    private String userType;
    
    @Default
    private CertiImg3DivType certiImg3DivType  = CertiImg3DivType.CHINA;
    @Default
    private CssFontSizeType fontSize = CssFontSizeType.LABEL_14;
    @Default
    private CssClassType classType = CssClassType.PRIMARY;
    private CssGridsType grids;
    private GridFlexType gridFlexType;
    public String html() {
        if (certiImg3DivType == CertiImg3DivType.CHINA) {
            return getChina();
        } else {
            return getGansu();
        }
        
    }
    
    private String getGansu() {
        StringBuffer sb = new StringBuffer();
        
        return getContext(sb.toString());
    }

    private String getChina() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='");
        sb.append(classType.getKey());
        sb.append(" ");
        sb.append(fontSize.getKey());
        sb.append(" certi-china-bgImg3 '");
        sb.append(">");
        sb.append("<div class='row'>");
        //左半部分
        //--------------
        sb.append("<div class='col-6'>");
        //头像
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:108px; padding-top:102px;'>");
        sb.append("<img style='width:90px;height:120px;' src='" + imgSrc +"'>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        //会员证号
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:140px; padding-top:25px;'>");
        sb.append(userId);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        //入会日期
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:140px; padding-top:20px;'>");
        sb.append(registDate);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        //有效结束日期(yyyy-MM-dd HH
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:140px; padding-top:25px;'>");
        sb.append(validEndDate);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        //二维码
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:122px; padding-top:10px;'>");
        sb.append("<div style='width:60px;height:60px;' id='"+qrImgDivId+"'></div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        sb.append("</div>");
        //--------------
        //右半部分
        //--------------
        sb.append("<div class='col-6'>");
        //姓名，性别
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:70px; padding-top:93px;'>");
        sb.append(name);
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        sb.append(sex);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        //出生日期
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:120px; padding-top:51px;'>"+birth+"</div>");
        sb.append("</div>");
        sb.append("</div>");
        //工作单位
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:120px; padding-top:56px;'>"+employer+"</div>");
        sb.append("</div>");
        sb.append("</div>");
        //职称
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:120px; padding-top:55px;'>"+jobTitle+"</div>");
        sb.append("</div>");
        sb.append("</div>");
        //会员类别
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:120px; padding-top:56px;'>"+userType+"</div>");
        sb.append("</div>");
        sb.append("</div>");
        
        sb.append("</div>");
        //--------------
        
        sb.append("</div>");
        sb.append("</div>");
        
        return getContext(sb.toString());
    }
    
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        if (Objects.nonNull(grids)) {
            sb.append("<div ");
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            + " " + (Objects.nonNull(gridFlexType) ? gridFlexType.getKey() : GridFlexType.LEFT.getKey()) + " '>");
            sb.append(coreStr);
            sb.append("</div>");
        } else {
            sb.append(coreStr);
        }
        return sb.toString();
    }
    
    public enum CertiImg3DivType {

        CHINA                     (1),
        GANSU                     (2),
        ;
        
        /** type. */
        private int key;

        private CertiImg3DivType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CertiImg3DivType[] list() {
            return CertiImg3DivType.values();
        }
        
        public static CertiImg3DivType keyOf(int key) {
            for(CertiImg3DivType type : CertiImg3DivType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
