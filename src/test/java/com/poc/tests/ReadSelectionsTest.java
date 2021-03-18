package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.Application;


/**
 * @author galonsoi
 */
public class ReadSelectionsTest {

	private Set<Integer> availableSelections;

	@BeforeEach
	public void setup() {
    	this.availableSelections = new HashSet<>();
	}

    @Test
    public void test_readSelections_01() {
    	this.availableSelections.add(1);
    	System.setIn(new ByteArrayInputStream("1".getBytes()));

   		Application.readSelections(this.availableSelections);

   		assertTrue(true);
    }

    @Test
    public void test_readSelections_02() {
    	System.setIn(new ByteArrayInputStream("mock".getBytes()));

   		Application.readSelections(this.availableSelections);

   		assertTrue(true);
    }

}