package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Invertable;

import java.util.*;
import java.util.function.Function;

public class InvertableTransformer extends AbstractTransformer<Invertable,Boolean> {
    private static final int INVERTABLE_SOCKET_DATA_INDEX = 11;

    private static InvertableTransformer instance = null;

    public static InvertableTransformer getInstance() {
        if (InvertableTransformer.instance == null) InvertableTransformer.instance = new InvertableTransformer();
        return InvertableTransformer.instance;
    }

    private InvertableTransformer() {
        super(Invertable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("inverted")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("inverted")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- INVERTABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean inverted;\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isInverted() {\n")
                .append("\t\treturn this.inverted;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Invertable setInvert(boolean val) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.inverted = val;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.inverted = false;");
        copyProperties.add((var) -> "this.inverted = " + var + ".inverted;");
        listImplements.add("Invertable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[INVERTABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.inverted ? 0b00000_0_1_0 : 0)";
    }

    @Override
    protected Invertable loadSocketData(Invertable b, int[] socketData) {
        boolean val = (socketData[INVERTABLE_SOCKET_DATA_INDEX] & 0b00000_0_1_0) > 0;
        return b.setInvert(val);
    }

    @Override
    public Invertable applyPropertiesToBlock(Invertable base, Map<String, String> arguments) {
        Boolean val = this.getSingle(((Block)base).getName(), arguments);
        if (val != null) base = base.setInvert(val);
        return base;
    }

    @Override
    public String modifyBlockData(Invertable block, String blockData) {
        boolean current = block.isInverted();
        return setBlockDataProperty(blockData, "inverted", String.valueOf(current));
    }
}
