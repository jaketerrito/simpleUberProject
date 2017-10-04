package uberV1;

import java.util.ArrayList;
/**
 * The client using the uber app.
 * @author jterrito
 *
 */
public class Client {
	private String name;
	private int[] location;
	private double balance;
	private Manager manager;
	private Ride currentRide;
	/**
	 * Creates a client with a random location.
	 * @param name The name of the client.
	 * @param balance The intial starting balance.
	 */
	public Client(Manager manager, String name, double balance){

	}
	
	/**
	 * Creates a client.
	 * @param name The name of the client.
	 * @param balance The intial starting balance.
	 * @param location The client's starting location.
	 */
	public Client(Manager manager, String name, double balance, int[] location){

	}
	
	public void request(int[] destination){
		this.currentRide = manager.receiveRequest(this, destination);
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
	
	public void  setRide(Ride ride){
		this.currentRide = ride;
	}
}
