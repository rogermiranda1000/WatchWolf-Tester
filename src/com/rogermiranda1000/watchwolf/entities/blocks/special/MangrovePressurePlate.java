package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangrovePressurePlate extends Block {

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)0);
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public MangrovePressurePlate(short id) {
		super(id, "MANGROVE_PRESSURE_PLATE");
	}

	public MangrovePressurePlate(int id) {
		this((short) id);
	}

	private MangrovePressurePlate(MangrovePressurePlate old) {
		this(old.id);
	}

}