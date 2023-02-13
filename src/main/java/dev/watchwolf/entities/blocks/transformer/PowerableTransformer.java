package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Powerable;

import java.util.*;
import java.util.function.Function;

public class PowerableTransformer extends AbstractTransformer<Powerable,Boolean> {
    private static final int POWERABLE_SOCKET_DATA_INDEX = 11;

    private static PowerableTransformer instance = null;

    public static PowerableTransformer getInstance() {
        if (PowerableTransformer.instance == null) PowerableTransformer.instance = new PowerableTransformer();
        return PowerableTransformer.instance;
    }

    private PowerableTransformer() {
        super(Powerable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("powered")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("powered")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- POWERABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean powered;\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isPowered() {\n")
                .append("\t\treturn this.powered;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Powerable setPowered(boolean val) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.powered = val;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.powered = false;");
        copyProperties.add((var) -> "this.powered = " + var + ".powered;");
        listImplements.add("Powerable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[POWERABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.inverted ? 0b00000_1_0_0 : 0)";
    }

    @Override
    protected Powerable loadSocketData(Powerable b, int[] socketData) {
        boolean val = (socketData[POWERABLE_SOCKET_DATA_INDEX] & 0b00000_1_0_0) > 0;
        return b.setPowered(val);
    }

    @Override
    public Powerable applyPropertiesToBlock(Powerable base, Map<String, String> arguments) {
        Boolean val = this.getSingle(((Block)base).getName(), arguments);
        if (val != null) base = base.setPowered(val);
        return base;
    }

    @Override
    public String modifyBlockData(Powerable block, String blockData) {
        boolean current = block.isPowered();
        return setBlockDataProperty(blockData, "powered", String.valueOf(current));
    }
}
