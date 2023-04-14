package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Conditionable;

import java.util.*;
import java.util.function.Function;

public class ConditionableTransformer extends AbstractTransformer<Conditionable,Boolean> {
    private static final int CONDITIONABLE_SOCKET_DATA_INDEX = 11;

    public ConditionableTransformer() {
        super(Conditionable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("conditional")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("conditional")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- CONDITIONABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean conditionable;\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isConditional() {\n")
                .append("\t\treturn this.conditionable;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Conditionable setConditional(boolean val) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.conditionable = val;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.conditionable = false;");
        copyProperties.add((var) -> "this.conditionable = " + var + ".conditionable;");
        listImplements.add("Conditionable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[CONDITIONABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.conditionable ? 0b00000_0_0_1 : 0)";
    }

    @Override
    protected Conditionable loadSocketData(Conditionable b, int[] socketData) {
        boolean conditional = (socketData[CONDITIONABLE_SOCKET_DATA_INDEX] & 0b00000_0_0_1) > 0;
        return b.setConditional(conditional);
    }

    @Override
    public Conditionable applyPropertyToBlock(Conditionable base, Map<String, String> arguments) {
        Boolean conditional = this.getSingle(((Block)base).getName(), arguments);
        if (conditional != null) base = base.setConditional(conditional);
        return base;
    }

    @Override
    public String modifyBlockData(Conditionable block, String blockData) {
        boolean current = block.isConditional();
        return setBlockDataProperty(blockData, "conditional", String.valueOf(current));
    }
}
