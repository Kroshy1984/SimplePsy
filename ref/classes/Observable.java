package ru.sfedu.simplepsy.classes;
public interface Observable {
    void setObserver(Observer observer);
    void notifyObservers();
}
