package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Stateful;

import java.util.*;
import java.util.function.Function;

public class StatefulTransformer extends AbstractTransformer<Stateful,Stateful.Mode> {
    private static final int STATEFUL_SOCKET_DATA_INDEX = 9;

    public StatefulTransformer() {
        super(Stateful.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("mode")) {
            if (mat.equals("COMPARATOR")) {
                r.add("compare");
                r.add("subtract");
            } else { // STRUCTURE BLOCK
                r.add("load");
                r.add("corner");
                r.add("save");
            }
        }

        return r;
    }

    @Override
    public Collection<Stateful.Mode> get(String mat, HashMap<String, List<String>> options) {
        Collection<Stateful.Mode> r = new HashSet<>();

        if (options.containsKey("mode")) {
            if (mat.equals("COMPARATOR")) {
                r.add(Stateful.Mode.COMPARE);
                r.add(Stateful.Mode.SUBTRACT);
            } else { // STRUCTURE BLOCK
                r.add(Stateful.Mode.LOAD);
                r.add(Stateful.Mode.CORNER);
                r.add(Stateful.Mode.SAVE);
            }
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Stateful.Mode> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- STATEFUL INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Stateful.Mode state;\n")
                .append("\tprivate final HashSet<Stateful.Mode> allowedStates = new HashSet<>();\n");

        sb.append("\t@Override\n")
                .append("\tpublic Stateful.Mode getMode() {\n")
                .append("\t\treturn this.state;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Stateful setMode(Stateful.Mode mode) throws IllegalArgumentException {\n")
                .append("\t\tif (!this.allowedStates.contains(mode)) throw new IllegalArgumentException(\"" + className + " block doesn't allow the state \" + mode.name());\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.state = mode;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\tpublic Set<Stateful.Mode> getValidModes() {\n")
                .append("\t\treturn (HashSet)this.allowedStates.clone();\n")
                .append("\t}\n");

        for (Stateful.Mode mode : list) loadEval.add("this.allowedStates.add(Stateful.Mode." + mode.name() + ");");
        loadEval.add("this.state = Stateful.Mode." + list.stream().findFirst().orElseThrow(() -> new RuntimeException("Stateful block without any state")) + ";");
        copyProperties.add((var) -> "this.state = " + var + ".state;");
        listImplements.add("Stateful");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[STATEFUL_SOCKET_DATA_INDEX] += " |\n\t\t\t\t(byte)(this.state.getSendData() << 4)";
    }

    @Override
    protected Stateful loadSocketData(Stateful stateful, int[] socketData) {
        int tmp = (socketData[STATEFUL_SOCKET_DATA_INDEX] >> 4);
        try {
            switch (tmp) {
                case 0:
                    stateful = stateful.setMode(Stateful.Mode.COMPARE);
                    break;
                case 1:
                    stateful = stateful.setMode(Stateful.Mode.SUBTRACT);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        try {
            switch (tmp) {
                case 0:
                    stateful = stateful.setMode(Stateful.Mode.LOAD);
                    break;
                case 1:
                    stateful = stateful.setMode(Stateful.Mode.CORNER);
                    break;
                case 2:
                    stateful = stateful.setMode(Stateful.Mode.SAVE);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        return stateful;
    }

    @Override
    public Stateful applyPropertyToBlock(Stateful base, Map<String, String> arguments) {
        Stateful.Mode mode = this.getSingle(((Block)base).getName(), arguments);
        if (mode != null) base = base.setMode(mode);
        return base;
    }

    @Override
    public String modifyBlockData(Stateful block, String blockData) {
        Stateful.Mode current = block.getMode();
        return setBlockDataProperty(blockData, "mode", current.name().toLowerCase());
    }
}
