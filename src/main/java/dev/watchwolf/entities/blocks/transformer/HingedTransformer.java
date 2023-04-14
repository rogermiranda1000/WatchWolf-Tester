package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Eyeable;
import dev.watchwolf.entities.blocks.Hinged;

import java.util.*;
import java.util.function.Function;

public class HingedTransformer extends AbstractTransformer<Hinged, Hinged.Hinge> {
    private static final int HINGED_SOCKET_DATA_INDEX = 5;

    public HingedTransformer() {
        super(Hinged.class);
    }

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
        Collection<Hinged.Hinge> r = new HashSet<>();

        if (options.containsKey("hinge")) {
            r.add(Hinged.Hinge.LEFT);
            r.add(Hinged.Hinge.RIGHT);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Hinged.Hinge> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- HINGED INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Hinged.Hinge hingeDirection;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Hinged setHinge(Hinged.Hinge hinge) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.hingeDirection = hinge;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Hinge getHinge() {\n")
                .append("\t\treturn this.hingeDirection;\n")
                .append("\t}\n");

        loadEval.add("this.hingeDirection = Hinged.Hinge.LEFT;");
        copyProperties.add((var) -> "this.hingeDirection = " + var + ".hingeDirection;");
        listImplements.add("Hinged");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[HINGED_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.hingeDirection.equals(Hinged.Hinge.RIGHT) ? 0b000_00_0_1_0 : 0)";
    }

    @Override
    protected Hinged loadSocketData(Hinged b, int[] socketData) {
        boolean rightHinge = (socketData[HINGED_SOCKET_DATA_INDEX] & 0b000_00_0_1_0) > 0;
        return b.setHinge(rightHinge ? Hinged.Hinge.RIGHT : Hinged.Hinge.LEFT);
    }

    @Override
    public Hinged applyPropertyToBlock(Hinged base, Map<String, String> arguments) {
        Hinged.Hinge hinge = this.getSingle(((Block)base).getName(), arguments);
        if (hinge != null) base = base.setHinge(hinge);
        return base;
    }

    @Override
    public String modifyBlockData(Hinged block, String blockData) {
        Hinged.Hinge hinge = block.getHinge();
        return setBlockDataProperty(blockData, "hinge", hinge.name().toLowerCase()); // left/right
    }
}
