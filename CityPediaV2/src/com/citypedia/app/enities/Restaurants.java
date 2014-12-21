package com.citypedia.app.enities;

public class Restaurants {
	/*
	 * CREATE TABLE `atms_banks` ( `ID` int(11) NOT NULL, `NAME` varchar(45)
	 * DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, `BRANCH` varchar(200)
	 * DEFAULT NULL, `IFSC_CODE` varchar(15) DEFAULT NULL, `MICR_CODE`
	 * varchar(15) DEFAULT NULL, `LATITUDE` varchar(15) DEFAULT NULL,
	 * `LONGITUDE` varchar(45) DEFAULT NULL, `CITY` varchar(45) DEFAULT NULL,
	 * `STREET` varchar(45) DEFAULT NULL, `PINCODE` varchar(10) DEFAULT NULL,
	 * `ATM_BANK` tinyint(1) DEFAULT NULL, `SUMMARY` varchar(200) DEFAULT NULL,
	 * `AVG_RATING` float DEFAULT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB
	 * DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `bus` ( `ID` int(11) NOT NULL, `BUS_NO` varchar(45) DEFAULT
	 * NULL, `ROUTE_NO` varchar(45) DEFAULT NULL, `START_POINT` varchar(45)
	 * DEFAULT NULL, `END_POINT` varchar(45) DEFAULT NULL, `STOPS` varchar(300)
	 * DEFAULT NULL, `FREQUENCY` varchar(45) DEFAULT NULL,
	 * `STOP_DISTANCE_FROM_IP` varchar(300) DEFAULT NULL, PRIMARY KEY (`ID`) )
	 * ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `gyms_spas` ( `ID` int(11) NOT NULL, `NAME` varchar(45)
	 * DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, `FAMOUS_FOR`
	 * varchar(200) DEFAULT NULL, `LATITUDE` varchar(15) DEFAULT NULL,
	 * `LONGITUDE` varchar(45) DEFAULT NULL, `CITY` varchar(45) DEFAULT NULL,
	 * `STREET` varchar(45) DEFAULT NULL, `PINCODE` varchar(10) DEFAULT NULL,
	 * `GYM_SPA` tinyint(1) DEFAULT NULL, `SUMMARY` varchar(200) DEFAULT NULL,
	 * `AVG_RATING` float DEFAULT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB
	 * DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `helplines` ( `ID` int(11) NOT NULL, `NUMBER` varchar(45)
	 * DEFAULT NULL, `SUMMARY` varchar(45) DEFAULT NULL, `HELP_FOR` varchar(45)
	 * DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, PRIMARY KEY (`ID`) )
	 * ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `hospitals` ( `ID` int(11) NOT NULL, `NAME` varchar(45)
	 * DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, `FAMOUS_FOR`
	 * varchar(45) DEFAULT NULL, `CATEGORY` varchar(45) DEFAULT NULL, `LATITUDE`
	 * varchar(15) DEFAULT NULL, `LONGITUDE` varchar(45) DEFAULT NULL, `CITY`
	 * varchar(45) DEFAULT NULL, `STREET` varchar(45) DEFAULT NULL, `PINCODE`
	 * varchar(10) DEFAULT NULL, `SUMMARY` varchar(200) DEFAULT NULL,
	 * `AVG_RATING` float DEFAULT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB
	 * DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `news` ( `ID` int(11) NOT NULL, `NEWS_URL` varchar(90)
	 * DEFAULT NULL, `MARKED_FOR_READING` tinyint(1) DEFAULT NULL, `HEADLINE`
	 * varchar(200) DEFAULT NULL, `DATE_OF_NEWS` date DEFAULT NULL, PRIMARY KEY
	 * (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `petrol_pumps` ( `ID` int(11) NOT NULL, `NAME` varchar(45)
	 * DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, `LATITUDE` varchar(15)
	 * DEFAULT NULL, `LONGITUDE` varchar(45) DEFAULT NULL, `CITY` varchar(45)
	 * DEFAULT NULL, `STREET` varchar(45) DEFAULT NULL, `PINCODE` varchar(10)
	 * DEFAULT NULL, `SUMMARY` varchar(200) DEFAULT NULL, `AVG_RATING` float
	 * DEFAULT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `places_to_visit` ( `ID` int(11) NOT NULL, `NAME_OF_PLACE`
	 * varchar(45) DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL,
	 * `FAMOUS_FOR` varchar(200) DEFAULT NULL, `LATITUDE` varchar(15) DEFAULT
	 * NULL, `LONGITUDE` varchar(45) DEFAULT NULL, `CITY` varchar(45) DEFAULT
	 * NULL, `STREET` varchar(45) DEFAULT NULL, `PINCODE` varchar(10) DEFAULT
	 * NULL, `SUMMARY` varchar(200) DEFAULT NULL, `AVG_RATING` float DEFAULT
	 * NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 * 
	 * 
	 * delimiter $$
	 * 
	 * CREATE TABLE `restaurants` ( `ID` int(11) NOT NULL, `NAME_OF_RESTAURANT`
	 * varchar(45) DEFAULT NULL, `ADDRESS` varchar(45) DEFAULT NULL, `CUISINES`
	 * varchar(200) DEFAULT NULL, `AVG_COST_PER_PERSON` decimal(10,0) DEFAULT
	 * NULL, `LATITUDE` varchar(15) DEFAULT NULL, `LONGITUDE` varchar(45)
	 * DEFAULT NULL, `CITY` varchar(45) DEFAULT NULL, `STREET` varchar(45)
	 * DEFAULT NULL, `PINCODE` varchar(10) DEFAULT NULL, `AVG_RATING` float
	 * DEFAULT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
	 */

	private String id;
	private String nameRestaurant;
	private String address;
	private String cuisines;
	private String avgCostPerPerson;
	private String services;
	private String latitude;
	private String longitude;
	private String locality;
	private String timings;
	private String payments;
	private String city;
	private String contactDetails;

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
	 * @return the nameRestaurant
	 */
	public String getNameRestaurant() {
		return nameRestaurant;
	}

	/**
	 * @param nameRestaurant
	 *            the nameRestaurant to set
	 */
	public void setNameRestaurant(String nameRestaurant) {
		this.nameRestaurant = nameRestaurant;
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
	 * @return the cuisines
	 */
	public String getCuisines() {
		return cuisines;
	}

	/**
	 * @param cuisines
	 *            the cuisines to set
	 */
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	/**
	 * @return the avgCostPerPerson
	 */
	public String getAvgCostPerPerson() {
		return avgCostPerPerson;
	}

	/**
	 * @param avgCostPerPerson
	 *            the avgCostPerPerson to set
	 */
	public void setAvgCostPerPerson(String avgCostPerPerson) {
		this.avgCostPerPerson = avgCostPerPerson;
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

	/**
	 * @return the timings
	 */
	public String getTimings() {
		return timings;
	}

	/**
	 * @param timings
	 *            the timings to set
	 */
	public void setTimings(String timings) {
		this.timings = timings;
	}

	/**
	 * @return the payments
	 */
	public String getPayments() {
		return payments;
	}

	/**
	 * @param payments
	 *            the payments to set
	 */
	public void setPayments(String payments) {
		this.payments = payments;
	}

	/**
	 * @return the contactDetails
	 */
	public String getContactDetails() {
		return contactDetails;
	}

	/**
	 * @param contactDetails
	 *            the contactDetails to set
	 */
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
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
	 * @return the services
	 */
	public String getServices() {
		return services;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

}
