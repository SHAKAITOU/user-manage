package cn.caam.gs.common.html;

import java.util.Objects;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.util.PaginationHolder;


@Component
public class HtmlImagePageLinkedHelper extends HtmlBaseHelper {

    public static String getPageLinkedHtml(PaginationHolder paginationHolder) {
        StringBuffer sb = new StringBuffer();
        if(Objects.nonNull(paginationHolder) && paginationHolder.getPageCount() > 1) {
            sb.append("<div class='row'>");
            sb.append("<div class='container'>");
            sb.append("<div class='text-center' role='toolbar' aria-label='Toolbar with button groups'>");
            sb.append("<div class='btn-group mr-2' role='group' aria-label='Second group'>");

            sb.append("<button ");
            sb.append(" id='"+paginationHolder.getPageLinkIdPrefix()+"Prev'");
            sb.append(" type='button' class='btn btn_pageLink card-radius-leftRad btn-outline-info'>");
            sb.append("<i class='fa fa-caret-left' aria-hidden='true'></i>");
            sb.append("</button>");
            
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
            
            
            sb.append("<button ");
            sb.append("id='"+paginationHolder.getPageLinkIdPrefix()+"Next'");
            sb.append("type='button' class='btn btn_pageLink card-radius-rightRad btn-outline-info'>");
            sb.append("<i class='fa fa-caret-right' aria-hidden='true'></i>");
            sb.append("</button>");

            sb.append("</div>");
            sb.append("</div>");
            sb.append("</div>");
            sb.append("</div>");
        }
        return sb.toString();
    }
}
