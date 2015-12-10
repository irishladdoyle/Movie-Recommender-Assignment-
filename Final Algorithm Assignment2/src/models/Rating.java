package models;

import utils.ToJsonString;

/**
 * 
 * @author Jamie
 * Constructor for the Rating class
 *
 */

public class Rating {

	private long userID;
	private long movieID;
	private int ratingScores;

	public Rating(long userID, long movieID, int ratingScores) {
		this.userID = userID;
		this.movieID = movieID;
		this.ratingScores = ratingScores;
	}

	/**
	 * 
	 * @return
	 * Getters the instance variables
	 */
	public long getUserID() {
		return userID;
	}

	public long getMovieID() {
		return movieID;
	}

	public int getRatingScores() {
		return ratingScores;
	}

	public String toString()
	{
		return new ToJsonString(getClass(), this).toString();
	}
}
