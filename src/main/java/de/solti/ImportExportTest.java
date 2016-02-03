package de.solti;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import de.solti.pathutils.io.PathIO;
import de.solti.pathutils.path.DoubleLocation;
import de.solti.pathutils.path.DoublePath;
import de.solti.pathutils.path.Location;
import de.solti.pathutils.path.Path;

public class ImportExportTest {

	@Test
	public void testImport() throws Exception {
		DoublePath path = PathIO.readPathFromFile(new File("test/path1.dat"));
		Assert.assertEquals(path.getLocationAtTime(10).getComponents().get(0), 0.0, 0.00001);
	}
	
	@Test
	public void testExport() throws Exception {
		Location<Double>[] locations = new DoubleLocation[2];
		Vector<Double> loc1 = new Vector<Double>();
		loc1.add(0.0);loc1.add(10.0);
		Vector<Double> loc2 = new Vector<Double>();
		loc2.add(100.0);loc2.add(10.0);
		
		locations[0] = new DoubleLocation(loc1);
		locations[1] = new DoubleLocation(loc2);
		
		Long[] times = new Long[]{new Long(10), new Long(1000)};
		
		Path<Double> path1 = new DoublePath(locations, times);
		
		String exportString = PathIO.getPathString(path1);
		
		Assert.assertEquals("timeStamp;coordinates\n10;0.0,10.0\n1000;100.0,10.0\n", exportString);
	}
}
