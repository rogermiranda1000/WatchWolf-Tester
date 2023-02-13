package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Ageable;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AgeableTransformer extends AbstractTransformer<Ageable,Integer> {
    private static final int AGEABLE_SOCKET_DATA_INDEX = 2;

    private static AgeableTransformer instance = null;

    public static AgeableTransformer getInstance() {
        if (AgeableTransformer.instance == null) AgeableTransformer.instance = new AgeableTransformer();
        return AgeableTransformer.instance;
    }

    private AgeableTransformer() {
        super(Ageable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();
        switch (argument) {
            case "age":
                switch (mat) {
                    case "BEETROOTS":
                    case "FROSTED_ICE":
                    case "NETHER_WART":
                    case "SWEET_BERRY_BUSH":
                        for (int n = 0; n <= 3; n++) r.add(String.valueOf(n));
                        break;
                    case "CHORUS_FLOWER":
                        for (int n = 0; n <= 5; n++) r.add(String.valueOf(n));
                        break;
                    case "BAMBOO":
                        for (int n = 0; n <= 1; n++) r.add(String.valueOf(n));
                        break;
                    case "CARROTS":
                    case "WHEAT":
                    case "PUMPKIN_STEM":
                    case "POTATOES":
                    case "MELON_STEM":
                        for (int n = 0; n <= 7; n++) r.add(String.valueOf(n));
                        break;
                    case "COCOA":
                        for (int n = 0; n <= 2; n++) r.add(String.valueOf(n));
                        break;
                }
                break;
            case "berries":
                r.add("true");
                r.add("false");
                break;
            case "honey-level":
                for (int n = 0; n <= 5; n++) r.add(String.valueOf(n));
                break;
        }
        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();
        if (options.containsKey("age")) r.addAll(options.get("age").stream().map(Integer::valueOf).collect(Collectors.toList()));
        if (options.containsKey("berries")) {
            r.add(0);
            r.add(1);
        }
        if (options.containsKey("honey-level")) r.addAll(options.get("age").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        int maxAge = Collections.max(list);
        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- AGEABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate int age;\n")
                .append("\tprivate final int maxAge;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Ageable setAge(int age) throws IllegalArgumentException {\n")
                .append("\t\tif (age > this.getMaxAge()) throw new IllegalArgumentException(\"" + className + " block only allows age from 0 to \" + this.getMaxAge());\n")
                .append("\t\tthis.age = age;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getAge() {\n")
                .append("\t\treturn this.age;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getMaxAge() {\n")
                .append("\t\treturn this.maxAge;\n")
                .append("\t}\n");

        loadEval.add("this.age = 0;");
        loadEval.add("this.maxAge = " + maxAge + ";");
        copyProperties.add((var) -> "this.age = " + var + ".age;");
        listImplements.add("Ageable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[AGEABLE_SOCKET_DATA_INDEX] = "(byte)this.age";
    }

    @Override
    protected Ageable loadSocketData(Ageable b, int[] socketData) {
        int age = socketData[AgeableTransformer.AGEABLE_SOCKET_DATA_INDEX];
        return b.setAge(age);
    }

    @Override
    public Ageable applyPropertiesToBlock(Ageable base, Map<String, String> arguments) {
        Integer age = this.getSingle(((Block)base).getName(), arguments);
        if (age != null) base = base.setAge(age);
        return base;
    }

    @Override
    public String modifyBlockData(Ageable block, String blockData) {
        int age = block.getAge();
        blockData = setBlockDataProperty(blockData, "age", String.valueOf(age));
        blockData = setBlockDataProperty(blockData, "honey-level", String.valueOf(age));
        blockData = setBlockDataProperty(blockData, "berries", (age == 0) ? "false" : "true");
        return blockData;
    }
}
