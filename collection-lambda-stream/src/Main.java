import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> persons = getPersons();
        sortByCity(persons);
        partitioningByAge(persons);
        groupByCity(persons);
        ageAveragePrint(persons);
    }

    private static void sortByCity(List<Person> persons) {
        persons.sort(Comparator.comparing(a -> a.getAddress().city()));
        persons.forEach(System.out::println);
    }

    private static void ageAveragePrint(List<Person> persons) {
        var sumAge = persons.stream()
                .map(Person::getAge)
                .collect(Collectors.summarizingDouble(a -> (double) a));
        System.out.println("Average age: " + sumAge.getAverage());
    }

    private static void groupByCity(List<Person> persons) {
        var results = persons.stream().collect(
                Collectors.groupingBy(a -> a.getAddress().city())
        );
        System.out.println(results);
    }

    private static void partitioningByAge(List<Person> persons) {
        Map<Boolean, List<Person>> agePartitions = persons.stream()
                .collect(Collectors.partitioningBy(
                        a -> a.getAge() > 18
                ));
        System.out.println("person with age > 18: ");
        agePartitions.get(true)
                .forEach(System.out::println);


        System.out.println("person with age <= 18: ");
        agePartitions.get(false)
                .forEach(System.out::println);
    }

    private static List<Person> getPersons() {
        Address address1 = new Address("tehran", "iran");
        Person person1 = new Person("ali", "bagheri", (short)25, address1);

        Address address2 = new Address("esfahan", "iran");
        Person person2 = new Person("hadi", "davoodi", (short)26, address2);
        Address address3 = new Address("shiraz", "iran");
        Person person3 = new Person("mehdi", "dadras", (short)27, address3);
        Address address6 = new Address("tabriz", "usa");
        Person person6 = new Person("morteza", "afshar", (short)18, address6);
        Address address4 = new Address("kerman", "uae");
        Person person4 = new Person("dara", "mashti", (short)16, address4);

        Address address5 = new Address("kerman", "turkey");
        Person person5 = new Person("gthjo", "nams", (short)8, address5);
        Address address7 = new Address("esfahan", "jvovw");
        Person person7 = new Person("s jkdl", "svju", (short)23, address7);
        Address address8 = new Address("shiraz", "eina");
        Person person8 = new Person("wiUFB", "seugiuq", (short)20, address8);
        Address address9 = new Address("shiraz", "s;v");
        Person person9 = new Person("urbvtdzi", " UBIEWR:Soe", (short)2, address9);
        Address address10 = new Address("esfahan", "viobuwre");
        Person person10 = new Person("skjvsu", "jsvjdn vs", (short)35, address10);
        Address address11 = new Address("tehran", "skjdv ");
        Person person11 = new Person("xkjd v", "SUvhrlo", (short)2, address11);

        return Arrays.asList(
                person1, person2, person3, person5, person7, person8, person4,
                person6, person10, person11, person9);
    }
}