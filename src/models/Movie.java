package models;
import java.util.ArrayList;

import com.google.common.base.Objects;

import utils.ToJsonString;
/*************************************************************************
 * Compilation: java User.java
 * 
 * The <tt>Movie</tt> class
 * <p>
 * This class will be used to create an object of Movie based on
 * the parameters laid out below
 * 
 * 
 * @author David Walsh
 *************************************************************************/


public class Movie  {
	private String title,date,imdbLink;
	private int filmId,rating, amountOfRatings;
	private ArrayList<String> genres;
	
	
	
	public Movie(int filmId,String title, String date,String imdbLink,ArrayList<String> genres){
		this.genres = genres;
		this.filmId = filmId;
		this.title = title;
		this.date = date;
		this.imdbLink = imdbLink; 
		this.rating = 0;
		this.setAmountOfRatings(0);
		
	}
	
	@Override
	 public String toString()
	 {
	 return new ToJsonString(getClass(), this).toString();
	 }

	 @Override
	 public int hashCode()
	 {
	 return Objects.hashCode(this.filmId, this.title, this.date, this.imdbLink);
	 }

	 @Override
	 public boolean equals(final Object obj)
	 {
	 if (obj instanceof Movie)
	 {
	 final Movie other = (Movie) obj;
	 return Objects.equal(title, other.title)
	 && Objects.equal(date, other.date)
	 && Objects.equal(imdbLink, other.imdbLink);
	 }
	 else
	 {
	 return false;
	 }
	 }

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		
		if(rating > 5){
		this.rating = 5;
		}else if(rating< -5){
			this.rating = -5;
		}else{
			this.rating = rating;
		}
		
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImdbLink() {
		return imdbLink;
	}

	public void setImdbLink(String imdbLink) {
		this.imdbLink = imdbLink;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getAmountOfRatings() {
		return amountOfRatings;
	}

	public void setAmountOfRatings(int amountOfRatings) {
		this.amountOfRatings += amountOfRatings;
	}

	
}
