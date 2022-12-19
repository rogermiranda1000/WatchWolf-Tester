package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class JungleSign extends Block {

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
	public JungleSign(short id) {
		super(id, "JUNGLE_SIGN");
	}

	public JungleSign(int id) {
		this((short) id);
	}

	private JungleSign(JungleSign old) {
		this(old.id);
	}

}