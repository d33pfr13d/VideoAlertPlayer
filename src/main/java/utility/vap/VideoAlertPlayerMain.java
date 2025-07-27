package utility.vap;

import utility.vap.command.PlayVideoInInternalCommand;

public class VideoAlertPlayerMain {
	
	public static void main(String[] args) {
		
		if(args.length < 1 || args[0].length() < 1) {
			System.out.println("Pass the path to the video file as argument!");
		}
		else {
			// Assemble all arguments into one path to allow for spaces in the path
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<args.length; i++) {
				sb.append(args[i]);
			}
			
			new PlayVideoInInternalCommand(sb.toString()).execute();;
		}
	}

}
