/**
 * User.java
 */
package org.com1027.coursework.q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class User {

  private String                name          = null;
  private Map<Integer, Integer> purchases     = new HashMap<Integer, Integer>();
  private Map<Integer, Double>  succesfulBids = new HashMap<Integer, Double>();

  /**
   * Constructor built in order to be able to create a User object and also consists of validation checks.
   * @param name
   */
  public User(String name) {
    super();
    if (name != null && Pattern.matches("[a-zA-z]+", name) == true) {
      this.name = name;
    }
    else {
      throw new IllegalArgumentException("The name can only consist letters");
    }

  }

  public Map<Integer, Integer> getPurchases() {
    return this.purchases;
  }

  /**
   * Will display what a user has purchased.
   * @return string
   */
  public String displayPurchases() {
    String string = "";
    ArrayList<String> list = new ArrayList<String>();
    if (purchases.isEmpty() == false) {
      for (Map.Entry<Integer, Integer> entry : purchases.entrySet()) {
        list.add(entry.getKey() + " with quantity " + entry.getValue() + "\n");
      }
      string = "Purchases:" + " " + "\n" + String.join("", list);
    }
    else {
      string = "Purchases:" + " " + "\n";
    }
    return string;
  }

  /**
   * Will display the successful bids of a User.
   * @return string
   */
  public String displaySuccesfulBids() {
    String string = "";
    ArrayList<String> list = new ArrayList<String>();
    if (succesfulBids.isEmpty() == false) {
      for (Map.Entry<Integer, Double> entry : succesfulBids.entrySet()) {
        list.add(entry.getKey() + " at a cost of " + entry.getValue() + "\n");
      }
      string = "Successful Bids:" + " " + "\n" + String.join("", list);
    }
    else {
      string = "Successful Bids:" + " " + "\n";
    }
    return string;
  }
 
  /**
   * Will display everything a user tried to purchase.
   * @return string
   */
  public String displayAllPurchases() {
    String string = "All Purchased Products:" + " " + "\n" + displayPurchases() + displaySuccesfulBids();
    return string;
  }

  public Map<Integer, Double> getSuccesfulBids() {
    return this.succesfulBids;
  }

  public void buy(int productID, int quantity) {
    purchases.put(productID, quantity);
  }

  public void wonAuction(int productID, double winningPrice) {
    succesfulBids.put(productID, winningPrice);
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return name.substring(0, 1) + name.replaceAll(name.substring(0, name.length() - 1), "***");
  }

}
