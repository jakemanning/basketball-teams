import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
* PeoplAce
* CS 2334, Section 010
* March 27, 2015
* <P>
* Constructs the Plate Caree Map to be viewed by the User. It displays the pinpoint location of the birth cities of each
* person and connects them the home town of their team.
* <P>
* Extends JPanel
* </P>
* @version 1.0
*/
public class ImagePanel extends JPanel
{
	
	
	/* Convention:
	 * JFrame jFrame = new JFrame("Distribution of Locations of People");
			System.out.println(stack.peek().peopleSelected.get(0).getCityOfBirth().getLatitude());
			ImagePanel imagePanel = new ImagePanel(stack.peek().peopleSelected,null);
			jFrame.add(imagePanel);
			jFrame.setSize(591,430);
		    jFrame.setVisible(true);
		    jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	 */
	/**Serial ID required by the program.*/
	private static final long serialVersionUID = 1L;
	
	/**Variable to hold the plate caree map of the United States.*/
	private BufferedImage image;
    
	/**Construction of elements needed to calculate the latitude and longitude in terms of pixels.*/
    private static final double X_SLOPE = (41-209)/(-124.738770 + 106.442456);
    
    private static final double X_INTERCEPT = 209.0 + (X_SLOPE * 106.442456);
    
    private static final double Y_SLOPE = (87-288)/(48.385442 - 31.777576);
    
    private static final double Y_INTERCEPT = 288.0 - (Y_SLOPE)*(31.777576);
   
    /**Team passed to identify the the locations that will be displayed on the map.*/
    private Season teamPassed;
    
    /**ArrayList of Persons to hold the ArrayList of Persons that will be passed in.*/
    private List<Person> personList;

    /**
     * Constructs the image of the map along with the displayed locations of each Person's home town and distance from Team.
     * <P>
     * @param listToPass List of Persons that are on the Team.
     * @param teamPassed Earlier verification that this roster is actually part of a Team.
     */
    public ImagePanel(List<Person> listToPass,Season teamPassed) 
    {
    	//Transfers the passed ArrayList of Persons and specified Team.
    	this.personList = listToPass;
    	this.teamPassed = teamPassed;
    	
    	//Reads the Map to put into the JPanel.
    	try 
    	{                
    		image = ImageIO.read(new File("usa.jpg"));
    	} 
    	catch (IOException ex) 
    	{
    		// handle exception...
    	}
    }

    @Override
    /**
     * Draws the line from each Person's town to the Team town.
     */
    protected void paintComponent(Graphics graphicsObject) 
    {
    	//Constructs the window for the Map.
        super.paintComponent(graphicsObject);
        Graphics2D g2 = (Graphics2D) graphicsObject;
        
        //Sets an acceptable ratio of window quality and contruction time.
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters  
        g2.setFont(new Font("default", Font.PLAIN, 10));


        //For each Person in the List, plot the location of his or her home town
        //and draw a line from his or her town to the location of the Team.
        for(Person p:personList)
        {

        	if((p.getCityOfBirth().getLongitude() != null) || (p.getCityOfBirth().getLatitude() != null) )
        	{

        		try
        		{
        			int x = (int)(Math.round(Double.parseDouble(p.getCityOfBirth().getLongitude()) * X_SLOPE + X_INTERCEPT));

        			int y = (int)(Math.round(Double.parseDouble(p.getCityOfBirth().getLatitude()) * Y_SLOPE + Y_INTERCEPT));





        			g2.drawString(p.getCityOfBirth().toString(), x - 20, y - 5);

        			graphicsObject.fillRect(x,y, 3, 3);
        			if(teamPassed != null)
        			{
        				if(teamPassed.getTeamCity().getLatitude() != null || teamPassed.getTeamCity().getLongitude() != null)
        				{
        					int teamX = (int)(Math.round(Double.parseDouble(teamPassed.getTeamCity().getLongitude()) * X_SLOPE + X_INTERCEPT));

        					int teamY = (int)(Math.round(Double.parseDouble(teamPassed.getTeamCity().getLatitude()) * Y_SLOPE + Y_INTERCEPT));

        					g2.drawString(teamPassed.getTeamCity().toString(), teamX - 20, teamY - 5);

        					g2.fillRect(teamX,teamY, 3, 3);
        					g2.drawLine(x, y, teamX, teamY);
        				}
        			}

        		}
        		catch(NumberFormatException nf)
        		{
        			//        			System.out.println(p.getCityOfBirth() + "|" + p.getCityOfBirth().getLongitude());
        		}
        	}

        }

    }//End of Paint Component Method.
    

    
    

}