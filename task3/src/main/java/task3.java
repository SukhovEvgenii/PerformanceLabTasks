import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class task3
{
    public static void main( String[] args )
    {
        if (args.length !=3) {
            System.out.println("The program must have 3 arguments");
            return;
        }
        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];
        Gson gson = new Gson();
        try {
            FileReader valuesReader = new FileReader(valuesPath);
            Type valuesType = new TypeToken<Map<String, JsonArray>>() {}.getType();
            Map<String, JsonArray> valuesData = gson.fromJson(valuesReader, valuesType);
            valuesReader.close();
            Map<Integer, String> valuesMap = new HashMap<>();
            for (JsonElement valueElement : valuesData.get("values")) {
                JsonObject valueObject = valueElement.getAsJsonObject();
                int id = valueObject.get("id").getAsInt();
                String value = valueObject.get("value").getAsString();
                valuesMap.put(id, value);
            }
            FileReader testsReader = new FileReader(testsPath);
            JsonObject testsData = gson.fromJson(testsReader, JsonObject.class);
            testsReader.close();
            fillValues(testsData.getAsJsonArray("tests"), valuesMap);
            Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
            FileWriter reportWriter = new FileWriter(reportPath);
            gsonPretty.toJson(testsData,reportWriter);
            reportWriter.close();
        } catch (Exception e) {
            System.out.println("Parsing error " + e);
        }
    }

    private static void fillValues(JsonArray tests, Map<Integer, String> valuesMap) {
        for (JsonElement testElement : tests) {
            JsonObject testObject = testElement.getAsJsonObject();
            int id = testObject.get("id").getAsInt();
            if (valuesMap.containsKey(id)) {
                testObject.addProperty("value", valuesMap.get(id));
            }
            if (testObject.has("values")) {
                fillValues(testObject.getAsJsonArray("values"), valuesMap);
            }
        }
    }
}