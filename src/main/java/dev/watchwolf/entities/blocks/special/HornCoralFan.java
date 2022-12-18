package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class HornCoralFan extends Block {

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
	public HornCoralFan(short id) {
		super(id, "HORN_CORAL_FAN");
	}

	public HornCoralFan(int id) {
		this((short) id);
	}

	private HornCoralFan(HornCoralFan old) {
		this(old.id);
	}

}