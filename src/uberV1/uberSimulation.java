package uberV1;
import java.awt.Point;
import java.lang.NullPointerException;

public class uberSimulation {
	public static void main(String[] args){
		Manager manager = new Manager();
		Client jeff = new Client(manager,"Jeff",100);
		Driver charlene = new Driver(manager,"Charlene",0,4.2);
		Driver willy = new Driver(manager,"Willy",15,3.2);
		Driver amber = new Driver(manager,"Amber",34,2.9);
		Driver arnold = new Driver(manager,"Arnold",1,5);
		manager.addClient(jeff);
		manager.addDriver(charlene);
		manager.addDriver(willy);
		manager.addDriver(amber);
		manager.addDriver(arnold);
		
		jeff.request(new Point(1,2));
		try{
			jeff.getRide().info();
		}catch(NullPointerException e){}
		//wait some time for their locations to update
		//jeff rates the driver
		System.out.println("YOLO");
	}
}
