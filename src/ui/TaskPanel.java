package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskPanel extends JPanel {

    private JPanel panel1;

    private final int JLABEL_SIZE = 5;

    JLabel[] jLabels = new JLabel[JLABEL_SIZE];

    public TaskPanel(ArrayList<Task> list) {
        setLayout(new GridBagLayout());
        init();
        render(list);

    }

    public void clearLabels() {
        for (int i = 0; i < JLABEL_SIZE; i++) {
            jLabels[i].setText("");
        }
    }

    public void init() {

        for (int i = 0; i < JLABEL_SIZE; i++) {
            jLabels[i] = new JLabel("");
        }

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LINE_START;
        gc.weighty = 5;

        gc.gridy = 0;
        add(jLabels[0], gc);

        gc.gridy = 1;
        add(jLabels[1], gc);

        gc.gridy = 2;
        add(jLabels[2], gc);

        gc.gridy = 3;
        add(jLabels[3], gc);

        gc.gridy = 4;
        add(jLabels[4], gc);
    }

    public void render(ArrayList<Task> list) {

        int listSize = list.size();

        //gc.gridx = 0;

        //String task = "123";
        //JLabel taskLabel = new JLabel(task);
        //gc.gridy = 0;
        //add(taskLabel, gc);

        clearLabels();


        for (int i = 0; i < listSize; i++) {
            String task = String.valueOf(i + 1) + "." + " " + list.get(i).getName();
            JLabel taskLabel = new JLabel(task);

            jLabels[i].setText(task);
            //gc.gridy = i;
            //add(taskLabel, gc);
        }

    }

}