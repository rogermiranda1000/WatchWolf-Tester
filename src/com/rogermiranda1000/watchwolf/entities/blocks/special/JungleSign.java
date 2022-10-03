package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class JungleSign extends Block {

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