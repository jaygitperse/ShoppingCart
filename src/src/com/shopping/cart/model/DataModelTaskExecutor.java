package src.com.shopping.cart.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DataModelTaskExecutor implements ModelTaskExecutor {
	
	private final ExecutorService modelExecutors = Executors.newSingleThreadExecutor(new ModelUpdateThreadFactory());
	private Thread modelThread;
	private final ExecutorService tasks = Executors.newCachedThreadPool();
	
	@Override
	public void updateModel(ModelTask task) {
		Runnable runnable = new TaskRunner(task);
		if (modelThread == Thread.currentThread() ) {
			runnable.run();
		} else {
			modelExecutors.execute(runnable);
		}
	}

	@Override
	public void executeTask(ModelTask task) {
		if (!tasks.isShutdown()) {
			tasks.execute(new TaskRunner(task));
		}
	}

	@Override
	public void dispose() {
		updateModel(new ModelTask() {

			@Override
			public void perform() {
				modelExecutors.shutdown();
				tasks.shutdown();
			}
			
		});
	}
	
	private class ModelUpdateThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			modelThread = new Thread(r, "ModelUpdateThread");
			return modelThread;
		}
		
	}
	
	private class TaskRunner implements Runnable {
		private ModelTask task;

		public TaskRunner(ModelTask task) {
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.perform();
			}catch (Exception e) {
				//do cleanup if needed
			}
		}
		
	}

}
