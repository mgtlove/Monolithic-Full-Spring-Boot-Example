package com.cognixia.jump.requestmodels;

public class StudentAndUserReqModel {

	private Long userId;
	private Long studentId;
	
	public StudentAndUserReqModel() {
		
	}
	
	public StudentAndUserReqModel(Long userId, Long studentId) {
		super();
		this.userId = userId;
		this.studentId = studentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "StudentAndUserReqModel [userId=" + userId + ", studentId=" + studentId + "]";
	}
	
}
