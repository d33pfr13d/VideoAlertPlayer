package utility.vap.command;

import java.io.File;
import java.io.IOException;

import jonas.tools.command.Command;
import utility.vap.gui.VideoFrame;
import utility.vap.tools.Constants;
import utility.vap.tools.TimeKeeper;

/**
 * Command to play videos in swing window
 */
public class PlayVideoInInternalCommand implements Command, Runnable {
	
	private String videoPath;
	
	

    public PlayVideoInInternalCommand(String videoPath) {
		super();
		this.videoPath = videoPath;
	}

	@Override
    public void execute() {
		
		if(Constants.lockFile.exists()) {
			System.out.println("VAP currently playing, skipping video: "+videoPath);
			System.exit(1);
		}
		
		
		if(!VideoFrame.getLastMediaPlayerComponent().mediaPlayer().status().isPlaying()) {
			System.out.println("Playing "+videoPath);
			VideoFrame videoFrame = new VideoFrame(null); //MainFrame.getTheMainFrame()
			videoFrame.playVideo(videoPath);
			createNewLockFile();
			new TimeKeeper(videoFrame.getMediaPlayerComponent(), videoFrame).start();
		}
		else {
			//XXX Sollange playback läuft, darf kein anderes video gestartet werden
			//ODER soll das zweite das erste abbrechen? ...
			System.out.println("VIDEO STILL PLAYING. WON'T LAUNCH SECOND CLIP!");
			
		}
    	
    }

	private void createNewLockFile() {
		try {
			Constants.lockFile.createNewFile();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void run() {
        execute();
    }
    
    // TEST
    public static void main(String[] args) {
		new PlayVideoInInternalCommand("C:\\Users\\jonas\\OneDrive\\overlays\\mixitup\\clips_shared\\on-a-boat-clipped.mp4")
		.execute();
	}
    

}
