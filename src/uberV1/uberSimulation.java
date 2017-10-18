package uberV1;
import java.awt.Point;
import java.lang.NullPointerException;
import java.util.ArrayList;
/**
 * BLahBlahBLAAH
 * @author jterrito
 *
 */
public class uberSimulation {
	public static void main(String[] args){
		Manager manager = new Manager();
		manager.addClient(new Client(manager,"Jeff",3000));
        manager.addClient(new Client(manager,"Clyde",1234));
        manager.addClient(new Client(manager,"Syd",50));
		manager.addDriver(new Driver(manager,"Charlene",0,1.0));
		manager.addDriver(new Driver(manager,"Willy",0,5.0));
		manager.addDriver(new Driver(manager,"Amber",0,2.3));
		manager.addDriver(new Driver(manager,"Arnold",0,1.2));
		manager.addDriver(new Driver(manager,"Carl",0,3.4));
		for(String name: manager.getClients().keySet()){
			Client client = manager.getClient(name);
			if(client.request(new Point(150,150))){
				manager.checkRides();
				Ride ride = client.getRide();
				Driver driver = ride.getDriver();
				driver.setLocation(ride.getPickup());
				manager.checkRides();
				driver.setLocation(ride.getDestination());
				client.setLocation(ride.getDestination());
				manager.checkRides();
			}
		}		
	}
}
