package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Eyeable;

import java.util.*;
import java.util.function.Function;

public class EyeableTransformer extends AbstractTransformer<Eyeable,Boolean> {
    private static final int EYABLE_SOCKET_DATA_INDEX = 5;

    private static EyeableTransformer instance = null;

    public static EyeableTransformer getInstance() {
        if (EyeableTransformer.instance == null) EyeableTransformer.instance = new EyeableTransformer();
        return EyeableTransformer.instance;
    }

    private EyeableTransformer() {
        super(Eyeable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("eye")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("eye")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- EYEABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean hasEye;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Eyeable setEyePlaced(boolean placed) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.hasEye = hasEye;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic boolean isEyePlaced() {\n")
                .append("\t\treturn this.hasEye;\n")
                .append("\t}\n");

        loadEval.add("this.hasEye = false;");
        copyProperties.add((var) -> "this.hasEye = " + var + ".hasEye;");
        listImplements.add("Eyeable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[EYABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.hasEye ? 0b000_00_1_0_0 : 0)";
    }

    @Override
    protected Eyeable loadSocketData(Eyeable b, int[] socketData) {
        boolean hasEye = (socketData[EYABLE_SOCKET_DATA_INDEX] & 0b000_00_1_0_0) > 0;
        return b.setEyePlaced(hasEye);
    }

    @Override
    public Eyeable applyPropertiesToBlock(Eyeable base, Map<String, String> arguments) {
        Boolean hasEye = this.getSingle(((Block)base).getName(), arguments);
        if (hasEye != null) base = base.setEyePlaced(hasEye);
        return base;
    }

    @Override
    public String modifyBlockData(Eyeable block, String blockData) {
        boolean hasEye = block.isEyePlaced();
        return setBlockDataProperty(blockData, "eye", String.valueOf(hasEye));
    }
}
