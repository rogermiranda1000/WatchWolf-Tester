package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PolishedDioriteSlab extends Block implements Orientable {
	/*   --- ORIENTABLE INTERFACE ---   */
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	public boolean isSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		if (result == null) throw new IllegalArgumentException("PolishedDioriteSlab block doesn't contain orientation " + o.name());
		return result;
	}

	public Orientable set(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		if (!this.orientation.containsKey(o)) throw new IllegalArgumentException("PolishedDioriteSlab block doesn't contain orientation " + o.name());
		PolishedDioriteSlab current = new PolishedDioriteSlab(this);
		current.orientation.put(o, value);
		return current;
	}

	public Orientable set(Orientable.Orientation o) throws IllegalArgumentException {
		return this.set(o, true);
	}

	public Orientable unset(Orientable.Orientation o) throws IllegalArgumentException {
		return this.set(o, false);
	}

	public Set<Orientable.Orientation> getValidOrientations() {
		return orientation.keySet();
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)(((this.orientation.get(Orientable.Orientation.U) == true) ? 0b100000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.D) == true) ? 0b010000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.N) == true) ? 0b001000_00 : 0x00) |((this.orientation.get(Orientable.Orientation.S) == true) ? 0b000100_00 : 0x00) |((this.orientation.get(Orientable.Orientation.E) == true) ? 0b000010_00 : 0x00) |((this.orientation.get(Orientable.Orientation.W) == true) ? 0b000001_00 : 0x00)));
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public PolishedDioriteSlab(short id) {
		super(id, "POLISHED_DIORITE_SLAB");
		this.orientation.put(Orientable.Orientation.D, false);
		this.orientation.put(Orientable.Orientation.U, false);
	}

	public PolishedDioriteSlab(int id) {
		this((short) id);
	}

	private PolishedDioriteSlab(PolishedDioriteSlab old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
	}

}