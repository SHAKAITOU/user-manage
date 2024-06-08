package cn.caam.gs.manage.dbmaintenance.form;

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
	private String comment;
	private Boolean pkFlg;
	private String type;
	private Integer characterMaxLength;
	private Integer numericPrecision;
	private Integer numericScale;
	private String defaultValue;
	private Boolean nullable;
}
