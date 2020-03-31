package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiddableProduct extends Product {

  private double    reservedPrice = 0;
  private List<Bid> bids          = new ArrayList<Bid>();

  public BiddableProduct(int productId, String productName, double reservedPrice) {
    super(productId, productName);
    if (reservedPrice <= 0) {
      throw new IllegalArgumentException("the reserved price cannot be 0 or less");
    }
    this.reservedPrice = reservedPrice;

  }

  public boolean attemptToPurchase(User user, double bidValue) {
    boolean purchase = true;
    if (user == null || bidValue <= 0) {
      throw new IllegalArgumentException("user cannot be null and the bidValue cannot be 0 or less");
    }
    if (bids.isEmpty()) {
      Bid bid = new Bid(bidValue, user);
      bids.add(bid);
      purchase = true;
    }
    else if (getCurrentPrice() < bidValue) {
      Bid bid = new Bid(bidValue, user);
      bids.add(bid);
      purchase = true;
    }
    else {
      purchase = false;
    }
    return purchase;

  }

  @Override
  public String displayHistory() {
    String string = null;
    ArrayList<String> list = new ArrayList<String>();
    if (bids.isEmpty()) {
      string = getProductId() + ": " + getProductName() + " = no bids";
    }
    else {
      for (int i = 0; i < bids.size(); i++) {
        list.add(bids.get(i).getBuyer() + " bid £" + bids.get(i).getBidValue() + "\n");
      }
      Collections.reverse(list);
      string = getProductId() + ": " + getProductName() + " = \n" + String.join("", list);
    }
    return string;
  }

  @Override
  public String displayUserInfoForProduct() {
    String string = null;
    Bid bid = new Bid(0, null);
    ArrayList<String> list = new ArrayList<String>();
    if (bids.isEmpty()) {
      string = "";
    }
    else {
      for (int i = 0; i < bids.size(); i++) {
        if (bids.get(i).getBidValue() > bid.getBidValue()) {
          bid = bids.get(i);

        }
      }
      list.add(bid.getBuyer().getName().substring(0, 1) + "***"
          + bid.getBuyer().getName().substring(bid.getBuyer().getName().length() - 1, bid.getBuyer().getName().length()) + " bid £"
          + bid.getBidValue());
      string = String.join("", list);
    }
    return string;
  }

  @Override
  public double getCurrentPrice() {
    double currentPrice = 0;
    Bid highest = new Bid(0, null);
    if (bids.isEmpty()) {
      currentPrice = 0;
    }
    else {
      for (int i = 0; i < bids.size(); i++) {
        if (bids.get(i).getBidValue() > highest.getBidValue()) {
          highest = bids.get(i);
        }
      }
      currentPrice = highest.getBidValue();
    }
    return currentPrice;
  }

  public List<Bid> getBids() {
    return this.bids;
  }

  @Override
  public boolean isProductSold() {
    boolean sold = false;
    for (int i = 0; i < bids.size(); i++) {
      if (bids.get(i).getBidValue() > reservedPrice) {
        sold = true;
      }
      else if (bids.isEmpty()) {
        sold = false;
      }
      else {
        sold = false;
      }
    }
    return sold;
  }

}
