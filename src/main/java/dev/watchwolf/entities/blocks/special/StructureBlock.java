package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class StructureBlock extends Block implements Stateful {
	/*   --- STATEFUL INTERFACE ---   */
	@RelevantBlockData
	private Stateful.Mode state;
	private final HashSet<Stateful.Mode> allowedStates = new HashSet<>();
	@Override
	public Stateful.Mode getMode() {
		return this.state;
	}
	@Override

	public Stateful setMode(Stateful.Mode mode) throws IllegalArgumentException {
		if (!this.allowedStates.contains(mode)) throw new IllegalArgumentException("StructureBlock block doesn't allow the state " + mode.name());
		StructureBlock current = new StructureBlock(this);
		current.state = mode;
		return current;
	}
	@Override
	public Set<Stateful.Mode> getValidModes() {
		return (HashSet)this.allowedStates.clone();
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)(0)); // age
		out.add((byte)(0)); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)(0)); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)((byte)(this.state.getSendData() << 4))); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)(0)); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public StructureBlock(short id) {
		super(id, "STRUCTURE_BLOCK", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.allowedStates.add(Stateful.Mode.SAVE);
		this.allowedStates.add(Stateful.Mode.CORNER);
		this.allowedStates.add(Stateful.Mode.LOAD);
		this.state = Stateful.Mode.SAVE;
	}

	public StructureBlock(int id) {
		this((short) id);
	}

	private StructureBlock(StructureBlock old) {
		this(old.id);
		this.state = old.state;
	}

}