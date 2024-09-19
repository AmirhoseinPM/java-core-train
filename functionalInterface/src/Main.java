import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer() {
            @Override
            public void print(String message) {
                System.out.println("printer: " + message);
            }
        };
        Printer printer2 = message -> System.out.println("printer2: " + message);
        printer.print("3");
        printer.print("6");


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(12);
        list.add(10);
        list.sort((a, b) -> {
            if (a < b)
                return +1;
            else if (a == b)
                return 0;
            else
                return -1;
        });

        Consumer<Integer> consumer = x -> System.out.println("first: " + x);
        Consumer<Integer> consumer1 = x -> System.out.println("Then: " + x);
        list.forEach(consumer.andThen(consumer1));

        Predicate<Integer> predicate1 = x -> x>0;
        Predicate<Integer> predicate2 = x -> x<10;
        Predicate<Integer> andPredicate = predicate1.and(predicate2);
        Predicate<Integer> orPredicate = predicate1.or(predicate2);
        Predicate<Integer> notOrPredicate = orPredicate.negate();
        System.out.println(andPredicate.test(3));
        System.out.println(orPredicate.test(12));
        System.out.println(notOrPredicate.test(-12));

        Function<Integer, String> function = (a) -> "then: " + a.toString();
        BinaryOperator<Integer> binaryOperator = (a, b) -> a * b;
        BinaryOperator<Integer> binaryOperator1 = (a, b) -> a + b;
        System.out.println(binaryOperator.andThen(function).apply(2, 3));

        UnaryOperator<Integer> unaryOperation = a -> a * 2;
        System.out.println(binaryOperator1.andThen(unaryOperation).apply(5, 1));

        Supplier<String> supplier = () -> "This is a supplier";
        System.out.println(supplier.get());
    }
}