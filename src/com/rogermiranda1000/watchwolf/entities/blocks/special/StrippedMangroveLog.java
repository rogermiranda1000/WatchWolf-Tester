package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class StrippedMangroveLog extends Block implements Directionable {
	/*   --- DIRECTIONABLE INTERFACE ---   */
	private Directionable.Direction direction;
	private final HashSet<Directionable.Direction> allowedDirections = new HashSet<>();
	public Directionable.Direction getFacingDirection() {
		return this.direction;
	}

	public Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException {
		if (!this.allowedDirections.contains(d)) throw new IllegalArgumentException("StrippedMangroveLog block doesn't allow the direction " + d.name());
		StrippedMangroveLog current = new StrippedMangroveLog(this);
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
	public StrippedMangroveLog(short id) {
		super(id, "STRIPPED_MANGROVE_LOG");
		this.allowedDirections.add(Directionable.Direction.X);
		this.allowedDirections.add(Directionable.Direction.Y);
		this.allowedDirections.add(Directionable.Direction.Z);
		this.direction = Directionable.Direction.X;
	}

	public StrippedMangroveLog(int id) {
		this((short) id);
	}

	private StrippedMangroveLog(StrippedMangroveLog old) {
		this(old.id);
		this.direction = old.direction;
	}

}