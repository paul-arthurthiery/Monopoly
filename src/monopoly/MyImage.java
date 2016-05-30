

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MyImage extends JLayeredPane {
	
	BufferedImage image;
	
	public MyImage(String source) throws IOException
	{
		this.image = ImageIO.read(new File(source));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(this.image , 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
	@Override
	public Dimension getPreferredSize()
	{
		 return new Dimension(310, 310);
	}
	
}
