package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import com.poc.Application;


/**
 * @author galonsoi
 */
public class MainTest {

	@Test
    public void test_main_01() {
		System.setIn(new ByteArrayInputStream("1".getBytes()));

   		Application.main(null);

   		assertTrue(true);
    }

	@Test
    public void test_main_02() {
		System.setIn(new ByteArrayInputStream("mock".getBytes()));

   		Application.main(null);

   		assertTrue(true);
    }

}