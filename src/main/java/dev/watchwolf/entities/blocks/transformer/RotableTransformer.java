package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Rotable;

import java.util.*;
import java.util.function.Function;

public class RotableTransformer extends AbstractTransformer<Rotable,Rotable.Rotation> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "rotation":
                for (int n = 0; n <= 15; n++) r.add(String.valueOf(n));
                break;
        }

        return r;
    }

    @Override
    public Collection<Rotable.Rotation> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Rotable.Rotation> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Rotable applyPropertiesToBlock(Rotable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Rotable block, String blockData) {
        return null;
    }
}
