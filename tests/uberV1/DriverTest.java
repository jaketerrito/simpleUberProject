package uberV1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;

public class DriverTest {

	@Test
	public void testHandleRequestYes() {
		Driver driver = new Driver(null, "KARL", 0, null,new Point(0,0));
		Client client = new Client(null,null,0,new Point(0,23));
		Ride ride = new Ride(new Point(1,1),client);
		Scanner scanner = new Scanner("y");
		assertTrue(driver.handleRequest(ride,scanner));
		assertEquals(driver.getRide(),ride);
		assertEquals(ride.getDriver(),driver);
		assertFalse(driver.isAvailable());
		scanner.close();
	}
	
	@Test
	public void testHandleRequestNo() {
		Driver driver = new Driver(null, "KARL", 0, null);
		Ride ride = new Ride(null,null);
		Scanner scanner = new Scanner("naw");
		assertFalse(driver.handleRequest(ride,scanner));
		scanner.close();
	}
	
	
	@Test
	public void testHandleRequestFail() {
		Driver driver = new Driver(null, "KARL", 0, null);
		Ride ride = new Ride(null,null);
		driver.setRide(ride);
		assertFalse(driver.handleRequest(null,null));
	}

	@Test
	public void testGetRating(){
		ArrayList<Double> ratings = new ArrayList<Double>();
		ratings.add(3.0);
		ratings.add(4.0);
		ratings.add(2.0);
		Driver driver = new Driver(null, "KARL", 0, ratings);
		assertEquals(driver.getRating(),3.0,.01);
		driver.addRating(5.0);
		assertEquals(driver.getRating(),3.5,.01);
	}

}
