# MovieRecomender
My movie recommender contains a CVS Loader to read in the movies, users , genres and ratings data files and create objects of them
to be populated into corresponding Arraylists.

My top 10 will return the top 10 rated films but only if they are posistively rated, thus in the small data set only five are returned
because the other 5 are negatively rated which I feel should not be returned. It would return the top 10 of the large data set but because
I used two different ArrayLists for Ratings and Movies the application becomes very slow as the size of the ratings arraylist is huge. 
Looking back I should of used a more appropriate method of data storage but I was comfortable using ArrayLists. 

The application can serialize in 4 array save them and load them back out to the User. I had trouble serializing the Long ID number
so my application does contain the User ID bug.

My recomendation for a user works by sorted their ratings, pick the highest rating and finding the movie it refers to. I then use this movie
as a refernce to cross reference with the movies array to find a movie that has the same genres and is the highest rated one which is not 
the original one the user rated up. I feel this is a bit better than just returning them the highest one as this matches the genres they 
enjoy.

