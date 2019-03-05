/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
 
public class MatrixPanel {
	
	private JPanel mainPanel, scrollPanel;
	private JScrollPane scrollPane;
	private static GridBagLayout gblManager;
	private MatrixTextFields[][] matrixInput;
	private GridBagConstraints tmplayout;
	private JLabel matrixSize;
	private JButton addRow, addCol;
	private Dimension screenRes;
	private boolean resultSet;
	
	public MatrixPanel(final Matrix matrix, String name, final Dimension screenRes, final boolean resultSet){
		gblManager = new GridBagLayout();
		this.screenRes = screenRes;
		this.resultSet = resultSet;
		mainPanel = new JPanel(gblManager);
		matrixInput = new MatrixTextFields[matrix.getSize().getRows()][matrix.getSize().getColumns()];
		if(!resultSet){
			addRow = new JButton("Добавить строку");
			addCol = new JButton("Добавить столбец");
			addRow.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					matrix.setSize(matrix.getSize().getRows()+1, matrix.getSize().getColumns());
					mainPanel.remove(scrollPane);
					mainPanel.add(buildScrollPanel(matrix), new GridBagConstraints(0, 1, 4, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					mainPanel.updateUI();
				}
			});
			addCol.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					matrix.setSize(matrix.getSize().getRows(), matrix.getSize().getColumns()+1);
					mainPanel.remove(scrollPane);
					mainPanel.add(buildScrollPanel(matrix), new GridBagConstraints(0, 1, 4, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					
					mainPanel.updateUI();
				}
			});
		}
		matrixSize = new JLabel("Размер матрицы : " + matrix.getSize().getRows() + " X " + matrix.getSize().getColumns());
		
		
		tmplayout = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		mainPanel.add(new JLabel(name), tmplayout);
		tmplayout.gridx = 1;
		if(!resultSet){
			mainPanel.add(addRow, tmplayout);
			tmplayout.gridx = 2;
			mainPanel.add(addCol, tmplayout);
			tmplayout.gridx = 3;
		}
		mainPanel.add(matrixSize, tmplayout);
		mainPanel.add(buildScrollPanel(matrix), new GridBagConstraints(0, 1, 4, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
	}
	private JScrollPane buildScrollPanel(Matrix matrix){
		scrollPanel = null;
		matrixInput = null;
		tmplayout = null;
		scrollPane = null;
		tmplayout = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		scrollPanel = new JPanel(gblManager);
		matrixInput = new MatrixTextFields[matrix.getSize().getRows()][matrix.getSize().getColumns()];
		for(int i=0; i<matrix.getSize().getRows(); i++){
			tmplayout.gridy = i;
			for(int j=0; j<matrix.getSize().getColumns(); j++){
				matrixInput[i][j] = new MatrixTextFields(i, j, matrix, resultSet);
				tmplayout.gridx = j;
				scrollPanel.add(matrixInput[i][j], tmplayout);
			}
		}
		matrixSize.setText("Размер матрицы : " + matrix.getSize().getRows() + " X " + matrix.getSize().getColumns());
		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setMinimumSize(new Dimension((int)(screenRes.getWidth()/2-50), (int)screenRes.getHeight()/3));
		scrollPane.setPreferredSize(scrollPane.getMinimumSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scrollPane;
		
	}
	public JPanel getMatrixMainPanel(){
		return mainPanel;
	}
	public void rebuildPanel(Matrix matrix){
		mainPanel.remove(scrollPane);
		mainPanel.add(buildScrollPanel(matrix), new GridBagConstraints(0, 1, 4, 1, 0, 0,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		mainPanel.updateUI();
	}
}
