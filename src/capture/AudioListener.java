package capture;

import javax.sound.sampled.*;

import java.awt.AWTException;
import java.io.InputStream;


public class AudioListener
{
    private TargetDataLine dataLine;
    private DataLine.Info dInfo;
    private AudioFormat format;
    private InputStream inputStream;
    private int mixerIndex = 0;
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
       format = new AudioFormat(44100, 16, 2, true, false);
       dInfo = new DataLine.Info(TargetDataLine.class, format);
       System.out.println(AudioSystem.isLineSupported(dInfo));
        try 
        {
            dataLine = (TargetDataLine) AudioSystem.getTargetDataLine(format);
            dataLine.open();
        } catch (LineUnavailableException e) {e.printStackTrace();}


       CaptureManager cm = new CaptureManager(60);
       cm.start();
    }

    public static void main(String[] args)
    {
        new AudioListener();    
    }
}