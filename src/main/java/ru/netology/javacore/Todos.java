package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private List<String> myTasks;

    public Todos() {
        this.myTasks = new ArrayList<>();
    }

    public void addTask(String task) {
        this.myTasks.add(task);
    }

    public void removeTask(String task) {
        this.myTasks.remove(task);
    }

    public void setAllTasks (List<String> allTasks) {
        this.myTasks = myTasks;
    }

    public String getAllTasks() {
        Collections.sort(this.myTasks);
        StringBuilder sb = new StringBuilder();
        for (String task : this.myTasks) {
            sb.append(task);
            sb.append(" ");
        }
        return sb.toString();
    }

    public List<String> getAllTasksAsList() {
        return this.myTasks;
    }


    @Override
    public String toString() {
        return "Todos { " +
                "all tasks = " + myTasks +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todos)) return false;
        Todos todos = (Todos) o;
        return getAllTasks().equals(todos.getAllTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAllTasks());
    }
}
