package de.solti.pathutils.path;

import java.util.Vector;

public interface Location<T extends Number> {
	
	public T getDistance(Location<T> other);

	public Vector<T> getComponents();
}
