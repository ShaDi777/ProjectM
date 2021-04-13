package ru.myitschool.projectsamsung.squares;

public class Square {
    private int x0;
    private int y0;
    private int size;
    private int id;

    public Square(int x0, int y0, int size, int id) {
        this.x0 = x0;
        this.y0 = y0;
        this.size = size;
        this.id = id;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
