package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class MangroveLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public MangroveLog(short id) {
		super(id, "MANGROVE_LOG");
	}

	public MangroveLog(int id) {
		this((short) id);
	}

	private MangroveLog(MangroveLog old) {
		this(old.id);
	}

}