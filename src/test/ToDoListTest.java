package test;

import model.RegularTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoListTest {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Task testTask;

    @BeforeEach
    public void runBefore() {
        testTask = new RegularTask();
    }

    @Test
    public void testAddTaskWhenEmpty() {
        assertEquals(tasks.size(), 0);
        assertFalse(tasks.contains(testTask));
        tasks.add(testTask);
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void testAddTaskAlreadyThere() {
        Task otherTask = new RegularTask();
        assertEquals(tasks.size(), 0);
        tasks.add(otherTask);
        assertTrue(tasks.contains(otherTask));
        tasks.add(testTask);
        assertEquals(tasks.size(), 2);
        assertTrue(tasks.contains(testTask));
    }

    @Test
    public void testRemoveTasksWhenEmpty() {
        assertEquals(tasks.size(), 0);
        tasks.remove(testTask);
        assertEquals("That item did not exist in your task list.", "That item did not exist in your task list.");
    }

    @Test
    public void testRemoveTasksWithOne() {
        assertEquals(tasks.size(), 0);
        testTask.setName("test");
        tasks.add(testTask);
        assertEquals(tasks.size(), 1);
        assertTrue(testTask.status);
        tasks.remove(testTask);
        assertEquals(tasks.size(), 0);
        assertTrue(testTask.status);
    }

    @Test
    public void testRemoveTasksWithTwo() {
        assertEquals(tasks.size(), 0);
        Task otherTask = new RegularTask();
        tasks.add(otherTask);
        tasks.add(testTask);
        assertEquals(tasks.size(), 2);
        tasks.remove(testTask);
        assertEquals(tasks.size(), 1);
        assertFalse(tasks.contains(testTask));
        assertTrue(tasks.contains(otherTask));
    }

    @Test
    public void testRemoveTasksAll() {
        Task otherTask = new RegularTask();
        tasks.add(otherTask);
        tasks.add(testTask);
        tasks.remove(otherTask);
        tasks.remove(testTask);
        assertEquals(tasks.size(), 0);
        assertFalse(tasks.contains(testTask));
        assertFalse(tasks.contains(otherTask));
    }

    @Test
    public void testChangeStatusToFalse() {
        assertTrue(testTask.status);
        testTask.setStatus(false);
        assertFalse(testTask.status);
    }

    @Test
    public void testChangeStatusToTrue() {
        assertTrue(testTask.status);
        testTask.setStatus(false);
        assertFalse(testTask.status);
        testTask.setStatus(true);
        assertTrue(testTask.status);
    }

    @Test
    public void testChangeName() {
        assertEquals(testTask.name, null);
        testTask.setName("test");
        assertEquals(testTask.name, "test");
    }

}
