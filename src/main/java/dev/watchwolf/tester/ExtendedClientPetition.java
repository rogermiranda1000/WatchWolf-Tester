package dev.watchwolf.tester;

import dev.watchwolf.client.ClientPetition;
import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.Container;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.items.Item;
import dev.watchwolf.entities.items.ItemNotFoundInContainerException;
import dev.watchwolf.entities.items.ItemType;

import java.io.IOException;
import java.util.Arrays;

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
     * Instead of calculating the pitch and yaw, just specify the target position
     * TODO the author said that the yaw is not correct
     * @see <a href="https://www.spigotmc.org/threads/make-a-player-look-at-specific-block.492925/">SpigotMC - Make a player look at specific block</a>
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

    /**
     * Set a block from your inventory
     * @param item Block to set
     * @param pos Where to place the block
     * @throws IOException Socket error
     */
    default public void setBlock(Item item, Position pos) throws IOException {
        this.equipItemInHand(item);
        this.setBlock(pos);
    }

    /**
     * Set a block from your inventory
     * @param block Block to set
     * @param pos Where to place the block
     * @throws IOException Socket error
     * @throws ItemNotFoundInContainerException The specified item couldn't be found on player's inventory
     */
    default public void setBlock(final ItemType block, Position pos) throws IOException, ItemNotFoundInContainerException {
        Item i = Arrays.stream(this.getInventory().getItems()).filter(it -> it.getType().equals(block)).findFirst().orElseThrow(() -> new ItemNotFoundInContainerException(block.name() + " not found in player's inventory."));
        this.equipItemInHand(i);
        this.setBlock(pos);
    }

    /**
     * Set a block from your inventory
     * @param block Block to set
     * @param pos Where to place the block
     * @throws IOException Socket error
     * @throws ItemNotFoundInContainerException The specified item couldn't be found on player's inventory
     */
    default public void setBlock(Block block, Position pos) throws IOException, ItemNotFoundInContainerException {
        this.setBlock(block.getItemType(), pos);
    }

    default public void attack(Entity e) throws IOException {
        this.attack(e.getUUID());
    }
}
