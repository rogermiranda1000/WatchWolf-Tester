package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Playable;

import java.util.*;
import java.util.function.Function;

public class PlayableTransformer extends AbstractTransformer<Playable,Integer> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "note":
                for (int n = 0; n <= 24; n++) r.add(String.valueOf(n));
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
    public Playable applyPropertiesToBlock(Playable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Playable block, String blockData) {
        return null;
    }
}
