package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static models.Fixtures.users;

public class MovieTest
{

Movie movie1 = new movies ("title", "year", "url");

  @Test
  public void testCreate()
  {
    assertEquals ("title",            movie1.title);
    assertEquals ("year",             movie1.year);
    assertEquals ("url",              movie1.url);
  }

  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
	for (Movie movie1 : Movie)
    {
      ids.add(movie1.id);
    }
  }

  @Test
  public void testToString()
  {
    assertEquals ("Movie{" + movie1.id + ", title, year, url}", movie1.toString());
  }
  
  @Test
  public void testEquals()
  {
    Movie movie1 = new Movie ("title", "year", "url"); 


    assertEquals(movie1, movie1);
    assertEquals(movie1, movie1);
  } 
  
}
