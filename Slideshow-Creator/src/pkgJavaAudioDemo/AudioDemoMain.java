package pkgJavaAudioDemo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

//=============================================================================
/** Class: AudioDemoMain
*  Purpose: This class implements the main window for an audio file player
*  Author: Dr. Rick Coleman
*  Date: February 2016
*  Notes:
*  This application is an adaptation of code found in the demonstration
*  JavaSound found on-line.  It still needs some work.  My original
*  understanding of playing audio files with Java is that for long
*  files it would load and play as a BufferedInputStream or a Sequence, but
*  all files used to test this program load as clips, i.e. the entire
*  file is read into memory and then played.
*  In this version fast forward and rewind are not implemented yet.
*  There are several other function in the Clip class that can be used:
*  int getFrameLength() - returns the media length in sample frames.
*  int getMicrosecondLength() - returns the media length in microseconds.
*  void setFramePosition(int frames) - set the play position in the midia
*  			by specifying the number of frames offset from the beginning.
*  void setMicrosecondPosition(int ms) - set the play position in the midia
*  			by specifying the number of microseconds offset from the beginning.
*  On a windows system a warning pops up when run stating:
*  java.util.prefs.WindowsPreferences <init>
*  WARNING: Could not open/create prefs root node Software\JavaSoft\Prefs at root 0x80000002. Windows RegCreateKeyEx(...) returned error code 5.
*  I have no idea what this means as the application still seems to run OK.
*  
*  FYI:  Someone in the FAQ about file sizes says he is getting an Out of
*  		Memory error on files of 5Meg or more. But, I'm playing files of
*  		24+ meg using clips.  The printouts are showing a clip is created
*  		not an audio input stream.  Ten meg is about the
*  		memory size for 1 minute of playback.
*/
//=============================================================================
public class AudioDemoMain extends JFrame
{
	/** Main screen width  */
	public int m_iScnWidth = 640;
	
	/** Main screen height  */
	public int m_iScnHeight = 480;
	
	/** This JFrame - so action listeners can get to it */
	private JFrame m_ThisJFrame;
	
	/** Center panel for whatever use */
	public JPanel m_MainPanel;
	
	/** Button panel for misc. buttons */
	public JPanel m_ButtonPanel;
	
	/** Select audio file  button */
	private JButton m_SelectAudioFileBtn;
	
	/** Rewind button */
	private JButton m_RewindBtn;

	/** Play button */
	private JButton m_PlayBtn;
	
	/** Pause button */
	private JButton m_PauseBtn;
	
	/** Stop button */
	private JButton m_StopBtn;

	/** Fast forward button */
	private JButton m_FastForwardBtn;
	
	/** Exit button */
	private JButton m_ExitBtn;
	
	/** Audio player control panel */
	private AudioPlayer m_Player = null;
	
	/** File name to open and play */
	private String m_sAudioFileName;
		
	/** File to open and play */
	private File m_AudioFile = null;
		
	/** Flag if a file is playing */
	private boolean m_bIsPlaying = false;
	
	/** Flag if we are in pause mode */
	private boolean m_bIsPaused = true;
	
	//----------------------------------------------------------------------
	/** Default constructor  */
	//----------------------------------------------------------------------
	public AudioDemoMain()
	{
		m_ThisJFrame = this;	// Set the reference to this JFrame so action listeners can get it.
		
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Center on the screen
        this.setLocation((d.width/2 - m_iScnWidth/2), (d.height/2 - m_iScnHeight/2));
        this.setTitle("Audio File Player Demonstration");
		this.setSize(m_iScnWidth, m_iScnHeight);
		this.setResizable(false);
		this.getContentPane().setLayout(null); // We'll do our own layouts, thank you.
		this.getContentPane().setBackground(Color.gray); // Set visible area to gray
		
		// Create the main panel.  We'll use this for some simple graphics to see
		//    if we can be drawing while the file is playing
		m_MainPanel = new JPanel();
		m_MainPanel.setSize(this.getWidth(), this.getHeight() - 75);
		m_MainPanel.setLocation(0,0);
		m_MainPanel.setBackground(Color.LIGHT_GRAY);
		m_MainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(m_MainPanel);
		
		// Create the button panel.  
		m_ButtonPanel = new JPanel();
		m_ButtonPanel.setSize(this.getWidth(), 50);
		m_ButtonPanel.setLocation(0,this.getHeight() - 75);
		m_ButtonPanel.setBackground(Color.LIGHT_GRAY);
		m_ButtonPanel.setLayout(null);
		m_ButtonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(m_ButtonPanel);
		
		int xLoc = 175;
		int yLoc = 5;
		
		// Create the select audio file button
		m_SelectAudioFileBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/OpenFile.jpg")));
		m_SelectAudioFileBtn.setSize(40, 40);
		m_SelectAudioFileBtn.setLocation(xLoc, yLoc);
		m_SelectAudioFileBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_SelectAudioFileBtn.setToolTipText("Click to select an audio file to play.");
		m_SelectAudioFileBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//	Handle getting the audio file to play
						selectAudioFile();
					}
				});
		m_ButtonPanel.add(m_SelectAudioFileBtn);
		xLoc+=50;
		
		
		// Create the rewind button
		m_RewindBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/Rewind.jpg")));
		m_RewindBtn.setSize(40, 40);
		m_RewindBtn.setLocation(xLoc, yLoc);
		m_RewindBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_RewindBtn.setToolTipText("Click to rewind the audio file.");
		m_RewindBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//	Handle rewind
					}
				});
		m_RewindBtn.setEnabled(false);  // turn off till we have a file
		m_ButtonPanel.add(m_RewindBtn);
		xLoc+=50;

		// Create the play button
		m_PlayBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/Play.jpg")));
		m_PlayBtn.setSize(40, 40);
		m_PlayBtn.setLocation(xLoc, yLoc);
		m_PlayBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_PlayBtn.setToolTipText("Click to play the audio file.");
		m_PlayBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(!m_bIsPlaying)
						{
							//	Handle playing the file
							m_PauseBtn.setVisible(true);  // Show the pause button
							m_PlayBtn.setVisible(false);  // Hide the play button							
							m_bIsPlaying = true;
							m_bIsPaused = false;
							if(m_AudioFile != null)
							{
								// Create an audio player and start the file playing
								if(m_Player == null)
									m_Player = new AudioPlayer(m_ThisJFrame);
							}
						}
						else
						{
							// resume playing
							if(m_bIsPaused)
							{
								m_bIsPaused = false;
								m_PauseBtn.setVisible(true);  // Show the pause button
								m_PlayBtn.setVisible(false);  // Hide the play button							
								if(m_Player != null)
									m_Player.resumePlaying();
							}
						}
					}
				});
		m_PlayBtn.setEnabled(false);  // turn off till we have a file
		m_ButtonPanel.add(m_PlayBtn);
//		xLoc+=50;
		
		// Create the pause button
		m_PauseBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/Pause.jpg")));
		m_PauseBtn.setSize(40, 40);
		m_PauseBtn.setLocation(xLoc, yLoc);
		m_PauseBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_PauseBtn.setToolTipText("Click to pause playing.");
		m_PauseBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//	Handle pausing play
						if(m_Player != null)
							m_Player.pausePlaying();
						m_PauseBtn.setVisible(false);  // Hide the pause button
						m_PlayBtn.setVisible(true);    // Show the play button
						m_bIsPaused = true;
					}
				});
		m_ButtonPanel.add(m_PauseBtn);
		m_PauseBtn.setVisible(false);
		xLoc+=50;

		// Create the stop button
		m_StopBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/Stop.jpg")));
		m_StopBtn.setSize(40, 40);
		m_StopBtn.setLocation(xLoc, 5);
		m_StopBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_StopBtn.setToolTipText("Click to stop playing.");
		m_StopBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//	Handle stopping play
						m_bIsPlaying = false;
						m_bIsPaused = false;
						// Call stop function in AudioPlayer
						if(m_Player != null)
							m_Player.stopPlaying();
						// Disable all buttons
						m_PauseBtn.setVisible(false);  // Hide the pause button
						m_PlayBtn.setVisible(true);    // Show the play button
						m_PlayBtn.setEnabled(false);  
						m_RewindBtn.setEnabled(false);
						m_FastForwardBtn.setEnabled(false);
						m_StopBtn.setEnabled(false);
						// Clear all other stuff
						m_AudioFile = null;
						m_Player = null;
						System.gc(); // Force garbage collection
					}
				});
		m_ButtonPanel.add(m_StopBtn);
		m_StopBtn.setEnabled(false);  // turn off till we have a file
		xLoc+=50;

		// Create the fast forward button
		m_FastForwardBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/FastForward.jpg")));
		m_FastForwardBtn.setSize(40, 40);
		m_FastForwardBtn.setLocation(xLoc, yLoc);
		m_FastForwardBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_FastForwardBtn.setToolTipText("Click to fast forward playing.");
		m_FastForwardBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//	Handle fast forward
					}
				});
		m_FastForwardBtn.setEnabled(false);  // turn off till we have a file
		m_ButtonPanel.add(m_FastForwardBtn);
		xLoc+=50;

		// Create the exit button
		m_ExitBtn = new JButton(new ImageIcon(getClass().getResource("ButtonImages/Exit.jpg")));
		m_ExitBtn.setSize(40, 40);
		m_ExitBtn.setLocation(xLoc, yLoc);
		m_ExitBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		m_ExitBtn.setToolTipText("Click to exit the Audo Demo Player.");
		m_ExitBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Exit the application with status=0 (normal exit)
						System.exit(0);
					}
				});
		m_ButtonPanel.add(m_ExitBtn);
		
		// Make the window visible
		this.setVisible(true);
		

	}

	//----------------------------------------------------------------------
	/** Select the audio file to play */
	//----------------------------------------------------------------------
	private void selectAudioFile()
	{
		int retValue;	// Return value from the JFileChooser

		 
	    JFileChooser chooser = new JFileChooser();	// Create the file chooser dialog box
	    chooser.setDialogTitle("Select Audio File"); // Set dialog title
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only select files
	    chooser.addChoosableFileFilter(null);
	    chooser.setApproveButtonText("Select");
	    retValue = chooser.showOpenDialog(this); // Show the dialog box
	    if(retValue == JFileChooser.APPROVE_OPTION) // User selected a file
	    {
	    	// Got a file so get it's full path
	    	m_sAudioFileName = chooser.getSelectedFile().getAbsolutePath();
	    	m_AudioFile =  new File(m_sAudioFileName);
	    	// Now that we have a file turn on the control buttons
			m_PlayBtn.setEnabled(true);  
			m_RewindBtn.setEnabled(false);
			m_FastForwardBtn.setEnabled(false);
			m_StopBtn.setEnabled(true);
	    }
	}
	
	//----------------------------------------------------------------------
	/** Get the file to play.  The AudioPlayer calls this function when
	 *  the thread is started. */
	//----------------------------------------------------------------------
	public File getAudioFile()
	{
		return m_AudioFile;
	}

	//----------------------------------------------------------------------
	/** Main function for this demonstration
	 * @param args - Array of strings from the command line
	 */
	//----------------------------------------------------------------------
	public static void main(String[] args) 
	{
		// When you start this application this function gets called by the
		//  operating system.  Main just creates an ImageViewer object.
		//  To follow the execution trail from here go to the ImageViewer
		//  constructor.
		AudioDemoMain adm = new AudioDemoMain();
	}
}
