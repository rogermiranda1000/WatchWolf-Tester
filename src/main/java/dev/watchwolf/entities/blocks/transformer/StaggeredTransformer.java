package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Staggered;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StaggeredTransformer extends AbstractTransformer<Staggered,Integer> {
    private static final int STAGGERED_SOCKET_DATA_INDEX = 6;

    private static StaggeredTransformer instance = null;

    public static StaggeredTransformer getInstance() {
        if (StaggeredTransformer.instance == null) StaggeredTransformer.instance = new StaggeredTransformer();
        return StaggeredTransformer.instance;
    }

    private StaggeredTransformer() {
        super(Staggered.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "bites":
                for (int n = 0; n <= 6; n++) r.add(String.valueOf(n));
                break;
            case "charges":
                for (int n = 0; n <= 4; n++) r.add(String.valueOf(n));
                break;
            case "layers":
                for (int n = 1; n <= 8; n++) r.add(String.valueOf(n));
                break;
            case "level":
                switch (mat) {
                    case "COMPOSTER":
                        for (int n = 0; n <= 8; n++) r.add(String.valueOf(n));
                        break;
                    case "LAVA":
                    case "WATER":
                        for (int n = 0; n <= 7; n++) r.add(String.valueOf(n));
                        break;
                    case "CAULDRON":
                    case "POWDER_SNOW_CAULDRON":
                    case "WATER_CAULDRON":
                        for (int n = 1; n <= 3; n++) r.add(String.valueOf(n));
                        break;
                }
                break;
        }

        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();

        if (options.containsKey("bites")) r.addAll(options.get("bites").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("charges")) r.addAll(options.get("charges").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("layers")) r.addAll(options.get("layers").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("level")) r.addAll(options.get("level").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        int maxStage = Collections.max(list),
            minStage = Collections.min(list);
        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- STAGGERED INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate int stage;\n")
                .append("\tprivate final int maxStage, minStage;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Staggered setStage(int stage) throws IllegalArgumentException {\n")
                .append("\t\tif (amount < this.getMinStage() || amount > this.getMaxStage()) throw new IllegalArgumentException(\"" + className + " block only allows stages from \" + this.getMinStage() + \" to \" + this.getMaxStage());\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.stage = stage;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getStage() {\n")
                .append("\t\treturn this.stage;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getMaxStage() {\n")
                .append("\t\treturn this.maxStage;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getMinStage() {\n")
                .append("\t\treturn this.minStage;\n")
                .append("\t}\n");

        loadEval.add("this.stage = " + minStage + ";");
        loadEval.add("this.maxStage = " + maxStage + ";");
        loadEval.add("this.minStage = " + minStage + ";");
        copyProperties.add((var) -> "this.stage = " + var + ".stage;");
        listImplements.add("Staggered");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[STAGGERED_SOCKET_DATA_INDEX] = "(byte)(this.stage)";
    }

    @Override
    protected Staggered loadSocketData(Staggered b, int[] socketData) {
        int stage = socketData[STAGGERED_SOCKET_DATA_INDEX];
        return b.setStage(stage);
    }

    @Override
    public Staggered applyPropertiesToBlock(Staggered base, Map<String, String> arguments) {
        Integer stage = this.getSingle(((Block)base).getName(), arguments);
        if (stage != null) base = base.setStage(stage);
        return base;
    }

    @Override
    public String modifyBlockData(Staggered block, String blockData) {
        int stage = block.getStage();
        blockData = setBlockDataProperty(blockData, "bites", String.valueOf(stage));
        blockData = setBlockDataProperty(blockData, "charges", String.valueOf(stage));
        blockData = setBlockDataProperty(blockData, "layers", String.valueOf(stage));
        blockData = setBlockDataProperty(blockData, "level", String.valueOf(stage));
        return blockData;
    }
}
