import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        System.out.println("current nodes: " + linkedList);
        System.out.println("IsEmpty? " + linkedList.isEmpty());
        System.out.println("contains 3: " + linkedList.contains(3));
        System.out.println("contains 5: " + linkedList.contains(5));
        System.out.println("size: " + linkedList.size());
        System.out.println("remove 2: " + linkedList.remove(2));
        System.out.println("size: " + linkedList.size());
        linkedList.add(2);
        System.out.println("add 2: " + linkedList.add(2));
        linkedList.addFirst(5);
        System.out.println("add first 5: " + linkedList);
        System.out.println("Iterate over linked list");
        for (Object num: linkedList)
            System.out.println(num);
        System.out.println("value at index 3: " + linkedList.get(3));
        System.out.println("to array: " + Arrays.toString(linkedList.toArray()));

    }
}