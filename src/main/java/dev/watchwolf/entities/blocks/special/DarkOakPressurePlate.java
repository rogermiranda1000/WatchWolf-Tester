package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DarkOakPressurePlate extends Block {

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
	public DarkOakPressurePlate(short id) {
		super(id, "DARK_OAK_PRESSURE_PLATE");
	}

	public DarkOakPressurePlate(int id) {
		this((short) id);
	}

	private DarkOakPressurePlate(DarkOakPressurePlate old) {
		this(old.id);
	}

}