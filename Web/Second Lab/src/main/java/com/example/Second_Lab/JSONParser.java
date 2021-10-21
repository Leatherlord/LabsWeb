package com.example.Second_Lab;

import java.util.ArrayList;

public class JSONParser {

    public static String toJSON(ArrayList<Lane> collection) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        if (collection.size() != 0) {
            for (int i = 0; i < collection.size(); i++) {
                if (i != collection.size() - 1) {
                    json.append(toJSON(collection.get(i))).append(",\n");
                } else {
                    json.append(toJSON(collection.get(i))).append("\n");
                }
            }
        } else {
            json.append("   {\n" +
                    "       \"result\": \"none\",\n" +
                    "       \"message\": \"\"\n" +
                    "}\n");
        }
        json.append("]");
        return json.toString();
    }

    public static String toJSON(Lane lane) {
        return "    {\n" +
                "       \"x\": \"" + lane.getX() + "\",\n" +
                "       \"y\": \"" + lane.getY() + "\",\n" +
                "       \"r\": \"" + lane.getR() + "\",\n" +
                "       \"result\": \"" + lane.isInArea() + "\",\n" +
                "       \"date\": \"" + lane.getCreationDate() + "\",\n" +
                "       \"state\": \"" + lane.isError() + "\",\n" +
                "       \"message\": \"" + lane.getMessage() + "\"\n" +
                "   }";
    }
}
