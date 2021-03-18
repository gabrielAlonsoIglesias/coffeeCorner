package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.poc.Application;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class GetMenuTest {

    @Test
    public void test_GetMenu_01() {
    	Map<Integer, Product> menu = Application.getMenu();

        assertTrue(menu != null);
    }

}