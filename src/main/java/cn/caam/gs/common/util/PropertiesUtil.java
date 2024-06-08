package cn.caam.gs.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import cn.caam.gs.common.exception.ShaException;

public class PropertiesUtil {
	
	@Autowired
	private LogCommonUtil log;
	
	private ResourceBundle bundle;


	public PropertiesUtil(InputStream input) {
		try {
			bundle = new PropertyResourceBundle(input);
		} catch (IOException e) {
			log.error("プロパティの取得クラス初期化", "InputStream I/O error.", e);
			throw new ShaException(e.getMessage());
		}
	}
	
	public PropertiesUtil(File file) {
		try {
			bundle = new PropertyResourceBundle(new FileInputStream(file));
		} catch (IOException e) {
			log.error("プロパティの取得クラス初期化", "InputStream I/O error.", e);
			throw new ShaException(e.getMessage());
		}
	}
	
	public String getProperty(String key) {
		String test = bundle.getString(key);
		try {
			test = new String( test.getBytes("8859_1"),"UTF-8");

		} catch (UnsupportedEncodingException e) {
			log.error("プロパティの取得", "The Character Encoding is not supported.", e);
			throw new ShaException(e.getMessage());
		}

		return test;
	}
	
	public Enumeration<String> getProperties() {
		return bundle.getKeys();
	}
	
	public boolean containsKey(String key) {
		return bundle.containsKey(key);
	}
}

