package cn.caam.gs.common.html;


import java.util.List;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.TableSet;
import cn.caam.gs.common.html.element.TableSet.TableSetType;
import cn.caam.gs.common.html.element.TrSet;

@Component
public class HtmlTableHelper extends HtmlBaseHelper {
    
    public String get(String id, TrSet headTr, List<TrSet> bodyList) {
        return TableSet.builder().outPutType(TableSetType.NORMAL)
                .id(id)
                .headTr(headTr).bodyList(bodyList)
                .build().html();
    }
    
    public String get(String id, int tableHeight, TrSet headTr, List<TrSet> bodyList) {
        return TableSet.builder().outPutType(TableSetType.NORMAL)
                .id(id).bodyHeight(tableHeight)
                .headTr(headTr).bodyList(bodyList)
                .build().html();
    }
    
    public String get(String id, int tableHeight, 
            TrSet headTr, List<TrSet> bodyList, String pageLinkedHtml) {
        return TableSet.builder().outPutType(TableSetType.NORMAL)
                .id(id).bodyHeight(tableHeight)
                .headTr(headTr).bodyList(bodyList)
                .build().html()+pageLinkedHtml;
    }
}
