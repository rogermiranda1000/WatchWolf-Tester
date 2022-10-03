package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WhiteCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WhiteCandle(short id) {
		super(id, "WHITE_CANDLE");
	}

	public WhiteCandle(int id) {
		this((short) id);
	}

	private WhiteCandle(WhiteCandle old) {
		this(old.id);
	}

}