package uberV1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;

public class DriverTest {

	@Test
	public void testHandleRequestYes() {
		Driver driver = new Driver(null, "KARL", 0, 2,new Point(0,0));
		Client client = new Client(null,null,0,new Point(0,23));
		Ride ride = new Ride(new Point(1,1),client,2.5,2.5);
		Scanner scanner = new Scanner("y");
		assertTrue(driver.handleRequest(ride,scanner));
		assertEquals(driver.getRide(),ride);
		assertEquals(ride.getDriver(),driver);
		assertFalse(driver.isAvailable());
		scanner.close();
	}
	
	@Test
	public void testHandleRequestNo() {
		Driver driver = new Driver(null, "KARL", 0, 1);
		Client client = new Client(null,null,0,new Point(0,23));
		Ride ride = new Ride(new Point(0,231),client,2.5,2.5);
		Scanner scanner = new Scanner("n");
		assertFalse(driver.handleRequest(ride,scanner));
		scanner.close();
	}
	
	
	@Test
	public void testHandleRequestFail() {
		Driver driver = new Driver(null, "KARL", 0, 1);
		Client client = new Client(null,null,0,new Point(0,23));
		Ride ride = new Ride(new Point(0,24), client,2.5,2.5);
		driver.setRide(ride);
		assertFalse(driver.handleRequest(null,null));
	}

	@Test
	public void testGetRating(){
		Driver driver = new Driver(null, "KARL", 0, 3);
		assertEquals(driver.getRating(),3.0,.01);
		driver.addRating(5.0);
		assertEquals(driver.getRating(),4,.01);
	}

}
