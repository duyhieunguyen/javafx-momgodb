package application.model;

import java.util.Date;

public class Book {
	private String supplierId;
	private String supplierName;
	private String address;
	private String contactNo;
	private String emailId;
	private String remarks;
	private String date;
	private String lop;
	

	private SupplierType supplierType;

	public Book() {
	}

	public Book(String supplierId, String supplierName, String address, String contactNo, String emailId,
			String remarks, SupplierType supplierType, String date, String lop) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.address = address;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.remarks = remarks;
		this.supplierType = supplierType;
		this.date = date;
		this.lop = lop;

	}
	
	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SupplierType getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [supplierId=" + supplierId + ", supplierName=" + supplierName + ", address=" + address
				+ ", contactNo=" + contactNo + ", emailId=" + emailId + ", remarks=" + remarks + ", date=" + date
				+ ", lop=" + lop + ", supplierType=" + supplierType + "]";
	}



}
