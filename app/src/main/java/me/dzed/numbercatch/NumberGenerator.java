package me.dzed.numbercatch;

import java.util.Random;

/**
 * Created by dzed on 26/09/16.
 */
public class NumberGenerator {

    public Random random = new Random();
    public boolean integer = false;
    public boolean addSub = false;
    public boolean mulDiv = false;
    public boolean decimal = false;
    public boolean date = false;
    public boolean ordinal = false;
    public boolean telephone = false;

    public NumberGenerator() {

    }

    public NumberGenerator(boolean integer, boolean addSub, boolean mulDiv, boolean decimal,
                           boolean date, boolean ordinal, boolean telephone) {
        this.integer = integer;
        this.addSub = addSub;
        this.mulDiv = mulDiv;
        this.decimal = decimal;
        this.date = date;
        this.ordinal = ordinal;
        this.telephone = telephone;
    }

    public int generateInt(int max, int min) {
        int result = min + random.nextInt(max);
        System.out.println("GENERATE INT: " + result);
        return result;
    }

}
