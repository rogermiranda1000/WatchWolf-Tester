package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Ageable;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Groupable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupableTransformer extends AbstractTransformer<Groupable,Integer> {
    private static final int GROUPABLE_SOCKET_DATA_INDEX = 5;

    private static GroupableTransformer instance = null;

    public static GroupableTransformer getInstance() {
        if (GroupableTransformer.instance == null) GroupableTransformer.instance = new GroupableTransformer();
        return GroupableTransformer.instance;
    }

    private GroupableTransformer() {
        super(Groupable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "candles":
            case "eggs":
            case "pickles":
                for (int n = 1; n <= 4; n++) r.add(String.valueOf(n));
                break;
        }

        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();

        if (options.containsKey("candles")) r.addAll(options.get("candles").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("eggs")) r.addAll(options.get("eggs").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("pickles")) r.addAll(options.get("pickles").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        int maxGroup = Collections.max(list);
        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- GROUPABLE INTERFACE ---   */\n");
        sb.append("\tprivate int groupAmountMinusOne;\n")
                .append("\tprivate final int maxGroupAmount;\n");

        sb.append("\n\tpublic Ageable setGroupAmount(int amount) throws IllegalArgumentException {\n")
                .append("\t\tif (amount < 1 || amount > this.getMaxGroupAmount()) throw new IllegalArgumentException(\"" + className + " block only allows grouping from 1 to \" + this.getMaxGroupAmount());\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.groupAmountMinusOne = amount - 1;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@RelevantBlockData\n")
                .append("\n\tpublic int getGroupAmount() {\n")
                .append("\t\treturn this.groupAmountMinusOne + 1;\n")
                .append("\t}\n");

        sb.append("\n\tpublic int getMaxGroupAmount() {\n")
                .append("\t\treturn this.maxGroupAmount;\n")
                .append("\t}\n");

        loadEval.add("this.groupAmountMinusOne = 0; // 0 is 1");
        loadEval.add("this.maxGroupAmount = " + maxGroup + ";");
        copyProperties.add((var) -> "this.groupAmountMinusOne = " + var + ".groupAmountMinusOne;");
        listImplements.add("Groupable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[GROUPABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.groupAmountMinusOne << 5)";
    }

    @Override
    protected Groupable loadSocketData(Groupable b, int[] socketData) {
        int group = (socketData[GROUPABLE_SOCKET_DATA_INDEX] >> 5) + 1;
        return b.setGroupAmount(group);
    }

    @Override
    public Groupable applyPropertiesToBlock(Groupable base, Map<String, String> arguments) {
        Integer group = this.getSingle(((Block)base).getName(), arguments);
        if (group != null) base = base.setGroupAmount(group);
        return base;
    }

    @Override
    public String modifyBlockData(Groupable block, String blockData) {
        int amount = block.getGroupAmount();
        blockData = setBlockDataProperty(blockData, "candles", String.valueOf(amount));
        blockData = setBlockDataProperty(blockData, "eggs", String.valueOf(amount));
        blockData = setBlockDataProperty(blockData, "pickles", String.valueOf(amount));
        return blockData;
    }
}
