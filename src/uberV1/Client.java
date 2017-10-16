package uberV1;

import java.awt.Point;
import java.util.Scanner;

/**
 * The client using the uber app.
 * @author jterrito
 *
 */
public class Client extends User{
	
    /**
     * Initializes client at random location.
     *  @param manager the manager for this client.
     *  @param name the client's name.
     *  @param balance the client's initial balance.
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
	 * @param destination the desired destination for the ride.
	 */
	public void request(Point destination){
		if(destination.getY() > GRIDMAX || destination.getX() > GRIDMAX || destination.getX() < 0 || destination.getY() < 0){
			System.out.printf("Invalid Request: Destination out of bounds.");
			return;
		}
		this.currentRide = manager.receiveRequest(this, destination);
	}
	
	public void rate(Driver driver,Scanner scanner){
		System.out.printf("Please give a rating for your driver, %s.(0.0-5.0)\n",driver.getName());
		//make sure that it is a valid response
		driver.addRating(scanner.nextDouble());
	}
	
}
