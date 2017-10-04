package uberV1;
import java.util.*;

public class Driver {
	private String name;
	private int[] location;
	private double balance;
	private boolean available;
	private ArrayList<Double> ratings;
	private Manager manager;
	private Ride currentRide;
	
	public Driver(Manager manager, String name, double balance, double rating){
		
	}
	
	public Driver(Manager manager, String name, double balance, double rating, int[] location){
		
	}
	
	public int[] getLocation(){
		return location;
	}
	
	public void setLocation(int[] location){
		this.location = location;
	}

	public Ride getRide(){
		return currentRide;
	}
	
	public boolean handleRequest(){
		//return true if accepts ride (driver would you like to accept this ride?y/n
		//return false otherwise
		return false;
	}
	
	public void  setRide(Ride ride){
		this.currentRide = ride;
	}
	
	public void addRating(double rating){
		ratings.add(rating);
	}
	
	public double getRating(){
		//average of all ratings
		return 1;
	}
	
}
