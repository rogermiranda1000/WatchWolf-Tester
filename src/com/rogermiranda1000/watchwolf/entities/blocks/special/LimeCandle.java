package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LimeCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LimeCandle(short id) {
		super(id, "LIME_CANDLE");
	}

	public LimeCandle(int id) {
		this((short) id);
	}

	private LimeCandle(LimeCandle old) {
		this(old.id);
	}

}