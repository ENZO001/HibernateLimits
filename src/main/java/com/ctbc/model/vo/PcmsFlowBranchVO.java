package com.ctbc.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PCMS_FLOW_BRANCH")
public class PcmsFlowBranchVO extends PcmsBasicVO {

	private static final long serialVersionUID = 1L;

	public PcmsFlowBranchVO() {
	}

	@Column(name = "FLOW_MAIN_SEQ")
	private Integer flowMainSeq;
	
	@Column(name = "PROCESS_USER")
	private String processUser;

	public Integer getFlowMainSeq() {
		return flowMainSeq;
	}

	public void setFlowMainSeq(Integer flowMainSeq) {
		this.flowMainSeq = flowMainSeq;
	}

	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

}
