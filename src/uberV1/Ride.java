package uberV1;

import java.awt.Point;

/**
 * Information about the current ride.
 * @author jterrito
 *
 */
public class Ride {
	private Point destination = null;
	private Client client = null;
	private Driver driver = null;
	private boolean ongoing = false;
	private double price = -1;
	private double RATE = 2.5;
	private double TIME = 10;
	
	public Ride(Point destination, Client client){
		this.destination = destination;
		this.client = client;
	}
	
	public void addDriver(Driver driver){
		this.driver = driver;
		price = (driver.distance(client.getLocation()) + client.distance(destination)) * RATE;
	}
	
	
	public void updateStatus(boolean ongoing){
		this.ongoing = ongoing;
	}
	
	public Driver getDriver(){
		return driver;
	}
	
	public Client getClient(){
		return client;
	}
	
	public boolean getStatus(){
		return ongoing;
	}
	
	public double estimateWait(){
		return driver.distance(client.getLocation()) * TIME;
	}
	
	public double estimateTravelTime(){
		return client.distance(destination) * TIME;
	}
	
	public double getPrice(){
		if(driver == null){
			System.out.printf("No driver assigned.");
		}
		return price;
	}
	
	public void info(){
		if(driver == null){
			System.out.printf("Client: %s \nPrice: %d \n Estimated Wait %d \nRemaining Time: %d", client.getName(), getPrice(), estimateTravelTime());
		} else {
		System.out.printf("Driver: %s \nClient: %s \nPrice: %d \n Estimated Wait %d \nRemaining Time: %d", driver.getName(), client.getName(), getPrice(), estimateWait(),estimateTravelTime());
		}
	}
	
	public void endRide(){
		driver.setRide(null);
		client.setRide(null);
		ongoing = false;
	}
}
