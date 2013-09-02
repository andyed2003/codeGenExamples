package heating_ControllerTutorial2_Completed;
import static heating_ControllerTutorial2_Completed.MainEntry.*;
// Task: Heater_Monitor_Task_IMPL

public class Heater_Monitor_Task_IMPL implements Runnable
{
	
	// Instance variables and constants
	protected boolean hs1 = false;
	protected boolean nha = false;
	protected boolean shs = false;
	protected int priority = 5;
	
	public Heater_Monitor_Task_IMPL()
	{
	}
	
	public void run()
	{
		while(true)
		{
			// [Internal] Timer information for repeating or periodic tasks
			long THREAD_START_TIME = System.currentTimeMillis();
			
			// Translated code
			Pointer<Boolean> shsPointer = new Pointer<Boolean>();
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Sense_Heater_Status(shsPointer);
			shs = shsPointer.value;
			Pointer<Boolean> hs1Pointer = new Pointer<Boolean>();
			((Shared_Object_IMPL)MainEntry.getTask("Shared_Object_IMPL")).Get_Stored_HeatSource(hs1Pointer);
			hs1 = hs1Pointer.value;
			if ((hs1 != shs))
			{
				nha = true;
			}
			else
			{
				nha = false;
			}
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Actuate_NoHeat_Alarm(nha);
			
			// [Internal] Code to monitor time between periodic tasks
			long THREAD_END_TIME = System.currentTimeMillis();
			long THREAD_TIME_TAKEN = THREAD_END_TIME - THREAD_START_TIME;
			try
			{
				Thread.sleep(Math.max(250 - THREAD_TIME_TAKEN,0));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// Subroutines
	public int getPriority()
	{
		return priority;
	}
	
}
