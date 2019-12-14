package org.ankur.advent2019;

import org.ankur.advent.util.FileReader;

import java.util.*;

public class SpaceStoichiometry {

    private Map<String, Chemical> chemicals = null;

    private Map<String, Integer> produced = null;

    public int ore(String file) {
        List<String> strings = FileReader.readFile(file);
        chemicals = new HashMap<>();
        outputs(strings);
        inputs(strings);
        initOre();
        produce("FUEL", 1);
        return Integer.MAX_VALUE - produced.get("ORE");
    }

    private void initOre() {
        produced = new HashMap<>();
        produced.put("ORE", Integer.MAX_VALUE);
        System.out.println("Produced ORE " + Integer.MAX_VALUE);
        for (String s : chemicals.keySet()) {
            produced.put(s, 0);
        }
    }

    private void inputs(List<String> strings) {
        for (String string : strings) {
            String[] split = string.split("=>");
            String input = split[0].trim();
            String[] inputs = input.split(", ");
            String output = split[1].trim();
            Chemical chemical = split(output);
            Chemical chemical2 = chemicals.get(chemical.getFormula());
            for (String s : inputs) {
                Chemical chemical1 = split(s);
                if (null != chemical2) {
                    //System.out.println("Adding " + chemical1 + " to " + chemical2);
                    chemical2.addChemical(chemical1);
                }
            }
        }
    }

    private void outputs(List<String> strings) {
        for (String string : strings) {
            String[] split = string.split("=>");
            String output = split[1].trim();
            Chemical chemical = split(output);
            chemicals.put(chemical.getFormula(), chemical);
        }
    }

    private Chemical split(String values) {
        String[] outputs = values.split(" ");
        int quantity = Integer.parseInt(outputs[0]);
        String output = outputs[1];
        return new Chemical(quantity, output);
    }

    private void produce(String formula, Integer quantity) {
        System.out.println("Need " + formula + " " + quantity);
        Integer integer = produced.get(formula);
        if (integer >= quantity) {
            integer -= quantity;
            System.out.println(formula + " already produced remaining " + integer);
            produced.put(formula, integer);
        }
        else {
            int required = (quantity - integer);
            System.out.println("Producing " + formula + " " + required);
            Chemical chemical = chemicals.get(formula);
            int multiple;
            if (required <= chemical.getQuantity()) {
                multiple = 1;
            } else {
                int mode = required % chemical.getQuantity();
                multiple = required / chemical.getQuantity();
                if (mode != 0) {
                    multiple++;
                }
            }
            Set<Chemical> inputs = chemical.getChemicalSet();
            for (Chemical input : inputs) {
                produce(input.getFormula(), input.getQuantity() * multiple);
            }
            produced.put(formula, integer + multiple * chemical.getQuantity() -  quantity);
        }
    }
}
