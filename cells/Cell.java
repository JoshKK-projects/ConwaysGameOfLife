package cells;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Cell extends Component {
	JButton disp;
	int checker;
	int state;
	
	public Cell(int check,int stat){
		checker=check;
		state=stat;
		JButton butt = new JButton();
		butt.setPreferredSize(new Dimension(50,50));
		butt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(state==0){
					this.cellDisplay(1);
					this.setstate(1);
				}else{
					this.cellDisplay(0);
					this.setstate(0);
				}
				
			}
			private void cellDisplay(int color) {
				if(color==0){
					disp.setBackground(Color.WHITE);
				}
				else if(color==1){
					disp.setBackground(Color.RED);
				}
			}
			public void setstate(int stat){
				state = stat;
			}

		});
		disp = butt;
	}
	public JButton cellDisplay(int color){
		if(color==0){
			disp.setBackground(Color.WHITE);
		}
		else if(color==1){
			disp.setBackground(Color.RED);
		}
		return disp;
	}
	public void setcheck(int check){
		this.checker = check;
	}
	public void setstate(int stat){
		this.state = stat;
	}
	public int checkget(){
		return this.checker;
	}
	public int stateget(){
		return state;
	}
	public static void update(){
		
		for(int i=0;i<Display.xsize;i++){
			for(int j=0;j<Display.ysize;j++){
				int chek = 0;
				if(j<Display.ysize-1 && ((Cell) Display.array[i][j+1]).stateget()==1){
					chek++;
				}
				if(i<Display.xsize-1 &&((Cell) Display.array[i+1][j]).stateget()==1){
					chek++;
				}
				if(j>0 &&((Cell) Display.array[i][j-1]).stateget()==1){
					chek++;
				}
				if(i>0 &&((Cell) Display.array[i-1][j]).stateget()==1){
					chek++;
				}
				if((j<Display.ysize-1 && i<Display.xsize-1)&&((Cell) Display.array[i+1][j+1]).stateget()==1){
					chek++;
				}
				if((i<Display.xsize-1 &&j>0) &&((Cell) Display.array[i+1][j-1]).stateget()==1){
					chek++;
				}
				if((j<Display.ysize-1 && i>0)&&((Cell) Display.array[i-1][j+1]).stateget()==1){
					chek++;
				}
				if((i>0 &&j>0) &&((Cell) Display.array[i-1][j-1]).stateget()==1){
					chek++;
				}
				((Cell) Display.array[i][j]).setcheck(chek);
			}
		}
		for(int i=0;i<Display.xsize;i++){
			for(int j=0;j<Display.ysize;j++){
				if(((Cell)Display.array[i][j]).checker<2){
					((Cell)Display.array[i][j]).cellDisplay(0);
					((Cell) Display.array[i][j]).setstate(0);
				}
				/*if((((Cell)Display.array[i][j]).checker==2 || ((Cell)Display.array[i][j]).checker==3)&&((Cell)Display.array[i][j]).state==1){
					((Cell)Display.array[i][j]).cellDisplay(1);
					((Cell) Display.array[i][j]).setstate(1);
				}*/
				if(((Cell)Display.array[i][j]).checker>3){
					((Cell)Display.array[i][j]).cellDisplay(0);
					((Cell) Display.array[i][j]).setstate(0);
				}
				if(((Cell)Display.array[i][j]).checker==3 &&((Cell)Display.array[i][j]).stateget()==0){
					((Cell)Display.array[i][j]).cellDisplay(1);
					((Cell) Display.array[i][j]).setstate(1);
				}
			}
		}
		
	}

}
