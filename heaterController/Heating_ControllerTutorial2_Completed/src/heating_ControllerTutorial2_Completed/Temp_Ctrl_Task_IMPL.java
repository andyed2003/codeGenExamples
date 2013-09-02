package heating_ControllerTutorial2_Completed;
import static heating_ControllerTutorial2_Completed.MainEntry.*;
// Task: Temp_Ctrl_Task_IMPL

public class Temp_Ctrl_Task_IMPL implements Runnable
{
	
	// Instance variables and constants
	protected int avt = 0;
	protected int cttm2 = 0;
	protected boolean hsc = false;
	protected boolean ota = false;
	protected int stm1 = 0;
	protected int stm2 = 0;
	protected int priority = 5;
	
	public Temp_Ctrl_Task_IMPL()
	{
	}
	
	public void run()
	{
		while(true)
		{
			// [Internal] Timer information for repeating or periodic tasks
			long THREAD_START_TIME = System.currentTimeMillis();
			
			// Translated code
			Pointer<Integer> stm1Pointer = new Pointer<Integer>();
			Pointer<Integer> stm2Pointer = new Pointer<Integer>();
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Sense_Temperatures(stm1Pointer, stm2Pointer);
			stm1 = stm1Pointer.value;
			stm2 = stm2Pointer.value;
			avt = ((stm1 + stm2) / 2);
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Display_Current_Temperature(avt);
			Pointer<Integer> cttm2Pointer = new Pointer<Integer>();
			((Shared_Object_IMPL)MainEntry.getTask("Shared_Object_IMPL")).Get_Target_Temperature2(cttm2Pointer);
			cttm2 = cttm2Pointer.value;
			if ((avt < cttm2))
			{
				hsc = true;
			}
			else
			{
				hsc = false;
			}
			((Shared_Object_IMPL)MainEntry.getTask("Shared_Object_IMPL")).Set_Heat_Source_State(hsc);
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Actuate_Heat_Source(hsc);
			if ((avt > Max))
			{
				ota = true;
			}
			else
			{
				ota = false;
			}
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Actuate_OverHeat_Alram(ota);
			System.out.println("heat on = " + hsc);
			System.out.println("ota on = " + ota);
			System.out.println("avt temp :" + avt);
			
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
