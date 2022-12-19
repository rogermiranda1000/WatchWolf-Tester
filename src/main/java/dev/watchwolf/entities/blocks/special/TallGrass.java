package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;
import dev.watchwolf.entities.blocks.Orientable;

import java.util.*;

public class TallGrass extends Block implements Orientable {
	/*   --- ORIENTABLE INTERFACE ---   */
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	public boolean isOrientationSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		if (result == null) throw new IllegalArgumentException("TallGrass block doesn't contain orientation " + o.name());
		return result;
	}

	public Orientable setOrientation(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		if (!this.orientation.containsKey(o)) throw new IllegalArgumentException("TallGrass block doesn't contain orientation " + o.name());
		TallGrass current = new TallGrass(this);
		current.orientation.put(o, value);
		return current;
	}

	public Orientable setOrientation(Orientable.Orientation o) throws IllegalArgumentException {
		return this.setOrientation(o, true);
	}

	public Orientable unsetOrientation(Orientable.Orientation o) throws IllegalArgumentException {
		return this.setOrientation(o, false);
	}

	public Set<Orientable.Orientation> getValidOrientations() {
		return orientation.keySet();
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)((Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.U)) ? 0b00_000001 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.D)) ? 0b00_000010 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.N)) ? 0b00_000100 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.S)) ? 0b00_001000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.E)) ? 0b00_010000 : 0x00) |
				(Boolean.TRUE.equals(this.orientation.get(Orientable.Orientation.W)) ? 0b00_100000 : 0x00)));
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public TallGrass(short id) {
		super(id, "TALL_GRASS");
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.D, false);
	}

	public TallGrass(int id) {
		this((short) id);
	}

	private TallGrass(TallGrass old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
	}

}