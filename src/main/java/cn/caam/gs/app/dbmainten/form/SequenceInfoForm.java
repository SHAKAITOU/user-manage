package cn.caam.gs.app.dbmainten.form;

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
