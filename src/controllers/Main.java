package controllers;
import java.io.File;
import java.util.Collection;

import com.google.common.base.Optional;

import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.User;
/*************************************************************************
 * Compilation: java Main.java
 * 
 * The <tt>Main</tt> class
 * <p>
 * This class will be used to create a command shell and create an 
 * instance of the MoviesAPI.
 * 
 * 
 * @author David Walsh
 *************************************************************************/
public class Main
{
  public MoviesAPI likeMovies;

  public Main() throws Exception
  {
	
    File datastore = new File("datastore3.xml");
    Serializer serializer = new XMLSerializer(datastore);

    likeMovies = new MoviesAPI(serializer);
    if (datastore.isFile())
    {
      likeMovies.load();
    }
    
    likeMovies.store();

  }
 
  public static void main(String[] args) throws Exception
  {	
	  Main main = new Main();
	  Shell shell = ShellFactory.createConsoleShell("dw", "Welcome to David Walsh's Movie Reccomender! ?help for instructions", main);
	  shell.commandLoop();
  }
  
 
  @Command(description="prime")
  public void prime () throws Exception
  {
  likeMovies.prime();
  }
 
 
 @Command(description="Add a new User")
 
 public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName,
 @Param(name="age") int age, @Param(name="gender") String gender, @Param(name="occupation") String occupation,
 @Param(name="zip code") String zipCode)
 {
	 
 likeMovies.addUser(firstName, lastName, age, gender, occupation,zipCode);
 }

 @Command(description="Delete a User")
 public void removeUser (@Param(name="id") Long id)
 { 
	 if(id <= likeMovies.movies.size()){
	 likeMovies.removeUser(id);
	 }else{
		 System.out.println("That is not a valid User ID.");
	 }
 }
 
 @Command(description="Add a Movie")
 public void addMovie (@Param(name="title") String title, @Param(name="year") String year, @Param(name="url") String url)
 {
 likeMovies.addMovie(title, year, url);
 }

 @Command(description="Get all users details")
 public void getUsers ()
 {
   Collection<User> users = likeMovies.getUsers();
   System.out.println(users);
 }
 
 @Command(description="Save the current library of movies/users")
 public void save() throws Exception
 {
   likeMovies.store();
   
 }
 
 @Command(description="Get all users detail")
 public void read() throws Exception
 {
   likeMovies.load();
   
 }
 
 @Command(description="Get a users details")
 public void getUser(@Param(name="indexNumber") int i) throws Exception
 {
   System.out.println(likeMovies.getUsers().get(i));
   
 }
 @Command(description="Get the Top 10 Movies")
 public void getTop10() throws Exception
 {
	 if(likeMovies.getTopTenMovies().size() > 10){
		  for(int i=0 ; i<10; i++)
		 System.out.print(likeMovies.getTopTenMovies().get(i));
	   }else{
	 System.out.println(likeMovies.getTopTenMovies());
	   }
 }
 
 @Command(description="Gets all the ratings from a specific user")
 public void getUserRating(@Param(name="userID") int userID) 
 {
  System.out.print(likeMovies.getUserRating(userID));
  
   
 }
 
 @Command(description="Adds a rating to a movie")
 public void  addRating(@Param(name="userID") int userID, @Param(name="movieID") int movieID, @Param(name="rating") int rating)
 {
   likeMovies.addRating(userID,movieID, rating);
   
 }
 @Command(description="Prints the ratings")
 public void  printRatings()
 {
   
	 System.out.print(likeMovies.getRatings());
   
 }
 
 @Command(description="Get the user reccomendations.")
 public void   getUserRecommendations(@Param(name="userID") long userID)
 {
   System.out.println("Based on the the users previous ratings the movie I would reccomend is " + likeMovies.getUserRecommendations(userID).get(0));
   
 }

 
 

}