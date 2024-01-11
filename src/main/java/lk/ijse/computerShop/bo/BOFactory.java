package lk.ijse.computerShop.bo;
/* 
    @author Sachi_S_Bandara
    @created 1/11/2024 - 9:14 PM 
*/

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER,QUERY
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case QUERY:
                return new QueryBOImpl();
            default:
                return null;
        }
    }
}
