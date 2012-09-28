package de.uni_hamburg.informatik.sep.zuul.server.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ServerManager
{
	private final Thread _thread;
	private BlockingQueue<Runnable> _queue = new ArrayBlockingQueue<Runnable>(
			25);

	private ServerManager()
	{
		//System.err.println("Creating ServerManager");
		_thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				threadRunner();
			}
		}, "ServerManager-Thread-1");
		//System.err.println("Running Thread.");
		_thread.start();
	}

	private static volatile ServerManager instance = new ServerManager();

	private void threadRunner()
	{
		try
		{
			while(true)
			{
				Runnable runnable = _queue.take();
				//System.err.println(Thread.currentThread().getName()
				//+ ": Executing event.");
				try
				{
					runnable.run();
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @require runnable != null
	 */
	public static void invokeLater(Runnable runnable)
	{
		instance._invokeLater(runnable);
	}

	private void _invokeLater(Runnable runnable)
	{
		try
		{
			_queue.put(runnable);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
