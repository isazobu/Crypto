import java.util.ArrayList;
import java.util.Arrays;

public class MatrixOperations {
	
	public static int numberone(Character[][][] cubes,int yuz,int a,int b)
	{	
		return ((int)cubes[yuz][a][b] + (int)cubes[yuz][a][b+2] + (int)cubes[yuz][a+2][b]  + (int)cubes[yuz][a+2][b+2]) % 8;
	}
	
	public static void ayarla(Character[][][] cubes,int yuz,int yuz2)
	{
		ArrayList<Integer> tumdegerler = new ArrayList<Integer>();
		tumdegerler.add(numberone(cubes,yuz,0,0)); // 1.bölgedeki toplam sayının modu var.
		tumdegerler.add(numberone(cubes,yuz,0,3)); // 2.bolgedeki toplam sayının modu var
		tumdegerler.add(numberone(cubes,yuz,3,0)); // 3.
		tumdegerler.add(numberone(cubes,yuz,3,3)); // 4.
		for(int i=0;i<4;i++)
		{
			rotate4x(cubes,yuz2,i+1,tumdegerler.get(i));
		}
	}
	
	public static void usttekiyuzdegis(Character[][][] cubes,int yuz,int yuz2,int yuz3)
	{
		ayarla(cubes,yuz,yuz2);
		ayarla(cubes,yuz2,yuz3);
		ayarla(cubes,yuz3,yuz);
		
		int a[] = diziyiTopla(cubes,yuz);
		rotate20x(cubes,yuz2,5,a[0]);
		
		
	}
	
	public static void alttakiyuzdegis(Character[][][] cubes,int yuz , int yuz2,int yuz3)
	{
		Character[][] yeni = new Character[6][];
		for (int i = 0; i < 6; i++) {
			yeni[i] = Arrays.copyOf(cubes[yuz][i], 6);
	    }
		Character[][] yeni2 = new Character[6][];
		for (int i = 0; i < 6; i++) {
			yeni2[i] = Arrays.copyOf(cubes[yuz2][i], 6);
	    }
		
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				cubes[yuz][i][j] = cubes[yuz3][i][j];
				cubes[yuz2][i][j] = yeni[i][j];
				cubes[yuz3][i][j] = yeni2[i][j];
			}
		}
	}
	
	
	public static int[] diziyiTopla(Character[][][] cubes,int yuz){
	    int toplam=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++) {
            if((i==0 && j==0) || (i==0 && j==5) || (i==5 && j==0) || (i==5 && j==5))
            	toplam+=cubes[yuz][i][j];	       
            }
        }
        int abc [] = {toplam%20,toplam%12,toplam%4};
        return abc; 
 }
	
	public static void printCubes(Character[][][] cubes)
	{
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				for(int k=0;k<6;k++)
				{
					System.out.print(cubes[i][j][k]+"  ");
				}
				System.out.println();
			}
		}
	}
	
	public static void rotate20x(Character cubes[][][],int yuz,int bolge,int cycleValue)
	{
		Character[][] yeni = new Character[6][6];
		if(bolge==5)
		{
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<6;j++)
				{
					yeni[i][j] = cubes[yuz][i][j];
				}
			}
		}
		else if(bolge==6)
		{
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<6;j++)
				{
					yeni[i][j] = cubes[yuz][i][j];
				}
			}
		}
		else if(bolge==3)
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					yeni[i][j] = cubes[yuz][i+3][j];
				}
			}
		}
		for(int q=0;q<cycleValue;q++)
		{
			 int row = 0, col = 0; 
			    char prev,curr;
		        int m=6,n=6;
		        while (row < m && col < n) 
		        { 
		            if (row + 1 == m || col + 1 == n) 
		                break; 
		      
		            // Store the first element of next 
		            // row, this element will replace  
		            // first element of current row 
		            prev = yeni[row + 1][col]; 
		      
		            // Move elements of first row  
		            // from the remaining rows  
		            for (int i = col; i < n; i++) 
		            { 
		                curr = yeni[row][i]; 
		                yeni[row][i] = prev; 
		                prev = curr; 
		            } 
		            row++; 
		      
		            // Move elements of last column 
		            // from the remaining columns  
		            for (int i = row; i < m; i++) 
		            { 
		                curr = yeni[i][n-1]; 
		                yeni[i][n-1] = prev; 
		                prev = curr; 
		            } 
		            n--; 
		      
		            // Move elements of last row  
		            // from the remaining rows  
		            if (row < m) 
		            { 
		                for (int i = n-1; i >= col; i--) 
		                { 
		                    curr = yeni[m-1][i]; 
		                    yeni[m-1][i] = prev; 
		                    prev = curr; 
		                } 
		            } 
		            m--; 
		      
		            // Move elements of first column 
		            // from the remaining rows  
		            if (col < n) 
		            { 
		                for (int i = m-1; i >= row; i--) 
		                { 
		                    curr = yeni[i][col]; 
		                    yeni[i][col] = prev; 
		                    prev = curr; 
		                } 
		            } 
		            col++; 
		        }  
		}
		Esle(cubes,yeni,bolge,yuz);
       // System.out.println(Arrays.deepToString(yeni));
	}
	public static void rotate4x(Character cubes[][][],int yuz,int bolge,int cycleValue)
	{
		Character[][] yeni = new Character[3][3];
		if(bolge==1)
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					yeni[i][j] = cubes[yuz][i][j];
				}
			}
		}
		else if(bolge==2)
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					yeni[i][j] = cubes[yuz][i][j+3];
				}
			}
		}
		else if(bolge==3)
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					yeni[i][j] = cubes[yuz][i+3][j];
				}
			}
		}
		else
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					yeni[i][j] = cubes[yuz][i+3][j+3];
				}
			}
		}
		// System.out.println(Arrays.deepToString(yeni));
		for(int q=0;q<cycleValue;q++)
		{
			 int row = 0, col = 0; 
			    char prev,curr;
		        int m=3,n=3;
		        while (row < m && col < n) 
		        { 
		            if (row + 1 == m || col + 1 == n) 
		                break; 
		      
		            // Store the first element of next 
		            // row, this element will replace  
		            // first element of current row 
		            prev = yeni[row + 1][col]; 
		      
		            // Move elements of first row  
		            // from the remaining rows  
		            for (int i = col; i < n; i++) 
		            { 
		                curr = yeni[row][i]; 
		                yeni[row][i] = prev; 
		                prev = curr; 
		            } 
		            row++; 
		      
		            // Move elements of last column 
		            // from the remaining columns  
		            for (int i = row; i < m; i++) 
		            { 
		                curr = yeni[i][n-1]; 
		                yeni[i][n-1] = prev; 
		                prev = curr; 
		            } 
		            n--; 
		      
		            // Move elements of last row  
		            // from the remaining rows  
		            if (row < m) 
		            { 
		                for (int i = n-1; i >= col; i--) 
		                { 
		                    curr = yeni[m-1][i]; 
		                    yeni[m-1][i] = prev; 
		                    prev = curr; 
		                } 
		            } 
		            m--; 
		      
		            // Move elements of first column 
		            // from the remaining rows  
		            if (col < n) 
		            { 
		                for (int i = m-1; i >= row; i--) 
		                { 
		                    curr = yeni[i][col]; 
		                    yeni[i][col] = prev; 
		                    prev = curr; 
		                } 
		            } 
		            col++; 
		        }  
		}
		Esle(cubes,yeni,bolge,yuz);
       // System.out.println(Arrays.deepToString(yeni));
	}
	
	public static void Esle(Character[][][] buyukmatris,Character kucukmatris[][],int bolge,int yuz){
		 if(bolge == 1) {
			 for (int i = 0; i < 3; i++) {
				 for (int j = 0; j < 3; j++) {
					 buyukmatris[yuz][i][j] = kucukmatris[i][j]; 
				 }
			}
		 }
		 else if(bolge==2) {
			 for (int i = 0; i < 3; i++) {
				 for(int j=0; j<3; j++) {
					 buyukmatris[yuz][i][j+3] = kucukmatris[i][j]; 
				 }
			 }
		 }
		 else if(bolge==3) {
			 for (int i = 0; i < 3; i++) {
				 for(int j=0; j<3; j++) {
					 buyukmatris[yuz][i+3][j] = kucukmatris[i][j];
				 }
		 }
		 }
		 else if(bolge==4) {
			 for (int i = 0; i < 3; i++) {
				 for(int j=0; j<3; j++) {
					 buyukmatris[yuz][i+3][j+3] = kucukmatris[i][j];
				 }
		 }
		 }
		 else if(bolge==5) {
			 for (int i = 0; i < 6; i++) {
				 for(int j=0; j<6; j++) {
					 buyukmatris[yuz][i][j] = kucukmatris[i][j];
				 }
		 }
		 }
	 }

}
