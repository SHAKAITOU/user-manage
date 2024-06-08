package cn.caam.gs.service;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.common.util.BeanUtility;
import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.custom.entity.LoginResult;

@Service
public class BaseService {

	protected LoginResult loginResult;
	
	@Autowired
	protected MessageSourceUtil messageSourceUtil;
	
	protected String getContext(String key) {
		return messageSourceUtil.getContext(key);
	}
	
	protected void signInsertMark(Object entity) {
		//common item
		String dt = DateUtility.getCurrentDateTime();
		BeanUtility.invokeSetter(entity, "createId", 0);
		BeanUtility.invokeSetter(entity, "createDt", dt);
		BeanUtility.invokeSetter(entity, "updateId", 0);
		BeanUtility.invokeSetter(entity, "updateDt", dt);
	}
	
	protected void signInsertMark(HttpServletRequest request, Object entity) {
		//common item
		String dt = DateUtility.getCurrentDateTime();
		BeanUtility.invokeSetter(entity, "createId", getLoginUserId(request));
		BeanUtility.invokeSetter(entity, "createDt", dt);
		BeanUtility.invokeSetter(entity, "updateId", getLoginUserId(request));
		BeanUtility.invokeSetter(entity, "updateDt", dt);
	}
	
	protected void signUpdateMark(HttpServletRequest request, Object entity) {
		//common item
		String dt = DateUtility.getCurrentDateTime();
		BeanUtility.invokeSetter(entity, "updateId", getLoginUserId(request));
		BeanUtility.invokeSetter(entity, "updateDt", dt);
	}
	
	protected void signUpdateMark(Object entity) {
		//common item
		String dt = DateUtility.getCurrentDateTime();
		BeanUtility.invokeSetter(entity, "updateId", 0);
		BeanUtility.invokeSetter(entity, "updateDt", dt);
	}
	
	protected String getLoginUserId(HttpServletRequest request) {
		if(Objects.isNull(getLoginResult(request))) {
			return "";
		}
		
		return getLoginResult(request).getUser().getId();
	}
	protected LoginResult getLoginResult(HttpServletRequest request) {
		return LoginInfoHelper.getLoginResult(request);
	}
}
