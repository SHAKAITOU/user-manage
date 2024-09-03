package cn.caam.gs.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.domain.db.base.entity.MSmsConfig;
import cn.caam.gs.domain.db.base.mapper.MSmsConfigMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SmsConfigService extends BaseService {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MSmsConfigMapper mSmsConfigMapper;
    
	public MSmsConfig getSmsConfig(String templateType) {
//		mSmsConfigMapper.sel
    	return null;
	}
	
	
}
