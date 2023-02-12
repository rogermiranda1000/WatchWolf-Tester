package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Sectionable;

import java.util.*;
import java.util.function.Function;

public class SectionableTransformer extends AbstractTransformer<Sectionable,Sectionable.Section> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "part":
                r.add("foot");
                r.add("head");
                break;
            case "extended":
                r.add("true");
                r.add("false");
                break;
            case "shape":
                if (!mat.endsWith("RAIL")) {
                    r.add("straight");
                    r.add("inner_right");
                    r.add("inner_left");
                    r.add("outer_right");
                    r.add("outer_left");
                }
                break;
        }

        return r;
    }

    @Override
    public Collection<Sectionable.Section> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Sectionable.Section> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Sectionable applyPropertiesToBlock(Sectionable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Sectionable block, String blockData) {
        return null;
    }
}
