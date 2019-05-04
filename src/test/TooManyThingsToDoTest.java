package test;

import model.RegularTask;
import model.Task;
import model.UrgentTask;
import model.exceptions.TooManyThingsToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class TooManyThingsToDoTest {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Task testTask;
    Task priorityTask = new UrgentTask();


    private void addObject(Task newTask) throws TooManyThingsToDo {
        if (tasks.size() >= 5) {
            throw new TooManyThingsToDo();
        } else {
            tasks.add(newTask);
//            tasksName.add(newTask.getName());
        }
    }

    public TooManyThingsToDoTest() throws IOException {
    }

    @BeforeEach
    public void runBefore() {
        testTask = new RegularTask();
    }

    @Test
    public void testEnoughSpaceForTask() {
        try {
            addObject(testTask);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            fail("Caught Exception");
        }
    }

    @Test
    public void testNotEnoughSpaceForTask() {
        try {
            addObject(testTask);
            addObject(testTask);
            addObject(testTask);
            addObject(testTask);
            addObject(testTask);
            addObject(testTask);
            fail("Failed to throw exception.");
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            // do nothing
        }
    }

    @Test
    public void testEnoughSpacePriority() {
        try {
            addObject(priorityTask);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            fail("Should not have thrown exception.");
        }
    }

    @Test
    public void testNotEnoughSpacePriority() {
        try {
            addObject(priorityTask);
            addObject(priorityTask);
            addObject(priorityTask);
            addObject(priorityTask);
            addObject(priorityTask);
            addObject(priorityTask);
            fail("Should have thrown exception.");
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            //do nothing
        }
    }

    @Test
    public void testEnoughSpaceBothTask() {
        try {
            addObject(testTask);
            addObject(priorityTask);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            fail("Should not have thrown. ");
        }
    }

    @Test
    public void testNotEnoughSpaceBothTask() {
        try {
            addObject(testTask);
            addObject(testTask);
            addObject(testTask);
            addObject(priorityTask);
            addObject(priorityTask);
            addObject(priorityTask);
            fail("Should have thrown exception.");
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            //do nothing
        }
    }
}


