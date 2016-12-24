package com.hack.week10;

/**
 * Created by mestanov on 20.12.16.
 */
public enum Type {
    STRING("String"),
    INT("Int"),
    LONG("Long"),
    BOOL("Bool");


    private String val;

    Type(String val){
        this.val = val;
    }

    public static Type make(String s){
        if(s.equals(STRING.val)){
            return STRING;
        } else if(s.equals(INT.val)){
            return INT;
        } else if(s.equals(LONG.val)){
            return LONG;
        } else {
            return BOOL;
        }
    }

    @Override
    public String toString() {
        return val;
    }
}
