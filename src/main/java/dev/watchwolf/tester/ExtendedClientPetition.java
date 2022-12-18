package dev.watchwolf.tester;

import dev.watchwolf.client.ClientPetition;
import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.Container;

import java.io.IOException;

/**
 * There's some petitions player-related that needs to be launched in the server-side.
 * The interface will "redirect" the petition, thus being invisible to the user.
 */
public interface ExtendedClientPetition extends ClientPetition {
    public Position getPosition() throws IOException;
    public float getPitch() throws IOException;
    public float getYaw() throws IOException;
    public Container getInventory() throws IOException;

    /**
     * Instead of calculating the pitch&yaw, just specify the target position
     * TODO the author said that the yaw is not correct
     * @ref https://www.spigotmc.org/threads/make-a-player-look-at-specific-block.492925/
     * @param targetPosition Place to look
     * @throws IOException Socket error
     */
    default public void lookAt(Position targetPosition) throws IOException {
        Position currentPosition = this.getPosition();

        double dx = targetPosition.getX() - currentPosition.getX();
        double dy = targetPosition.getY() - currentPosition.getY();
        double dz = targetPosition.getZ() - currentPosition.getZ();
        double pitch, yaw = this.getYaw(); // TODO is getting the yaw necessary?

        // Set yaw
        if (dx != 0) {
            if (dx < 0) {
                yaw = (1.5f * Math.PI);
            } else {
                yaw = (0.5f * Math.PI);
            }
            yaw -= Math.atan(dz / dx);
        } else if (dz < 0) {
            yaw = Math.PI;
        }

        double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
        pitch = -Math.atan(dy / dxz);
        yaw = -yaw * 180f / Math.PI;
        pitch *= 180f / Math.PI;

        this.lookAt((float)pitch, (float)yaw);
    }
}
