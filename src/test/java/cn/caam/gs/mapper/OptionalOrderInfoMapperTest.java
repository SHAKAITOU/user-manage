package cn.caam.gs.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.TestConstant;
import cn.caam.gs.app.user.order.form.OrderSearchForm;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalOrderInfoMapper;

@SpringJUnitConfig(locations = {TestConstant.TEXT_CONTEXT_PATH})
@TestMethodOrder(OrderAnnotation.class)
//rollback when test end
@Transactional
public class OptionalOrderInfoMapperTest {

    // モックを設定するテスト対象クラス
    
    @Autowired
    OptionalOrderInfoMapper optionalOrderInfoMapper;
   
    @Test
    @Order(1)
    void test01_select_all() throws Exception {
        OrderSearchForm pageForm = new OrderSearchForm();
        MOrder order = new MOrder();
        order.setUserId("M24060914330223");
        pageForm.setOrder(order);
        List<OrderInfo> result = optionalOrderInfoMapper.getOrderList(pageForm);
        assertThat(result.size() > 0, is(true));
    }
}
