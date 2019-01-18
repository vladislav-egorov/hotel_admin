package db.mappers;

import org.dalesbred.annotation.DalesbredInstantiator;

public class Manager {
    private int managerId;
    private String firstName;
    private String lastName;
    private int involvedClients;
    private int grade;

    @DalesbredInstantiator
    public Manager(int managerId, String firstName, String lastName, int involvedClients, int grade) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.involvedClients = involvedClients;
        this.grade = grade;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getInvolvedClients() {
        return involvedClients;
    }

    public int getGrade() {
        return grade;
    }
}
