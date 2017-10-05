package uberV1;

public class User{
   protected String name;
   protected int[] location;
   protected double balance;
   protected Manager manager;
   protected Ride currentRide;

   public User(Manager manager, String name, double balance){
      this.manager = manager;
      this.name = name;
      this.balance = balance;
      // need random this.location = sumtin;
   }

   public User(Manager manager, String name, double balance, int[] location){
      this.manager = manager;
      this.name = name;
      this.balance = balance;
      this.location = location;
   }

   public int[] getLocation(){
      return location;
   }

   public void setLocation(int[] location){
      this.location = location;
   }

   public Ride getRide(){
      return currentRide;
   }

   public void setRide(Ride ride){
      this.currentRide = ride;
   }

}
