package utility.vap.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import utility.vap.tools.Constants;

public class VideoFrame extends JFrame implements WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1234834618749053108L;

	private static String hiddenTitle = "SCC hidden";
	private static String visibleTitle = "SCC VideoClip"; //XXX Make configurable
	
	public static  EmbeddedMediaPlayerComponent lastMediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	//TODO Make configurable like in the SCC
	private int preferedWidth = 640;// Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_WIDTH);
	private int preferedHeight = 480;// Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_HEIGHT);

	public VideoFrame(JFrame reference) {
		super(hiddenTitle, reference!=null?reference.getGraphicsConfiguration():null);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		lastMediaPlayerComponent = mediaPlayerComponent;
		setLayout(new MigLayout());
		addWindowListener(this);
		// Close application once the video frame is exited!
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPreferredSize(new Dimension(preferedWidth, preferedHeight));
		mediaPlayerComponent.setSize(preferedWidth, preferedHeight);
		
		JPanel videoPanel = new JPanel(new MigLayout());
		videoPanel.add(mediaPlayerComponent, "width 100%, height 100%, span, wrap");
		add(videoPanel,"width 100%, height 100%");
		
		//stays invis at first
	}
	
	public void playVideo(String videoFile) {
		
		setVisible(true);
		pack();
		
		mediaPlayerComponent.mediaPlayer().media().play(videoFile);
		setTitle(visibleTitle);
		
	}
	
	public void unvisible() {
		setTitle(hiddenTitle);
		setVisible(false);
		deleteLock();
		//actually properly close that frame - seems to be the only thing that will "remove" it from being visible in OBS
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void deleteLock() {
		//Delete the lock file, so the VAP can be opened again!
		if(Constants.lockFile.exists())
			Constants.lockFile.delete();
	}
	
	public EmbeddedMediaPlayerComponent getMediaPlayerComponent() {
		return mediaPlayerComponent;
	}
	
	public static EmbeddedMediaPlayerComponent getLastMediaPlayerComponent() {
		return lastMediaPlayerComponent;
	}

	//Test
	public static void main(String[] args) {
		
		new VideoFrame(null).playVideo("C:\\Users\\jonas\\OneDrive\\overlays\\mixitup\\clips_shared\\on-a-boat-clipped.mp4");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Window closing, deleting lock...");
		deleteLock();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
