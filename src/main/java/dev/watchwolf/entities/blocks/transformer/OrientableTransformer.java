package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Orientable;

import java.util.*;
import java.util.function.Function;

public class OrientableTransformer extends AbstractTransformer<Orientable,Orientable.Orientation> {
    private static final int ORIENTABLE_SOCKET_DATA_INDEX = 3;

    private static OrientableTransformer instance = null;

    public static OrientableTransformer getInstance() {
        if (OrientableTransformer.instance == null) OrientableTransformer.instance = new OrientableTransformer();
        return OrientableTransformer.instance;
    }

    private OrientableTransformer() {
        super(Orientable.class);
    }

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
                break;
            case "type":
                if (mat.endsWith("_SLAB")) {
                    r.add("bottom");
                    r.add("top");
                    r.add("double");
                }
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
        Collection<Orientable.Orientation> r = new HashSet<>(); // TODO why duplicates?
        if (options.containsKey("up")) r.add(Orientable.Orientation.U);
        if (options.containsKey("hanging")) r.add(Orientable.Orientation.U);
        if (options.containsKey("down")) r.add(Orientable.Orientation.D);
        if (options.containsKey("north")) r.add(Orientable.Orientation.N);
        if (options.containsKey("south")) r.add(Orientable.Orientation.S);
        if (options.containsKey("east")) r.add(Orientable.Orientation.E);
        if (options.containsKey("west")) r.add(Orientable.Orientation.W);
        if (options.containsKey("face")) {
            if (options.get("face").contains("ceiling")) r.add(Orientable.Orientation.U);
            if (options.get("face").contains("floor")) r.add(Orientable.Orientation.D);
        }
        if (options.containsKey("attachment")) {
            if (options.get("attachment").contains("ceiling")) r.add(Orientable.Orientation.U);
            if (options.get("attachment").contains("floor")) r.add(Orientable.Orientation.D);
        }
        if (options.containsKey("half")) {
            if (options.get("half").contains("top") || options.get("half").contains("upper")) r.add(Orientable.Orientation.U);
            if (options.get("half").contains("bottom") || options.get("half").contains("upper")) r.add(Orientable.Orientation.D);
        }
        if (options.containsKey("facing")) {
            if (options.get("facing").contains("up")) r.add(Orientable.Orientation.U);
            if (options.get("facing").contains("down")) r.add(Orientable.Orientation.D);
            if (options.get("facing").contains("north")) r.add(Orientable.Orientation.N);
            if (options.get("facing").contains("south")) r.add(Orientable.Orientation.S);
            if (options.get("facing").contains("east")) r.add(Orientable.Orientation.E);
            if (options.get("facing").contains("west")) r.add(Orientable.Orientation.W);
        }
        if (options.containsKey("vertical-direction")) {
            if (options.get("vertical-direction").contains("up")) r.add(Orientable.Orientation.U);
            if (options.get("vertical-direction").contains("down")) r.add(Orientable.Orientation.D);
        }
        if (options.containsKey("type")) {
            if (options.get("type").contains("top") || options.get("type").contains("double")) r.add(Orientable.Orientation.U);
            if (options.get("type").contains("bottom") || options.get("type").contains("double")) r.add(Orientable.Orientation.D);
        }
        if (options.containsKey("orientation")) {
            if (regexContains(options.get("orientation"), "^up_")) r.add(Orientable.Orientation.U);
            if (regexContains(options.get("orientation"), "^down_")) r.add(Orientable.Orientation.D);
            if (regexContains(options.get("orientation"), "_north$") || options.get("orientation").contains("north_up")) r.add(Orientable.Orientation.N);
            if (regexContains(options.get("orientation"), "_south$") || options.get("orientation").contains("south_up")) r.add(Orientable.Orientation.S);
            if (regexContains(options.get("orientation"), "_east$") || options.get("orientation").contains("east_up")) r.add(Orientable.Orientation.E);
            if (regexContains(options.get("orientation"), "_west$") || options.get("orientation").contains("west_up")) r.add(Orientable.Orientation.W);
        }
        if (options.containsKey("shape")) {
            if (regexContains(options.get("shape"), "^ascending_")) r.add(Orientable.Orientation.U);
            if (regexContains(options.get("shape"), "^north_") || options.get("shape").contains("ascending_north")) r.add(Orientable.Orientation.N);
            if (regexContains(options.get("shape"), "^south_") || options.get("shape").contains("north_south") || options.get("shape").contains("ascending_south")) r.add(Orientable.Orientation.S);
            if (regexContains(options.get("shape"), "^east_") || options.get("shape").contains("ascending_east") || options.get("shape").contains("east_west")) r.add(Orientable.Orientation.E);
            if (regexContains(options.get("shape"), "^west_") || options.get("shape").contains("ascending_west")) r.add(Orientable.Orientation.W);
        }
        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Orientable.Orientation> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- ORIENTABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isOrientationSet(Orientable.Orientation o) throws IllegalArgumentException {\n")
                .append("\t\tBoolean result = this.orientation.get(o);\n");
        if (list.size() < 6) sb.append("\t\tif (result == null) throw new IllegalArgumentException(\"" + className + " block doesn't contain orientation \" + o.name());\n");
        sb.append("\t\treturn result;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Orientable setOrientation(Orientable.Orientation o, boolean value) throws IllegalArgumentException {\n");
        if (list.size() < 6) sb.append("\t\tif (!this.orientation.containsKey(o)) throw new IllegalArgumentException(\"" + className + " block doesn't contain orientation \" + o.name());\n");
        sb.append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.orientation.put(o, value);\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Set<Orientable.Orientation> getValidOrientations() {\n")
                .append("\t\treturn this.orientation.keySet();\n")
                .append("\t}\n");

        for (Orientable.Orientation orientation : list) loadEval.add("this.orientation.put(Orientable.Orientation." + orientation.name() + ", false);"); // TODO some items defaults to true
        copyProperties.add((var) -> "this.orientation.putAll(" + var + ".orientation);");
        listImplements.add("Orientable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[ORIENTABLE_SOCKET_DATA_INDEX] += " |\n\t\t\t\t(byte)((Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.U)) ? 0b00_000001 : 0x00) |\n" +
                                                            "\t\t\t\t(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.D)) ? 0b00_000010 : 0x00) |\n" +
                                                            "\t\t\t\t(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.N)) ? 0b00_000100 : 0x00) |\n" +
                                                            "\t\t\t\t(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.S)) ? 0b00_001000 : 0x00) |\n" +
                                                            "\t\t\t\t(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.E)) ? 0b00_010000 : 0x00) |\n" +
                                                            "\t\t\t\t(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.W)) ? 0b00_100000 : 0x00))";
    }

    @Override
    protected Orientable loadSocketData(Orientable orientable, int[] socketData) {
        int tmp = socketData[OrientableTransformer.ORIENTABLE_SOCKET_DATA_INDEX];
        try {
            if ((tmp & 0b00_000001) > 0) orientable = orientable.setOrientation(Orientable.Orientation.U);
            if ((tmp & 0b00_000010) > 0) orientable = orientable.setOrientation(Orientable.Orientation.D);
            if ((tmp & 0b00_000100) > 0) orientable = orientable.setOrientation(Orientable.Orientation.N);
            if ((tmp & 0b00_001000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.S);
            if ((tmp & 0b00_010000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.E);
            if ((tmp & 0b00_100000) > 0) orientable = orientable.setOrientation(Orientable.Orientation.W);
        } catch (IllegalArgumentException ignore) {}
        return orientable;
    }

    @Override
    public Orientable applyPropertiesToBlock(Orientable base, Map<String, String> arguments) {
        Collection<Orientable.Orientation> orientations = this.get(((Block)base).getName(), arguments);
        if (this.applies(orientations)) {
            for (Orientable.Orientation orientation : Orientable.Orientation.values()) {
                if (orientations.contains(orientation)) base = base.setOrientation(orientation);
            }
        }
        return base;
    }

    @Override
    public String modifyBlockData(Orientable orientable, String spigotBlock) {
        boolean doubleType = false;
        try {
            if (orientable.isOrientationSet(Orientable.Orientation.U)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "up", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "face", "ceiling");
                spigotBlock = setBlockDataProperty(spigotBlock, "attachment", "ceiling");
                spigotBlock = setBlockDataProperty(spigotBlock, "half", "top");
                spigotBlock = setBlockDataProperty(spigotBlock, "half", "upper");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "up");
                spigotBlock = setBlockDataProperty(spigotBlock, "vertical-direction", "up");
                try {
                    doubleType = orientable.isOrientationSet(Orientable.Orientation.D); // both top and bottom
                } catch (IllegalArgumentException ignore) {}
                spigotBlock = setBlockDataProperty(spigotBlock, "type", doubleType ? "double" : "top");
                // TODO orientation
                // TODO shape
                spigotBlock = setBlockDataProperty(spigotBlock, "hanging", "true");
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "up", "false");
        } catch (IllegalArgumentException ignore) {}

        try {
            if (orientable.isOrientationSet(Orientable.Orientation.D)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "down", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "face", "floor");
                spigotBlock = setBlockDataProperty(spigotBlock, "attachment", "floor");
                spigotBlock = setBlockDataProperty(spigotBlock, "half", "bottom");
                spigotBlock = setBlockDataProperty(spigotBlock, "half", "lower");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "down");
                spigotBlock = setBlockDataProperty(spigotBlock, "vertical-direction", "down");
                // double already done on top
                // TODO orientation
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "down", "false");
        } catch (IllegalArgumentException ignore) {}

        try {
            if (orientable.isOrientationSet(Orientable.Orientation.N)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "north", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "north");
                // TODO orientation
                // TODO shape
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "north", "false");
        } catch (IllegalArgumentException ignore) {}

        try {
            if (orientable.isOrientationSet(Orientable.Orientation.S)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "south", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "south");
                // TODO orientation
                // TODO shape
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "south", "false");
        } catch (IllegalArgumentException ignore) {}

        try {
            if (orientable.isOrientationSet(Orientable.Orientation.E)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "east", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "east");
                // TODO orientation
                // TODO shape
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "east", "false");
        } catch (IllegalArgumentException ignore) {}

        try {
            if (orientable.isOrientationSet(Orientable.Orientation.W)) {
                spigotBlock = setBlockDataProperty(spigotBlock, "west", "true");
                spigotBlock = setBlockDataProperty(spigotBlock, "facing", "west");
                // TODO orientation
                // TODO shape
            }
            else spigotBlock = setBlockDataProperty(spigotBlock, "west", "false");
        } catch (IllegalArgumentException ignore) {}

        return spigotBlock;
    }
}
