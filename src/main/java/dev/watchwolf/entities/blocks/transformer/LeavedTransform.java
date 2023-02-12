package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Leaved;

import java.util.*;
import java.util.function.Function;

public class LeavedTransform extends AbstractTransformer<Leaved,Leaved.Leaves> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("leaves")) {
            r.add("none");
            r.add("small");
            r.add("large");
        }

        return r;
    }

    @Override
    public Collection<Leaved.Leaves> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Leaved.Leaves> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Leaved applyPropertiesToBlock(Leaved base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Leaved block, String blockData) {
        return null;
    }
}
