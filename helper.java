/*
 * Copyright (c) 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package trial;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector.Element;

/**
 * @author chimpler.com 
 */
public class helper {
	
	
	public static double[][] readMatrixSequenceFile(String fileName) throws Exception {
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Reader matrixReader = new SequenceFile.Reader(fs, 
			new Path(fileName), configuration);
		
		List<double[]> rows = new ArrayList<double[]>();
		IntWritable key = new IntWritable();
		VectorWritable value = new VectorWritable();
		while(matrixReader.next(key, value)) {
			Vector vector = value.get();
			double[] row = new double[vector.size()];
			for(int i = 0 ; i < vector.getNumNondefaultElements() ; i++) {
				Element element = vector.getElement(i);
				row[element.index()] = element.get();
			}
			rows.add(row);
		}
		return rows.toArray(new double[rows.size()][]);
	}

	public static void writeMatrixSequenceFile(String matrixSeqFileName, double[][] covarianceMatrix) throws Exception{
		int rowCount = covarianceMatrix.length;
		int columnCount = covarianceMatrix[0].length;

		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Writer matrixWriter = new SequenceFile.Writer(fs, configuration, 
				new Path(matrixSeqFileName),
				IntWritable.class, VectorWritable.class);

		IntWritable key = new IntWritable();
		VectorWritable value = new VectorWritable();
		
		double[] doubleValues = new double[columnCount];
		for(int i = 0 ; i < rowCount ; i++) {
			key.set(i);			
			for(int j = 0 ; j < columnCount ; j++) {
				doubleValues[j] = covarianceMatrix[i][j];
			}
			Vector vector = new DenseVector(doubleValues);
			value.set(vector);
			
			matrixWriter.append(key, value);
		}
		matrixWriter.close();
	}

	
}
