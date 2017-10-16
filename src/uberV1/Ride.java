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
		price = (client.distance(destination) * RATE);
	}
	
	public void addDriver(Driver driver){
		this.driver = driver;
		price = (driver.distance(client.getLocation()) + client.distance(destination)) * RATE;
		ongoing = true;
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
	
	public String info(){
		if(driver == null){
			return String.format("Client: %s \nPrice: %.2f \nTime to Destination: %.2f\n", client.getName(), getPrice(), estimateTravelTime());
		}
		return String.format("Driver: %s \nClient: %s \nPrice: %.2f \n Estimated Wait %.2f \nTime to Destination: %.2f\n", driver.getName(), client.getName(), getPrice(), estimateWait(),estimateTravelTime());
	}
	
	public void endRide(){
		driver.setRide(null);
		client.setRide(null);
		ongoing = false;
	}
}
