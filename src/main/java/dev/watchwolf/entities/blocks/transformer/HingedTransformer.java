package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Hinged;

import java.util.*;
import java.util.function.Function;

public class HingedTransformer extends AbstractTransformer<Hinged, Hinged.Hinge> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("hinge")) {
            r.add("left");
            r.add("right");
        }

        return r;
    }

    @Override
    public Collection<Hinged.Hinge> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Hinged.Hinge> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Hinged applyPropertiesToBlock(Hinged base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Hinged block, String blockData) {
        return null;
    }
}
