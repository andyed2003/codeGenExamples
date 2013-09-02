// MainEntry: MainEntry
package heating_ControllerTutorial2_Completed;
import java.util.HashMap;
public class MainEntry
{
	protected static final int Max = 45;
	
	protected static final int Min = 5;
	
	//	Stores all tasks and shared machines with their names
	private static HashMap<String, Object> tasks = new HashMap<String, Object>();
	
	public static void addTask(Object task, String name)
	{
		tasks.put(name, task);
	}
	
	public static Object getTask(String name)
	{
		return tasks.get(name);
	}
	
	public static void main(String [] args)
	{
		// Create the protected objects
		Shared_Object_IMPL protected0 = new Shared_Object_IMPL();
		
		// Store the tasks in a shared map to allow communication between tasks
		addTask(protected0, "Shared_Object_IMPL");
		
		// Create the tasks
		Temp_Ctrl_Task_IMPL task0 = new Temp_Ctrl_Task_IMPL();
		Heater_Monitor_Task_IMPL task1 = new Heater_Monitor_Task_IMPL();
		Display_Update_Task_IMPL task2 = new Display_Update_Task_IMPL();
		Envir1_IMPL task3 = new Envir1_IMPL();
		
		// Store the tasks in a shared map to allow communication between tasks
		addTask(task0, "Temp_Ctrl_Task_IMPL");
		addTask(task1, "Heater_Monitor_Task_IMPL");
		addTask(task2, "Display_Update_Task_IMPL");
		addTask(task3, "Envir1_IMPL");
		
		// Create the threads
		Thread thread0 = new Thread(task0);
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		
		// Set priority
		thread0.setPriority(task0.getPriority());
		thread1.setPriority(task1.getPriority());
		thread2.setPriority(task2.getPriority());
		thread3.setPriority(task3.getPriority());
		
		// Start the threads
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
