package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Ignitable;
import dev.watchwolf.entities.blocks.Leaved;

import java.util.*;
import java.util.function.Function;

public class IgnitableTransformer extends AbstractTransformer<Ignitable,Boolean> {
    private static final int IGNITABLE_SOCKET_DATA_INDEX = 9;

    private static IgnitableTransformer instance = null;

    public static IgnitableTransformer getInstance() {
        if (IgnitableTransformer.instance == null) IgnitableTransformer.instance = new IgnitableTransformer();
        return IgnitableTransformer.instance;
    }

    private IgnitableTransformer() {
        super(Ignitable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("lit")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("lit")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- IGNITABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean ignited;\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isIgnited() {\n")
                .append("\t\treturn this.ignited;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Ignitable setIgnited(boolean value) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.ignited = value;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.ignited = true;");
        copyProperties.add((var) -> "this.ignited = " + var + ".ignited;");
        listImplements.add("Ignitable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[IGNITABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.ignited ? 0b0000_00_1_0 : 0)";
    }

    @Override
    protected Ignitable loadSocketData(Ignitable b, int[] socketData) {
        boolean ignited = (socketData[IGNITABLE_SOCKET_DATA_INDEX] & 0b0000_00_1_0) > 0;
        return b.setIgnited(ignited);
    }

    @Override
    public Ignitable applyPropertiesToBlock(Ignitable base, Map<String, String> arguments) {
        Boolean ignited = this.getSingle(((Block)base).getName(), arguments);
        if (ignited != null) base = base.setIgnited(ignited);
        return base;
    }

    @Override
    public String modifyBlockData(Ignitable block, String blockData) {
        boolean current = block.isIgnited();
        return setBlockDataProperty(blockData, "lit", String.valueOf(current));
    }
}
