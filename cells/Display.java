package cells;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display {
	public static int xsize = 50;
	public static int ysize = 50;
	public static boolean play=false;
	public static Component[][] array = new Component[xsize][ysize];
	
	public static void makeDisplay(){
		
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		frame.setSize(new Dimension(1000,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		for(int i=0;i<xsize;i++){
			JPanel pane = new JPanel();
			pane.setLayout(new GridLayout(100,100));
			pane.setSize(new Dimension(800,800));
			for(int j=0;j<ysize;j++){
				array[i][j] = new Cell(0,0);
				pane.add((((Cell) array[i][j]).cellDisplay(0)));
			}
			frame.add(pane);
		}

		final JButton start = new JButton("Play");
		start.setPreferredSize(new Dimension(50,100));
		frame.add(start);
		start.addActionListener(new ActionListener(){
			int text = 0;
			public void actionPerformed(ActionEvent e) {
				if(text==1){
					start.setText("Play");
					text=0;
				}else{
					start.setText("Pause");
					text=1;
				}
				play=!play;
			}

		});
		
		JButton clear = new JButton("Clear");
		clear.setPreferredSize(new Dimension(50,100));
		frame.add(clear);
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean holder=play;
				play = false;
				for(int i=0;i<Display.xsize;i++){
					for(int j=0;j<Display.ysize;j++){
						((Cell)array[i][j]).cellDisplay(0);
						((Cell)array[i][j]).setstate(0);
					}
				}
				play = holder;
			}

		});
		JButton randomize = new JButton("Randomize");
		clear.setPreferredSize(new Dimension(50,100));
		frame.add(randomize);
		randomize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<Display.xsize;i++){
					for(int j=0;j<Display.ysize;j++){
						Random num = new Random();
						int n = num.nextInt((1-0)+1);
						((Cell)array[i][j]).cellDisplay(n);
						((Cell)array[i][j]).setstate(n);
					}
				}
			}

		});
		frame.setVisible(true);
		
	}
	
	public static void main(String[]args){
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		makeDisplay();
		Thread go = new Thread(r);
		go.start();
		
	}
	static Runnable r = new Runnable(){
		public void run(){
			try{
				while(true){
					if(play){
						Cell.update();
						Thread.sleep(100);
					}
					else{
						Thread.sleep(100);
					}
				}
			}
			catch(InterruptedException iex){}
		}
	};

}
