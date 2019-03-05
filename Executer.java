/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

public class Executer {
	
	private Executer() {
		
	}
	public static Matrix creatMatrix(int rows, int columns, double[][] data){
		Matrix mt = Matrix.creatMatrix(rows, columns);
			for(int i=0; i<data.length; i++){
				for(int j=0; j<data[i].length; j++){
					mt.setElem(i, j, data[i][j]);
				}
			}
			return mt;
	}
	
	public static Matrix addition(Matrix mt1, Matrix mt2) {
		int rows = mt1.getSize().getRows();
		int cols = mt1.getSize().getColumns();
		int rows2 = mt2.getSize().getRows();
		int cols2 = mt2.getSize().getColumns();
		if ((rows == rows2) && (cols == cols2)) {
			Matrix mt = Matrix.creatMatrix(rows, cols);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					mt.setElem(i, j, mt1.getElem(i, j) + mt2.getElem(i, j));
				}
			}
			return mt;
		} else {
			return null;
		}
	}
	
	public static Matrix multiplication(Matrix mt1, Matrix mt2) {
		int rows = mt1.getSize().getRows();
		int cols = mt1.getSize().getColumns();
		int rows2 = mt2.getSize().getRows();
		int cols2 = mt2.getSize().getColumns();
		if (cols == rows2) {
			Matrix mt = Matrix.creatMatrix(rows, cols2);
			double tmp = 0;
			for (int i = 0; i < rows; i++) {
				for (int c = 0; c < cols2; c++) {
					for (int j = 0; j < cols; j++) {
						tmp = mt1.getElem(i, j) * mt2.getElem(j, c);
 
					}
					mt.setElem(i, c, tmp);
					tmp = 0;
				}
			}
			return mt;
		} else {
			return null;
		}
	}
}