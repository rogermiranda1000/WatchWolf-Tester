package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Directionable;

import java.util.*;

public class NetherPortal extends Block implements Directionable {
	/*   --- DIRECTIONABLE INTERFACE ---   */
	private Directionable.Direction direction;
	private final HashSet<Directionable.Direction> allowedDirections = new HashSet<>();
	public Directionable.Direction getFacingDirection() {
		return this.direction;
	}

	public Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException {
		if (!this.allowedDirections.contains(d)) throw new IllegalArgumentException("NetherPortal block doesn't allow the direction " + d.name());
		NetherPortal current = new NetherPortal(this);
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
	public NetherPortal(short id) {
		super(id, "NETHER_PORTAL");
		this.allowedDirections.add(Directionable.Direction.X);
		this.allowedDirections.add(Directionable.Direction.Z);
		this.direction = Directionable.Direction.X;
	}

	public NetherPortal(int id) {
		this((short) id);
	}

	private NetherPortal(NetherPortal old) {
		this(old.id);
		this.direction = old.direction;
	}

}