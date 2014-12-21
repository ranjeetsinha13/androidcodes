package com.citypedia.app.enities;

public class Atms {

	/*
	 * "ATM_ID": "ATM_1", "ATM_NAME": "Axis Bank", "ATM_CITY": "Chandigarh",
	 * "ATM_ADDRESS": "Booth No.34, Sector- 7, Near Govind Sweet, Chandigarh"
	 */

	private String id;
	private String atmName;
	private String city;
	private String atmAddr;
	private String locality;

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
	 * @return the atmName
	 */
	public String getAtmName() {
		return atmName;
	}

	/**
	 * @param atmName
	 *            the atmName to set
	 */
	public void setAtmName(String atmName) {
		this.atmName = atmName;
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
	 * @return the atmAddr
	 */
	public String getAtmAddr() {
		return atmAddr;
	}

	/**
	 * @param atmAddr
	 *            the atmAddr to set
	 */
	public void setAtmAddr(String atmAddr) {
		this.atmAddr = atmAddr;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality
	 *            the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

}
