package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Optional;

import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.Rating;
import models.User;

/**
 * 
 * @author Jamie Doyle Student Number 20067808 This is the constructor for the
 *         Main Class where the Command line interface can be ran
 */

public class Main {
	public MovieRecommenderAPI movapi;

	public ArrayList<Movie> movies;

	/**
	 * 
	 * @throws Exception
	 *             An Exceptions provide the means to separate the details of
	 *             what to do when something out of the ordinary happens from
	 *             the main logic of a program. This is done to allow
	 */
	public Main() throws Exception {
		File datastore = new File("Movie_Data Files/datastore.xml"); //
		Serializer serializer = new XMLSerializer(datastore);

		movapi = new MovieRecommenderAPI(serializer);

		if (datastore.isFile()) {
			movapi.load();
		}
	}

	/**
	 * 
	 * The following command allows the user to retrieve all the users if there
	 * is any.
	 * 
	 */
	@Command(description = "Get all users details")
	public void getUsers() {
		Collection<User> users = movapi.getUsers();
		System.out.println(users);
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param occupation
	 * 
	 *            This command allows the user to add a new user by filling in
	 *            the required details in the command line interface.
	 */

	@Command(description = "Add a User")
	public void createUser
	(@Param(name = "first name") String firstName,
			@Param(name = "last name") String lastName,
			@Param(name = "age") String age,
			@Param(name = "gender") String gender,
			@Param(name = "occupation") String occupation) {
		movapi.createUser(firstName, lastName, age, gender, occupation);
	}

	/**
	 * 
	 * @param userID
	 * 
	 *            This command allows the user to find a single user by using the
	 *            user's ID. All users are assigned and automatic Id once they
	 *            have been added
	 */

	@Command(description = "Get a Users detail")
	public void getUser
	(@Param(name = "userID") long userID) {
		User user = movapi.getUser(userID);
		System.out.println(user);
	}

	/**
	 * 
	 * @param movieID
	 * 
	 *            The get movie detail command is identical to the previous the get User
	 *            detail. The movie's are all assigned an Id.
	 */
	@Command(description = "Get a movie detail")
	public void getMovie
	(@Param(name = "movieID") long movieID) {
		Movie movie = movapi.getMovie(movieID);
		if (movie == null)
			System.out.println("Invalid ID");
		else
			System.out.println(movie);
	}

	/**
	 * 
	 * @param userID
	 * 
	 *            The following command allows the user to delete other users. It
	 *            is done by using the userID.
	 */
	@Command(description = "Delete a User")
	public void deleteUser
	(@Param(name = "userID") long userID) {
		Optional<User> user = Optional.fromNullable(movapi.getUser(userID));
		if (user.isPresent()) {
			movapi.deleteUser(user.get().getUserId());
		}
	}

	/**
	 * 
	 * @param title
	 * @param year
	 * @param url
	 * 
	 *            The add movie command allows the user to add movies by using the
	 *            above details in the command line interface.
	 */

	@Command(description = "Add a Movie")
	public void addMovie
	(@Param(name = "title") String title,
			@Param(name = "year") String year, 
			@Param(name = "url") String url) {

		movapi.addMovie(title, year, url);
	}

	/**
	 * 
	 * @param userID
	 * @param movieID
	 * @param ratingScore
	 * 
	 *            This command allows the user to add a rating to a movie in the
	 *            command line interface.
	 */
	@Command(description = "Add a Rating")
	public void addRating
	(@Param(name = "userID") long userID,
			@Param(name = "movieID") long movieID,
			@Param(name = "score") int ratingScore) {
		movapi.addRating(userID, movieID, ratingScore);
	}

	/**
	 * 
	 * This command allows the user to print off the top 10 rated movies.
	 * 
	 */
	@Command(description = "getTopTenMovies")
	public void getTopTenMovies() {
		ArrayList<Movie> toptenmov = movapi.getTopTenMovies();
		if (toptenmov.size() == 0)
			System.out.println("Nothing");
		else {
			for (Movie movie : toptenmov) {
				System.out.println(movie.getTitle());
			}
		}
	}

	/**
	 * 
	 * @param userID
	 *            Allows the user to get the users ratings.
	 */
	@Command(description = "Get user's Ratings")
	public void getUserRatings(@Param(name = "userID") long userID) {
		ArrayList<Rating> userRatings = movapi.getUserRatings(userID);
		if (userRatings == null)
			System.out.println("User ID invalid");
		else {
			for (Rating rating : userRatings) {
				System.out.println(rating);
			}
		}
	}

	/**
	 * 
	 * @param title
	 * 
	 *            Allows the user to search for movies that have been stored
	 */
	@Command(description = "Search movie(s)")
	public void searchForMovie(@Param(name = "title") String title) {
		ArrayList<Movie> searchingResult = movapi.searchForMovie(title);
		if (searchingResult.size() == 0) {
			System.out.println("Not found");
		} else {
			for (Movie movie : searchingResult) {
				System.out.println(movie);
			}
		}

	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * 
	 *            Allows the user to search for other users that have been stored
	 */
	@Command(description = "Search user(s)")
	public void searchForUser
	(@Param(name = "first name") String firstName,
			@Param(name = "last name") String lastName) {
		ArrayList<User> searchingResult = movapi.searchForUser(firstName,
				lastName);
		if (searchingResult.size() == 0) {
			System.out.println("Not found");
		} else {
			for (User user : searchingResult) {
				System.out.println(user);
			}
		}
	}

	/**
	 * 
	 * @throws Exception
	 *             An Exceptions provide the means to separate the details of
	 *             what to do when something out of the ordinary happens from
	 *             the main logic of a program. This is done to allow
	 * 
	 * This is the opening line of the command line interface. Users can then
	 * place their command requests into the interface. If they need help thy
	 * can type ?list-all or ?la to see a list of the available commands
	 */

	public static void main(String[] args) throws Exception {
		Main main = new Main();

		Shell shell = ShellFactory.createConsoleShell("pm",
				"Welcome to Movie Recommender - ?help for instructions", main);
		shell.commandLoop();

		main.movapi.store();
	}

	/**
	 * 
	 * @throws Exception
	 * Allows the user to load from the XML file where there information is 
	 * stored.
	 * 
	 */
	@Command(description = "Load from xml")
	public void loadXml() throws Exception {
		movapi.load();
	}

	/**
	 * 
	 * @throws Exception
	 * Allows the user to save their information to the XML file.
	 */
	@Command(description = "Save to xml")
	public void writeXml() throws Exception {
		movapi.store();
	}
	/**
	 * 
	 * @param userID
	 * @throws Exception
	 * Allows the use to get the top 5 recommended movies
	 */
	@Command(description = "Get recommendation")
	public void getRecommendation(@Param(name = "User ID") long userID)
			throws Exception {
		ArrayList<Movie> movies = movapi.getRecommendation(userID);
		if (movies.size() == 0)
			System.out.println("No recommendation");
		else {
			for (Movie movie : movies) {
				System.out.println(movie.getTitle());
			}
		}
	}
}
