package lib;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class ShellNavigation implements NativeKeyListener {

    public ShellNavigation() {
        this.key = "";
        this.position = 0;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private void insert(String what_to_insert) {
        this.message = message.substring(0, position) + what_to_insert + message.substring(position);
    }

    private void desertLastPosition() {
        this.message = message.substring(0, lastPosition) + message.substring(lastPosition + 1);
    }

    public String key;

    private final String cursor = "|";
    private String message;
    private int position;
    private int lastPosition;

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        lastPosition = position;
        desertLastPosition();

        if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_LEFT) {
            position--;
        } else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
            position++;
        } else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_D) {
            insert("D");
        }
        insert(cursor);

        if (position == -1) {
            position = 0;
        } else if (position == message.length()) {
            position = message.length() - 1;
        }
    }
}
