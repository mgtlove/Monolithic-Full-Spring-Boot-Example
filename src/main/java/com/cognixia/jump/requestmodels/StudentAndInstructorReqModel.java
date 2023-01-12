package com.cognixia.jump.requestmodels;

public class StudentAndInstructorReqModel {

	private Long instructorId;
	private Long studentId;
	public StudentAndInstructorReqModel(Long instructorId, Long studentId) {
		super();
		this.instructorId = instructorId;
		this.studentId = studentId;
	}
	public Long getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString() {
		return "StudentAndInstructorReqModel [instructorId=" + instructorId + ", studentId=" + studentId + "]";
	}
	
}
