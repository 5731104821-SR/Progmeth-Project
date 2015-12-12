package logic;

public class EnemyRunnable implements Runnable {
	
	protected Enemy enemy;
	protected Thread prevThread;
	
	public EnemyRunnable (Enemy enemy, Thread prevThread)
	{
		this.enemy = enemy;
		this.prevThread = prevThread;

	}
	
	public void run()
	{
		if (prevThread != null)
		{
			try
			{
				prevThread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		while(true)
		{
			
			try
			{
				Thread.sleep(100);
				synchronized(enemy)
				{
					enemy.update();
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
