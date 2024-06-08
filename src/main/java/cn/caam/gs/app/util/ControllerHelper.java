package cn.caam.gs.app.util;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.bean.ViewData;

@Component
public class ControllerHelper {
	
	
	public static final String FOWARD_HTML = "forward";

	
	public static ModelAndView getModelAndView(String pageContext, String jsClassName) {
		return getModelAndView(pageContext, jsClassName, null);
	}
	
	public static ModelAndView getModelAndView(ViewData viewData) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("viewData", viewData);
		mav.setViewName(FOWARD_HTML);
		return mav;
	}
	
	
	public static ModelAndView getModelAndView(String pageContext, String viewName, Map<String, ?> allObjects) {
		ModelAndView mav = new ModelAndView();
		if(Objects.nonNull(allObjects)) {
			mav.addAllObjects(allObjects);
		}
		mav.addObject("pageContext", pageContext);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	public static boolean isMobileDisplay(HttpServletRequest request) {
		if(request.getSession().getAttribute(SessionConstants.IS_MOBILE.getValue()) != null) {
			return Boolean.parseBoolean(
					request.getSession().getAttribute(SessionConstants.IS_MOBILE.getValue()).toString());
		}
		
		return false;
	}
}
