package saikaone;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[348];
    private boolean keyBeginPress[] = new boolean[348];

    private KeyListener() {

    }

    public static void endFrame() {
        Arrays.fill(get().keyBeginPress, false);
    }

    public static KeyListener get() {
        if(KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {

        //System.out.println("Key pressed: " + glfwGetKeyName(key,scancode)); //for mapping custom key later

            if (action == GLFW_PRESS && (glfwGetKeyScancode(key) != 0)) {
                get().keyPressed[key] = true;
                get().keyBeginPress[key] = true;
            } else if (action == GLFW_RELEASE && (glfwGetKeyScancode(key) != 0)) {
                get().keyPressed[key] = false;
                get().keyBeginPress[key] = false;
            }
    }
    //getter
    public static boolean isKeyPressed(int keyCode) {
            return get().keyPressed[keyCode];
    }
    public static boolean keyBeginPress(int keyCode) {
        return get().keyBeginPress[keyCode];
    }
}
