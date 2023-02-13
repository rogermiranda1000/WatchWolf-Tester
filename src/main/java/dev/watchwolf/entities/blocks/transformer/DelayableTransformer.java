package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Delayable;
import dev.watchwolf.entities.blocks.Groupable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DelayableTransformer extends AbstractTransformer<Delayable,Integer> {
    private static final int DELAYABLE_SOCKET_DATA_INDEX = 5;

    private static DelayableTransformer instance = null;

    public static DelayableTransformer getInstance() {
        if (DelayableTransformer.instance == null) DelayableTransformer.instance = new DelayableTransformer();
        return DelayableTransformer.instance;
    }

    private DelayableTransformer() {
        super(Delayable.class);
    }
    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("delay")) {
            for (int n = 1; n <= 4; n++) r.add(String.valueOf(n));
        }

        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();

        if (options.containsKey("delay")) r.addAll(options.get("delay").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        int maxGroup = Collections.max(list);
        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- GROUPABLE INTERFACE ---   */\n");
        sb.append("\tprivate int delayMinusOne;\n");

        sb.append("\n\tpublic Delayable setDelay(int delay) throws IllegalArgumentException {\n")
                .append("\t\tif (delay < 1 || delay > 4) throw new IllegalArgumentException(\"" + className + " block only allows delay from 1 to 4\");\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.delayMinusOne = delay - 1;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@RelevantBlockData\n")
                .append("\n\tpublic int getDelay() {\n")
                .append("\t\treturn this.delayMinusOne + 1;\n")
                .append("\t}\n");

        loadEval.add("this.delayMinusOne = 0; // 0 is 1");
        copyProperties.add((var) -> "this.delayMinusOne = " + var + ".delayMinusOne;");
        listImplements.add("Delayable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[DELAYABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.delayMinusOne << 3)";
    }

    @Override
    protected Delayable loadSocketData(Delayable b, int[] socketData) {
        int delay = ((socketData[DELAYABLE_SOCKET_DATA_INDEX] & 0b000_11_0_0_0) >> 3) + 1;
        return b.setDelay(delay);
    }

    @Override
    public Delayable applyPropertiesToBlock(Delayable base, Map<String, String> arguments) {
        Integer delay = this.getSingle(((Block)base).getName(), arguments);
        if (delay != null) base = base.setDelay(delay);
        return base;
    }

    @Override
    public String modifyBlockData(Delayable block, String blockData) {
        int delay = block.getDelay();
        return setBlockDataProperty(blockData, "delay", String.valueOf(delay));
    }
}
