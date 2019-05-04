package model;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }


    public void notifyObserver(Task t)
    {
        for(Observer o : observers)
        {
            o.update(t);
        }
    }


    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

}
