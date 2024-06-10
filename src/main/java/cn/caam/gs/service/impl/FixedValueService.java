package cn.caam.gs.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalFixedValueInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FixedValueService extends BaseService {
    
    /** ログ出力用Logger宣言 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	OptionalFixedValueInfoMapper optionalFixedValueInfoMapper;

	public Map<FixedValueType, List<FixValueInfo>> getMap() {
	    List<MFixedValue> valueList = optionalFixedValueInfoMapper.getAll();
	    Map<FixedValueType, List<FixValueInfo>> map = new HashMap<FixedValueType, List<FixValueInfo>>();
	    for (MFixedValue value : valueList) {
	        FixedValueType type = FixedValueType.keyOf(value.getCode());
	        if (value.getValue().length() <= 3) {
                if (!map.containsKey(type)) {
                    map.put(type, new ArrayList<FixValueInfo>());
                }
                FixValueInfo fixValueInfo = new FixValueInfo();
                fixValueInfo.setValueObj(value);
                map.get(type).add(fixValueInfo);
            } else {
                for (int i=0; i < map.get(type).size(); i++) {
                    if (map.get(type).get(i).getValueObj().getValue().equals(value.getValue().substring(0, 2))) {
                        if (Objects.isNull(map.get(type).get(i).getSubList())) {
                            map.get(type).get(i).setSubList(new ArrayList<MFixedValue>());
                        }
                        map.get(type).get(i).getSubList().add(value);
                        break;
                    }
                }
            }
	    }
    	return map;
	}

}
