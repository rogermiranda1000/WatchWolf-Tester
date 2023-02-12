package dev.watchwolf.entities.blocks;

/**
 * delay
 */
public interface Delayable extends BlockModifier {
    /**
     * Sets the delay of a repeater
     * @param delay Ticks to delay (1-4)
     * @return The new block with the delay changed
     * @throws IllegalArgumentException `delay` less than 1 or more than 4
     */
    Delayable setDelay(int delay) throws IllegalArgumentException;

    int getDelay();
}
