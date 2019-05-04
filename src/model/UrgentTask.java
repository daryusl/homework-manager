package model;

import model.exceptions.TooManyThingsToDo;
import ui.ToDoList;

import java.util.Collection;
import java.util.HashSet;

public class UrgentTask extends Task {
    public Boolean urgent;
    private Collection<UrgentTask> urgentTasks;


    public UrgentTask() {
        super();
        setUrgent(true);
//        setName(name);
    }

    private UrgentTask checkUrgentTask(String completed) {
        for (UrgentTask t : urgentTasks) {
            if (t.getName().equals(completed)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.urgent = true;
    }

    public void setUrgent(Boolean urgent){
        this.urgent = urgent;
    }

    public Boolean getUrgent() {
        return this.urgent;
    }

}