package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class DarkOakFenceGate extends Block implements Orientable, Openable, Powerable {
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
		DarkOakFenceGate current = new DarkOakFenceGate(this);
		current.orientation.put(o, value);
		return current;
	}
	@Override

	public Set<Orientable.Orientation> getValidOrientations() {
		return this.orientation.keySet();
	}
	/*   --- OPENABLE INTERFACE ---   */
	@RelevantBlockData
	private boolean isOpen;
	@Override

	public Openable setOpened(boolean opened) {
		DarkOakFenceGate current = new DarkOakFenceGate(this);
		current.isOpen = opened;
		return current;
	}
	@Override

	public boolean isOpened() {
		return this.isOpen;
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
		DarkOakFenceGate current = new DarkOakFenceGate(this);
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
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.W)) ? 0b00_100000 : 0x00)))); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.isOpen ? 1 : 0))); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)(0)); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)((byte)(this.powered ? 0b00000_1_0_0 : 0))); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public DarkOakFenceGate(short id) {
		super(id, "DARK_OAK_FENCE_GATE", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.orientation.put(Orientable.Orientation.E, false);
		this.orientation.put(Orientable.Orientation.W, false);
		this.orientation.put(Orientable.Orientation.S, false);
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.D, false);
		this.orientation.put(Orientable.Orientation.N, false);
		this.isOpen = false;
		this.powered = false;
	}

	public DarkOakFenceGate(int id) {
		this((short) id);
	}

	private DarkOakFenceGate(DarkOakFenceGate old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
		this.isOpen = old.isOpen;
		this.powered = old.powered;
	}

}