package uberV1;
import java.util.*;
import java.awt.Point;

public class Driver extends User{
	private boolean available = true;
	private ArrayList<Double> ratings = new ArrayList<Double>();
	
	/**
	 * Initializes driver at a random location.
	 * @param manager the manager for this driver.
	 * @param name the driver's name.
	 * @param balance the driver's balance.
	 * @param ratings the driver's ratings.
	 */
	public Driver(Manager manager, String name, double balance, ArrayList<Double> ratings){
            super(manager,name,balance);
            if(ratings != null){
            	this.ratings = ratings;
            }
	}
        
        public Driver(Manager manager, String name, double balance, rating){
            super(manager,name,balance);
            ratings.add(rating);
	}
	
	/**
	 * Initializes Driver at a specified location.
	 * @param manager the manager for this driver.
	 * @param name the driver's name.
	 * @param balance the driver's balance.
	 * @param ratings the driver's ratings.
	 * @param location the driver's starting location.
	 */
	public Driver(Manager manager, String name, double balance, ArrayList<Double> ratings, Point location){
           super(manager,name,balance,location);
           if(ratings != null){
           	this.ratings = ratings;
           }
    }
	
	/**
	 * Passed a ride request with input method to determine driver's response to request.
	 * @param ride the current ride to view
	 * @return whether or not the ride was accepted.
	 */
	public boolean handleRequest(Ride ride, Scanner scanner){
		if(currentRide != null){
			System.out.printf("Driver already has a ride, something is wrong in this code.");
			return false;
		}
		System.out.printf("%s %s: Do you accept ride?(y/n)\n",ride.info(),name);
		if(scanner.nextLine().equals("y")){
			available = false;
			ride.addDriver(this);
			currentRide = ride;
			return true;
		} 
		// maybe add feature in case it isn't "n"
		return false;
	}
	
	public boolean isAvailable(){
		return available;
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
		double total = 0;
		for(double rating : ratings){
			total += rating;
		}
		return total/(ratings.size());
	}
	
}
