package uberV1;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Point;

public class UserTest {

	@Test
	public void testUpdateBal() {
		User kyle = new User(null,"Kyle",10);
		kyle.updateBal(-5);
		assertEquals(kyle.getBal(),5.0,.1);
		kyle.updateBal(25);
		assertEquals(kyle.getBal(),30.0,.1);
		kyle.updateBal(-31);
		assertEquals(kyle.getBal(),-1.0,.1);
	}

	@Test
	public void testDistance() {
		User kyle = new User(null,"Kyle",0,new Point(0,0));
		assertEquals(kyle.distance(new Point(0,1)),1,.01);
		assertEquals(kyle.distance(new Point(2,0)),2,.01);
		assertEquals(kyle.distance(new Point(0,-3)),3,.01);
		assertEquals(kyle.distance(new Point(-12,0)),12,.01);
		assertEquals(kyle.distance(new Point(2,2)),2.83,.01);
		assertEquals(kyle.distance(new Point(-2,23)),23.09,.01);
	}

}
