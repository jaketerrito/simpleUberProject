package uberV1;
import java.awt.Point;
import java.lang.NullPointerException;
import java.util.ArrayList;

public class uberSimulation {
	public static void main(String[] args){
		Manager manager = new Manager();
		Client jeff = new Client(manager,"Jeff",100);
		ArrayList<Double> DoesThisWork = new ArrayList<Double>();
		DoesThisWork.add(1.00);
		Driver charlene = new Driver(manager,"Charlene",0,DoesThisWork);
		Driver willy = new Driver(manager,"Willy",15,DoesThisWork);
		Driver amber = new Driver(manager,"Amber",34,DoesThisWork);
		Driver arnold = new Driver(manager,"Arnold",1,DoesThisWork);
		manager.addClient(jeff);
		manager.addDriver(charlene);
		manager.addDriver(willy);
		manager.addDriver(amber);
		manager.addDriver(arnold);
		jeff.request(new Point(1,2));
		try{
			System.out.print(jeff.getRide().info());
		}catch(NullPointerException e){}
		//wait some time for their locations to update
		//jeff rates the driver
		System.out.println("YOLO");
	}
}
