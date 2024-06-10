package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.CardSet;
import cn.caam.gs.common.html.element.CardSet.CardSetType;

@Component
public class HtmlBorderCardHelper extends HtmlBaseHelper {

    public String noTitleNoScroll(String id, CssClassType type, String bodyId, String... contexts) {
        return CardSet.builder().id(id).outPutType(CardSetType.BORDER_NO_TITLE_NO_SCROLL)
                .classType(type).bodyId(bodyId)
                .contexts(contexts).build().html();
    }
    public String withTitleNoScroll(String id, CssClassType type, String bodyId, String title, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BORDER_TITLE_NO_SCROLL)
        .classType(type).id(id).bodyId(bodyId).title(title)
        .contexts(contexts).build().html();
    }
    public String withTitleWithScroll(String id, CssClassType type, String bodyId, String title, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BORDER_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).title(title)
        .contexts(contexts).build().html();
    }
    public String withTitleWithScroll(String id, CssClassType type, String bodyId, String title, int cardHeight, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BORDER_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).height(cardHeight).title(title)
        .contexts(contexts).build().html();
    }
    
    public String noTitleWithScroll(String id, CssClassType type, String bodyId, int cardHeight, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BORDER_NO_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).height(cardHeight)
        .contexts(contexts).build().html();
    }
    
    public String noTitleWithScroll(String id, CssClassType type, String bodyId, int cardWidth,int cardHeight, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BORDER_NO_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).width(cardWidth).height(cardHeight)
        .contexts(contexts).build().html();
    }
    
    public String noTitleNoScrollBg(String id, CssClassType type, String bodyId, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BG_NO_TITLE_NO_SCROLL)
                .classType(type).id(id).bodyId(bodyId)
                .contexts(contexts).build().html();
    }
    public String withTitleNoScrollBg(String id, CssClassType type, String bodyId, String title, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BG_TITLE_NO_SCROLL)
        .classType(type).id(id).bodyId(bodyId).title(title)
        .contexts(contexts).build().html();
    }
    public String withTitleWithScrollBg(String id, CssClassType type, String bodyId, String title, int cardHeight, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BG_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).height(cardHeight).title(title)
        .contexts(contexts).build().html();
    }
    
    public String noTitleWithScrollBg(String id, CssClassType type, String bodyId, int cardHeight, String... contexts) {
        return CardSet.builder().outPutType(CardSetType.BG_NO_TITLE_SCROLL)
        .classType(type).id(id).bodyId(bodyId).height(cardHeight)
        .contexts(contexts).build().html();
    }
    public String forTab(String id, CssClassType type, String bodyId, String... contexts) {
        return CardSet.builder().id(id).outPutType(CardSetType.BORDER_FOR_TAB)
                .classType(type).bodyId(bodyId)
                .contexts(contexts).build().html();
    }
    
    public String forTabBg(String id, CssClassType type, String bodyId, String... contexts) {
        return CardSet.builder().id(id).outPutType(CardSetType.BG_FOR_TAB)
                .classType(type).bodyId(bodyId)
                .contexts(contexts).build().html();
    }
}
