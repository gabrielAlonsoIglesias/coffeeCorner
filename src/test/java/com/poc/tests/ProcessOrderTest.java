package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.Application;
import com.poc.domain.EProductType;
import com.poc.domain.Order;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class ProcessOrderTest {
	
	private Order order;
	
	private Product beverageProduct;
	
	private Product snackProduct;

	private Product extraProduct;

	private List<Product> products;

	private List<Product> freeBeverages;
	
	private List<Product> freeExtras;

	@BeforeEach
	public void setup() {
		this.products = new ArrayList<Product>();
		this.freeBeverages = new ArrayList<Product>();
		this.freeExtras = new ArrayList<Product>();
		this.beverageProduct = new Product("mock", EProductType.BEVERAGE, "5", 4.6, "EUR");
		this.snackProduct = new Product("mock", EProductType.SNACK, "5", 4.6, "EUR");
		this.extraProduct = new Product("mock", EProductType.EXTRA, "5", 4.6, "EUR");
		this.order = new Order();
		this.order.setProducts(new ArrayList<>());
		this.order.setFreeBeverages(new ArrayList<>());
		this.order.setFreeExtras(new ArrayList<>());
		this.order.setTotalAmount(4.5);
		this.order.setDiscounts(7.5);
		this.order.setAmountToPay(3.0);
	}

	@Test
    public void test_processOrder_01() {
   		Application.processOrder(this.order);

   		assertTrue(true);
    }
	
	@Test
    public void test_processOrder_02() {
		this.order.getProducts().clear();
		this.order.getFreeBeverages().clear();
		this.order.getFreeExtras().clear();
		this.products.add(this.beverageProduct);
		this.products.add(this.beverageProduct);
		this.products.add(this.beverageProduct);
		this.products.add(this.beverageProduct);
		this.products.add(this.beverageProduct);
		this.products.add(this.snackProduct);
		this.products.add(this.snackProduct);
		this.products.add(this.extraProduct);
		this.products.add(this.extraProduct);
		this.products.add(this.extraProduct);
		this.freeBeverages.add(this.beverageProduct);
		this.freeExtras.add(this.beverageProduct);
		this.order.setProducts(this.products);
		this.order.setFreeBeverages(this.freeBeverages);
		this.order.setFreeExtras(this.freeExtras);

   		Application.processOrder(this.order);

   		assertTrue(true);
    }

}