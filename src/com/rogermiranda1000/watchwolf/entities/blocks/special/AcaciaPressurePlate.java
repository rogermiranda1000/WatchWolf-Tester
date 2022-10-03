package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class AcaciaPressurePlate extends Block {

	/*   --- CONSTRUCTORS ---   */
	public AcaciaPressurePlate(short id) {
		super(id, "ACACIA_PRESSURE_PLATE");
	}

	public AcaciaPressurePlate(int id) {
		this((short) id);
	}

	private AcaciaPressurePlate(AcaciaPressurePlate old) {
		this(old.id);
	}

}