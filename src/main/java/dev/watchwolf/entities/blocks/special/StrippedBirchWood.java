package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Directionable;

import java.util.*;

public class StrippedBirchWood extends Block implements Directionable {
	/*   --- DIRECTIONABLE INTERFACE ---   */
	private Directionable.Direction direction;
	private final HashSet<Directionable.Direction> allowedDirections = new HashSet<>();
	public Directionable.Direction getFacingDirection() {
		return this.direction;
	}

	public Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException {
		if (!this.allowedDirections.contains(d)) throw new IllegalArgumentException("StrippedBirchWood block doesn't allow the direction " + d.name());
		StrippedBirchWood current = new StrippedBirchWood(this);
		current.direction = d;
		return current;
	}
	public Set<Directionable.Direction> getValidDirections() {
		return (HashSet)this.allowedDirections.clone();
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)(this.direction.getSendData() << 6));
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public StrippedBirchWood(short id) {
		super(id, "STRIPPED_BIRCH_WOOD");
		this.allowedDirections.add(Directionable.Direction.X);
		this.allowedDirections.add(Directionable.Direction.Y);
		this.allowedDirections.add(Directionable.Direction.Z);
		this.direction = Directionable.Direction.X;
	}

	public StrippedBirchWood(int id) {
		this((short) id);
	}

	private StrippedBirchWood(StrippedBirchWood old) {
		this(old.id);
		this.direction = old.direction;
	}

}