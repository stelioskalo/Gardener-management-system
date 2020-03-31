package org.com1027.coursework.q1;

import static org.junit.Assert.*;

import org.junit.Test;


public class BidTest {

  @Test
  public void test() {
    User user = new User("Stelios");
    Bid bid = new Bid (300, user);
    assertEquals(300, bid.getBidValue(), 0);
    assertEquals("S***s", bid.getBuyer().toString());
  }

}
