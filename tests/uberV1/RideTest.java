package uberV1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Point;

public class RideTest {

	@Test
	public void testAddDriver() {
		Ride ride = new Ride(new Point(0,0),new Client(null,"Jake",0,new Point(0,1)));
		Driver driver = new Driver(null, "Max", 0, null, new Point(0,2));
		ride.addDriver(driver);
		assertEquals(ride.getPrice(),2.5*2,.01);
	}

	@Test
	public void testEstimateWait() {
		Ride ride = new Ride(new Point(0,0),new Client(null,"Jake",0,new Point(0,1)));
		Driver driver = new Driver(null, "Max", 0, null, new Point(0,2));
		ride.addDriver(driver);
		assertEquals(ride.estimateWait(),10,.01);
		driver.setLocation(new Point(0,3));
		assertEquals(ride.estimateWait(),20,.01);
	}

	@Test
	public void testEstimateTravelTime() {
		Ride ride = new Ride(new Point(0,0),new Client(null,"Jake",0,new Point(0,1)));
		Driver driver = new Driver(null, "Max", 0, null, new Point(0,2));
		ride.addDriver(driver);
		assertEquals(ride.estimateTravelTime(),10,.01);
		ride.getClient().setLocation(new Point(0,3));
		assertEquals(ride.estimateTravelTime(),30,.01);
	}

}
