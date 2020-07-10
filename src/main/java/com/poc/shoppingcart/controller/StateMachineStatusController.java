package com.poc.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poc.shoppingcart.entity.CartTemp;
import com.poc.shoppingcart.entity.Order;
import com.poc.shoppingcart.service.CartMgmtService;
import com.poc.shoppingcart.service.OrderService;

/**
 * @author ArunabhaB
 *
 */

@Controller
@RequestMapping("/Cart")
public class StateMachineStatusController {

	@Autowired
	CartMgmtService cartService;

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "upload";
	}

	@RequestMapping(value = "/fetchCartPage", method = RequestMethod.GET)
	public String fetCartPage(@RequestParam("cartId") Integer cartId, @RequestParam("orderId") Long orderId,
			@RequestParam("custId") Long custId, @RequestParam("status") String status) {
		System.out.println(cartId);
		System.out.println(orderId);

		return "cart";
	}

	@RequestMapping(value = "/getStateMachineStatus", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public String getSMStatus(@RequestParam("custId") Integer custId, Model model) throws Exception {
		ModelAndView view = new ModelAndView();
		CartTemp cartTemp = cartService.getCartByCustId(custId);
		Order order = null;
		if (!ObjectUtils.isEmpty(cartTemp)) {
			order = orderService.getOrderDtls(cartTemp.getOrderId());
			System.out.println(cartTemp);
//			return cartTemp + "-------------" + order.toString();
			return "previousUser";
		}

		model.addAttribute("custId", custId);

		System.out.println(order);

//	    view.setViewName("cartCreation");
//	    view.addObject("myClass", custId);
		return "cartCreation";

	}

}
