package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Ageable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AgeableTransformer extends AbstractTransformer<Ageable,Integer> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();
        switch (argument) {
            case "age":
                switch (mat) {
                    case "BEETROOTS":
                    case "FROSTED_ICE":
                    case "NETHER_WART":
                    case "SWEET_BERRY_BUSH":
                        for (int n = 0; n <= 3; n++) r.add(String.valueOf(n));
                        break;
                    case "CHORUS_FLOWER":
                        for (int n = 0; n <= 5; n++) r.add(String.valueOf(n));
                        break;
                    case "BAMBOO":
                        for (int n = 0; n <= 1; n++) r.add(String.valueOf(n));
                        break;
                    case "CARROTS":
                    case "WHEAT":
                    case "PUMPKIN_STEM":
                    case "POTATOES":
                    case "MELON_STEM":
                        for (int n = 0; n <= 7; n++) r.add(String.valueOf(n));
                        break;
                    case "COCOA":
                        for (int n = 0; n <= 2; n++) r.add(String.valueOf(n));
                        break;
                }
                break;
            case "berries":
                r.add("true");
                r.add("false");
                break;
            case "honey-level":
                for (int n = 0; n <= 5; n++) r.add(String.valueOf(n));
                break;
        }
        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();
        if (options.containsKey("age")) r.addAll(options.get("age").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("berries")) {
            r.add(0);
            r.add(1);
        }
        if (options.containsKey("honey-level")) r.addAll(options.get("age").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        this.getSocketData(socketData);
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Ageable applyPropertiesToBlock(Ageable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Ageable block, String blockData) {
        return null;
    }
}
