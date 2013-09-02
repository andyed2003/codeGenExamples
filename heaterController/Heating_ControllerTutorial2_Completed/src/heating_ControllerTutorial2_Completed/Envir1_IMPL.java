package heating_ControllerTutorial2_Completed;
import static heating_ControllerTutorial2_Completed.MainEntry.*;
// EnvironTask: Envir1_IMPL

public class Envir1_IMPL implements Runnable
{
	
	// Instance variables and constants
	protected boolean inc_flag = false;
	protected boolean dec_flag = false;
	protected int ts1 = 0;
	protected int ts2 = 0;
	protected boolean hss = false;
	protected boolean anha = false;
	protected int ttd = 20;
	protected boolean hsa = false;
	protected int ctd = 0;
	protected boolean aota = false;
	private static Object lock = new Object();
	
	protected int priority = 5;
	
	public Envir1_IMPL()
	{
	}
	
	public void run()
	{
		while(true)
		{
			// [Internal] Timer information for repeating or periodic tasks
			long THREAD_START_TIME = System.currentTimeMillis();
			synchronized(lock)
			{
				// Translated code
				if ((inc_flag == false))
				{
					inc_flag = true;
				}
				else
				{
					inc_flag = false;
				}
				if ((dec_flag == false))
				{
					dec_flag = true;
				}
				else
				{
					dec_flag = false;
				}
				hss = false;
				ts1 = (ts1 + 1);
				System.out.println("current temperature:" + ctd);
				System.out.println("overTemperature = " + aota);
			}
			// [Internal] Code to monitor time between periodic tasks
			long THREAD_END_TIME = System.currentTimeMillis();
			long THREAD_TIME_TAKEN = THREAD_END_TIME - THREAD_START_TIME;
			try
			{
				Thread.sleep(Math.max(100 - THREAD_TIME_TAKEN,0));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// Subroutines
	public void Sense_PressIncrease_Target_Temperature(Pointer<Boolean> state_inc)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				state_inc.value = inc_flag ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Sense_PressDecrease_Target_Temperature(Pointer<Boolean> state_dec)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				state_dec.value = dec_flag ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Display_Target_Temperature(int tm_tt)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				ttd = tm_tt;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Sense_Temperatures(Pointer<Integer> t1, Pointer<Integer> t2)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				t1.value = ts1 ;
				t2.value = ts2 ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Display_Current_Temperature(int tm_avt)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				ctd = tm_avt;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Actuate_Heat_Source(boolean state_hsc)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				hsa = state_hsc;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Actuate_OverHeat_Alram(boolean state_ota)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				aota = state_ota;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Sense_Heater_Status(Pointer<Boolean> state_hss)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				state_hss.value = hss ;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public void Actuate_NoHeat_Alarm(boolean state_nha)
	{
		// [Internal] This will block until the guard has been met
		boolean completed = false; 	 // private to the thread by default
		while(!completed)
		{
			// [Internal] Try and get lock
			synchronized(lock)
			{
				// Translated code
				anha = state_nha;
				// [Internal] Set completed flag
				completed = true;
			}
		}
	}
	
	public int getPriority()
	{
		return priority;
	}
	
}
