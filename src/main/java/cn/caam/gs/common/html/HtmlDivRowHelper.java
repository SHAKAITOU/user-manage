package cn.caam.gs.common.html;


import java.util.List;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.html.element.DivRowSet;
import cn.caam.gs.common.html.element.DivRowSet.DivRowSetType;
import cn.caam.gs.common.html.element.bs5.DivContainerSet;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;

@Component
public class HtmlDivRowHelper extends HtmlBaseHelper {

    public String get(String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.SIMPLE)
                .components(contexts)
                .build().html();
    }
    
    public String cellBlank(int height) {
        return DivContainerSet.builder().outPutType(DivContainerSetType.SIMPLE)
                .contexts(new String[] {DivRowSet.builder().outPutType(DivRowSetType.CELL_BLANK)
                        .cellHeight(height).build().html()}).build().html();
    }
    
    public String narrow(CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NARROW)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .components(contexts)
                .build().html();
    }
    
    public String narrow(CellWidthType cellWidthType, List<CssAlignType> alignTypes, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NARROW)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellAligns(alignTypes)
                .components(contexts)
                .build().html();
    }
    
    public String get(String[] cellIds, CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellIds(cellIds)
                .components(contexts)
                .build().html();
    }
    
    public String get(CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .components(contexts)
                .build().html();
    }
    
    public String getWithWidth(int width, CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL)
                .rowWidth(width)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .components(contexts)
                .build().html();
    }
    
    public String get(int height, CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL_WITH_HEIGHT)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellHeight(height)
                .components(contexts)
                .build().html();
    }
    
    public String get(String[] cellIds, int height, CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL_WITH_HEIGHT)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellIds(cellIds)
                .cellHeight(height)
                .components(contexts)
                .build().html();
    }
    
    public String withContainer(CellWidthType cellWidthType, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL_WITH_CONTAINER)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .components(contexts)
                .build().html();
    }
    
    public String get(int height, CellWidthType cellWidthType, List<CssAlignType> alignTypes, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL_WITH_HEIGHT)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellAligns(alignTypes)
                .cellHeight(height)
                .components(contexts)
                .build().html();
    }
    
    public String get(CellWidthType cellWidthType, List<CssAlignType> alignTypes, String... contexts) {
        return DivRowSet.builder().outPutType(DivRowSetType.NORMAL)
                .cellWidthType(cellWidthType)
                .mCellWidthType(cellWidthType)
                .cellAligns(alignTypes)
                .components(contexts)
                .build().html();
    }
    
    
    public String withColumns(String... cols) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'>");
        for(String col : cols) {
            sb.append(col);
        }
        sb.append("</div>");
        return sb.toString();
    }
}
