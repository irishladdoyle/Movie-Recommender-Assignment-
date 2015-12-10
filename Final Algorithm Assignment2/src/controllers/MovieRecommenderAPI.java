package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import utils.Serializer;
import models.Movie;
import models.Rating;
import models.User;

/**
 * 
 * @author Jamie
 * This is the MovieRecommenderAPI.
 *
 */
public class MovieRecommenderAPI {
	private Serializer serializer;


	/**
	 * The Hashmap is a data structure used to implement an associative array, 
	 * a structure that can map keys to values
	 */

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<Long, Movie> movieIndex = new HashMap<>();

	/**
	 * 
	 * @param serializer
	 * The constructor for the MovieRecommenderAPI
	 */
	public MovieRecommenderAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		User.setCounter((long) serializer.pop());
		Movie.setCounter((long) serializer.pop());
		movieIndex = (Map<Long, Movie>) serializer.pop();
		userIndex = (Map<Long, User>) serializer.pop();
	}

	public void store() throws Exception {
		serializer.push(userIndex);
		serializer.push(movieIndex);
		serializer.push(Movie.getCounter());
		serializer.push(User.getCounter());
		serializer.write();
	}

	public Collection<User> getUsers() {
		return userIndex.values();
	}

	public void deleteUsers() {
		userIndex.clear();

	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param occupation
	 * @return
	 * Method add a new user
	 */
	public User createUser(String firstName, String lastName, String age,
			String gender, String occupation) {
		User user = new User(firstName, lastName, age, gender, occupation);
		if (userIndex.containsValue(user)) {
			System.out.println("User duplicate");
		} else {
			userIndex.put(user.getUserId(), user);
		}
		return user;
	}

	/**
	 * 
	 * @param title
	 * @param year
	 * @param url
	 * @return
	 * Method to add a new movie
	 */

	public Movie addMovie(String title, String year, String url) {
		Movie movie = new Movie(title, year, url);
		if (movieIndex.containsValue(movie)) {
			System.out.println("Movie duplicate");
		} else {
			movieIndex.put(movie.getId(), movie);
		}
		return movie;
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * 
	 * Method to search for a user using the first name and last name.
	 * Compared the lowercase match name to both first and last name
	 */

	public ArrayList<User> searchForUser(String firstName, String lastName) {
		ArrayList<User> toReturn = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>(userIndex.values());
		for (User user : users) {
			boolean fNameMatch = user.getFirstName().toLowerCase()
					.compareTo(firstName.toLowerCase()) == 0; // 0 means equal
			boolean lNameMatch = user.getLastName().toLowerCase()
					.compareTo(lastName.toLowerCase()) == 0;
			if (fNameMatch && lNameMatch)
				toReturn.add(user);
		}
		return toReturn;
	}

	/**
	 * 
	 * @param title
	 * @return
	 * 
	 * Similiar to the search user method it alows users to search
	 * for movies and compare it to the lowercase.
	 */

	public ArrayList<Movie> searchForMovie(String title) {
		ArrayList<Movie> toReturn = new ArrayList<>();
		ArrayList<Movie> movies = new ArrayList<>(movieIndex.values());
		for (Movie movie : movies) {
			boolean tNameMatch = movie.getTitle().toLowerCase()
					.compareTo(title.toLowerCase()) == 0; // 0 means equal
			if (tNameMatch)
				toReturn.add(movie);
		}
		return toReturn;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * 
	 * This method allow the user to get other users using the 
	 * usersID
	 */

	public User getUser(long userId) {
		if (userIndex.containsKey(userId))
			return userIndex.get(userId);
		else
			return null;
	}

	/**
	 * 
	 * @param id
	 * This method allows the user to delete a user using the user id
	 */
	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
	}

	/**
	 * 
	 * @param movieId
	 * @return
	 * Allows the user to get a movie in the command line interface
	 * using it's ID
	 */
	public Movie getMovie(long movieId) {
		if (movieIndex.containsKey(movieId))
			return movieIndex.get(movieId);
		else
			return null;
	}

	/**
	 * 
	 * @param userID
	 * @param movieID
	 * @param ratingScore
	 * This method allows the user to add a rating provided it is correspondanet
	 * to on of the rating scores in the rating score range
	 */
	public void addRating(long userID, long movieID, int ratingScore) {
		if (movieIndex.containsKey(movieID) && userIndex.containsKey(userID)) {

			boolean ratingScoreIsInRange = true;
			switch (ratingScore) {
			case -5:
				;
				break;
			case -3:
				;
				break;
			case -1:
				;
				break;
			case 0:
				;
				break;
			case 1:
				;
				break;
			case 3:
				;
				break;
			case 5:
				;
				break;
			default:
				ratingScoreIsInRange = false;
				break;
			}
			if (ratingScoreIsInRange) {
				Rating rating = new Rating(userID, movieID, ratingScore);
				User user = userIndex.get(userID);
				user.addRating(rating);
				Movie movie = movieIndex.get(movieID);
				movie.addRating(rating);
			}
		} else {
			System.out.println("User ID or Movie ID doesn't exist");
		}
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * This method allows the user to get the user ratings using the user ID
	 */

	public ArrayList<Rating> getUserRatings(long userId) {
		if (userIndex.containsKey(userId)) {
			User user = userIndex.get(userId);
			return user.getRatings();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * This method allows the users to get the top 10 rated movies
	 * 
	 */
	public ArrayList<Movie> getTopTenMovies() {
		ArrayList<Movie> topTenMovies = new ArrayList<>();

		// Retrieve all movies in to a list
		ArrayList<Movie> movies = new ArrayList<>(movieIndex.values());

		// sort the list according to the average point of each movie
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie m1, Movie m2) {
				if (m1.getAverageRatingScore() < m2.getAverageRatingScore())
					return 1; // meaning that movie with the higher score is in
				// higher order
				if (m1.getAverageRatingScore() > m2.getAverageRatingScore())
					return -1;
				return 0;
			}
		});

		// only take 10 of the sorted list (only if there are more than 10
		// movies in the top list)
		if (movies.size() >= 10) {
			for (int i = 0; i <= 9; i++) {
				topTenMovies.add(movies.get(i));
			}
		}

		return topTenMovies;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * Finally this method is allows the user to get the most recommend movies. 
	 * It will return the top 5 movies
	 */

	public ArrayList<Movie> getRecommendation(long userId) {

		ArrayList<Movie> toRecommend = new ArrayList<>();
		if (userIndex.containsKey(userId)) {
			User user = userIndex.get(userId);

			// Retrieve all movies in to a list
			ArrayList<Movie> movies = new ArrayList<>(movieIndex.values());
			System.out.println(movieIndex.size());
			// sort the list according to the average point of each movie
			Collections.sort(movies, new Comparator<Movie>() {
				@Override
				public int compare(Movie m1, Movie m2) {
					if (m1.getAverageRatingScore() < m2.getAverageRatingScore())
						return 1; // meaning that movie with the higher score is
					// in
					// higher order
					if (m1.getAverageRatingScore() > m2.getAverageRatingScore())
						return -1;
					return 0;
				}
			});

			int count = 1;
			for(Movie movie: movies) {
				if(movie.getAverageRatingScore() > 0) {
					boolean userHasSeenIt = user.checkIfUserHasSeenAMovie(movie.getId());
					if(!userHasSeenIt) {
						toRecommend.add(movie);
					}
				}
				count++;
				if(count > 5)
					break;
			}
		}
		return toRecommend;
	}
}
