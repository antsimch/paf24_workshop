package sg.edu.nus.iss.paf24_workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.paf24_workshop.exception.OrderIsNotCreatedException;
import sg.edu.nus.iss.paf24_workshop.model.OrderDetails;
import sg.edu.nus.iss.paf24_workshop.model.Orders;
import sg.edu.nus.iss.paf24_workshop.service.OrdersService;

@Controller
@RequestMapping(path = "/api/orders")
public class OrdersController {
    
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public String getOrderForm(Model model, HttpSession session) {
        model.addAttribute("orders", new Orders());
        return "ordersForm";
    }

    @PostMapping
    public String postOrderForm(Orders order, BindingResult binding, HttpSession session) {

        if (binding.hasErrors())
            return "ordersForm";
        
        session.setAttribute("orders", order);
        return "redirect:/api/orders/details";
    }

    @GetMapping(path = "/details")
    public String getOrderDetailsForm(Model model) {
        model.addAttribute("orderDetails", new OrderDetails());
        return "orderDetailsForm";
    }

    @PostMapping(path = "/details")
    public String postOrderDetailsForm(OrderDetails orderDetails, BindingResult binding, HttpSession session) {

        if (binding.hasErrors())
            return "orderDetailsForm";
        
        List<OrderDetails> orderDetailsList = (List<OrderDetails>) session.getAttribute("orderDetailsList");

        if (session.getAttribute("orderDetailsList") == null)
            orderDetailsList = new ArrayList<OrderDetails>();

        orderDetailsList.add(orderDetails);
        session.setAttribute("orderDetailsList", orderDetailsList);
    
        return "orderDetailsForm";
    }

    @GetMapping(path = "/create")
    public String createOrder(HttpSession session, Model model) {

        Orders order = (Orders) session.getAttribute("orders");
        List<OrderDetails> orderDetailsList = (List<OrderDetails>) session.getAttribute("orderDetailsList");

        Integer orderId = ordersService.createOrder(order, orderDetailsList);

        if (order == null) {
            throw new OrderIsNotCreatedException("Order is not created");
        } else {
            model.addAttribute("orders", ordersService.findOrderById(orderId));
            model.addAttribute("orderDetailsList", ordersService.findOrderDetailsByOrderId(orderId));
            return "orderConfirmation";
        }
    }
}
