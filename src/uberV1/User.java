package uberV1;

import java.util.Random;
import java.awt.Point;
import java.lang.Math;
/**
 * Represents all users of the uber service.
 * @author jterrito
 *
 */
public class User{
   protected String name;
   protected Point location;
   protected double balance;
   protected Manager manager;
   protected Ride currentRide = null;
   protected int GRIDMAX = 300;
   /**
    * A user of the uber service, given random location.
    * @param manager The manager handling this user.
    * @param name The user's name.
    * @param balance The user's balance.
    */
   public User(Manager manager, String name, double balance){
      this.manager = manager;
      this.name = name;
      this.balance = balance;
      Random rand = new Random();
      location = new Point(rand.nextInt(GRIDMAX),rand.nextInt(GRIDMAX));
   }
   /**
    * A user of the uber service.
    * @param manager The manager handling this user.
    * @param name The user's name.
    * @param balance The user's balance.
    * @param location The user's location.
    */
   public User(Manager manager, String name, double balance, Point location){
      this.manager = manager;
      this.name = name;
      this.balance = balance;
      this.location = location;
   }
   
   public String getName(){
	   return name;
   }
   
   public double getBal(){
      return balance;
   }

   /**
    * Updates user's balance by given amount.
    * @param amount
    */
   public void updateBal(double amount){
	   balance+= amount;
   }

   public Point getLocation(){
      return location;
   }

   public void setLocation(Point location){
      this.location = location;
   }

   public Ride getRide(){
	   if(currentRide == null){
		   System.out.printf("No ride yet.\n");
	   }
	   return currentRide;
   }

   public void setRide(Ride ride){
      this.currentRide = ride;
   }
   /**
    * Calculates the distance from user to a given point.
    * @param point
    * @return
    */
   public double distance(Point point){
	   return Math.hypot(point.getX() - location.getX(), point.getY() - location.getY());
   }
	
}
