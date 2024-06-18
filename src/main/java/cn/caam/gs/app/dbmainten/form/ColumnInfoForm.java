package cn.caam.gs.app.dbmainten.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfoForm {
	private String name;
	private Boolean pkFlg;
	private String type;
	private Integer characterMaxLength;
	private Integer numericPrecision;
	private Integer numericScale;
	private Boolean nullable;
	private String defaultValue;
	private String labelName;
	private String placeholder;
	
	public String getPageName(String prefixName) {
	    String[] chips = name.split("_");
	    int idx = 0;
	    String pageName = "";
	    for (String ship : chips) {
	        if (idx == 0) {
	            pageName += ship;
	        } else {
	            pageName += ship.substring(0, 1).toUpperCase()
	                    + ship.substring(1);
	        }
	        idx++;
	    }
	    
	    return prefixName + pageName;
	}
}
