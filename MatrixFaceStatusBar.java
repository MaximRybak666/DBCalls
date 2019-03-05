/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author lucas
 */
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
public class MatrixFaceStatusBar {
	
	private JPanel mainPanel;
	private JTextArea errorMessage;
	private JButton doResult;
	private JComboBox operationList;
	private Matrix matrix1, matrix2, resultSet;
	private static GridBagLayout gblManager;
	private static GridBagConstraints tmpLayout;
	private OperationsID [] operations = {new OperationsID("Сложение матриц", 1), new OperationsID("Умножение матриц", 2) };
	
	
	public MatrixFaceStatusBar(final Matrix mt1, final Matrix mt2, final MatrixPanel resultMatrix, Dimension screenRes){
		
		gblManager = new GridBagLayout();
		tmpLayout = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		
		matrix1 = mt1;
		matrix2 = mt2;
		
		mainPanel = new JPanel(gblManager);
		mainPanel.setBorder(BorderFactory.createTitledBorder("Операции"));
		//mainPanel.setMinimumSize(new Dimension((int)(screenRes.getWidth()/2-50), (int)screenRes.getHeight()/3+35));
		//mainPanel.setPreferredSize(mainPanel.getMinimumSize());
		operationList = new JComboBox(operations);
		errorMessage = new JTextArea();
		errorMessage.setEditable(false);
		errorMessage.setWrapStyleWord(true);
		errorMessage.setBorder(BorderFactory.createTitledBorder("Сообщения об ошибках"));
		doResult = new JButton("Расчитать");
		doResult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int opID = ((OperationsID)operationList.getSelectedItem()).getID();
				int mt1Rows = matrix1.getSize().getRows();
				int mt2Rows = matrix2.getSize().getRows();
				int mt1Columns = matrix1.getSize().getColumns();
				int mt2Columns = matrix2.getSize().getColumns();
				StringBuilder sb = new StringBuilder();
				boolean isError = false;
				switch (opID) {
				case 1:
					if(mt1Rows!=mt2Rows){
						sb.append("Строки матриц не совпадают: \n Матрица 1 имеет " + mt1Rows + " строк \n Матрица 2 имеет " + mt2Rows + " строк \n");
						isError = true;
					}
					if(mt1Columns!=mt2Columns){
						sb.append("Столбцы матриц не совпадают: \n Матрица 1 имеет " + mt1Columns + " столбца \n Матрица 2 имеет " + mt2Columns + " столбца \n");
						isError = true;
					}
					if(isError){
						errorMessage.setText(sb.toString());
						break;
					}else{
						resultSet = Executer.addition(matrix1, matrix2);
						resultMatrix.rebuildPanel(resultSet);
						break;
					}
				case 2:
					if(mt1Columns!=mt2Rows){
						sb.append("Для умножения матриц нужно что бы кол-во столбцов первой матрицы, было равно кол-ву строк во второй: \n Матрица 1 имеет " + mt1Columns + " столбцов \n Матрица 2 имеет " + mt2Rows + " строк \n");
						isError = true;
					}
					if(isError){
						errorMessage.setText(sb.toString());
						break;
					}else{
						resultSet = Executer.multiplication(matrix1, matrix2);
						resultMatrix.rebuildPanel(resultSet);
						break;
					}
	
				default:
					break;
				}
				
			}
		});
		
		mainPanel.add(operationList, tmpLayout);
		tmpLayout.gridy = 1;
		mainPanel.add(doResult, tmpLayout);
		tmpLayout.gridy = 0;
		tmpLayout.gridx = 1;
		tmpLayout.gridheight = 2;
		tmpLayout.fill = GridBagConstraints.BOTH;
		tmpLayout.weightx = 1;
		tmpLayout.weighty = 1;
		mainPanel.add(errorMessage, tmpLayout);
	}
	public JPanel getStatusPanel(){
		return mainPanel;
	}
	private class OperationsID{
		String name;
		int value;
		public OperationsID(String name, int value){
			this.name = name;
			this.value = value;
		}
		public int getID(){
			return value;
		}
		public String toString(){
			return name;
		}
	}
}