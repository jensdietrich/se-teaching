package nz.ac.vuw.jenz.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * Simple servlet that  dynamically generates an image
 * visualising the current time with bars for hours, minutes and seconds.
 * The image visualizes the current time.
 * @author  Jens Dietrich
 */
// alternative, annotation-based configuration, see README for discussion
// @WebServlet(name = "ImageServlet", urlPatterns = "/Clock")
public class ImageServlet extends HttpServlet {
    public static final int BAR_WIDTH = 600;
    public static final int V_OFFSET = 7;
    public static final int H_OFFSET = 80;
    public static final int BAR_HEIGTH = 20;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // the content type is jpg now !!!
        response.setContentType("image/jpeg");
	
        // since an image contains bytes and no characters, we must output to a stream now!	
        ServletOutputStream out = response.getOutputStream();
        
        
        BufferedImage image = new BufferedImage(BAR_WIDTH+H_OFFSET+5,(BAR_HEIGTH*3)+(V_OFFSET*2)+5, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        // render to g here
        Date now = new Date();
        int hours = now.getHours();
        int minutes = now.getMinutes();
        int seconds = now.getSeconds();
        
        g.setColor(Color.red);
        g.draw3DRect(H_OFFSET,0,BAR_WIDTH,BAR_HEIGTH,true);
        g.fill3DRect(H_OFFSET,0,BAR_WIDTH*seconds/60,BAR_HEIGTH,true);
        g.drawString(""+seconds+ " sec",5,BAR_HEIGTH/2);

        g.setColor(Color.green);
        g.draw3DRect(H_OFFSET,BAR_HEIGTH+V_OFFSET,BAR_WIDTH,BAR_HEIGTH,true);
        g.fill3DRect(H_OFFSET,BAR_HEIGTH+V_OFFSET,BAR_WIDTH*minutes/60,BAR_HEIGTH,true);
        g.drawString(""+minutes+ " min",5,BAR_HEIGTH+V_OFFSET+BAR_HEIGTH/2);
        
        g.setColor(Color.blue);
        g.draw3DRect(H_OFFSET,2*(BAR_HEIGTH+V_OFFSET),BAR_WIDTH,BAR_HEIGTH,true);
        g.fill3DRect(H_OFFSET,2*(BAR_HEIGTH+V_OFFSET),BAR_WIDTH*hours/24,BAR_HEIGTH,true);
        g.drawString(""+hours+ " hours",5,2*(BAR_HEIGTH+V_OFFSET)+BAR_HEIGTH/2);
        
        javax.imageio.ImageIO.write(image, "jpeg", out);
        
        // clean up - important in order to free resources on the server 
        g.dispose();      
        out.close();
    }
    
}
