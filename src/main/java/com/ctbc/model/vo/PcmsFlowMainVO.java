package com.ctbc.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PCMS_FLOW_MAIN")
public class PcmsFlowMainVO extends PcmsBasicVO {

	private static final long serialVersionUID = 1L;

	public PcmsFlowMainVO() {
	}

	@Column(name = "CASE_NO")
	private String caseNo;

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

}
