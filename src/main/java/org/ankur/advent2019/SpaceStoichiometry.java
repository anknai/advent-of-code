package org.ankur.advent2019;

import org.ankur.advent.util.FileReader;

import java.util.*;

public class SpaceStoichiometry {

    private Map<String, Chemical> chemicals = null;

    private Map<String, Long> produced = null;

    public long ore(String file) {
        List<String> strings = FileReader.readFile(file);
        chemicals = new HashMap<>();
        outputs(strings);
        inputs(strings);
        initOre(Integer.MAX_VALUE);
        produce("FUEL", 1L);
        return Integer.MAX_VALUE - produced.get("ORE");
    }

    public long ore2(String file, int limit) {
        List<String> strings = FileReader.readFile(file);
        chemicals = new HashMap<>();
        outputs(strings);
        inputs(strings);
        initOre(1_000_000_000_000L);
        int count = 0;
        while (produced.get("ORE") > limit) {
            produce("FUEL", 1L);
            count++;
            if (count % 1000 == 0) {
                System.out.print(".");
            }
            if (count % 100000 == 0) {
                System.out.println(count);
            }
        }
        return count;
    }

    public long ore3(String file, int limit) {
        List<String> strings = FileReader.readFile(file);
        chemicals = new HashMap<>();
        outputs(strings);
        inputs(strings);
        long target = 1_000_000_000_000L;
        initOre(Long.MAX_VALUE);
        long start = target / limit;
        long works = start;
        long doesnt = start * 2;
        while (true) {
            initOre(Long.MAX_VALUE);
            produce("FUEL", start);
            long remaining = produced.get("ORE");
            long used = Long.MAX_VALUE - remaining;
            System.out.println("took " + used + " to generate " + start + " left " + (target - used) );
            if (used < target) {
                works = start;
                start = (works + doesnt) / 2;
                if (target - used < limit) {
                    break;
                }
            } else {
                doesnt = start;
                start -= (doesnt - works) / 2;
            }
            System.out.println("range " + works + "  " + doesnt);
        }
        return start;
    }

    private void initOre(long limit) {
        produced = new HashMap<>();
        produced.put("ORE", limit);
        for (String s : chemicals.keySet()) {
            produced.put(s, 0L);
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

    private void produce(String formula, Long quantity) {
        //System.out.println("Need " + formula + " " + quantity);
        Long integer = produced.get(formula);
        if (integer >= quantity) {
            integer -= quantity;
            //System.out.println(formula + " already produced remaining " + integer);
            produced.put(formula, integer);
        }
        else {
            long required = (quantity - integer);
            //System.out.println("Producing " + formula + " " + required);
            Chemical chemical = chemicals.get(formula);
            long multiple;
            if (required <= chemical.getQuantity()) {
                multiple = 1;
            } else {
                long mode = required % chemical.getQuantity();
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
