package Algoritma;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GenetikAlgoritma {
	static DecimalFormat df = new DecimalFormat("####0.00");
	static String output="";
	public String Result(){
		output="";
		int y=decimalNumber(0.31F); // rakamı 0 kurtardık.
		int m = decimalToBinary(y).length();
		output+=y+" : "+decimalToBinary(y)+" m : "+m+"\n";

		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		int popsize=Gui.popsize;
	
		String[] kromozom=new String[popsize];
		
		int xmax=0,eniyikromozom=0;
		int[] cozumleneny=new int[popsize];
		
		for(int i=0;i<popsize;i++) {
			
			kromozom[i]=randomKromozom(m);//kromozomu oluşturduk
			int cozumlenenx= kromozomCozumle(Integer.parseInt(kromozom[i]));//kromozomu çözümledik.
			cozumleneny[i]=(int)Math.pow(cozumlenenx, 2);
			output+=(i+1)+".kromozom : "+kromozom[i]+ " = "+cozumlenenx+"  →  y : "+cozumleneny[i]+"\n";
			if(cozumlenenx>xmax) {
				xmax=cozumlenenx;//en yüksek kromozomu bulduk
				eniyikromozom=i+1;
			}
		}

		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		output+="1. jenerasyonda uygunluk fonksiyonu en yüksek değeri V"+eniyikromozom+" için sağlanmaktadır."
		+"\n"+"Bu yüzden ilk jenerasyonumun en iyi kromozomu "+eniyikromozom+". kromozomdur."+"\n";
		
		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		output+="Xmax : "+xmax+" Fmax : "+(int)Math.pow(xmax, 2)+"\n";

		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		double q[]=ruletTekeri(cozumleneny);

		double r[]=new double[popsize];
		output+="\n"+"0 ile 1 arasında "+ popsize +" adet sayı üretiyoruz"+"\n";
		for(int i=0;i<popsize;i++) {
			r[i]=Math.random()+0.01;
			output+="r"+(i+1)+" : "+df.format(r[i])+" ";
		}
		output+="\n------------------------------------------------------------------------------------------------------------"+"\n";

		String[] kromozomyeni=new String[popsize];
		for(int i=0;i<popsize;i++) {
			int t=0;
			kromozomyeni[i]=kromozomDegisim(kromozom,q,r,i,t);
		}
		
		output+="Eski kromozomların yerine yeni kromozomlar geçer."+"\n";
		
		for(int i=0;i<popsize;i++)
			output+=(i+1)+".kromozom : "+kromozomyeni[i]+"\n";
		
		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		double pj=Gui.pj;
		
		int caprazlamasayisi=(int) Math.round(popsize*pj);
		output+="Çaprazlamaya maruz kalıcak kromozom sayısı : "+caprazlamasayisi+"\n";
		
		if(caprazlamasayisi%2==1) {
			caprazlamasayisi--;
			output+="Çaprazlamaya maruz kalıcak kromozom sayısı çift olmalıdır. Bu yüzden kromozom sayısı: "+caprazlamasayisi+"\n";
			
		}
		
		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		double r2[]=new double[popsize];
		int caprazlanacakkromozom[]=new int[caprazlamasayisi];
		int t=0;
		output+="0 ile 1 arasında "+ popsize +" adet sayı üretiyoruz"+"\n";
		
		//Bu for çaprazlamaya girecek kromozomları bulmaya yarıyor.
		for(int i=0;i<popsize;i++) {
			r2[i]=Math.random()+0.01;
			if(r2[i]>pj)output+="r"+(i+1)+" : "+df.format(r2[i])+" > "+pj+"\n";
			else{
				output+="r"+(i+1)+" : "+df.format(r2[i])+" < "+pj+"\n";
				if(t<caprazlamasayisi){
					caprazlanacakkromozom[t]=i;
					t++;
				}
			}
		}
		
		for(int i=0;i<caprazlanacakkromozom.length;i++) {
			output+="r"+(caprazlanacakkromozom[i]+1)+" ";
		}
		output+="Çaprazlamaya maruz kalıcak kromozomları rastgele seçtikten sonra"
	    +"\nÇaprazlanacak kromozom çifti için [1,"+(m-1)+"] aralıkta rastgele pos değeri üretilir."+"\n";
		
		int[] pos=new int[caprazlamasayisi/2];
		
		
		for(int i=0;i<pos.length;i++)
			pos[i]=(int)(Math.random()*(m-1));
		
		kromozomCaprazla(pos,kromozomyeni,caprazlanacakkromozom);
		
		output+="Çaprazlama sonrası oluşan kromozomlar;"+"\n";
		for(int i=0;i<popsize;i++)
			output+=(i+1)+".kromozom : "+kromozomyeni[i]+"\n";
		
		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		double pmutasyon=Gui.pm;
		int gensayisi=(int) Math.round(popsize*m*pmutasyon);
		
		output+="pm : "+pmutasyon+" Mutasyona maruz kalacak gen sayısı : "+gensayisi+"\n";
		
		output+="1. yaklaşımda popülasyonda toplam "+(popsize*m)+" gen mevcuttur bu yüzden [0,"+gensayisi+"] aralığında\n"
		+(popsize*m)+" gerçel sayı üretiyoruz. Eğer üretilen sayı pm:"+pmutasyon+"'den küçükse gen mutasyona uğrar."+"\n";
		
		output+="------------------------------------------------------------------------------------------------------------"+"\n";
		
		double[] gercelsayi=new double[popsize*m];
		int[] mutasyongeni=new int[popsize*m];
		int index=0;
		
		//Bu for mutasyon genini bulmaya yarıyor
		for(int i=0;i<popsize*m;i++) {
			if(index==gensayisi)break;
			gercelsayi[i]=Math.random()+(gensayisi-1);
			output+="r"+(i+1)+" : "+df.format(gercelsayi[i])+" ";
			if((i+1)%popsize==0) output+="\n";
			if(gercelsayi[i]<pmutasyon) {
				mutasyongeni[index]=i;
				index++;
			}
	
		}
		
		output+="\n------------------------------------------------------------------------------------------------------------"+"\n";
		
			if(index>0) {
				for(int i=0;i<mutasyongeni.length;i++) {
					if(mutasyongeni[i]>0)
						output+=mutasyongeni[i]+1+".gen mutasyona uğrar"+"\n";
				}
			}
			else {
				output+="Mutasyona uğramış gen bulunmamaktadır."+"\n";
			}
			if(index>0) {
				Character[] kromozomyeni2=new Character[popsize*m];
				int x=0;
				t=0;
				
				//Bu for sayesinde kromozomlar karakter dizisine aktarılır 
				for(int i=0;i<popsize*m;i++) {
					kromozomyeni2[i]=kromozomyeni[t].charAt(x);
					x++;
					if((i+1)%m==0) {
						t++;
						x=0;
					}
				}
				
				//gen mutasyona uğrar
				for(int i=0;i<mutasyongeni.length;i++){
					if(kromozomyeni2[mutasyongeni[i]]=='0')
						kromozomyeni2[mutasyongeni[i]]='1';
					else
						kromozomyeni2[mutasyongeni[i]]='0';
				}
				String cikti="";
				t=0;
				
				//Bu for mutasyon sonrası değişen kromozomları yeniler.
				for(int i=0;i<popsize*m;i++) {
					cikti+=kromozomyeni2[i];
					if((i+1)%m==0) {
						kromozomyeni[t]=cikti;
						t++;
						cikti="";
					}
				}

				output+="------------------------------------------------------------------------------------------------------------"+"\n";
				output+="Mutasyon sonrası oluşan kromozomlar;\n";
			}
			
			xmax=0;
			for(int i=0;i<popsize;i++) {
				
				int cozumlenenx= kromozomCozumle(Integer.parseInt(kromozomyeni[i]));
				cozumleneny[i]=(int)Math.pow(cozumlenenx, 2);
				output+=(i+1)+".kromozom : "+kromozomyeni[i]+ " = "+cozumlenenx+"  →  y : "+cozumleneny[i]+"\n";
				if(cozumlenenx>xmax) {
					xmax=cozumlenenx;
					eniyikromozom=i+1;
				}
			}

			output+="------------------------------------------------------------------------------------------------------------"+"\n";
			
			output+="Mutasyon sonrası oluşan kromozomları uygunluk fonksiyonuna tekrardan sokuyoruz ve \nsoruyu noktalıyoruz."+"\n";

			output+="------------------------------------------------------------------------------------------------------------"+"\n";
			
			output+="2. jenerasyonda uygunluk fonksiyonu en yüksek değeri V"+eniyikromozom+" için sağlanmaktadır."
			+"\n"+"Bu yüzden ikinci jenerasyonumun en iyi kromozomu "+eniyikromozom+". kromozomdur."+"\n";
			output+="Xmax : "+xmax+" Fmax : "+(int)Math.pow(xmax, 2)+"\n";
			return output;
	}
	
	//Sayıyı 0. dan kurtardık
	public static int decimalNumber(float number) {
		
		String y = Float.toString(number);
		String x = y.substring(2, y.length());
		return Integer.parseInt(x);
		
	}
	
	//2 lik sayı 10 luk sayıya döndü
	public static String decimalToBinary(int number) {
		
		 int binaryNumber;
         String result = "";
         
         while (number > 0)
         {
        	 binaryNumber = number % 2;
             number /= 2;
             result = String.valueOf(binaryNumber) + result;
         }
         
         return result;
         
     }
	
	//Random m basamaklı kromozomlar üretildi.
	public static String randomKromozom(int m) {
		 int binaryNumber;
         String result = "";
         for(int i=0;i<m;i++) {
        	 binaryNumber=(int)(Math.random()*2);
             result += String.valueOf(binaryNumber);
         }
         return result;
	}
	
	//Kromozomlar çözümlendi
	public static int kromozomCozumle(int kromozom) {
		    int onlukSayi = 0, i = 0;
	        int kalan;
	        while (kromozom != 0)
	        {
	            kalan = kromozom % 10;
	            kromozom /= 10;
	            onlukSayi += kalan * Math.pow(2, i);
	            ++i;
	        }
	        return onlukSayi;
	}
	
	//rulet tekeri oluşturuldu ve q değerleri bulundu
	public static double[] ruletTekeri(int[] cozumlenen) {
		
		double toplam=0;
		double[] p=new double[cozumlenen.length];
		double[] q=new double[cozumlenen.length];
		for(int i=0;i<cozumlenen.length;i++) {
			toplam+=cozumlenen[i];
		}
		
		for(int i=0;i<cozumlenen.length;i++) {
			p[i]=(double)cozumlenen[i]/toplam;
			output+="p"+(i+1)+" : "+df.format(p[i])+" ";
		}
		output+="\n------------------------------------------------------------------------------------------------------------"+"\n";
		for(int i=0;i<cozumlenen.length;i++) {
			if(i==0)
				q[i]=p[i];
			else
				q[i]=q[i-1]+p[i];
				output+="q"+(i+1)+" : "+df.format(q[i])+" ";
		}
		return q;
	}
	
	//kromozomlar çaprazlanıyor.
	public static void kromozomCaprazla(int[] pos,String[] kromozom,int[] capindex) {//capindex=çaprazlanacak index
		
		int k=0;
		for(int i=0;i<pos.length;i++) {
			output+="pos : "+pos[i]+" "+kromozom[capindex[k]]+" → "+kromozom[capindex[k+1]]+"\n";
			String ilk=kromozom[capindex[k]].substring(pos[i]);//Kromozomun pos değerine göre değeri alınıyor
			String ikinci=kromozom[capindex[k+1]].substring(pos[i]);
			kromozom[capindex[k]]=kromozom[capindex[k]].substring(0,pos[i])+""+kromozom[capindex[k]].substring((pos[i])).replaceAll(ilk, ikinci);
			//kromozomun pos a kadar olan kısmı aynı kalıyor.Ondan sonraki kısmı replaceAll metodu ile değişiyor.
			kromozom[capindex[k+1]]=kromozom[capindex[k+1]].substring(0,pos[i])+kromozom[capindex[k+1]].substring((pos[i])).replaceAll(ikinci, ilk);
			k=k+2;
			output+="Ilk:"+ilk+" Ikinci:"+ikinci+"\n";
		}
	}
	public static String kromozomDegisim(String[] kromozom,double[] q,double[] r,int gelenindex,int gezenindex) {
		String newkromozom = "";
		if(kromozom.length>gezenindex) {
			if(r[gelenindex]>=q[kromozom.length-(gezenindex+1)]) {
				//gelenindex=0 gezenindex=0 r[0]>q[4] ise bu if e gir.
				//başa döndü gezenindex=1 r[0]>q[3] ise bu if e gir.
					newkromozom=kromozom[kromozom.length-(gezenindex)];
			}
			else
				return kromozomDegisim(kromozom, q, r,gelenindex, gezenindex+1);//değilse gezenindex i artır
		}
		else {
			if(newkromozom.equals(""))//eğer hep küçükse 1.kromozomdur.
				newkromozom=kromozom[0];
		}
		return newkromozom;
	}
}

