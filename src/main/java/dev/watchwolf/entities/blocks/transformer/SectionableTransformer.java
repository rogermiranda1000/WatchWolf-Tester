package dev.watchwolf.entities.blocks.transformer;

import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Directionable;
import dev.watchwolf.entities.blocks.Sectionable;

import java.util.*;
import java.util.function.Function;

public class SectionableTransformer extends AbstractTransformer<Sectionable,Sectionable.Section> {
    private static final int SECTIONABLE_SOCKET_DATA_INDEX = 7;

    public SectionableTransformer() {
        super(Sectionable.class);
    }

    @Override
    public List<String> getOptions(String mat, String argument) {
        List<String> r = new ArrayList<>();

        switch (argument) {
            case "part":
                r.add("foot");
                r.add("head");
                break;
            case "extended":
                r.add("true");
                r.add("false");
                break;
            case "shape":
                if (!mat.endsWith("RAIL")) {
                    r.add("straight");
                    r.add("inner_right");
                    r.add("inner_left");
                    r.add("outer_right");
                    r.add("outer_left");
                }
                break;
        }

        return r;
    }

    @Override
    public Collection<Sectionable.Section> get(String mat, HashMap<String, List<String>> options) {
        Collection<Sectionable.Section> r = new HashSet<>();

        if (options.containsKey("part")) {
            r.add(Sectionable.Section.FOOT);
            r.add(Sectionable.Section.HEAD);
        }
        if (options.containsKey("extended")) {
            r.add(Sectionable.Section.EXTENDED_PISTON);
            r.add(Sectionable.Section.PISTON);
        }
        if (options.containsKey("shape")) {
            r.add(Sectionable.Section.STRAIGHT);
            r.add(Sectionable.Section.INNER_RIGHT);
            r.add(Sectionable.Section.INNER_LEFT);
            r.add(Sectionable.Section.OUTER_RIGHT);
            r.add(Sectionable.Section.OUTER_LEFT);
        }

        return r;
    }

    @Override
    public String getImplementation(String className, Collection<Sectionable.Section> list, List<String> listImplements, List<String> loadEval, List<Function<String, String>> copyProperties, String[] socketData) {
        if (!this.applies(list)) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\t/*   --- DIRECTIONABLE INTERFACE ---   */\n");
        sb.append("\t@RelevantBlockData\n")
                .append("\tprivate Sectionable.Section section;\n")
                .append("\tprivate final HashSet<Sectionable.Section> allowedSections = new HashSet<>();\n");

        sb.append("\t@Override\n")
                .append("\tpublic Sectionable.Section getSection() {\n")
                .append("\t\treturn this.section;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\n\tpublic Sectionable setSection(Sectionable.Section section) throws IllegalArgumentException {\n")
                .append("\t\tif (!this.allowedSections.contains(section)) throw new IllegalArgumentException(\"" + className + " block doesn't allow the section \" + section.name());\n")
                .append("\t\t" + className + " current = new " + className + "(this);\n")
                .append("\t\tcurrent.section = section;\n")
                .append("\t\treturn current;\n")
                .append("\t}\n");

        sb.append("\t@Override\n")
                .append("\tpublic Set<Sectionable.Section> getValidSections() {\n")
                .append("\t\treturn (HashSet)this.allowedSections.clone();\n")
                .append("\t}\n");

        for (Sectionable.Section section : list) loadEval.add("this.allowedSections.add(Sectionable.Section." + section.name() + ");");
        loadEval.add("this.section = Sectionable.Section." + list.stream().findFirst().orElseThrow(() -> new RuntimeException("Sectionable block without any section")) + ";");
        copyProperties.add((var) -> "this.section = " + var + ".section;");
        listImplements.add("Sectionable");

        this.getSocketData(socketData);

        return sb.toString();
    }

    @Override
    protected void getSocketData(String[] socketData) {
        socketData[SECTIONABLE_SOCKET_DATA_INDEX] = " |\n\t\t\t\t(byte)(this.section.getSendData() << 4)";
    }

    @Override
    protected Sectionable loadSocketData(Sectionable sectionable, int[] socketData) {
        int tmp = (socketData[SECTIONABLE_SOCKET_DATA_INDEX] >> 4);
        try {
            switch (tmp) {
                case 0:
                    sectionable = sectionable.setSection(Sectionable.Section.FOOT);
                    break;
                case 1:
                    sectionable = sectionable.setSection(Sectionable.Section.HEAD);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        try {
            switch (tmp) {
                case 0:
                    sectionable = sectionable.setSection(Sectionable.Section.PISTON);
                    break;
                case 1:
                    sectionable = sectionable.setSection(Sectionable.Section.EXTENDED_PISTON);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        try {
            switch (tmp) {
                case 0:
                    sectionable = sectionable.setSection(Sectionable.Section.STRAIGHT);
                    break;
                case 1:
                    sectionable = sectionable.setSection(Sectionable.Section.INNER_RIGHT);
                    break;
                case 2:
                    sectionable = sectionable.setSection(Sectionable.Section.INNER_LEFT);
                    break;
                case 3:
                    sectionable = sectionable.setSection(Sectionable.Section.OUTER_RIGHT);
                    break;
                case 4:
                    sectionable = sectionable.setSection(Sectionable.Section.OUTER_LEFT);
                    break;
            }
        } catch (IllegalArgumentException ignore) {}
        return sectionable;
    }

    @Override
    public Sectionable applyPropertyToBlock(Sectionable base, Map<String, String> arguments) {
        Sectionable.Section section = this.getSingle(((Block)base).getName(), arguments);
        if (section != null) base = base.setSection(section);
        return base;
    }

    @Override
    public String modifyBlockData(Sectionable block, String blockData) {
        Sectionable.Section current = block.getSection();
        if (current == Sectionable.Section.FOOT) blockData = setBlockDataProperty(blockData, "part", "foot");
        else if (current == Sectionable.Section.HEAD) blockData = setBlockDataProperty(blockData, "part", "head");
        else if (current == Sectionable.Section.PISTON) blockData = setBlockDataProperty(blockData, "extended", "false");
        else if (current == Sectionable.Section.EXTENDED_PISTON) blockData = setBlockDataProperty(blockData, "extended", "true");
        else blockData = setBlockDataProperty(blockData, "shape", current.name().toLowerCase()); // straight/inner_right/...
        return blockData;
    }
}
