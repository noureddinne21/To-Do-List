package com.example.todolist;

public class Listtodo {

    private String todo;
    private int id;

    public Listtodo(){

    }
    public Listtodo(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
