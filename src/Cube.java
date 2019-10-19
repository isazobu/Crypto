import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;


public class Cube {
	
	
	private static void addPanel(JPanel panel) throws Exception
	{
			panel.setLayout(null); 
			JTextField jTextBox = new JTextField();
	        JLabel jLabel = new JLabel("Text");
	        JButton jButton = new JButton("Oluþtur"); 
	        JTextArea jt = new JTextArea(" ", 10, 10); 
	        
	        
	        jTextBox.setBounds(70, 30, 300, 20); // x,y,width,height
	        jLabel.setBounds(10,30,150,20);
	        jButton.setBounds(120, 100, 120, 20); 
	        jt.setBounds(10,200,360,145);
	        jt.setEditable(false); // disable edit
	        jt.setLineWrap(true);
	        
	        JRadioButton rb1=new JRadioButton("Encryption");    
	        rb1.setBounds(90,50,100,30);      
	        JRadioButton rb2=new JRadioButton("Decryption");    
	        rb2.setBounds(200,50,100,30);    
	        ButtonGroup bg=new ButtonGroup();    
	        bg.add(rb1);bg.add(rb2);    
   
	        
	        jButton.addActionListener(new ActionListener(){ // when jButton clicked
				@Override
				public void actionPerformed(ActionEvent e) {
					if(rb1.isSelected())
					{
						String plainText = jTextBox.getText();
						String cipherText = encrypt(cubes, plainText);
						jt.setText(cipherText);
					}
					else if(rb2.isSelected())
					{
						String cipherText = jTextBox.getText();
						String plainText = decrypt(cubesDecrypt, cipherText);
						jt.setText(plainText);
					}
					
				}
	        });
	        
	        panel.add(jTextBox);
	        panel.add(jLabel);
	        panel.add(jButton);
	        panel.add(jt);  
	        panel.add(rb1);
	        panel.add(rb2); 
	}
	
	//static char[] alphabet = "abcdefghiýjklmnoprsþtuüvyz0123456789".toCharArray();
	static char[] alphabet = "abcdefghijklmnoprstuvwxyz0123456789 ".toCharArray();
	static Character[][][] cubes = new Character[6][6][6];
	static Character[][][] cubesDecrypt = new Character[6][6][6];
	static MatrixOperations matrixInstance = new MatrixOperations();
	

	public static void alttakiyüzdegis(Character[][][] cubes,int yuz , int yuz2,int yuz3)
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
	
	public static String encrypt(Character cubes[][][],String plainText)
	{
		StringBuilder cipherText=new StringBuilder();
		int sayac=0;
		int sayac2=0;
		for(int i=0;i<plainText.length()%3;i++)
			plainText += "j";
		
		for(int i=0;i<plainText.length();i++)
		{
			sayac=0;
			if(i%3==0 && i!=0)
			{
				if(sayac2%2==1)
				{
					matrixInstance.alttakiyüzdegis(cubes,3,4,5);
				}
				else
					matrixInstance.alttakiyüzdegis(cubes,0,1,2);
			}		

			int[] sayilar = turnIndex(cubes,plainText.charAt(i++), sayac);
			cipherText.append(cubes[sayac+3][sayilar[0]][sayilar[1]]);
			sayac++;
			sayilar = turnIndex(cubes,plainText.charAt(i++), sayac);
			cipherText.append(cubes[sayac+3][sayilar[0]][sayilar[1]]);
			sayac++;
			sayilar = turnIndex(cubes,plainText.charAt(i), sayac);
			cipherText.append(cubes[sayac+3][sayilar[0]][sayilar[1]]);
			
		}
	
	
		return cipherText.toString();
	}
	
	public static String decrypt(Character cubes[][][],String cipherText)
	{
		int sayac=3;
		int sayac2=0;
		StringBuilder plainText=new StringBuilder("");
		
		for(int i=0;i<cipherText.length();i++)
		{
			sayac=3;
			if(i%3==0 && i!=0)
			{
				if(sayac2%2==1)
				{
					matrixInstance.alttakiyüzdegis(cubes,3,4,5);
				}
				else
					matrixInstance.alttakiyüzdegis(cubes,0,1,2);
			}		
			int[] sayilar = turnIndex(cubes,cipherText.charAt(i++), sayac);
			plainText.append(cubes[sayac-3][sayilar[0]][sayilar[1]]);
			sayac++;
			sayilar = turnIndex(cubes,cipherText.charAt(i++), sayac);
			plainText.append(cubes[sayac-3][sayilar[0]][sayilar[1]]);
			sayac++;
			sayilar = turnIndex(cubes,cipherText.charAt(i), sayac);
			plainText.append(cubes[sayac-3][sayilar[0]][sayilar[1]]);
			
		}
		
	
		return plainText.toString();
	}
	
	public static int[] turnIndex(Character cubes[][][] , Character a , int yuz)
	{
		for(int j=0;j<6;j++)
		{
			for(int k=0;k<6;k++)
			{
				if(cubes[yuz][j][k] == a)
				{
					return new int[] {j,k};
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception 
	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		Random rnd = new Random();
		int number;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				for(int k=0;k<6;k++)
				{
					number = rnd.nextInt(alphabet.length);
					while(array.contains(number))
						number = rnd.nextInt(alphabet.length);
					array.add(number);
					
					cubes[i][j][k] = alphabet[number];
					cubesDecrypt[i][j][k] = alphabet[number];
				}	
			}	
			array.clear();
		}
		printCubes();
		String plainText = "furkankah";
		String cipherText = encrypt(cubes, plainText);
		
		System.out.println("P.T = "+plainText);
		System.out.println("C.T = "+cipherText);
		
		
		System.out.println("P.T = "+decrypt(cubesDecrypt,cipherText));
		
		/*JFrame frame = new JFrame("OTP");
	   	frame.setSize(400, 400);
	   	frame.setResizable(false); // disable maximize/resize button
	   	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   	
	   	JPanel panel = new JPanel();   
	   	frame.add(panel);
	   	addPanel(panel);
	   	frame.setVisible(true);	   */	
		
	}
	
	public static void printCubes()
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
			System.out.println("yüz bitiþi");
		}
	}
}
