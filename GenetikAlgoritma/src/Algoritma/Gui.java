package Algoritma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Gui extends JFrame implements ActionListener{

	static int popsize;
	static double pj,pm;
	JLabel lblInput,lblInputSide,lblResult,lblResultSide,lblPopSize,lblCaprazlamaOran,lblMutasyonOran;
	JTextField txtPopSize,txtCaprazlamaOran,txtMutasyonOran;
	JButton btnBaslat,btnSıfırla;
	JTextArea txtSonuc;
	JScrollPane scrollSonuc;
	
	public Gui() {
		setTitle("Genetik Algoritma");
		setSize(1068,621);
		setLocationRelativeTo(null);
		setUndecorated(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("src/algoritma/bg.jpg")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		lblInput=new JLabel("  Değerleri Giriniz");
		lblInput.setBounds(60, 50, 380, 40);
		lblInput.setBackground(Color.white);
		lblInput.setForeground(Color.DARK_GRAY);
		lblInput.setOpaque(true);
		lblInput.setFont(new Font("Arial",Font.TYPE1_FONT,20));
		lblInputSide=new JLabel();
		lblInputSide.setBounds(60, 90, 380, 280);
		Border border = BorderFactory.createLineBorder(Color.white, 2);
		lblInputSide.setBorder(border);
		lblInputSide.setForeground(Color.DARK_GRAY);
		lblInputSide.setOpaque(false);
		lblPopSize=new JLabel("Popülasyon Boyutu  : ");
		lblPopSize.setBounds(80, 120, 150, 40);
		lblPopSize.setForeground(Color.white);
		lblPopSize.setFont(new Font("Arial",Font.BOLD,12));
		txtPopSize=new JTextField();
		txtPopSize.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txtPopSize.getText().length() >= 2 )
	                e.consume();
	        }
	    });
		txtPopSize.setBounds(210,121,200,40);
		txtPopSize.setFont(new Font("Arial",Font.TYPE1_FONT,17));
		txtPopSize.setHorizontalAlignment(txtPopSize.CENTER);
		txtPopSize.setBorder(null);
		lblCaprazlamaOran=new JLabel("Çaprazlama İhtimali : ");
		lblCaprazlamaOran.setBounds(80, 180, 150, 40);
		lblCaprazlamaOran.setForeground(Color.white);
		lblCaprazlamaOran.setFont(new Font("Arial",Font.BOLD,12));
		txtCaprazlamaOran=new JTextField();
		txtCaprazlamaOran.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txtCaprazlamaOran.getText().length() >= 4 )
	                e.consume();
	        }
	    });
		txtCaprazlamaOran.setBounds(210,181,200,40);
		txtCaprazlamaOran.setFont(new Font("Arial",Font.TYPE1_FONT,17));
		txtCaprazlamaOran.setHorizontalAlignment(txtPopSize.CENTER);
		txtCaprazlamaOran.setBorder(null);
		lblMutasyonOran=new JLabel("Mutasyon İhtimali     : ");
		lblMutasyonOran.setBounds(80, 240, 150, 40);
		lblMutasyonOran.setForeground(Color.white);
		lblMutasyonOran.setFont(new Font("Arial",Font.BOLD,12));
		txtMutasyonOran=new JTextField();
		txtMutasyonOran.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txtMutasyonOran.getText().length() >= 4 )
	                e.consume();
	        }
	    });
		txtMutasyonOran.setBounds(210,241,200,40);
		txtMutasyonOran.setFont(new Font("Arial",Font.TYPE1_FONT,17));
		txtMutasyonOran.setHorizontalAlignment(txtPopSize.CENTER);
		txtMutasyonOran.setBorder(null);
		btnBaslat=new JButton("Başla");
		btnBaslat.setBounds(210,300,95,40);
		btnBaslat.setBorder(null);
		btnBaslat.setBackground(Color.white);
		btnBaslat.setForeground(Color.DARK_GRAY);
		btnBaslat.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		btnBaslat.addActionListener(this);
		btnSıfırla=new JButton("Sıfırla");
		btnSıfırla.setBounds(315,300,95,40);
		btnSıfırla.setBorder(null);
		btnSıfırla.setBackground(Color.white);
		btnSıfırla.setForeground(Color.DARK_GRAY);
		btnSıfırla.setEnabled(false);
		btnSıfırla.addActionListener(this);
		btnSıfırla.setFont(new Font("Courier",Font.TYPE1_FONT,17));
		lblResult=new JLabel("  Sonuçlar");
		lblResult.setBounds(500, 50, 490, 40);
		lblResult.setBackground(Color.white);
		lblResult.setForeground(Color.DARK_GRAY);
		lblResult.setOpaque(true);
		lblResult.setFont(new Font("Arial",Font.TYPE1_FONT,20));
		lblResultSide=new JLabel();
		lblResultSide.setBounds(500, 90, 490, 440);
		lblResultSide.setBorder(border);
		lblResultSide.setForeground(Color.DARK_GRAY);
		lblResultSide.setOpaque(false);
		add(lblInput);
		txtSonuc=new JTextArea();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
		scrollSonuc=new JScrollPane(txtSonuc);
		scrollSonuc.setBounds(510,100,470,420);
		scrollSonuc.setBorder(null);
		scrollSonuc.setVisible(false);
		txtSonuc.setMargin(new Insets(10, 10, 10, 10));
		txtSonuc.setLineWrap(true);
		txtSonuc.setEditable(false);
		txtSonuc.setFont(new Font("Arial",Font.PLAIN,12));
		add(lblInputSide);
		add(lblResult);
		add(lblResultSide);
		add(lblPopSize);
		add(txtPopSize);
		add(lblCaprazlamaOran);
		add(txtCaprazlamaOran);
		add(lblMutasyonOran);
		add(txtMutasyonOran);
		add(btnBaslat);
		add(btnSıfırla);
		add(scrollSonuc);
	}
	
	public static void main(String[]args) {
		Gui gui = new Gui();
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnBaslat) {
			String hata="";
			boolean kontrol=true;
			if(txtPopSize.getText().equals("") || txtCaprazlamaOran.getText().equals("") || txtMutasyonOran.getText().equals("")) {
				hata+="Lütfen boş alan bırakmayınız.";
				kontrol=false;
			}
			else {
				try {
					popsize = Integer.parseInt(txtPopSize.getText());
					pj = Double.parseDouble(txtCaprazlamaOran.getText());
					pm = Double.parseDouble(txtMutasyonOran.getText());
			    } catch (NumberFormatException nfe) {
			    	txtPopSize.setText("");
			    	txtCaprazlamaOran.setText("");
			    	txtMutasyonOran.setText("");
			    	kontrol=false;
			    }
				if(popsize<3) {
					hata+="Popülasyon boyutu minumum 3 olmalıdır.\n";
					kontrol=false;
				}
				if(pj>1 || pj<0) {
					hata+="Çaprazlama ihtimali 0 ile 1 arasında olmalıdır.\n";
					kontrol=false;
				}
				if(pm>1 || pm<0) {
					hata+="Mutasyon oranı 0 ile 1 arasında olmalıdır.";
					kontrol=false;
				}
			}
			if(kontrol==false) {
				JOptionPane.showMessageDialog(null,hata);
			}
			else {
				GenetikAlgoritma ga = new GenetikAlgoritma();
				txtSonuc.setText(ga.Result());
				scrollSonuc.setVisible(true);
				btnBaslat.setEnabled(false);
				btnSıfırla.setEnabled(true);
			}
		}
		if(e.getSource()==btnSıfırla) {
			txtSonuc.setText("");
			scrollSonuc.setVisible(false);
			btnSıfırla.setEnabled(false);
			btnBaslat.setEnabled(true);
		}
		
	}

}
