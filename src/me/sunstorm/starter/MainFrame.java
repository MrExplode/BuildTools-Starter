package me.sunstorm.starter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Image;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7207433073427653341L;

	private JPanel contentPane;
	
	public String version = "lastest";
	public String bashDir = "C:\\Program Files\\Git\\bin\\bash.exe";
	public String buildDir = "";
	private JTextField txtLastest;
	private JTextField gitLoc;
	private JTextField btLoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Bold-Black@2x.png")));
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		setTitle("BuildTools Starter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 414, 239);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel desc = new JLabel("Start BuildTools without scripting");
		desc.setBounds(10, 11, 179, 14);
		panel.add(desc);
		
		JLabel lblVersion = new JLabel("version:");
		lblVersion.setBounds(65, 85, 46, 14);
		panel.add(lblVersion);
		
		txtLastest = new JTextField();
		txtLastest.setToolTipText("The version being compiled.");
		txtLastest.setText("latest");
		txtLastest.setBounds(113, 82, 112, 20);
		panel.add(txtLastest);
		txtLastest.setColumns(10);
		
		JCheckBox chkDoc = new JCheckBox("Generate JavaDoc");
		chkDoc.setToolTipText("Select if you waht to generate  JavaDoc");
		chkDoc.setBounds(262, 145, 146, 23);
		panel.add(chkDoc);
		
		JCheckBox chkSource = new JCheckBox("Generate Source");
		chkSource.setToolTipText("Select if you want to generate source");
		chkSource.setBounds(262, 110, 146, 23);
		panel.add(chkSource);
		
		JCheckBox skipComp = new JCheckBox("Skip compile");
		skipComp.setToolTipText("Select if you want to skip compiling");
		skipComp.setBounds(262, 34, 146, 23);
		panel.add(skipComp);
		
		JCheckBox checkDisable = new JCheckBox("Disable certificate check");
		checkDisable.setToolTipText("Select if you want to skip compiling");
		checkDisable.setBounds(262, 182, 146, 23);
		panel.add(checkDisable);
		
		JCheckBox update = new JCheckBox("Don't update");
		update.setToolTipText("Select if you don't want to update.");
		update.setBounds(262, 72, 146, 23);
		panel.add(update);
		
		JLabel lblGit = new JLabel("Git location:");
		lblGit.setBounds(54, 211, 57, 14);
		panel.add(lblGit);
		
		JLabel lblBuildtoolsLocation = new JLabel("BuildTools location:");
		lblBuildtoolsLocation.setBounds(20, 183, 91, 14);
		panel.add(lblBuildtoolsLocation);
		
		JLabel label = new JLabel("");
		Image img = (new ImageIcon(MainFrame.class.getResource("/me/sunstorm/starter/icon.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(img));
		label.setBounds(10, 93, 100, 100);
		panel.add(label);
		
		JLabel lblAttentionUse = new JLabel("ATTENTION: use '/' in file path instead of '\\'");
		lblAttentionUse.setBounds(10, 53, 215, 14);
		panel.add(lblAttentionUse);
		
		gitLoc = new JTextField();
		gitLoc.setToolTipText("Points at Git/bin folder");
		gitLoc.setBounds(113, 208, 112, 20);
		panel.add(gitLoc);
		gitLoc.setColumns(10);
		
		btLoc = new JTextField();
		btLoc.setToolTipText("Points at the folder of BuildTools.jar");
		btLoc.setColumns(10);
		btLoc.setBounds(113, 180, 112, 20);
		panel.add(btLoc);
		
		File save = new File(System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "Build" + File.separator + "save.cfg");
		if (save.exists()) {
			try {
				System.out.println("save file exists");
				BufferedReader reader = new BufferedReader(new FileReader(save));
				gitLoc.setText(reader.readLine());
				btLoc.setText(reader.readLine());
				reader.close();
			} catch (IOException e1 ) {
				// catch exception
				e1.printStackTrace();
			}
		}
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Started");
				StringBuilder arguments = new StringBuilder();
				if (update.isSelected()) {
					arguments.append("--dont-update ");
					System.out.println("No update");
				}
				if (checkDisable.isSelected()) {
					arguments.append("--disable-certificate-check ");
					System.out.println("Disable certificate");
				}
				if (skipComp.isSelected()) {
					arguments.append("--skip-compile ");
					System.out.println("Skip compile");
				}
				if (chkSource.isSelected()) {
					arguments.append("--generate-source ");
					System.out.println("Generate source");
				}
				if (chkDoc.isSelected()) {
					arguments.append("--generate-docs ");
					System.out.println("Generate docs");
				}
				
				/*
				System.out.println("Entering the cycle of death powered by the evil OutOfMemoryError, feat: Jave heap space");
				int length = arguments.length();
				for (int i = 0; i < length; i++) {
					char c = arguments.charAt(i);
					System.out.println(c);
					if (c == '-' && i != 0) {
						if (arguments.charAt(i-1) == '-' && i != 1) {
							arguments.insert(i -1, " ");
						} else {
							arguments.insert(i, " ");
						}
					}
				}
				*/
				
				{//scope for visibility
					System.out.println("entered saving scope...");
					File b = new File(btLoc.getText());
					File g = new File(gitLoc.getText());
					if (!b.exists() || !g.exists()) {
						JOptionPane.showMessageDialog(null, "The specified directory not exist!", "BuildTools Starter", JOptionPane.ERROR_MESSAGE, null);
						return;
					} else {
						try {
							if (!save.exists())
								save.getParentFile().mkdirs();
								save.createNewFile();
							BufferedWriter writer = new BufferedWriter(new FileWriter(save));
							writer.write(gitLoc.getText() + "\n");
							writer.write(btLoc.getText());
							writer.flush();
							writer.close();
						} catch (IOException e1) {
							// catch exception
							e1.printStackTrace();
						}
					}
				}
				System.out.println("exited the saving scope");
				
				bashDir = "\"" + gitLoc.getText() + "/bash.exe\"";
				buildDir = "\"" + btLoc.getText() + "/BuildTools.jar\"";
				version = txtLastest.getText();
				
				File launcher = new File(System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "Build" + File.separator + "starter.bat");
				try {
					launcher.getParentFile().mkdirs();
					launcher.createNewFile();
					BufferedWriter writer2 = new BufferedWriter(new FileWriter(launcher));
					writer2.write(bashDir + " --login -i -c \"java -jar " + buildDir + " --rev " + version + " " + arguments.toString().trim() + "\n");
					writer2.write("pause");
					writer2.flush();
					writer2.close();
				} catch (IOException e2) {
					// catch exception
					e2.printStackTrace();
				}
				
				
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c start \"\" " + launcher.getPath());
				try {
					System.out.println("started process: " + bashDir + " --login -i -c \"java -jar " + buildDir + " --rev " + version + " " + arguments.toString() + "\"" + "\n");
					@SuppressWarnings("unused")
					Process p = builder.start();
				} catch (IOException e1) {
					// catch exception
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnStart.setBounds(113, 118, 112, 39);
		panel.add(btnStart);
		
	}
}
