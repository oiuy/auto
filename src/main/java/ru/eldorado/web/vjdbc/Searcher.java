package ru.eldorado.web.vjdbc;

import ru.eldorado.web.core.CoreObject;
import ru.eldorado.web.core.Order;

import java.sql.*;

public class Searcher {
    private static final String HYBRIS_DRIVER_CLASS = "de.hybris.vjdbc.VirtualDriver";
    private static final String HYBRIS_DRIVER_URL = "jdbc:hybris:flexiblesearch:http://10.0.1.140:9001/virtualjdbc/service";
    private static final String HYBRIS_USER = "admin";
    private static final String HYBRIS_PASSWORD = "PlanBPassword2015!";

    private Connection connection;
    private Statement statement;

    private void beforeSearch(){
        try {
            Class.forName(HYBRIS_DRIVER_CLASS).newInstance();
            connection = DriverManager.getConnection(HYBRIS_DRIVER_URL, HYBRIS_USER, HYBRIS_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch(ClassNotFoundException e){
            System.out.println("Searcher beforeSearch "+e.getMessage());

        }
        catch(SQLException e){
            System.out.println("Searcher beforeSearch "+e.getMessage());
        }
        catch (Exception e){
            System.out.println("Searcher beforeSearch "+e.getMessage());
        }
    }

    private void afterSearch(ResultSet res){
        try {
            res.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Searcher afterSearch "+e.getMessage());
        }
    }

    private boolean isObjectPresent(ResultSet result){
        int size = 0;
        try {
            result.last();
            size = result.getRow();
        }
        catch (SQLException e){
            System.out.println("Searcher isObjectPresent "+e.getMessage());
        }
        return size==1;
    }

    private boolean compareObj(ResultSet result, ResultSetMetaData resultMetaData) {
        Order order = Order.getInstance();
        try {
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                String columnName = resultMetaData.getColumnName(i);
                if(columnName.equals(Order.CREATEDATE)){
                    String columnValue = result.getString(i);
                    int dotIndex1 = columnValue.indexOf(".");
                    columnValue = columnValue.substring(0,dotIndex1-2);
                    String orderValue = order.getAttribute(Order.CREATEDATE);
                    int dotIndex2 = orderValue.indexOf(".");
                    orderValue = orderValue.substring(0,dotIndex2-2);
                    if(!columnValue.equals(orderValue)) return false;
                }
                else {
                    if (!order.getAttribute(resultMetaData.getColumnName(i)).equals(result.getString(i))) {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Searcher compareObj" + e.getMessage());
        }
        return true;
    }

    public boolean isObjectRightCreated() {
        try {
            beforeSearch();
            Order order =Order.getInstance();
            PreparedStatement prepStatement = connection.prepareStatement(order.getQuery());
            for (int i = 1; i <= order.getSubstitutionNumber(); i++) {
                prepStatement.setString(i, order.getAttributes().get(order.getID()));
            }
            ResultSet result = prepStatement.executeQuery();
            ResultSetMetaData resultMetaData = result.getMetaData();
            if (!isObjectPresent(result)) {
                System.out.println("The object isn't found");
                return false;
            }
            afterSearch(result);
            return compareObj(result, resultMetaData);
        }
        catch (SQLException e){
            System.out.println("Searcher isObjectRightCreated "+e.getMessage());
        }
        return false;
    }
}
