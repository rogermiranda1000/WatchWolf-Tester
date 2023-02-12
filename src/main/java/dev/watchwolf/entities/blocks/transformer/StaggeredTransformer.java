package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Staggered;

import java.util.*;
import java.util.function.Function;

public class StaggeredTransformer extends AbstractTransformer<Staggered,Integer> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "bites":
                for (int n = 0; n <= 6; n++) r.add(String.valueOf(n));
                break;
            case "charges":
                for (int n = 0; n <= 4; n++) r.add(String.valueOf(n));
                break;
            case "layers":
                for (int n = 1; n <= 8; n++) r.add(String.valueOf(n));
                break;
            case "level":
                switch (mat) {
                    case "COMPOSTER":
                        for (int n = 0; n <= 8; n++) r.add(String.valueOf(n));
                        break;
                    case "LAVA":
                    case "WATER":
                        for (int n = 0; n <= 7; n++) r.add(String.valueOf(n));
                        break;
                    case "CAULDRON":
                    case "POWDER_SNOW_CAULDRON":
                    case "WATER_CAULDRON":
                        for (int n = 1; n <= 3; n++) r.add(String.valueOf(n));
                        break;
                }
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
    public Staggered applyPropertiesToBlock(Staggered base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Staggered block, String blockData) {
        return null;
    }
}
