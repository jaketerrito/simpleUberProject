package uberV1;
import java.awt.Point;
import java.io.*;
import java.util.*;
/**
 * BLahBlahBLAAH
 * @author jterrito
 *
 */
public class uberSimulation {
	private static Manager manager;
	
	public static void main(String[] args) throws InterruptedException{
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
						try {
							Thread.currentThread().sleep((long)(ride.estimateWait()*1000));
						}  catch (InterruptedException e) {}
						driver.setLocation(ride.getPickup());
						manager.checkRides();
						try {
							Thread.currentThread().sleep((long)(ride.estimateTravelTime()*1000));
						}  catch (InterruptedException e) {}
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
	
}
