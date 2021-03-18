package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.Application;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class CreateOrderTest {
	
	private Map<Integer, Product> menu;

	private List<Integer> selections;

	@BeforeEach
	public void setup() {
		this.menu = new HashMap<>();
		this.selections = new ArrayList<>();
	}

	@Test
    public void test_createOrder_01() {
		this.menu.put(1, new Product());
		this.selections.add(1);

   		Application.createOrder(this.menu, this.selections);

   		assertTrue(true);
    }

	@Test
    public void test_createOrder_02() {
		this.menu.put(1, null);
		this.selections.add(1);

   		Application.createOrder(this.menu, this.selections);

   		assertTrue(true);
    }

	@Test
    public void test_createOrder_03() {
		this.menu.put(1, new Product());

   		Application.createOrder(this.menu, this.selections);

   		assertTrue(true);
    }

    @Test
    public void test_createOrder_04() {
   		Application.createOrder(null, null);

   		assertTrue(true);
    }

}