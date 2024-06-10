package cn.caam.gs.util;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.caam.gs.common.util.BeanConverter;
import cn.caam.gs.common.util.MessageSourceUtil;
import lombok.Getter;
import lombok.Setter;

@TestMethodOrder(OrderAnnotation.class)
public class MessageSourceUtilTest {
    
    /** ログ出力用Logger宣言 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    MessageSourceUtil messageSourceUtil = new MessageSourceUtil();
    
    @Test
    @Order(1)
    void test01_toList() throws Exception {
        LOGGER.info(String.join(",", messageSourceUtil.getKeys()));
        JSONObject msgObj = new JSONObject();
        for (String key : messageSourceUtil.getKeys()) {
            msgObj.put(key, messageSourceUtil.getContext(key));
        }
        LOGGER.info(msgObj.toString());
    }
        
    @Test
    @Order(2)
    void test02_toObject() {

    }
    
    @Test
    @Order(3)
    void test03_toJson() {

    }

    @Test
    @Order(6)
    void test07_listToString() {
        List<String> list = new ArrayList<>();
        list.add("ssss");
        //list.add("aaaa");
        LOGGER.info(String.join(",", list));
        
        List<String> oldRoleList = Arrays.asList("".split(","));
        LOGGER.info(String.join(",", oldRoleList));
    }
    
    @Getter
    @Setter
    private class TestBean {
        private TestBean1 bean1;
        private TestBean2 bean2;
        private List<TestBean1> bean1List;
        private List<TestBean2> bean2List;
        private String item5;
        private String item6;
    }
    
    @Getter
    @Setter
    private class TestBean1 {
        private String item1;
        private String item2;
    }
    
    @Getter
    @Setter
    private class TestBean2 {
        private String item3;
        private String item4;
    }
    
    @Getter
    @Setter
    private class TestBean41 {
        private String item1;
        private String item2;
    }
    
    @Getter
    @Setter
    private class TestBean42 {
        private String item1;
        private String item2;
        private String item3;
    }
}
