package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



//import controllers.PacemakerAPI;
import models.Activity;
import models.Location;
import models.Movie;
import models.User;
import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;

public class MovieRecommenderAPITest
{
  private MovieRecommenderAPI movapi;

  @Before
  public void setup()
  {
	  movapi = new MovieRecommenderAPI();
    for (User user : users)
    {
    	movapi.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
    }
  }
  

  @Test
  public void testUser()
  {
    assertEquals (users.length, movapi.getUsers().size());
    movapi.createUser("jamie", "doyle", "jamie@gmail.com", "20", "male", "student", "secret");
    assertEquals (users.length+1, movapi.getUsers().size());
    assertEquals (users[0], movapi.getUserByEmail(users[0].email));
  }  

  @Test
  public void testUsers()
  {
    assertEquals (users.length, movapi.getUsers().size());
    for (User user: users)
    {
      User eachUser = movapi.getUserByEmail(user.email);
      assertEquals (user, eachUser);
      assertNotSame(user, eachUser);
    }
  }

  @Test
  public void testDeleteUsers()
  {
    assertEquals (users.length, movapi.getUsers().size());
    User joe = movapi.getUserByEmail("joe@bloggs.com");
    movapi.deleteUser(joe.id);
    assertEquals (users.length-1, movapi.getUsers().size());    
  }  
} 


