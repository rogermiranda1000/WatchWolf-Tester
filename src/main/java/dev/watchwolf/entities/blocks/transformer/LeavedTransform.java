package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Leaved;

import java.util.*;
import java.util.function.Function;

public class LeavedTransform extends AbstractTransformer<Leaved,Leaved.Leaves> {
    private static final int LEAVED_SOCKET_DATA_INDEX = 9;

    private static LeavedTransform instance = null;

    public static LeavedTransform getInstance() {
        if (LeavedTransform.instance == null) LeavedTransform.instance = new LeavedTransform();
        return LeavedTransform.instance;
    }

    private LeavedTransform() {
        super(Leaved.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("leaves")) {
            r.add("none");
            r.add("small");
            r.add("large");
        }

        return r;
    }

    @Override
    public Collection<Leaved.Leaves> get(String mat, HashMap<String, List<String>> options) {
        Collection<Leaved.Leaves> r = new HashSet<>();

        if (options.containsKey("leaves")) {
            r.add(Leaved.Leaves.NONE);
            r.add(Leaved.Leaves.SMALL);
            r.add(Leaved.Leaves.LARGE);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Leaved.Leaves> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- LEAVED INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Leaved.Leaves leaves;\n");

        sb.append("\t@Override\n")
                .append("\tpublic Leaved.Leaves getLeaves() {\n")
                .append("\t\treturn this.leaves;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Leaved setLeaves(Leaved.Leaves l) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.leaves = leaves;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.leaves = Leaved.Leaves.NONE;");
        copyProperties.add((var) -> "this.leaves = " + var + ".leaves;");
        listImplements.add("Leaved");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[LEAVED_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.leaves.ordinal() << 2)";
    }

    @Override
    protected Leaved loadSocketData(Leaved b, int[] socketData) {
        int tmp = (socketData[LEAVED_SOCKET_DATA_INDEX] >> 2) & 0b11;
        switch (tmp) {
            case 0:
                b = b.setLeaves(Leaved.Leaves.NONE);
                break;
            case 1:
                b = b.setLeaves(Leaved.Leaves.SMALL);
                break;
            case 2:
                b = b.setLeaves(Leaved.Leaves.LARGE);
                break;
        }
        return b;
    }

    @Override
    public Leaved applyPropertiesToBlock(Leaved base, Map<String, String> arguments) {
        Leaved.Leaves leaves = this.getSingle(((Block)base).getName(), arguments);
        if (leaves != null) base = base.setLeaves(leaves);
        return base;
    }

    @Override
    public String modifyBlockData(Leaved block, String blockData) {
        Leaved.Leaves current = block.getLeaves();
        return setBlockDataProperty(blockData, "leaves", current.name().toLowerCase());
    }
}
