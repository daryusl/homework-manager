package ui;


import java.awt.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;


public class MainFrame extends JFrame {
    private HomeworkPanel homeworkPanel;
    private ToDoList todo;
    private TaskPanel taskPanel;

    // learned from cave of programming youtube video
    public MainFrame(String title, ToDoList todo) throws IOException {
        super(title);
        this.todo = todo;
        setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();

        taskPanel = new TaskPanel(todo.tasks);
        homeworkPanel = new HomeworkPanel(todo, taskPanel);


        Container c = getContentPane();

        c.add(homeworkPanel, BorderLayout.WEST);
        c.add(taskPanel, BorderLayout.CENTER);

    }
}
