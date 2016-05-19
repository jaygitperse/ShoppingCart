package src.com.shopping.cart.model;

public interface ModelTaskExecutor {
	
	void updateModel(ModelTask task);
	void executeTask(ModelTask task);
	void dispose();

}
