package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class Bell extends Block implements Orientable, Directionable, Powerable {
	/*   --- ORIENTABLE INTERFACE ---   */
	@RelevantBlockData
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	@Override
	public boolean isOrientationSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		return result;
	}
	@Override

	public Orientable setOrientation(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		Bell current = new Bell(this);
		current.orientation.put(o, value);
		return current;
	}
	@Override

	public Set<Orientable.Orientation> getValidOrientations() {
		return this.orientation.keySet();
	}
	/*   --- DIRECTIONABLE INTERFACE ---   */
	@RelevantBlockData
	private Directionable.Direction direction;
	private final HashSet<Directionable.Direction> allowedDirections = new HashSet<>();
	@Override
	public Directionable.Direction getFacingDirection() {
		return this.direction;
	}
	@Override

	public Directionable setDirection(Directionable.Direction d) throws IllegalArgumentException {
		if (!this.allowedDirections.contains(d)) throw new IllegalArgumentException("Bell block doesn't allow the direction " + d.name());
		Bell current = new Bell(this);
		current.direction = d;
		return current;
	}
	@Override
	public Set<Directionable.Direction> getValidDirections() {
		return (HashSet)this.allowedDirections.clone();
	}
	/*   --- POWERABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean powered;
	@Override
	public boolean isPowered() {
		return this.powered;
	}
	@Override

	public Powerable setPowered(boolean val) {
		Bell current = new Bell(this);
		current.powered = val;
		return current;
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)(0)); // age
		out.add((byte)((byte)((Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.U)) ? 0b00_000001 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.D)) ? 0b00_000010 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.N)) ? 0b00_000100 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.S)) ? 0b00_001000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.E)) ? 0b00_010000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.W)) ? 0b00_100000 : 0x00)) |
				(byte)(this.direction.getSendData() << 6))); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)(0)); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.powered ? 0b00000_1_0_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public Bell(short id) {
		super(id, "BELL", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.orientation.put(Orientable.Orientation.E, false);
		this.orientation.put(Orientable.Orientation.W, false);
		this.orientation.put(Orientable.Orientation.S, false);
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.D, false);
		this.orientation.put(Orientable.Orientation.N, false);
		this.allowedDirections.add(Directionable.Direction.DOUBLE_WALL);
		this.allowedDirections.add(Directionable.Direction.NONE);
		this.allowedDirections.add(Directionable.Direction.SINGLE_WALL);
		this.direction = Directionable.Direction.DOUBLE_WALL;
		this.powered = false;
	}

	public Bell(int id) {
		this((short) id);
	}

	private Bell(Bell old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
		this.direction = old.direction;
		this.powered = old.powered;
	}

}