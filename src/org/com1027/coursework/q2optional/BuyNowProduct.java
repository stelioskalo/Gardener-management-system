package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.List;

public class BuyNowProduct extends Product {

  private double         price     = 0;
  private int            quantity  = 0;
  private List<Purchase> purchases = new ArrayList<Purchase>();

  public BuyNowProduct(int productId, String productName, double price, int quantity) {
    super(productId, productName);
    if (price <= 0) {
      throw new IllegalArgumentException("Price cannot be less than 0");
    }
    else {
      this.price = price;
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity cannot be less than 0");
    }
    else {
      this.quantity = quantity;
    }

  }

  public boolean attemptToPurchase(User user, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity cannot be 0 or less");
    }
    boolean purchase = false;
    double count = 0;
    for (int i = 0; i < purchases.size(); i++) {
      count += purchases.get(i).getQuantityPurchased();
    }
    if (count == getQuantity() || count + quantity > getQuantity()) {
      purchase = false;
    }
    else {
      Purchase purchase1 = new Purchase(user, quantity);
      purchases.add(purchase1);
      purchase = true;
    }
    return purchase;
  }

  public int getQuantity() {
    return this.quantity;
  }

  @Override
  public String displayHistory() {
    String string = null;
    ArrayList<String> list = new ArrayList<String>();
    if (this.purchases.isEmpty()) {
      string = getProductId() + ": " + getProductName() + " quantity" + ": " + getQuantity() + "\n" + "no purchases";
    }
    else {
      for (int i = 0; i < this.purchases.size(); i++) {
        list.add(this.purchases.get(i).getBuyer().toString() + " bought " + this.purchases.get(i).getQuantityPurchased() + "\n");
      }

      string = getProductId() + ": " + getProductName() + " quantity: " + getQuantity() + "\n" + "buy now history: \n"
          + String.join("", list);
    }
    return string;
  }

  @Override
  public String displayUserInfoForProduct() {
    String string = "";
    if (this.purchases.isEmpty()) {
      return string;
    }
    for (int i = purchases.size() - 1; i < purchases.size(); i++) {
      string = purchases.get(i).getBuyer().toString() + " bought " + purchases.get(i).getQuantityPurchased();
    }
    return string;
  }

  @Override
  public double getCurrentPrice() {
    return this.price;
  }

  public int howManyPurchases() {
    int count = 0;
    for (int i = 0; i < purchases.size(); i++) {
      count += purchases.get(i).getQuantityPurchased();
    }
    return count;
  }

  @Override
  public boolean isProductSold() {
    boolean sold = true;
    if (howManyPurchases() == getQuantity()) {
      sold = true;
    }
    else {
      sold = false;
    }
    return sold;
  }

}
