package es.iessoterohernandez.daw.endes.CarritoCompra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({})
public class TestShoppingCart {
	private static ShoppingCart cart;
	private static Product product1, product2;

	@BeforeEach
	public void init() {
		cart = new ShoppingCart();
		product1 = new Product("Product 1", 10.00);
		product2 = new Product("Product 2", 20.00);
	}
	@AfterEach
	public void finish() {
		cart = null;
		product1 = null;
		product2 = null;
	}

	@Test
	public void testInitialProducts() {
		assertEquals(0, cart.getItemCount());
	}

	@Test
	public void testEmptyCartProducts() {
		cart.empty();
		assertEquals(0, cart.getItemCount());
	}
	@Test
    public void testAddItem() {
        cart.addItem(product1);
        assertEquals(1, cart.getItemCount());
    }
    
    @Test
    public void testAddItemBalance() {
        cart.addItem(product1);
        assertEquals(10.00, cart.getBalance(), 0.01); 
    }
    
    @Test
    public void testAddMultipleItemsBalance() {
        cart.addItem(product1);
        cart.addItem(product2);
        assertEquals(30.00, cart.getBalance(), 0.01);
    }
    
    @Test
    public void testRemoveItem() throws ProductNotFoundException {
    	cart.addItem(product1);
        cart.addItem(product2);
        cart.removeItem(product1);
        assertEquals(1, cart.getItemCount());
    	
    }
    @Test
    public void testRemoveItemProductNotFoundException() {
        Product pCart = new Product("Producto existente", 10.0);
        cart.addItem(pCart);

        Product pNCart = new Product("Producto no existente", 20.0);
        try {
            cart.removeItem(pNCart);
            fail("Excepción ProductNotFoundException");
        } catch (ProductNotFoundException e) {
        	System.out.println("Excepción esperada");
        }
    }

}
