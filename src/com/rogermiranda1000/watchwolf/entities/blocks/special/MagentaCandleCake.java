package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MagentaCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MagentaCandleCake(short id) {
		super(id, "MAGENTA_CANDLE_CAKE");
	}

	public MagentaCandleCake(int id) {
		this((short) id);
	}

	private MagentaCandleCake(MagentaCandleCake old) {
		this(old.id);
	}

}