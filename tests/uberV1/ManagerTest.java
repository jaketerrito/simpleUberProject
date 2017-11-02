package uberV1;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;
import org.junit.Test;

public class ManagerTest {

	@Test
	public void testReceiveRequest() {
		System.out.println("testRecieved");
		Scanner scanner = new Scanner("y");
		Manager manager = new Manager(scanner);
		Driver a = new Driver(manager,"Kyle",0,3, new Point(300,0));
		Driver b = new Driver(manager,"Ellen",0,3, new Point(0,0)); //rating 3.0      
		Driver c = new Driver(manager,"Riley",0,5, new Point(0,0)); //rating 5.0
		// driver b and c are the closest but c has a better rating, so c should be chosen
		Client d = new Client(manager,"Ruby",10000,new Point(0,1));
		Driver e = new Driver(manager,"Jim",0,3, new Point(300,0));
		manager.addDriver(a);
		manager.addDriver(b);
		manager.addDriver(c);
		manager.addDriver(e);
		manager.addClient(d);
		d.request(new Point(2,2));
		assertTrue(a.isAvailable());
		assertTrue(b.isAvailable());
		assertTrue(e.isAvailable());
		assertFalse(c.isAvailable());
		assertEquals(d.getRide(),c.getRide());
	}
	

	@Test
	public void testTransactionGood() {
		Manager manager = new Manager();
		Client client = new Client(manager, "Avery", 23, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client,1,1);
		Driver driver = new Driver(manager, "KARL", 0, 0, new Point(0,2));
		ride.addDriver(driver);
		assertTrue(manager.transaction(ride));
		assertEquals(client.getBal(), 20,.1);
		assertEquals(driver.getBal(), 2.25,.1);
	}
	
	@Test
	public void testTransactionBad() {
		Manager manager = new Manager();
		Client client = new Client(manager, "Avery", 4, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client,2.5,2.5);
		Driver driver = new Driver(manager, "KARL", 0, 0, new Point(0,2));
		ride.addDriver(driver);
		assertFalse(manager.transaction(ride));
		assertEquals(client.getBal(), 4.0,.1);
		assertEquals(driver.getBal(), 0,.1);
	}

	@Test
	public void testCheckRides() {
		Client client = new Client(null, "Avery", 4, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client,2.5,2.5);
		client.setRide(ride);
		Driver driver = new Driver(null, "KARL", 0, 1, new Point(0,2));
		ride.addDriver(driver);
		driver.setRide(ride);
		ArrayList<Ride> rides = new ArrayList<Ride>();
		rides.add(ride);
		Scanner scanner = new Scanner("4.0");
		Manager manager = new Manager(null,null,rides,scanner);
		manager.checkRides();
		assertTrue(rides.contains(ride));
		assertEquals(driver.getRide(),ride);
		assertEquals(client.getRide(),ride);
		client.setLocation(new Point(0,1));
		driver.setLocation(new Point(0,1));
		manager.checkRides();
		assertFalse(rides.contains(ride));
		assertEquals(driver.getRide(),null);
		assertEquals(client.getRide(),null);
		assertEquals(driver.getRating(),2.5,.1);
	}

}
