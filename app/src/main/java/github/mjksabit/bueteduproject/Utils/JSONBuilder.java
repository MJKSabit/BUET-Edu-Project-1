package github.mjksabit.bueteduproject.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONBuilder {
    public static JSONObject mapToJSON(Map<String, Object> map) throws JSONException {
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, Object> subMap = (Map<String, Object>) value;
                obj.put(key, mapToJSON(subMap));
            } else if (value instanceof List) {
                obj.put(key, listToJSONArray((List) value));
            }
            else {
                obj.put(key, value);
            }
        }
        return obj;
    }

    public static JSONArray listToJSONArray(List<Object> list) throws JSONException {
        JSONArray arr = new JSONArray();
        for(Object obj: list) {
            if (obj instanceof Map) {
                arr.put(mapToJSON((Map) obj));
            }
            else if(obj instanceof List) {
                arr.put(listToJSONArray((List) obj));
            }
            else {
                arr.put(obj);
            }
        }
        return arr;
    }

    /// Reverse Library by MJKSabit

    public static Map<String, Object> jsonToMap(JSONObject jsonObject) throws JSONException {
        Iterator<String> keys = jsonObject.keys();
        Map<String, Object> map = new HashMap<>();

        while (keys.hasNext()) {
            String key = keys.next();

            Object object = jsonObject.get(key);
            if (object instanceof JSONArray) {
                object = jsonToList((JSONArray) object);
            } else if (object instanceof JSONObject) {
                object = jsonToMap((JSONObject) object);
            }
            map.put(key, object);
        }

        return map;
    }

    public static List<Object> jsonToList(JSONArray jsonArray) throws JSONException {
        List<Object> list = new ArrayList<>();

        for (int i=0; i<jsonArray.length(); i++){

            Object object = jsonArray.get(i);
            if (object instanceof JSONArray) {
                object = jsonToList((JSONArray) object);
            } else if (object instanceof JSONObject) {
                object = jsonToMap((JSONObject) object);
            }
            list.add(object);
        }

        return list;
    }
}
