package jp.co.systena.tigercave.shoppingcartdb.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Order> orderList = new ArrayList<Order>();

  public Cart() {

  }

  public void add(Order order) {
    orderList.add(order);
  }

  public List<Order> getOrderList() {
    return this.orderList;
  }

  public int getTotalPrice() {
    int totalPrice = 0;

    for(Order order : this.orderList) {

    }

    return totalPrice;

  }
}