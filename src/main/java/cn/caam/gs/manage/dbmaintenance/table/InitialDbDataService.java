package cn.caam.gs.manage.dbmaintenance.table;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import cn.caam.gs.service.BaseService;

@Service
@AllArgsConstructor
public class InitialDbDataService extends BaseService {


    public void initDbData(HttpServletRequest request) {
    	initCompanyData(request);
    }
    
    private void initCompanyData(HttpServletRequest request) {
    	
    }
    
}
