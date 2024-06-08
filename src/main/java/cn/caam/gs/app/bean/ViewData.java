package cn.caam.gs.app.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewData {

	private String pageContext;
	private String jsClassName;
	Map<String, Object> dataMap;
}
