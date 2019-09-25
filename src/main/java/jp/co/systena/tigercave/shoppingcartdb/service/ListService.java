package jp.co.systena.tigercave.shoppingcartdb.service;

import java.util.ArrayList;
import java.util.List;
import jp.co.systena.tigercave.shoppingcartdb.model.Item;


public class ListService{

  public List<Item> getItemList(){

    List<Item> itemList = new ArrayList<Item>();

    /*インスタンス生成　
     * クラス名　変数名　＝　new　クラス名();
    */
    Item item = new Item();

    item.setItemid(10);
    item.setName("じゃがりこ");
    item.setPrice(150);
    itemList.add(item);


    item = new Item(20,"がりがりくん",200);
    itemList.add(item);

    return itemList;

  }
}