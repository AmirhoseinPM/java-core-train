public class Main {
    public static void main(String[] args) {
        var textBox = new TextBox(
                "text box that extends UIControl class \nwith it's features");
        System.out.println(textBox.isEnabled());
        textBox.disable();
        System.out.println(textBox.isEnabled());
        System.out.println(textBox.toString());

        System.out.println("----------------");
        show(textBox); // every subClass have superClass members

        System.out.println("-----------------");
        var point1 = new Point(1, 2);
        var point2 = new Point(1, 2);

        System.out.println(point1 == point2);
        System.out.println(point1.equals(point2));

        System.out.println("-----------------");
        // use base method in super and
        // override it on subClasses
        // for multi form of objects
        // this is polymorphism
        UIControl[] elems = { new CheckBox(), new TextBox("text")};
        for (UIControl element: elems)
            element.render();
    }

    public static void show(UIControl control) {
        // control.setText("..") not allowed, for this must cast to TextBox SubClass
        if (control instanceof TextBox)
            ((TextBox) control).setText("cast to subClass");
        System.out.println(control);
    }
}