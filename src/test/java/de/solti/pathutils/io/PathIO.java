package de.solti.pathutils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import javax.imageio.stream.FileImageOutputStream;

import de.solti.pathutils.path.DoubleLocation;
import de.solti.pathutils.path.DoublePath;
import de.solti.pathutils.path.Location;
import de.solti.pathutils.path.Path;

public class PathIO {

	public static DoublePath readPathFromFile(File input){
		DoublePath path = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String header = reader.readLine();
			
			Vector<Long> times = new Vector<Long>();
			Vector<Location<Double>> locations = new Vector<Location<Double>>();
			
			String line = reader.readLine();
			while (line != null){
				String[] parts = line.split(";");
				times.add(Long.valueOf(parts[0]));
				locations.add(DoubleLocation.fromString(parts[1]));
				line = reader.readLine();
			}
			path = new DoublePath(locations, times);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getPathString(Path<? extends Number> path){
		StringBuffer buffer = new StringBuffer();
		buffer.append(Path.getHeader());
		buffer.append("\n");
		
		buffer.append(path.toString());
		
		return buffer.toString();
	}
}
