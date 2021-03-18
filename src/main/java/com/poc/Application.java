package com.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.poc.common.Constants;
import com.poc.domain.EProductType;
import com.poc.domain.Order;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class Application {

	/**
	 * Main executable
	 *
	 * @param args
	 *			the arguments
	 */
	public static void main(String[] args) {
		List<Integer> selections = null;
		Order order = null;
		Map<Integer, Product> menu = new HashMap<>();

		try {
			// First of all retrieve menu from configurable repository
			menu = getMenu();

			// Prints the menu with all available selecctions / products
			printMenu(menu);

			// We assure the selections are valid, but can be out of range
			selections = readSelections(menu.keySet());

			// Creates a new order with the selected products
			order = createOrder(menu, selections);

			if (order != null) {
				// Process recent created order: calculate free extras, discounts, ... 
				processOrder(order);
	
				// Prints receipt
				printReceipt(order);
			} else {
				System.out.println("\nNo order han been attended for your selections");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the available menu configured in a JSON file (could be any repository) into a map structure
	 * 	- key   -> selected order
	 *  - value -> product
	 *
	 * @return the menu 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<Integer, Product> getMenu() {
		Map<Integer, Product> menu = new HashMap<>();
		ScriptEngineManager sem = new ScriptEngineManager();

		try {			
			ScriptEngine engine = sem.getEngineByName(Constants.ENGINE_NAME);
			String contentFile = readFile();
			Map<String, Object> map = (Map<String, Object>) engine.eval(Constants.SCRIPT.replace(Constants.DASH, contentFile));
			List products = (List) map.get(Constants.PRODUCTS);

			products.forEach((t) -> {
				Map<String, Object> productAttrs = (Map<String, Object>) t;
			   	Product product = new Product((String) productAttrs.get(Constants.NAME),
			   								  EProductType.valueOf(((String) productAttrs.get(Constants.TYPE)).toUpperCase()),
			   								  (String) productAttrs.get(Constants.SIZE),
			   								  (Double) productAttrs.get(Constants.PRICE),
			   								  (String) productAttrs.get(Constants.CURRENCY));
			   	menu.put((Integer) productAttrs.get(Constants.ID), product);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	/**
	 * Reads the JSON file into a String
	 *
	 * @return the content
	 */
	private static String readFile() {
		StringBuilder content = new StringBuilder();
		InputStream inputStream = Application.class.getClassLoader().getResourceAsStream(Constants.PRODUCTS_FILE);

		// The stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("File not found! " + Constants.PRODUCTS_FILE);
		}
		try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		     BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	/**
	 * Prints the <b>menu</b> in console
	 *
	 * @param menu
	 *			the menu
	 */
	public static void printMenu(Map<Integer, Product> menu) {
		System.out.println("===================================");
		System.out.println("Welcome to Charlene's Coffee Corner");
		System.out.println("===================================");
		System.out.println("");
		System.out.println("Offering");
		System.out.println("--------");
		System.out.println("[0] EXIT menu");
		System.out.println("········ ····");
		menu.forEach((k, p) -> System.out.println(("[" + k + "] " + p)));
	}

	/**
	 * Reads via console the entry user selections
	 *
	 * @param availableSelections
	 *			the selections user can pick up
	 * @return the selected selections
	 */
	public static List<Integer> readSelections(Set<Integer> availableSelections) {
		List<Integer> selections = null;
		int i = 0;

		while (selections == null && i < Constants.MAX_RETRIES) {
			System.out.print("\nIntroduce products numbers separated by ',' without blanks and press ENTER key (Example: 1,3,4). Available selections are: ");
			availableSelections.forEach(result -> System.out.print(result + " "));
			System.out.print("\nYour selection: ");

			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				selections = Stream.of(bufferRead.readLine().split(Constants.COMMA)).map (elem -> new Integer(elem)).collect(Collectors.toList());
			} catch (Exception e) {
				System.out.print("Wrong format. Please follow requested pattern. Examples are: 1,3,4 or 3,6,8 or ...");
				i++;
			}			
		}
		return selections;
	}

	/**
	 * Creates a new order based on current <b>selections</b> and <b>menu</b>
	 *
	 * @param menu
	 *			the menu
	 * @param selections
	 *			the selections
	 * @return the order
	 */
	public static Order createOrder(Map<Integer, Product> menu, List<Integer> selections) {
		Order order = null;
		List<Product> selectedProducts = new ArrayList<>();

		if (selections != null) {
			for (Integer selection : selections) {
				// Retrieves the product mapped to the selection
				Product product = menu.get(selection);

				// Selected product is in the menu?
				if (product != null) {
					// Yes; add selected product
					selectedProducts.add(product);
				}
			}

			if (selectedProducts.size() > 0) {
				// Set products into the order
				order = new Order(selectedProducts);
			}			
		}

		return order;
	}

	/**
	 * Calculates order items: free beverages, free extras, total amount, discounts, amount to pay
	 *
	 * @param order
	 *			the order
	 */
	public static void processOrder(Order order) {
		Integer nFreeExtras = 0;
		Integer totalBeverages = 0;
		Integer totalSnacks = 0;
		Double totalAmount = 0.0;
		Double discounts  = 0.0;
		List<Product> freeBeverages = new ArrayList<>();
		List<Product> freeExtras = new ArrayList<>();

		for (Product product : order.getProducts()) {
			// Total bill
			totalAmount += product.getPrice();
			
			// Beverage?
			if (product.getType().equals(EProductType.BEVERAGE)) {
				// Yes; count it
				totalBeverages++;
				
				// Per each 5 beverages, the 5th is free
				if (totalBeverages % Constants.STAMP_CARD_LIMIT == 0) {
					freeBeverages.add(product);
					discounts += product.getPrice();
				}
			}
			// Snack?
			if (product.getType().equals(EProductType.SNACK)) {
				// Yes; count it
				totalSnacks++;
			}			
		}
		// Count free extras
		nFreeExtras = ((totalBeverages + totalSnacks - Math.abs(totalBeverages - totalSnacks)) / 2); 

		// Add first <nFreeExtras> for free
		for (int i = 0; i < order.getProducts().size() || i < nFreeExtras; i++) {
			if (order.getProducts().get(i).getType().equals(EProductType.EXTRA)) {
				freeExtras.add(order.getProducts().get(i));
				discounts += order.getProducts().get(i).getPrice();
			}
		}

		// Set order info
		order.setFreeBeverages(freeBeverages);
		order.setFreeExtras(freeExtras);
		order.setTotalAmount(totalAmount);
		order.setDiscounts(discounts);
		order.setAmountToPay(totalAmount - discounts);
	}

	/**
	 * Prints the <b>order</b> through console
	 * 
	 * @param order
	 *			the order
	 */
	public static void printReceipt(Order order) {

		DecimalFormat format = new DecimalFormat(Constants.DECIMAL_FORMAT_2_DIGITS);

		System.out.println("");
		System.out.println("============================================");
		System.out.println("Thanks for visiting Charlene's Coffee Corner");
		System.out.println("============================================");
		System.out.println("");
		System.out.println("-----------");
		System.out.println("| Invoice |");
		System.out.println("-----------");
		System.out.println("Ordered products:");
		for (Product product : order.getProducts()) {
			System.out.println("- " + product.toString());
		}
		System.out.println("----------------------------------");
		System.out.println("Free beverages:");
		if (order.getFreeBeverages().size() > 0) {
			for (Product product : order.getFreeBeverages()) {
				System.out.println("- " + product.toString());
			}			
		} else {
			System.out.println("<NA>");
		}

		System.out.println("----------------------------------");
		System.out.println("Free freeExtras:");
		
		if (order.getFreeExtras().size() > 0) {
			for (Product product : order.getFreeExtras()) {
				System.out.println("- " + product.toString());
			}
		} else {
			System.out.println("<NA>");
		}
		System.out.println("----------------------------------");
		System.out.println("Resume:");
		System.out.println("Gross Amount:  " + format.format(order.getTotalAmount()));
		System.out.println("Discounts:   - " + format.format(order.getDiscounts()));
		System.out.println("Total to pay:  " + format.format(order.getAmountToPay()));
	}

}