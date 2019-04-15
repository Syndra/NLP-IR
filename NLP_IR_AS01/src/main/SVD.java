package main;

//import org.apache.commons.math3.linear.*;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SVD {
	
	/*RealMatrix origin;
	RealMatrix U;
	RealMatrix S;
	RealMatrix V;
	RealMatrix Vt;*/
	
	public SVD(double[][] mat) {
		double[][] matrixData = { {1d,2d,3d}, {2d,5d,3d}};
		
		/*RealMatrix m = MatrixUtils.createRealMatrix(mat);
		
		SingularValueDecomposition svd_model = new SingularValueDecomposition(m);
		
		V = svd_model.getV();
		Vt = svd_model.getVT();
		S = svd_model.getS();
		U = svd_model.getU();
		*/
		//
		
		
		 // create M-by-N matrix that doesn't have full rank
	      int M = 5, N = 5;
	      Matrix B = Matrix.random(5, 3);
	      Matrix A = Matrix.random(M, N).times(B).times(B.transpose());
	      System.out.print("A = ");
	      A.print(9, 6);

	      // compute the singular vallue decomposition
	      System.out.println("A = U S V^T");
	      System.out.println();
	      SingularValueDecomposition s = A.svd();
	      System.out.print("U = ");
	      Matrix U = s.getU();
	      U.print(9, 6);
	      System.out.print("Sigma = ");
	      Matrix S = s.getS();
	      S.print(9, 6);
	      System.out.print("V = ");
	      Matrix V = s.getV();
	      V.print(9, 6);
	      System.out.println("rank = " + s.rank());
	      System.out.println("condition number = " + s.cond());
	      System.out.println("2-norm = " + s.norm2());

	      // print out singular values
	      System.out.print("singular values = ");
	      Matrix svalues = new Matrix(s.getSingularValues(), 1);
	      svalues.print(9, 6);
	}
	
	public void print() {
		/*System.out.println("U" + U.getRowDimension() + "-" + U.getColumnDimension() + " : " + U.toString());
		System.out.println("S" + S.getRowDimension() + "-" + S.getColumnDimension() + " : " + S.toString());
		System.out.println("V" + V.getRowDimension() + "-" + V.getColumnDimension() + " : " + V.toString());*/
	}
}
