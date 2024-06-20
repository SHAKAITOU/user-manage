package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.TdSet;
import cn.caam.gs.common.html.element.TdSet.TdSetType;

@Component
public class HtmlTdHelper extends HtmlBaseHelper {

    public TdSet index(CssGridsType grids, String... context) {
        return TdSet.builder().outPutType(TdSetType.INDEX).grids(grids.getKey()).contexts(context).build();
    }
    
    public TdSet index(int height, CssGridsType grids, String... context) {
        return TdSet.builder().outPutType(TdSetType.INDEX)
                .height(height).grids(grids.getKey()).contexts(context).build();
    }
    
    public TdSet get(int width, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.NORMAL).width(width).align(alignType).contexts(context).build();
    }
    public TdSet get(CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.NORMAL).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet get(int height, CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.NORMAL)
                .height(height).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet withTrimWidth(int width, int height, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM).width(width).height(height).align(alignType).contexts(context).build();
    }
    
    public TdSet withTrimWidth(int width, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM).width(width).align(alignType).contexts(context).build();
    }
    
    public TdSet withTrim(CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet withTrim(int height, CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM)
                .height(height).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet subWithTrim(CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet subWithTrim(int height, CssGridsType grids, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM)
                .height(height).grids(grids.getKey()).align(alignType).contexts(context).build();
    }
    
    public TdSet withTooltip(int height, CssGridsType grids, 
            String tooltipContext, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.TOOLTIP)
                .height(height).grids(grids.getKey()).align(alignType)
                .contexts(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet withTrimWithTooltip(CssGridsType grids, 
            String tooltipContext, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_CUSTOMIZE_TOOLTIP)
                .grids(grids.getKey()).align(alignType)
                .contexts(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet withTrimWithTooltip(int height, CssGridsType grids, 
            String tooltipContext, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_CUSTOMIZE_TOOLTIP)
                .height(height).grids(grids.getKey()).align(alignType)
                .contexts(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet subWithTrimWithTooltip(CssGridsType grids, 
            String tooltipContext, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_CUSTOMIZE_TOOLTIP)
                .grids(grids.getKey()).align(alignType)
                .contexts(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet subWithTrimWithTooltip(int height, CssGridsType grids, 
            String tooltipContext, CssAlignType alignType, String... context) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_CUSTOMIZE_TOOLTIP)
                .height(height).grids(grids.getKey()).align(alignType)
                .contexts(context).tooltipContext(tooltipContext).build();
    }
    
}
