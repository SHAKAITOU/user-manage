package cn.caam.gs.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.FileUtil;
import cn.caam.gs.domain.db.DBConfig;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalDbTableInfoMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;
import cn.caam.gs.manage.dbmaintenance.form.ColumnInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.IndexInfoForm;
import cn.caam.gs.manage.dbmaintenance.form.TableInfoForm;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends BaseService {
	
	@Autowired
	OptionalUserInfoMapper optionalUserInfoMapper;

	public List<UserInfo> getUserList(MUser user) {
    	return optionalUserInfoMapper.getUserList(user);
	}
}
