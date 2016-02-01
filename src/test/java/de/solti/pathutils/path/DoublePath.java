package de.solti.pathutils.path;

import java.util.Iterator;
import java.util.Vector;

public class DoublePath extends Path<Double>{

	public DoublePath(Location<Double>[] locations, long[] times) {
		super(locations, times);
		// TODO Auto-generated constructor stub
	}
	
	protected Location<Double> getInterpolation(Location<Double> last, Location<Double> next,
			double ratio) {
		Vector<Double> lastComponents = last.getComponents();
		Vector<Double> nextComponents = next.getComponents();
		Vector<Double> components = new Vector<Double>();
		Iterator<Double> iter = nextComponents.iterator();
		Class<?> numberClass = lastComponents.get(0).getClass();
		for (Double elem : lastComponents){
			Double nextElem = iter.next();
			components.add(new Double(elem.doubleValue() + nextElem.doubleValue()));
		}
		return new DoubleLocation(components);
	}

}
