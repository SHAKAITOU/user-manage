package cn.caam.gs.app.dbmainten.controller;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import cn.caam.gs.app.dbmainten.form.DbMaintenanceForm;
import cn.caam.gs.app.dbmainten.form.TableInfoForm;
import cn.caam.gs.app.dbmainten.view.DbMaintenanceViewHelper;
import cn.caam.gs.app.dbmainten.view.TableDetailViewHelper;
import cn.caam.gs.app.dbmainten.view.TableSabunViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.ScreenBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.exception.ShaApiException;
import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.config.DBConfig;
import cn.caam.gs.domain.tabledef.DbTableType;
import cn.caam.gs.domain.tabledef.DbTableUtil;
import cn.caam.gs.domain.tabledef.InitialDbDataService;
import cn.caam.gs.service.impl.DbMaintenanceService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping(path=DbMaintenanceViewHelper.URL_BASE)
public class DbMaintenanceController extends ScreenBaseController{
	
	final static String CONTENT_DISPOSITION_FORMAT = 
			"attachment; filename=\"%s\"; filename*=UTF-8''%s";
	
	@Autowired
	MessageSourceUtil messageSourceUtil;
	
	@Autowired
	Environment environment;
	
    @Autowired
    ResourceLoader resourceLoader;
    
    @Autowired
    DbMaintenanceService dbMaintenanceService;
    
    @Autowired
    InitialDbDataService initialDbDataService;
	
	@Autowired
	DBConfig dbConfig;

	@GetMapping()
	public ModelAndView index(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response)  {

		ModelAndView mav = new ModelAndView();
		if (allRequestParams.get("admin") == null || !allRequestParams.get("admin").equals("echolly66")) {
			throw new ShaApiException("login failed!");
		}
		mav.setViewName(DbMaintenanceViewHelper.HTML_INDEX);
		return mav;
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_INIT)
	public ModelAndView init(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		DbMaintenanceForm dbMaintenanceForm = new DbMaintenanceForm();
		dbMaintenanceForm.setSabunFlgs(judgeSabun());
		dbMaintenanceForm.setDbUrl(dbConfig.getUrl());
		dbMaintenanceForm.setSchema(getDbSchema());
		
		return ControllerHelper.getModelAndView(
				DbMaintenanceViewHelper.getPage(Integer.valueOf(allRequestParams.get("mediaHeight")), dbMaintenanceForm),
				DbMaintenanceViewHelper.HTML_DBMAINTENANCE);
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_DETAIL)
	public ModelAndView detail(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {

		return ControllerHelper.getModelAndView(
				TableDetailViewHelper.getPage(DbTableType.nameOf(allRequestParams.get("selectTableName"))),
				TableDetailViewHelper.HTML_DETAIL);
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_EDIT_INIT)
	public ModelAndView showSabun(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		DbTableType type = DbTableType.nameOf(allRequestParams.get("selectTableName"));
		TableInfoForm dbTableInfoForm = dbMaintenanceService.getTableInfoForm(type.getName());
		String sabun = DbTableUtil.judgeDiff(getDbSchema(), type.getTableInfoForm(), dbTableInfoForm);
		return ControllerHelper.getModelAndView(
				TableSabunViewHelper.getPage(DbTableType.nameOf(allRequestParams.get("selectTableName")), sabun),
				TableSabunViewHelper.HTML_SABUN);
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_ADD_REGIST)
	@ResponseBody
	public Object createDdl(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		
		File ddlPath = resourceLoader.getResource("classpath:ddl").getFile();
		DbTableUtil.createAllDdl(getDbSchema(), ddlPath.getPath());
		
		return ExecuteReturnType.OK;
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_INIT_DATA)
	@ResponseBody
	public Object createInitData(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		
		initialDbDataService.initDbData(request);
		
		return ExecuteReturnType.OK;
	}
	
	@PostMapping(path=DbMaintenanceViewHelper.URL_C_BACKUP_DATA)
	@ResponseBody
	public ResponseEntity<InputStreamResource> backupData(
			@RequestParam Map<String,String> allRequestParams,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception  {

		MediaType mediaType = MediaType.APPLICATION_JSON;
		String fileName = DateUtility.getCurrentDateTime() + "data.zip";
		String headerValue =
				String.format(CONTENT_DISPOSITION_FORMAT,
						fileName,
						UriUtils.encode(fileName, StandardCharsets.UTF_8.name()));
		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + headerValue)
				// Content-Type
				.contentType(mediaType)
				// Contet-Length
				.body(dbMaintenanceService.backup());

	}

	private List<Boolean> judgeSabun(){
		List<Boolean> sabunFlg = new ArrayList<>();
		for(DbTableType type : DbTableType.values()) {
			TableInfoForm tableInfoForm = type.getTableInfoForm();
			TableInfoForm dbTableInfoForm = dbMaintenanceService.getTableInfoForm(tableInfoForm.getTableName());
			if(Objects.isNull(dbTableInfoForm)) {
				sabunFlg.add(true);
			} else {
				String sabun = DbTableUtil.judgeDiff(getDbSchema(), tableInfoForm, dbTableInfoForm);
				if(StringUtils.isNotBlank(sabun)) {
					sabunFlg.add(true);
				}else {
					sabunFlg.add(false);
				}
			}
		}
		
		return sabunFlg;
	}
	
	private String getDbSchema() {
		return "public";
	}
}
