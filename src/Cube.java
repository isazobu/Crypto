import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;




/*
   
    Kriptoloji şifre bilimidir. Çeşitli iletilmek istenen textlerin güvenli bir ortamda alıcıya iletilmesi ve alıcının da bu texti deşifre etmesidir.
    Tarih boyunca sayIsIz Şifreleme yÖntemleri kullanIlmIŞtIr. Bu yÖntemlerin birçoĞu kullanIldIĞI dÖnemlerde kIrIlamayan yÖntemler olarak nitelendirilmiŞ olmasIna raĞmen zaman içerisinde kendilerine duyulan gÜveni yitirerek tarih içerisinde yerlerini almIŞlardIr.
    Merak edenler için Örnek olarak(Caesar, Polybius, Vigenere Cipher vs.).
    Tarihte kullanIlan Şifreleme yÖntemlerinin Java diliyle yazIlmIŞ kodlarIna da burdan ulaŞabilirsiniz.(https://github.com/furkankahvecii/traditional-ciphers)
    
    KodlarIn açIklamasI ve yapIlan yÖntemler;
    - RÜbik KÜp mekanik bir bulmacadIr. Bu Ödev için simetrik bir Şifreleme yÖntemi yazmak istedim ve bunu da RÜbik KÜp'Ün yardImI ile gerçekleŞtirdim.
    - RÜbik KÜp 2x2x2, 3x3x3, 4x4x4, 5x5x5, 6x6x6 vs. ÇeŞitleri vardIr. YazdIgIm programda 6x6x6 tipinde bir RÜbik KÜp'Ü anahtar olarak kullandIm.
    - Random olarak seçilen 216 tane character (english alphabet) rÜbik kÜpe yerleŞtirilir. Yani 6 yÜzÜnde de 6 tane english alphabet bulunur. Buna Şifreleme yÖntemlerinde (Polialfabetik Şifreleme) deniyor. Bu tip Şifrelemede mono alfabetik yÖntemlerden farklI olarak bir harf deĞiŞtirilince her seferinde aynI harfe dÖnÜŞmez.
    - KullanIcIdan alInan açIk metin(plain text) ve random olarak oluŞturulan kÜp Cryptography sInIfInIn encrypt metoduna gÖnderilir.
    - Gelen kÜp her bir karakteri Şifrelemeden Önce kÜpde bazI deĞiŞiklikler yapmaktadIr.
    - Bu deĞiŞiklikler = ilk 3 yÜzÜ diger 3 yÜzle deĞiŞ, yÜzleri satIr olarak deĞiŞ, yÜzleri sÜtun olarak deĞiŞ.
    - Şifreleme tek tek character ile yapIlIr ve seçilen yÜzde Şifrelenmek istenen karakterin, karŞIsIndaki yÜzde ki karŞIlIĞI Şifreli metine(cipherText) eklenir.
    - Şifrelemeler blok blok yapIldIgI için bu Şifreleme yÖntemi bir blok-Şifreleme (block-cipher) yÖntemine Örnektir. Her bir bloktan sonra kÜpte yine bazI deĞiŞiklikler yapIlmaktadIr. 
    - Bu deĞiŞiklikler; kÜpÜn her bir yÜzÜnÜn dIŞa bakan ve içteki kIsImlarInIn 90 derece kaydIrIlmsI ve kaç kere kaydIrIlcagInI(cycleValue) kÜpteki toplam sayIlarIn modu ile kararlaŞtIrIlan iŞlemler vardIr.
    - BloklarIn Şifrelemesi bittikten sonra , karŞImIza çIkan Şifreli metin(cipherText) kullanIcIya sunulur.
    - Şifreli metine sahip kiŞide bu Şifreli metini açIk metine çevirebilmesi iin random olarak seçilen 216 tane karakter bulunan kÜpÜn ilk hali bulunmalIdIr.
    - AçIk metin Şifrelenirken ne yapIldIysa , Şifreli metin açIk metine dÖnÜŞtÜlÜrken de tam tersi yapIlIr. Buna kabaca simetrik Şifreleme diyebiliriz. Şifrelenirken de Şifre çözerken de aynı anahtarı kullanma iŞlemi.
    - UygulamanIn collision'larI (çarpIŞmalarI) kesinlikle vardIr. GÜvenlik açIklarI kesinlikle vardIr. Bu Şifreleme merak ve hobi amacIyla Java Programlama Dilinde kodlanmIŞtIr. Hiç bir gÜvence saĞlamaz.
    - UygulamanIn kodlarIna -> https://github.com/isazobu/Crypto

*/

public class Cube {

	static char[] alphabet = "abcdefghijklmnoprstuvwxyz0123456789 ".toCharArray();
	static Character[][][] cubes = new Character[6][6][6];
	static Character[][][] cubesDecrypt = new Character[6][6][6];
	static Cryptography crypto = new Cryptography();

	public static void main(String[] args) throws Exception {
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
		
	
		JFrame frame = new JFrame("Halic Crypto");
	   	frame.setSize(400, 400);
	   	frame.setResizable(false); // disable maximize/resize button
	   	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   	
	   	JPanel panel = new JPanel();   
	   	frame.add(panel);
	   	addPanel(panel);
	   	frame.setVisible(true);	   
	}
	
	private static void addPanel(JPanel panel) throws Exception
	{
			panel.setLayout(null); 
			JTextField jTextBox = new JTextField();
	        JLabel jLabel = new JLabel("Plain Text = ");
	        JButton jButton = new JButton("Submit"); 
	        JTextArea jt = new JTextArea(" ", 10, 10); 
	        
	        
	        jTextBox.setBounds(80, 30, 250, 20); // x,y,width,height
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
						String cipherText = crypto.encrypt(cubes, plainText);
						jt.setText(cipherText);
						crypto.setPlainText(new StringBuilder(""));
					}
					else if(rb2.isSelected())
					{
						String cipherText = jTextBox.getText();
						String plainText = crypto.decrypt(cubesDecrypt, cipherText);
						jt.setText(plainText);
						crypto.setCipherText(new StringBuilder(""));
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
	
	public static void printCubes()
	{
		System.out.println("Anahtar");
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
			System.out.println();
		}
	}


}
