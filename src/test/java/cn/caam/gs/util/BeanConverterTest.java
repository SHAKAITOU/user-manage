package cn.caam.gs.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import cn.caam.gs.common.util.BeanConverter;
import lombok.Getter;
import lombok.Setter;

@TestMethodOrder(OrderAnnotation.class)
public class BeanConverterTest {
    
    /** ログ出力用Logger宣言 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private static final String TESTBEAN1_1_STR = "{\"item1\":\"111\",\"item2\":\"222\"}";
    private static final String TESTBEAN1_2_STR = "{\"item1\":\"333\",\"item2\":\"444\"}";
    private static final String TESTBEAN2_1_STR = "{\"item3\":\"111\",\"item4\":\"222\"}";
    private static final String TESTBEAN2_2_STR = "{\"item3\":\"333\",\"item4\":\"444\"}";
    
    private static final String TESTBEAN1_LIST_STR = "[" + TESTBEAN1_1_STR + "," + TESTBEAN1_2_STR + "]";
    private static final String TESTBEAN2_LIST_STR = "[" + TESTBEAN2_1_STR + "," + TESTBEAN2_2_STR + "]";
    
    private static final String TESTBEAN_STR = "{" 
                                                + "\"bean1\":" + TESTBEAN1_1_STR + ","
                                                + "\"bean2\":" + TESTBEAN2_1_STR + ","
                                                + "\"bean1List\":" + TESTBEAN1_LIST_STR + ","
                                                + "\"bean2List\":" + TESTBEAN2_LIST_STR + ","
                                                + "\"item5\":\"555\",\"item6\":\"666\""
                                            + "}";
    
    @Test
    @Order(1)
    void test01_toList() {
        List<TestBean1> rsList = BeanConverter.toList(TESTBEAN1_LIST_STR, TestBean1.class);
        assertThat(new Gson().toJson(rsList).equals(TESTBEAN1_LIST_STR),  is(true));
    }
    
    @Test
    @Order(2)
    void test02_toObject() {

        TestBean rs = BeanConverter.toObject(TESTBEAN_STR, TestBean.class);
        assertThat(new Gson().toJson(rs).equals(TESTBEAN_STR),  is(true));
    }
    
    @Test
    @Order(3)
    void test03_toJson() {

        TestBean testBean = BeanConverter.toObject(TESTBEAN_STR, TestBean.class);
        String rs = BeanConverter.toJson(testBean);
        assertThat(rs.equals(TESTBEAN_STR),  is(true));
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
    
    @Test
    @Order(8)
    void test08_listToString() throws Exception {
        File file = new File("C:\\shaWork\\work\\DNVE7XYCDP8D1669677758841.jpg");
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        ImageIO.write(image, "jpg", baos);
        LOGGER.info(Base64.encodeBase64String(baos.toByteArray()));
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
