package ru.eldorado.web.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class CoreObject {
    protected static final HashMap<String, String> attributes = new HashMap<>();

    public static final String ID = "CODE";

    abstract public String getQuery();

    abstract public void prepare(PreparedStatement prepStatement) throws SQLException;

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public HashMap<String, String> getAttributes() {
        return new HashMap<>(attributes);
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Order)) return false;

        Order otherOrder = (Order) obj;

        HashMap<String, String> otherAttributes = otherOrder.getAttributes();
        if (otherAttributes.size() != attributes.size()) return false;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (otherAttributes.get(entry.getKey()).equals(entry.getValue())) return false;
        }

        return true;
    }

    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            bld.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }

        return bld.toString();
    }
}
