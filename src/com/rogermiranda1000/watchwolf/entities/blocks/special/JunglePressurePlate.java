package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JunglePressurePlate extends Block {

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
	public JunglePressurePlate(short id) {
		super(id, "JUNGLE_PRESSURE_PLATE");
	}

	public JunglePressurePlate(int id) {
		this((short) id);
	}

	private JunglePressurePlate(JunglePressurePlate old) {
		this(old.id);
	}

}