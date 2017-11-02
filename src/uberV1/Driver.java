package uberV1;
import java.util.*;
import java.awt.Point;

/**
 * A driver working for the uber service.
 * @author jterrito
 *
 */
public class Driver extends User{
	private ArrayList<Double> ratings = new ArrayList<Double>();
    
	/**
	 * Initializes driver at a random location with a single rating.
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
	 * Initializes Driver at a specified location with a single rating.
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
	 * Passed a ride request with input method to determine driver's response to request.
	 * @param ride The current ride being requested.
	 * @param scanner The input for driver's response.
	 * @return Whether or not the ride was accepted.
	 */
	public boolean handleRequest(Ride ride, Scanner scanner){
		if(currentRide != null){
			System.out.printf("Driver already has a ride, something is wrong in this code.");
			return false;
		}
		System.out.printf("%s: You're at (%.0f,%.0f).\n",name,location.getX(),location.getY());
		System.out.printf("%s: %s is requesting a ride from (%.0f,%.0f) to (%.0f,%.0f). Do you accept ride?(y/n)\n",name,ride.getClient().getName(),ride.getPickup().getX(),ride.getPickup().getY(),ride.getDestination().getX(),ride.getDestination().getY());
		String ans = scanner.nextLine();
		while(!ans.equals("n")){
			if(ans.equals("y")){
				ride.addDriver(this);
				currentRide = ride;
				return true;
			}
			System.out.printf("%s: Response must be 'y' or 'n'.\n",name);
			ans = scanner.nextLine();
		}
		return false;
	}
	
	public boolean isAvailable(){
		if(currentRide != null){
			return false;
		}
		return true;
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
