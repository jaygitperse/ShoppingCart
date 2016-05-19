package src.com.shopping.cart.model;

import java.math.BigDecimal;

public interface Cart {
	
	void addItem(Item item);
	void removeItem(Item item);
	BigDecimal getTotal();
	int getCountOfItems();

}
