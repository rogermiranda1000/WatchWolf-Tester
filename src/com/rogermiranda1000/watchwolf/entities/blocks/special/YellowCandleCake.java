package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class YellowCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public YellowCandleCake(short id) {
		super(id, "YELLOW_CANDLE_CAKE");
	}

	public YellowCandleCake(int id) {
		this((short) id);
	}

	private YellowCandleCake(YellowCandleCake old) {
		this(old.id);
	}

}