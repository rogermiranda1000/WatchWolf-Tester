package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class NetherPortal extends Block {

	/*   --- CONSTRUCTORS ---   */
	public NetherPortal(int id) {
		super(id, "NetherPortal");
	}

	private NetherPortal(NetherPortal old) {
		this(old.id);
	}

}