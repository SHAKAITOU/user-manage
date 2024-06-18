package cn.caam.gs.app.dbmainten.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.domain.tabledef.DbTableType;

@Component
public class TableSabunViewHelper extends HtmlViewBaseHelper {
	
	public static final String HTML_SABUN 		= "dbmainten/sabunTable";
	public static final String FORM_NAME 		  = "sabunTable_form";

	public static final String COLUMNS_TABLE_CARD_BODY_ID = "columnsTable";

	
	public static final String INDEXS_TABLE_CARD_BODY_ID = "indexsTable";


	public static String getPage(DbTableType dbTableType, String sabunSql) {
		StringBuffer sb = new StringBuffer();
		//hidden values
		sb.append(getPageHiddenData());
		sb.append(divRow().cellBlank(5));
		//company data table
		sb.append(getHeadPanel(dbTableType));
		sb.append(getSabunPanel(sabunSql));

		//bottom button 
		sb.append(getManagePageBottomButtonRow());
		
		return getForm(FORM_NAME, divContainer().get(sb.toString()));
	}
	
	private static String getPageHiddenData() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	private static String getHeadPanel(DbTableType dbTableType) {
		StringBuffer sb = new StringBuffer();
		int labelWidth 		 = 120;

		String labelName 	= "TABLE NAME";
		String context 		= dbTableType.getName();
		String component = context().narrowWithLabel(labelWidth, labelName, context, CssAlignType.LEFT);

		labelName 	= "COMMENT";
		context 		= dbTableType.getComment();
		String component2 = context().narrowWithLabel(labelWidth, labelName, context, CssAlignType.LEFT);

		sb.append(divRow().narrow(CellWidthType.TWO_6_6, component, component2));
		
		return borderCard().noTitleNoScroll("", CssClassType.DANGER, 
				"",  
				divContainer().get(sb.toString()));
	}
	
	private static String getSabunPanel(String sabunSql) {
		StringBuffer sb = new StringBuffer();
		int labelWidth 		 = 120;
		String labelName 	= "SABUN SQL";
		String component = textArea().getWithLabel(labelWidth, labelName, "sabunSql", sabunSql, 15);
		sb.append(divRow().get(CellWidthType.ONE, component));
		return borderCard().withTitleNoScroll("", CssClassType.INFO, "", 
				"SABUN SQL",  
				sb.toString());
	}
	
	

	private static String getManagePageBottomButtonRow() {
		StringBuffer sb = new StringBuffer();
		List<CssAlignType> aligs = new ArrayList<>();
		//bottom button 
	
		String id 				= "btnSabunTableClose";
		String context = getContext("common.page.btn.close");
		String comp2 = button().close(id, context);
		aligs.add(CssAlignType.RIGHT);
		sb.append(divRow().get(CellWidthType.ONE, aligs, comp2));
		
		return sb.toString();
	}
	
}
