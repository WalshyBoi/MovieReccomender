package models;
import java.util.Date;
public class Rating implements Comparable<Rating>{
	/*************************************************************************
	 * Compilation: java User.java
	 * 
	 * The <tt>Rating</tt> class
	 * <p>
	 * This class will be used to create an object of Rating based on
	 * the parameters laid out below
	 * 
	 * 
	 * @author David Walsh
	 *************************************************************************/
	

	private int filmID,rating;
	private long timetamp, userID;
	private Date time;
	
	public Rating(long userID, int itemID, int rating,long timeStamp){
		this.userID = userID;
		this.filmID = itemID;
		this.rating = rating;
	  //changes the timestamp in unix form to a readable date
		Date time = new java.util.Date((long)timeStamp*1000);
		this.time = time;
		
	}
	public Date getTimeStamp() {
		return time;
	}
	public void setTimeStamp(Date time) {
		this.time = time;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int itemID) {
		this.filmID = itemID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getTimetamp() {
		return timetamp;
	}

	public void setTimetamp(long timetamp) {
		this.timetamp = timetamp;
	}
	@Override
	public String toString() {
		return "Rating [userID=" + userID + ", filmID=" + filmID + ", rating="
				+ rating + ", timetamp=" + time + "]"+ "\n";
	}
	@Override
	public int compareTo(Rating o) {
		
		return getTimeStamp().compareTo(o.getTimeStamp());
	}

}

