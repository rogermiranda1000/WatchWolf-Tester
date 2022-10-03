package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MagentaCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MagentaCandle(short id) {
		super(id, "MAGENTA_CANDLE");
	}

	public MagentaCandle(int id) {
		this((short) id);
	}

	private MagentaCandle(MagentaCandle old) {
		this(old.id);
	}

}