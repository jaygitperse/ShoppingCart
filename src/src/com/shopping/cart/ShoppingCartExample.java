package src.com.shopping.cart;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.com.shopping.cart.model.DataModelTaskExecutor;
import src.com.shopping.cart.model.Item;
import src.com.shopping.cart.model.ModelTask;

public class ShoppingCartExample {
    
    Item banana;
    Item orange;
    Item apple;
    Item lemon;
    Item peaches;
    
    Logger log = Logger.getLogger("ShoppingCartExample");
    
    CopyOnWriteArrayList<ShoppingCart> cart;
    private final DataModelTaskExecutor executor = new DataModelTaskExecutor();
    
    public static void main(String[] args) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.initialiseProducts();
        example.initialiseCarts();
        example.delayShop();
        example.fillCart();
        example.shuffleCart();
        example.printTotal();
    }

    private void printTotal() {
        for (ShoppingCart shoppingCart : cart) {
//            System.out.println("Total Items: "+shoppingCart.getCountOfItems() 
//            +" - "+"Price £"+shoppingCart.getTotal());
            log.log(Level.INFO, "Total Items: "+shoppingCart.getCountOfItems() 
            +" - "+"Price £"+shoppingCart.getTotal());
            
        }
        
   
    }
    
    private void shuffleCart() {
        for (int i = 0; i < cart.size(); i++) {
            if (i/2==0) {
                cart.get(i).removeItem(banana);
            } else {
                cart.get(i).addItem(lemon);
                cart.get(i).addItem(peaches);
            }
            
        }
    }

    private void fillCart() {
        for (ShoppingCart shoppingCart : cart) {
            shoppingCart.addItem(apple);
            shoppingCart.addItem(banana);
            shoppingCart.addItem(orange);
            shoppingCart.addItem(lemon);
            shoppingCart.addItem(peaches);
        }
    }

    private void initialiseCarts() {
        cart = new CopyOnWriteArrayList<ShoppingCart>();
        executor.updateModel(new ModelTask() {
            
            @Override
            public void perform() {
                for (int i = 0; i < 5; i++) {
                    cart.add(new ShoppingCart());
                }
                
            }
        });
    }

    private void initialiseProducts() {
        banana = new Item("1","Banana",new BigDecimal("0.25"));
        orange = new Item("2","Orange",new BigDecimal("0.24"));
        apple = new Item("3","Apple",new BigDecimal("0.28"));
        lemon = new Item("4","Lemon",new BigDecimal("0.30"));
        peaches = new Item("5","Peaches",new BigDecimal("1.00"));        
    }
    
    protected void delayShop() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
