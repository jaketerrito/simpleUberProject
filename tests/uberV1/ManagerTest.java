package uberV1;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;
import org.junit.Test;

public class ManagerTest {

	@Test
	public void testReceiveRequest() {
		Scanner scanner = new Scanner("n\ny\n");
		Manager manager = new Manager(scanner);
		ArrayList<Double> ratings = new ArrayList<Double>();
		ratings.add(3.0);
		ratings.add(4.0);
		ratings.add(2.0);
		Driver a = new Driver(manager,"Kyle",0,ratings, new Point(300,0));
		Driver b = new Driver(manager,"Ellen",0,ratings, new Point(0,0)); //rating 3.0
		ratings = new ArrayList<Double>();
		ratings.add(5.0);
		Driver c = new Driver(manager,"Riley",0,ratings, new Point(0,0)); //rating 5.0
		Client d = new Client(manager,"Ruby",500,new Point(0,1));
		manager.addClient(d);
		manager.addDriver(a);
		manager.addDriver(b);
		manager.addDriver(c);
		d.request(new Point(2,2));
		assertTrue(a.isAvailable());
		assertTrue(c.isAvailable());
		assertFalse(b.isAvailable());
		assertEquals(d.getRide(),b.getRide());
	}
	

	@Test
	public void testTransactionGood() {
		Manager manager = new Manager();
		Client client = new Client(manager, "Avery", 23, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client);
		ArrayList<Double> ratings = new ArrayList<Double>();
		Driver driver = new Driver(manager, "KARL", 0, ratings, new Point(0,2));
		ride.addDriver(driver);
		assertTrue(manager.transaction(ride));
		assertEquals(client.getBal(), 15.5,.1);
		assertEquals(driver.getBal(), 7.5*.75,.1);
	}
	
	@Test
	public void testTransactionBad() {
		Manager manager = new Manager();
		Client client = new Client(manager, "Avery", 4, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client);
		ArrayList<Double> ratings = new ArrayList<Double>();
		Driver driver = new Driver(manager, "KARL", 0, ratings, new Point(0,2));
		ride.addDriver(driver);
		assertFalse(manager.transaction(ride));
		assertEquals(client.getBal(), 4.0,.1);
		assertEquals(driver.getBal(), 0,.1);
	}

	@Test
	public void testCheckRides() {
		Client client = new Client(null, "Avery", 4, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client);
		client.setRide(ride);
		ArrayList<Double> ratings = new ArrayList<Double>();
		Driver driver = new Driver(null, "KARL", 0, ratings, new Point(0,2));
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
		ride.updateStatus(false);
		manager.checkRides();
		assertFalse(rides.contains(ride));
		assertEquals(driver.getRide(),null);
		assertEquals(client.getRide(),null);
		assertEquals(driver.getRating(),4.0,.1);
	}

	@Test
	public void testGetRating() {
		Client client = new Client(null, "Avery", 4, new Point(0,0));
		Ride ride = new Ride(new Point(0,1),client);
		client.setRide(ride);
		ArrayList<Double> ratings = new ArrayList<Double>();
		Driver driver = new Driver(null, "KARL", 0, ratings, new Point(0,2));
		ride.addDriver(driver);
		driver.setRide(ride);
		Scanner scanner = new Scanner("2.0");
		Manager manager = new Manager(scanner);
		manager.getRating(ride);
		assertEquals(driver.getRating(),2.0,.1);
	}

}
