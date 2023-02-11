package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Directionable;

import java.util.*;
import java.util.function.Function;

public class DirectionableTransformer extends AbstractTransformer<Directionable,Directionable.Direction> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();
        switch (argument) {
            case "attachment":
                r.add("floor");
                r.add("ceiling");
                r.add("double_wall");
                r.add("single_wall");
                break;
            case "axis":
                r.add("x");
                if (!mat.equals("NETHER_PORTAL")) r.add("y");
                r.add("z");
                break;
        }

        return r;
    }

    @Override
    public Collection<Directionable.Direction> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Directionable.Direction> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        this.getSocketData(socketData);
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Directionable applyPropertiesToBlock(Directionable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Directionable block, String blockData) {
        return null;
    }
}
