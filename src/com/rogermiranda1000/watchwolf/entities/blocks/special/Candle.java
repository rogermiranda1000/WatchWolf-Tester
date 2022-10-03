package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class Candle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Candle(short id) {
		super(id, "CANDLE");
	}

	public Candle(int id) {
		this((short) id);
	}

	private Candle(Candle old) {
		this(old.id);
	}

}