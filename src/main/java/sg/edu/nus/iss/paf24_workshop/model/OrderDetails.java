package sg.edu.nus.iss.paf24_workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    
    private Integer id;

    private String product;

    private Float unitPrice;

    private Float discount;

    private Integer quantity;

    private Integer orderId;
}
