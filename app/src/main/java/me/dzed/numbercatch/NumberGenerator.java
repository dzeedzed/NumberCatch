package me.dzed.numbercatch;

/**
 * Created by dzed on 26/09/16.
 */
public class NumberGenerator {

    public boolean addSub = false;
    public boolean mulDiv = false;
    public boolean decimal = false;
    public boolean date = false;
    public boolean ordinal = false;
    public boolean telephone = false;

    public NumberGenerator(boolean addSub, boolean mulDiv, boolean decimal,
                           boolean date, boolean ordinal, boolean telephone) {
        this.addSub = addSub;
        this.mulDiv = mulDiv;
        this.decimal = decimal;
        this.date = date;
        this.ordinal = ordinal;
        this.telephone = telephone;
    }

}
