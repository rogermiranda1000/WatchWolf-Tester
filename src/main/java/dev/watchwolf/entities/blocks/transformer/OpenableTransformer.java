package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Openable;

import java.util.*;
import java.util.function.Function;

public class OpenableTransformer extends AbstractTransformer<Openable,Boolean> {
    private static final int OPENABLE_SOCKET_DATA_INDEX = 5;

    private static OpenableTransformer instance = null;

    public static OpenableTransformer getInstance() {
        if (OpenableTransformer.instance == null) OpenableTransformer.instance = new OpenableTransformer();
        return OpenableTransformer.instance;
    }

    private OpenableTransformer() {
        super(Openable.class);
    }


    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("open")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("open")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- OPENABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean isOpen;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Openable setOpened(boolean opened) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.isOpen = opened;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic boolean isOpened() {\n")
                .append("\t\treturn this.isOpen;\n")
                .append("\t}\n");

        loadEval.add("this.isOpen = false;");
        copyProperties.add((var) -> "this.isOpen = " + var + ".isOpen;");
        listImplements.add("Openable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[OPENABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.hasEye ? 1 : 0)";
    }

    @Override
    protected Openable loadSocketData(Openable b, int[] socketData) {
        boolean opened = (socketData[OPENABLE_SOCKET_DATA_INDEX] & 1) > 0;
        return b.setOpened(opened);
    }

    @Override
    public Openable applyPropertiesToBlock(Openable base, Map<String, String> arguments) {
        Boolean isOpened = this.getSingle(((Block)base).getName(), arguments);
        if (isOpened != null) base = base.setOpened(isOpened);
        return base;
    }

    @Override
    public String modifyBlockData(Openable block, String blockData) {
        boolean isOpened = block.isOpened();
        return setBlockDataProperty(blockData, "open", String.valueOf(isOpened));
    }
}
