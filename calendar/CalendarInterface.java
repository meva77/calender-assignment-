import java.rmi.*;
public interface CalendarInterface extends Remote{

	//services offered to clients
	public String[] viewEvent(String x)throws RemoteException,ArrayStoreException;
	public String addEvent(String name, String description,String time, String status,String scheduler,int id)throws RemoteException;
	public String deleteEvent(int id,String scheduler)throws RemoteException;
	public String editEvent(String time,String description,int id,String scheduler)throws RemoteException;


}
