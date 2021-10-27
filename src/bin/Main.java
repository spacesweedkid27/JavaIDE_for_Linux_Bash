package bin;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import lib.ShellAccessor;
import lib.ShellRenderer;


public class Main extends ShellAccessor {
    static String test =
            """
                    private static String getThisShite(String input){                 
                            StringBuilder temp = new StringBuilder(input.length());   
                            String miniTemp = "";                                     
                            for(int i = 0; i < input.length(); i++){                  
                                if (input.toCharArray()[i] == '\\n'){                 
                                    temp.append(miniTemp + ", ");                     
                                    miniTemp = "";                                    
                                } else if (input.toCharArray()[i] == '0'){            
                                    temp.append(miniTemp);                            
                                    return temp.toString();                           
                                } else                                                
                                    miniTemp = miniTemp + input.toCharArray()[i];     
                            }                                                         
                            return null;                                              
                        }""";

    public static void demo1() throws InterruptedException {
        long milliseconds = 25;

        ShellRenderer shellRenderer = new ShellRenderer(milliseconds);
        shellRenderer.start();

        while (true) {
            for (int i = 0; i < test.length(); i++) {
                Thread.sleep(milliseconds);
                shellRenderer.setFrame(checkAndColor(test.substring(0, i) + "<*>" + test.substring(i + 2)));
            }


        }
    }

    public static void main(String[] args) {
        //ShellRenderer shellRenderer = new ShellRenderer(200);
        //shellRenderer.start();

        NativeKeyListener nativeKeyListener = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                System.out.println(NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
            }
        };

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(nativeKeyListener);


        while (true) {

        }

    }


}
