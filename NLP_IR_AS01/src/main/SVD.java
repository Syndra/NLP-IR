package main;

import org.apache.commons.math3.linear.*;

public class SVD {
	
	RealMatrix origin;
	RealMatrix U;
	RealMatrix S;
	RealMatrix V;
	RealMatrix Vt;
	
	RealMatrix _U;
	RealMatrix _S;
	RealMatrix _V;
	RealMatrix _Vt;
	
	RealMatrix output;
	
	private final int ADD_DOC = 20;
	
	public SVD(double[][] mat, int choose_words) {
		
		choose_words --;
		
		int MAX_SIZE = (mat.length > mat[0].length) ? mat.length : mat[0].length;
		
		double[][] mat_cal = new double[MAX_SIZE + ADD_DOC][MAX_SIZE];
		
		for(int i = 0 ; i < mat.length; i++) {
			for(int j = 0 ; j < mat[0].length; j++) {
				mat_cal[i][j] = mat[i][j];
			}
		}
		
		RealMatrix m = MatrixUtils.createRealMatrix(mat_cal);
		
		SingularValueDecomposition svd_model = new SingularValueDecomposition(m);
		
		V = svd_model.getV();
		Vt = svd_model.getVT();
		S = svd_model.getS();
		U = svd_model.getU();
		
		_Vt = Vt.getSubMatrix(0, choose_words, 0, Vt.getColumnDimension()-1);
		_S = S.getSubMatrix(0, choose_words, 0, choose_words);
		_U = U.getSubMatrix(0, U.getColumnDimension(), 0, choose_words);
		
		output = _U.multiply(_S).multiply(_Vt);
		
		System.out.println(output.getColumnDimension() + "^" + output.getRowDimension());
	}
	
	public void print() {
		System.out.println("U" + U.getRowDimension() + "-" + U.getColumnDimension() + " : " + U.toString());
		System.out.println("S" + S.getRowDimension() + "-" + S.getColumnDimension() + " : " + S.toString());
		System.out.println("V" + V.getRowDimension() + "-" + V.getColumnDimension() + " : " + V.toString());
	}
}
