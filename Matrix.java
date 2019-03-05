package matrix;
 
public class Matrix {
	
	private double[][] matrix;
	
	public Matrix(){
 
	}
	public void setSize(int rows, int columns){
		matrix = new double[rows][columns];
	}
	public MatrixSize getSize(){ 
		int rows = matrix.length;
		int columns = matrix[0].length;
		return new MatrixSize(rows, columns);
	}
	public void setElem(int rowNum, int colNum, double value){
		matrix[rowNum][colNum] = value;
	}
	public double getElem(int rowNum, int colNum){
		return matrix[rowNum][colNum];
	}
	public static Matrix creatMatrix(int rows, int columns){
		Matrix mt = new Matrix();
		mt.setSize(rows, columns);
		return mt;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public class MatrixSize{
		private int rows, columns;
		public MatrixSize(int rows, int columns){
			this.rows = rows;
			this.columns = columns;
		}
		public int getRows(){
			return this.rows;
		}
		public int getColumns(){
			return this.columns;
		}
	}
 
}