package org.ankur.advent2019;

import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString(exclude = "chemicals")
public class Chemical {

    private long quantity;

    private String formula;

    private Set<Chemical> chemicalSet;

    public Chemical(int quantity, String formula) {
        this.quantity = quantity;
        this.formula = formula;
        chemicalSet = new HashSet<>();
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Set<Chemical> getChemicalSet() {
        return chemicalSet;
    }

    public void addChemical(Chemical chemical) {
        this.chemicalSet.add(chemical);
    }
}
