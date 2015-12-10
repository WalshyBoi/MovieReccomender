package controllers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import models.Rating;
import models.User;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class MoviesAPITest.
 */
public class MoviesAPITest {

	 /** The like movies. */
 	private MoviesAPI likeMovies;
	 
	

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		likeMovies = new MoviesAPI();
		likeMovies.prime();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		likeMovies = null;
	}

	 /**
 	 * Test add user.
 	 */
 	@Test
	  public void testAddUser()
	  {
	    int size = likeMovies.getUsers().size()+1;
	   
	    likeMovies.addUser("Homer", "Simpson", 40 , "male", "Scientist", "02154");
	    assertEquals(likeMovies.getUsers().size(), size);
	   
	  }  
	 
	 
	 /**
 	 * Test add movie. his test follows the CORRECT guidelines specifically
 	 * Conformance, Reference,Existence and Cardinality. It also covers Right.
 	 */
 	@Test
	  public void testAddMovie()
	  {
	    int size = likeMovies.getMovies().size()+1;
	   
	    likeMovies.addMovie("Interstellar","2014", "http://www.imdb.com/title/tt0816692/");
	    assertEquals(likeMovies.getMovies().size(), size);
	   
	  }  

	 
	 
	 /**
 	 * Test remove user. This test follows the CORRECT guidelines specifically
 	 * Conformance, Reference,Existence and Cardinality. It also covers Right as well.
 	 */
 	@Test
	  public void testRemoveUser()
	  {
	    int size = likeMovies.getUsers().size()-1;
	   
	    likeMovies.removeUser(likeMovies.getUsers().get(1).getUserID());
	    assertEquals(likeMovies.getUsers().size(), size);
	   
	  }  
	 
	 /**
 	 * Gets the movie details.
 	 * This test follows the CORRECT guidelines specifically
 	 * Conformance, Range, Reference,Existence and Cardinality
 	 *
 	 * @return the movie details
 	 */
 	@Test
	  public void getMovieDetails()
	  
	  {
		  String title = "Toy Story (1995)";
		  int filmId = 1;
		  String date = "01-Jan-1995";
		  String imdbLink = "http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)"; 
		  int genreArraySize = 3;
		  
		  String actualTitle = likeMovies.movies.get(0).getTitle();
		  String actualDate =  likeMovies.movies.get(0).getDate();
		  int actualFilmID = likeMovies.movies.get(0).getFilmId();
		  String actualIMDB = likeMovies.movies.get(0).getImdbLink();
		  int actualGenreSize = likeMovies.movies.get(0).getGenres().size();
		  
		  assertEquals(title, actualTitle);
		  assertEquals(date, actualDate);
		  assertEquals(imdbLink, actualIMDB);
		  assertEquals(filmId, actualFilmID);
		  assertEquals(genreArraySize, actualGenreSize);
		  
	  }
	 


 	 /**
 	 * This test's the get user rating but also tests the setRatings method 
 	 * for the ratings array.
	  * This test follows the CORRECT guidelines specifically
	  * Conformance, Range, Reference,Existence and Cardinality
	  *
	  * @param userID the user id
	  * @return the user rating
 	 */
 	@Test
	  public void getUserRating()
	  
	  {
 		
 		int i =0;
 		Rating rating = new Rating(i,i,i,i);
 		likeMovies.getUsers().get(0).getMyRatings().add(rating);
 		int ratingsArraySize = likeMovies.getUsers().get(0).getMyRatings().size();
 		int actual = 1;
 		assertEquals(ratingsArraySize, actual);
		  
	  }
}
