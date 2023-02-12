package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Delayable;

import java.util.*;
import java.util.function.Function;

public class DelayableTransformer extends AbstractTransformer<Delayable,Integer> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("delay")) {
            for (int n = 1; n <= 4; n++) r.add(String.valueOf(n));
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
    public Delayable applyPropertiesToBlock(Delayable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Delayable block, String blockData) {
        return null;
    }
}
