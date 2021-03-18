package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.Application;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class PrintMenuTest {

	private Map<Integer, Product> menu = new HashMap<>();

	@BeforeEach
	public void setup() {
    	this.menu.put(1, new Product());
	}

    @Test
    public void test_PrintMenu_01() {
   		Application.printMenu(this.menu);

   		assertTrue(true);
    }

    @Test
    public void test_PrintMenu_02() {
    	Assertions.assertThrows(NullPointerException.class, () -> {
    		Application.printMenu(null);
    	});
    }

}