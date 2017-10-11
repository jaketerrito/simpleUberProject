package uberV1;
import java.util.*;
import java.awt.Point;

public class Manager {
	private HashMap<String,Driver> drivers = new HashMap<String,Driver>();
	private HashMap<String,Client> clients = new HashMap<String,Client>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	
	/**
	 * Initializes manager with out any users.
	 */
	public Manager(){}
	
	/**
	 * Initializes manager with users and rides.
	 * @param drivers the list of drivers being managed.
	 * @param clients the list of clients being managed.
	 * @param rides the list of rides being managed.
	 */
	public Manager(HashMap<String,Driver> drivers, HashMap<String,Client> clients, ArrayList<Ride> rides){
		this.drivers = drivers;
		this.rides = rides;
		this.clients = clients;
	}
	
	public void addDriver(Driver driver){
		drivers.put(driver.getName(),driver);
	}
	
	public void addClient(Client client){
		clients.put(client.getName(),client);
	}
	
	public Client getClient(String name){
		return clients.get(name);
	}
	
	public Driver getDriver(String name){
		return drivers.get(name);
	}
	
	public Ride receiveRequest(Client client, Point destination){
		Ride ride = new Ride(destination, client);
		//createQueue
		//request everyone in queue
		//ride addDriver(driver
		//set ride for driver
		//charge client, handle if low balance
		return ride; //sends to client
	}
	
	public boolean transaction(Ride ride){
		//return false if client balance is too low
		//otherwise charge client and give driver their share.
		return true;
	}
	
	public void checkRides(){
		//sees if rides are still ongoing
		//if ended prompt user for a rating.
		//update location of client and driver
	}
	
	public void getRating(Ride ride){
		//requests rating from a client
		//updates rating of driver
	}
}
