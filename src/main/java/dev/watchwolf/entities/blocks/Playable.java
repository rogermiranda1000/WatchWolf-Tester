package dev.watchwolf.entities.blocks;

/**
 * note
 */
public interface Playable extends BlockModifier {
    int getNote();

    /**
     * Set the noteblock note
     * @param note Note to play (0-24)
     * @return Block with the modified note
     * @throws IllegalArgumentException note less tan 0 or more than 24
     */
    Playable setNote(int note) throws IllegalArgumentException;
}
