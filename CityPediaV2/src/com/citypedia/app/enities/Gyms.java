package com.citypedia.app.enities;

public class Gyms {
	/*
	 * "GS_ID": "1", "GYM_NAME": "Suprama Gym & Spa", "ADDRESS":
	 * "Sco-3013-14,Sector-22-D", "CITY": "Chandigarh", "CONTACT_NUMBER":
	 * "(0172) 4982256", "PINCODE": null
	 */

	private String id;
	private String gymName;
	private String addr;
	private String city;
	private String contactNo;
	private String pincode;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the gymName
	 */
	public String getGymName() {
		return gymName;
	}

	/**
	 * @param gymName
	 *            the gymName to set
	 */
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
