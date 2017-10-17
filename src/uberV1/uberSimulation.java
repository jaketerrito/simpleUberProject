package uberV1;
import java.awt.Point;
import java.lang.NullPointerException;
import java.util.ArrayList;

public class uberSimulation {
	public static void main(String[] args){
		Manager manager = new Manager();
		manager.addClient(new Client(manager,"Jeff",1000));
                manager.addClient(new Client(manager,"Clyde",1234));
                manager.addClient(new Client(manager,"Syd",50));
		manager.addDriver(new Driver(manager,"Charlene",0,1.0));
		manager.addDriver(new Driver(manager,"Willy",0,5.0));
		manager.addDriver(new Driver(manager,"Amber",0,2.3));
		manager.addDriver(new Driver(manager,"Arnold",0,1.2));
		manager.addDriver(new Driver(manager,"Carl",0,3.4));
		jeff.request(new Point(150,150));
		try{
			System.out.print(jeff.getRide().info());
		}catch(NullPointerException e){}
		//wait some time for their locations to update
		//jeff rates the driver
		System.out.println("YOLO");
	}
}
