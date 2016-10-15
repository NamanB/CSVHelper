import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***
 * A class to read/write numerical CSV files and allow easy access
 * 
 * @author Naman
 *
 */
public class CSVData {
	private int numRows;
	private String filePathToCSV;
	private double[][] data;
	private String[] columnNames;
	
	/***
	 * Returns a new CVSData object for a file ignoring lines at the top. 
	 * All other data is stored as doubles.
	 * 
	 * @param filename the file to read
	 * @param numLinesToIgnore number of lines at the top to ignore
	 * @param colunmNames the names of the columns
	 * @return a CVSData object for that file
	 */
	public CSVData(String filepath, int numLinesToIgnore, String[] columnNames) {
		this.filePathToCSV = filepath;

		String dataString = readFileAsString(filepath);
		String[] lines = dataString.split("\n");

		// number of data points
		int n = lines.length - numLinesToIgnore;
		this.numRows = n;
		int numColumns = columnNames.length;

		// create storage for column names
		this.columnNames = columnNames;

		// create storage for data
		this.data = new double[n][numColumns];
		for (int i = 0; i < lines.length - numLinesToIgnore; i++) {
			String line = lines[numLinesToIgnore + i];
			String[] coords = line.split(",");
			for (int j = 0; j < numColumns; j++) {
				if (coords[j].endsWith("#")) coords[j] = coords[j].substring(0, coords[j].length()-1);
				double val = Double.parseDouble(coords[j]);
				data[i][j] = val;
			}
		}
	}
	
	/***
	 * Returns a new CVSData object for a file ignoring lines at the top. 
	 * All other data is stored as doubles. The first line in the CSV file 
	 * must contain the names of the columns
	 * 
	 * @param filename the file to read
	 * @param numLinesToIgnore the line where the column names is, where the next line has the data
	 * @return a CVSData object for that file
	 */
	public CSVData(String filepath, int numLinesToIgnore) {
		this.filePathToCSV = filepath;

		String dataString = readFileAsString(filepath);
		String[] lines = dataString.split("\n");

		// create storage for column names
		String[] colNames = getColumnNames(lines[numLinesToIgnore]);
		this.columnNames = colNames;
		
		numLinesToIgnore++;
		
		// number of data points
		int n = lines.length - numLinesToIgnore;
		this.numRows = n;
		int numColumns = colNames.length;

		
		// create storage for data
		this.data = new double[n][numColumns];
		for (int i = 0; i < lines.length - numLinesToIgnore; i++) {
			String line = lines[numLinesToIgnore + i];
			String[] coords = line.split(",");
			for (int j = 0; j < numColumns; j++) {
				if (coords[j].endsWith("#")) coords[j] = coords[j].substring(0, coords[j].length()-1);
				double val = Double.parseDouble(coords[j]);
				data[i][j] = val;
			}
		}
	}
	
	/***
	 * Returns a new CVSData object for a file ignoring lines at the top. 
	 * It uses the first row as the column names. All other data is stored 
	 * as doubles.
	 * 
	 * @param filename the file to read
	 * @param numLinesToIgnore number of lines at the top to ignore
	 * @return a CVSData object for that file
	 */
	public static CSVData newCSVData(String filepath, int numLinesToIgnore) {
		return new CSVData(filepath, numLinesToIgnore);
	}
	
	/***
	 * Returns a new CVSData object for a file ignoring lines at the top. 
	 * It uses the first row as the column names. All other data is stored 
	 * as doubles.
	 * 
	 * @param filename the file to read
	 * @param numLinesToIgnore number of lines at the top to ignore
	 * @return a CVSData object for that file
	 */
	public static CSVData newCSVData(String filepath, int numLinesToIgnore, String[] columnNames) {
		return new CSVData(filepath, numLinesToIgnore, columnNames);
	}
	
	/***
	 * Corrects the PowerSense Data into the specific data and format we want
	 * 
	 * @param filepath the file path
	 * @return the corrected CSVData object
	 */
	public static CSVData newCSVCorrectedPowerSenseData(String filepath) {
		CSVData current = new CSVData(filepath, 0);
		
		current.deleteColumns(new int[] {1,2,3,7,8,9,13,14,15,16,17,18,19,20,21});
		
		return current;
	}
	
	/***
	 * Deletes the columns in the indexes specified
	 * @param columnIndexes the indexes of the columns to be deleted
	 */
	public void deleteColumns(int[] columnIndexes) {
		for (int index = 0; index < columnIndexes.length; index++) {
			deleteColumn(columnIndexes[index]);
		}
	}
	
	/***
	 * Deletes the column in the index specified
	 * @param columnIndex the index of the column to be deleted
	 */
	public void deleteColumn(int columnIndex) {
		double[][] data = new double[this.columnNames.length - 1][this.numRows];
		
		for (int row = 0; row < data[0].length; row++) {
			int currentCol = 0;
			for (int column = 0; column < data.length; column++) {
				if (column != columnIndex) data[row][currentCol++] = data[row][column];
			}
		}
		this.data = data;
	}
	
	/***
	 * Returns an array containing the column names
	 * 
	 * @param titleLine the string containing the title names separated by columns
	 * @return the array containing the column names
	 */
	public String[] getColumnNames(String titleLine) {
		return titleLine.split(",");
	}
	
	/***
	 * Converts the file into a String
	 * 
	 * @param filepath the path to the string 
	 * @return the String version of the file
	 */
	private String readFileAsString(String filepath) {
		StringBuilder output = new StringBuilder();
		try (Scanner scanner = new Scanner(new File(filepath))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output.append(line + System.getProperty("line.separator"));
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	/***
	 * Returns all the values in a row
	 * 
	 * @param rowIndex the index of the row
	 * @return all the values in a row
	 */
	public double[] getRow(int rowIndex) {
		return data[rowIndex];
	}
	
	/***
	 * Returns all the values in a column
	 * 
	 * @param columnIndex the index of the column
	 * @return all the values in a column
	 */
	public double[] getColumn(int columnIndex) {
		double[] columnValues = new double[data.length];
		
		for (int i = 0; i < data.length; i++) 
			columnValues[i] = data[columnIndex][i];
		
		return columnValues;
	}
	
	/***
	 * Returns all the values in a column
	 * 
	 * @param name the name of the column
	 * @return all the values in a column
	 */
	public double[] getColumn(String name) {
		int index = getColumnIndex(name);
		
		return getColumn(index);
	}
	
	/***
	 * Returns all the values from multiple rows. 
	 * It keeps the rows sorted.
	 * 
	 * @param rowIndexes the indexes of the rows
	 * @return all the values from multiple rows
	 */
	public double[][] getRows(int[] rowIndexes) {
		double[][] output = new double[data.length][rowIndexes.length];
		
		for (int i = 0; i < rowIndexes.length; i++) 
			output[i] = data[rowIndexes[i]];
		
		return output;
	}
	
	/***
	 * Returns all the values from rows between a start row and an end row. 
	 * It keeps the rows sorted.
	 * 
	 * @param startIndex the row index to start from
	 * @param endIndex the column index to start from
	 * @return all the values in the rows
	 */
	public double[][] getRows(int startIndex, int endIndex) {
		int difference = endIndex - startIndex, currentIndex = 0;
		double[][] output = new double[data.length][difference];
		
		for (int i = startIndex; i <= endIndex; i++) 
			output[currentIndex++] = data[i];
		
		return output;
	}
	
	/***
	 * Returns all the values in columns. 
	 * It keeps the columns sorted.
	 * 
	 * @param columnIndexes the indexes of the columns to return
	 * @return the values in the columns specified
	 */
	public double[][] getColumns(int[] columnIndexes) {
		double[][] output = new double[data[0].length][columnIndexes.length];
		
		for (int i = 0; i < columnIndexes.length; i++) 
			output[i] = getColumn(columnIndexes[i]);
		
		return output;
	}
	
	/***
	 * Returns all the values in columns. 
	 * It keeps the columns sorted.
	 * 
	 * @param startIndex the index to start from
	 * @param endIndex the index to end at
	 * @return the values in the columns specified
	 */
	public double[][] getColumns(int startIndex, int endIndex) {
		int difference = endIndex-startIndex;
		double[][] output = new double[data[0].length][difference];
		
		for (int i = 0; i < difference; i++) 
			output[i] = getColumn(startIndex+i);
		
		return output;
	}
	
	/***
	 * Returns all the values in columns. 
	 * It keeps the columns sorted.
	 * 
	 * @param colNames the names of the columns to return values from
	 * @return the values in the columns specified
	 */
	public double[][] getColumns(String[] colNames) {
		int[] columnIndexes = new int[colNames.length];
		
		for (int i = 0; i < colNames.length; i++) 
			columnIndexes[i] = getColumnIndex(colNames[i]);
		
		return getColumns(columnIndexes);
	}
	
	/***
	 * Returns one value
	 * 
	 * @param rowIndex the row index
	 * @param colIndex the column index
	 * @return the value at that point
	 */
	public double getValue(int rowIndex, int colIndex) {
		return this.data[rowIndex][colIndex];
	}
	
	/***
	 * Sets a value at a specific spot
	 * 
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @param value the value to save
	 */
	public void setValue(int rowIndex, int columnIndex, double value) {
		data[rowIndex][columnIndex] = value;
	}
	
	/***
	 * Returns then sets a value at a specific spot 
	 * 
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @param value the value to store
	 * @return the old value in that spot
	 */
	public double returnSetValue(int rowIndex, int columnIndex, double value) {
		double output = data[rowIndex][columnIndex];
		
		data[rowIndex][columnIndex] = value;
		
		return output;
	}
	
	/***
	 * Sets a full row
	 * 
	 * @param rowIndex the index for the row to replace
	 * @param rowValues the values to save into the row
	 */
	public void setRow(int rowIndex, double[] rowValues) {
		data[rowIndex] = rowValues;
	}
	
	/***
	 * Sets a full column
	 * 
	 * @param columnIndex the index of the column
	 * @param columnValues the values to store in the column
	 */
	public void setColumn(int columnIndex, double[] columnValues) {
		for (int i = 0; i < data[0].length; i++)
			data[columnIndex][i] = columnValues[i];
	}
	
	/***
	 * Sets a full column
	 * 
	 * @param colName the name of the column to set
	 * @param columnValues the values to store in the column
	 */
	public void setColumn(String colName, double[] columnValues) {
		int columnIndex = getColumnIndex(colName);
		
		setColumn(columnIndex, columnValues);
	}
	
	/***
	 * Sets all the data to the input data 
	 * 
	 * @param data the double array data
	 */
	public void setData(double[][] data) {
		this.data = data;
	}
	
	/***
	 * Returns the column titles
	 * 
	 * @return the column titles
	 */
	public String[] getColumnTitles() {
		return columnNames;
	}
	
	/***
	 * Returns the number of rows
	 * 
	 * @return the number of rows
	 */
	public int getNumRows() {
		return this.numRows;
	}
	
	/***
	 * Saves the current state of the object into a CSV file
	 */
	public void saveCurrentState() {
		
	}
	
	/***
	 * Finds the index for the column specified by name. 
	 * Returns -1 if the name is invalid
	 * 
	 * @param colName the name of the column
	 * @return the column index
	 */
	public int getColumnIndex(String colName) {
		for (int i = 0; i < columnNames.length; i++) 
			if (colName.equals(columnNames[i])) return i;
		
		System.out.println("The column name " + colName + " does not exist as a column title");
		return -1;
	}
	
	public String getFilePath() {
		return filePathToCSV;
	}
	
	public String dataToString() {
		StringBuilder output = new StringBuilder(); 
		
		for (String columnName : this.columnNames)
			output.append(columnName + ", ");
		
		for (int i = 0; i < data.length; i++) {
			int length = output.length();
			output.delete(length-2, length);
			output.append("\n");
			for (int j = 0; j < data[0].length; j++) {
				output.append(data[i][j] + ", ");
			}
		}
		output.delete(output.length()-2, output.length());
		
		return output.toString();
	}
	
}
