import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Calculator
{
	JFrame frame=new JFrame("Calculator");
	JTextField textbox=new JTextField("0");
	JButton[] bt=new JButton[20];
	JPanel panel=new JPanel();
	public Calculator()
	{
		frame.setSize(400,450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLayout(null);
		addTextBox();
		frame.setVisible(true);
	}
	private void addTextBox()
	{
		textbox.setBounds(20,20,360,40);
		frame.add(textbox);
		textbox.setHorizontalAlignment(JTextField.RIGHT);
		textbox.setFont(new Font("arial",0,25));
		textbox.setEditable(false);
		//textbox.setBackground(Color.white);
		textbox.setBackground(new Color(255,255,255));
		textbox.setBorder(BorderFactory.createLineBorder(Color.black,1));
		addButtons();
	}
	private void addButtons()
	{
		panel.setBounds(20,90,360,300);
		frame.add(panel);
		panel.setLayout(new GridLayout(5,4,5,5));
		Font font=new Font("arial",Font.PLAIN,20);
		String[] str= {"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		CalListener listener=new CalListener();
		for(int i=0;i<20;i++)
		{
			bt[i]=new JButton(str[i]);
			bt[i].addActionListener(listener);
			bt[i].setFont(font);
			if(i==3 || i==7 || i==11 || i==15 || i==19)
				bt[i].setForeground(Color.red);
			else
				bt[i].setForeground(Color.blue);
			panel.add(bt[i]);
		}
		bt[17].setFont(new Font("arial",Font.PLAIN,30));
	}
	class CalListener implements ActionListener
	{
		int ao=0,num1;
		String op=null;
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			//when arithmetic operation button will be clicked
			if(bb==bt[7]||bb==bt[11]||bb==bt[15]||bb==bt[19])
			{
				if(op!=null)
					cal();
				ao=1;
				num1=Integer.parseInt(textbox.getText());
				op=bb.getText();
			}
			//when digits button will be clicked
			if(bb==bt[4]||bb==bt[5]||bb==bt[6]||bb==bt[8]||bb==bt[9]||bb==bt[10]||bb==bt[12]||bb==bt[13]||bb==bt[14]||bb==bt[16])
			{
				String tvalue=textbox.getText();
				String bvalue=bb.getText();
				if(tvalue.equals("0") || ao==1)
				{
					textbox.setText(bvalue);
					ao=0;
				}
				else
					textbox.setText(tvalue+bvalue);
			}
			//When button CE clicked
			if(bb==bt[1])
			{
				textbox.setText("0");
			}
			//When button C clicked
			if(bb==bt[2])
			{
				textbox.setText("0");
				op=null;
				ao=0;
			}
			//When button Back clicked
			if(bb==bt[0])
			{
				String tv=textbox.getText();
				int num=Integer.parseInt(tv);
				if(num==0)
					return;
				num=num/10;
				textbox.setText(String.valueOf(num));
			}
			//When button = clicked
			if(bb==bt[18])
			{
				cal();
			}
		}//end of actionPerformed
		private void cal()
		{
			int num2=Integer.parseInt(textbox.getText());
			if(op.equals("+"))
			{
				int res=num1+num2;
				textbox.setText(String.valueOf(res));
			}
			else if(op.equals("-"))
			{
				int res=num1-num2;
				textbox.setText(String.valueOf(res));
			}
			else if(op.equals("*"))
			{
				int res=num1*num2;
				textbox.setText(String.valueOf(res));
			}
			else if(op.equals("/"))
			{
				int res=num1/num2;
				textbox.setText(String.valueOf(res));
			}
		}
	}
	public static void main(String[] args) 
	{
		new Calculator();
	}
}
