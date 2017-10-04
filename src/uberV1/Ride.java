package uberV1;

/**
 * Information about the current ride.
 * @author jterrito
 *
 */
public class Ride {
	private int[] destination;
	private Client client;
	private Driver driver;
	private boolean ongoing;
	
	public Ride(int[] destination, Client client){

	}
	
	public void addDriver(Driver driver){
		
	}
	
	
	public void updateStatus(boolean ongoing){
		this.ongoing = ongoing;
	}
	
	public boolean getStatus(){
		return ongoing;
	}
	
	public double estimateWait(){
		return 12;
	}
	
	public double distance(int[] a, int[] b){
		return 12;
	}
	
	public double travelTime(double distance){
		return 12;
	}
	
	public double price(double time){
		return 12;
	}
	
}
