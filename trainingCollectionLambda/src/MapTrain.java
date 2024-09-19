import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class MapTrain {
    public static void train() {
        Map<Integer, Customer> map = new HashMap<>();
        var customer1 = new Customer("ali", "ali@gmail.com", 1);
        var customer2 = new Customer("amir", "amir@gmail.com", 2);
        var customer3 = new Customer("dani", "dani@gmail.com", 3);
        map.put(1, customer1);
        map.put(2, customer2);
        map.put(3, customer3);
        System.out.println(map);
        System.out.println(map.get(1));
        System.out.println(map.getOrDefault(5, customer1));
        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue(customer1));
        map.replace(3, customer1);
        map.replace(5, customer1);
        System.out.println(map);
        for(int key: map.keySet())
            System.out.println(key);
        for (Customer c: map.values())
            System.out.println(c);
        for(var s: map.entrySet())
            System.out.println(s.getKey() + "=" + s.getValue() + " -> " + s.getClass());
    }
}
