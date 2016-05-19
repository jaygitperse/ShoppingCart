package src.com.shopping.cart.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemInCart {

    private Item item;
    private BigDecimal itemPrice;
    private AtomicInteger quantity;

    public ItemInCart(Item item) {
        this.item = item;
        quantity = new AtomicInteger(0);
    }

    public BigDecimal getPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void addItem(Item item) {
        quantity.incrementAndGet();
        updatePrice();
    }

    public boolean updateOnlyQuantityInCart() {
        return quantity.get() > 1;
    }

    private void updatePrice() {
        this.itemPrice = item.getPrice().multiply(new BigDecimal(quantity.get()));
    }

    public void updateQuantity() {
        quantity.decrementAndGet();
        updatePrice();
        
    }

}
