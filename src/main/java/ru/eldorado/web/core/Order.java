package ru.eldorado.web.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order extends CoreObject {

    private static final ThreadLocal<Order> INSTANCE = new ThreadLocal<>();

    public enum Status {
        RECEIVED
    }

    public static final String CREATEDATE = "CREATEDATE";
    public static final String TOTALPRICE = "TOTALPRICE";
    public static final String PRODUCTNUMBER = "PRODUCTNUMBER";
    public static final String SUBTOTAL = "SUBTOTAL";
    public static final String DELIVERYCOST = "DELIVERYCOST";
    public static final String DELIVERYMODE = "DELIVERYMODE";
    public static final String STATUS = "STATUS";
    public static final String USEREMAIL = "USEREMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String USERPHONE = "USERPHONE";
    public static final String PRODUCTID = "PRODUCTID";
    public static final String PRODUCTPRICE = "PRODUCTPRICE";
    public static final String POINTOFSERVICE = "POINTOFSERVICE";
    public static final String PRODUCTNAME = "PRODUCTNAME";

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

    public static Order getInstance() {
        Order order = INSTANCE.get();
        if (order == null) {
            synchronized (Order.class) {
                if (order == null) {
                    order = new Order();
                }
            }
        }

        return order;
    }

    @Override
    public void prepare(PreparedStatement prepStatement) throws SQLException {
        for (int i = 1; i <= 5; i++) {
            prepStatement.setString(i, attributes.get(ID));
        }
    }

    public String getQuery() {
        return QUERY_ORDER_BY_ID;
    }

    public String getID() {
        return ID;
    }
}
