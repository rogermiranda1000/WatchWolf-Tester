package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Playable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayableTransformer extends AbstractTransformer<Playable,Integer> {
    private static final int PLAYABLE_SOCKET_DATA_INDEX = 8;

    private static PlayableTransformer instance = null;

    public static PlayableTransformer getInstance() {
        if (PlayableTransformer.instance == null) PlayableTransformer.instance = new PlayableTransformer();
        return PlayableTransformer.instance;
    }

    private PlayableTransformer() {
        super(Playable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "note":
                for (int n = 0; n <= 24; n++) r.add(String.valueOf(n));
                break;
        }

        return r;
    }

    @Override
    public Collection<Integer> get(String mat, HashMap<String, List<String>> options) {
        Collection<Integer> r = new HashSet<>();

        if (options.containsKey("note")) r.addAll(options.get("note").stream().map(Integer::valueOf).collect(Collectors.toList()));

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Integer> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- PLAYABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate int note;\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Playable setNote(int note) throws IllegalArgumentException {\n")
                .append("\t\tif (note < 0 || note > 24) throw new IllegalArgumentException(\"" + className + " block allows notes from 0 to 24\");\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.note = note;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic int getNote() {\n")
                .append("\t\treturn this.note;\n")
                .append("\t}\n");

        loadEval.add("this.note = 0;");
        copyProperties.add((var) -> "this.note = " + var + ".note;");
        listImplements.add("Playable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[PLAYABLE_SOCKET_DATA_INDEX] = "(byte)(this.note)";
    }

    @Override
    protected Playable loadSocketData(Playable b, int[] socketData) {
        int note = socketData[PLAYABLE_SOCKET_DATA_INDEX];
        return b.setNote(note);
    }

    @Override
    public Playable applyPropertiesToBlock(Playable base, Map<String, String> arguments) {
        Integer note = this.getSingle(((Block)base).getName(), arguments);
        if (note != null) base = base.setNote(note);
        return base;
    }

    @Override
    public String modifyBlockData(Playable block, String blockData) {
        int note = block.getNote();
        return setBlockDataProperty(blockData, "note", String.valueOf(note));
    }
}
