package de.solti.pathutils.path;

import java.io.Serializable;

/**
 * A path is an enumeration of positions in time.
 * It has a continuous model underneath the hood which can be queried at distinct time points.  
 * 
 * @param T is the measure of distance between locations 
 * 
 * @author Andreas Rogge-Solti
 *
 */
public abstract class Path<T extends Number> implements Serializable{
	private static final long serialVersionUID = 4665961375810089515L;
	
	public static final String SEP = ";";
	
	protected Location<T>[] locations;
	
	protected Long[] timesAtLocations;
	
	public Path(Location<T>[] locations, Long[] times){
		this.locations = locations;
		this.timesAtLocations = times;
	}
	
	/**
	 * 
	 * @param other Path to compare against
	 * @param granularity tells us at how many points we want to compare the two paths.
	 * @return
	 */
	public abstract T getDistance(Path<T> other, int granularity);
		
	
	public Location<T> getLocationAtTime(long timeStamp) throws TimeOutOfBoundsException{
		if (timeStamp < timesAtLocations[0] || timeStamp > timesAtLocations[timesAtLocations.length-1]){
			throw new TimeOutOfBoundsException();
		}
		int pos = getLowerValue(timesAtLocations, timeStamp); 
		if (pos >= 0){
			if (timesAtLocations[pos] == timeStamp){
				return locations[pos];
			} else {
				// interpolate with next value;
				long prevTime = timesAtLocations[pos];
				long nextTime = timesAtLocations[pos+1];
				double ratio = (timeStamp-prevTime) / (double)(nextTime - prevTime);
				Location<T> last = locations[pos];
				Location<T> next = locations[pos+1];
				return getInterpolation(last,next,ratio);
			}
		} else {
			throw new TimeOutOfBoundsException();
		}
	}
	


	protected abstract Location<T> getInterpolation(Location<T> last, Location<T> next, double ratio);

	public int getLowerValue(Long[] times, long time) throws TimeOutOfBoundsException{
		if (times[0] > time || times[times.length-1] < time){
			throw new TimeOutOfBoundsException();
		}
		int low = 0; int high = times.length-1;
		int mid = high+low / 2;
		while (high > low){
			if (times[mid] == time){
				return mid;
			}
			if (times[mid] < time){
				low = mid+1;
			} else {
				high = mid-1;
			}
			mid = high+low / 2;
		}
		return mid;
	}

	public static String getHeader() {
		return "timeStamp"+SEP+"coordinates";
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < timesAtLocations.length; i++){
			buffer.append(timesAtLocations[i]);
			buffer.append(SEP);
			buffer.append(locations[i].toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
