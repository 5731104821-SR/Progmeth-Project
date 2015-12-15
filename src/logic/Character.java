package logic;

import java.awt.image.BufferedImage;

public abstract class Character extends ScreenObject {

	public Character(double x, double y, double speedX, double speedY, double accelX, double accelY, int maxHp, BufferedImage image) {
		super(x, y, speedX, speedY, accelX, accelY, maxHp, image);
	}

	public abstract boolean collideWith(ScreenObject object);
}
