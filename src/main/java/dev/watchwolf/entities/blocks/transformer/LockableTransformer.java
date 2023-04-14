package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Lockable;

import java.util.*;
import java.util.function.Function;

public class LockableTransformer extends AbstractTransformer<Lockable,Boolean> {
    private static final int LOCKABLE_SOCKET_DATA_INDEX = 9;

    public LockableTransformer() {
        super(Lockable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        if (argument.equals("locked")) {
            r.add("true");
            r.add("false");
        }

        return r;
    }

    @Override
    public Collection<Boolean> get(String mat, HashMap<String, List<String>> options) {
        Collection<Boolean> r = new HashSet<>();

        if (options.containsKey("locked")) {
            r.add(true);
            r.add(false);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Boolean> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- IGNITABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate boolean locked;\n");

        sb.append("\t@Override\n")
                .append("\tpublic boolean isLocked() {\n")
                .append("\t\treturn this.locked;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Lockable setLocked(boolean val) {\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.locked = val;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        loadEval.add("this.locked = false;");
        copyProperties.add((var) -> "this.locked = " + var + ".locked;");
        listImplements.add("Lockable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[LOCKABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.locked ? 0b0000_00_0_1 : 0)";
    }

    @Override
    protected Lockable loadSocketData(Lockable b, int[] socketData) {
        boolean locked = (socketData[LOCKABLE_SOCKET_DATA_INDEX] & 0b0000_00_0_1) > 0;
        return b.setLocked(locked);
    }

    @Override
    public Lockable applyPropertyToBlock(Lockable base, Map<String, String> arguments) {
        Boolean locked = this.getSingle(((Block)base).getName(), arguments);
        if (locked != null) base = base.setLocked(locked);
        return base;
    }

    @Override
    public String modifyBlockData(Lockable block, String blockData) {
        boolean current = block.isLocked();
        return setBlockDataProperty(blockData, "locked", String.valueOf(current));
    }
}
