package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Orientable;

import java.util.*;
import java.util.function.Function;

public class OrientableTransformer extends AbstractTransformer<Orientable,Orientable.Orientation> {
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();
        switch (argument) {
            case "down":
            case "up":
            case "hanging":
                r.add("true");
                r.add("false");
                break;
            case "north":
            case "south":
            case "east":
            case "west":
                if (mat.endsWith("_WALL")) {
                    r.add("none");
                    r.add("low");
                    // ignore tall
                }
                else if (mat.equals("REDSTONE_WIRE")) {
                    r.add("none");
                    r.add("slide");
                }
                else {
                    r.add("true");
                    r.add("false");
                }
                break;
            case "face":
                r.add("wall");
                r.add("floor");
                r.add("ceiling");
                break;
            case "facing":
                r.add("up");
                r.add("down");
                r.add("north");
                r.add("south");
                r.add("east");
                r.add("west");
                break;
            case "half":
                r.add("bottom");
                r.add("top");
                r.add("lower");
                r.add("upper");
                break;
            case "orientation":
                r.add("north_up");
                r.add("south_up");
                r.add("east_up");
                r.add("west_up");
                r.add("up_north");
                r.add("up_south");
                r.add("up_east");
                r.add("up_west");
                r.add("down_north");
                r.add("down_south");
                r.add("down_east");
                r.add("down_west");
                break;
            case "shape":
                if (mat.endsWith("RAIL")) {
                    r.add("north_south");
                    r.add("east_west");
                    r.add("north_east");
                    r.add("north_west");
                    r.add("south_east");
                    r.add("south_west");
                    r.add("ascending_north");
                    r.add("ascending_south");
                    r.add("ascending_east");
                    r.add("ascending_west");
                }
                else {
                    r.add("straight");
                    r.add("inner_right");
                    r.add("inner_left");
                    r.add("outer_right");
                    r.add("outer_left");
                }
                break;
            case "type":
                r.add("bottom");
                r.add("top");
                r.add("double");
                break;
            case "vertical-direction":
                r.add("up");
                r.add("down");
                break;
        }

        return r;
    }

    @Override
    public Collection<Orientable.Orientation> get(String mat, HashMap<String, List<String>> options) {
        return null;
    }

    @Override
    public String getImplementation(String className, Collection<Orientable.Orientation> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        this.getSocketData(socketData);
        return null;
    }

    @Override
    protected void getSocketData(String[] socketData) {

    }

    @Override
    public Orientable applyPropertiesToBlock(Orientable base, Map<String, String> arguments) {
        return null;
    }

    @Override
    public String modifyBlockData(Orientable block, String blockData) {
        return null;
    }
}
