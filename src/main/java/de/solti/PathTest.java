package de.solti;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import de.solti.pathutils.path.DoubleLocation;
import de.solti.pathutils.path.DoublePath;
import de.solti.pathutils.path.Location;
import de.solti.pathutils.path.Path;

public class PathTest {

	@Test
	public void testEqualPaths() throws Exception {
		Location<Double>[] locations = new DoubleLocation[2];
		Vector<Double> loc1 = new Vector<Double>();
		loc1.add(0.0);loc1.add(10.0);
		Vector<Double> loc2 = new Vector<Double>();
		loc2.add(100.0);loc2.add(10.0);
		
		locations[0] = new DoubleLocation(loc1);
		locations[1] = new DoubleLocation(loc2);
		
		Long[] times = new Long[]{new Long(10), new Long(1000)};
		
		Path<Double> path1 = new DoublePath(locations, times);
		
		Path<Double> path2 = new DoublePath(locations, times);
		
		double dist = path1.getDistance(path2, 10);
		Assert.assertEquals(0.0, dist, 0.00001);
	}
	
	@Test
	public void testUnEqualPaths1() throws Exception {
		Location<Double>[] locations = new DoubleLocation[2];
		Vector<Double> loc1 = new Vector<Double>();
		loc1.add(0.0);loc1.add(10.0);
		Vector<Double> loc2 = new Vector<Double>();
		loc2.add(100.0);loc2.add(10.0);
		
		locations[0] = new DoubleLocation(loc1);
		locations[1] = new DoubleLocation(loc2);
		
		Long[] times = new Long[]{new Long(10), new Long(1000)};
		Long[] times2 = new Long[]{new Long(15), new Long(1015)};
		
		Path<Double> path1 = new DoublePath(locations, times);
		
		Path<Double> path2 = new DoublePath(locations, times2);
		
		double dist = path1.getDistance(path2, 100);
		Assert.assertEquals(0.02982, dist, 0.001);
	}
	
	@Test
	public void testUnEqualPaths2() throws Exception {
		Location<Double>[] locations = new DoubleLocation[2];
		Vector<Double> loc1 = new Vector<Double>();
		loc1.add(0.0);loc1.add(100.0);
		Vector<Double> loc2 = new Vector<Double>();
		loc2.add(0.0);loc2.add(0.0);
		
		locations[0] = new DoubleLocation(loc1);
		locations[1] = new DoubleLocation(loc2);
		
		Location<Double>[] locations2 = new DoubleLocation[2];
		locations2[0] = new DoubleLocation(loc2);
		locations2[1] = new DoubleLocation(loc1);
		
		Long[] times = new Long[]{new Long(10), new Long(1000)};
		
		Path<Double> path1 = new DoublePath(locations, times);
		
		Path<Double> path2 = new DoublePath(locations2, times);
		
		double dist = path1.getDistance(path2, 100);
		Assert.assertEquals(0.5, dist, 0.00001);
	}
}
