/*
 * Product.java
 */
package org.com1027.coursework.q1;

import java.util.ArrayList;
import java.util.List;

public class Product {

  private int       productId     = 0;
  private String    productName   = null;
  private double    reservedPrice = 0;
  private List<Bid> bids          = null;

  /**
   * Public constructor used in order to be able to create product objects and consists of validation checks for invalid or null input.
   * @param productId
   * @param productName
   * @param reservedPrice
   */
  public Product(int productId, String productName, double reservedPrice) {
    super();
    if (productId <= 0) {
      throw new IllegalArgumentException("product ID cannot be 0 or less");
    }
    else {
      this.productId = productId;
    }
    if (productName != null) {
      this.productName = productName;
    }
    else {
      throw new IllegalArgumentException("The name of the product can only contain letters");
    }
    if (reservedPrice < 0) {
      throw new IllegalArgumentException("the reserved price cannot be less than 0");
    }
    else {
      this.reservedPrice = reservedPrice;
    }
   this.bids = new ArrayList<Bid>();
  }

  /**
   * Method used in order to calculate the highest bid recorded.
   * @return highestBid
   */
  public Bid getHighestBid() {
    Bid highestBid = null;

    if (bids.isEmpty()) {
      System.out.println("no bid returned");
      return null;
    }
    else {
      Bid highestBid2 = new Bid(0.00, null);
      for (int i = 0; i < bids.size(); i++) {
        if (bids.get(i).getBidValue() > highestBid2.getBidValue()) {
          highestBid2 = bids.get(i);
          highestBid = highestBid2;

        }

      }
    }
    return highestBid;
  }

  public int getProductId() {
    return this.productId;
  }

  public String getProductName() {
    return this.productName;
  }

  public double getReservedPrice() {
    return this.reservedPrice;
  }

  public List<Bid> getBids() {
    return bids;
  }

  /**
   * Place bid method that alows a user to bid on an item.
   * @param user
   * @param bidValue
   * @return bid
   */
  public boolean placeBid(User user, double bidValue) {
    boolean bid = false;

    if (bidValue <= 0 || user == null) {
      throw new IllegalArgumentException("bid value cannot be 0 or less and user cannot be null");
    }

    if (bids.isEmpty()) {
      Bid first = new Bid(bidValue, user);
      bids.add(first);
      bid = true;
    }
    else if (getHighestBid().getBidValue() < bidValue) {
      Bid first = new Bid(bidValue, user);
      bids.add(first);
      bid = true;

    }
    else {
      bid = false;
    }

    return bid;
  }
}
