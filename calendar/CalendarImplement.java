
// Ampaire Christine
// Eva Muhanguzi
// Matano Ali
// Ssekyanzi Joseph
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.sql.ResultSet;

//extend unicastRemoteObject to enable different distribution schemes
public class CalendarImplement extends UnicastRemoteObject implements CalendarInterface{

	
	Scanner input = new Scanner(System.in); // get user input

	public  CalendarImplement()throws RemoteException{

			super();//invoke super class constructor
		}


	public String[] viewEvent(String x)throws RemoteException,ArrayStoreException{//===============================overriding viewEvent method
		String myarray[]=new String[20]; //declare and initialise string array to store event details
		try{

			
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest_db?", "root", "");

			Statement stmt = conn.createStatement();

			String select = "select * From calendar where sceduler=\'"+x+"\' or status='group'";
			

			ResultSet r =stmt.executeQuery(select); ///executing the querry
			//System.out.println("================== EVENTS ===================");
			int i=0;
			while(r.next()){// pick elements from the database
					
					String name=r.getString("name");//get event name
					int id = r.getInt("id");//get event id
					String description=r.getString("description");//get event description
					String status=r.getString("status");//get event status
					String scheduler=r.getString("sceduler");//get event scheduler
					String time = r.getString("time");//get event time
					
					//System.out.println("event id:\t"+id +"\nEvent name:\t"+name+"\nDescription:\t"+description+"\nStatus:\t\t"+status+"\nScheduler:\t"+scheduler+"\nTime:\t\t"+time);
					
					//store event details in a string
					String avail = "event id:\t"+id +"\nEvent name:\t"+name+"\nDescription:\t"+description+"\nStatus:\t\t"+status+"\nScheduler:\t"+scheduler+"\nTime:\t\t"+time;
  					myarray[i]=avail;//add the string to a string array
  					i++;


					
			}
		}

		catch(SQLException ex) {
         ex.printStackTrace();
      }
     return myarray; //make String array available to client
	}
	//.........................................................................................................................................................
	public String addEvent(String name, String description,String time, String status,String scheduler,int id)throws RemoteException{
		
  	try{	
  			//connect to database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest_db?", "root", "");

			String insert = "insert into calendar values (?,?,?,?,?,?)";//querry to execute

			//insert appropriate values into database
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, name);
            pst.setString(2, description);
            pst.setString(3, time);
            pst.setString(4, status);
            pst.setString(5, scheduler);
            pst.setInt(6,id);
 	
			int x=pst.executeUpdate();

			System.out.println("event successfully added");//notify user of successfull insertion

			}

		catch(SQLException ex) {
         ex.printStackTrace();
      }

		return "data successfully added";
}
//.........................................................................................................................................................

	public String deleteEvent(int id,String scheduler)throws RemoteException{//deleting an event

			try{
			//connect to db
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest_db?", "root", "");
			Statement stmt = conn.createStatement();

			String delete = "delete From calendar where id=\'"+id+"\'and sceduler =\'"+scheduler+"\' ";
			int r =stmt.executeUpdate(delete);
		

			System.out.println();
		}

		catch(Exception e){
			e.getMessage();
		}

		return "event successfully deleted";}
//.........................................................................................................................................................
	public String editEvent(String time,String description,int id,String scheduler)throws RemoteException{

		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest_db?", "root", "");
			Statement stmt = conn.createStatement();

			String update = "update calendar set time =\'"+time+"\', description =\'"+description+"\' where id =\'"+id+"\'and sceduler =\'"+scheduler+"\' ";
 	
			int x=stmt.executeUpdate(update);

			System.out.println("event successfully adited");

			}

		catch(SQLException ex) {
         ex.printStackTrace();
      }

		return "data successfully edited";

}

	CalendarImplement(String name,int id,String description,String status,String scheduler,String time)throws RemoteException{

		System.out.println("event id:\t"+id +"\nEvent name:\t"+name+"\nDescription:\t"+description+"\nStatus:\t\t"+status+"\nScheduler:\t"+scheduler+"\nTime:\t\t"+time);

	}

}