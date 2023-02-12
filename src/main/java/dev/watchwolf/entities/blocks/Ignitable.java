package dev.watchwolf.entities.blocks;

public interface Ignitable extends BlockModifier {
    boolean isIgnited();
    Ignitable setIgnited(boolean value);

    default Ignitable ignite() {
        return this.setIgnited(true);
    }
    default Ignitable extinguish() {
        return this.setIgnited(false);
    }
}
