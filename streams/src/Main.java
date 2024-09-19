import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("f", 20, Genre.ACTION));
        movies.add(new Movie("c", 50, Genre.THILlER));
        movies.add(new Movie("a", 30, Genre.ACTION));
        movies.add(new Movie("b", 5, Genre.THILlER));

        // calculate number of movies that like more than 10

            // Imperative programing
        int count = 0;
        for(var movie: movies)
            if (movie.getLikes() > 10)
                count++;

            // Declarative programming
        long count1 = movies.stream()
                .filter(m -> m.getLikes() > 10)
                .count();

        //----------------------------
        // create stream

        System.out.println("-------------");
        Collection<Integer> collection = List.of(5, 6, 7);
        collection.forEach(System.out::println);

        System.out.println("-------------");
        int[] array = {8, 9, 10, 11};
        Arrays.stream(array).forEach(System.out::println);

        System.out.println("-------------");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        stream.forEach(System.out::println);

        System.out.println("--------------");
        Stream<Double> infinitStream = Stream.generate(
                Math::random
        );
        infinitStream.limit(10).forEach(System.out::println);

        System.out.println("---------------");
        Stream<Integer> iterable = Stream.iterate(1, n -> n+1);
        iterable.limit(10).forEach(System.out::println);

        //-----------------------------------
        // mapping
        System.out.println("----------------");
        Collection<String> moviesName = new ArrayList<>();
        movies.stream()
                .map(Movie::getName)
                .forEach(moviesName::add);
        System.out.println(moviesName);
        // flatMap
        Stream<List<Integer>> s = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));
        s.forEach(list -> System.out.println(list));

        Stream<List<Integer>> s1 = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));
        s1.flatMap(list -> list.stream())
                .forEach(n -> System.out.println(n));

        //----------------------
        // filtering
        Predicate<Movie> popular = movie -> movie.getLikes() > 10;
        movies.stream().filter(popular)
                .forEach(m -> System.out.println(m.getName()));

        //-----------------------
        // slicing;
        System.out.println(movies);
        movies.stream()
                .filter(x -> true)
                .peek(x -> System.out.println("movie: " + x.getLikes()))
                .distinct()
                .peek(x -> System.out.println("tw: " + x.getLikes()))
                .forEach(System.out::println);

        char a = '\u0041';
        System.out.println(a);
        Character b  = '\u0041';
        System.out.println(b);

        int c = 10 / 4;

        Super s13 = new Drieved();
        s13.show();

        // sorting
        System.out.println("Default sort with Comparable -> likes");
        movies.stream().sorted()
                .forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("Custom sort with Comparator -> reversed name");
        movies.stream().sorted(
                        Comparator.comparing(Movie::getName).reversed()
                )
                .forEach(System.out::println);
        System.out.println("------------movies------------");
        System.out.println(movies);
        System.out.println("------------default sort------------");
        Collections.sort(movies);
        System.out.println(movies);
        System.out.println("------------custom sort------------");
        Collections.sort(movies, Comparator.comparing(Movie::getName));
        System.out.println(movies);

        System.out.println("------------------");
        System.out.println(movies.stream()
                .anyMatch(m -> m.getLikes() > 30));
        System.out.println(movies.stream()
                .allMatch(m -> m.getLikes() > 30));
        System.out.println(movies.stream()
                .noneMatch(m -> m.getLikes() > 30));
        System.out.println(
                movies.stream()
                        .min(Comparator.comparing(Movie::getLikes))
                        .get()
        );
        System.out.println(
                movies.stream()
                        .mapToDouble(
                                Movie::getLikes
                        ).reduce(
                                Double::sum
                        ).orElse(0)
        );

        System.out.println("------------------------");
        var collector = movies.stream()
                .filter(m -> m.getLikes() > 25)
                .collect(Collectors.summarizingDouble(Movie::getLikes));
        System.out.println(collector.getMax());
        System.out.println(collector.getMin());
        System.out.println(collector.getSum());
        System.out.println(collector.getAverage());
        System.out.println(collector.getCount());

        System.out.println("-----------------");
        var collect = movies.stream()
                .filter(m -> m.getLikes() < 25)
                .peek(System.out::println)
                .collect(Collectors.summingDouble(Movie::getLikes));
        System.out.println("summing: " + collect);
        System.out.println("-----------------");
        var joining = movies.stream()
                .filter(m -> m.getLikes() > 25)
                .peek(System.out::println)
                .map(Movie::getName)
                .collect(Collectors.joining(", "));
        System.out.println("joining: " + joining);
        System.out.println("------------------");
        var grouping = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.toSet()));
        System.out.println("Actions: " + grouping.get(Genre.ACTION));
        System.out.println("Comedy: " + grouping.get(Genre.COMEDY));
        System.out.println("Thiller: " + grouping.get(Genre.THILlER));

        System.out.println("-------------------");
        var partition = movies.stream()
                .collect(Collectors.partitioningBy(m -> m.getLikes() >= 20));
        System.out.println("likes >= 20:" + partition.get(true));
        System.out.println("likes < 20:" + partition.get(false));



    }
}