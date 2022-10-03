package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightGrayCandle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightGrayCandle(short id) {
		super(id, "LIGHT_GRAY_CANDLE");
	}

	public LightGrayCandle(int id) {
		this((short) id);
	}

	private LightGrayCandle(LightGrayCandle old) {
		this(old.id);
	}

}