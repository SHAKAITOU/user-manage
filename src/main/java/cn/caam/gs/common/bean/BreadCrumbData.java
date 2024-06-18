package cn.caam.gs.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreadCrumbData {

	private boolean isActvie;
	private String id;
	private String name;
	private String className;
}
