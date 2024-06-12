package cn.caam.gs.service.impl;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.app.form.RegistForm;
import cn.caam.gs.app.user.view.RegistStep1ViewHelper;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.base.mapper.MAuthCodeMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthCodeService extends BaseService {
	
	@Autowired
	MAuthCodeMapper mauthCodeMapper;

	public MAuthCode addAuthCode(RegistForm pageForm) {
	    MAuthCode mauthCode = new MAuthCode();
	    String uuid = UUID.randomUUID().toString();
	    mauthCode.setId(uuid);
	    mauthCode.setAuthMethod(pageForm.getAuthMethod());
	    if (mauthCode.getAuthMethod().equals(RegistStep1ViewHelper.GET_AUTH_CODE_BY_PHONE)) {
	        mauthCode.setRecievedBy(pageForm.getUser().getPhone());
	    } else {
	        mauthCode.setRecievedBy(pageForm.getUser().getMail());
	    }
	    mauthCode.setAuthCode(getRandomAuthCode());
	    LocalDateTime expiredDt = LocalDateTime.now();
	    mauthCode.setInvalidDate(LocalDateUtility.formatDateTime(expiredDt.plusMinutes(10l), DateTimePattern.UUUUMMDDHHMISS));
	    mauthCodeMapper.insert(mauthCode);
    	return mauthCodeMapper.selectByPrimaryKey(uuid);
	}
	
	private String getRandomAuthCode() {
	    int length = 6;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        
        return randomString.toString();
	}
}
