package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaLog extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AcaciaLog(short id) {
		super(id, "ACACIA_LOG");
	}

	public AcaciaLog(int id) {
		this((short) id);
	}

	private AcaciaLog(AcaciaLog old) {
		this(old.id);
	}

}