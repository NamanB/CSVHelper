/***
 * A class to read/write numerical CSV files and allow easy access
 * 
 * @author Naman
 *
 */
public class CSVData {
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
	public CSVData readCSVFile(String filename, int numLinesToIgnore, String[] colunmNames) {
		return null;
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
	public CSVData readCSVFile(String filename, int numLinesToIgnore) {
		return null;
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
		
	}
	
	/***
	 * Sets a full column
	 * 
	 * @param columnIndex the index of the column
	 * @param columnValues the values to store in the column
	 */
	public void setColumn(int columnIndex, double[] columnValues) {
		
	}
	
	/***
	 * Sets a full column
	 * 
	 * @param colName the name of the column to set
	 * @param columnValues the values to store in the column
	 */
	public void setColumn(String colName, double[] columnValues) {
		
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
		
		return -1;
	}
	
}
