package ui;

import model.*;
import model.exceptions.TooManyThingsToDo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ToDoList extends Subject implements Saveable, Loadable {
    public ArrayList<Task> tasks = new ArrayList<>();
    public ArrayList<String> tasksName = new ArrayList<>();
    public ArrayList<Task> completedTasks = new ArrayList<>();
    public ArrayList<String> completedTasksName = new ArrayList<>();
    private Collection<UrgentTask> urgentTasks;
    private Map<TaskType, ArrayList<Task>> TaskMap = new HashMap<>();

    // MODIFIES: this
    // EFFECTS: Constructs the ToDoList that takes user inputs and acts correspondingly
    //          and with empty regularTasks lists
    public ToDoList() throws IOException {
        super();

        User user = new User();
        addObserver(user);

        load();
        urgentTasks = new HashSet<>();
        TaskType Urgent = new TaskType("Urgent");
        TaskType Regular = new TaskType("Regular");
        addTaskType(Urgent);
        addTaskType(Regular);
    }


    public void addTaskType(TaskType type) {
        TaskMap.put(type, new ArrayList<Task>());
    }


//    // REQUIRES: tasksName and completedRegularTasks not empty
//    // EFFECTS: displays list of assignments to do and list of completed assignments
//    public String showFullList() {
//        return ("You need to complete: " + tasksName + " BUT YOU HAVE COMPLETED: " + completedTasks);
//    }

    // MODIFIES: this
    // EFFECTS: takes a new task and adds it to the object regularTasks array and
    //          the name to the tasksName array
    public void addObject(Task newTask) throws TooManyThingsToDo {
        if (tasks.size() >= 5) {
            throw new TooManyThingsToDo();
        } else {
            notifyObserver(newTask);
            tasks.add(newTask);
            tasksName.add(newTask.getName());
        }
    }


    // MODIFIES: this
    // EFFECTS: check whether given name of completed task (string) corresponds to any name of object in the
    //          list of regularTasks
    //          if it does, then it will remove the object from the list and change the status of the completion
    //                  this will also remove the object name from the tasksName array and
    //                  add the object to completedRegularTasks and the object name to completedTasksName
    //          if not, the system will print that the item did not exist in the list
    public void removeObject(String completed) {
        ArrayList<Task> temp = new ArrayList<>(tasks);
        for (Task t : tasks) {
            if (t.getName().equals(completed)) {
                temp.remove(t);
                t.setStatus(false);
                tasksName.remove(completed);
                completedTasks.add(t);
                completedTasksName.add(completed);
            }
        }
        tasks = temp;
    }

    @Override
    public void save() throws IOException {
        PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
        int index = 0;

        while (index < tasks.size()) {
            Task t = tasks.get(index);
            writer.print(t.getName() + ", ");
            writer.println(t.getStatus());
            index += 1;
        }
        writer.close();
    }

    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        try {
            if (!(Files.exists(Paths.get("outputfile.txt")))) {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("The file does not exist.");
        }
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Task newTask = new RegularTask();
            newTask.setName(partsOfLine.get(0));
            newTask.setStatus(Boolean.valueOf(partsOfLine.get(1)));
            try {
                addObject(newTask);
            } catch (TooManyThingsToDo tooManyThingsToDo) {
                System.out.println("Addition was unsuccessful because the task list is too full.");
            }
        }
    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }
}