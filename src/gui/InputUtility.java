package gui;

public class InputUtility {

	private static int mouseX,mouseY;
	private static boolean mouseLeftDown,mouseRightDown,mouseOnScreen;
	private static boolean mouseLeftTriggered,mouseRightTriggered;
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];
	
	public static int getMouseX() {
		/* fill getters & setters */
		return mouseX;
	}
	public static void setMouseX(int mouseX) {
		/* fill getters & setters */
		InputUtility.mouseX = mouseX;
	}
	public static int getMouseY() {
		/* fill getters & setters */
		return mouseY;
	}
	public static void setMouseY(int mouseY) {
		/* fill getters & setters */
		InputUtility.mouseY = mouseY;
	}
	
	public static boolean isMouseLeftDown() {
		/* fill getters & setters */
		return mouseLeftDown;
	}
	public static void setMouseLeftDown(boolean mouseLeftDown) {
		/* fill getters & setters */
		InputUtility.mouseLeftDown = mouseLeftDown;
	}
	public static boolean isMouseRightDown() {
		/* fill getters & setters */
		return mouseRightDown;
	}
	public static void setMouseRightDown(boolean mouseRightDown) {
		/* fill getters & setters */
		InputUtility.mouseRightDown = mouseRightDown;
	}
	
	public static boolean isMouseOnScreen() {
		/* fill getters & setters */
		return mouseOnScreen;
	}
	public static void setMouseOnScreen(boolean mouseOnScreen) {
		/* fill getters & setters */
		InputUtility.mouseOnScreen = mouseOnScreen;
	}
	
	public static boolean isMouseLeftClicked() {
		/* fill getters & setters */
		return mouseLeftTriggered;
	}
	public static void setMouseLeftTriggered(boolean v){
		/* fill getters & setters */
		InputUtility.mouseLeftTriggered = v;
	}
	public static boolean isMouseRightClicked() {
		/* fill getters & setters */
		return mouseRightTriggered;
	}
	public static void setMouseRightTriggered(boolean v){
		/* fill getters & setters */
		InputUtility.mouseRightTriggered = v;
	}
	
	public static boolean getKeyPressed(int key) {
		/* fill getters & setters */
		if(key < 0 || key >= keyPressed.length) return false;
		return keyPressed[key];
	}
	public static void setKeyPressed(int key,boolean pressed) {
		/* fill getters & setters */
		if(key >= 0 && key < keyPressed.length){
			keyPressed[key] = pressed;
		}
	}
	
	public static boolean getKeyTriggered(int key) {
		/* fill getters & setters */
		if(key < 0 || key >= keyTriggered.length) return false;
		return keyTriggered[key];
	}
	public static void setKeyTriggered(int key,boolean pressed) {
		/* fill getters & setters */
		if(key >= 0 && key < keyTriggered.length){
			keyTriggered[key] = pressed;
		}
	}
}
