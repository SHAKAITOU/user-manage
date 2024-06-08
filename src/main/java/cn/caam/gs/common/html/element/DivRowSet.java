package cn.caam.gs.common.html.element;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivRowSet {

    
    private String rowId;
    private int cellHeight;
    private int rowWidth;
    private CellWidthType cellWidthType;
    private CellWidthType mCellWidthType;
    private List<CssAlignType> cellAligns;
    private String[] cellIds;
    private String[] components;
    private DivRowSetType outPutType;
    
    public String html() {
        if(outPutType == DivRowSetType.NARROW) {
            return getNarrowRow(false);
        }else if(outPutType == DivRowSetType.NARROW_WITH_CONTAINER) {
            return getNarrowRow(true);
        }else if(outPutType == DivRowSetType.NORMAL_WITH_HEIGHT) {
            return getNomalRowWithHeight();
        } else if(outPutType == DivRowSetType.CELL_BLANK) {
            return getCellBlankRow();
        } else if(outPutType == DivRowSetType.SIMPLE) {
            return getSimpleRow();
        }else if(outPutType == DivRowSetType.NORMAL_WITH_CONTAINER) {
            return getNomalRow(true);
        } else {
            return getNomalRow(false);
        }
    }
    
    private String getNomalRow(boolean withContainer) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'");
        if(StringUtils.isNotEmpty(rowId)) {
            sb.append(" id='"+rowId+"'");
        }
        
        if (rowWidth > 0) {
            sb.append(" style='width:" + rowWidth + "px;' ");
        }
        sb.append(">");
        if(withContainer) {
            sb.append("<div class='container'>");
        }
        
        if(cellIds == null) {
            cellIds = new String[components.length];
            for(int i=0; i<components.length; i++) {
                cellIds[i] = "";
            }
        }
        
        int index = 0;
        for(int i=0; i<cellWidthType.getValue().length; i++) {
            if(index < components.length) {
                if(Objects.nonNull(cellAligns) && index < cellAligns.size()) {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            cellAligns.get(index), components[index]));
                } else {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            CssAlignType.LEFT, components[index]));
                }
            }else {
                sb.append(getCell(null, 
                        cellWidthType.getValue()[index], 
                        Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                        CssAlignType.LEFT, ""));
            }
            index++;
        }
        if(withContainer) {
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getNomalRowWithHeight() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row' style='height: "+cellHeight+"px;");
        if (rowWidth > 0) {
            sb.append(" width:" + rowWidth + "px; ");
        }
        sb.append("' ");
        if(StringUtils.isNotEmpty(rowId)) {
            sb.append(" id='"+rowId+"'");
        }
        sb.append(">");
        if(cellIds == null) {
            cellIds = new String[components.length];
            for(int i=0; i<components.length; i++) {
                cellIds[i] = "";
            }
        }
        int index = 0;
        for(int i=0; i<cellWidthType.getValue().length; i++) {
            if(index < components.length) {
                if(Objects.nonNull(cellAligns) && index < cellAligns.size()) {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            cellAligns.get(index), components[index]));
                } else {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            CssAlignType.LEFT, components[index]));
                }
            }else {
                sb.append(getCell(null, 
                        cellWidthType.getValue()[index], 
                        Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                        CssAlignType.LEFT, ""));
            }
            index++;
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getNarrowRow(boolean withContainer) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row item-row'");
        if (rowWidth > 0) {
            sb.append(" style='width:" + rowWidth + "px;' ");
        }
        if(StringUtils.isNotEmpty(rowId)) {
            sb.append(" id='"+rowId+"'");
        }
        sb.append(">");
        if(withContainer) {
            sb.append("<div class='container'>");
        }
        if(cellIds == null) {
            cellIds = new String[components.length];
            for(int i=0; i<components.length; i++) {
                cellIds[i] = "";
            }
        }
        int index = 0;
        for(int i=0; i<cellWidthType.getValue().length; i++) {
            if(index < components.length) {
                if(Objects.nonNull(cellAligns) && index < cellAligns.size()) {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            cellAligns.get(index), components[index]));
                } else {
                    sb.append(getCell(cellIds[index], 
                            cellWidthType.getValue()[index], 
                            Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                            CssAlignType.LEFT, components[index]));
                }
            }else {
                sb.append(getCell(null, 
                        cellWidthType.getValue()[index], 
                        Objects.nonNull(mCellWidthType.getValue()[index]) ? mCellWidthType.getValue()[index] : cellWidthType.getValue()[index],
                        CssAlignType.LEFT, ""));
            }
            index++;
        }
        if(withContainer) {
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getCellBlankRow() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'><div class='col-12 text-right' style='height: "+cellHeight+"px;'></div></div>");
        return sb.toString();
    }
    
    private String getSimpleRow() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'>");
        for(String component : components) {
            sb.append(component);
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getCell(String id, int cellWidth, int mCellWidth, CssAlignType align, String component) {
        if(cellWidth == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='col-"+cellWidth+" col-"+mCellWidth+" text-"+align.getKey()+"' ");
        if(StringUtils.isNotEmpty(id)) {
            sb.append("id='"+id+"'");
        }
        sb.append(">");
        sb.append(component);
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum DivRowSetType {

        NORMAL                     (1),
        NARROW                     (2),
        NORMAL_WITH_HEIGHT        (3),
        CELL_BLANK                (4),
        SIMPLE                    (5),
        NORMAL_WITH_CONTAINER    (6),
        NARROW_WITH_CONTAINER    (7),
        ;
        
        /** type. */
        private int key;

        private DivRowSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public DivRowSetType[] list() {
            return DivRowSetType.values();
        }
        
        public static DivRowSetType keyOf(int key) {
            for(DivRowSetType type : DivRowSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
