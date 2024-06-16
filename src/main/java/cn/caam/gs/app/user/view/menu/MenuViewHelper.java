package cn.caam.gs.app.user.view.menu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.app.user.view.UserDetailViewHelper;
import cn.caam.gs.manage.util.HtmlViewHelper;

@Component
public class MenuViewHelper extends HtmlViewHelper {

    public static Map<String, String> getJsProperties() {
        Map<String, String> js = new HashMap<String, String>();
        // url
        js.put("url_user_detail_init",      UserDetailViewHelper.URL_BASE + UserDetailViewHelper.URL_USER_DETAIL_INIT);
        return js;
    }
}
