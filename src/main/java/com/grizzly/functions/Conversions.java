
package com.grizzly.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//A class containing reflection methods.
public class Conversions {

    /**
     * Creates an String from any object. The String format is "member name:member value \n"
     *
     * @param obj the source object
     * @return a string
     */
    public static String stringifyObject(Object obj) {
        String values = "";
        List<String> orderObject = new ArrayList<String>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true); // if you want to modify private fields
            try {
                if (!field
                        .getName()
                        .toUpperCase()
                        .equalsIgnoreCase(
                                field.get(obj).toString().toUpperCase())) {
                    if (field.getType().equals(boolean.class)) {
                        if (field.get(obj).toString().toLowerCase()
                                .equals("false")) {
                            orderObject.add(TextFunctions
                                    .setFirstUCase(field.getName())
                                    + ":"
                                    + "False" + "\n");
                        } else {
                            orderObject.add(TextFunctions
                                    .setFirstUCase(field.getName())
                                    + ":"
                                    + "True" + "\n");
                        }
                    } else {

                        orderObject.add(TextFunctions.setFirstUCase(field
                                .getName())
                                + ":"
                                + TextFunctions.setFirstUCase(field.get(
                                obj).toString()) + "\n");
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        boolean order = false;
        int c = 0;
        int cOrder = 0;
        int min = 0;
        int minPos = 0;
        int orSize = orderObject.size();
        StringBuilder sb = new StringBuilder();

        while (!order) {
            if (c < orderObject.size()) {
                if (min == 0) {
                    min = orderObject.get(c).length();
                }

                if (min > orderObject.get(c).length()) {
                    min = orderObject.get(c).length();
                    minPos = c;
                }
                c++;
            } else {
                if (cOrder < orSize) {
                    cOrder++;
                    sb.append(orderObject.get(minPos));
                    orderObject.remove(minPos);
                } else {
                    order = true;
                }
                c = min = minPos = 0;
            }
        }
        return sb.toString();
    }

    /**
     * Creates a Json Object from any object using org.json
     *
     * @param obj the source object
     * @return a org.json.JSONObject instance
     */
    private static JSONObject jsonifyObject(Object obj) {

        JSONObject jsonObject = new JSONObject();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (!field.getName().toUpperCase().equalsIgnoreCase(field.get(obj).toString().toUpperCase())
                        && !field.isAnnotationPresent(com.fasterxml.jackson.annotation.JsonIgnore.class)) {
                    if (field.getType().getSuperclass().isInstance(Object.class)) {
                        jsonObject.put(field.getName(), jsonifyObject(field.get(obj)));
                    } else {
                        jsonObject.put(field.getName(), field.get(obj));
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    private static String ObjectToJson(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static <T> String ObjectToJson(T object, Class<T> objectClass) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static <T> T JsonToObject(Class<T> theObjectClass, String json) throws InstantiationException, IllegalAccessException, IOException {
        Class<T> objectClass = theObjectClass;
        ObjectMapper mapper = new ObjectMapper();
        T n = mapper.readValue(json, objectClass);
        return n;
    }
}
