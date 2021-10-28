package lib;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class ShellRenderer extends Thread {


    public ShellRenderer() {

    }

    public ShellRenderer(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    private ShellNavigation shellNavigation;
    private long milliseconds;
    private String frame;
    private String rawFrame;

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getFrame() {
        return this.frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setRawFrame(String rawFrame) {
        this.rawFrame = rawFrame;
    }

    private void init() {
        shellNavigation = new ShellNavigation();
        shellNavigation.setMessage(rawFrame);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
        }
        GlobalScreen.addNativeKeyListener(shellNavigation);
    }

    private boolean isWindows;

    @Override
    public void run() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            isWindows = true;
        }
        init();

        while (true) {
            setFrame(shellNavigation.getMessage());
            if (!isWindows) {
                System.out.print(ShellAccessor.checkAndColor(frame));
            } else {
                System.out.print(frame);
            }
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
