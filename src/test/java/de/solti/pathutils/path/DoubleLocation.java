package de.solti.pathutils.path;

import java.io.Serializable;
import java.util.Vector;

import org.apache.commons.math3.util.FastMath;

import com.google.common.base.Joiner;

public class DoubleLocation implements Location<Double>, Serializable{
	private static final long serialVersionUID = 7854832682545872110L;
	
	private Vector<Double> elements;

	public DoubleLocation(Vector<Double> elements){
		this.elements = elements;
	}
	
	@Override
	public Double getDistance(Location<Double> other) {
		double euclidDist = 0;
		for (int i = 0; i < elements.size(); i++){
			euclidDist += FastMath.pow(elements.get(i) - other.getComponents().get(i), 2);
		}
		return FastMath.sqrt(euclidDist);
	}

	@Override
	public Vector<Double> getComponents() {
		return elements;
	}

	public String toString(){
		return Joiner.on(",").join(elements);
	}
	
	public static DoubleLocation fromString(String locationString){
		Vector<Double> components = new Vector<Double>();
		String[] array = locationString.split(",");
		for(String d : array){
			components.add(Double.valueOf(d));
		}
		return new DoubleLocation(components);
	}
}
