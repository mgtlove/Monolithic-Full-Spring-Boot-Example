package com.cognixia.jump.requestmodels;

// Model to be used in Student controller for address update
public class StudentAndAddressReqModel {

	private Long studentId;
	private Long addressId;
	
	public StudentAndAddressReqModel(Long studentId, Long addressId) {
		super();
		this.studentId = studentId;
		this.addressId = addressId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Override
	public String toString() {
		return "StudentAndAddressReqModel [studentId=" + studentId + ", addressId=" + addressId + "]";
	}
	
}
