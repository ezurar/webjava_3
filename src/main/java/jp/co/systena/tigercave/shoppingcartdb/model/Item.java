package jp.co.systena.tigercave.shoppingcartdb.model;

public class Item {

  // 商品ID
  private int itemid;

  // 名前
  private String name;

  // 価格
  private int price;

  //ゲッター,セッター
  public int getItemid() {
    return itemid;
  }

  public void setItemid(int itemid) {
    this.itemid = itemid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


  public Item() {

  }

  public Item(int itemid, String name, int price) {
    this.itemid = itemid;
    this.name = name;
    this.price = price;
  }

}