package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuctionHouse {

  /**
   * The hashMaps are created in order to store the product objects. If a product is for sale we store it in the forSaleProducts
   * hashmap, if a product has been sold we store it in the soldProducts hashMap and if a product hasn't been sold we store it in
   * the unsoldProducts hashmap.
   */
  private Map<Product, User> forSaleProducts;
  private Map<Product, User> soldProducts;
  private Map<Product, User> unsoldProducts;

  /**
   * Constructor is needed in order to be able to create an AuctionHouse object.
   */
  public AuctionHouse() {
    super();
    this.forSaleProducts = new HashMap<Product, User>();
    this.soldProducts = new HashMap<Product, User>();
    this.unsoldProducts = new HashMap<Product, User>();
  }

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
    if (this.soldProducts.isEmpty() == false) {
      for (Product p : key) {
        b.append(p.getProductId() + " - " + p.displayUserInfoForProduct() + "\n");

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

    if (product.isProductSold() == true) {
      this.forSaleProducts.remove(product);
      this.soldProducts.put(product, null);
    }
    else {
      this.forSaleProducts.remove(product);
      this.unsoldProducts.put(product, null);
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
      if (((BiddableProduct) product).getBids().isEmpty()) {
        Bid first = new Bid(bidValue, user);
        ((BiddableProduct) product).getBids().add(first);
        bid = true;

      }
      else if (product.getCurrentPrice() < bidValue) {
        Bid first = new Bid(bidValue, user);
        ((BiddableProduct) product).getBids().add(first);
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

  /**
   * the buyNow method attempts to purchase, either purchase or bid a product.
   * 
   * @param product
   * @param buyer
   * @param quantity
   * @return buy
   */
  public boolean buyNow(Product product, User buyer, int quantity) {
    boolean buy = true;
    if (((BuyNowProduct) product).attemptToPurchase(buyer, quantity) == true) {
      buy = true;
      soldProducts.put(product, buyer);
    }
    else {
      buy = false;
    }
    return buy;
  }

  public String highestSoldProducts() {
    String string = "";
    int id1 = 0;
    int id2 = 0;
    double highest = 0;
    ArrayList<Product> list = new ArrayList<Product>();
    ArrayList<String> string2 = new ArrayList<String>();
    for (Product p : soldProducts.keySet()) {
      list.add(p);
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCurrentPrice() > highest) {
        highest = list.get(i).getCurrentPrice();
        id1 = list.get(i).getProductId();

      }

    }
    for (int i = 0; i < list.size(); i++) {
      if (highest <= list.get(i).getCurrentPrice()) {
        id2 = list.get(i).getProductId();
       
      }
    }
    string2.add("product id " + id1 + "\n");
    string2.add("product id " + id2 + "\n");
   
    string = "Highest Sold Products: \n" + String.join("", string2);
    return string;
  }

}
