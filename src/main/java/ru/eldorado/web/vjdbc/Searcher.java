package ru.eldorado.web.vjdbc;

import ru.eldorado.web.core.CoreObject;
import ru.eldorado.web.core.Order;

import java.sql.*;

import static java.lang.System.getProperty;
import static org.junit.Assert.assertEquals;
import static ru.eldorado.web.utils.HybrisProps.*;

public class Searcher {
    private static final String HYBRIS_DRIVER_CLASS = "de.hybris.vjdbc.VirtualDriver";
    private static final String HYBRIS_DRIVER_URL = "jdbc:hybris:flexiblesearch:http://%s:%s/virtualjdbc/service";

    private Connection connection;
    private Statement statement;

    public boolean isObjectRightCreated(CoreObject object) {
        try {
            beforeSearch();

            PreparedStatement prepStatement = connection.prepareStatement(object.getQuery());
            object.prepare(prepStatement);

            ResultSet result = prepStatement.executeQuery();
            ResultSetMetaData resultMetaData = result.getMetaData();
            if (!isObjectPresent(result)) {
                throw new AssertionError("The object isn't found");
            }

            afterSearch(result);

            return compareObj(result, resultMetaData);
        } catch (SQLException e) {
            throw new AssertionError("The object isn't found", e);
        }
    }

    private void beforeSearch() {
        try {
            Class.forName(HYBRIS_DRIVER_CLASS).newInstance();
            connection = DriverManager.getConnection(getHybrisDriverUrl(), getProperty(PROP_VJDBC_USER), getProperty(PROP_VJDBC_PASSWORD));
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void afterSearch(ResultSet res) {
        try {
            res.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Searcher afterSearch " + e.getMessage());
        }
    }

    private boolean isObjectPresent(ResultSet result) {
        int size = 0;
        try {
            result.last();
            size = result.getRow();
        } catch (SQLException e) {
            System.out.println("Searcher isObjectPresent " + e.getMessage());
        }

        return size == 1;
    }

    private boolean compareObj(ResultSet result, ResultSetMetaData resultMetaData) {
        Order order = Order.getInstance();
        try {
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                String expected = order.getAttribute(resultMetaData.getColumnName(i));
                if (expected == null) continue;

                String actual = result.getString(resultMetaData.getColumnName(i));
                assertEquals(expected, actual);
            }
        } catch (SQLException e) {
            System.out.println("Searcher compareObj" + e.getMessage());
        }

        return true;
    }

    private String getHybrisDriverUrl() {
        return String.format(HYBRIS_DRIVER_URL, getProperty(PROP_HOST), getProperty(PROP_PORT));
    }
}
