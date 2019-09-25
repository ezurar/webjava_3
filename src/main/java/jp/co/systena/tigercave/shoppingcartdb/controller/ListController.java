package jp.co.systena.tigercave.shoppingcartdb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigercave.shoppingcartdb.model.Cart;
import jp.co.systena.tigercave.shoppingcartdb.model.Item;
import jp.co.systena.tigercave.shoppingcartdb.model.ListForm;
import jp.co.systena.tigercave.shoppingcartdb.model.Order;
import jp.co.systena.tigercave.shoppingcartdb.service.ListService;



//Viewを返却するアノテーション
@Controller
public class ListController {

  //セッション管理
  @Autowired
  HttpSession session;

  //URLとのマッピング
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ModelAndView index(ModelAndView mav) {


    Map<Integer, Item> itemListMap = getItemListMap();
    mav.addObject("itemList", itemListMap);

    Cart cart = getCart();
    mav.addObject("orderList", cart.getOrderList());

    // 合計金額計算
    int totalPrice = 0;
    for (Order order : cart.getOrderList()) {
      if (itemListMap.containsKey(order.getItemid())) {
        totalPrice += order.getNum() * itemListMap.get(order.getItemid()).getPrice();
      }
    }
    mav.addObject("totalPrice",totalPrice);


    mav.setViewName("ListView");
    return mav;
  }

  private Cart getCart() {
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    return cart;
  }

  private Map<Integer, Item> getItemListMap(){
    ListService service = new ListService();
    List<Item> itemList = service.getItemList();

    Map<Integer, Item> itemListMap =new HashMap<Integer, Item>();
    for (Item item : itemList) {
      itemListMap.put(item.getItemid(), item);
    }

    return itemListMap;

  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  private ModelAndView order(ModelAndView mav, @Valid ListForm listForm,
      BindingResult bindingResult, HttpServletRequest request) {

    Cart cart = getCart();

    if (bindingResult.getAllErrors().size() > 0){
      mav.addObject("listForm", listForm);

      Map<Integer, Item> itemListMap = getItemListMap();
      mav.addObject("itemList", itemListMap);

      mav.setViewName("ListView");

      return mav;

    }
    Order order = new Order();
    order.setItemid(listForm.getItemid());
    order.setNum(listForm.getNum());
    cart.add(order);

    session.setAttribute("cart", cart);
    return new ModelAndView("redirect:/list");

  }

}
