package cn.caam.gs.common.html;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.LabelSelectSet;
import cn.caam.gs.common.html.element.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.DateUtility.MonthFormat;

@Component
public class HtmlSelectHelper extends HtmlBaseHelper {

    public String getYearMonthList(String name, String selectedValue, List<YearMonth> yearMonthList, DateUtility.MonthFormat showFormat) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (int i=0; i<yearMonthList.size(); i++) {
            YearMonth yearMonth = yearMonthList.get(i);
            HtmlRadio radio = new HtmlRadio(
                    DateUtility.formatYearMonth(yearMonth, MonthFormat.UUUUMM.getFormat()), 
                    DateUtility.formatYearMonth(yearMonth, MonthFormat.UUUUCMMC.getFormat()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.SIMPLE)
                .id(convertNameDotForId(name))
                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }

}
