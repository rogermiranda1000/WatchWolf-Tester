package dev.watchwolf.entities.blocks;

/**
 * eye
 */
public interface Eyeable extends BlockModifier {
    boolean isEyePlaced();
    Eyeable setEyePlaced(boolean placed);

    default public Eyeable setEyePlaced() {
        return this.setEyePlaced(true);
    }

    default public Eyeable clearEyePlaced() {
        return this.setEyePlaced(false);
    }
}
