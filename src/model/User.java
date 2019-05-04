package model;

public class User implements Observer {

    @Override
    public void update(Task task) {

        System.out.println(task.name + " was added to the list");
    }
}
