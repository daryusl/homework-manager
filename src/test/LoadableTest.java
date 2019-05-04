package test;

import model.RegularTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadableTest {

    ArrayList<Task> tasks = new ArrayList<>();
    Task task1 = new RegularTask();
    Task task2 = new RegularTask();
    Task task3 = new RegularTask();
    ArrayList<String> tasksName = new ArrayList<>();


    public void save() throws IOException {
        PrintWriter writer = new PrintWriter("loadTest.txt","UTF-8");
        int index = 0;

        while (index < tasks.size()) {
            Task t = tasks.get(index);
            writer.print(t.getName() + ", ");
            writer.println(t.getStatus());
            index += 1;
        }
        writer.close();
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void addObject(Task newTask) {
        tasks.add(newTask);
        tasksName.add(newTask.getName());
    }


    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("loadTest.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Task newTask = new RegularTask();
            newTask.setName(partsOfLine.get(0));
            newTask.setStatus(Boolean.valueOf(partsOfLine.get(1)));
            addObject(newTask);
        }
    }

    @BeforeEach
    public void setUp() {
        task1.setName("test1");
        task1.setStatus(true);
        tasks.add(task1);
        task2.setName("test2");
        task2.setStatus(true);
        tasks.add(task2);
        task3.setName("test3");
        task3.setStatus(true);
        tasks.add(task3);
    }

    @Test
    public void loadTest() throws IOException {
        assertEquals(tasks.contains(task1), true);
        assertEquals(tasks.contains(task2), true);
        assertEquals(tasks.contains(task3), true);
        assertEquals(tasks.size(), 3);

        save();
        load();

        assertEquals(tasks.contains(task1), true);
        assertEquals(tasks.contains(task2), true);
        assertEquals(tasks.contains(task3), true);
        assertEquals(tasks.size(), 6);

        load();

        assertEquals(tasks.contains(task1), true);
        assertEquals(tasks.contains(task2), true);
        assertEquals(tasks.contains(task3), true);
        assertEquals(tasks.size(), 9);

    }
}
