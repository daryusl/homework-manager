package ui;

import model.RegularTask;
import model.Task;
import model.exceptions.TooManyThingsToDo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class HomeworkPanel extends JPanel {
    private Icon tooManyImage;
    private ToDoList todo;
    private TaskPanel taskPanel;

    Icon bookGif = new ImageIcon("book.gif");
    JLabel bookLabel = new JLabel(bookGif);

    // citing cave of programming, helped me to learn
    public HomeworkPanel(ToDoList todo, TaskPanel taskPanel) {
        this.todo = todo;

        Dimension size = getPreferredSize();

        size.width = 300;
        setPreferredSize(size);
        tooManyImage = new ImageIcon("trump.png");

        setBorder(BorderFactory.createTitledBorder("Homework Manager: ")); // learned from cave from programming

        JLabel nameLabel = new JLabel("Add Task: ");
        JLabel removeNameLabel = new JLabel("Remove Task: ");

        JTextField nameField = new JTextField(10);
        JTextField removeNameField = new JTextField(10);

        JButton addBtn = new JButton("Add");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task newTask = new RegularTask();
                String name = nameField.getText();
                try {
                    newTask.setName(name);
                    todo.addObject(newTask);
                    playSound("boing_x.wav");
                    taskPanel.render(todo.tasks);

                } catch (TooManyThingsToDo tooManyThingsToDo) {
                    JFrame frame = new JFrame();
                    JOptionPane.showConfirmDialog(frame, "Too many tasks!\n" + "Will you work hard to remove some tasks?", "Error!",
                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        JButton removeBtn = new JButton("Remove");

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = removeNameField.getText();
                todo.removeObject(name);
                System.out.println(todo.tasks);

                playSound("kick.wav");

                taskPanel.render(todo.tasks);
            }
        });

        JButton removeAllBtn = new JButton("Remove All");

        removeAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                todo.tasks.clear();

                playSound("bye.wav");

                taskPanel.render(todo.tasks);
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //// First Column

        gc.anchor = GridBagConstraints.LINE_END; // learned from cave of programming
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(removeNameLabel, gc);

        //// Second column
        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 0;
        add(nameField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(removeNameField, gc);

        // Final row
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        gc.gridx = 1;
        gc.gridy = 2;
        add(addBtn, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(removeBtn, gc);

        gc.weighty = 10;
        gc.gridx = 1;
        gc.gridy = 4;
        add(removeAllBtn, gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 1;
        gc.gridy = 5;
        add(bookLabel);


    }

    //citing code from this stackoverflow post! https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(HomeworkPanel.class.getResourceAsStream(url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}