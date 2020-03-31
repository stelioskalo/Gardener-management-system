/**
 * AuctionHouse.java
 */
package org.com1027.coursework.q1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuctionHouse {

  /**
   * The hashMaps are created in order to store the product objects. If a product is for sale we store it in the forSaleProducts
   * hashmap, if a product has been sold we store it in the soldProducts hashMap and if a product hasn't been sold we store it in
   * the unsoldProducts hashmap.
   */
  private Map<Product, User> forSaleProducts = new HashMap<Product, User>();
  private Map<Product, User> soldProducts    = new HashMap<Product, User>();
  private Map<Product, User> unsoldProducts  = new HashMap<Product, User>();

  /**
   * This method takes as a parameter a product object and checks if it already exists in the auctionhouse.
   * 
   * @param product
   * @return contains
   */
  public boolean checkExistence(Product product) {
    boolean contains = true;
    if (forSaleProducts.containsKey(product)) {
      contains = true;
    }
    else {
      contains = false;
    }
    return contains;
  }

  /**
   * This method will display all the sold products in a specific way.
   * 
   * @return string2
   */
  public String displaySoldProducts() {
    Set<Product> key = soldProducts.keySet();
    StringBuffer b = new StringBuffer("");
    if (soldProducts.size() != 0) {
      for (Product p : key) {
        b.append(p.getProductId() + " " + "-" + " " + soldProducts.get(p) + " bid £" + p.getHighestBid().getBidValue() + "\n");
      }
    }
    else {
      b.append("");
    }
    return b.toString();
  }

  /**
   * This method will display all the unsold products in a specific way.
   * 
   * @return string2;
   */
  public String displayUnsoldProducts() {
    Set<Product> key = unsoldProducts.keySet();
    StringBuffer b = new StringBuffer("");
    if (unsoldProducts.size() != 0) {
      for (Product p : key) {
        b.append(p.getProductId() + " " + "-" + " " + p.getProductName() + "\n");
      }
    }
    else {
      b.append("");
    }
    return b.toString();
  }

  /**
   * This method will take as a parameter a product object and remove it from the forSaleProduct hashmap and place it to the
   * unsoldProducts hashmap or the soldProducts hashmap.
   * 
   * @param product
   */
  public void endAuction(Product product) {
    if (product.getHighestBid() == null) {
      forSaleProducts.remove(product);
      unsoldProducts.put(product, null);
    }
    else if (product.getHighestBid().getBidValue() < product.getReservedPrice()) {
      forSaleProducts.remove(product, product.getHighestBid().getBuyer());
      unsoldProducts.put(product, product.getHighestBid().getBuyer());
    }
    else {
      forSaleProducts.remove(product, product.getHighestBid().getBuyer());
      soldProducts.put(product, product.getHighestBid().getBuyer());
    }

  }

  /**
   * This method will take as a parameter a product object, a User object and a bidValue. The method will check if the product
   * exists and if it does, it will check whether there are already placed Bids for that object. If there are, it will check wheter
   * the bidValue is bigger than the highestBid for that object. If the bidValue is bigger it will then create a new Bid object and
   * place it in the Bid List of the specific product. If there are no bids it will do the same thing and place the new bid.
   * 
   * @param product
   * @param user
   * @param bidValue
   * @return bid
   */
  public boolean placeBid(Product product, User user, double bidValue) {
    boolean bid = false;

    if (bidValue <= 0 || user == null || product == null) {
      throw new IllegalArgumentException("bid value cannot be 0 or less and user and product cannot be null");
    }
    if (forSaleProducts.containsKey(product)) {
      if (product.getBids().isEmpty()) {
        Bid first = new Bid(bidValue, user);
        product.getBids().add(first);
        bid = true;

      }
      else if (product.getHighestBid().getBidValue() < bidValue) {
        Bid first = new Bid(bidValue, user);
        product.getBids().add(first);
        bid = true;

      }
      else {
        bid = false;
      }
    }
    else {
      bid = false;
    }

    return bid;
  }

  /**
   * The register method will take as parameters a product object and a user object. This method will check if the product already
   * exists in the forSaleProducts hashMap and if it doesn't it will register the new product.
   * 
   * @param product
   * @param user
   * @return existance
   */
  public boolean register(Product product, User user) {
    boolean existance = false;
    if (product == null || user == null) {
      throw new IllegalArgumentException("product and user cannot be null");
    }
    if (forSaleProducts.containsKey(product)) {
      existance = false;
    }
    else {
      forSaleProducts.put(product, user);
      existance = true;
    }
    return existance;
  }

}
