package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import utils.ToJsonString;

/**
 * 
 * @author Jamie Doyle
 * This is the constructor for the movie class
 */
public class Movie {
	private static Long counter = 0l;

	private Long id;
	private String title;
	private String year;
	private String url;

	private ArrayList<Rating> ratings = new ArrayList<>();

	public Movie(String title, String year, String url) {
		this.id = counter++;
		this.title = title;
		this.year = year;
		this.url = url;
	}

	/**
	 * The JsonString allows for an easier way of doing a toString
	 */
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}
	
	/**
	 * 
	 */
/**
 * HashCode takes the data stored in an instance of the class into a single hash value
 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.title, this.year, this.url);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Movie) {
			final Movie other = (Movie) obj;
			return Objects.equal(title, other.title)
					&& Objects.equal(year, other.year)
					&& Objects.equal(year, other.year);
		} else {
			return false;
		}
	}
/**
 * 
 * @return
 * The following  are getters and setters for the instance variables
 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	/**
	 * 
	 * @param rating
	 * Checks for duplicate recors and removes them
	 */
	public void addRating(Rating rating) {
		long userId = rating.getUserID();
		
		//Check and record duplicates
		ArrayList<Integer> duplicateUserIndices = new ArrayList<>();
		for(Rating storedRating: ratings) {
			if(storedRating.getUserID() == userId) {
				//ratings.remove(storedRating);
				int indexOfDuplicate = ratings.indexOf(storedRating);
				duplicateUserIndices.add(indexOfDuplicate);
			}
		}
		
		//Remove duplicates
		for(int index: duplicateUserIndices) {
			ratings.remove(index);
		}
		
		//Add rating
		ratings.add(rating);
	}
	
	/**
	 * 
	 * @return
	 * Gets the average rating score 
	 */
	public double getAverageRatingScore() {
		int sum = 0;
		
		//avoid divide by 0 error
		if(ratings.size() == 0)
			return 0;
		
		for(Rating rating: ratings) {
			sum += rating.getRatingScores();
		}
		
		double average = (sum/ratings.size());
		
		return average;
	}
	
	/**
	 * 
	 * @return
	 * Counter method getter and setter
	 */
	
	public static long getCounter() {
		return counter;
	}
	
	public static void setCounter(long nCounter) {
		counter = nCounter;
	}
}
