import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.*;
public class MM extends JApplet implements Runnable{
int a,b,c,d,e,f,h,i;
//int const=10000;
public void init(){
resize(500,500);
Container cp=getContentPane();

}
public void run(){

}
public void paint(Graphics g){
	super.paint(g); 
e=(int)(Math.random()*256);
f=(int)(Math.random()*256);
h=(int)(Math.random()*256); 
g.setColor(new Color(e,f,h));
g.drawLine(1,2,3,4); 
//repaint();
}
}