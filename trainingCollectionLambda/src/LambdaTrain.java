public class LambdaTrain {
    private static String classField = "-";
    private String instanceField = "+";
    public static void train() {
        print(new ConsolePrinter());
        print(new Printer() {
            @Override
            public void print(String message) {
                System.out.println("print with inner class that implement functional interface");
            }
        });
        print( (String message) -> {
            System.out.println("Lambda expression that implement functional interface");
        });

        String m = "shortest lambda";
        print(message -> System.out.println(classField + m));
        // we can use instanceField in non static methods
    }
    public static void print(Printer p) {
        p.print("print with functional interface that has just one abstract method");
    }
}
