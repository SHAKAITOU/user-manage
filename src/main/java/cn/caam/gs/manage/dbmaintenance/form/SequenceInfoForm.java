package cn.caam.gs.manage.dbmaintenance.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SequenceInfoForm {
	private String seqName;
	private int start;
}
