package cn.caam.gs.app.common.controller;


import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.view.OrderDetailViewHelper;
import cn.caam.gs.app.util.ControllerHelper;
import cn.caam.gs.common.controller.JcbcBaseController;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.mapper.MImageMapper;
import cn.caam.gs.service.impl.OrderService;
import lombok.AllArgsConstructor;

/**
 * Main menu controller.
 *
 */
@Controller
@AllArgsConstructor
@RequestMapping(path=OrderDetailViewHelper.URL_BASE)
public class OrderDetailController extends JcbcBaseController{
    
    @Autowired
    OrderService orderService;
    
    @Autowired
	MImageMapper mImageMapper;
	
    @PostMapping(path=OrderDetailViewHelper.URL_C_INIT)
	public ModelAndView init(
	        IdForm idForm,
			HttpServletRequest request,
			HttpServletResponse response) {

		return ControllerHelper.getModelAndView(
		        OrderDetailViewHelper.getMainPage(request, orderService.getOrder(idForm.getId())));
	}
    
    @GetMapping(path=OrderDetailViewHelper.URL_C_GET_NOT_FINISH_CNT)
    @ResponseBody
    public int[] getNotFinishCnt(
            HttpServletRequest request,
            HttpServletResponse response) {
        int orderWaitCnt = orderService.getOrderWaitCount();
        int orderReviewCnt = orderService.getOrderReviewCount();
        int [] cnts = new int[] {
            orderWaitCnt,
            orderReviewCnt   
        };
        return cnts;
    }
    
    @GetMapping(path=OrderDetailViewHelper.URL_INVOICE_DOWNLOAD+"/{id}")
    public void download(
    		@PathVariable("id") String id,
            HttpServletRequest request,
            HttpServletResponse response)  throws Exception {
    	MImage imageDb = mImageMapper.selectByPrimaryKey(id);
    	response.setHeader(HttpHeaders.PRAGMA, "No-cache");
	    response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
	    String filename = URLEncoder.encode(id+"_发票凭证."+imageDb.getBillPhotoExt(),"UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
	    response.setContentType("application/pdf;charset=UTF-8");
	    orderService.downloadInvoice(id, response.getOutputStream());
    }
}
