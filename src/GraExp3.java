//package ddd;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class GraExp3 extends JFrame{
	CInstead c1=new CInstead();
	Container c;
	double[][] map=new double[20][20];int dis=0;String[] city=new String[20];int count;
	boolean[] vis=new boolean[20];double[] length=new double[20];
	String[] zt={"慈溪市","富阳市","奉化市","杭州市","海宁市","建德市","金华市","临安市","临海市","兰溪市","宁波市","绍兴市","嵊州市","台州市","永康市","义乌市","余姚市","诸暨市","舟山市"};
	String[] zx={"慈溪市","富阳市","奉化市","杭州市","海宁市","建德市","金华市","临安市","临海市","兰溪市","宁波市","绍兴市","嵊州市","台州市","永康市","义乌市","余姚市","诸暨市","舟山市"};
	int begin,end;   
	JLabel lbl1=new JLabel("起始站：");
	JLabel lbl2=new JLabel("终点站：");
	JButton btn1=new JButton("设置距离");
	JTextField tf1=new JTextField(25);
	JButton btn2=new JButton("运行");
	JButton btn3=new JButton("退出");
	JComboBox cb1=new JComboBox(zt),cb2=new JComboBox(zx);
	public GraExp3(){	
		//获取可用字体名称数组
		init();
		//GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		setContentPane(c1);
		c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.add(lbl1);
		c.add(cb1);
		c.add(lbl2);
		c.add(cb2);
		c.add(btn1);
		c.add(tf1);
		c.add(btn2);
		c.add(btn3);
		cb1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				int state=event.getStateChange();
				begin=cb1.getSelectedIndex();
				System.out.println(begin);
			}
		});
		cb2.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				int state=event.getStateChange();
				//JOptionPane.showMessageDialog(null,cb2.getItemAt(cb2.getSelectedIndex()));
				//System.out.println(cb2.getSelectedIndex());
				end=cb2.getSelectedIndex();
				System.out.println(end);
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
				Dijkstra(begin);
/*				String ans="";
				if(distance==0)
					ans+="目标不可达!";
				else{
					ans="最短路径为:";
					for(int i=0;i<count-1;i++){
						ans+=city[i];
						ans+="->";
					}
					ans+=city[count-1];
					ans+="********最短路总长为:";
					ans+=String.valueOf(distance);
				}
*/
				JOptionPane.showMessageDialog(null,getPath(end));
			}
		});
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 System.exit(0);
			}
		});
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new	Dimension(650,650));
		show();
	}
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
		map[12][13]=153.9;map[10][8]=115;map[12][15]=92.6;map[2][13]=156.2;
		map[13][8]=20;map[8][14]=128.2;map[14][6]=57.9;map[6][15]=55.8;
		map[15][17]=64.5;map[17][3]=90.2;map[3][4]=40.5;map[3][7]=47.1;
		map[3][1]=47;map[1][5]=100.6;map[5][9]=116.7;map[9][6]=27.2;
		
		map[11][0]=82.4;map[10][0]=58.7;map[4][11]=72.1;map[3][11]=61.5;
		map[16][11]=71.8;map[12][11]=74.3;map[17][11]=59.5;map[16][10]=53.1;
		map[18][10]=84.9;map[2][10]=39.9;map[12][10]=98.8;map[2][12]=77.8;
		map[13][12]=153.9;map[8][10]=115;map[15][12]=92.6;map[13][2]=156.2;
		map[8][13]=20;map[14][8]=128.2;map[6][14]=57.9;map[15][6]=55.8;
		map[17][15]=64.5;map[3][17]=90.2;map[4][3]=40.5;map[7][3]=47.1;
		map[1][3]=47;map[5][1]=100.6;map[9][5]=116.7;map[6][9]=27.2;
	}
	public double min(double a,double b){
		return a<b?a:b;
	}
	int[] prev = new int[20];
	private void Dijkstra(int a) {
		int i;
		for(i = 0;i < 19;i++) {
			vis[i] = false;
			city[i] = "";
			prev[i] = -1;
			length[i] = 1000000;
		}
		length[a] = 0;
		while(true) {
			int v = -1;
			for(i = 0;i < 19;++i) {
				if(!vis[i] && (v == -1 || length[i] < length[v]))
					v = i;
			}
			
			if(v == -1)
				break;
			vis[v] = true;
			
			for(i = 0;i < 19;++i) {
				if(length[i] > length[v] + map[v][i]) {
					length[i] = length[v] + map[v][i];
					prev[i] = v;
				}
			}
		}
		
	}
	private String getPath(int b) {
		double d = length[b];
		if(length[b] > 1000000)
			return "目标不可达！";
		int cnt = 0,i;
		String ans = "最短路径为:" + zt[begin];
		int[] sp= new int[20];
/*
		for(i = 0 ; i < 19 ; ++i)
			System.out.print(prev[i] + " ");
		System.out.println();
*/
		for(;b != -1;b = prev[b]) {
			sp[cnt++] = b;
		}
/*
		for(i = 0 ; i < 19 ; ++i)
			System.out.print(prev[i] + " ");
		System.out.println();

		for(i = 0 ; i < 19 ; ++i)
			System.out.print(sp[i] + " ");
		System.out.println();
*/
		for(i = 0;i < cnt / 2;++i) {
			int t = sp[i];
			sp[i] = sp[cnt-i-1];
			sp[cnt-i-1] = t;
		}
		
		for(i = 0 ; i < 19 ; ++i)
			System.out.print(sp[i] + " ");
		System.out.println();

		for(i = 1;i < cnt;++i) {
			ans += "->" ;
			ans += zt[sp[i]];
		}
		ans += "********最短路总长为:" + String.valueOf(d);
		System.out.println(ans);
		return ans;
	}
/* 	private double Dijkstra(int a,int b){
		count=0;
		System.out.println(a+" "+b);
		for(int i=0;i<19;i++)
			vis[i]=false;
		for(int i=0;i<19;i++)
			city[i]="";
		for(int i=0;i<19;i++)
			length[i]=map[a][i];
		vis[a]=true;
		sp[a]=zt[a];
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
//			city[count++]=zt[k];
			vis[k]=true;
			if(k==b)
				break;
			for(int j=0;j<19;j++){
				if(!vis[j]) {
					if(length[k]+map[k][j]<length[j]) {
						length[j]=length[k]+map[k][j];
						sp[j]=sp[k]+"->"+zt[j];
					}
				}
					length[j]=min(length[j],length[k]+map[k][j]);
			}
		}
		if(length[b]!=1000000)
			return length[b];
		else
			return 0;
		
	}
*/
	class CInstead extends JPanel{
		ImageIcon icon;
		   Image img;
		   public CInstead(){
			   icon=new ImageIcon(CInstead.class.getResource("1.png"));
			   img=icon.getImage();
		   }
		   public void paintComponent(Graphics g){
			   super.paintComponent(g);
			   g.drawImage(img,0,150,null);
		   }
	}
	public static void main(String[] args){
		GraExp3 ge=new GraExp3();
	}

}