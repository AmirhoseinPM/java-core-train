public class Customer {
    private String name = "";
    private String email = "";

    private Integer uid;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUid() {
        return uid;
    }

    public Customer(String name, String email, Integer uid) {
        this.email = email;
        this.name = name;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return name + "-" + email;
    }


}
