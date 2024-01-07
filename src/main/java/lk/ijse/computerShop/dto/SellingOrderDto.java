package lk.ijse.computerShop.dto;
/* 
    @author Sachi_S_Bandara
    @created 11/28/2023 - 10:40 PM 
*/

import lk.ijse.computerShop.dto.tm.SellingOrderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellingOrderDto {
    public String orderId;
    public String customerId;
    public String orderDate;
    public double total;
    List<SellingOrderTm> tmList = new ArrayList<>();
}
