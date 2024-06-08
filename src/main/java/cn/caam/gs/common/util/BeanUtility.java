package cn.caam.gs.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.modelmapper.ModelMapper;

import cn.caam.gs.common.exception.ShaException;



public class BeanUtility {

    /**
     * The ObjectMapper instance.
     */
    private static ModelMapper modelMapper;
    
    static {
    	modelMapper = new ModelMapper();
    }
    
    public static void map(Object source, Object destination) {
    	try {
    		modelMapper.map(source, destination);
    	} catch (Exception e) {
    		 throw new ShaException(e);
    	}
    }
    
    public static void invokeSetter(Object orgBean, String setterName, Object value) {
    	try {
			PropertyDescriptor nameProp = new PropertyDescriptor(setterName, orgBean.getClass());
			Method nameSetter = nameProp.getWriteMethod();
			nameSetter.invoke(orgBean, value);
		} catch (IntrospectionException e) {
			throw new ShaException(e);
		} catch (IllegalAccessException e) {
			throw new ShaException(e);
		} catch (IllegalArgumentException e) {
			throw new ShaException(e);
		} catch (InvocationTargetException e) {
			throw new ShaException(e);
		}
    }
    
    public static Object invokeGetter(Object orgBean, String getterName) {
    	try {
			PropertyDescriptor nameProp = new PropertyDescriptor(getterName, orgBean.getClass());
			Method nameGetter = nameProp.getReadMethod();
			return nameGetter.invoke(orgBean);
		} catch (IntrospectionException e) {
			throw new ShaException(e);
		} catch (IllegalAccessException e) {
			throw new ShaException(e);
		} catch (IllegalArgumentException e) {
			throw new ShaException(e);
		} catch (InvocationTargetException e) {
			throw new ShaException(e);
		}
    }
}

