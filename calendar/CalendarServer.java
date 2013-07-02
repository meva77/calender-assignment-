import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;


public class CalendarServer{
	
	public static void main(String[]args){
		System.out.println("\n-----------------SERVER-------------------\n");
		try{	
			System.out.println("Registering remote object...");
			CalendarImplement mary = new CalendarImplement(); //calendar object for mary
			CalendarImplement john = new CalendarImplement();//calendar object for mary
			CalendarImplement alice = new CalendarImplement();//calendar object for mary
			Naming.rebind("calender",mary);//register objects with name "calender" in the registry
			Naming.rebind("calender",alice);
			Naming.rebind("calender",john);

			System.out.println("\nRemote object \"calender\" registered");
			System.out.println("ready...");
	
	
		}

		catch(Exception e){
			System.out.println("ERROR OCCURED"+e);
		}
}
}