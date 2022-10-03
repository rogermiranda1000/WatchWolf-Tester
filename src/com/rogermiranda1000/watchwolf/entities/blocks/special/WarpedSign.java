package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class WarpedSign extends Block {

	/*   --- CONSTRUCTORS ---   */
	public WarpedSign(int id) {
		super(id, "WarpedSign");
	}

	private WarpedSign(WarpedSign old) {
		this(old.id);
	}

}