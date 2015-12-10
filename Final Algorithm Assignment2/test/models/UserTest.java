package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static models.Fixtures.users;

public class UserTest
{
  User user1 = new User ("firstName", "lastName", "age", "gender", "occupation", "email", "password");

  @Test
  public void testCreate()
  {
    assertEquals ("firstName",            user1.firstName);
    assertEquals ("lastName",             user1.lastName);
    assertEquals ("age",                  user1.age);
    assertEquals ("gender",               user1.gender);
    assertEquals ("occupation",           user1.occupation);
  }


  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("User{" + user1.id + ", firstName, lastName, age, gender, occupation, email, password}", user1.toString());
  }
  
  @Test
  public void testEquals()
  {
    User user1 = new User ("jamie", "doyle", "20", "male", "student", "jamie@gmail.com", "secret"); 
    
    assertEquals(user1, user1);
    assertEquals(user1, user1);
    assertNotEquals(user1, user1);
  } 
  
}
