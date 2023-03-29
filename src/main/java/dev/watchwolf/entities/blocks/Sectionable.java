package dev.watchwolf.entities.blocks;

import java.util.Set;

/**
 * part/extended/stair_shape
 */
public interface Sectionable extends BlockModifier {
    public static enum Section {
        FOOT(0), HEAD(1),
        PISTON(0), EXTENDED_PISTON(1),
        STRAIGHT(0), INNER_RIGHT(1), INNER_LEFT(2), OUTER_RIGHT(3), OUTER_LEFT(4);
        // TODO add door part (top/bottom)

        private final byte send;
        private Section(int send) {
            this.send = (byte) send;
        }

        public byte getSendData() {
            return this.send;
        }
    }

    Section getSection();
    Sectionable setSection(Section section) throws IllegalArgumentException;
    Set<Section> getValidSections();
}
