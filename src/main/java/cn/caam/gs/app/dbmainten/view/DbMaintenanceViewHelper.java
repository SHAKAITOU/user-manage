package cn.caam.gs.app.dbmainten.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.UrlConstants;
import cn.caam.gs.app.dbmainten.form.DbMaintenanceForm;
import cn.caam.gs.common.enums.CellWidthType;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.util.UtilConstants;
import cn.caam.gs.domain.tabledef.DbGroupTableType;
import cn.caam.gs.domain.tabledef.DbTableType;

@Component
public class DbMaintenanceViewHelper extends HtmlViewBaseHelper {
	
	//init url
	public static final String URL_BASE = UrlConstants.DB;
	//init url
	public static final String URL_C_INIT = UrlConstants.INIT;
	
	public static final String URL_C_DETAIL = UrlConstants.DETAIL;
	//add regist url
	public static final String URL_C_ADD_REGIST = UrlConstants.ADD+UrlConstants.REGIST;
	public static final String URL_C_EDIT_INIT = UrlConstants.EDIT+UrlConstants.INIT;
	public static final String URL_C_INIT_DATA = UrlConstants.INIT+UrlConstants.DATA;
	public static final String URL_C_BACKUP_DATA = "/backupData";
	
	public static final String HTML_INDEX 				= "dbmainten/indexDb";
	public static final String HTML_DBMAINTENANCE 		= "dbmainten/dbMaintenance";
	public static final String FORM_NAME 		  = "dbTableInfo_form";
	
	public static final int HEAD_CARD_HEIGHT = 50;
	
	public static final String TABLE_CARD_BODY_ID = "dbTableInfoTable";
	public static int TABLE_CARD_HEIGHT = 455;
	public static int TABLE_BODY_HEIGHT = TABLE_CARD_HEIGHT - 120;
	
	public static int TABLE_TD_HEIGHT = 30;

	public static String getPage(int mediaHeight, DbMaintenanceForm dbMaintenanceForm) {
		StringBuffer sb = new StringBuffer();
		//hidden values
		sb.append(getPageHiddenData(dbMaintenanceForm));
		sb.append(getHeadPanel(dbMaintenanceForm));
		//company data table
		sb.append(getTableDataPanel(dbMaintenanceForm));
		//bottom button 
		sb.append(getManagePageBottomButtonRow());
		
		return getForm(FORM_NAME, divContainer().get(sb.toString()));
	}
	
	private static String getPageHiddenData(DbMaintenanceForm dbMaintenanceForm) {
		StringBuffer sb = new StringBuffer();
		String name = "selectTableName";
		String value = "";
		sb.append(hidden().get(name, value));
		
		return sb.toString();
	}
	
	private static String getHeadPanel(DbMaintenanceForm dbMaintenanceForm) {
		StringBuffer sb = new StringBuffer();
		int labelWidth 		 = 120;

		String labelName 	= "DB URL";
		String context 		= dbMaintenanceForm.getDbUrl();
		String component = context().withLabel(labelWidth, labelName, context, CssAlignType.LEFT);
		labelName 	= "SCHEMA";
		context 		= dbMaintenanceForm.getSchema();
		String component2 = context().withLabel(labelWidth, labelName, context, CssAlignType.LEFT);

		sb.append(divRow().narrow(CellWidthType.TWO_7_5, component, component2));
		//-----------------row 2----------------]
		
		return borderCard().noTitleNoScroll("", CssClassType.DANGER, 
				"",  
				divContainer().get(sb.toString()));
	}
	
	private static String getTableDataPanel(DbMaintenanceForm dbMaintenanceForm) {
		return borderCard().withTitleWithScroll("", CssClassType.INFO, "", 
				"TABLE LIST",  
				getTableData(dbMaintenanceForm));
	}
	
	private static String getTableData(DbMaintenanceForm dbMaintenanceForm) {
	    CssGridsType[] grids = new CssGridsType[] {
	            CssGridsType.G1, 
	            CssGridsType.G1, 
	            CssGridsType.G2, 
	            CssGridsType.G3, 
	            CssGridsType.G3, 
	            CssGridsType.G1, 
	            CssGridsType.G1};
		int index = 0;
		//head
		TrSet headTr = tr().head(CssClassType.INFO);
		// --[
		// --col1--
		headTr.addTh(th().index(grids[index++]));
		// --col2--
		String context = "SEQ";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		
		context = "GROUP";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		// --col2--
		context			= "TABLE PHYSIC NAME";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		// --col3--
		context = "TABLE LOGIC NAME";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		// --col5--
		context			= "DETAIL";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		// --col6--
		context			= "SABUN";
		headTr.addTh(th().get(grids[index++], context, CssAlignType.CENTER));
		// --]

		//body
		List<TrSet> bodyList = new ArrayList<>();
		for(int i=0; i<DbTableType.values().length; i++) {
			index = 0;
			DbTableType dbTableType = DbTableType.values()[i];
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("rowDataKey", dbTableType.getName());
			TrSet tr = tr().row(properties, DbGroupTableType.nameOf(dbTableType.getGroup()).getCssClassType());
			// --col1--
			context = String.valueOf((i+1));
			tr.addTd(td().index(TABLE_TD_HEIGHT, grids[index++], context));
			// --col2--
			context = dbTableType.getSeq();
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			// --col2--
			context = dbTableType.getGroup();
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			// --col3--
			context = dbTableType.getName();
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			// --col4--
			context = dbTableType.getComment();
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.LEFT, context));
			// --col5--
			context = button().forTable(IconSetType.DETAIL, CssClassType.INFO, "", dbTableType.getName(), "detail");
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			// --col6--
			context = button().forTable(IconSetType.BULLHORN, CssClassType.SUCCESS, "", dbTableType.getName(), "sabun");
			tr.addTd(td().get(TABLE_TD_HEIGHT, grids[index++], CssAlignType.CENTER, context));
			bodyList.add(tr);
		}

		return table().get(TABLE_CARD_BODY_ID, TABLE_BODY_HEIGHT, 
				headTr, bodyList);
	}
	
	private static String getManagePageBottomButtonRow() {
		StringBuffer sb = new StringBuffer();
		List<CssAlignType> aligs = new ArrayList<>();
		//bottom button 
	
		String id 				= "btnCreateDdl";
		String context = "一括DDL作成";
		String comp2 = button().get(CssClassType.INFO, id, context);
		comp2	+= UtilConstants.HTML_SPACE+UtilConstants.HTML_SPACE;
		id 				= "btnCreateInitData";
		context = "初期データ登録";
		comp2 += button().get(CssClassType.SUCCESS, id, context);
		
		comp2	+= UtilConstants.HTML_SPACE+UtilConstants.HTML_SPACE;
		id 				= "btnDownloadData";
		context = "データBackup";
		comp2 += button().get(CssClassType.DANGER, id, context);

		aligs.add(CssAlignType.RIGHT);
		sb.append(divRow().get(CellWidthType.ONE, aligs, comp2));
		
		return sb.toString();
	}
}
