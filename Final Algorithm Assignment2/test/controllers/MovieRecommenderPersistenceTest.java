package controllers;
import controllers.movapiAPI;
import static org.junit.Assert.*;

import java.io.File;


import models.User;

import org.junit.Test;

import utils.Serializer;
import utils.XMLSerializer;


public class MovieRecommenderPersistenceTest
{
  movapiAPI paceapi;
  
  void populate (movapiAPI paceapi)
  {
    for (User user : users)
    {
      movapi.createUser(user.firstName, user.lastName, user.age, user.gender);
    }
  }
  
  @Test
  public void testPopulate()
  { 
	  paceapi = new movapiAPI(null);
    assertEquals(0, paceapi.getUsers().size());
    populate (paceapi);
  }

  void deleteFile(String fileName)
  {
    File datastore = new File ("testdatastore.xml");
    if (datastore.exists())
    {
      datastore.delete();
    }
  }
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    //deleteFile (datastoreFile);
    
    Serializer serializer = new XMLSerializer(new File (datastoreFile));
    
    movapi = new movapiAPI(serializer); 
    populate(movapi);
    movapi.store();
    
    movapiAPI movapi2 =  new movapiAPI(serializer);
    movapi2.load();
    
    assertEquals (movapi.getUsers().size(), movapi2.getUsers().size());
    for (User user : movapi.getUsers())
    {
      assertTrue (movapi2.getUsers().contains(user));
    }
    //deleteFile ("testdatastore.xml");
  }
  
  
}
