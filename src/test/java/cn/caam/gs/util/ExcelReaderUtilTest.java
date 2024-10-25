package cn.caam.gs.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.TestConstant;
import cn.caam.gs.app.util.UserImportUtil;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.mapper.OptionalFixedValueInfoMapper;

@SpringJUnitConfig(locations = {TestConstant.TEXT_CONTEXT_PATH})
@TestMethodOrder(OrderAnnotation.class)
//rollback when test end
@Transactional
public class ExcelReaderUtilTest {

    // モックを設定するテスト対象クラス
    
    @Autowired
    OptionalFixedValueInfoMapper optionalFixedValueInfoMapper;
    
   
    @Test
    @Order(1)
    void test01_select_all() throws Exception {
      
        List<MFixedValue> result = optionalFixedValueInfoMapper.selectByName("F0000", "超级管理员");
        System.out.println("value="+result.get(0).getValue());
        assertThat(result.size() > 0, is(true));
    }
    
    @Test
    @Order(2)
    void test02_excel_reader() {
    	try {
    		UserImportUtil excelReaderUtil = new UserImportUtil(null );
//    		File file = new File("C:\\work_gs\\document\\甘肃省针灸学会汇总表.xlsx");
    		File file = new File("C:\\work_gs\\document\\甘肃省针灸学会汇总表 - 301件.xlsx");
    		excelReaderUtil.readExcel(file);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
