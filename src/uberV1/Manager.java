package uberV1;
import java.util.*;
import java.awt.Point;
/**
 * Handles all the overhead for requesting and tracking rides.
 * @author jterrito
 *
 */
public class Manager {
	private HashMap<String,Driver> drivers = new HashMap<String,Driver>();
	private HashMap<String,Client> clients = new HashMap<String,Client>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private Scanner scanner = new Scanner(System.in);
	protected Point tempPoint; //for sorting
	private double rate = 1;
	private double time = 1;
	/**
	 * Initializes manager with out any users.
	 */
	public Manager(){}
	
	public Manager(double rate, double time){
		this.rate = rate;
		this.time = time;
	}
	
	/**
	 * Initializes manager with special input.
	 * @param scanner Source for input.
	 */
	public Manager(Scanner scanner){
		this.scanner = scanner;
	}
	/**
	 * Initializes manager with preset values. For testing.
	 * @param drivers List of current drivers.
	 * @param clients List of current clients.
	 * @param rides List of current rides.
	 * @param scanner Source for input.
	 */
	public Manager(HashMap<String,Driver> drivers, HashMap<String,Client> clients, ArrayList<Ride> rides, Scanner scanner){
		this.drivers = drivers;
		this.rides = rides;
		this.clients = clients;
		this.scanner = scanner;
	}
	/**
	 * 
	 * @param driver
	 */
	public void addDriver(Driver driver){
		drivers.put(driver.getName(),driver);
	}
	
	public void addClient(Client client){
		clients.put(client.getName(),client);
	}
	
	public Client getClient(String name){
		return clients.get(name);
	}
	
	public HashMap<String,Client> getClients(){
		return clients;
	}
	
	public Driver getDriver(String name){
		return drivers.get(name);
	}
	
	/**
	 * Used to sort potential drivers by distance and ratings when assigning a ride.
	 * @author jterrito
	 *
	 */
	private class comparator implements Comparator<Driver>{
		public int compare(Driver a,Driver b){
			if(a.distance(tempPoint) == b.distance(tempPoint)){
				return (int)(b.getRating() - a.getRating());
			}
			return (int)(a.distance(tempPoint) - b.distance(tempPoint));
		}
	}
	
	/**
	 * Creates a ride for a client and requests drivers for the ride.
	 * @param client The client requesting the ride.
	 * @param destination The destination for the ride.
	 * @return Returns the ride that the client requested.
	 */
	public Ride receiveRequest(Client client, Point destination){
		Ride ride = new Ride(destination, client,rate, time);
		
		//creates queue of available drivers sorted by distance from the client
		Driver driver;
		PriorityQueue<Driver> list = new PriorityQueue<Driver>(new comparator());
		tempPoint = destination;
		for(String name: drivers.keySet()){
			driver = drivers.get(name);
			if(!(driver.isAvailable())){
				continue;
			}
			list.add(driver);
		}
		if(list.size() == 0){
			System.out.printf("%s: No available drivers.\n",client.getName());
			return null;
		}
		
		//requests everyone in queue
		while(!(list.poll().handleRequest(ride, scanner))){
			if(list.size() == 0){
				System.out.printf("%s: No available drivers.\n",client.getName());
				return null;
			}
		}
		System.out.printf("%s: Your driver,%s, has accepted your request.\n",client.getName(),ride.getDriver().getName());
		//charge client, handle if low balance
		if(!transaction(ride)){
			return null;
		}
		rides.add(ride);
		return ride; //sends to client
	}
	
	/**
	 * Charges client and pays driver based off price of ride.
	 * @param ride The ride that is being payed for.
	 * @return Returns whether or not the transaction succeeded.
	 */
	public boolean transaction(Ride ride){
		if(ride.getClient().getBal() < ride.getPrice()){
			System.out.printf("%s: Insufficient funds, ride cancelled.\n",ride.getClient().getName());
                        ride.endRide();
			return false;
		}
		ride.getClient().updateBal(-1 * ride.getPrice());
        System.out.printf("%s: Account charged, $%.2f.\n",ride.getClient().getName(),ride.getPrice());
		ride.getDriver().updateBal(ride.getPrice() * .75);
		return true;
	}
	/**
	 * Provides output to update client on the status of their ride.
	 */
	public void checkRides(){
		ArrayList<Ride> toRemove = new ArrayList<Ride>();
		for(Ride ride: rides){
			switch(ride.getStatus()){
			case "DONE":   toRemove.add(ride);
						   System.out.printf("%s: You have arrived at your destination.\n",ride.getClient().getName());
						   ride.getClient().rate(ride.getDriver(), scanner);
						   System.out.printf("Trip Complete: From (%.0f,%.0f) to (%.0f,%.0f), costing %.2f.\n",ride.getPickup().getX(),ride.getPickup().getY(),ride.getDestination().getX(),ride.getDestination().getY(),ride.getPrice());
						   System.out.printf("Client, %s, new balance is %.2f. Driver, %s, new balance is %.2f and rating is %.0f.\n", ride.getClient().getName(),ride.getClient().getBal(),ride.getDriver().getName(),ride.getDriver().getBal(),ride.getDriver().getRating());
						   ride.endRide();
						   break;
			case "COMING": System.out.printf("%s: Driver on the way, estimated wait is %.2f minutes.\n",ride.getClient().getName(),ride.estimateWait());
						   break;
			case "PICKUP": System.out.printf("%s: Driver has arrived, travel time is %.2f minutes.\n", ride.getClient().getName(),ride.estimateTravelTime());
						   break;
			case "ENROUT": System.out.printf("%s: You are on your way, travel time is %.2f minutes.\n", ride.getClient().getName(),ride.estimateTravelTime());
						   break;
			default:       break;
			}
		}
		for(Ride ride: toRemove){
			rides.remove(ride);
		}
	}
}
