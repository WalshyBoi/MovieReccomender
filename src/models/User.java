package models;
import java.util.ArrayList;

import utils.ToJsonString;

import com.google.common.base.Objects;


public class User {
	private String firstName,lastName,occupation,gender,zipCode;
	private int age;
	public long userID;
	private ArrayList<Rating> myRatings;
	public static Long counter = (long) 01;
	
	public User(String firstName, String lastName, int age, String gender, String occupation,String zipCode, ArrayList<Rating> myRatings){
		this.userID = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zipCode = zipCode;
		this.myRatings = myRatings;
		
	}
	@Override
	 public String toString()
	 {
	 return new ToJsonString(getClass(), this).toString();
	 }

	@Override
	 public int hashCode()
	 {
	 return Objects.hashCode(this.userID,this.lastName, this.firstName, this.gender, this.age);
	 }

	 @Override
	 public boolean equals(final Object obj)
	 {
	 if (obj instanceof User)
	 {
	 final User other = (User) obj;
	 return Objects.equal(firstName, other.firstName)
	 && Objects.equal(userID, other.userID) 
	 && Objects.equal(lastName, other.lastName)
	 && Objects.equal(gender, other.gender)
	 && Objects.equal(age, other.age)
	 && Objects.equal(occupation, other.occupation)
	 && Objects.equal(getMyRatings(), other.getMyRatings());
	 }
	 else
	 {
	 return false;
	 }
	 }
	 
	 
	 
	 public String getzipCode() {
		return zipCode;
	}


	


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public ArrayList<Rating> getMyRatings() {
		return myRatings;
	}


	public void setMyRatings(ArrayList<Rating> myRatings) {
		this.myRatings = myRatings;
	}


	public void setUserID(long userID) {
		this.userID = userID;
	}





	public String getGender() {
		return gender;
	}


	public long getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	public String isGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	






}













