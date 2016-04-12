package com.smtl.app.param;

public class CusCallParam {

	private Integer callID;			//呼叫ID
	private String statement;	//任务描述或完成情况描述
	public Integer getCallID() {
		return callID;
	}
	public void setCallID(Integer callID) {
		this.callID = callID;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
}
