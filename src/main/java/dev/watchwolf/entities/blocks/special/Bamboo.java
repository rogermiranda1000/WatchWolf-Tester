package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import java.util.*;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public class Bamboo extends Block implements Ageable, Leaved {
	/*   --- AGEABLE INTERFACE ---   */
	@RelevantBlockData
	private int age;
	private final int maxAge;
	@Override

	public Ageable setAge(int age) throws IllegalArgumentException {
		if (age > this.getMaxAge()) throw new IllegalArgumentException("Bamboo block only allows age from 0 to " + this.getMaxAge());
		Bamboo current = new Bamboo(this);
		current.age = age;
		return current;
	}
	@Override

	public int getAge() {
		return this.age;
	}
	@Override

	public int getMaxAge() {
		return this.maxAge;
	}
	/*   --- LEAVED INTERFACE ---   */
	@RelevantBlockData
	private Leaved.Leaves leaves;
	@Override
	public Leaved.Leaves getLeaves() {
		return this.leaves;
	}
	@Override

	public Leaved setLeaves(Leaved.Leaves l) {
		Bamboo current = new Bamboo(this);
		current.leaves = leaves;
		return current;
	}

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)((byte)this.age)); // age
		out.add((byte)(0)); // directionable & orientable
		out.add((byte)0); // reserved
		out.add((byte)(0)); // group_count & delay & eye & hinge & open
		out.add((byte)(0)); // stage
		out.add((byte)(0)); // part & rotation
		out.add((byte)(0)); // note
		out.add((byte)((byte)(this.leaves.ordinal() << 2))); // mode & leaves & lit & locked
		out.add((byte)0); // reserved
		out.add((byte)(0)); // cond & inv & pow
		SocketHelper.fill(out, 44); // reserved
	}

	/*   --- CONSTRUCTORS ---   */
	public Bamboo(short id) {
		super(id, "BAMBOO", (ins, f) -> {
			try { return f.get(ins).toString(); }
			catch (IllegalAccessException ignore) { return "?"; }
		});
		this.age = 0;
		this.maxAge = 1;
		this.leaves = Leaved.Leaves.NONE;
	}

	public Bamboo(int id) {
		this((short) id);
	}

	private Bamboo(Bamboo old) {
		this(old.id);
		this.age = old.age;
		this.leaves = old.leaves;
	}

}