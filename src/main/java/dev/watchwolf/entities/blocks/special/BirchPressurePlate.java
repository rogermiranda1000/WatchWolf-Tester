package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class BirchPressurePlate extends Block {

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
	public BirchPressurePlate(short id) {
		super(id, "BIRCH_PRESSURE_PLATE");
	}

	public BirchPressurePlate(int id) {
		this((short) id);
	}

	private BirchPressurePlate(BirchPressurePlate old) {
		this(old.id);
	}

}