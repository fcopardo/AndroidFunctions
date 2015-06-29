
package com.grizzly.functions;

import android.os.Bundle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Maps an object with a bundle. It may fail with complex objects.
     * @param object The object to be filled.
     * @param objectClass The class of the Object.
     * @param bundle The bundle which values we pretend to put inside the object.
     */
    public static <T> void bundleToObject(T object, Class<T> objectClass, Bundle bundle){
        for(Field field : objectClass.getDeclaredFields()){
            field.setAccessible(true);
            if(bundle.containsKey(field.getName())){
                try {
                    field.set(object, bundle.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Takes a list and converts it to a map
     * @param list
     * @return
     */
    public static <C, T> Map<C, T> getDataAsMap(List<T> list, String getter, Class<C> indexClass) {
        Map<C, T> map = new LinkedHashMap<>();
        boolean dontWasteTime = false;
        for (T element : list) {
            try {
                Method method = null;
                method = element.getClass().getMethod(getter.replace("(", "").replace(")", ""));
                map.put((C) method.invoke(element), element);
                if(dontWasteTime) break;
            } catch (NoSuchMethodException e) {
                dontWasteTime = true;
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                dontWasteTime = true;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                dontWasteTime = true;
                e.printStackTrace();
            }
        }
        return map;
    }
}
