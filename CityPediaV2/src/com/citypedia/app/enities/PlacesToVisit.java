package com.citypedia.app.enities;

public class PlacesToVisit {

	/**
	 * "ATT_ID": "18", "NAME_OF_PLACE": "Kansal & Nepli", "FAMOUS_FOR":
	 * "Chandigarh has 3245 hectares under forest and most of it is hilly. The forest areas are mostly around Sukhna Lake, Sukhna Choe and Patiala ki Rao. Near village Kansal on the outskirts of Chandigarh towards the hills is a reserve forest, entry to which is restricted. One has to obtain an entry pass to see it. A large area of natural forest is preserved intact and one can have a real feel of a forest. Inside this forest is located a rest house which is surrounded by beautiful grassy lawns and flower beds.At Nepli Forestsa short distance from Kansal forest is located another reserve forest known as Nepli. Nepli is a bit more wild than Kansal. On two sides it is surrounded by small hills and the whole area is covered by thick forest which is full of wild life. There is a small rest house amidst green and flowery lawns.A walk in these forest areas can be very rewarding as one may come across large variety of wild animals - antelopes, neelgais, hyena, jackals and hares. The Deputy Conservator of Forest Chandigarh issues permits for entry to these forests."
	 * , "ADDRESS":
	 * "areas lie around Sukhna Lake and Patiala ki Rao. Close to village Kansal, on the outskirts of Chandigarh, towards the hills is a reserve forest."
	 * , "LATITUDE": null, "LONGITUDE": null, "CITY": "Chandigah", "PINCODE":
	 * null
	 */

	private String id;
	private String pName;
	private String famousFor;
	private String address;
	private String latitude;
	private String longitude;
	private String city;
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
	 * @return the pName
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName
	 *            the pName to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}

	/**
	 * @return the famousFor
	 */
	public String getFamousFor() {
		return famousFor;
	}

	/**
	 * @param famousFor
	 *            the famousFor to set
	 */
	public void setFamousFor(String famousFor) {
		this.famousFor = famousFor;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
