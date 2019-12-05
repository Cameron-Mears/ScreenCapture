package capture;

import javax.sound.sampled.*;

import java.awt.AWTException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class AudioListener implements LineListener
{
    private SourceDataLine dataLine;
    private DataLine.Info dInfo;
    private AudioFormat format;
    private InputStream inputStream;
    private int mixerIndex = 0;
    private byte[] buf;
    private Mixer desktopAudio;

    public AudioListener()
    {
       Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
       
       
       for (int index = 0; index < mixerInfo.length; index++) 
       {
            Mixer.Info temp = mixerInfo[index];
            if (temp.getName().equals("Speakers (Realtek High Definition Audio)"))
            {
                mixerIndex = index;
                break;
            }
       }
       desktopAudio = AudioSystem.getMixer(mixerInfo[mixerIndex]);
       desktopAudio.addLineListener(this);
       format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
       dInfo = new DataLine.Info(SourceDataLine.class, format);
       System.out.println(AudioSystem.isLineSupported(dInfo));
        try 
        {
            dataLine = (SourceDataLine) AudioSystem.getLine(dInfo);
            dataLine.open(format);
            buf = new byte[dataLine.getBufferSize()];

        } catch (LineUnavailableException e) {e.printStackTrace();}


       CaptureManager cm = new CaptureManager(60, this);
       cm.start();
    }

    public void printLevel()
    {
       dataLine.write(buf, 0, 10000);

       BufferedInputStream bis = new BufferedInputStream(new InputStream(){
       
           @Override
           public int read() throws IOException {
               return 0;
           }
       });

       
    }

    public static void main(String[] args)
    {
        new AudioListener();    
    }

    @Override
    public void update(LineEvent event) 
    {
        System.out.println(event.getType());
    }
}