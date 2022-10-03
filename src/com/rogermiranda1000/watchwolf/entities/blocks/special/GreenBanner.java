package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GreenBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GreenBanner(short id) {
		super(id, "GREEN_BANNER");
	}

	public GreenBanner(int id) {
		this((short) id);
	}

	private GreenBanner(GreenBanner old) {
		this(old.id);
	}

}