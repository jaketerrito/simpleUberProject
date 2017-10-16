package uberV1;

import java.util.Random;
import java.awt.Point;
import java.lang.Math;

public class User{
   protected String name;
   protected Point location;
   protected double balance;
   protected Manager manager;
   protected Ride currentRide = null;
   protected int GRIDMAX = 300;

   public User(Manager manager, String name, double balance){
      this.manager = manager;
      this.name = name;
      this.balance = balance;
      Random rand = new Random();
      location = new Point(rand.nextInt(GRIDMAX),rand.nextInt(GRIDMAX));
   }

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
   
	public double distance(Point a){
		return Math.hypot(a.getX() - location.getX(), a.getY() - location.getY());
	}
	
}
