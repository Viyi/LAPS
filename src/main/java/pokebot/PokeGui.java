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

public class PokeGui {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPasssword;
	private JTextField txtToPlay;

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
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblLaps = new JLabel("LAPS");
		lblLaps.setBounds(10, 11, 415, 52);
		frame.getContentPane().add(lblLaps);
		
		JCheckBox chckbxDefault = new JCheckBox("Default");
		chckbxDefault.setBounds(6, 55, 97, 23);
		frame.getContentPane().add(chckbxDefault);
		
		JCheckBox chckbxCustom = new JCheckBox("Custom");
		chckbxCustom.setBounds(6, 81, 97, 23);
		frame.getContentPane().add(chckbxCustom);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username:");
		txtUsername.setBounds(10, 117, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPasssword = new JTextField();
		txtPasssword.setText("Passsword:");
		txtPasssword.setBounds(10, 142, 86, 20);
		frame.getContentPane().add(txtPasssword);
		txtPasssword.setColumns(10);
		
		JLabel lblPlaystyle = new JLabel("Playstyle:");
		lblPlaystyle.setBounds(10, 184, 93, 14);
		frame.getContentPane().add(lblPlaystyle);
		
		JCheckBox chckbxAggressive = new JCheckBox("Aggressive");
		chckbxAggressive.setBounds(6, 205, 97, 23);
		frame.getContentPane().add(chckbxAggressive);
		
		JCheckBox chckbxTactical = new JCheckBox("Tactical");
		chckbxTactical.setBounds(6, 231, 97, 23);
		frame.getContentPane().add(chckbxTactical);
		
		JButton btnMute = new JButton("Mute");
		btnMute.setBounds(10, 283, 89, 23);
		frame.getContentPane().add(btnMute);
		
		JCheckBox chckbxProxy = new JCheckBox("Proxy");
		chckbxProxy.setBounds(10, 329, 97, 23);
		frame.getContentPane().add(chckbxProxy);
		
		JCheckBox chckbxInfinite = new JCheckBox("Infinite");
		chckbxInfinite.setBounds(10, 355, 97, 23);
		frame.getContentPane().add(chckbxInfinite);
		
		txtToPlay = new JTextField();
		txtToPlay.setText("# to play:");
		txtToPlay.setBounds(10, 385, 86, 20);
		frame.getContentPane().add(txtToPlay);
		txtToPlay.setColumns(10);
	}
}
