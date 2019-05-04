package model;

import java.sql.Date;
import java.util.Calendar;

public abstract class Task{
    public String name;
    public Boolean status; // true for uncompleted, false for completed
    public Date dueDate;

    public Task() {
        this.status = true;
    }

    // EFFECTS: gets the current year
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    // MODIFIES: this
    // EFFECTS: sets the name for the task created by the user
    public abstract void setName(String name);

    // EFFECTS: returns the name of the task
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: changes the status of the completion of the task object
    //          true for uncompleted, false for completed
    public void setStatus(Boolean status) {
        this.status = status;
    }

    // EFFECTS: returns the status of the task
    public Boolean getStatus() {return status;}

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Task task = (Task) o;
//
//        return name.equals(task.name);
//    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}