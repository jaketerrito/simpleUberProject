package uberV1;

import java.awt.Point;

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
		this.currentRide = manager.receiveRequest(this, destination);
	}
	
}
