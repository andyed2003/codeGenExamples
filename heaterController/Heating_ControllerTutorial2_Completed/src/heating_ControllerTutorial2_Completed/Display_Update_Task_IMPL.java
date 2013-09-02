package heating_ControllerTutorial2_Completed;
import static heating_ControllerTutorial2_Completed.MainEntry.*;
// Task: Display_Update_Task_IMPL

public class Display_Update_Task_IMPL implements Runnable
{
	
	// Instance variables and constants
	protected int cttm1 = 0;
	protected int ttm = 20;
	protected boolean sinc_flag = false;
	protected boolean sdec_flag = false;
	protected int priority = 5;
	
	public Display_Update_Task_IMPL()
	{
	}
	
	public void run()
	{
		while(true)
		{
			// [Internal] Timer information for repeating or periodic tasks
			long THREAD_START_TIME = System.currentTimeMillis();
			
			// Translated code
			Pointer<Integer> cttm1Pointer = new Pointer<Integer>();
			((Shared_Object_IMPL)MainEntry.getTask("Shared_Object_IMPL")).Get_Target_Temperature1(cttm1Pointer);
			cttm1 = cttm1Pointer.value;
			Pointer<Boolean> sinc_flagPointer = new Pointer<Boolean>();
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Sense_PressIncrease_Target_Temperature(sinc_flagPointer);
			sinc_flag = sinc_flagPointer.value;
			if ((cttm1 < Max) && (sinc_flag == true))
			{
				ttm = (cttm1 + 1);
				sinc_flag = false;
			}
			else
			{
				sinc_flag = false;
			}
			Pointer<Boolean> sdec_flagPointer = new Pointer<Boolean>();
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Sense_PressDecrease_Target_Temperature(sdec_flagPointer);
			sdec_flag = sdec_flagPointer.value;
			if ((sdec_flag == true) && (cttm1 > Min))
			{
				ttm = ((cttm1) - 1);
				sdec_flag = false;
			}
			else
			{
				sdec_flag = false;
			}
			((Shared_Object_IMPL)MainEntry.getTask("Shared_Object_IMPL")).Set_Target_Temperature(ttm);
			((Envir1_IMPL)MainEntry.getTask("Envir1_IMPL")).Display_Target_Temperature(ttm);
			
			// [Internal] Code to monitor time between periodic tasks
			long THREAD_END_TIME = System.currentTimeMillis();
			long THREAD_TIME_TAKEN = THREAD_END_TIME - THREAD_START_TIME;
			try
			{
				Thread.sleep(Math.max(500 - THREAD_TIME_TAKEN,0));
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
