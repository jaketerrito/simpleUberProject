package uberV1;

import java.awt.Point;
import java.lang.Math;

/**
 * Information about the current ride.
 * @author jterrito
 *
 */
public class Ride {
	private Point destination;
	private Client client;
	private Driver driver;
	private boolean ongoing;
	private double price;
	private double RATE = 2.5;
	private double TIME = 10;
	
	public Ride(Point destination, Client client){
		this.destination = destination;
		this.client = client;
	}
	
	public void addDriver(Driver driver){
		this.driver = driver;
		price = distance(driver.getLocation(),client.getLocation()) + distance(client.getLocation(),destination) * RATE;
	}
	
	
	public void updateStatus(boolean ongoing){
		this.ongoing = ongoing;
	}
	
	public boolean getStatus(){
		return ongoing;
	}
	
	public double estimateWait(){
		return travelTime(driver.getLocation(),client.getLocation());
	}
	
	public double distance(Point a, Point b){
		return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
	}
	
	public double travelTime(Point a, Point b){
		return distance(a,b) * TIME;
	}
	
	// only works initially
	public double price(){
		return price;
	}
	
	public void info(){
		System.out.printf("Driver: %s \nClient: %s \nPrice: %d \n Estimated Wait %d \nRemaining Time: %d", driver.getName(), client.getName(), price(), estimateWait(),travelTime(client.getLocation(),destination));
	}
	
}
