package sg.edu.nus.iss.paf24_workshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf24_workshop.model.OrderDetails;
import sg.edu.nus.iss.paf24_workshop.model.Orders;
import sg.edu.nus.iss.paf24_workshop.repository.OrderDetailsRepository;
import sg.edu.nus.iss.paf24_workshop.repository.OrdersRepository;

@Service
public class OrdersService {
 
    private OrdersRepository ordersRepo;
    private OrderDetailsRepository orderdRepo;

    public OrdersService(OrdersRepository ordersRepo, 
            OrderDetailsRepository orderdRepo) {

        this.ordersRepo = ordersRepo;
        this.orderdRepo = orderdRepo;
    }

    public Orders findOrderById(int orderId) {
        return ordersRepo.findOrderById(orderId);
    }

    public List<OrderDetails> findOrderDetailsByOrderId(
            int orderId) {

        return orderdRepo.findOrderDetailsByOrderId(orderId);
    }

    @Transactional
    public Integer createOrder(Orders order, List<OrderDetails> orderDetailsList) {
        
        Integer orderId = ordersRepo.createOrders(order);

        for (OrderDetails od : orderDetailsList) {
            orderdRepo.createOrderDetails(od, orderId);
        }
        
        return orderId;
    }
}
