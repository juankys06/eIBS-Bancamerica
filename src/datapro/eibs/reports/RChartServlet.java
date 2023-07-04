//  RMail
//  Copyright (C)
//
//  RReport@Confluencia.net
//  All rights reserved
//
// Adquisition , use and distribution of this code is subject to restriction:
//  - You may modify the source code in order to adapt it to your needs.
//  - Redistribution of this ( or a modifed version) source code is prohibited. You may only redistribute compiled versions.
//  - You may not remove this notice from the source code
//  - This notice disclaim all warranties of all material
//

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import MSBChart.*;
import MSBChart.encoder.*;
import java.awt.Graphics2D.*;
import java.awt.*;

		public class RChartServlet  extends HttpServlet
	{
	private boolean debug=false;

		/**
		 * Handle the HTTP POST method by sending an e-mail
		 *
		 *
		 */


	    public void init() throws ServletException  {

	    }


	   // MODIFY THIS FUNCTION TO CREATE THE CHART USING THE request PARAMETERS
	   private Chart getChart (HttpServletRequest request) {

		 ChartApplet chartApplet=new ChartApplet();
		 chartApplet.isStandalone=true;

		 chartApplet.paintDirect=true;
		 String dataFile=null;

		 // load parameters
		 if (request!=null) {

			if (request.getParameter("DEBUG")!=null)
			   if (request.getParameter("DEBUG").toUpperCase().compareTo("ON")==0) debug =true;

			java.util.Enumeration ps=request.getParameterNames();
			while (ps.hasMoreElements()) {
			  String name=(String) ps.nextElement();
			  chartApplet.setParameter(name,request.getParameter(name));

			  if (debug) System.out.println("PARAM: "+name+"="+request.getParameter(name));

			  if (name.compareTo("DATAFILE")==0) dataFile=request.getParameter(name);




			}
		 }


		 java.io.File f=new java.io.File("a.txt");
		  if (debug) System.out.println(f.getAbsolutePath());

		 // create Chart
		 if (dataFile!=null) {
			 if (debug) System.out.println("loading file "+dataFile);
			chartApplet.loadFromFile(dataFile,"N");
		 }

		 chartApplet.rebuild("N","N");

		 if (debug) System.out.println("Chart Built");

		 if (chartApplet.gChart.tmpImage==null) {
			   if (debug)       System.out.println("Creating TMP Image");
			Chart.tmpImage=new java.awt.image.BufferedImage(200,200,java.awt.image.BufferedImage.TYPE_INT_RGB);
		 }

		 return chartApplet.gChart;


	   }   

	   // Handle a request
	   // 1. create chart
	   // 2. draw chart in a Buffered Image
	   // 3. encode image as GIF or JPEG and send it to the browser
		public void doGet (HttpServletRequest request,
					   HttpServletResponse response)
		throws ServletException, IOException
		{
			   PrintWriter		out;
			   ServletOutputStream outb;

			   String encode="jpeg";

			   if (request!=null) {
				 if (request.getParameter("FORMAT")!=null)  encode=request.getParameter("FORMAT").toLowerCase();

				 if (encode.compareTo("gif")!=0) encode="jpeg";
			   }

			   response.setContentType("image/"+encode);

		//out=response.getWriter();
		outb=response.getOutputStream();

				// avoid caching in browser
		response.setHeader ("Pragma", "no-cache");
		response.setHeader ("Cache-Control", "no-cache");
		response.setDateHeader ("Expires",0);

			   try { // Create buffer

			   int w=500;
			   int h=500;


			   if (request!=null) {
				 if (request.getParameter("WIDTH")!=null) w=new Integer(request.getParameter("WIDTH")).intValue();
				 if (request.getParameter("HEIGHT")!=null) h=new Integer(request.getParameter("HEIGHT")).intValue();
			   }

		java.awt.image.BufferedImage ChartImage=new java.awt.image.BufferedImage(w,h,java.awt.image.BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics2D ChartGraphics=ChartImage.createGraphics();

				// get Chart
				Chart c=getChart(request);

				 if (debug) System.out.println("Size: "+w+" "+h);
				c.setSize(w,h);

				c.paint(ChartGraphics);


				if (encode.compareToIgnoreCase("gif")==0) {
				 // encode buffered image to a gif
				  GifEncoder encoder = new GifEncoder(ChartImage ,outb);
				  encoder.encode();
				}
				else
				{
				  // create JPEG image
				  com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(outb );
				  encoder.encode( ChartImage );
				}

				// use this to create a gif image
				//GifEncoder encoder = new GifEncoder(ChartImage,outb);
				//encoder.encode();


		} catch (Exception e) {
				  System.out.println(e.getMessage());
				  e.printStackTrace();}

			   //outb.close();

		}


		public void doPost (HttpServletRequest request,
					   HttpServletResponse response)
		throws ServletException
		{
							  try {
				  doGet (request,response);
								} catch (Exception e) { e.printStackTrace();}

	}

    }