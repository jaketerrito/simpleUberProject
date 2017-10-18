package uberV1;

import java.awt.Point;
import java.util.Scanner;

/**
 * A client using the uber service.
 * @author jterrito
 *
 */
public class Client extends User{
	
    /**
     * Initializes client at random location.
     *  @param manager The manager for this client.
     *  @param name The client's name.
     *  @param balance The client's initial balance.
     */
	public Client(Manager manager, String name, double balance){
           super(manager,name,balance);
	}

	/**
	 * Initializes client at given location.
	 * @param manager the manager for this client.
	 * @param name the client's name.
	 * @param balance the client's initial balance.
	 * @param location the client's initial location.
     */
	public Client(Manager manager, String name, double balance, Point location){
           super(manager,name,balance,location);
	}
	
	/**
	 * Requests a ride.
	 * @param destination The desired destination for the ride.
	 * @return Whether or not the request succeeded.
	 */
	public boolean request(Point destination){
		if(destination.getY() > GRIDMAX || destination.getX() > GRIDMAX || destination.getX() < 0 || destination.getY() < 0){
			System.out.printf("Invalid Request: Destination out of bounds.");
			return false;
		}
		currentRide = manager.receiveRequest(this, destination);
		if(currentRide == null){
			return false;
		}
		return true;
	}
	
	/**
	 * Rates a driver.
	 * @param driver The driver being rated.
	 * @param scanner The input method for the client's response.
	 */
	public void rate(Driver driver,Scanner scanner){
		String ans;
		while(true){
			try{
				System.out.printf("%s: Please give a rating for your driver, %s.(0.0-5.0)\n",name,driver.getName());
				ans = scanner.nextLine();
				if(Double.valueOf(ans) >= 0 && Double.valueOf(ans) <= 5){
					driver.addRating(Double.valueOf(ans));
					return;
				}
			}catch(NumberFormatException e){}
		}
	}
	
}
