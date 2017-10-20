package uberV1;

import java.awt.Point;

/**
 * Information about the current trip.
 * @author jterrito
 *
 */
public class Ride {
	private Point destination = null;
	private Point pickup = null;
	private Client client = null;
	private Driver driver = null;
	private double price = -1;
	private double rate;
	private double time;
	
	/**
	 * Initializes ride for client with given destination. 
	 * @param destination The destination for this ride.
	 * @param client The user requesting the ride.
	 * @param rate Price rate used for price calculation.
	 * @param time Time rate used for time calculation.
	 */
	public Ride(Point destination, Client client, double rate, double time){
		this.rate = rate;
		this.time = time;
		this.destination = destination;
		this.client = client;
		price = (client.distance(destination) * rate);
		pickup = client.getLocation();
	}
	
	/**
	 * Assigns driver and calculates the price.
	 * @param driver The driver for this ride.
	 */
	public void addDriver(Driver driver){
		this.driver = driver;
		price = (driver.distance(client.getLocation()) + client.distance(destination)) * rate;
	}
	
	public Driver getDriver(){
		return driver;
	}
	
	public Client getClient(){
		return client;
	}
	
	public Point getPickup(){
		return pickup;
	}
	
	public Point getDestination(){
		return destination;
	}
	
	/**
	 * Gives information on the status of this ride.
	 * @return Returns whether the driver is on their way, picking up the user, on route to the destination, or has completed the ride.
	 */
	public String getStatus(){
		if(client.getLocation().equals(destination)){
			return "DONE";
		}
		if(driver.getLocation().equals(pickup)){
			return "PICKUP"; 
		}
		if(!(driver.getLocation().equals(client.getLocation()))){
			return "COMING";
		}
		return "ENROUT";
	}
	
	/**
	 * Gives the time until the driver reaches the pickup location.
	 * @return
	 */
	public double estimateWait(){
		return driver.distance(client.getLocation()) * time;
	}
	
	/**
	 * Gives the drive time from the client to the destination.
	 * @return
	 */
	public double estimateTravelTime(){
		return client.distance(destination) * time;
	}
	
	public double getPrice(){
		if(driver == null){
			System.out.printf("No driver assigned.\n");
		}
		return price;
	}

	/**
	 * Removes references to this ride by client and driver.
	 */
	public void endRide(){
		driver.setRide(null);
		client.setRide(null);
	}
}
