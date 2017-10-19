import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Scanner;

public class main {
	public void drawRectangle(int x, int y, int w, int h) {

	}
	public static void main(String[] args) {
		int x = 1280; //to be replaced by Scanner
		int y = 720;
		
		BufferedImage bim = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		
		try {
		    File outputfile = new File("test.png");
		    ImageIO.write(bim, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Something went wrong, apparently");
			System.exit(0);
		}
	}
}
	