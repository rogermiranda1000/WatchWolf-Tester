package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class SpruceSlab extends Block implements Orientable {
	/*   --- ORIENTABLE INTERFACE ---   */
	private final Map<Orientable.Orientation,Boolean> orientation = new HashMap<>();
	public boolean isSet(Orientable.Orientation o) throws IllegalArgumentException {
		Boolean result = this.orientation.get(o);
		if (result == null) throw new IllegalArgumentException("SpruceSlab block doesn't contain orientation " + o.name());
		return result;
	}

	public Orientable set(Orientable.Orientation o, boolean value) throws IllegalArgumentException {
		if (!this.orientation.containsKey(o)) throw new IllegalArgumentException("SpruceSlab block doesn't contain orientation " + o.name());
		SpruceSlab current = new SpruceSlab(this);
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

	/*   --- CONSTRUCTORS ---   */
	public SpruceSlab(int id) {
		super(id, "SpruceSlab");
		this.orientation.put(Orientable.Orientation.U, false);
		this.orientation.put(Orientable.Orientation.D, false);
	}

	private SpruceSlab(SpruceSlab old) {
		this(old.id);
		this.orientation.putAll(old.orientation);
	}

}