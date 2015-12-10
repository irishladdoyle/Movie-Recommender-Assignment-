package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;



public class RatingTest
{
  Rating rating = new Rating ("ratingID", "movieID", "ratingScore");

  @Test
  public void testCreate()
  {
    assertEquals ("ratingID",            rating1.ratingID);
    assertEquals ("movieID",             rating1.movieID);
    assertEquals ("ratingScore",         rating1.ratingScore);

  }


  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (rating rating : ratings)
    {
      ids.add(rating.id);
    }
    assertEquals (ratings.length, ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("rating{" + rating1.id + ", fratingID, movieID, ratingScore}", rating1.toString());
  }
  
  @Test
  public void testEquals()
  {
    rating rating1 = new rating ("ratingID", "movieID", "ratingScore"); 
    
    assertEquals(rating1, rating1);
    assertEquals(rating1, rating1);
    assertNotEquals(rating1, rating1);
  } 
  
}
