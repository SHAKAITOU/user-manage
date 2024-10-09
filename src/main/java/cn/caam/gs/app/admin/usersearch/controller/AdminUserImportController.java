package cn.caam.gs.app.admin.usersearch.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.usersearch.form.AdminUserImportForm;
import cn.caam.gs.app.admin.usersearch.output.AdminUserImportResult;
import cn.caam.gs.app.admin.usersearch.view.AdminUserImportViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.app.util.UserImportUtil;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.ImportUserUpdateType;
import cn.caam.gs.common.exception.ShaException;
import cn.caam.gs.common.util.CommonUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.service.impl.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Main menu controller.
 *
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(path=AdminUserImportViewHelper.URL_BASE)
public class AdminUserImportController extends JcbcBaseController{
    
    @Autowired
    UserService userService;
	
	@PostMapping(path=AdminUserImportViewHelper.URL_C_INIT)
	public ModelAndView init(
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
				AdminUserImportViewHelper.getMainPage(request));
	}
	
	@PostMapping(path=AdminUserImportViewHelper.URL_C_IMPORT)
	@ResponseBody
    public AdminUserImportResult importFile(
    		AdminUserImportForm pageForm,
            HttpServletRequest request,
            HttpServletResponse response) {
		AdminUserImportResult importResult = new AdminUserImportResult();
		
		File savePath = new File(GlobalConstants.UPLODA_FILE_SAVE_PATH);
		if (!savePath.exists()) {
			savePath.mkdir();
		}
		
		String prefix = LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUMMDDHHMISS);
		File importfile = new File(GlobalConstants.UPLODA_FILE_SAVE_PATH+"/"+prefix+"_"+pageForm.getImportFile().getOriginalFilename());
		try {
			pageForm.getImportFile().transferTo(importfile);
		}catch(IOException e) {
			importResult.setExecuteReturnType(ExecuteReturnType.NG);
			importResult.setResult(messageSourceUtil.getContext("userImport.uploadFile.fail"));
		}
		UserImportUtil userImportUtil = new UserImportUtil(messageSourceUtil);
		try {
			List<UserInfo> userInfoList = userImportUtil.readExcel(importfile);
			List<AdminUserImportResult> list = userService.importUserInfo(request, userInfoList, ImportUserUpdateType.INSERT_ONLY);
			int insertCount = 0;
			StringBuilder sb = new StringBuilder();
			for(AdminUserImportResult result:list) {
				if (result.getExecuteReturnType() == ExecuteReturnType.OK) {
					insertCount++;
				}else {
					sb.append(result.getResult()+"\n");
				}
			}
			
			importResult.setExecuteReturnType(ExecuteReturnType.OK);
			importResult.setResult(messageSourceUtil.getContext("userImport.success.count.msg", String.valueOf(insertCount)) + "\n" + sb.toString());
		}catch(Exception e) {
			importResult.setExecuteReturnType(ExecuteReturnType.NG);
			importResult.setResult(e.getMessage());
		}
		
		importResult.setResult(importResult.getResult().replace("\n", "<BR>"));
		log.info(importResult.getResult());
		return importResult;
    }
	
}
