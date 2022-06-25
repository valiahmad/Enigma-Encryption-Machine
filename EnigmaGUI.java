import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


class cons{
	final int fast = 0;
    final int medium = 1;
    final int slow = 2;
    final int Royal = 18;
    final int Flags = 6;
    final int Wave = 23;
    final int Kings = 11;
    final int Above = 1;
    String royal = "Royal.dat", flags = "Flags.dat", wave = "Wave.dat",
	kings = "Kings.dat", above = "Above.dat";
}
/////////////////////////////////////////////////////////////////////SOCKET
class Socket {
	int  wire;
	int socket_name;
	
	void nomination(int _socket_name) {
		socket_name = _socket_name;
	}
	boolean name_socket(int code) {
		if (code == socket_name)return false;
		else return true;
	}
	int set_wire(int couple, int option) {
		if (option == 1)
		wire = couple;
		else if (option == -1)
		return wire;
        return -1;
	}
}
//////////////////////////////////////////////////////////////////////PLUGBOARD
class Plugboard {
	Socket[] socket = new Socket[20];
    int i;
    
	Plugboard() {
		for(int j = 0; j < 20; j++)
		socket[j] = new Socket();
		for (i = 0; i < 20; i++)
		socket[i].nomination(i + 69);
	}
	int plug_coding(int word) {
		if (find_plugboard(word) == -1)return word;
		else return find_plugboard(word);
	}
	boolean chek_code(int code) {
		if (code > 68 && code < 89)return true;
		else return false;
	}
	void set_plugboard(int couple1, int couple2) {
		if (!chek_code(couple1))return;
		if (!chek_code(couple2))return;
		socket[couple1 - 69].set_wire(couple2, 1);
		socket[couple2 - 69].set_wire(couple1, 1);
	}
	int find_plugboard(int code) {
		if (!chek_code(code))return -1;
		for (i = 0; i < 20; i++) {
			if (!socket[i].name_socket(code)) {
				code = socket[i].set_wire(0, -1);
				return code;
			}
		}
        return -1;
	}
}
////////////////////////////////////////////////////////////////////ROTOR
class Rotor {
	int i, Element_int[] = new int[26], Element_char[] = new int[26];
	int connect[][] = new int[26][2];
	int roll;
	
	Rotor() {
		for (i = 0; i < 26; i++) {
			Element_int[i] = i + 65;
			Element_char[i] = i + 65;
			connect[i][1] = Element_int[i];
			connect[i][0] = Element_char[i];
		}
	}
	void Rotor2(){
		for (i = 0; i < 26; i++) 
			connect[i][1] = Element_int[i];
	}
	void RollUp() {
		roll = connect[0][0];
		for (i = 0; i < 25; i++) {
			connect[i][0] = connect[i + 1][0];
		}
		connect[25][0] = roll;
	}
	void RollDown() {
		roll = connect[25][0];
		for (i = 25; i > 0; i--) {
			connect[i][0] = connect[i - 1][0];
		}
		connect[0][0] = roll;
	}
	int rotor_encoding(int code) {
		for (i = 0; i < 26; i++) {
			if (connect[i][1] == code)return connect[i][0];
		}
        return -1;
	}
	int rotor_decoding(int code) {
		for (i = 0; i < 26; i++) {
			if (connect[i][0] == code)return connect[i][1];
		}
        return -1;
	}
}
////////////////////////////////////////////////////////////////////////MANAGE ROTOR
class Rotors {
	cons c = new cons();
	Rotor rotors[] = new Rotor[3];
	int setting[] = new int[3];
	int kind_fast, kind_medium, kind_slow;
	int S, M, F, set;
	int _S, _M, _F;

	Rotors() {
		set = 1;
		for(int j = 0; j < 3; j++)
		rotors[j] = new Rotor();
	}	
	void Roll_Clock() {
		int[] smf = new int[3];
		if (set == 1) {
			smf = set_SMF(_S, _M, _F);
			_S = smf[0];
			_M = smf[1];
			_F = smf[2];
		}
		_F = circular_counter(_F);
		rotors[c.fast].RollUp();
		if (_F == kind_fast) {
			_M = circular_counter(_M);
			rotors[c.medium].RollUp();
		}
		if (_F == kind_fast && _M == kind_medium) {
			_S = circular_counter(_S);
			rotors[c.slow].RollUp();
		}
	}
	int[] set_SMF(int s, int m, int f) {
		int[] smf = new int[3];
		set = 0;
		if (F > 0)f = F + 1;
		else if (F < 0)f = F + 27;
		else f = 1;

		if (M > 0)m = M + 1;
		else if (M < 0)m = M + 27;
		else m = 1;

		if (S > 0)s = S + 1;
		else if (S < 0)s = S + 27;
		else s = 1;
		smf[0] = s;
		smf[1] = m;
		smf[2] = f;
		return smf;
	}
	void set_kind(int kind, int _which) {
		switch (_which)
		{
			case 0:
			switch (kind)
			{
				case 1:kind_fast = c.Royal; break;
				case 2:kind_fast = c.Flags; break;
				case 3:kind_fast = c.Wave; break;
				case 4:kind_fast = c.Kings; break;
				case 5:kind_fast = c.Above; break;
				default:
				break;
			}
			break;
			case 1:
			switch (kind)
			{
				case 1:kind_medium = c.Royal; break;
				case 2:kind_medium = c.Flags; break;
			case 3:kind_medium = c.Wave; break;
			case 4:kind_medium = c.Kings; break;
			case 5:kind_medium = c.Above; break;
			default:
			break;
		}
		break;
		case 2:
		switch (kind)
			{
				case 1:kind_slow = c.Royal; break;
				case 2:kind_slow = c.Flags; break;
				case 3:kind_slow = c.Wave; break;
				case 4:kind_slow = c.Kings; break;
				case 5:kind_slow = c.Above; break;
				default:
				break;
			}
			break;
			default:
			break;
		}
	}
	void set_rotors(int f, int m, int s, int ff, int mm, int ss) throws IOException {
		setting[c.fast] = ff;
		setting[c.medium] = mm;
		setting[c.slow] = ss;
		reset();
		
		set_kind(f, c.fast);
		set_kind(m, c.medium);
		set_kind(s, c.slow);
		file();
		
		rotors[c.fast].Rotor2();
		rotors[c.medium].Rotor2();
		rotors[c.slow].Rotor2();
	}
	int reflector(int t) {//COMPLETED
		if (t == 'A')return 69; if (t == 'H')return 88; if (t == 'O')return 81; if (t == 'U')return 80;
		if (t == 'B')return 74; if (t == 'I')return 86; if (t == 'P')return 85; if (t == 'V')return 73;
		if (t == 'C')return 77; if (t == 'J')return 66; if (t == 'Q')return 79; if (t == 'W')return 75;
		if (t == 'D')return 90; if (t == 'K')return 87; if (t == 'R')return 78; if (t == 'X')return 72;
		if (t == 'E')return 65; if (t == 'L')return 70; if (t == 'S')return 84; if (t == 'Y')return 71;
		if (t == 'F')return 76; if (t == 'M')return 67; if (t == 'T')return 83; if (t == 'Z')return 68;
		if (t == 'G')return 89; if (t == 'N')return 82;
        return -1;
	}
	int rotors_coding(int woco_code) {//COMPLETED
		int tcode;
		tcode = rotors[c.slow].rotor_encoding(rotors[c.medium].rotor_encoding(rotors[c.fast].rotor_encoding(woco_code)));
		tcode = reflector(tcode);
		return rotors[c.fast].rotor_decoding(rotors[c.medium].rotor_decoding(rotors[c.slow].rotor_decoding(tcode)));
	}
	int circular_counter(int num) {
		if (num == 26)return 1;
		else return ++num;
	}
	void file() throws IOException {
		int ch;
		String add = new String();
		
		//read for fast rotor
		add = set_add(c.fast);
		File file = new File(add);
		FileReader myReader = new FileReader(file);
		ch = myReader.read();
		for (int i = 0; ch != -1; i++) {
			rotors[c.fast].Element_int[i] = ch;
			ch = myReader.read();
		}
		myReader.close();
		//read for medium rotor
		add = set_add(c.medium);
		file = new File(add);
		myReader = new FileReader(file);
		ch = myReader.read();
		for (int i = 0; ch != -1; i++) {
			rotors[c.medium].Element_int[i] = ch;
			ch = myReader.read();
		}
		myReader.close();
		//read for slow rotor
		add = set_add(c.slow);
		file = new File(add);
		myReader = new FileReader(file);
		ch = myReader.read();
		for (int i = 0; ch != -1; i++) {
			rotors[c.slow].Element_int[i] = ch;
			ch = myReader.read();
		}
		myReader.close();
	}
	String set_add(int kind) {
		String add = new String();
		//set address
		switch (kind)
		{
			//set address for c.fast rotor
			case 0:
			switch (kind_fast)
			{
			case 18:add = c.royal; break;
			case 6:add = c.flags; break;
			case 23:add = c.wave; break;
			case 11:add = c.kings; break;
			case 1:add = c.above; break;
			default:
			break;
		}
		break;
		//set address for medium rotor
		case 1:
		switch (kind_medium)
			{
			case 18:add = c.royal; break;
			case 6:add = c.flags; break;
			case 23:add = c.wave; break;
			case 11:add = c.kings; break;
			case 1:add = c.above; break;
			default:
			break;
			}
			break;
			//set address for medium rotor
			case 2:
			switch (kind_slow)
			{
			case 18:add = c.royal; break;
			case 6:add = c.flags; break;
			case 23:add = c.wave; break;
			case 11:add = c.kings; break;
			case 1:add = c.above; break;
			default:
			break;
			}
			break;
			default:
			break;
		}
		return add;
	}
	void reset() {
		for (int i = 0; i < 26; i++) {
			if (setting[c.fast] == rotors[c.fast].connect[0][0])
				break;
			rotors[c.fast].RollUp();
		}
		for (int i = 0; i < 26; i++) {
			if (setting[c.medium] == rotors[c.medium].connect[0][0])
				break;
			rotors[c.medium].RollUp();
		}
		for (int i = 0; i < 26; i++) {
			if (setting[c.slow] == rotors[c.slow].connect[0][0])
				break;
			rotors[c.slow].RollUp();
		}
		set = 1;
	}
}

/////////////////////////////////////////////////////////////////////////////ENIGMA
class Enigma {
	Rotors rotors = new Rotors();
	Plugboard plugboard = new Plugboard();
	
	void Reset(){
		rotors.reset();
	}
	void Set(int FastFlag, int MediumFlag, int SlowFlag, int FastSet, int MediumSet, int SlowSet, String PlugSet) throws IOException{
		rotors.set_rotors(FastFlag, MediumFlag, SlowFlag, FastSet, MediumSet, SlowSet);
		get_plugboard(PlugSet);
	}
	String Code_Go(String woco) {//CODE GO
		String w = "";
		for(int i = 0; i < woco.length(); i++){
			int p = (int)woco.charAt(i);
			p = plugboard.plug_coding(p);
			p = rotors.rotors_coding(p);
			w = w + (char)plugboard.plug_coding(p);
			rotors.Roll_Clock();
		}
		return w;
	}
	void get_plugboard(String s) {
		int setting[][] = new int[2][10];
		for(int i = 0; i < 10; i++){
			setting[0][i] = (int)s.charAt(i*3);
			setting[1][i] = (int)s.charAt(i*3+1);
		}
		for(int i = 0; i < 10; i++)
		plugboard.set_plugboard(setting[0][i], setting[1][i]);
	}
}
/////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
///////////////////////////////////////////////////////
//////////////////////////////////////////////////////
/////////////////////////////////////////////////////
////////////////////////////////////////////////////
class EnigmaFrame extends JFrame implements ActionListener {

    Container container=getContentPane();
    JLabel fastLabel = new JLabel("Fast");
    JLabel mediumLabel = new JLabel("Medium");
    JLabel slowLabel = new JLabel("Slow");
    JLabel fastTypeLabel = new JLabel("Type");
    JLabel mediumTypeLabel = new JLabel("Type");
    JLabel slowTypeLabel = new JLabel("Type");
    JLabel plugLabel = new JLabel("Plugboard");
    JLabel text=new JLabel("Text");
    JLabel coded=new JLabel("Coded");
    Choice fastChoice = new Choice();
    Choice mediumChoice = new Choice();
    Choice slowChoice = new Choice();
    Choice fastTypeChoice = new Choice();
    Choice mediumTypeChoice = new Choice();
    Choice slowTypeChoice = new Choice();
    JTextField textField=new JTextField();
    JTextField codedField=new JTextField();
    JButton set = new JButton("Set");
    JButton reset = new JButton("Reset");
    JButton go=new JButton("GO");
    MaskFormatter formatter = new MaskFormatter("UU-UU-UU-UU-UU-UU-UU-UU-UU-UU");
    JFormattedTextField plugField = new JFormattedTextField(formatter);
	
    Enigma myEnigma = new Enigma();
    
    
	
    EnigmaFrame() throws Exception {
		//Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
		

        go.setEnabled(false);
		String[] flag = {"Royal(I)", "Flags(II)", "Wave(III)", "Kings(IV)", "Above(V)"};
        for(int i=0; i < 26 ; i++){
            fastChoice.add(""+(i+1));
            mediumChoice.add(""+(i+1));
            slowChoice.add(""+(i+1));
        }
        for(int i = 0;i < 5; i++){
            fastTypeChoice.add(flag[i]);
            mediumTypeChoice.add(flag[i]);
            slowTypeChoice.add(flag[i]);
        }
    }
	public void setLayoutManager()
	{
		container.setLayout(null);
	}
	public void setLocationAndSize()
	{
		//Setting location and Size of each components using setBounds() method.
			fastLabel.setBounds(50,20,50,30);
			fastChoice.setBounds(50,50,50,30);
			fastTypeLabel.setBounds(110,20,50,30);
			fastTypeChoice.setBounds(110,50,70,30);
			mediumLabel.setBounds(250,20,50,30);
			mediumChoice.setBounds(250,50,50,30);
			mediumTypeLabel.setBounds(310,20,50,30);
			mediumTypeChoice.setBounds(310,50,70,30);
			slowLabel.setBounds(450,20,50,30);
			slowChoice.setBounds(450,50,50,30);
			slowTypeLabel.setBounds(510,20,50,30);
			slowTypeChoice.setBounds(510,50,70,30);
			plugLabel.setBounds(50, 90, 100, 30);
			plugField.setBounds(50,120,500,30);
			set.setBounds(200,160,100,30);
			reset.setBounds(305,160,100,30);
			text.setBounds(10,300,100,30);
			coded.setBounds(10,335,100,30);
			textField.setBounds(50,300,500,30);
			codedField.setBounds(50,335,500,30);
			go.setBounds(250,375,100,30);


	}
	public void addComponentsToContainer()
	{
		//Adding each components to the Container
			container.add(fastLabel);
			container.add(fastChoice);
			container.add(fastTypeLabel);
			container.add(fastTypeChoice);
			container.add(mediumLabel);
			container.add(mediumChoice);
			container.add(mediumTypeLabel);
			container.add(mediumTypeChoice);
			container.add(slowLabel);
			container.add(slowChoice);
			container.add(slowTypeLabel);
			container.add(slowTypeChoice);
			container.add(plugLabel);
			container.add(plugField);
			container.add(set);
			container.add(reset);
			container.add(text);
			container.add(coded);
			container.add(textField);
			container.add(codedField);
			container.add(go);
			

	}

	public void addActionEvent() {
    set.addActionListener(this);
    reset.addActionListener(this);
    go.addActionListener(this);
   }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == set){
            fastChoice.setEnabled(false);
            fastTypeChoice.setEnabled(false);
            mediumChoice.setEnabled(false);
            mediumTypeChoice.setEnabled(false);
            slowChoice.setEnabled(false);
            slowTypeChoice.setEnabled(false);
            plugField.setEnabled(false);
            set.setEnabled(false);
			go.setEnabled(true);
			try {
				myEnigma.Set(
				fastTypeChoice.getSelectedIndex() + 1, 
				mediumTypeChoice.getSelectedIndex() + 1, 
				slowTypeChoice.getSelectedIndex() + 1, 
				fastChoice.getSelectedIndex() + 65, 
				mediumChoice.getSelectedIndex() + 65, 
				slowChoice.getSelectedIndex() + 65, 
				plugField.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if(e.getSource() == reset){
            fastChoice.setEnabled(true);
            fastTypeChoice.setEnabled(true);
            mediumChoice.setEnabled(true);
            mediumTypeChoice.setEnabled(true);
            slowChoice.setEnabled(true);
            slowTypeChoice.setEnabled(true);
            plugField.setEnabled(true);
            set.setEnabled(true);
			go.setEnabled(false);
        }
        if(e.getSource() == go){
            myEnigma.Reset();
            codedField.setText(myEnigma.Code_Go(textField.getText()));
        }
    }
}

public class EnigmaGUI {
    public static void main(String[] a) throws Exception{

        EnigmaFrame setFrame = new EnigmaFrame();
        setFrame.setTitle("Enigma");
        setFrame.setVisible(true);
        setFrame.setBounds(10,10,650,600);
        setFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrame.setResizable(false);
    }

}