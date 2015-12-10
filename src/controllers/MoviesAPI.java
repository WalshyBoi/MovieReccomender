package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import edu.princeton.cs.introcs.In;
import sun.applet.Main;
import utils.CSVLoader;
import utils.Serializer;
import models.User;
import models.Movie;
import models.Genre;
import models.Rating;



/*************************************************************************
 * Compilation: java MoviesAPI.java
 * 
 * The <tt>MoviesAPI</tt> class
 * <p>
 * This class will be used to create a movie recommendation database
 * of users, movies and the recommendations. Users, Movies and Recommendations
 * can be added to the database.
 * 
 * 
 * @author David Walsh
 *************************************************************************/
public class MoviesAPI {

	/** The serializer. */
	private Serializer serializer;

	/** The users. */
	private ArrayList<User> users;

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	/** The movies. */
	ArrayList<Movie> movies;

	/** The ratings. */
	private ArrayList<Rating> ratings ;

	/** The genres. */
	private ArrayList<Genre> genres;

	/** The user index. */
	private Map<Long,   User>   userIndex       = new HashMap<>();



	/**
	 * Instantiates a new movies api.
	 */
	public MoviesAPI(){}

	/**
	 * Instantiates a new movies api.
	 *
	 * @param serializer the serializer
	 * @throws Exception the exception
	 */
	public MoviesAPI(Serializer serializer) throws Exception
	{
		this.serializer = serializer;

	}

	/**
	 * This methods uses the CSVLoader class to parse 
	 * through the csv files and populate arrays of 
	 * users, genres, movies and ratings.It calls on the removeDuplicates()
	 * to remove duplicate ratings by users based on the timestamp.
	 * It then calls on the addUserRatings to populate each
	 * users ratings array. It then calls on the addMoievRatings
	 * to add the ratings to each movie.
	 *
	 * @throws Exception the exception
	 */
	public void prime() throws Exception{
		CSVLoader loader = new CSVLoader();
		users = loader.loadUsers("data_movieLens/users5.dat");
		genres = loader.loadGenres("data_movieLens/genre.dat");
		movies = loader.loadMovies("data_movieLens/items5.dat");
		ratings = loader.loadRatings("data_movieLens/ratings5.dat");
		removeDuplicates();
		addUserRatings();
		addMovieRatings();

	}


	/**
	 * This method allows you to add a rating
	 * to a movie.
	 *
	 * @param userID the user id
	 * @param movieID the movie id
	 * @param rating the rating
	 */
	public void addRating(int userID,int movieID, int rating){
		for(Movie currentMovie : movies){

			if(movieID == currentMovie.getFilmId()&& ((rating >= -5) && (rating <=5))){
				currentMovie.setRating(rating);

			}
		}
	}

	/**
	 * Adds the values of the ratings from
	 * the rating array to an atrribute int in each
	 *  movie called rating. It gets the average rating by add 
	 */
	public void addMovieRatings(){
		for(Movie currentMovie : movies){

			int rating = 0;
			for(Rating currentRating : ratings){
				if( (currentRating.getFilmID() == currentMovie.getFilmId())){
					//currentMovie.setRating(rating);
					rating +=currentRating.getRating();
					currentMovie.setAmountOfRatings(1);

				}
			}currentMovie.setRating(rating/currentMovie.getAmountOfRatings());
		}
	}



	/**
	 * Returns the user rating array
	 * filled with all their ratings.
	 *
	 * @param userID the user id
	 * @return the user rating
	 */
	public ArrayList<Rating> getUserRating(int userID){
		for(User currentUser : users){
			if(currentUser.getUserID() == userID){
				return currentUser.getMyRatings();
			}

		}
		return null;
	}

	/**
	 * Gets the movie based on the moiveID.
	 *
	 * @param movieID the movie id
	 * @return the movie if it is present otherwise returns
	 * null
	 */
	public Movie getMovie(int movieID){

		for(Movie currentMovie : movies){
			if(currentMovie.getFilmId() == movieID){
				return currentMovie;

			}
		}
		return null;
	}



	/**
	 * Adds the users ratings by comparing the
	 * ratings array and the user array and adds the ratings
	 * for the user to there ratings array.
	 */
	public void addUserRatings(){
		for(Rating currentRating : getRatings()){
			for(User currentUser : users){

				if (currentUser.getUserID() == currentRating.getUserID()){
					currentUser.getMyRatings().add(currentRating);
				}
			}
		}

	}

	/**
	 * This method will use an iterator to iterate through
	 * the a new array called removeRatings which is given the
	 * contents of the ratings array. It then goes through the 
	 * array and removes ratings that are duplicates and also been 
	 * created at an older timestamp. At the very end it
	 * gives the ratings array the contents of the removeRatings array
	 */
	public void removeDuplicates(){
		ArrayList<Rating> removeRatings = ratings;

		for (Iterator<Rating> iterator = removeRatings.iterator(); iterator.hasNext();) {
			Rating currentRating = iterator.next();
			for(int i =0; i < ratings.size(); i++)
				if ((currentRating.getUserID() == ratings.get(i).getUserID()) && (currentRating.getFilmID() == ratings.get(i).getFilmID()) &&
						(currentRating.getTimeStamp().before(ratings.get(i).getTimeStamp()) == true) 
						){
					iterator.remove();
				}

		}

		ratings = removeRatings;
	}
	
	/**
	 * This creates a new arrayList called top10 
	 * first given the objects from the movies arrayList.
	 * It then iterate through it and removes any ratings that are
	 * below zero. It then uses a comparator based of their ratings to 
	 * order them from highest to lowest based on the movie rating.
	 * It then returns the ratings arrayList.
	 *
	 * @return the top ten movies
	 */
	public ArrayList<Movie> getTopTenMovies(){
		ArrayList<Movie> top10 = movies;


		for (Iterator<Movie> iterator = top10.iterator(); iterator.hasNext();) {
			Movie movie = iterator.next();
			if (movie.getRating() < 0) {

				iterator.remove();
			}


			Collections.sort(top10, new Comparator<Movie>(){ 
				public int compare(Movie o1, Movie o2) {
					return o2.getRating() - o1.getRating(); 
					//My implementation
				}
			});

		}return top10;

	}




	/**
	 * Load.
	 * This will pop each on of the arraylist off a stack and read
	 * in.
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public void load() throws Exception{
		
		serializer.read();
		//	User.counter = (Long) serializer.pop();
		users = (ArrayList<User>) serializer.pop();
		movies = (ArrayList<Movie>)   serializer.pop();
		genres = (ArrayList<Genre>)     serializer.pop();
		ratings =((ArrayList<Rating>)     serializer.pop());
	}

	/**
	 * This is push everything onto a stack and saved to
	 * an XML file.
	 *
	 * @throws Exception the exception
	 */
	public void store() throws Exception{

		//	serializer.push(User.counter);
		serializer.push(users);
		serializer.push(movies);
		serializer.push(genres);
		serializer.push(getRatings());
		serializer.write();

	}

	/**
	 * Adds the user.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param age the age
	 * @param gender the gender
	 * @param occupation the occupation
	 * @param zipCode the zip code
	 */
	public void addUser(String firstName, String lastName, int age, String gender, String occupation,String zipCode){
		ArrayList<Rating> bla = new ArrayList<Rating>();
		User user = new User(firstName,lastName,age,gender,occupation,zipCode,bla);
		users.add(user);
		userIndex.put(user.userID, user);

	}

	/**
	 * Removes the user.
	 *
	 * @param userID the user id
	 */
	public void removeUser(long userID){
//
   ArrayList<User> removeUsers = users;
   
	for (Iterator<User> iterator = removeUsers.iterator(); iterator.hasNext();) {
		User user = iterator.next();
		if (user.getUserID() == userID) {

			iterator.remove();
		}
	}
	}

//	}

	/**
	 * Adds the movie.
	 *
	 * @param title the title
	 * @param date the date
	 * @param imdbLink the imdb link
	 */
	public void addMovie(String title, String date,String imdbLink){
		int filmId = movies.size()+1; 
		ArrayList<String> bla = new ArrayList<String>();
		Movie movie = new Movie(filmId,title,date,imdbLink,bla);
		movies.add(movie);
	}


	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * Gets the movie details.
	 *
	 * @param movieID the movie id
	 * @return the movie details
	 */
	public void getMovieDetails(int movieID){

		for(Movie movie : movies){
			if(movieID == movie.getFilmId()){
				System.out.println(movie);

			}

		}

	}

	/**
	 * Gets the ratings.
	 *
	 * @return the ratings
	 */
	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	/**
	 * Sets the ratings.
	 *
	 * @param ratings the new ratings
	 */
	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

		
	public ArrayList<Rating> recommend(long userID){
		ArrayList<Rating> reccomend = new ArrayList();
		for (User user: users){
			if(user.getUserID()==userID){
			reccomend = user.getMyRatings();
			
			 Collections.sort(reccomend, new Comparator<Rating>(){ 
					public int compare(Rating o1, Rating o2) {
						return o2.getRating() - o1.getRating(); 
						//My implementation of a comparator that sorts based on rating
					}
					 
				});
			}
		
	}
		return reccomend;
	}


	public ArrayList<Movie> getUserRecommendations(long userID){
			ArrayList<Rating> reccomend1 = new ArrayList();
			reccomend1 = recommend(userID);
			   
			
			//gets their highest rating
			Rating rating = reccomend1.get(0);
			
			//gets their highest rated film
			Movie movie = getMovie(rating.getFilmID());
			ArrayList<String> movieGenres = movie.getGenres();
			
			
			ArrayList<Movie> topFilm = new ArrayList<Movie>();
		
				for(Movie currentMovie: movies){
					for(int i = 0; i<currentMovie.getGenres().size(); i++ ){
						for(int k = 0; k<movieGenres.size(); k++){
							  if((currentMovie.getGenres().get(i) == movieGenres.get(k)) && (currentMovie != movie)) {
					  
				 
					   topFilm.add(currentMovie);
				   }
				   }
				   }
				}
				
				
				Collections.sort(topFilm, new Comparator<Movie>(){ 
					public int compare(Movie o1, Movie o2) {
						return o2.getRating() - o1.getRating(); 
						//My implementation
					}
				});
			  
		return topFilm;
				}
}
