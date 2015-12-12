package gui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import logic.IRenderable;
import res.Resource;

public class GameBackground implements IRenderable {

	public static final AlphaComposite transcluentBlack = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
	public static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	private BufferedImage bgImage = null;
	private int currentX = 0;
	private int imageWidth;
	
	public GameBackground(){
		bgImage = Resource.background;
		if(bgImage != null){
			imageWidth = bgImage.getWidth();
		}else{
			imageWidth = 0;
		}
	}
	
	public void update(){
		currentX++;
		if(currentX >= imageWidth){
			currentX = 0;
		}
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		if(bgImage == null) return;
		int currentDrawingX = 0;
		int currentDrawingY = 0;
		
		while(currentDrawingY < GameWindow.SCREEN_HEIGHT){
			g2.drawImage(bgImage.getSubimage(currentX, 0, imageWidth-currentX, bgImage.getHeight()),
					null, currentDrawingX, currentDrawingY);
			currentDrawingY += bgImage.getHeight();
		}
		currentDrawingX += imageWidth - currentX;
		currentDrawingY = 0;
		
		while(currentDrawingX < GameWindow.SCREEN_WIDTH){
			while(currentDrawingY < GameWindow.SCREEN_HEIGHT){
				g2.drawImage(bgImage, null, currentDrawingX, currentDrawingY);
				currentDrawingY += bgImage.getHeight();
			}
			currentDrawingX += imageWidth;
			currentDrawingY = 0;
		}
	}

}
