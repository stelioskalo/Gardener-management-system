
package org.com1027.coursework.q3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the User class.
 * 
 * @author Stella Kazamia
 */
public class UserTest {

  /**
   * Creating a User object with valid input parameter for the user. Test that the toString() can be retrieved correctly.
   * 
   * 
   */
  @Test
  public void testUserConstruction() {
    User user = new User("Helen");
    assertEquals("H***n", user.toString());
  }

  /**
   * Creating a User object with invalid input parameter for the user. Test that the exception thrown.
   * 
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUserInvalidConstructions() {
    User user = new User(null);
  }

  @Test
  public void testDisplay() {
    AuctionHouse ahouse1 = new AuctionHouse();
    User user = new User("Stelios");
    BuyNowProduct product1 = new BuyNowProduct(1, "ski", 100.0, 20);
    BuyNowProduct product2 = new BuyNowProduct(2, "mask", 80.0, 40);
    BiddableProduct product3 = new BiddableProduct(3, "helmet", 3.0);
    BiddableProduct product4 = new BiddableProduct(4, "suit", 10.0);
    ahouse1.register(product1, user);
    ahouse1.register(product2, user);
    ahouse1.register(product3, user);
    ahouse1.register(product4, user);
    ahouse1.buyNow(product1, user, 3);
    ahouse1.buyNow(product2, user, 7);
    ahouse1.placeBid(product3, user, 5);
    ahouse1.placeBid(product4, user, 10);
 

    assertEquals("Purchases: \n" + "1 with quantity 3\n" + "2 with quantity 7\n", user.displayPurchases());
    assertEquals("Successful Bids: \n" + "3 at a cost of 5.0\n" + "4 at a cost of 10.0\n", user.displaySuccesfulBids());
  }

}