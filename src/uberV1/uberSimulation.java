package uberV1;
import java.awt.Point;
import java.io.*;
import java.util.*;
/**
 * Simulates the use of the uber program based off a textfile "sim.txt".
 * @author jterrito
 *
 */
public class uberSimulation {
	private static Manager manager;
	/**
	 * Runs the actual simulation taking input from input.txt and outputting to log.txt with 
	 * final state information stored in finalOutput.txt, switching comments will leave 
	 * output and input to console.
	 * @param args 
	 */
	public static void main(String[] args) {
		FileReader fileReader;
		BufferedReader bufferedReader;
	    Scanner scanner;
	    String temp;
	    Driver driver;
	    Client client;
	    int count = 0;
	    int success = 0;
	    int cancelled = 0;
		try{
			fileReader = new FileReader("resources/sim.txt");
		    bufferedReader = new BufferedReader(fileReader);
		    String line = bufferedReader.readLine();
		    scanner = new Scanner(line);
		    
		    //For using console for input/output.
		    //manager = new Manager(scanner.nextDouble(),scanner.nextDouble());
		    
		    
		    /*
		     * For logging with specific inputs only, along with code on line 99.
		     * Comment out this block and uncomment above line in order to use console for input/output.
		     */
		    Scanner input = new Scanner(new File("resources/input.txt"));
		    manager = new Manager(scanner.nextDouble(),scanner.nextDouble(), input);
		    PrintStream norm = System.out;
		    PrintStream out = new PrintStream(new FileOutputStream("resources/log.txt"));
		    System.setOut(out);
		    //end of logging
		    
		    while((line = bufferedReader.readLine()) != null) {
	        	scanner = new Scanner(line);
	        	if(!scanner.hasNext()){
	        		continue;
	        	}
	        	temp = scanner.next();
	        	
	        	if(temp.equals("Driver")){ //creates new driver and sends to manager
	        		driver = toDriver(line);
	        		System.out.printf("New Driver: %s, balance $%.2f,rating %.1f,location (%.0f,%.0f).\n",driver.getName(),driver.getBal(),driver.getRating(),driver.getLocation().getX(),driver.getLocation().getY());
	        		manager.addDriver(driver);
	        	}else if(temp.equals("Client")){ //creates new client and sends to manager
	        		client = toClient(line);
	        		System.out.printf("New Client: %s, balance $%.2f,location (%.0f,%.0f).\n",client.getName(),client.getBal(),client.getLocation().getX(),client.getLocation().getY());
	        		manager.addClient(toClient(line));
	        	}else if(temp.equals("Request")){ //creates request and sends through client
	        		client = manager.getClient(scanner.next());
                    Point point;
                    if(scanner.hasNext()){ //if no location given creates random destination.
                    	point = new Point(scanner.nextInt(),scanner.nextInt());
                    }else{
                    	Random rand = new Random();
                    	point = new Point(rand.nextInt(301), rand.nextInt(301));
                    }
	        		if(client.request(point)){ //executes the ride.
	        			manager.checkRides();
						Ride ride = client.getRide();
						driver = ride.getDriver();
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
						success ++;
	        		}else{
	        			cancelled ++;
	        		}
	        		count++;
	        	}else{
	        		System.out.println("FORMAT ERROR");
	        	}
	        }
	        bufferedReader.close(); 
	        
	        //Only if logging to file, comment out for console input/output.
	        out.close();
	        System.setOut(norm);
	        
	        //For information on state at end of simulation.
	        PrintStream finalLog = new PrintStream(new File("resources/finalOutput.txt"));
	        finalLog.format("Total Trips: %d, Cancelled: %d, Successful: %d.\n", count,cancelled, success);
	        for(String name: manager.getClients().keySet()){
				client = manager.getClients().get(name);
				finalLog.format("Client: %s, balance $%.2f,location (%.0f,%.0f).\n",client.getName(),client.getBal(),client.getLocation().getX(),client.getLocation().getY());
	        }
	        for(String name: manager.getDrivers().keySet()){
				driver = manager.getDrivers().get(name);
				finalLog.format("Driver: %s, balance $%.2f,rating %.1f,location (%.0f,%.0f).\n",driver.getName(),driver.getBal(),driver.getRating(),driver.getLocation().getX(),driver.getLocation().getY());
	        }
	        finalLog.close();
	        		
		}catch (Exception e){
			e.printStackTrace(System.out);
		}	
	}
	
	/**
	 * Creates new Driver object from line of text.
	 * @param line Expected format: "Driver name bal rating [x y]"
	 * @return The new Driver
	 */
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
	
	/**
	 * Creates new Client object from line of text.
	 * @param line Expected format: "Client name bal [x y]"
	 * @return The new Client
	 */
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
