package db.mappers;

import org.dalesbred.annotation.DalesbredInstantiator;

import java.time.LocalDate;

public class Visitor {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate startVisit;
    private final LocalDate endVisit;
    private final int roomId;
    private final int managerId;

    @DalesbredInstantiator
    public Visitor(int id, String firstName, String lastName, LocalDate startVisit, LocalDate endVisit, int roomId, int managerId) {
        this.id = 9999;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startVisit = startVisit;
        this.endVisit = endVisit;
        this.roomId = roomId;
        this.managerId = managerId;
    }

    public Visitor(String firstName, String lastName, LocalDate startVisit, LocalDate endVisit, int roomId, int managerId) {
        this.id = 9999;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startVisit = startVisit;
        this.endVisit = endVisit;
        this.roomId = roomId;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getStartVisit() {
        return startVisit;
    }

    public LocalDate getEndVisit() {
        return endVisit;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getManagerId() {
        return managerId;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", startVisit=" + startVisit +
                ", endVisit=" + endVisit +
                ", roomId=" + roomId +
                ", managerId=" + managerId +
                '}';
    }
}
