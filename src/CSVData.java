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
	public double[] getRows(int rowIndex) {
		return data[rowIndex];
	}
	
	/***
	 * Returns all the values in a column
	 * 
	 * @param columnIndex the index of the column
	 * @return all the values in a column
	 */
	public double[] getColumn(int columnIndex) {
		return null;
	}
	
	public double[][] getRows(int[] rowIndexes) {
		return null;
	}
	
	public double[][] getColumns(int[] columnIndexes) {
		return null;
	}
	
	public void setValue(int rowIndex, int columnIndex, double value) {
		
	}
	
	public void setRow(int rowIndex, double[] rowValues) {
		
	}
	
	public void setColumn(int columnIndex, double[] columnValues) {
		
	}
	
	public String[] getColumnTitles() {
		return columnNames;
	}
	
	public void saveCurrentState() {
		
	}
	
}
