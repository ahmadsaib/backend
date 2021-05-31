package com.Ahmad.Caterer.Pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Caterer")
public class Caterer {
	@Id
	private String ID;

	@NotNull(message = "Name cannot be Null")
	@NotBlank(message = "Name sholdn't be blank")
	private String name;

	@Positive(message = "Value Cannot be Negative")
	private int minNumOfGuests;

	@Positive(message = "Value Cannot be Negative")
	private int maxNumOfGuests;

	@NotNull(message = "Location Details cannot be Null")
	private Location location;

	private String phoneNumber;

	@NotNull(message = "Name cannot be Null")
	@Pattern(regexp = "^[0-9]{10,11}$", message = "Please Enter Proper Mobile Num")
	private String mobileNumber;

	@NotNull(message = "Name cannot be Null")
	@Email(message = "Please Enter proper mail id")
	private String email;

	public Caterer(String iD, String name, int minNumOfGuests, int maxNumOfGuests, Location location,
			String phoneNumber, String mobileNumber, String email) {
		super();
		this.ID = iD;
		this.name = name;
		this.minNumOfGuests = minNumOfGuests;
		this.maxNumOfGuests = maxNumOfGuests;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public Caterer(String name, int minNumOfGuests, int maxNumOfGuests, Location location, String phoneNumber,
			String mobileNumber, String email) {
		super();
		this.name = name;
		this.minNumOfGuests = minNumOfGuests;
		this.maxNumOfGuests = maxNumOfGuests;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Caterer() {
		super();

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinNumOfGuests() {
		return minNumOfGuests;
	}

	public void setMinNumOfGuests(int minNumOfGuests) {
		this.minNumOfGuests = minNumOfGuests;
	}

	public int getMaxNumOfGuests() {
		return maxNumOfGuests;
	}

	public void setMaxNumOfGuests(int maxNumOfGuests) {
		this.maxNumOfGuests = maxNumOfGuests;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Caterer [ID=" + ID + ", name=" + name + ", minNumOfGuests=" + minNumOfGuests + ", maxNumOfGuests="
				+ maxNumOfGuests + ", location=" + location + ", phoneNumber=" + phoneNumber + ", mobileNumber="
				+ mobileNumber + ", email=" + email + "]";
	}

}
