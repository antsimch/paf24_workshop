package sg.edu.nus.iss.paf24_workshop.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf24_workshop.model.OrderDetails;

@Repository
public class OrderDetailsRepository {
    
    private JdbcTemplate template;

    private final String FIND_BY_ID_SQL = 
            "select * from order_details where order_id = ?";

    private final String CREATE_SQL =
            """
                insert into order_details
                (product, unit_price, discount, quantity, order_id)
                values (?, ?, ?, ?, ?)
                    """;

    public OrderDetailsRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<OrderDetails> findOrderDetailsByOrderId(int orderId) {
        return template.query(
                FIND_BY_ID_SQL, 
                BeanPropertyRowMapper
                .newInstance(OrderDetails.class), 
                orderId);
    }

    public Boolean createOrderDetails(
            OrderDetails orderDetails,
            Integer orderId) {

        Integer created = template.update(
                CREATE_SQL, 
                orderDetails.getProduct(), 
                orderDetails.getUnitPrice(), 
                orderDetails.getDiscount(), 
                orderDetails.getQuantity(),
                orderId);

        return created > 0 ? true : false;
    }
}
