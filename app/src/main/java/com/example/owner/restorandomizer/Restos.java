package com.example.owner.restorandomizer;

/**
 * Created by adria_000 on 1/05/2016.
 */
public class Restos {
    private boolean isChecked;
    private String resto;

    public Restos(String resto, boolean isChecked) {
        this.isChecked = isChecked;
        this.resto = resto;
    }

    public boolean getChecked(){
        return this.isChecked;
    }

    public void setChecked(boolean b){
        this.isChecked=b;
    }

    public String getRestoName(){
        return this.resto;
    }

}
