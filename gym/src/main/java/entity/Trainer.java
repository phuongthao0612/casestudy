package entity;

public class Trainer extends Person {
    private String specialization;

    public Trainer(int id, String name, String phone, String specialization) {
        super(id, name, phone);
        this.specialization = specialization;
    }
    public Trainer(String name, String phone, String specialization) {
        super(name, phone);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



}
