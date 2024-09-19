import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var items = new MyArray(2);
        items.insert(10);
        items.insert(20);
        items.insert(30);
        items.insert(40);
        items.delete(10);
        items.insert(10);
        items.removeAt(0);
        System.out.println(items.indexOf(10));
        System.out.println(items.indexOf(100));
        items.print();

        System.out.println("--------------");
        var points = new ArrayList<Integer>();
        points.add(1);
        points.add(2);
        points.add(3);
        System.out.println(points.toString());
        points.remove(1);
        System.out.println(points.size());

    }
}