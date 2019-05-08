import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class GraExp1 extends JFrame{
	Container c;
	public class MyPoint { 
		public MyPoint(){
			this.x=0;this.y=0;
		}
		public MyPoint(int x,int y) { 
		this.x = x; 
		this.y = y; 
		}
		public int getX() {
		return x;
		}
		public int getY() {
		return y;
		}
		public void setXY(int x,int y){
			this.x=x;this.y=y;
		}
		private int x;
		private int y;
	}
	double[][] map=new double[20][20];
	int dis=0,count;
	int[] city=new int[20];
	boolean[] vis=new boolean[20];double[] length=new double[20];
	CInstead cd1;
	String[] zt={"慈溪市","富阳市","奉化市","杭州市","海宁市","建德市","金华市","临安市","临海市","兰溪市","宁波市","绍兴市","嵊州市","台州市","永康市","义乌市","余姚市","诸暨市","舟山市"};
	String[] zx={"慈溪市","富阳市","奉化市","杭州市","海宁市","建德市","金华市","临安市","临海市","兰溪市","宁波市","绍兴市","嵊州市","台州市","永康市","义乌市","余姚市","诸暨市","舟山市"};
	int begin,end;   
	MyPoint pnt[]=new MyPoint[20];
	JLabel lbl1=new JLabel("起始站：");
	JLabel lbl2=new JLabel("终点站：");
	JLabel lbl3=new JLabel("坐标：");
	JLabel lbl4=new JLabel("路线总长度为:：");
	JButton btn1=new JButton("设置距离");
	JTextField tf1=new JTextField(25);
	JTextField T=new JTextField(35);
	JTextField tf2=new JTextField(15);
	JButton btn2=new JButton("运行");
	JButton btn3=new JButton("退出");
	JButton btn4=new JButton("清空");
	ArrayList <Rectangle2D> squares;
    int SIZE = 10;
    Rectangle2D current;
	JComboBox cb1=new JComboBox(zt),cb2=new JComboBox(zx);
	private void init(){
		begin=0;end=0;count=0;
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
				if(i==j)
					map[i][j]=0;
				else
					map[i][j]=1000000;
				map[0][11]=82.4;map[0][10]=58.7;map[11][4]=72.1;map[11][3]=61.5;
				map[11][16]=71.8;map[11][12]=74.3;map[11][17]=59.5;map[10][16]=53.1;
				map[10][18]=84.9;map[10][2]=39.9;map[10][12]=98.8;map[12][2]=77.8;
				map[12][13]=153.9;map[12][8]=115;map[12][15]=92.6;map[2][13]=156.2;
				map[13][8]=20;map[8][14]=128.2;map[14][6]=57.9;map[6][15]=55.8;
				map[15][17]=64.5;map[17][3]=90.2;map[3][4]=40.5;map[3][7]=47.1;
				map[3][1]=47;map[1][5]=100.6;map[5][9]=116.7;map[9][6]=27.2;
				
				map[11][0]=82.4;map[10][0]=58.7;map[4][11]=72.1;map[3][11]=61.5;
				map[16][11]=71.8;map[12][11]=74.3;map[17][11]=59.5;map[16][10]=53.1;
				map[18][10]=84.9;map[2][10]=39.9;map[12][10]=98.8;map[2][12]=77.8;
				map[13][12]=153.9;map[8][12]=115;map[15][12]=92.6;map[13][2]=156.2;
				map[8][13]=20;map[14][8]=128.2;map[6][14]=57.9;map[15][6]=55.8;
				map[17][15]=64.5;map[3][17]=90.2;map[4][3]=40.5;map[7][3]=47.1;
				map[1][3]=47;map[5][1]=100.6;map[9][5]=116.7;map[6][9]=27.2;
				;
				for(int i=0;i<19;i++)
					pnt[i]= new MyPoint();
				pnt[0].setXY(385,270);pnt[1].setXY(149,293);pnt[2].setXY(413,380);
				pnt[3].setXY(185,242);pnt[4].setXY(284,192);pnt[5].setXY(25,415);
				pnt[6].setXY(95,495);pnt[7].setXY(104,259);pnt[8].setXY(364,548);
				pnt[9].setXY(58,473);pnt[10].setXY(440,331);pnt[11].setXY(264,304);
				pnt[12].setXY(307,392);pnt[13].setXY(418,583);pnt[14].setXY(164,540);
				pnt[15].setXY(172,452);pnt[16].setXY(368,295);pnt[17].setXY(201,369);
				pnt[18].setXY(543,300);
				//cc = new MyPoint();
		     	//pnt.add(cc)
	}
	public GraExp1(){	
		init();
		//add(panel);
		//GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		CInstead c1=new CInstead();
		setContentPane(c1);
		c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		c.add(lbl1);
		c.add(cb1);
		c.add(lbl2);
		c.add(cb2);
		c.add(btn1);
		c.add(tf1);
		c.add(btn2);
		c.add(btn3);
		c.add(btn4);
		c.add(lbl3);
		c.add(T);
		c.add(lbl4);
		c.add(tf2);
		addMouseMotionListener(new MouseMotionHandler());//坐标随鼠标移动而变化
		//addMouseListener(new MouseHandler());
		cb1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				int state=event.getStateChange();
				begin=cb1.getSelectedIndex();
				//System.out.println(begin);
			}
		});
		cb2.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				int state=event.getStateChange();
				//JOptionPane.showMessageDialog(null,cb2.getItemAt(cb2.getSelectedIndex()));
				//System.out.println(cb2.getSelectedIndex());
				end=cb2.getSelectedIndex();
			}
		});
		tf1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				String s=tf1.getText();
				dis=Integer.parseInt(s);
			}
		}); 
		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(dis>0)
					map[begin][end]=dis;
			}
		});
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 //repaint();
				double distance=Dijkstra(begin,end);
				String ans="";
				String DisAns=String.valueOf(distance);
				// myPanel PP=new myPanel();
				 //c.add("South",PP);
				 if(distance==0){
					 ans+="目标已达!";
					 JOptionPane.showMessageDialog(null,ans);
				 }
				 else{
					 int j=end;
					 while(city[j]!=begin){
						 myPanel PP=new myPanel();
						 //System.out.println("111111111222222222");
						 PP.setXY(pnt[j].x,pnt[j].y,pnt[city[j]].x,pnt[city[j]].y);
						 PP.paint(getGraphics());
						 c.add(PP);
						 j=city[j];
					 }
					 myPanel PP=new myPanel();
					 PP.setXY(pnt[j].x,pnt[j].y,pnt[city[j]].x,pnt[city[j]].y);
					 PP.paint(getGraphics());
					 c.add(PP);
				}
				tf2.setText( DisAns+"公里");
				
			}
		});
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		btn4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new	Dimension(650,650));
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/////////
	}

	private class MouseMotionHandler implements MouseMotionListener {       
		public void mouseMoved(MouseEvent e) {
			T.setText("X="+e.getX()+",Y="+e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}	
  
	public double min(double a,double b){
		return a<b?a:b;
	}
	private double Dijkstra(int a,int b){
		count=0;
		//System.out.println(a+" "+b);
		for(int i=0;i<19;i++){
			vis[i]=false;
			city[i]=-1;
			length[i]=map[a][i];
			if(map[a][i]!=1000000)
				city[i]=a;
		}
		vis[a]=true;
		for(int i=1;i<19;i++){
			double temp=1000000;
			int k=a;
			for(int j=0;j<19;j++){
				if(!vis[j]){
					if(temp>length[j]){
						temp=length[j];
						k=j;
					}
				}
			}
			if(temp==1000000)
				break;
			vis[k]=true;
			for(int j=0;j<19;j++){
				if(!vis[j]&&map[k][j]!=1000000){
					if(length[j]>length[k]+map[k][j]){
						length[j]=length[k]+map[k][j];
						city[j]=k;
					}
				}
			}
		}
		if(length[b]!=1000000)
			return length[b];
		else
			return 0;
	}
	class CInstead extends JPanel{
		ImageIcon icon;
		Image img;
		public CInstead(){
			icon=new ImageIcon(CInstead.class.getResource("1.png"));
			img=icon.getImage();
		}
		public void paint(Graphics g){
			super.paint(g);
			g.drawImage(img,0,150,null);
		}
	}
	class myPanel extends JPanel{
		int xx,yy,xxx,yyy;
		public myPanel(){
			
		}
		public void paint(Graphics g) {
			System.out.printf("check222");
			super.paint(g);
        	g.setColor(Color.red);
        	g.drawLine(xx,yy,xxx,yyy);
		}
		public void setXY(int x1,int y1,int x2,int y2){
			System.out.printf("check111");
			xx=x1;yy=y1;
			xxx=x2;yyy=y2;
		}
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GraExp1 ge=new GraExp1();
			}
		});
	}
	
}