package ua.org.myko.system.util;

/**
 * Created by ronaldo on 17/01/2017.
 */
public class MockHelper {
    public static Boolean findKeyInsideBody(String body, String key, String value) {
        body = body.replaceAll("\"","").toLowerCase();
        key = key + value;
        return body.contains(key.toLowerCase());
    }
}
