package uberV1;

/**
 * The client using the uber app.
 * @author jterrito
 *
 */
public class Client extends User{
	
        /**
         * Initializes client at random location
         *
         * @param manager the manager for this client
         * @param name the client's name
         * @param balance the client's intial balance
         */
        public Client(Manager manager, String name, double balance){
           this.super(manager,name,balance);
	}

        /**
         * Initializes client at given location
         *
         * @param manager the manager for this client
         * @param name the client's name
         * @param balance the client's intial balance
         * @param location the client's intial location
         */
	public Client(Manager manager, String name, double balance, int[] location){
           this.super(manager,name,balance,location);
	}
	
	public void request(int[] destination){
		this.currentRide = manager.receiveRequest(this, destination);
	}
	
}
