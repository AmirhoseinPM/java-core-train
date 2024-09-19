public class Movie implements Comparable<Movie>{
    private String name;
    private int likes;
    private Genre genre;

    public Movie(String name, int likes, Genre genre) {
        this.name = name;
        this.likes = likes;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
    public int getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", likes=" + likes +
                ", genre=" + genre +
                '}';
    }

    @Override
    public int compareTo(Movie o) {
        return ((Integer) this.getLikes())
                .compareTo(o.getLikes());
    }
}
