
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;



public class Notebook {
	public static void main(String arge[]){
		createinit text =new createinit();
		text.eventsadd();
	}
}


class createinit implements ActionListener
{
	boolean canrelin=false,candrag=true,setbg=false; 
	int saveflage=0;
		JFrame textFrame =new JFrame ("�ı��༭��");
		JMenuBar edi  =new JMenuBar();
		JTextArea textArea;
		JMenu menufile;
		JMenuItem itemMenuopen;
		JMenuItem itemMenunew;
		JMenuItem itemMenusave;
		JMenuItem itemMenusaveother;
		JMenuItem itemMenuclose;
		JMenuItem itemMenuexit;
		JMenu menuaction;
		JMenuItem itemMenucopy;
		JMenuItem itemMenupaste;
		JMenuItem itemMenucut;
		JMenuItem itemMenusall;
		JMenuItem itemMenuudo;
		JMenuItem itemMenurdo;
		JMenuItem itemMenuaddtime;
		JMenu menuset;
		JMenuItem itemMenureline;
		JMenuItem itemMenufont;
		JMenuItem itemMenuset;
		
		JPopupMenu mousemenu;
		JMenuItem itemMenuc=new JMenuItem("���ƣ�C��");
		JMenuItem itemMenup=new JMenuItem("ճ����V��");
		JMenuItem itemMenucu=new JMenuItem("���У�X��");
		JMenuItem itemMenus=new JMenuItem("ȫѡ��A��");
		
		Insets m;
		int bt=15,tp=15,lf=15,ri=15;
		
		Color stc=Color.magenta;
		Color sc=Color.lightGray;
		Color bc=Color.darkGray;
		Color tc=Color.white;
		
		JFileChooser filechooser;
		JDialog filewarring = new JDialog();
		UndoManager undoManager = new UndoManager();

		 String fontS,dfff="������";
		 int fontSize,fontStyle,dffsz=14,dffst=0,x,y,w=600,h=400;
	createinit()
	{

		
		m=new Insets(lf,tp,ri,bt);

		newlode();
		
		textFrame.setSize(w, h);
		textFrame.setLocation(x,y);
		textFrame.setResizable(true);
		textFrame.setVisible(true);
		textFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try //����UIΪ��ǰϵͳ�ķ��JAVA�Ŀ�ƽ̨�����ʵ���ǿ����߰�~
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		}catch (Exception e) {}
		

	   menufile=new JMenu("�ļ���F��");
	   menufile.setMnemonic(KeyEvent.VK_F);
	   itemMenuopen=new JMenuItem("�򿪣�O��");
	   itemMenuopen.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O,InputEvent.CTRL_MASK));
	   itemMenunew=new JMenuItem("�½���N��");
	   itemMenunew.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N,InputEvent.CTRL_MASK));
	   itemMenusave=new JMenuItem("���棨S��");
	   itemMenusave.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S,InputEvent.CTRL_MASK));
	   itemMenusaveother=new JMenuItem("���Ϊ��A��") ;
	   itemMenusaveother.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_A,InputEvent.ALT_MASK));
	   itemMenuclose=new JMenuItem("�رգ�C��");
	   itemMenuclose.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C,InputEvent.ALT_MASK));
	   itemMenuexit=new JMenuItem("�˳���X��");
	   itemMenuexit.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_X,InputEvent.ALT_MASK));
		
		menufile.add(itemMenuopen);
		menufile.add(itemMenunew);
		menufile.addSeparator();
		menufile.add(itemMenusave);
		menufile.add(itemMenusaveother);
		menufile.add(itemMenuclose);
		menufile.addSeparator();
		menufile.add(itemMenuexit);
		
		edi.add(menufile);
		
		
	    menuaction=new JMenu("�༭��E��");
	    menuaction.setMnemonic(KeyEvent.VK_E);
		itemMenucopy=new JMenuItem("���ƣ�C��");
		itemMenucopy.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C,InputEvent.CTRL_MASK));
		itemMenupaste=new JMenuItem("ճ����P��");
		itemMenupaste.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_V,InputEvent.CTRL_MASK));
		itemMenucut=new JMenuItem("���У�T��");
		itemMenucut.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_X,InputEvent.CTRL_MASK));
		itemMenuudo=new JMenuItem("������Z��");
		itemMenuudo.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_Z,InputEvent.CTRL_MASK));
		itemMenurdo=new JMenuItem("������Y��");
		itemMenurdo.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_Y,InputEvent.CTRL_MASK));
		itemMenusall=new JMenuItem("ȫѡ��A��");
		itemMenusall.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_A,InputEvent.CTRL_MASK));
		itemMenuaddtime=new JMenuItem("���ڴ���D��");
		itemMenuaddtime.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_D,InputEvent.CTRL_MASK));
		
		menuaction.add(itemMenuudo);
		menuaction.add(itemMenurdo);
		menuaction.addSeparator();
		menuaction.add(itemMenusall);
		menuaction.add(itemMenucopy);
		menuaction.add(itemMenupaste);
		menuaction.add(itemMenucut);
		menuaction.addSeparator();
		menuaction.add(itemMenuaddtime);
		
		mousemenu=new JPopupMenu();
		
		mousemenu.add(itemMenuc);
		mousemenu.add(itemMenup);
		mousemenu.add(itemMenucu);
		mousemenu.add(itemMenus);
		
	
		edi.add(menuaction);
		
		menuset=new JMenu("��ʽ��O��");
		menuset.setMnemonic(KeyEvent.VK_O);
		itemMenufont=new JMenuItem("���壨F��");
		itemMenufont.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_F,InputEvent.CTRL_MASK));
		itemMenureline=new JMenuItem("�Զ����У�W��");
		itemMenureline.setMnemonic(KeyEvent.VK_W);
		itemMenuset=new JMenuItem("��ѡ�S��");
		itemMenuset.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_M,InputEvent.CTRL_MASK));
		menuset.add(itemMenureline);
		menuset.add(itemMenufont);
		menuset.addSeparator();
		menuset.add(itemMenuset);
		
		edi.add(menuset);
		if(setbg)bgc();
		else textArea=new JTextArea();
		Font ft=new Font(dfff,dffst,dffsz);
		textArea.setFont(ft);
		textArea.setLineWrap(canrelin);
		JScrollPane scop = new JScrollPane(textArea);
		
	
		
		
		textArea.setMargin(m);

		textArea.setDragEnabled(candrag);
		
		textArea.setBackground(bc);
		textArea.setForeground(tc);
		textArea.setSelectedTextColor(stc);
		textArea.setSelectionColor(sc);
		
		textFrame.add(scop);
		textFrame.setJMenuBar(edi);
		textFrame.validate();
		
		
		filechooser= new JFileChooser();

		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("javaԴ�ļ�      *.java","java"));
		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("C,C++Դ�ļ�   *.c,*.cpp","c","cpp"));
		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("��ҳ�ļ�   *.html,*.hml","html","hml"));
		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("�ű��ļ�    *.asp,*.jsp","asp","jsp"));
		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("XML�ĵ�     *.dtd,*.xml","dtd","xml"));
		filechooser.addChoosableFileFilter(new FileNameExtensionFilter("�ı��ĵ�         *.txt","txt"));
		
}
	
public void newsave(){
	try{
	FileOutputStream fos=new FileOutputStream("info.sdt");
	DataOutputStream stout=new DataOutputStream(fos);
	
	stout.writeInt(textFrame.getX());

	stout.writeInt(textFrame.getY());

	stout.writeInt(textFrame.getHeight());

	stout.writeInt(textFrame.getWidth());

	stout.writeInt(textFrame.getFont().getStyle());

	stout.writeInt(textFrame.getFont().getSize());
m=textArea.getInsets();
	stout.writeInt(m.bottom);

	stout.writeInt(m.left);

	stout.writeInt(m.right);

	stout.writeInt(m.top);

	stout.writeBoolean(canrelin);
	stout.writeBoolean(setbg);
bc=textArea.getBackground();
	stout.writeInt(bc.getBlue());
	stout.writeInt(bc.getGreen());
	stout.writeInt(bc.getRed());
tc=textArea.getForeground();
	stout.writeInt(tc.getBlue());
	stout.writeInt(tc.getGreen());
	stout.writeInt(tc.getRed());
stc=textArea.getSelectedTextColor();
	stout.writeInt(stc.getBlue());
	stout.writeInt(stc.getGreen());
	stout.writeInt(stc.getRed());
sc=textArea.getSelectionColor();
	stout.writeInt(sc.getBlue());
	stout.writeInt(sc.getGreen());
	stout.writeInt(sc.getRed());
	
	stout.writeUTF(textFrame.getFont().getFamily());


	stout.close();
	fos.close();
	}
	catch(IOException e)
	{JOptionPane.showMessageDialog(textFrame,"����"+e, "��Ǹ������ϵͳ�����ļ�����������������ԡ�", JOptionPane.WARNING_MESSAGE);}
	
}
public void newlode(){
	try{
	FileInputStream fis=new FileInputStream("info.sdt");
	if(fis==null)return;
	DataInputStream rtin=new DataInputStream(fis);
	x=rtin.readInt();

	y=rtin.readInt();

	h=rtin.readInt();
	
	w=rtin.readInt();

	dffst=rtin.readInt();	
	
	dffsz=rtin.readInt();
	
	bt=rtin.readInt();
	m.bottom=bt;
	lf=rtin.readInt();
	m.left=lf;
	ri=rtin.readInt();
	m.right=ri;
	tp=rtin.readInt();
	m.top=tp;
	canrelin=rtin.readBoolean();
	setbg=rtin.readBoolean();
	
	bc=new Color(rtin.readInt(),rtin.readInt(),rtin.readInt());

	tc=new Color(rtin.readInt(),rtin.readInt(),rtin.readInt());

	stc=new Color(rtin.readInt(),rtin.readInt(),rtin.readInt());
	
	sc=new Color(rtin.readInt(),rtin.readInt(),rtin.readInt());
	
	dfff=rtin.readUTF();
		
	rtin.close();
	fis.close();
	}
	catch(IOException e)
	{JOptionPane.showMessageDialog(textFrame,"����"+e, "��Ǹ������ϵͳ�����ļ�����������������ԡ�", JOptionPane.WARNING_MESSAGE);}
	
	
}
public void fileread( File openfile)
{
	String s;
	try{
		FileReader rtfile= new FileReader(openfile);
		BufferedReader rtin= new BufferedReader(rtfile);
		textArea.setText("");
		while((s=rtin.readLine())!=null)
		{
			if(!s.endsWith("\n"))s=s+"\n";
			textArea.append(s);
		}
		rtin.close();
		rtfile.close();
		textFrame.setTitle("�ı��༭��     "+openfile.getAbsolutePath());//+openfile.getName()
		}
		catch(IOException e)
		{
		 JOptionPane.showMessageDialog(textFrame,"����"+e, "��Ǹ����ȡ�ļ�����������������ԡ�", JOptionPane.WARNING_MESSAGE);
		}
}

public void filesaveframe( int idtype)
{
	switch(idtype)
	{
	case 0:if(itsaved())
			{
				if(JFileChooser.APPROVE_OPTION==filechooser.showOpenDialog(textFrame))
				{
					fileread(filechooser.getSelectedFile());
				}
			}
			else
			{
				switch(JOptionPane.showConfirmDialog(textFrame,"��⵽��ǰ���ļ��ĸ���δ���棬�Ƿ񱣴���ģ�", "���棡", JOptionPane.YES_NO_CANCEL_OPTION))
				{
				case JOptionPane.NO_OPTION:saveflage=0;filesaveframe(0);break;
				case JOptionPane.OK_OPTION:	filesave(0);filesaveframe(0);break;
				case JOptionPane.CANCEL_OPTION:break;
				default:;
				}
				
			}
			break;
	case 1: if(itsaved()){textArea.setText("");}
			else{filesave(0);textArea.setText("");}
			break;
	case 2:filesave(1);
			break;
	case 3: if(itsaved()){
		newsave();
		System.exit(0);}
			else 
			{
				switch(JOptionPane.showConfirmDialog(textFrame,"��⵽��ǰ���ļ��ĸ���δ���棬�Ƿ񱣴���ģ�", "���棡", JOptionPane.YES_NO_CANCEL_OPTION))
				{
				case JOptionPane.NO_OPTION:System.exit(0);
				case JOptionPane.OK_OPTION:	filesave(0);System.exit(0);break;
				case JOptionPane.CANCEL_OPTION:break;
				default:;
				}
				
			}
			break;
	case 4:if(itsaved()){textArea.setText("");textFrame.setTitle("�ı��༭��");}
			else 
			{
				switch(JOptionPane.showConfirmDialog(textFrame,"��⵽��ǰ���ļ��ĸ���δ���棬�Ƿ񱣴���ģ�", "���棡", JOptionPane.YES_NO_CANCEL_OPTION))
				{
				case JOptionPane.NO_OPTION:textArea.setText("");saveflage=0;break;
				case JOptionPane.OK_OPTION:	filesave(0);	saveflage=0;break;
				case JOptionPane.CANCEL_OPTION:break;
				default:;
				}
				
			}break;
	case 5:if(JFileChooser.APPROVE_OPTION==filechooser.showSaveDialog(textFrame))
			{filesave(0);}
			break;
	default :;
	}
}
public void filesave(int ty)
{
	File temp = filechooser.getSelectedFile();
	try{
		if(!temp.exists()||ty==1)
		{
				FileWriter stfile= new FileWriter(temp,false);
				BufferedWriter stout =new BufferedWriter(stfile);
				stout.write(textArea.getText());
				stout.flush();
				stout.close();
				stfile.close();
				saveflage=0;
			}
			else
			{
				
			switch(JOptionPane.showConfirmDialog(textFrame,"��ǰĿ¼�´���ͬ���ļ����Ƿ񸲸ǣ�", "���棡", JOptionPane.YES_NO_CANCEL_OPTION))
			{
			case JOptionPane.NO_OPTION:
				temp=null;
				temp=new File(filechooser.getSelectedFile().getAbsolutePath()+"(��)");
				temp.createNewFile();
			case JOptionPane.OK_OPTION:
				FileWriter stfile= new FileWriter(temp,false);
				BufferedWriter stout =new BufferedWriter(stfile);
				stout.write(textArea.getText());
				stout.flush();
				stout.close();
				stfile.close();
				saveflage=0;break;
			case JOptionPane.CANCEL_OPTION:break;
			default:;
			}
			}
		
	}
	catch(IOException e)
		{
		JOptionPane.showMessageDialog(textFrame,"����"+e, "��Ǹ�������ļ�����������������ԡ�", JOptionPane.WARNING_MESSAGE);
		}
}


public boolean itsaved()
{
	if(saveflage==0)
	return true;
	else return false;
}
public void eventsadd()
	{
		itemMenucopy.addActionListener(this);
		itemMenupaste.addActionListener(this);
		itemMenucut.addActionListener(this);
		itemMenureline.addActionListener(this);
		itemMenurdo.addActionListener(this);
		itemMenuudo.addActionListener(this);
		itemMenuset.addActionListener(this);
		itemMenufont.addActionListener(this);
		itemMenuaddtime.addActionListener(this);
		itemMenuexit.addActionListener(this);
		itemMenuopen.addActionListener(this);
		itemMenunew.addActionListener(this);
		itemMenusave.addActionListener(this);
		itemMenuclose.addActionListener(this);
		itemMenusaveother.addActionListener(this);
		
		itemMenuc.addActionListener(this);
		itemMenup.addActionListener(this);
		itemMenucu.addActionListener(this);
		itemMenus.addActionListener(this);
		
		textArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getModifiers()==InputEvent.BUTTON3_MASK)
					mousemenu.show(textArea,e.getX(),e.getY());
				
			}
			
		});
		

		textArea.getDocument().addUndoableEditListener(undoManager);
	}
public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==itemMenucopy){textArea.copy();saveflage=1;}
		if(e.getSource()==itemMenupaste){textArea.paste();saveflage=1;}
		if(e.getSource()==itemMenucut){textArea.cut();saveflage=1;}
		if(e.getSource()==itemMenusall){textArea.select(0, textArea.getText().length());saveflage=1;}
		if(e.getSource()==itemMenureline){canrelin=!canrelin;textArea.setLineWrap(canrelin);}
		if(e.getSource()==itemMenuudo){undoManager.undo();saveflage=1;}
		if(e.getSource()==itemMenurdo){undoManager.redo();saveflage=1;}
		if(e.getSource()==itemMenuaddtime){addDate();saveflage=1;}
		
		if(e.getSource()==itemMenuc){textArea.copy();saveflage=1;}
		if(e.getSource()==itemMenup){textArea.paste();saveflage=1;}
		if(e.getSource()==itemMenucu){textArea.cut();saveflage=1;}
		if(e.getSource()==itemMenus){textArea.requestFocus();textArea.selectAll();saveflage=1;}
			//�˵����ô���δ֪���⣬�����޷�ȫѡ����ݼ�������Ҽ��˵�����
		if(e.getSource()==itemMenufont){new FontFrame().setLoca(textFrame.getX()+textArea.getWidth()/2,textFrame.getY()+textArea.getHeight()/2);}
		if(e.getSource()==itemMenuset){textFrame.setEnabled(false);new  setFrame().setLoca(textFrame.getX()+textArea.getWidth()/2,textFrame.getY()+textArea.getHeight()/2);}
		if(e.getSource()==itemMenusaveother){filesaveframe(5);};
		if(e.getSource()==itemMenuopen){filesaveframe(0);}
		if(e.getSource()==itemMenunew){filesaveframe(1);}
		if(e.getSource()==itemMenusave){filesaveframe(2);}
		if(e.getSource()==itemMenuexit){filesaveframe(3);}
		if(e.getSource()==itemMenuclose){filesaveframe(4);}
		
	}
public void bgc()
	{
	final ImageIcon imageIcon = new ImageIcon("bg.jpg");
		textArea=new JTextArea(){
		{setOpaque(false);}
		public void paint(Graphics g) 
			{ 
			g.drawImage(imageIcon.getImage(), 0,0,textArea.getWidth(),textArea.getHeight(),0, 0,imageIcon.getIconWidth(),imageIcon.getIconHeight(),null,this);
			super.paint(g);}
			};
		}
void addDate()
{
	  Date now = new Date();
	  DateFormat df= DateFormat.getDateInstance();
	  String today =df.format(now);
	  textArea.setText(textArea.getText()+today);
	  textArea.setCaretPosition(textArea.getText().length());
}

class FontFrame implements ActionListener,ItemListener,WindowListener
{
	  JFrame fontchoose;
	  GraphicsEnvironment graphenv;
	  String []fontname;  
	  JLabel fontfam,fonttsy,fonts;
	  JComboBox fnamec,fstylec,fsizec;
	  JButton surebutton,canselbutton;
	  String style[]={"����","����","б��","��б��"};
	  String[] fontsize=new String[72];
	  
	  JLabel fview,fviewstyle;
	public  FontFrame()
	  {
		for(int si=0;si<72;si++)
		{fontsize[si]=si+8+"";}
	   fontchoose=new JFrame("��������");
	   graphenv=GraphicsEnvironment.getLocalGraphicsEnvironment();
	   fontname=graphenv.getAvailableFontFamilyNames();//�����
	   fontfam=new JLabel("����:");
	   fonttsy=new JLabel("��ʽ:");
	   fonts=new JLabel("�ֺ�");
	   surebutton=new JButton("ȷ��");
	   canselbutton=new JButton("ȡ��");
	   
	   
	   fnamec=new JComboBox(fontname);
	   fnamec.addItemListener(this);
	   fstylec=new JComboBox(style);
	   fstylec.addItemListener(this);
	   fsizec=new JComboBox(fontsize);
	   fsizec.addItemListener(this);
	   fview=new JLabel("Ԥ��");
	   fviewstyle=new JLabel("�ҵ�Java�ı��༭��");

		Box setline=Box.createVerticalBox();
		setline.add(fontfam);
		setline.add(Box.createHorizontalStrut(5));
		setline.add(fnamec);
		setline.add(fonttsy);
		setline.add(Box.createHorizontalStrut(5));
		setline.add(fstylec);
		setline.add(fonts);
		setline.add(Box.createHorizontalStrut(5));
		setline.add(fsizec);
		setline.add(fview);
		setline.add(Box.createHorizontalStrut(5));
		setline.add(fviewstyle);

		fontchoose.add(setline,BorderLayout.CENTER); 
		JPanel shoe = new JPanel();
		shoe.add(surebutton);
		shoe.add(canselbutton);
		fontchoose.add(shoe,BorderLayout.SOUTH);
	   
	   surebutton.addActionListener(this);
	   canselbutton.addActionListener(this);
	   
	   fnamec.setSelectedItem(textArea.getFont().getFamily());
	   fsizec.setSelectedIndex(textArea.getFont().getSize()-8);
	   fstylec.setSelectedIndex(textArea.getFont().getStyle());
	   
	   fontchoose.setSize(300,300);
	   fontchoose.setResizable(false);
	   fontchoose.setVisible(true);
	   }
	public void setLoca(int i, int j) {
		fontchoose.setLocation(i-140,j-85);
		
	}
	public void itemStateChanged(ItemEvent e) {
	    fontSize=Integer.parseInt(fontsize[fsizec.getSelectedIndex()]);  
	    fontS=fontname[fnamec.getSelectedIndex()];
	   if(fstylec.getSelectedIndex()==0)
	    fontStyle=Font.PLAIN;
	   else if(fstylec.getSelectedIndex()==1)
	    fontStyle=Font.BOLD;
	   else if(fstylec.getSelectedIndex()==2)
	    fontStyle=Font.ITALIC;
	   else if(fstylec.getSelectedIndex()==3)
	    fontStyle=Font.BOLD+Font.ITALIC;
	   dfff=fontS;dffst=fontStyle;dffsz=fontSize;
	   Font f=new Font(fontS,fontStyle,fontSize);
	   fviewstyle.setFont(f);
	   
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==canselbutton){ 
			
			textFrame.setEnabled(true);
			fontchoose.dispose();
		   }
		   else if(e.getSource()==surebutton)
		   {
		    Font fontset=new Font(fontS,fontStyle,fontSize);
		   
		    textArea.setFont(fontset);
		    textFrame.setEnabled(true);
		    fontchoose.dispose();     
		   }
	}
	
	
	
	public void windowOpened(WindowEvent e) {
		textFrame.setEnabled(false);
	}
	public void windowClosing(WindowEvent e) {
		textFrame.setEnabled(true);//Ī������Ļ᲻������....��ʱ��Frame���᲻����...
	}
	public void windowClosed(WindowEvent e) {
		textFrame.setEnabled(true);
	}
	public void windowIconified(WindowEvent e) {
		
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
	public void windowActivated(WindowEvent e) {
		textFrame.setEnabled(false);
	}
	public void windowDeactivated(WindowEvent e) {
		
	}
}



class setFrame implements ActionListener,WindowListener,ItemListener
{
	JFrame setframe;
	JPanel colorset,infoset,copyright,cotr;
	JTabbedPane jtablepane;
	Color colorselected;
	
	JLabel bordersize=new JLabel("�������ֱ߾ࣺ                                ");
	JLabel borderl=new JLabel("��߾ࣺ");
	JLabel bordert=new JLabel("�ϱ߾ࣺ");
	JLabel borderr=new JLabel("�ұ߾ࣺ");
	JLabel borderb=new JLabel("�±߾ࣺ");
	JTextField bol=new JTextField(24);
	JTextField bot=new JTextField(24);
	JTextField bor=new JTextField(24);
	JTextField bob=new JTextField(24);
	
	JButton setbgcolor=new JButton("  ������ɫ  ");
	JButton settxcolor=new JButton("  ������ɫ  ");
	JButton setsecolor=new JButton("ѡ��������ɫ");
	JButton setsscolor=new JButton(" ѡ�����ɫ ");
	JTextField bgco=new JTextField(24);
	JTextField txco=new JTextField(24);
	JTextField seco=new JTextField(24);
	JTextField ssco=new JTextField(24);
	
	JLabel bgst=new JLabel("��ѡ�������ʽ");
	String [] chl={"�����","����","�Ҷ���"};
	JComboBox chlse=new JComboBox(chl);
	
	JLabel seepath =new JLabel("ѡ�񱳾��ļ�");
	JButton lobgim=new JButton("��");
	
	JPanel imgview=new JPanel();
	JLabel seeimg =new JLabel("Ԥ��");
	JTextField fpath=new JTextField(24);
	JTextArea vi;
	JButton busu,buca,setdf;
	public setFrame()
	{
		setframe=new JFrame("����");
		setframe.setSize(300, 400);
		colorset=new JPanel();
		infoset=new JPanel();
		copyright=new JPanel();
		jtablepane=new JTabbedPane(JTabbedPane.TOP);

		//����
		
		chlse.addItemListener(this);
		lobgim.addActionListener(this);
		
		//imgview.setLayout(new BorderLayout(1,1));
		imgview.add(seepath);
		imgview.add(fpath);
		imgview.add(lobgim);
		imgview.add(bgst);
		imgview.add(chlse);
		imgview.add(seeimg);
		 
		vi=new JTextArea();
		vi.setText("ʱ�䲻����������..........");
		//vi.setSize(100,120);
		//vi.setBounds(0,220,0,0);
		imgview.add(vi);
		
		//ϵͳ����
		
		//�߾�

		
		busu=new JButton("ȷ������ ");
		buca=new JButton("Ĭ������ ");
		setdf=new JButton("����ΪĬ��");
		
		cotr=new JPanel();
		cotr.add(busu);
		cotr.add(setdf);
		cotr.add(buca);
		
		Box frambox=Box.createVerticalBox();
		frambox.add(bordert);
		frambox.add(Box.createVerticalStrut(14));
		frambox.add(borderb);
		frambox.add(Box.createVerticalStrut(14));
		frambox.add(borderl);
		frambox.add(Box.createVerticalStrut(14));
		frambox.add(borderr);
		Box setboin=Box.createVerticalBox();
		setboin.add(bot);
		setboin.add(Box.createVerticalStrut(2));
		setboin.add(bob);
		setboin.add(Box.createVerticalStrut(2));
		setboin.add(bol);
		setboin.add(Box.createVerticalStrut(2));
		setboin.add(bor);

		
		Box complall=Box.createHorizontalBox();
		complall.add(frambox);
		complall.add(Box.createHorizontalStrut(10));
		complall.add(setboin);
		//��ɫ

		
		setbgcolor.addActionListener(this);
		settxcolor.addActionListener(this);
		setsecolor.addActionListener(this);
		setsscolor.addActionListener(this);
		busu.addActionListener(this);
		buca.addActionListener(this);
		setdf.addActionListener(this);
		
		Box colorsb=Box.createVerticalBox();
		colorsb.add(setbgcolor);
		colorsb.add(Box.createVerticalStrut(14));
		colorsb.add(settxcolor);
		colorsb.add(Box.createVerticalStrut(14));
		colorsb.add(setsecolor);
		colorsb.add(Box.createVerticalStrut(14));
		colorsb.add(setsscolor);
		
		Box showco=Box.createVerticalBox();
		showco.add(bgco);
		showco.add(Box.createVerticalStrut(2));
		showco.add(txco);
		showco.add(Box.createVerticalStrut(2));
		showco.add(seco);
		showco.add(Box.createVerticalStrut(2));
		showco.add(ssco);

		Box colorall=Box.createHorizontalBox();
		colorall.add(colorsb);
		colorall.add(Box.createHorizontalStrut(10));
		colorall.add(showco);

		FlowLayout floser=new FlowLayout();
		floser.setHgap(5);
		floser.setAlignment(FlowLayout.RIGHT);
		infoset.setLayout(floser);
		infoset.add(bordersize);
		infoset.add(complall);
		infoset.add(new JLabel("��ɫ���ã�                                    "));
		infoset.add(colorall);
		infoset.add(cotr,BorderLayout.SOUTH);
		
		jtablepane.add("ϵͳ����",infoset);
		jtablepane.add("�ı����򱳾�����",imgview);
		
		Icon im=new ImageIcon("about.jpg");
		JLabel info=new JLabel(im);

		copyright.add(info);

		jtablepane.add("��Ȩ����",copyright);
		jtablepane.validate();
		
		setframe.add(jtablepane,BorderLayout.CENTER);
		
		
		bgco.setBackground(textArea.getBackground());
		txco.setBackground(textArea.getForeground());
		seco.setBackground(textArea.getSelectedTextColor());
		ssco.setBackground(textArea.getSelectionColor());
		
		String t,b,l,r;
		Insets mm=textArea.getMargin();
		t=String.valueOf(mm.top);
		b=String.valueOf(mm.bottom);
		l=String.valueOf(mm.left);
		r=String.valueOf(mm.right);
		bol.setText(l);
		bor.setText(r);
		bot.setText(t);
		bob.setText(b);
		
		setframe.addWindowListener(this);
		
		setframe.setVisible(true);
		setframe.setResizable(false);
		setframe.validate();
		}
	public void setLoca(int i, int j)
	{
		setframe.setLocation(i-150, j-200);
	}

		

	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==lobgim){
			if(!setbg)JOptionPane.showConfirmDialog(textFrame,"��˵���ˣ��㻹��......����������,���³���ô��Ӧ������ŵ�ö�����...\n������������һ�£������о�ϲӴ~~   o(��_��)o ���� \n��������", "���棡", JOptionPane.YES_OPTION);
		else  JOptionPane.showMessageDialog(textFrame,"��ϲӴ~�԰ɣ��Ǻ�~", "����", JOptionPane.QUESTION_MESSAGE);
		setbg=!setbg;
		}
		
		if(e.getSource()==setbgcolor)
		{
			 colorselected=JColorChooser.showDialog(null,"ѡ��������ɫ",textArea.getBackground());
			if(colorselected!=null)
				{
				bgco.setBackground(colorselected);textArea.setBackground(colorselected);bc=colorselected;
				}
		}
		else	if(e.getSource()==settxcolor)
		{
			colorselected=JColorChooser.showDialog(null,"ѡ��������ɫ",textArea.getForeground());
			if(colorselected!=null)
				{
				txco.setBackground(colorselected);textArea.setForeground(colorselected);tc=colorselected;
				}
		}	
		else if(e.getSource()==setsecolor)
		{
			colorselected=JColorChooser.showDialog(null,"ѡ��������ɫ",textArea.getSelectedTextColor());
			if(colorselected!=null)
				{
				seco.setBackground(colorselected);textArea.setSelectedTextColor(colorselected);stc=colorselected;
				}
		}
		else	if(e.getSource()==setsscolor)
		{
			colorselected=JColorChooser.showDialog(null,"ѡ��������ɫ",textArea.getSelectionColor());
			if(colorselected!=null)
				{
				ssco.setBackground(colorselected);textArea.setSelectionColor(colorselected);sc=colorselected;
				}
		}
		else if(e.getSource()==busu)
		{
			String srtl=bol.getText();
			String srtr=bor.getText();
			String srtt=bot.getText();
			String srtb=bob.getText();
			String regex="[0-9]+";
			int st=0,sb=0,sr=0,sl=0;
			if(srtl.matches(regex))sl=Integer.parseInt(srtl);
			if(srtr.matches(regex))sr=Integer.parseInt(srtr);
			if(srtt.matches(regex))st=Integer.parseInt(srtt);
			if(srtb.matches(regex))sb=Integer.parseInt(srtb);
			Insets nn=new Insets(st,sl,sb,sr);
			textArea.setMargin(nn);
			textFrame.setEnabled(true);
			setframe.dispose();
		}
		else if(e.getSource()==buca)
		{
			String t,b,l,r;
			t=String.valueOf(m.top);
			b=String.valueOf(m.bottom);
			l=String.valueOf(m.left);
			r=String.valueOf(m.right);
			bol.setText(l);
			bor.setText(r);
			bot.setText(t);
			bob.setText(b);
			textArea.setMargin(m);
		}
		else if(e.getSource()==setdf)
		{
			newsave();
		}
	}
	public void windowOpened(WindowEvent e) {
		
	}
	public void windowClosing(WindowEvent e) {
		textFrame.setEnabled(true);
	}
	public void windowClosed(WindowEvent e) {
		
	}
	public void windowIconified(WindowEvent e) {
		
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
	public void windowActivated(WindowEvent e) {
		
	}
	public void windowDeactivated(WindowEvent e) {
		
	}
	public void itemStateChanged(ItemEvent arg0) {
		
	}
	}
	
	
	}

