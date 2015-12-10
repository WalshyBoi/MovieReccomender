package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;





import models.Genre;
import models.Movie;
import models.Rating;
import models.User;
import edu.princeton.cs.introcs.In;
/*************************************************************************
 * Compilation: java CVSLoader.java
 * 
 * The <tt>CVSLoader</tt> class
 * <p>
 * This class will be used to create a object of CVS Loader that can 
 * be used by other class to pass a filename through which will then 
 * be read in / parsed to create objects and populate the arrays.
 * 
 * 
 * @author David Walsh
 *************************************************************************/
public class CSVLoader
{ private ArrayList<User> users;
private ArrayList<Movie> movies;
private ArrayList<Rating> ratings ;
private ArrayList<Genre> genres;

public CSVLoader(){
	
	users = new ArrayList<>();
	movies= new ArrayList<>();
	genres= new ArrayList<>();
	ratings = new ArrayList<>();
	
	
}

 public ArrayList<User> loadUsers(String filename) throws Exception
 {
	 

	File usersFile = new File(filename);
	 In inUsers = new In(usersFile);
	    //each field is separated(delimited) by a '|'
	    String delims = "[|]";
	    while (!inUsers.isEmpty()) {
	    
	        // get user and rating from data source
	        String userDetails = inUsers.readLine();

//	        // parse user details string
	        String[] userTokens = userDetails.split(delims);

	        // output user data to console.
	        ArrayList<Rating> bla = new ArrayList<Rating>();
	        
	        users.add(new User(userTokens[1],userTokens[2],Integer.parseInt(userTokens[3]),userTokens[4],userTokens[5],userTokens[6],bla));
	           
	    }
		return users;
 }

 public ArrayList<Movie> loadMovies (String filename) throws Exception
 {
	  File usersFile = new File(filename);
	    
	    In inUsers = new In(usersFile);
	   
	  
	    //each field is separated(delimited) by a '|'
	    String delims = "[|]";
	    while (!inUsers.isEmpty()) {
	    	
	        // get user and rating from data source
	    	
	        String userDetails = inUsers.readLine();
	        
	        // parse user details string
	        String[] userTokens = userDetails.split(delims);
	     
	        	String str = "";
	        	 ArrayList<String> bla = new ArrayList<String>();
	        	 
				for(int i = 4; i<23; i++)
					
	        	 if(trueOrFalse(Integer.parseInt(userTokens[i])) == true){
	        	  str = (genres.get(i-4).getGenreName());
	        	  
	        	 bla.add(str);
	        	
	        	 }
				movies.add(new Movie(Integer.parseInt(userTokens[0]),userTokens[1],userTokens[2],userTokens[3],bla)); 
	    }
		return movies;
 }
 public ArrayList<Rating> loadRatings (String filename) throws Exception
 {
	 File usersFile = new File(filename);
	    In inUsers = new In(usersFile);
	    String delims = "[|]";
	    while (!inUsers.isEmpty()) {
	        String userDetails = inUsers.readLine();
	        String[] userTokens = userDetails.split(delims);
	        
	        
			ratings.add(new Rating(Long.parseLong(userTokens[0]),Integer.parseInt(userTokens[1]),Integer.parseInt(userTokens[2]),Long.parseLong(userTokens[3]))); 
    
	       
			      
	          
	    }
	    
	    
	    
	    //This makes a comparator to organise the ratings based on their 
	    //timestamps.
	    Collections.sort(ratings, new Comparator<Rating>() {
	    	  public int compare(Rating o1, Rating o2) {
	    	      return o1.getTimeStamp().compareTo(o2.getTimeStamp());
	    	  }});
	    
	 return ratings;
 }
 public ArrayList<Genre> loadGenres (String filename) throws Exception
 {File usersFile = new File(filename);
 
 In inUsers = new In(usersFile);
 
 //each field is separated(delimited) by a '|'
 String delims = "[|]";
 while (!inUsers.isEmpty()) {
 	
     // get user and rating from data source
     String userDetails = inUsers.readLine();

     // parse user details string
     String[] userTokens = userDetails.split(delims);

     // output user data to console.
		genres.add(new Genre(userTokens[0],Integer.parseInt(userTokens[1])));   
     	
         
 }
return genres;
 }
 
 
 private boolean trueOrFalse(int i){	
		if(i==1){
			return true;
		}else{
			return false;
		}
	}
 
 
}