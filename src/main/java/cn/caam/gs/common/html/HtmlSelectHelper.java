package cn.caam.gs.common.html;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.AdminMenuType;
import cn.caam.gs.common.enums.BuyPriceAnalysisType;
import cn.caam.gs.common.enums.ContactType;
import cn.caam.gs.common.enums.PayPeriodType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.LabelSelectSet;
import cn.caam.gs.common.html.element.LabelSelectSet.LabelSelectSetType;
import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.DateUtility.MonthFormat;

@Component
public class HtmlSelectHelper extends HtmlBaseHelper {
    
    // company list
    public String getPayPeriodTypeList(int labelWidth, String labelName, String name, int selectedValue) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (PayPeriodType likeType : PayPeriodType.values()) {
            HtmlRadio radio = new HtmlRadio(String.valueOf(likeType.getId()), 
                    getContext(likeType.getMsg()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .id(convertNameDotForId(name))
                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }
    
    public String getPayPeriodTypeList(String name, int selectedValue) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (PayPeriodType likeType : PayPeriodType.values()) {
            HtmlRadio radio = new HtmlRadio(String.valueOf(likeType.getId()), 
                    getContext(likeType.getMsg()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.SIMPLE)
                .id(convertNameDotForId(name))
                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }
    
    public String getContactTypeList(int labelWidth, String labelName, String name, int selectedValue) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (ContactType likeType : ContactType.values()) {
            HtmlRadio radio = new HtmlRadio(String.valueOf(likeType.getId()), 
                    getContext(likeType.getMsg()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .id(convertNameDotForId(name))
                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }
    
    // menu list
    public String getAdminMenuListWithLabel(Map<String,Object> session, int labelWidth, String labelName, String name, int selectedValue) {
        List<HtmlRadio> radios = new ArrayList<>();
        
        for (AdminMenuType type : AdminMenuType.values()) {
            HtmlRadio radio = new HtmlRadio(String.valueOf(type.getId()), getContext(type.getMsg()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .id(convertNameDotForId(name)).name(name)
                .selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }
    
    public String getBuyPriceAnalysisTypeList(String name, int selectedValue) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (BuyPriceAnalysisType likeType : BuyPriceAnalysisType.values()) {
            HtmlRadio radio = new HtmlRadio(String.valueOf(likeType.getId()), 
                    getContext(likeType.getMsg()));
            radios.add(radio);
        }

        return LabelSelectSet.builder().outPutType(LabelSelectSetType.SIMPLE)
                .id(convertNameDotForId(name))
                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
                .build().html();
    }
    
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
    
//    public String getStoreList(int labelWidth, String labelName, String name, String selectedValue, List<StoreResult> storeList) {
//        List<HtmlRadio> radios = new ArrayList<>();
//        HtmlRadio radio = new HtmlRadio("0", getContext("common.select.blank"), true);
//        radios.add(radio);
//        for (int i=0; i<storeList.size(); i++) {
//            StoreResult storeResult = storeList.get(i);
//            radio = new HtmlRadio(
//                    String.valueOf(storeResult.getStore().getId()), 
//                    storeResult.getStore().getName());
//            radios.add(radio);
//        }
//
//        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
//                .labelWidth(labelWidth).labelName(labelName)
//                .id(convertNameDotForId(name))
//                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
//                .build().html();
//    }
//    
//    public String getCategoryList(int labelWidth, String labelName, String name, String selectedValue, List<BidResult> bidList) {
//        List<HtmlRadio> radios = new ArrayList<>();
//        for (int i=0; i<bidList.size(); i++) {
//            BidResult bidResult = bidList.get(i);
//            String bidId = String.valueOf(bidResult.getBid().getId());
//            String bidName = (i+1) + UtilConstants.HTML_SPACE + bidResult.getBid().getName();
//            String bidNameHid = bidResult.getBid().getName();
//            HtmlRadio radio = new HtmlRadio(bidId, bidName, true, bidNameHid);
//            radios.add(radio);
//            for (int j=0; j<bidResult.getCtgryResultList().size(); j++) {
//                CtgryResult ctgryResult = bidResult.getCtgryResultList().get(j);
//                String ctgryId = bidId + UtilConstants.UNDER_BAR + String.valueOf(ctgryResult.getCtgry().getId());
//                String ctgryName = 
//                        UtilConstants.HTML_SPACE + (i+1) + "." + (j+1) + UtilConstants.HTML_SPACE
//                        + ctgryResult.getCtgry().getName();
//                String ctgryNameHid = 
//                        bidNameHid
//                        + UtilConstants.SLASH 
//                        + ctgryResult.getCtgry().getName();
//                radio = new HtmlRadio(ctgryId, ctgryName, true, ctgryNameHid);
//                radios.add(radio);
//                for (int n=0; n<ctgryResult.getKindList().size(); n++) {
//                    MKind kind = ctgryResult.getKindList().get(n);
//                    String kindId = ctgryId + UtilConstants.UNDER_BAR + String.valueOf(kind.getId());
//                    String kindName = 
//                            UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE
//                            + (i+1) + "." + (j+1) + "." + (n+1) + UtilConstants.HTML_SPACE
//                                + kind.getName();
//                    String kindNameHid = 
//                            ctgryNameHid
//                            + UtilConstants.SLASH 
//                            + kind.getName();
//                    radio = new HtmlRadio(kindId, kindName, kindNameHid);
//                    radios.add(radio);
//                }
//            }
//        }
//
//        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
//                .labelWidth(labelWidth).labelName(labelName)
//                .id(convertNameDotForId(name))
//                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
//                .build().html();
//    }
//    
//    public String getFamilyBudgetList(String name, String selectedValue, List<MFamilyBudget> familyBudgetList) {
//        List<HtmlRadio> radios = new ArrayList<>();
//        for (int i=0; i<familyBudgetList.size(); i++) {
//            MFamilyBudget familyBudget = familyBudgetList.get(i);
//            HtmlRadio radio = new HtmlRadio(
//                    String.valueOf(familyBudget.getId()), familyBudget.getName());
//            radios.add(radio);
//        }
//        return LabelSelectSet.builder().outPutType(LabelSelectSetType.SIMPLE)
//                .id(convertNameDotForId(name))
//                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
//                .build().html();
//    }
//    
//    public String getFamilyBudgetList(int labelWidth, String labelName, 
//            String name, String selectedValue, List<MFamilyBudget> familyBudgetList) {
//        List<HtmlRadio> radios = new ArrayList<>();
//        for (int i=0; i<familyBudgetList.size(); i++) {
//            MFamilyBudget familyBudget = familyBudgetList.get(i);
//            HtmlRadio radio = new HtmlRadio(
//                    String.valueOf(familyBudget.getId()), familyBudget.getName());
//            radios.add(radio);
//        }
//        return LabelSelectSet.builder().outPutType(LabelSelectSetType.WITH_LABEL)
//                .labelWidth(labelWidth).labelName(labelName)
//                .id(convertNameDotForId(name))
//                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
//                .build().html();
//    }
//    
//    public String getFamilyBudgetBillList(String name, String selectedValue, List<FamilyBudgetBillResult> familyBudgetBillResultList) {
//        List<HtmlRadio> radios = new ArrayList<>();
//        for (int i=0; i<familyBudgetBillResultList.size(); i++) {
//            FamilyBudgetBillResult familyBudgetBillResult = familyBudgetBillResultList.get(i);
//            YearMonth yearMonth = YearMonth.of(familyBudgetBillResult.getFamilyBudgetBillHead().getYear(), 
//                    familyBudgetBillResult.getFamilyBudgetBillHead().getMonth());
//            HtmlRadio radio = new HtmlRadio(
//                    String.valueOf(familyBudgetBillResult.getFamilyBudgetBillHead().getId()), 
//                    DateUtility.formatYearMonth(yearMonth, MonthFormat.UUUUCMMC.getFormat()));
//            radios.add(radio);
//        }
//        return LabelSelectSet.builder().outPutType(LabelSelectSetType.SIMPLE)
//                .id(convertNameDotForId(name))
//                .name(name).selectedValue(String.valueOf(selectedValue)).radios(radios)
//                .build().html();
//    }
}
