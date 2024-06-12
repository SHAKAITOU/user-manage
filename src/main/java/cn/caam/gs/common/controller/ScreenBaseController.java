package cn.caam.gs.common.controller;

import org.springframework.beans.factory.annotation.Autowired;

import cn.caam.gs.common.util.MessageSourceUtil;

public abstract class ScreenBaseController extends BaseController {

    @Autowired
    protected MessageSourceUtil messageSourceUtil;
    
    protected String getContext(String key) {
        return messageSourceUtil.getContext(key);
    }
}
