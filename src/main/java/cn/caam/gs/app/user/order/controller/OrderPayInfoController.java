package cn.caam.gs.app.user.order.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.user.order.view.OrderPayInfoHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MSystemConfig;
import cn.caam.gs.domain.db.base.mapper.MSystemConfigMapper;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=OrderPayInfoHelper.URL_BASE)
public class OrderPayInfoController extends JcbcBaseController{
    
    @Autowired
    MSystemConfigMapper systemConfigMapper;
    
    @PostMapping(path=OrderPayInfoHelper.URL_C_INIT)
	public ModelAndView init(
			@RequestParam("payType") String payType,
			HttpServletRequest request,
			HttpServletResponse response) {
    	MSystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(GlobalConstants.M_SYSTEM_CONFIG_PRIMARY_KEY);
		return ControllerHelper.getModelAndView(
				OrderPayInfoHelper.getMainPage(request, payType, systemConfig));
	}
	
	
}
