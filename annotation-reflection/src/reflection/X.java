package reflection;

import annotation.Validate;

public class X {

    @Validate(min = 30, max = 40)
    private short age;

    @Validate(min=0, max=100)
    private short rate;

    public String name;

    public X(short age, short rate) {
        this.age = age;
        this.rate = rate;
    }

    public void m1() {};
    private void m2() {
        System.out.println("private method invokes");
    };
    protected void m3() {};
    void m4() {};
}
