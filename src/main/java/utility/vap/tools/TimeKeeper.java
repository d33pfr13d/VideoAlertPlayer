package utility.vap.tools;

import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import utility.vap.gui.VideoFrame;

/**
 * Thread der prüft, ob das video durch ist um das Programm anschließend zu beenden!
 * @author d33pfr13d
 *
 */
public class TimeKeeper extends Thread {

		private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
		private volatile boolean running = true;
		private final VideoFrame vFrame;
		

		public TimeKeeper(EmbeddedMediaPlayerComponent mediaPlayerComponent, VideoFrame vFrame) {
			super();
			this.mediaPlayerComponent = mediaPlayerComponent;
			this.vFrame = vFrame;
		}

		public void run() {
			do {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// ignore
				}

				// Muss im gui thread passieren
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						if(!mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
							// EXIT THE PROGRAM
							// /(the vap utilty will only play one video and exit then)
							// XXX HANDLED BY THE vFrame AND NO LONGER HERE because cleanup needs to be done!!!
//							System.exit(0);
							//hide frame
							vFrame.unvisible();
							return;
						}
						
					}

				});

			} while (running);
		}

		public void halt() {
			running = false;
		}

	}