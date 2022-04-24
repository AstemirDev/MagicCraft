package org.astemir.magiccraft.utils;

public class Pair<K,V>{

    public K key;
    public V value;

    public static <A,B> Pair init(){
        return new Pair<A,B>();
    }

    public static <A,B> Pair init(Object key,Object value){
        Pair pair = new Pair<A,B>();
        pair.key(key);
        pair.value(value);
        return pair;
    }


    public Pair key(K key){
        this.key = key;
        return this;
    }

    public Pair value(V value){
        this.value = value;
        return this;
    }


}
