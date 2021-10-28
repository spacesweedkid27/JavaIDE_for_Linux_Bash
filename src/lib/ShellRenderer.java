package lib;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class ShellRenderer extends Thread {


    public ShellRenderer() {

    }

    public ShellRenderer(long milliseconds) {
        this.milliseconds = milliseconds;
    }


    private long milliseconds;
    private String frame;

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getFrame() {
        return this.frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    @Override
    public void run() {

        ShellNavigation shellNavigation = new ShellNavigation();


        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {

        }
        GlobalScreen.addNativeKeyListener(shellNavigation);


        while (true) {
            setFrame(shellNavigation.getMessage());

            System.out.print(frame);
            try {
                sleep(milliseconds);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }

    }


}
