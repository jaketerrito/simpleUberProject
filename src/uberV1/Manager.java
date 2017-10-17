package uberV1;
import java.util.*;
import java.awt.Point;

public class Manager {
	private HashMap<String,Driver> drivers = new HashMap<String,Driver>();
	private HashMap<String,Client> clients = new HashMap<String,Client>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private Scanner scanner = new Scanner(System.in);
	protected Point tempPoint; //for sorting
	/**
	 * Initializes manager with out any users.
	 */
	public Manager(){}
	
	public Manager(Scanner scanner){
		this.scanner = scanner;
	}
	public Manager(HashMap<String,Driver> drivers, HashMap<String,Client> clients, ArrayList<Ride> rides, Scanner scanner){
		this.drivers = drivers;
		this.rides = rides;
		this.clients = clients;
		this.scanner = scanner;
	}
	
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
	
	private class comparator implements Comparator<Driver>{
		public int compare(Driver a,Driver b){
			if(a.distance(tempPoint) == b.distance(tempPoint)){
				return (int)(b.getRating() - a.getRating());
			}
			return (int)(a.distance(tempPoint) - b.distance(tempPoint));
		}
	}
	
	public Ride receiveRequest(Client client, Point destination){
		Ride ride = new Ride(destination, client);
		
		//creates queue of available drivers sorted by distance from the client
		Driver driver;
		PriorityQueue<Driver> list = new PriorityQueue<Driver>(drivers.size(),new comparator());
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
		System.out.printf("%s: Driver, %s, has accepted your request.\n",client.getName(),ride.getDriver().getName());
		//charge client, handle if low balance
		if(!transaction(ride)){
			return null;
		}
	        
                System.out.printf("%s: Your driver is on their way.\n",client.getName());
		return ride; //sends to client
	}
	
	public boolean transaction(Ride ride){
		if(ride.getClient().getBal() < ride.getPrice()){
			System.out.printf("%s: Insufficient funds, ride cancelled.\n",ride.getClient().getName());
                        ride.endRide();
			return false;
		}
		ride.getClient().updateBal(-1 * ride.getPrice());
                System.out.printf("%s: Account charged, %.2f.\n",ride.getClient.getName(),ride.getPrice());
		ride.getDriver().updateBal(ride.getPrice() * .75);
		return true;
	}
	
	public void checkRides(){
		ArrayList<Ride> toRemove = new ArrayList<Ride>();
		for(Ride ride: rides){
			if(!(ride.getStatus())){
				toRemove.add(ride);
			}
                        System.out.print(ride.info());
                        //driver has arrived!
                        //destination reached!
		}
		for(Ride ride: toRemove){
			getRating(ride);
			ride.endRide();
			rides.remove(ride);
		}
	}
	
	public void getRating(Ride ride){
		ride.getClient().rate(ride.getDriver(), scanner);
	}
}
