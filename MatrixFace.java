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
import java.awt.Toolkit;
 
import javax.swing.JFrame;
 
 
public class MatrixFace{
	
	private JFrame mainFrame;
	private Matrix mt1, mt2, mt3;
	
	public MatrixFace(){
		
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		
		mt1 = Matrix.creatMatrix(1, 1);
		mt2 = Matrix.creatMatrix(1, 1);
		mt3 = Matrix.creatMatrix(1, 1);
		
		GridBagLayout gblManager = new GridBagLayout();
		
		mainFrame = new JFrame();
		mainFrame.setLayout(gblManager);
		GridBagConstraints tmpLayout = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		mainFrame.add(new MatrixPanel(mt1, "Матрица № 1 ", screenRes, false).getMatrixMainPanel(), tmpLayout);
		tmpLayout.gridx = 1;
		mainFrame.add(new MatrixPanel(mt2, "Матрица № 2", screenRes, false).getMatrixMainPanel(), tmpLayout);
		tmpLayout.gridy = 1;
		MatrixPanel resultSet =  new MatrixPanel(mt3, "Результирующая Матрица № 2", screenRes, true);
		mainFrame.add(resultSet.getMatrixMainPanel(), tmpLayout);
		tmpLayout.gridx = 0;
		tmpLayout.weightx = 1;
		tmpLayout.weighty = 1;
		tmpLayout.anchor = GridBagConstraints.NORTHWEST;
		tmpLayout.fill = GridBagConstraints.BOTH;
		mainFrame.add(new MatrixFaceStatusBar(mt1, mt2, resultSet, screenRes).getStatusPanel(), tmpLayout);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new MatrixFace();
		
	}
}