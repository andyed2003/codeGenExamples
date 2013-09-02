package heating_ControllerTutorial2_Completed;
import static heating_ControllerTutorial2_Completed.MainEntry.*;
// Protected: Shared_Object_IMPL

public class Shared_Object_IMPL
{
	
	// Instance variables and constants
	protected static int ctm = 20;
	protected static boolean shss = false;
	protected static int cttm = 20;
	private static Object lock = new Object();
	
	public Shared_Object_IMPL()
	{
		
	}
	
	// Subroutines
	public void Get_Target_Temperature1(Pointer<Integer> tm)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				tm.value = cttm ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Set_Target_Temperature(int tm)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				cttm = tm;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Get_Target_Temperature2(Pointer<Integer> tm)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				tm.value = cttm ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Set_Heat_Source_State(boolean state)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				shss = state;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Get_Stored_HeatSource(Pointer<Boolean> state)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				state.value = shss ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
}
