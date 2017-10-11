package uberV1;
import java.util.*;
import java.awt.Point;

public class Driver extends User{
	private boolean available;
	private ArrayList<Double> ratings;
	
	/**
	 * Initializes driver at a random location.
	 * @param manager the manager for this driver.
	 * @param name the driver's name.
	 * @param balance the driver's balance.
	 * @param rating the driver's rating.
	 */
	public Driver(Manager manager, String name, double balance, double rating){
            super(manager,name,balance);
            ratings.add(rating);
	}
	
	/**
	 * Initializes Driver at a specified location.
	 * @param manager the manager for this driver.
	 * @param name the driver's name.
	 * @param balance the driver's balance.
	 * @param rating the driver's rating.
	 * @param location the driver's starting location.
	 */
	public Driver(Manager manager, String name, double balance, double rating, Point location){
           super(manager,name,balance,location);
           ratings.add(rating);
	}
	
	/**
	 * Passed a ride request and either accepts or declines the request.
	 * @return whether or not the ride was accepted.
	 */
	public boolean handleRequest(){
		//return true if accepts ride (driver would you like to accept this ride?y/n
		//return false otherwise
		return false;
	}
	
	/**
	 * Adds a new rating.
	 * @param rating the given rating.
	 */
	public void addRating(double rating){
		ratings.add(rating);
	}
	
	/**
	 * Gives the driver's current rating.
	 * @return average of all past ratings.
	 */
	public double getRating(){
		//average of all ratings
		return 1;
	}
	
}
