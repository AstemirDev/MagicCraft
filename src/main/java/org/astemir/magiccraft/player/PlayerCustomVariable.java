package org.astemir.magiccraft.player;

public class PlayerCustomVariable<T> {

    private String name;
    private T value;


    public PlayerCustomVariable(T value) {
        this.value = value;
    }

    public void update(long ticks){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
