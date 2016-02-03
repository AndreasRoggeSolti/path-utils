package de.solti.pathutils.path;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.FastMath;

public class DoublePath extends Path<Double>{
	private static final long serialVersionUID = -3316979415943611203L;

	public DoublePath(Location<Double>[] locations, Long[] times) {
		super(locations, times);
	}
	
	public DoublePath(Collection<Location<Double>> locations, Collection<Long> times){
		super(locations.toArray(new DoubleLocation[locations.size()]), times.toArray(new Long[times.size()]));
	}
	
	protected Location<Double> getInterpolation(Location<Double> last, Location<Double> next,
			double ratio) {
		Vector<Double> lastComponents = last.getComponents();
		Vector<Double> nextComponents = next.getComponents();
		Vector<Double> components = new Vector<Double>();
		Iterator<Double> iter = nextComponents.iterator();
		for (Double elem : lastComponents){
			Double nextElem = iter.next();
			components.add( (1.-ratio) * elem.doubleValue() + ratio * nextElem.doubleValue() );
		}
		return new DoubleLocation(components);
	}
	
	/**
	 * 
	 * @param other Path to compare against
	 * @param granularity tells us at how many points we want to compare the two paths.
	 * @return
	 */
	public Double getDistance(Path<Double> other, int granularity){
		// define maxDistance between endpoints of bounding rectangles
		Vector<Double> lowest = (Vector<Double>) this.locations[0].getComponents().clone();
		Vector<Double> highest = (Vector<Double>) lowest.clone();
		updateBounds(lowest, highest, this.locations);
		updateBounds(lowest, highest, other.locations);
		double maxDist = new DoubleLocation(lowest).getDistance(new DoubleLocation(highest));
		long minTime = FastMath.min(timesAtLocations[0], other.timesAtLocations[0]);
		long maxTime = FastMath.max(timesAtLocations[timesAtLocations.length-1], other.timesAtLocations[timesAtLocations.length-1]);
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (int i = 0; i < granularity; i++){
			long timeToTest = minTime + (long)(i*(maxTime-minTime)/(double)granularity);
			try {
				Location<Double> loc1 = getLocationAtTime(timeToTest);
				Location<Double> loc2 = other.getLocationAtTime(timeToTest);
				System.out.println(loc1.getDistance(loc2).doubleValue() / maxDist +", at time "+timeToTest+", i="+i);
				stats.addValue(loc1.getDistance(loc2).doubleValue() / maxDist);
			} catch (TimeOutOfBoundsException e) {
				stats.addValue(1.);
			}
		}
		return new Double(stats.getMean());	
	}

	protected void updateBounds(Vector<Double> lowest, Vector<Double> highest, Location<Double>[] locations) {
		for (Location<Double> loc : locations){
			Vector<Double> comp = loc.getComponents();
			for (int i = 0; i < comp.size(); i++){
				if (lowest.get(i) > comp.get(i)){
					lowest.set(i, comp.get(i));
				} if (highest.get(i) < comp.get(i)){
					highest.set(i, comp.get(i));
				}
			}
		}
	}

}
