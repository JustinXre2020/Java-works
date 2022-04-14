import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


class Occurrence {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> occu = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] words = sc.nextLine().split(" ");
                for (String s : words) {
                    if (occu.containsKey(s)) {
                        occu.put(s, occu.get(s) + 1);
                    } else {
                        occu.put(s, 1);
                    }
                }
            }
            sc.close();
        }
        if (args.length >= 1 && !occu.isEmpty()) {
            LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
            occu.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            int max = Collections.max(occu.values());
            for (Entry<String, Integer> mapElement : reverseSortedMap.entrySet()) {
                String key = (String) mapElement.getKey();
                int value = (int) mapElement.getValue();
                if (value == max) {
                    System.out.println(key + ", " + value);
                }
            }
        }
    }
}