package cn.caam.gs.manage.dbmaintenance.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DbMaintenanceForm {
	private int mediaHeight;
	private String dbUrl;
	private String schema;
	private String selectTableName;
	private List<Boolean> sabunFlgs;
}
