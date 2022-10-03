package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Candle extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Candle(int id) {
		super(id, "Candle");
	}

	private Candle(Candle old) {
		this(old.id);
	}

}