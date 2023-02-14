package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Rotable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RotableTransformer extends AbstractTransformer<Rotable,Rotable.Rotation> {
    private static final int ROTABLE_SOCKET_DATA_INDEX = 7;

    private static RotableTransformer instance = null;

    public static RotableTransformer getInstance() {
        if (RotableTransformer.instance == null) RotableTransformer.instance = new RotableTransformer();
        return RotableTransformer.instance;
    }

    private RotableTransformer() {
        super(Rotable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("rotation")) {
            for (int n = 0; n <= 15; n++) r.add(String.valueOf(n));
        }

        return r;
    }

    @Override
    public Collection<Rotable.Rotation> get(String mat, HashMap<String, List<String>> options) {
        Collection<Rotable.Rotation> r = new HashSet<>();

        if (options.containsKey("rotation")) r.addAll(options.get("rotation").stream().map(Integer::valueOf).map(rot -> Rotable.Rotation.values()[rot]).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Rotable.Rotation> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- ROTABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Rotable.Rotation rotation;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Rotable setRotation(Rotable.Rotation rotation) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.rotation = rotation;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Rotable.Rotation getRotation() {\n")
                .append("\t\treturn this.rotation;\n")
                .append("\t}\n");

        loadEval.add("this.rotation = Rotable.Rotation.S;");
        copyProperties.add((var) -> "this.rotation = " + var + ".rotation;");
        listImplements.add("Rotable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[ROTABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.rotation.ordinal())";
    }

    @Override
    protected Rotable loadSocketData(Rotable b, int[] socketData) {
        Rotable.Rotation rotation = Rotable.Rotation.values()[socketData[ROTABLE_SOCKET_DATA_INDEX] & 0b0000_1111];
        return b.setRotation(rotation);
    }

    @Override
    public Rotable applyPropertiesToBlock(Rotable base, Map<String, String> arguments) {
        Rotable.Rotation rotation = this.getSingle(((Block)base).getName(), arguments);
        if (rotation != null) base = base.setRotation(rotation);
        return base;
    }

    @Override
    public String modifyBlockData(Rotable block, String blockData) {
        Rotable.Rotation rotation = block.getRotation();
        return setBlockDataProperty(blockData, "rotation", String.valueOf(rotation.ordinal()));
    }
}
