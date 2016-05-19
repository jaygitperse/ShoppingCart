package src.com.shopping.cart;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import src.com.shopping.cart.model.Cart;
import src.com.shopping.cart.model.Item;
import src.com.shopping.cart.model.ItemInCart;

public class ShoppingCart implements Cart {
    
    ConcurrentHashMap<String, ItemInCart> cart = new ConcurrentHashMap<String, ItemInCart>();

    @Override
    public void addItem(Item item) {
        ItemInCart cartItem = cart.get(item.getId());
        
        if (cartItem == null) {
            cartItem = new ItemInCart(item);
            cart.putIfAbsent(item.getId(), cartItem);
        }
        cartItem.addItem(item);
    }

    @Override
    public void removeItem(Item item) {
        ItemInCart cartItem = cart.get(item.getId());
        if (cartItem != null) {
            if (cartItem.updateOnlyQuantityInCart()) {
                cartItem.updateQuantity();
            } else {
                cart.remove(item.getId());
            }
        }

    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        Iterator<Entry<String, ItemInCart>> it = cart.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, ItemInCart> cartItem = it.next();
            sum = sum.add(cartItem.getValue().getPrice());
        }
        return sum;
    }

    @Override
    public int getCountOfItems() {
        int totalItems = 0;
        Iterator<Entry<String, ItemInCart>> it = cart.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, ItemInCart> cartItem = it.next();
            totalItems += cartItem.getValue().getQuantity();
        }
        return totalItems;
    }

}
