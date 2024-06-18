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
public class TableInfoForm {
	private String tableName;
	private String comment;
	
	List<ColumnInfoForm> columnInfos;
	
	List<IndexInfoForm> indexInfos;
	
	SequenceInfoForm sequenceInfo;
}
