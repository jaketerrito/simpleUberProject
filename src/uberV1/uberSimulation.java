package uberV1;
import java.awt.Point;
import java.io.*;
import java.util.*;
import java.lang.NullPointerException;
import java.util.ArrayList;
/**
 * BLahBlahBLAAH
 * @author jterrito
 *
 */
public class uberSimulation {
	private static Manager manager;
	
	public static Driver toDriver(String line){
		Scanner scanner = new Scanner(line);
		scanner.next();
		Point point = null;
		String name = scanner.next();
		double balance = scanner.nextDouble();
		double rating  = scanner.nextDouble();
		if(scanner.hasNextDouble()){
			point = new Point(scanner.nextInt(),scanner.nextInt());
		}
		scanner.close();
		if(point != null){
			return new Driver(manager,name,balance,rating,point);
		}
		return new Driver(manager,name,balance,rating);
	}
	
	public static Client toClient(String line){
		Scanner scanner = new Scanner(line);
		scanner.next();
		Point point = null;
		String name = scanner.next();
		double balance = scanner.nextDouble();
		if(scanner.hasNextDouble()){
			point = new Point(scanner.nextInt(),scanner.nextInt());
		}
		scanner.close();
		if(point != null){
			return new Client(manager,name,balance,point);
		}
		return new Client(manager,name,balance);
	}
	
	public static void main(String[] args){
		FileReader fileReader;
		BufferedReader bufferedReader;
	    Scanner scanner;
	    String temp;
		try{
			fileReader = new FileReader("resources/sim.txt");
		    bufferedReader = new BufferedReader(fileReader);
		    String line = bufferedReader.readLine();
		    scanner = new Scanner(line);
		    manager = new Manager(scanner.nextDouble(),scanner.nextDouble());
	        while((line = bufferedReader.readLine()) != null) {
	        	System.out.println(line);
	        	scanner = new Scanner(line);
	        	temp = scanner.next();
	        	if(temp.equals("Driver")){
	        		manager.addDriver(toDriver(line));
	        	}else if(temp.equals("Client")){
	        		manager.addClient(toClient(line));
	        	}else if(temp.equals("Request")){
	        		Client client = manager.getClient(scanner.next());
	        		//could add random requests!
	        		if(client.request(new Point(scanner.nextInt(),scanner.nextInt()))){
	        			manager.checkRides();
						Ride ride = client.getRide();
						Driver driver = ride.getDriver();
						driver.setLocation(ride.getPickup());
						manager.checkRides();
						driver.setLocation(ride.getDestination());
						client.setLocation(ride.getDestination());
						manager.checkRides();
	        		}
	        	}else{
	        		System.out.println("FORMAT ERROR");
	        	}
	        }
	        bufferedReader.close(); 
		}catch (Exception e){
			e.printStackTrace(System.out);
		}	
	}
}
