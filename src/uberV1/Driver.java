package uberV1;
import java.util.*;

public class Driver extends User{
	private boolean available;
	private ArrayList<Double> ratings;
	
	public Driver(Manager manager, String name, double balance, double rating){
            this.super(manager,name,balance);
            ratings.add(rating);
	}
	
	public Driver(Manager manager, String name, double balance, double rating, int[] location){
           this.super(manager,name,balance,location);
           ratings.add(rating);
	}
	
	
	public boolean handleRequest(){
		//return true if accepts ride (driver would you like to accept this ride?y/n
		//return false otherwise
		return false;
	}
	
	
	public void addRating(double rating){
		ratings.add(rating);
	}
	
	public double getRating(){
		//average of all ratings
		return 1;
	}
	
}
