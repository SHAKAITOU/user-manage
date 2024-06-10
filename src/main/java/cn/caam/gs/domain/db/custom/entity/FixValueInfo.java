package cn.caam.gs.domain.db.custom.entity;

import java.util.List;

import cn.caam.gs.domain.db.base.entity.MFixedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FixValueInfo {
    private MFixedValue valueObj;
	private List<MFixedValue> subList; 
}
