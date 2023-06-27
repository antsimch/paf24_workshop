package sg.edu.nus.iss.paf24_workshop.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf24_workshop.exception.OrderNotFoundException;
import sg.edu.nus.iss.paf24_workshop.model.Orders;

@Repository
public class OrdersRepository {
    
    private JdbcTemplate template;

    private final String FIND_BY_ID_SQL =
            "select * from orders where order_id = ?";

    private final String CREATE_SQL =
            """
                insert into orders
                (order_date, customer_name, ship_address, notes, tax)
                values (?, ?, ?, ?, ?)
                    """;

    public OrdersRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Orders findOrderById(int orderId) {

        List<Orders> orders = template.query(
                FIND_BY_ID_SQL, 
                BeanPropertyRowMapper
                .newInstance(Orders.class), 
                orderId);

        if (orders.isEmpty()) 
            throw new OrderNotFoundException("Order has not been created");

        return orders.get(0);
    }

    public Integer createOrders(Orders order) {
        
        KeyHolder generatedKey = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(
                    Connection con) throws SQLException {
                
                PreparedStatement ps = con.prepareStatement(
                        CREATE_SQL, 
                        new String[] {"order_id"});


                ps.setDate(1, new Date(0));
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());
                ps.execute();
                return ps;
            }
        };

        template.update(psc, generatedKey);

        return generatedKey.getKey().intValue();
    }
}
