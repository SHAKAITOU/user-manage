package cn.caam.gs.app.dbmainten.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.dbmainten.form.IndexInfoForm;
import cn.caam.gs.app.dbmainten.form.SequenceInfoForm;
import cn.caam.gs.app.dbmainten.form.TableInfoForm;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.domain.tabledef.DbTableType;
import cn.caam.gs.domain.tabledef.DbTableUtil;

@Component
public class TableDetailViewHelper extends HtmlViewBaseHelper {
	
	public static final String HTML_DETAIL 		= "dbmainten/detailTable";
	public static final String FORM_NAME 		  = "detailTable_form";
	
	public static final int HEAD_CARD_HEIGHT = 50;
	public static final String COLUMNS_TABLE_CARD_BODY_ID = "columnsTable";
	public static final int COLUMNS_TABLE_CARD_HEIGHT = 300;
	public static final int COLUMNS_TABLE_BODY_HEIGHT = COLUMNS_TABLE_CARD_HEIGHT - 120;
	
	public static final String INDEXS_TABLE_CARD_BODY_ID = "indexsTable";
	public static final int INDEXS_TABLE_CARD_HEIGHT = 180;
	public static final int INDEXS_TABLE_BODY_HEIGHT = INDEXS_TABLE_CARD_HEIGHT - 90;
	
	public static int TABLE_TD_HEIGHT = 30;

	public static String getPage(DbTableType dbTableType) {
		StringBuffer sb = new StringBuffer();
		TableInfoForm info = dbTableType.getTableInfoForm();
		//hidden values
		sb.append(getPageHiddenData());
		sb.append(divRow().cellBlank(5));
		//company data table
		sb.append(getHeadPanel(dbTableType));
		sb.append(getColumnsDataPanel(info.getColumnInfos()));
		sb.append(divRow().get(CellWidthType.TWO_7_5, getIndexsDataPanel(info.getIndexInfos()), 
				getSeqPanel(info)));
		//bottom button 
		sb.append(divRow().cellBlank(5));
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
		//-----------------row 2----------------]
		
		return borderCard().noTitleNoScroll("", CssClassType.DANGER, 
				"",  
				divContainer().get(sb.toString()));
	}
	
	private static String getColumnsDataPanel(List<ColumnInfoForm> columnInfoList) {
		return borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
				"COLUMN LIST",  
				getColumnsData(columnInfoList));
	}
	
	private static String getIndexsDataPanel(List<IndexInfoForm> indexInfoList) {
		return borderCard().withTitleWithScroll("", CssClassType.WARNING, "", 
				"INDEX LIST",  
				getIndexsData(indexInfoList));
	}
	
	private static String getSeqPanel(TableInfoForm info) {
		StringBuffer sb = new StringBuffer();
		int labelWidth 		 = 120;
		SequenceInfoForm infoForm = info.getSequenceInfo();
		String labelName 	= "SEQUENCE NAME";
		String context 		= Objects.isNull(infoForm) ? "" : infoForm.getSeqName();
		String component = context().narrowWithLabel(labelWidth, labelName, context, CssAlignType.LEFT);
		sb.append(divRow().narrow(CellWidthType.ONE, component));
		labelName 	= "SEQUENCE START";
		context 		= Objects.isNull(infoForm) ? "" : String.valueOf(infoForm.getStart());
		String component2 = context().narrowWithLabel(labelWidth, labelName, context, CssAlignType.LEFT);

		sb.append(divRow().narrow(CellWidthType.ONE, component2));
		//-----------------row 2----------------]
		
		return borderCard().noTitleNoScroll("", CssClassType.WARNING, 
				"",  
				divContainer().get(sb.toString()));
	}

	private static String getColumnsData(List<ColumnInfoForm> columnInfoList) {
	    CssGridsType[] grids = new CssGridsType[] {
	            CssGridsType.G1, CssGridsType.G3, CssGridsType.G2, 
	            CssGridsType.G1, CssGridsType.G3, CssGridsType.G1, CssGridsType.G1};
		int index = 0;
		//head
		TrSet headTr = tr().head(CssClassType.INFO);
		// --[
		// --col1--
		headTr.addTh(th().index(grids[index++]));
		// --col2--
		String context = "PHYSIC NAME";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col2--
		context = "LOGIC NAME";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col3--
		context			= "PK";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col3--
		context			= "TYPE";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col5--
		context			= "NUL";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col6--
		context			= "DFT";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --]

		//body
		List<TrSet> bodyList = new ArrayList<>();
		for(int i=0; i<columnInfoList.size(); i++) {
			index = 0;
			ColumnInfoForm cols = columnInfoList.get(i);
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("rowDataKey", cols.getName());
			TrSet tr = cols.getPkFlg() ? tr().row(properties, CssClassType.DANGER) : tr().row(properties);
			// --col1--
			context = String.valueOf((i+1));
			tr.addTd(td().index(TABLE_TD_HEIGHT, grids[index++], context));
			// --col2--
			context = cols.getName();
			tr.addTd(td().get  (TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			// --col3--
			context = cols.getLabelName();
			tr.addTd(td().subWithTrim(TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			// --col4--
			context = cols.getPkFlg() ? "YES" : "";
			tr.addTd(td().get  (TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			// --col5--
			context = DbTableUtil.getTypeSql(cols);
			tr.addTd(td().get  (TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			// --col6--
			context = cols.getNullable() ? "YES" : "";
			tr.addTd(td().get  (TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			// --col7--
			context = cols.getDefaultValue();
			tr.addTd(td().get  (TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			
			bodyList.add(tr);
		}

		return table().get(COLUMNS_TABLE_CARD_BODY_ID, COLUMNS_TABLE_BODY_HEIGHT, 
				headTr, bodyList);
	}
	
	private static String getIndexsData(List<IndexInfoForm> indexInfoList) {
	    CssGridsType[] grids = new CssGridsType[] {CssGridsType.G1, CssGridsType.G5, CssGridsType.G6};
		int index = 0;
		//head
		TrSet headTr = tr().head(CssClassType.WARNING);
		// --[
		// --col1--
		headTr.addTh(th().index(grids[index++]));
		// --col2--
		String context = "INDEX NAME";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));
		// --col2--
		context = "INDEX COLUMN";
		headTr.addTh(th().get(grids[index++], CssAlignType.CENTER, context));

		//body
		List<TrSet> bodyList = new ArrayList<>();
		for(int i=0; i<indexInfoList.size(); i++) {
			index = 0;
			IndexInfoForm indexInfo = indexInfoList.get(i);
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("rowDataKey", indexInfo.getIndexName());
			TrSet tr = tr().row(properties);
			// --col1--
			context = String.valueOf((i+1));
			tr.addTd(td().index(TABLE_TD_HEIGHT, grids[index++], context));
			// --col2--
			context = indexInfo.getIndexName();
			tr.addTd(td().get(TABLE_TD_HEIGHT,   grids[index++], CssAlignType.LEFT, context));
			// --col3--
			context = String.join(",", indexInfo.getColumnNames());
			tr.addTd(td().get(TABLE_TD_HEIGHT,   grids[index++], CssAlignType.LEFT, context));
			
			bodyList.add(tr);
		}

		return table().get(INDEXS_TABLE_CARD_BODY_ID, INDEXS_TABLE_BODY_HEIGHT, 
				 headTr, bodyList);
	}

	private static String getManagePageBottomButtonRow() {
		StringBuffer sb = new StringBuffer();
		List<CssAlignType> aligs = new ArrayList<>();
		//bottom button 
	
		String id 				= "btnAddTableClose";
		String context = getContext("common.page.btn.close");
		String comp2 = button().close(id, context);
		aligs.add(CssAlignType.RIGHT);
		sb.append(divRow().get(CellWidthType.ONE, aligs, comp2));
		
		return sb.toString();
	}
	
}
