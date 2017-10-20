import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;

import javax.imageio.*;

public class main {	
	public static void main(String[] args) {
		int x = 1280;
		int y = 720;
		int ceilingX = 128; //used as a pointer
		int ceilingY = 72;
		int allowedX = 1024; //allowed range for X
		int allowedY = 576;
		int readLine = 0; //reading line number
		int lineNum = 0; //total number of lines
		
		String fileName = "input.txt";
		
		int numLines = getNumLines(fileName); //IMPORTANT, gets value on init stage
		int numBars = numLines;
		int numSpaces = numLines - 1;
		int numLinesDrawn = 0;
		int width = allowedX / (numBars + numSpaces); //width of bars and spaces
		int height; //temporary, to be replaced with actual value
		
		System.out.println(numLines + " " + width); //debug
		
		
		BufferedImage bim = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = bim.createGraphics();
		

		g2d.setColor(new Color(Integer.parseInt( "FFFFFF", 16 ) ) );
		g2d.fillRect(0, 0, x, y); //background
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while (bufferedReader.readLine() != null) lineNum++; //getting total number of lines
			
			bufferedReader.close();
		}
		catch (IOException ex){
			System.out.println("IO Exception.");
			System.exit(0);
		}

		while(numLinesDrawn < numLines || readLine < lineNum) { //either lines read or drawn exceed max
				
			try{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					height = getLineLength(fileName, readLine) * 6;	
					
					ceilingY = allowedY - height;
					
					g2d.setColor(new Color(Integer.parseInt( "4286F4", 16 ) ) );
					g2d.fillRect(ceilingX, ceilingY, width, height); //test bar
			
					ceilingX = ceilingX + width * 2; //making space for a new bar
				
					numLinesDrawn++;
					readLine++;
				
					bufferedReader.close();
				}
				catch(IOException ex) {
				System.out.println("IOException");
				System.exit(0);
				}	
			}
			
			try {
			    File outputfile = new File("output.png");
			    ImageIO.write(bim, "png", outputfile);
			} catch (IOException e) {
				System.out.println("Something went wrong, apparently");
				System.exit(0);
			}
		}
	
	public static int getLineLength(String fileN, int readNum){
        String lineOutput; //use this one
        int lineLength = 0;
		
		try {
            FileReader fileReader =  new FileReader(fileN);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            lineOutput = Files.readAllLines(Paths.get(fileN)).get(readNum);
            lineLength = lineOutput.length();

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "File " + fileN + " not found.");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileN);
        }
		
		return lineLength;
}
	
	public static int getNumLines(String fileN) {
		String line;
		int numLn = 0;
		
		try{			
			FileReader fileReader = new FileReader(fileN);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				numLn++;
			}
			bufferedReader.close();
		}
		catch(IOException ex) {
			System.out.println("IOException");
			System.exit(0);
		}

		return numLn;
	}
}
