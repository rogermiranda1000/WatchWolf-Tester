package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Groupable;

import java.util.*;
import java.util.function.Function;

public class GroupableTransformer extends AbstractTransformer<Groupable,Integer> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "candles":
            case "eggs":
            case "pickles":
                for (int n = 1; n <= 4; n++) r.add(String.valueOf(n));
                break;
        }

        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Groupable applyPropertiesToBlock(Groupable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Groupable block, String blockData) {
        return null;
    }
}
