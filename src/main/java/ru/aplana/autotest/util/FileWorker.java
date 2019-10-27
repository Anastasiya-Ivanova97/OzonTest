package ru.aplana.autotest.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static ru.aplana.autotest.pages.BasePage.products;

public class FileWorker {

    public static void writeIntoFile(String name) throws IOException {

        File file = new File("src/main/resources/"+name);
        FileWriter writer = new FileWriter(file);
        for(Map.Entry<String, String> entry : products.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            writer.write(key+" = "+value.replaceAll("\\u20BD", "P")+"\n");
        }
        Collection<String> prices1 =  products.values();
        List<String> prices = new ArrayList<>();
        prices.addAll(prices1);
        Collections.sort(prices);

        Set<Map.Entry<String,String>> entrySet=products.entrySet();
        String key = null;
        String desiredObject=prices.get(prices.size()-1);//что хотим найти
        for (Map.Entry<String,String> pair : entrySet) {
            if (desiredObject.equalsIgnoreCase(pair.getValue())) {
                key = pair.getKey();// нашли наше значение и возвращаем  ключ
            }
        }
        writer.write("Товар с наибольшей ценой: "+key+" = "+products.get(key).replaceAll("\\u20BD", "P"));
        writer.flush();
        writer.close();
        products.clear();
    }
}
