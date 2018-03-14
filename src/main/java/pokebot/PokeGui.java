package pokebot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PokeGui {

	public JFrame frame;
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JPasswordField passwordField;
	private JTextField textField;
	String[] settings = new String[8];
	JCheckBox chckbxDefault = new JCheckBox("Default");
	JCheckBox chckbxProxy = new JCheckBox("Proxy");
	JCheckBox chckbxMute = new JCheckBox("Mute");
	JCheckBox chckbxStartGame = new JCheckBox("Start Game");
	JCheckBox chckbxCustom = new JCheckBox("Custom");
	JCheckBox chckbxAggressive = new JCheckBox("Aggressive");
	private int ready = 0;
	public int getReady() {
		return ready;
	}

	public void setReady(int ready) {
		this.ready = ready;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokeGui window = new PokeGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokeGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setSettings() {
		settings[0] = "" + chckbxCustom.isSelected();
		settings[1] = textField.getText();
		settings[2] = new String(passwordField.getPassword());
		settings[3] = "" + chckbxProxy.isSelected();
		settings[4] = "" + chckbxMute.isSelected();
		settings[5] = "" + chckbxAggressive.isSelected();
		
		
	}
	public JFrame getFrame() {
		return frame;
	}
	public String[] getSettings() {
		
		
		return settings;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setSize(250,480);
	
		
		
		
		JLabel lblLaps = new JLabel("LAPS");
		lblLaps.setBounds(10, 11, 415, 52);
		frame.getContentPane().add(lblLaps);
		
		
		chckbxDefault.setBounds(6, 55, 97, 23);
		frame.getContentPane().add(chckbxDefault);
		
		
		chckbxCustom.setBounds(6, 81, 97, 23);
		frame.getContentPane().add(chckbxCustom);
		
		JLabel lblPlaystyle = new JLabel("Playstyle:");
		lblPlaystyle.setBounds(10, 184, 93, 14);
		frame.getContentPane().add(lblPlaystyle);
		
		
		chckbxAggressive.setBounds(10, 205, 114, 23);
		frame.getContentPane().add(chckbxAggressive);
		
		JCheckBox chckbxTactical = new JCheckBox("Tactical");
		chckbxTactical.setBounds(10, 232, 97, 23);
		frame.getContentPane().add(chckbxTactical);
		
		
		chckbxProxy.setBounds(10, 285, 97, 23);
		frame.getContentPane().add(chckbxProxy);
		
		JCheckBox chckbxInfinite = new JCheckBox("Infinite");
		chckbxInfinite.setBounds(6, 377, 97, 23);
		frame.getContentPane().add(chckbxInfinite);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 153, 86, 19);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(10, 122, 86, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 107, 89, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 139, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		
		chckbxMute.setBounds(10, 258, 129, 23);
		frame.getContentPane().add(chckbxMute);
		
		JLabel lblOfGames = new JLabel("# of games");
		lblOfGames.setBounds(12, 328, 91, 15);
		frame.getContentPane().add(lblOfGames);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(10, 349, 29, 20);
		frame.getContentPane().add(spinner);
		
		
		chckbxStartGame.setBounds(54, 409, 129, 23);
		frame.getContentPane().add(chckbxStartGame);
		
		
	}
}
