package uberV1;
import java.util.*;

public class Manager {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Client> clients = new ArrayList<Client>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	
	public Manager(){
		
	}
	
	public void addDriver(Driver driver){
		
	}
	
	public void addClient(Client client){
		
	}

	public Ride receiveRequest(Client client, int[] destination){
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
