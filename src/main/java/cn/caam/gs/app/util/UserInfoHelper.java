package cn.caam.gs.app.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import cn.caam.gs.app.common.view.UserDetailViewHelper;
import cn.caam.gs.common.enums.DownloadFileType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.enums.ValidType;
import cn.caam.gs.common.util.CertificateCreateUtil;
import cn.caam.gs.common.util.CertificateCreateUtil.AligntType;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.QRCodeUtil;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T101MUserExtend;
import cn.caam.gs.domain.tabledef.impl.T105MUserCard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInfoHelper {

	public static boolean isUserValid(MUser user) {
		if (!Objects.isNull(user) && 
				ValidType.VALID.getKey().equals(user.getValidStatus()) &&
				(Strings.isBlank(user.getCheckStatus()) || UserCheckStatusType.PASS.getKey().equals(user.getCheckStatus()))) {
			return true;
		}
		
		return false;
	}
	
	 public static List<DownloadFileType> getCertficateImageList(UserType userType){
	    	List<DownloadFileType> list = new ArrayList<DownloadFileType>();
	    	if (userType == UserType.PERSON_CHINA) {
	    		list.add(DownloadFileType.USER_CHINA_CERTIFICATE_1);
	    		list.add(DownloadFileType.USER_CHINA_CERTIFICATE_2);
	    		list.add(DownloadFileType.USER_CHINA_CERTIFICATE_3);
	    		list.add(DownloadFileType.USER_CHINA_CERTIFICATE_4);
	    		
	    	}else if (userType == UserType.PERSON_GANSU) {
	    		list.add(DownloadFileType.USER_GANSU_CERTIFICATE_1);
	    		list.add(DownloadFileType.USER_GANSU_CERTIFICATE_2);
	    		list.add(DownloadFileType.USER_GANSU_CERTIFICATE_3);
	    		list.add(DownloadFileType.USER_GANSU_CERTIFICATE_4);
	    		
	    	}
	    	
	    	return list;
	    }
	    
    public static Map<String, CertificateCreateUtil.DrawOject> getMapDrawObject(HttpServletRequest request, UserInfo userInfo, DownloadFileType downloadFileType){
    	Map<String, CertificateCreateUtil.DrawOject> data = new HashMap<String, CertificateCreateUtil.DrawOject>();
    	if (downloadFileType == DownloadFileType.USER_CHINA_CERTIFICATE_3 ||
    			downloadFileType == DownloadFileType.USER_GANSU_CERTIFICATE_3) {
    		String key = T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE).getName();
        	String value = nonNull(userInfo.getUserCode());
        	data.put(key, new CertificateCreateUtil.DrawOject(390, 800, 740, AligntType.LEFT, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_REGIST_DATE).getName();
        	value = LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getRegistDate()));
        	data.put(key, new CertificateCreateUtil.DrawOject(390, 700, 860, AligntType.LEFT, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getName();
        	value = LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getValidEndDate()));
        	data.put(key, new CertificateCreateUtil.DrawOject(390, 700, 988, AligntType.LEFT, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_NAME).getName();
        	value = nonNull(userInfo.getUser().getName());
        	data.put(key, new CertificateCreateUtil.DrawOject(1175, 1490, 314, AligntType.CENTER, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_SEX).getName();
        	value = nonNull(userInfo.getSexName());
        	data.put("sex", new CertificateCreateUtil.DrawOject(1615, 1792, 314, AligntType.CENTER, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_BIRTH).getName();
        	value = nonNull(userInfo.getUser().getBirth());
        	data.put(key, new CertificateCreateUtil.DrawOject(1280, 1792, 517, AligntType.CENTER, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER).getName();
        	value = nonNull(userInfo.getUser().getEmployer());
        	data.put(key, new CertificateCreateUtil.DrawOject(1280, 1806, 733, AligntType.CENTER, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_POSITION).getName();
        	value = nonNull(userInfo.getPositionName());
        	data.put(key, new CertificateCreateUtil.DrawOject(1280, 1806, 946, AligntType.CENTER, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getName();
        	data.put(key, new CertificateCreateUtil.DrawOject(1280, 1806, 1158, AligntType.CENTER, "普通会员"));
        	
        	try {
        		if (userInfo.getUserExtend().getPhoto() != null && 
        				userInfo.getUserExtend().getPhoto().length > 0) {
    				key = T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO).getName();
    				data.put(key, new CertificateCreateUtil.DrawOject(344, 284, 273, 350, userInfo.getUserExtend().getPhoto()));
        		}
        	}catch(Exception ex) {
        		log.error(ex.getMessage(), ex);
	    		ex.printStackTrace();
        	}
        	
        	try {
        		String qrCodeUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        		qrCodeUrl += UserDetailViewHelper.URL_BASE + UserDetailViewHelper.URL_USER_DETAIL_MEMBER_INFO+"/"+userInfo.getUser().getId();
//        		qrCodeUrl = "https://vip.caam.cn/mobile/#/memberInfo?memberId=7a5ff43c-dda5-4863-9603-49106e66252c";
        		byte[] image = QRCodeUtil.createQRCode(qrCodeUrl, "png", 400, 400);
        		data.put("qrcode", new CertificateCreateUtil.DrawOject(411, 1026, 200, 200, image));
        	}catch(Exception ex) {
        		log.error(ex.getMessage(), ex);
	    		ex.printStackTrace();
        	}
    	}else if (downloadFileType == DownloadFileType.USER_CHINA_CERTIFICATE_4 ||
    			downloadFileType == DownloadFileType.USER_GANSU_CERTIFICATE_4) {
    		String key = T100MUser.getColumnInfo(T100MUser.COL_VALID_START_DATE).getName();
//        	String value = LocalDateUtility.formatDateZH(nonNull(nonNull(userInfo.getUser().getValidStartDate())));
    		String value = LocalDateUtility.formatDateZH(nonNull(nonNull(userInfo.getUser().getRegistDate())));
        	data.put(key, new CertificateCreateUtil.DrawOject(165, 430, 510, AligntType.LEFT, value));
        	
        	key = T100MUser.getColumnInfo(T100MUser.COL_VALID_END_DATE).getName();
        	value = LocalDateUtility.formatDateZH(nonNull(nonNull(userInfo.getUser().getValidEndDate())));
        	data.put(key, new CertificateCreateUtil.DrawOject(540, 800, 510, AligntType.LEFT, value));
    	}
    	
    	return data;
    }
    
    public static byte[] drawCertImage(String path, Map<String, CertificateCreateUtil.DrawOject> data) {
    	byte[] bytes= null;
    	if (!Strings.isBlank(path)) {
	    	try {
		    	 // 加载模板图片
           		ResourceLoader resourceLoader = new DefaultResourceLoader();
       	        InputStream inputStream = resourceLoader.getResource(path).getInputStream();
		        BufferedImage image = ImageIO.read(inputStream);
		    	CertificateCreateUtil.draw(image, data);
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write(image, "png", baos);
		        bytes = baos.toByteArray();
		        baos.close();
	    	}catch(Exception ex) {
	    		log.error(ex.getMessage(), ex);
	    		ex.printStackTrace();
	    	}
    	}
    	
    	return bytes;
    }
    
    public static String nonNull(String value) {
        return Objects.nonNull(value) ? value : "";
    }
	    
}
