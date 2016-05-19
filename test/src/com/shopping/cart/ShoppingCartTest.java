package src.com.shopping.cart;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import src.com.shopping.cart.model.Item;

public class ShoppingCartTest extends TestCase {
    
    Item banana;
    Item orange;
    Item apple;
    Item lemon;
    Item peaches;
    ShoppingCart cart = new ShoppingCart();

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        banana = new Item("1","Banana",new BigDecimal("0.25"));
        orange = new Item("2","Orange",new BigDecimal("0.24"));
        apple = new Item("3","Apple",new BigDecimal("0.28"));
        lemon = new Item("4","Lemon",new BigDecimal("0.30"));
        peaches = new Item("5","Peaches",new BigDecimal("1.00"));  
    }
    
    @After
    protected void tearDown() throws Exception {
        super.tearDown();
        banana = null;
        orange = null;
        apple = null;
        lemon = null;
        peaches = null;  
    }
    
    @Test
    public void testCart() {
        cart.addItem(apple);
        cart.addItem(banana);
        cart.addItem(lemon);
        cart.addItem(orange);
        cart.addItem(peaches);
        cart.addItem(peaches);
        cart.addItem(banana);
        cart.addItem(lemon);
        cart.addItem(lemon);
        assertEquals(cart.getTotal(), new BigDecimal("3.92"));
        assertEquals(cart.getCountOfItems(), 9);
        
        cart.removeItem(lemon);
        
        assertEquals(cart.getTotal(), new BigDecimal("3.62"));
        assertEquals(cart.getCountOfItems(), 8);
        
    }
}
