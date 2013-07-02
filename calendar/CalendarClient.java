  
  // The group Memebers
// Ampaire Christine
// MatoNo Ali
// Sekyanzi Joseph
// Eva Muhanguzi



import java.rmi.server.*;
import java.rmi.*;
import java.util.Scanner;
import java.io.*;

public class CalendarClient{
	
	public static void main(String[]args){
	System.out.println("\n---------------CLIENT--------------\n");
	String host="";
	int option;
	
	//pick command line argument that helps access server in question
	if (args.length == 1){

			host=args[0];
		}
		else{

			System.out.println("cannot access server object");
			System.exit(1); //exit program if server to access is not specified
		}

	//get user input from keyboard
	Scanner input = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    // Assign a security manager, in the event that dynamic classes are remotely loaded
	System.setSecurityManager(new RMISecurityManager());

	String servername = "rmi://"+host+"/calender";
	System.out.println("Looking up: remote object . . ."+servername );
	
	CalendarInterface calInt = null; //object to access interface methods


	try{
	

		calInt = (CalendarInterface)Naming.lookup(servername );//look up object registered by server machine in registry
	}

	catch(Exception e){
		System.out.println("Exception"+e);
		System.exit(1); //exit if exception occurs
	}

	try{
		for(int counter=0;counter<100;counter++){ //provide menu to user many times
		System.out.println("........................MENU..............................");
		System.out.println("1.view event\n2.Add Event\n3.Delete Event\n4.Edit Event\n5.Close");//menu for user
		System.out.println("--------------------------------");
		option= input.nextInt();
		

		if(option==1){ // for viewing scheduled events form the database
				System.out.println("enter name");
				String scheduler =  br.readLine();

				//..............................................................

				if(scheduler.equals("mary") ||scheduler.equals("john") ||scheduler.equals("alice") ){
					System.out.println("---------------- EVENTS ----------------------");
					for(int i=0;i<calInt.viewEvent(scheduler).length;i++){
						if(calInt.viewEvent(scheduler)[i]!=null){
							System.out.println(calInt.viewEvent(scheduler)[i]);//call the view event method using the user's name and print array elements returned
					
							System.out.println("----------------------------");	
						}
					
					
				}
					}
							


		}
		
		if(option==2){ // sheduling an event
				String name;
				String description;
				String time;
				String status;
				String scheduler;
				int id=0;

				System.out.println("----------------------ADD EVENT ---------------------------");

				System.out.println("enter event name");
				name =br.readLine();//get event name
				System.out.println("...................");

				System.out.println("describe event");
				description = br.readLine(); //get event description
				System.out.println("...................");

				System.out.println("event time : year-month-day");
				time = br.readLine();//get event date
				System.out.println("...................");

				System.out.println("event status...Group or Private");
				status= br.readLine();//get event status
				if(status!="group" || status!="private" ||status!="public"){//check if event falls under specified acceptable category


				System.out.println("wrong event status...");// notify user of wrong event status
				System.exit(1);

				}
				System.out.println("...................");

				System.out.println("scheduler name");
				scheduler= br.readLine();// get scheduler name

							
				System.out.println("----------------------------");
				
		//call method to register new event and pass it required event details
		System.out.println(calInt.addEvent(name,description,time,status,scheduler,id));
		System.out.println("---------------------------------");
		}


		if(option==3){//delete event by event id and name

				System.out.println("--------------------  DELETE EVENT ----------------------");
				System.out.println("First list events to identify them by id");
				System.out.println("enter id of item you would like to delete");
				int id= input.nextInt();

				//..............................................................

				System.out.println("enter your name");
				String scheduler =  br.readLine();

				if(scheduler.equals("mary") ||scheduler.equals("john") ||scheduler.equals("alice") ){//check for name of person deleting
					
					calInt.deleteEvent(id,scheduler); //call delete method
					System.out.println("event deleted successfull");

					}

				else{
					System.out.println("cannot delete an event that does not belong to you");
					System.exit(1);

				}
				
				

				System.out.println("-----------------------------------");


		}
		if(option==4){// edit event  time and description according to name and id
			String time;
			String description;
			String scheduler;

			System.out.println("------------------- EDIT EVENT-----------------");
			System.out.println("enter user name");
			scheduler =br.readLine();// get name of user responsible for editing
			System.out.println("...................");

			System.out.println("First list events to identify them by id");
			System.out.println("enter id of item you would like to edit");
			int id= input.nextInt();// get id of item to edit
			System.out.println("...................");


			System.out.println("new event time : year-month-day");
			time =br.readLine();//get new event time
			System.out.println("...................");

			System.out.println("new event description");
			description = br.readLine();//get new event description
			System.out.println("...................");

			System.out.println("--------------------------------------");
			System.out.println(calInt.editEvent(time,description,id,scheduler));//call method to edit the event with new details
			System.out.println("--------------------------------------");


		}

		if(option==5){//exit program after use
				System.out.println("thank you for using the servicesn\nGOODBYE!!!");
			

					System.out.println("-----------------------------");
					System.exit(1);//exit


		}


		}
	}
		catch(Exception e){
			System.out.println("Exception "+e);
			System.exit(1);//exit
		}

	}



}
