package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Openable;

import java.util.*;
import java.util.function.Function;

public class OpenableTransformer extends AbstractTransformer<Openable,Boolean> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("open")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Openable applyPropertiesToBlock(Openable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Openable block, String blockData) {
        return null;
    }
}
