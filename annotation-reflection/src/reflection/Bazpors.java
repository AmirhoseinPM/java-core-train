package reflection;

import annotation.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Bazpors {

    public static void main (String... c) {
        var x = new X((short) 2, (short)2);
        var y = new X((short) 34, (short)-2);
        try {
            System.out.println("Validate x:");
            runValidation(x);
            System.out.println("Validate y:");
            runValidation(y);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void runValidation(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        for (var field: fields) {
            field.setAccessible(true);
            Validate validateAnnotation = field.getAnnotation(Validate.class);
            if (validateAnnotation != null) {
                validation(field, (short)field.get(o), validateAnnotation);
                /*
                Object obj = validateAnnotation;
                Annotation a = validateAnnotation;
                Class ac = a.annotationType().getClass();
                System.out.println("Annotation: " + ac.getName());
                System.out.println("Annotation max argument: " + validateAnnotation.max());
                System.out.println("Annotation min argument: " + validateAnnotation.min());
                System.out.println("field value: " + field.get(o));
                System.out.println("ClassName: " + validateAnnotation.getClass().getName());
                System.out.println("SuperClass: " + validateAnnotation.getClass().getSuperclass().getName());
                */

            }
        }

    }

    private static void validation(Field field, short fieldValue, Validate validateAnnotation) {
        var maxValidate = validateAnnotation.max();
        var minValidate = validateAnnotation.min();
        if (maxValidate < fieldValue ||
                minValidate > fieldValue) {
            System.out.println("----FieldNotValidated: " + field.getName() + " must between " + maxValidate + " and " + minValidate);
        } else
            System.out.println("++++Validated: " + field.getName() + " " + fieldValue);
    }

    public static void printEteraf(Object o) {
        Class c = o.getClass();

        printEteraf(c);

        System.out.println("----------------------work with private method--------------------");
        try {
            Method m2 = c.getDeclaredMethod("m2");
            m2.setAccessible(true);
            m2.invoke(o);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    };
    public static void printEteraf(Class c) {

        System.out.println("Class name: " + c.getName());

        System.out.println("Declared methods: ");
        for (var m: c.getDeclaredMethods())
            System.out.println(m.getName());

        System.out.println("All public methods: ");
        for (Method m: c.getMethods())
            System.out.println(m.getName());
        System.out.println("----------------");
        System.out.println("Declared fields: ");
        try {
            Arrays.stream(c.getDeclaredField("age")
                            .getAnnotations())
                    .map(Annotation::annotationType)
                    .forEach(System.out::println);

            System.out.println(
                    "isAnnotationPresent(Validate.class)? " +
                            c.getDeclaredField("age")
                                    .isAnnotationPresent(Validate.class)
            );

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


        System.out.println("All fields: ");
        Arrays.stream(c.getFields())
                .map(Field::getName)
                .forEach(System.out::println);
    }
}
