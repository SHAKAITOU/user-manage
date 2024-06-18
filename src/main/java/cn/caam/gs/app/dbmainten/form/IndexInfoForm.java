package cn.caam.gs.app.dbmainten.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexInfoForm {
	private String indexName;
	private List<String> columnNames;
}
