package cn.caam.gs.app.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.caam.gs.common.bean.BreadCrumbData;
import cn.caam.gs.common.html.HtmlSelectHelper;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.TableListCard;


@Component
public class HtmlViewHelper extends HtmlViewBaseHelper {
	
	@Autowired
	private HtmlSelectHelper htmlSelectHelper0;
	private static HtmlSelectHelper htmlSelectHelper;
	
	@Autowired
	private HtmlEnumHelper htmlEnumHelper0;
	private static HtmlEnumHelper htmlEnumHelper;
	
	@PostConstruct
	private void initStaticDao () {
		htmlSelectHelper = htmlSelectHelper0;
		htmlEnumHelper = htmlEnumHelper0;
	}
	
	//--------------------------------------------------------------------------------------------------------
	//enum span
	//--------------------------------------------------------------------------------------------------------
	public static HtmlEnumHelper enumSpanC() {
		return htmlEnumHelper;
	}
	
	//--------------------------------------------------------------------------------------------------------
	//select
	//--------------------------------------------------------------------------------------------------------
	public static HtmlSelectHelper select() {
		return htmlSelectHelper;
	}
	
	//--------------------------------------------------------------------------------------------------------
	//util func
	//--------------------------------------------------------------------------------------------------------
	public static BreadCrumbData setMenuBreadCrumbData(boolean active) {
		return new BreadCrumbData(active, "menuBC", getContext("menu.menuSelect"), "");
	}
	
	public static BreadCrumbData setBidBreadCrumbData(boolean active) {
		return setBidBreadCrumbData(active, null);
	}
	
	public static BreadCrumbData setBidBreadCrumbData(boolean active, String bidName) {
		String title = getContext("bid.title");
		if (StringUtils.isNotEmpty(bidName)) {
			title += "[" + bidName + "]";
		}
		return new BreadCrumbData(active, "bidBC", title, "");
	}
	
	public static BreadCrumbData setCtgryBreadCrumbData(boolean active) {
		return setCtgryBreadCrumbData(active, null);
	}
	
	public static BreadCrumbData setCtgryBreadCrumbData(boolean active, String ctgryName) {
		String title = getContext("ctgry.title");
		if (StringUtils.isNotEmpty(ctgryName)) {
			title += "[" + ctgryName + "]";
		}
		return new BreadCrumbData(active, "ctgryBC", title, "");
	}
	
	public static BreadCrumbData setKindBreadCrumbData(boolean active) {
		return new BreadCrumbData(active, "kindBC", getContext("kind.title"), "");
	}
	
	public static String getBreadCrumb(String... context) {
		List<BreadCrumbData> breadCrumbDatas = new ArrayList<BreadCrumbData>();
		for (String con : context) {
			breadCrumbDatas.add(new BreadCrumbData(true, "", con, ""));
		}
		return getBreadCrumb(breadCrumbDatas);
	}
	
	public static String getBreadCrumb(List<BreadCrumbData> breadCrumbDatas) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ol class='breadcrumb'>");
		for (int i=0; i<breadCrumbDatas.size(); i++) {
			if (breadCrumbDatas.get(i).isActvie()) {
				sb.append("<li class='label-14b active ");
				if(StringUtils.isNotEmpty(breadCrumbDatas.get(i).getClassName())) {
					sb.append(breadCrumbDatas.get(i).getClassName());
				}
				sb.append("'>");
				sb.append(breadCrumbDatas.get(i).getName());
				sb.append("</li>");
			} else {
				sb.append("<li class='label-14b ");
				if(StringUtils.isNotEmpty(breadCrumbDatas.get(i).getClassName())) {
					sb.append(breadCrumbDatas.get(i).getClassName());
				}
				sb.append("'>");
				sb.append("<a ");
				sb.append(" id='").append(breadCrumbDatas.get(i).getId()).append("'");
				sb.append(" href='#'>");
				sb.append(breadCrumbDatas.get(i).getName());
				sb.append("</a>");
				sb.append("</li>");
			}
		}
		sb.append("</ol>");
		return sb.toString();
	}
	
	
	public static String getCardTableListBody(List<?> list, TableListCard tableListCard, int paddingHeight) {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		String[] components = new String[tableListCard.getMaxCol()];
		for(int i=0; i<components.length; i++) {
			components[i] = "";
		}
		for(Object obj : list) {
			
			components[index%tableListCard.getMaxCol()] = tableListCard.getTableListCard(index, obj);
			if(index%tableListCard.getMaxCol() == (components.length-1)) {
				sb.append(divRow().withColumns(components));
				sb.append(divRow().cellBlank(paddingHeight));
			}
			index++;
		}
		
		if(index > 0 && index%tableListCard.getMaxCol() > 0) {
			for(int i=index%tableListCard.getMaxCol(); i<components.length; i++) {
				components[i] = "";
			}
			sb.append(divRow().withColumns(components));
			sb.append(divRow().cellBlank(paddingHeight));
		}

		return sb.toString();
	}
	
	public static String convertTextAreaContext(String textArea) {
	    String preConStr = textArea.replaceAll("\r\n", "\n");
	    preConStr = preConStr.replaceAll("\r", "\n");
	    String[] chips = textArea.split("\n");
	    String context = "";
	    for (String chip : chips) {
	        context += chip + "<BR>";
	    }
	    return context;
	}
	
	public static boolean isPhoneMode(HttpServletRequest request) {
	    int outWidth = LoginInfoHelper.getMediaWidth(request);
	    return outWidth > 800 ? false : true;
	}
    
	public static int calcMaxCardWidth(HttpServletRequest request) {
	    int outWidth = LoginInfoHelper.getMediaWidth(request);
	    if (outWidth > 1200) {
	        return LoginInfoHelper.getMediaWidth(request) - 50;
	    } else {
	        return LoginInfoHelper.getMediaWidth(request) - 10;
	    }
        
    }
}
