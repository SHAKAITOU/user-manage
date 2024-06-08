package cn.caam.gs.common.handler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class CustomerLocaleResolver  implements LocaleResolver {

    private Locale locale = Locale.JAPAN;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if(request.getParameterMap().containsKey("lang")) {
            locale = Locale.forLanguageTag(request.getParameterMap().get("lang")[0]);
        }
        return this.locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        if(locale != null) {
            this.locale = locale;
        }

    }

}

