package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CyanCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CyanCandle(short id) {
		super(id, "CYAN_CANDLE");
	}

	public CyanCandle(int id) {
		this((short) id);
	}

	private CyanCandle(CyanCandle old) {
		this(old.id);
	}

}