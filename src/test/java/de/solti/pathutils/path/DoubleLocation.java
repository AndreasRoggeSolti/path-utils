package de.solti.pathutils.path;

import java.util.Vector;

public class DoubleLocation implements Location<Double>{

	private Vector<Double> elements;

	public DoubleLocation(Vector<Double> elements){
		this.elements = elements;
	}
	
	@Override
	public Double getDistance(Location<Double> other) {
		double euclidDist = 0;
		for (int i = 0; i < elements.size(); i++){
//			euclidDist += FastMa;
		}
		return null;
	}

	@Override
	public Vector<Double> getComponents() {
		// TODO Auto-generated method stub
		return null;
	}

}
