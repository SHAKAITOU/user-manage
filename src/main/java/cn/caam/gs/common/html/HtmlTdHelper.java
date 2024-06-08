package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.html.element.TdSet;
import cn.caam.gs.common.html.element.TdSet.TdSetType;

@Component
public class HtmlTdHelper extends HtmlBaseHelper {

    public TdSet index(int grids, String context) {
        return TdSet.builder().outPutType(TdSetType.INDEX).grids(grids).context(context).build();
    }
    
    public TdSet index(int height, int grids, String context) {
        return TdSet.builder().outPutType(TdSetType.INDEX)
                .height(height).grids(grids).context(context).build();
    }
    
    public TdSet get(int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.NORMAL).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet get(int height, int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.NORMAL)
                .height(height).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet withTrim(int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet withTrim(int height, int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM)
                .height(height).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet withTrim(int grids, String context, CssAlignType alignType, String foot) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM)
                .grids(grids).align(alignType).context(context).footContext(foot).build();
    }
    
    public TdSet withTrim(int height, int grids, String context, CssAlignType alignType, String foot) {
        return TdSet.builder().outPutType(TdSetType.WITH_TRIM)
                .height(height).grids(grids).align(alignType).context(context).footContext(foot).build();
    }
    
    public TdSet subWithTrim(int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet subWithTrim(int height, int grids, String context, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM)
                .height(height).grids(grids).align(alignType).context(context).build();
    }
    
    public TdSet subWithTrim(int grids, String context, CssAlignType alignType, String foot) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM)
                .grids(grids).align(alignType).context(context).footContext(foot).build();
    }
    
    public TdSet subWithTrim(int height, int grids, String context, CssAlignType alignType, String foot) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_TRIM)
                .height(height).grids(grids).align(alignType).context(context).footContext(foot).build();
    }
    
    public TdSet withTooltip(int height, int grids, String context, 
            String tooltipContext, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.TOOLTIP)
                .height(height).grids(grids).align(alignType)
                .context(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet withTrimWithTooltip(int grids, String context, 
            String tooltipContext, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_CUSTOMIZE_TOOLTIP)
                .grids(grids).align(alignType)
                .context(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet withTrimWithTooltip(int height, int grids, String context, 
            String tooltipContext, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_CUSTOMIZE_TOOLTIP)
                .height(height).grids(grids).align(alignType)
                .context(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet subWithTrimWithTooltip(int grids, String context, 
            String tooltipContext, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_CUSTOMIZE_TOOLTIP)
                .grids(grids).align(alignType)
                .context(context).tooltipContext(tooltipContext).build();
    }
    
    public TdSet subWithTrimWithTooltip(int height, int grids, String context, 
            String tooltipContext, CssAlignType alignType) {
        return TdSet.builder().outPutType(TdSetType.WITH_SUB_CUSTOMIZE_TOOLTIP)
                .height(height).grids(grids).align(alignType)
                .context(context).tooltipContext(tooltipContext).build();
    }
    
}
