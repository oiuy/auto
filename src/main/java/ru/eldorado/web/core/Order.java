package ru.eldorado.web.core;

import java.util.HashMap;
import java.util.Map;

public class Order extends CoreObject{

    public static final String ID = "CODE";


    private static final String QUERY_ORDER_BY_ID =
            "select createdts as "+ CREATEDATE+","+ TOTALPRICE+","+" ({{select count(distinct {product}) from {orderentry join "+
    "order on {orderentry.order} = {order.pk} and {order.code}=?}}}) as "+ PRODUCTNUMBER+","+ SUBTOTAL+","+ DELIVERYCOST+","+
        " ({{select code from {deliverymode} where {deliverymode.pk} in ({{select deliverymodepk from " +
                "{order} where {order.code}=?}})}})"+
        " as "+ DELIVERYMODE+", ({{select {enumerationvalue.code} as "+ STATUS +" from {enumerationvalue join order on " +
                "{enumerationvalue.pk}={order.status} and {order.code}=?}}}) as "+ STATUS+","+
        "({{select {address.streetname} from {address} where {pk}=({{select {pointofservice.address} from " +
                    "{pointofservice join order on {pointofservice.pk}={order.pointofservice} and " +
                    "{order.code}=?}}})}}) as "+POINTOFSERVICE+", "+
        " p_email as "+ USEREMAIL+", p_name as "+ USERNAME+","+
        " p_phonenumber as "+ USERPHONE+" from {order} where {order.code}=?";

    private int substitutionNumber = 5;



    private Order(){}

    private static class OrderHelper {
        private static final Order INSTANCE = new Order();
    }

    public static Order getInstance() {
        return OrderHelper.INSTANCE;
    }

    public String getQuery(){
        return  QUERY_ORDER_BY_ID;
    }

    public int getSubstitutionNumber(){
        return substitutionNumber;
    }





    public String getID(){
        return ID;
    }
}
