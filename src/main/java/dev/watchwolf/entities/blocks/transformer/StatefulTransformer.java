package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Stateful;

import java.util.*;
import java.util.function.Function;

public class StatefulTransformer extends AbstractTransformer<Stateful,Stateful.Mode> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("mode")) {
            if (mat.equals("COMPARATOR")) {
                r.add("compare");
                r.add("subtract");
            } else { // STRUCTURE BLOCK
                r.add("load");
                r.add("corner");
                r.add("save");
            }
        }

        return r;
    }

    @Override
    public Collection<Stateful.Mode> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Stateful.Mode> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Stateful applyPropertiesToBlock(Stateful base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Stateful block, String blockData) {
        return null;
    }
}
