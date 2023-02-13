package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Directionable;

import java.util.*;
import java.util.function.Function;

public class DirectionableTransformer extends AbstractTransformer<Directionable,Directionable.Direction> {
    private static final int DIRECTIONABLE_SOCKET_DATA_INDEX = 3;

    private static DirectionableTransformer instance = null;

    public static DirectionableTransformer getInstance() {
        if (DirectionableTransformer.instance == null) DirectionableTransformer.instance = new DirectionableTransformer();
        return DirectionableTransformer.instance;
    }

    private DirectionableTransformer() {
        super(Directionable.class);
    }

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
        Collection<Directionable.Direction> r = new HashSet<>();
        if (options.containsKey("axis")) {
            if (options.get("axis").contains("x")) r.add(Directionable.Direction.X);
            if (options.get("axis").contains("y")) r.add(Directionable.Direction.Y);
            if (options.get("axis").contains("z")) r.add(Directionable.Direction.Z);
        }
        if (options.containsKey("attachment")) {
            if (options.get("attachment").contains("double_wall")) r.add(Directionable.Direction.DOUBLE_WALL);
            if (options.get("attachment").contains("single_wall")) r.add(Directionable.Direction.SINGLE_WALL);
            if (options.get("attachment").contains("ceiling") || options.get("attachment").contains("floor")) r.add(Directionable.Direction.NONE);
        }
        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Directionable.Direction> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- DIRECTIONABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Directionable.Direction direction;\n")
                .append("\tprivate final HashSet<Directionable.Direction> allowedDirections = new HashSet<>();\n")
                .append("\tpublic Directionable.Direction getFacingDirection() {\n")
                .append("\t\treturn this.direction;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException {\n")
                .append("\t\tif (!this.allowedDirections.contains(d)) throw new IllegalArgumentException(\"" + className + " block doesn't allow the direction \" + d.name());\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.direction = d;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\tpublic Set<Directionable.Direction> getValidDirections() {\n")
                .append("\t\treturn (HashSet)this.allowedDirections.clone();\n")
                .append("\t}\n");

        for (Directionable.Direction orientation : list) loadEval.add("this.allowedDirections.add(Directionable.Direction." + orientation.name() + ");");
        loadEval.add("this.direction = Directionable.Direction." + list.stream().findFirst().orElseThrow(() -> new RuntimeException("Directionable block without any direction")) + ";"); // TODO some items defaults to other values
        copyProperties.add((var) -> "this.direction = " + var + ".direction;");
        listImplements.add("Directionable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[DIRECTIONABLE_SOCKET_DATA_INDEX] += " |\n\t\t\t\t(byte)(this.direction.getSendData() << 6)";
    }

    @Override
    protected Directionable loadSocketData(Directionable directionable, int[] socketData) {
        int tmp = (socketData[DirectionableTransformer.DIRECTIONABLE_SOCKET_DATA_INDEX] >> 6);
        try {
            switch (tmp) {
                case 1:
                    directionable = directionable.setDirection(Directionable.Direction.X);
                    break;
                case 2:
                    directionable = directionable.setDirection(Directionable.Direction.Y);
                    break;
                case 3:
                    directionable = directionable.setDirection(Directionable.Direction.Z);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        try {
            switch (tmp) {
                case 0:
                    directionable = directionable.setDirection(Directionable.Direction.NONE);
                    break;
                case 1:
                    directionable = directionable.setDirection(Directionable.Direction.SINGLE_WALL);
                    break;
                case 2:
                    directionable = directionable.setDirection(Directionable.Direction.DOUBLE_WALL);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        return directionable;
    }

    @Override
    public Directionable applyPropertiesToBlock(Directionable base, Map<String, String> arguments) {
        Directionable.Direction direction = this.getSingle(((Block)base).getName(), arguments);
        if (direction != null) base = base.setDirection(direction);
        return base;
    }

    @Override
    public String modifyBlockData(Directionable directionable, String spigotBlock) {
        Directionable.Direction current = directionable.getFacingDirection();
        if (current == Directionable.Direction.X) spigotBlock = setBlockDataProperty(spigotBlock, "axis", "x");
        else if (current == Directionable.Direction.Y) spigotBlock = setBlockDataProperty(spigotBlock, "axis", "y");
        else if (current == Directionable.Direction.Z) spigotBlock = setBlockDataProperty(spigotBlock, "axis", "z");
        else if (current == Directionable.Direction.DOUBLE_WALL) spigotBlock = setBlockDataProperty(spigotBlock, "attachment", "double_wall");
        else if (current == Directionable.Direction.SINGLE_WALL) spigotBlock = setBlockDataProperty(spigotBlock, "attachment", "single_wall");
        return spigotBlock;
    }
}
