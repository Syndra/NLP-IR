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
	
	double[][] arrayForm;
	
	VectorSpaceModel model;
	
	private final int ADD_DOC = 0;
	
	public SVD(VectorSpaceModel model, int choose_words) {
		
		this.model = model;
		
		RealMatrix m = MatrixUtils.createRealMatrix(model.weight_vector);
		
		SingularValueDecomposition svd_model = new SingularValueDecomposition(m.transpose());
		
		V = svd_model.getV();
		Vt = svd_model.getVT();
		S = svd_model.getS();
		U = svd_model.getU();
		
		//_Vt = Vt.getSubMatrix(0, choose_words, 0, Vt.getColumnDimension()-1);
		//_S = S.getSubMatrix(0, choose_words, 0, choose_words);
		//_U = U.getSubMatrix(0, U.getRowDimension(), 0, choose_words);
		
		//output = _U.multiply(_S).multiply(_Vt);
		
		//arrayForm = new double[output.getColumnDimension()][output.getRowDimension()];
		
		//for(int i = 0 ; i < 10; i ++) {
		//	arrayForm[i] = output.getRow(i);
		//}
		//System.out.println(output.getColumnDimension() + "^" + output.getRowDimension());
	}
	
	public void print() {
		System.out.println("U" + U.getRowDimension() + "-" + U.getColumnDimension() );
		System.out.println("S" + S.getRowDimension() + "-" + S.getColumnDimension());
		System.out.println("V" + V.getRowDimension() + "-" + V.getColumnDimension());
	}
	
	public void print_doc_sim() {
		RealMatrix SVt = S.multiply(Vt);
		
		float[][] sim_mat = new float[SVt.getColumnDimension()][SVt.getColumnDimension()];
		
		for(int i = 0 ; i < SVt.getColumnDimension(); i ++)
		{
			for(int j = 0; j < SVt.getColumnDimension(); j++)
			{
				sim_mat[i][j] = VectorSpaceModel.getAngle(SVt.getColumn(i), SVt.getColumn(j));
				System.out.print(sim_mat[i][j] + " ");
			}
			System.out.println(" MAX : " + argmin(sim_mat[i]));
		}
		
		for(int i = 0;i < SVt.getColumnDimension(); i++) {
			System.out.println("<" + model.scripts[i].scriptName + "> is close <" + model.scripts[argmin(sim_mat[i])].scriptName + ">");
		}
	}
	
	public void print_term_domi() {
		for(int i = 0; i < S.multiply(Vt).getColumnDimension(); i ++) {
			int index = argmax(U.multiply(S).getRow(i));
			System.out.println(S.multiply(Vt).getRow(i).toString());
			System.out.println(model.scripts[i].scriptName +  " : " + model.termList.get(index));
		}
	}
	
	public static int argmax (double [] elems)
    {
      int bestIdx = -1;
      double max = Double.NEGATIVE_INFINITY;
      for (int i = 0; i < elems.length; i++) {
        double elem = elems[i];
        if (elem > max) {
          max = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
	
	public static int argmax (float [] elems)
    {
      int bestIdx = -1;
      double max = Double.NEGATIVE_INFINITY;
      for (int i = 0; i < elems.length; i++) {
        double elem = elems[i];
        if (elem > max) {
          max = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
	
	public static int argmin (float [] elems)
    {
      int bestIdx = -1;
      double min = Double.POSITIVE_INFINITY;
      for (int i = 0; i < elems.length; i++) {
        double elem = elems[i];
        if(elem == 0)
        		elem = Double.POSITIVE_INFINITY;
        if (elem < min) {
          min = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
}
