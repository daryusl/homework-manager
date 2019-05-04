package model;

import java.sql.Date;
import java.util.Calendar;

public class RegularTask extends Task {

    // EFFECTS: Creates the object task with initial status of completion as true for uncompleted
    public RegularTask() {
        super();
//        this.setName(name);
    }

    // MODIFIES: this
    // EFFECTS: sets the name for the task created by the user
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}


