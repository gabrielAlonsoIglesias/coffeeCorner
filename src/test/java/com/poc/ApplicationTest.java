package com.poc;

import java.io.PrintWriter;

import org.junit.platform.launcher.listeners.TestExecutionSummary;

/**
 * @author galonsoi
 */
public class ApplicationTest {

	/**
	 * Main executable
	 *
	 * @param args
	 *			the arguments
	 */
	public static void main(String[] args) {

	    TestsRunner runner = new TestsRunner();
	    runner.runAll();

	    TestExecutionSummary summary = runner.listener.getSummary();
	    summary.printTo(new PrintWriter(System.out));
	}

}