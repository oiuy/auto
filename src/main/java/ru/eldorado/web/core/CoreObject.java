package ru.eldorado.web.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class CoreObject {

   protected static final HashMap<String,String> attributes = new HashMap<>();
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

   abstract public int getSubstitutionNumber();

   abstract public String getQuery();

   public void setAttribute(String key, String value){
       attributes.put(key,value);
   }

   public HashMap<String,String> getAttributes(){
       return new HashMap<>(attributes);
   }

   public String getID(){
       return "";
   }

   public String getAttribute(String key){
       return attributes.get(key);
   }

    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof Order)) return false;
        Order otherOrder = (Order)obj;
        HashMap<String,String> otherAttributes = otherOrder.getAttributes();
        if (otherAttributes.size()!=attributes.size()) return false;
        for (Map.Entry<String,String> entry: attributes.entrySet()){
            if(otherAttributes.get(entry.getKey()).equals(entry.getValue())) return false;
        }
        return true;
    }

    public String toString(){
        StringBuilder bld = new StringBuilder();
        for(Map.Entry<String,String> entry: attributes.entrySet()){
            bld.append(entry.getKey()+" = "+entry.getValue()+"\n");
        }
        return bld.toString();
    }
}
