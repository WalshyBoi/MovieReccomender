package controllers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import models.User;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MoviesAPITest {

	 private MoviesAPI likeMovies;
	 
	

	@Before
	public void setUp() throws Exception {
		likeMovies = new MoviesAPI();
		likeMovies.prime();
	}

	@After
	public void tearDown() throws Exception {
		likeMovies = null;
	}

	 @Test
	  public void testAddUser()
	  {
	    int size = likeMovies.getUsers().size()+1;
	   
	    likeMovies.addUser("Homer", "Simpson", 40 , "male", "Scientist", "02154");
	    assertEquals(likeMovies.getUsers().size(), size);
	   
	  }  
	 
	 
	 @Test
	  public void testAddMovie()
	  {
	    int size = likeMovies.getMovies().size()+1;
	   
	    likeMovies.addMovie("Interstellar","2014", "http://www.imdb.com/title/tt0816692/");
	    assertEquals(likeMovies.getMovies().size(), size);
	   
	  }  

	 
	 
	 @Test
	  public void testRemoveUser()
	  {
	    int size = likeMovies.getUsers().size()-1;
	   
	    likeMovies.removeUser(likeMovies.getUsers().get(1).getUserID());
	    assertEquals(likeMovies.getUsers().size(), size);
	   
	  }  
	 
	 @Test
	  public void getMovieDetails()
	  
	  {
		  String str = "Toy Story (1995)";
		  String actual = likeMovies.movies.get(0).getTitle();
		  assertEquals(str, actual);
		  
	  }
	 
	 getUserRating(int userID)
	 
}
