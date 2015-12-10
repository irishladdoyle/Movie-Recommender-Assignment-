package models;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import utils.ToJsonString;

/**
 * 
 * @author Jamie
 *The User methods for the command line interface
 */
public class User {
	private static Long counter = 0l;

	private Long id;
	private String firstName;
	private String lastName;
	private String age;
	private String gender;
	private String occupation;

	private ArrayList<Rating> ratings = new ArrayList<>();



	public User() {
	}

	public User(String firstName, String lastName, String age, String gender,
			String occupation) {
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;

	}
	/**
	 * The  JsonString allows for an easier way of doing a toString
	 */
	public String toString() {
		return new ToJsonString(getClass(), this).toString();
	}

	/**
	 * HashCode takes the data stored in an instance of the class into a single hash value
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.lastName, this.firstName, this.age,
				this.gender, this.occupation);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User other = (User) obj;
			return Objects.equal(firstName, other.firstName)
					&& Objects.equal(lastName, other.lastName)
					&& Objects.equal(age, other.age)
					&& Objects.equal(gender, other.gender)
					&& Objects.equal(occupation, other.occupation);

		} else {
			return false;
		}
	}
	/**
	 * 
	 * @return
	 * Getters for the instance variables
	 */
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAge() {
		return age;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getGender() {
		return gender;
	}

	public long getUserId() {
		return id;
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}


	/**
	 * 
	 * @param rating
	 * Checks for duplicate records and removes them
	 */
	public void addRating(Rating rating) {
		long movieId = rating.getMovieID();

		//Check and record duplicates
		ArrayList<Integer> duplicateMovieIndices = new ArrayList<>();
		for(Rating storedRating: ratings) {
			if(storedRating.getMovieID() == movieId) {
				//ratings.remove(storedRating);
				int indexOfDuplicate = ratings.indexOf(storedRating);
				duplicateMovieIndices.add(indexOfDuplicate);
			}
		}

		//Remove duplicates
		for(int index: duplicateMovieIndices) {
			ratings.remove(index);
		}

		//Add rating
		ratings.add(rating);
	}

	/**
	 * 
	 * @return
	 * Counter method
	 */
	public static long getCounter() {
		return counter;
	}

	public static void setCounter(long nCounter) {
		counter = nCounter;
	}

	//Assuming that if a user has rated a movie meaning he/she has seen it
	public boolean checkIfUserHasSeenAMovie(long movieId) {
		for(Rating rating: ratings) {
			if(rating.getMovieID() == movieId) {
				return true;
			}
		}
		return false;
	}
}

