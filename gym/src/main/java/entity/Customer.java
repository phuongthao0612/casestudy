package entity;

public class Customer extends Person {
    private int age;
    private String email;

    public Customer(int id, String name, int age, String phone, String email) {
        super(id, name, phone);
        this.age = age;
        this.email = email;
    }

    public Customer(String name, int age, String phone, String email) {
        super(name, phone);
        this.age = age;
        this.email = email;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer [age=" + age + ", email=" + email + "]";
    }

}
