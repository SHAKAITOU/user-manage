package cn.caam.gs.common.html;

import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.util.PaginationHolder;


@Component
public class HtmlPageLinkedHelper extends HtmlBaseHelper {

    public static String getPageLinkedHtml(PaginationHolder paginationHolder) {
        StringBuffer sb = new StringBuffer();
        if(Objects.nonNull(paginationHolder) && paginationHolder.getPageCount() > 1) {
            sb.append("<div class='row'>");
            sb.append("<div class='col-8 text-left'>");
            sb.append("<div class='btn-toolbar pull-left' role='toolbar' aria-label='Toolbar with button groups'>");
            sb.append("<div class='btn-group mr-2' role='group' aria-label='Second group'>");

            if(!paginationHolder.isFirstPage()) {
                sb.append("<button ");
                sb.append(" id='"+paginationHolder.getPageLinkIdPrefix()+"Prev'");
                sb.append(" type='button' class='btn btn_pageLink card-radius-leftRad btn-outline-info'>");
                sb.append("<i class='fa fa-caret-left' aria-hidden='true'></i>");
                sb.append("</button>");
            }
            for(int i=paginationHolder.getFirstLinkedPage(); i<=paginationHolder.getLastLinkedPage(); i++) {
                sb.append("<button ");
                sb.append(" id='"+paginationHolder.getPageLinkIdPrefix()+(i+1)+"' ");
                sb.append(" class='btn btn_pageLink btn-outline-info");
                if(paginationHolder.getPage() == i) {
                    sb.append(" active");
                }
                sb.append("' type='button'>");
                sb.append(i+1);
                sb.append("</button>");
            }
            
            if(!paginationHolder.isLastPage()) {
                sb.append("<button ");
                sb.append("id='"+paginationHolder.getPageLinkIdPrefix()+"Next'");
                sb.append("type='button' class='btn btn_pageLink card-radius-rightRad btn-outline-info'>");
                sb.append("<i class='fa fa-caret-right' aria-hidden='true'></i>");
                sb.append("</button>");
            }

            sb.append("</div>");
            sb.append("</div>");
            sb.append("</div>");
            sb.append("<div class='col-4 text-right'>");
            sb.append("<input type='hidden' ");
            sb.append(" id='"+paginationHolder.getPageLinkIdPrefix() + "Index'");
            sb.append(" name='"+paginationHolder.getPageLinkIdPrefix() + "Index'");
            sb.append(" value='"+paginationHolder.getPage()+"'>");
            sb.append("<span class='text-muted' style='font-size: 14px;padding: 0px 0px 0px 0px;'><strong>(第");
            sb.append((paginationHolder.getPage()+1));
            sb.append("页/总");
            sb.append((paginationHolder.getPageCount()));
            sb.append("页)</strong></span>");
            sb.append("</div>");
            sb.append("</div>");
        }
        return sb.toString();
    }
}
