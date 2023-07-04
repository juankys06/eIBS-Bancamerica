package datapro.eibs.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datapro.generic.tool.Util;
import com.datapro.services.ServiceLocator;

import datapro.eibs.beans.ELEERRMessage;
import datapro.eibs.beans.ESD000005Message;
import datapro.eibs.beans.ESS0030DSMessage;
import datapro.eibs.master.JSEIBSProp;
import datapro.eibs.master.SuperServlet;
import datapro.eibs.sockets.MessageContext;
import datapro.eibs.sockets.MessageReader;
import datapro.eibs.sockets.MessageRecord;

public class JSImagesExchange extends SuperServlet {

	protected String LangPath = "S";

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ESS0030DSMessage msgUser = null;
		HttpSession session = null;

		session = (HttpSession) req.getSession(false);

		if (session == null) {

			try {
				res.setContentType("text/html");
				printLogInAgain(res.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Exception ocurred. Exception = " + e);
			}
		} else {

			try {
				String screen = req.getParameter("SCREEN");
				int action = 100;
				if (screen != null) {
					action = Integer.parseInt(screen);
				}
	
				msgUser = (datapro.eibs.beans.ESS0030DSMessage) session
						.getAttribute("currUser");
	
				LangPath = super.rootPath + msgUser.getE01LAN() + "/";
	
				switch (action) {
					case 100: // PAGINA INICIAL DEL PROCESO DE EXPORTACION.
						exportPage(req, res);
						break;
					case 101: // PAGINA INICIAL DEL PROCESO DE IMPORTACION.
						importPage(req, res);
						break;
		
					case 200: //EXPORTACION DE FIRMAS DE LA BD
						exportImages(req, res);
						break;
					case 201: //IMPORTACION DE CHEQUES A LA BD
						importImages(req, res, msgUser);
						break;
					default:
						break;
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
			}
		}

	}

	private void exportPage(HttpServletRequest req,
			HttpServletResponse res) {

		GregorianCalendar cal = new GregorianCalendar();
		int year = cal.get(GregorianCalendar.YEAR);
		int month = cal.get(GregorianCalendar.MONTH) + 1 ;
		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
		
		req.setAttribute("TODAY_DAY", new Integer(day));
		req.setAttribute("TODAY_MON", new Integer(month));
		req.setAttribute("TODAY_YEA", new Integer(year));

		try {
			String page = "ImagesExchangeEnter.jsp";
			flexLog("About to call Page: " + LangPath + page);
			callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	private void exportImages(HttpServletRequest req, HttpServletResponse res) {

		Connection cnx = null;
		Connection cnx2 = null;
		Vector fileData = new Vector();
		Vector fileDataMov = new Vector();
		Timestamp ts = null;
		String imgFileName = null;
		String txtFileName = null;
		String accNum = null;
		String bankCode = "0163" ;
		String branchCode = "" ;
		String specialInst = "" ;
		int controlDigit1 = 0 ;
		int controlDigit2 = 0 ;
		String filesPath = JSEIBSProp.getImageExportPath() ;  //"C:\\TEMP\\img\\" ;
		int i = 0, j = 0 ;
		String rDay = null, rMonth = null, rYear = null ;
		String seq = "" ;
		String page = "ImagesExchangeRes.jsp";
		FileOutputStream fout = null ;
		FileOutputStream foutMov = null ;

		if ( filesPath.equals("") ) {
			
			ELEERRMessage error = new ELEERRMessage();
			error.setERRNUM("1") ;
			error.setERNU01("0001");
			error.setERDS01("Ruta de Exportación de Archivos no configurada.") ;
			
			req.getSession(false).setAttribute("error" , error) ;
			
			try {
				page = "/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=100";
				flexLog("About to call Page: " + LangPath + page);
				res.sendRedirect(super.srctx + page ) ;
				return ;
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
		}
		
		try {
			cnx = ServiceLocator.getInstance().getDBConn("image-server");

			rDay = req.getParameter("DAY");
			rDay = (rDay.length() == 1 ) ? "0" + rDay : rDay ; 
			rMonth = req.getParameter("MONTH");
			rMonth = (rMonth.length() == 1 ) ? "0" + rMonth : rMonth ; 
			rYear = req.getParameter("YEAR");

			String sql = " SELECT B.IMGBIN, B.IMGUID, A.TBLNUM "
					+ " FROM SCNDOCTBL A, SCNDOCIMG B "
					+ " WHERE TBLTYP = 'A' AND TBLDTY = 'SC' "
					+ " AND A.TBLUID = B.IMGUID " + " AND A.TBLLMD = ? "
					+ " AND A.TBLLMM = ? " + " AND A.TBLLMY = ? ";

			PreparedStatement ps = cnx.prepareStatement(sql);

			ps.setObject(1, rDay);
			ps.setObject(2, rMonth);
			ps.setObject(3, rYear);

			ResultSet rs = ps.executeQuery();

			flexLog("Query Executed for Date(d/m/y):" + rDay + "/" + rMonth
					+ "/" + rYear);

			
			//AS400 CONNECT
			
			Socket s = null;
			MessageContext mc = null;

			ESS0030DSMessage msgUser = null;
			HttpSession session = null;

			session = (HttpSession) req.getSession(false);

			if (session == null) {
				try {
					res.setContentType("text/html");
					printLogInAgain(res.getWriter());
				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Exception ocurred. Exception = " + e);
				}
			} else {

				try {

					msgUser =
						(datapro.eibs.beans.ESS0030DSMessage) session.getAttribute(
							"currUser");

					// Here we should get the path from the user profile
					LangPath = super.rootPath + msgUser.getE01LAN() + "/";

					try {
						flexLog("Opennig Socket Connection");
						s = new Socket(super.hostIP, getInitSocket(req) + 4);
						s.setSoTimeout(super.sckTimeOut);
						mc =
							new MessageContext(
								new DataInputStream(
									new BufferedInputStream(s.getInputStream())),
								new DataOutputStream(
									new BufferedOutputStream(s.getOutputStream())),
								"datapro.eibs.beans");


//	*************************************

						while (rs.next()) {
							seq = Integer.toString(i) ;
							seq = Util.addLeftChar( '0' , 4 , seq ) ;
							
							accNum = rs.getString("TBLNUM").trim();
							if (accNum.length() > 10) {
								accNum = accNum.substring(0 , 10);
							} else if(accNum.length() < 10){
								accNum = Util.addLeftChar( '0' , 10 , accNum ) ;
							}				
							
							//GET ACCOUNT SPECIAL INSTRUCTIONS
							specialInst = getSpecialInst( accNum, mc, msgUser, 
									req.getSession() ) ;
							
							branchCode = "0" + accNum.substring(0,3) ;
							controlDigit1 = getCheckDigit( bankCode + branchCode ) ;
							controlDigit2 = getCheckDigit( branchCode + accNum ) ;
							
							rYear = (rYear.length() == 1)? "0" + rYear : rYear ;
							imgFileName = rYear.substring(2) + rMonth + rDay + seq ;
							//Pone espacios a la Izq del nombre de la imágen
							if (imgFileName.length() > 10) {
								imgFileName = imgFileName.substring(imgFileName.length() - 10);
							} else {
								imgFileName = Util.addLeftChar('0' , 10, imgFileName ) ;
							}

							byte buf[] = rs.getBytes("IMGBIN");

							OutputStream osImage = new FileOutputStream(filesPath
									+ imgFileName + ".jpg");

							osImage.write(buf);
							osImage.close();

							addRecord(fileData, "A", branchCode, 
									"" + controlDigit1 + controlDigit2 , accNum, "F",
									imgFileName, "A", null );

							addRecord(fileDataMov, "A", branchCode, 
									"" + controlDigit1 + controlDigit2 , accNum, 
									null, null, null, specialInst );

							i++ ;

						}
						
//	*************************************

					} catch (Exception e) {
						e.printStackTrace();
						int sck = getInitSocket(req) + 4;
						flexLog("Socket not Open(Port " + sck + "). Error: " + e);
						res.sendRedirect(super.srctx + LangPath + super.sckNotOpenPage);
						//return;
					} finally {
						s.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
					flexLog("Error: " + e);
					res.sendRedirect(super.srctx + LangPath + super.sckNotRespondPage);
				}

			}

			//Write the signatures files.
			if ( fileData != null && fileData.size() != 0 ) {
				
				txtFileName = filesPath + rYear + rMonth + rDay + ".txt" ;
				fout = new FileOutputStream( txtFileName );
				PrintStream fileWriter = new PrintStream(fout);

				for (Iterator iter = fileData.iterator(); iter.hasNext();) {
					String record = (String) iter.next();

					// Print a line of text
					fileWriter.println(record);
				}
				

				// Write the Movilization features file

				txtFileName = filesPath + "MOV" + rYear + rMonth + rDay + ".txt" ;
				foutMov = new FileOutputStream( txtFileName );
				PrintStream fileWriterMov = new PrintStream(foutMov);

				for (Iterator iter = fileDataMov.iterator(); iter.hasNext();) {
					String record = (String) iter.next();

					fileWriterMov.println(record);
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			page = "/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=100";
		} finally {
			
			try {
				// Close our output stream
				fout.close();
				foutMov.close();
				cnx.close();
				
			} catch (Exception e) {
				flexLog("Error:" + e.getMessage() ) ;
			}

		}
		
		req.setAttribute( "nImgs" , new Integer(i) ) ;
		req.setAttribute( "fileName" , txtFileName ) ;
		req.setAttribute( "pDate" , rYear + "/" + rMonth + "/" + rDay ) ;
		
		try {
			
			flexLog("About to call Page: " + LangPath + page);
			callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}

	private String getSpecialInst(String accNum, MessageContext mc, 
			ESS0030DSMessage user, HttpSession ses ) 
		throws Exception {

		ELEERRMessage msgError = null ;
		String specInst = "" ;
		
		ESD000005Message msg = (ESD000005Message)mc.getMessageRecord("ESD000005");
	 	msg.setH05USR(user.getH01USR());
	 	msg.setH05PGM("EDD0000");
	 	msg.setH05TIM(""); //getTimeStamp()
	 	msg.setH05SCR("01");
	 	msg.setH05OPE( "0004" );
	 	msg.setE05ACC( accNum );
	 	msg.setE05TYP("1");
		msg.send();	
	 	msg.destroy();

	 	MessageRecord newmessage = mc.receiveMessage();
		  
		  	if (newmessage.getFormatName().equals("ELEERR")) {
				msgError = (ELEERRMessage)newmessage;
				showERROR( msgError );
				ses.setAttribute("error", msgError);
				if ( ! msgError.getERRNUM().equals("0") ) {
					throw new Exception( "Error Received from AS400" ) ;
				}
		  	}
		  	
		  	newmessage = mc.receiveMessage();
		  	if (newmessage.getFormatName().equals("ESD000005")) {
		  		flexLog( "MSG Received:" + newmessage.toString() );
				msg = new datapro.eibs.beans.ESD000005Message();
				msg = (ESD000005Message)newmessage ;
				
				specInst = msg.getE15DSC() +
				   msg.getE25DSC() +
				   msg.getE35DSC() +
				   msg.getE45DSC() +
				   msg.getE55DSC() +
				   msg.getE65DSC() +
				   msg.getE75DSC() +
				   msg.getE85DSC() +
				   msg.getE95DSC() +
				   msg.getEA5DSC() +
				   msg.getEB5DSC() +
				   msg.getEC5DSC() +
				   msg.getED5DSC() +
				   msg.getEE5DSC() +
				   msg.getEF5DSC() +
				   msg.getEG5DSC() +
				   msg.getEH5DSC() +
				   msg.getEI5DSC() +
				   msg.getEJ5DSC() ;
				
		  	}else {
		  		flexLog( "Msg received Not expected:" + newmessage.getFormatName() );
		  	}

	 	return specInst ;

	}

	private void addRecord(Vector fileData, String updType, String branchCode,
			String dc, String accNum, String cutType, String imgName,
			String cutId, String specialIns ) {

		//Pone Ceros a la Izquierda del número de cuenta.
		if ( accNum != null ) {
			if (accNum.length() > 10) {
				accNum = accNum.substring(0 , 10);
			} else {
				accNum = Util.addLeftChar( '0' , 10 , accNum ) ;
			}
		}

		//Pone espacios a la Izq del nombre de la imágen
		if ( imgName != null ) {

			if (imgName.length() > 10) {
				imgName = imgName.substring(imgName.length() - 10);
			} else {
				imgName = Util.addLeftChar( '0' , 10, imgName) ;
			}
		}
		
		if ( specialIns != null ) {
			specialIns = specialIns.trim();
			if ( specialIns.length() < 100 ) {
				specialIns = Util.addRightChar( ' ' , 100, specialIns) ;
			}else if ( specialIns.length() > 100 ) {
				specialIns = specialIns.substring( 0 , 100 ) ;
			}
		}
		
		/*
		String record = updType 
			+ branchCode 
			+ dc 
			+ accNum 
			+ cutType 
			+ imgName
			+ cutId 
			+ specialIns 
			;   */

		String record = "" ; 

		if ( updType != null) 		{ record = record + updType ; } 
		if ( branchCode != null )	{ record = record + branchCode ; }
		if ( dc != null )			{ record = record + dc ; }
		if ( accNum != null )		{ record = record + accNum ; }
		if ( cutType != null )		{ record = record + cutType ; }
		if ( imgName != null )		{ record = record + imgName ; }
		if ( cutId != null )		{ record = record + cutId ; }
		if ( specialIns != null )	{ record = record + specialIns ; }
		
		fileData.add(record);

	}
	
	private Vector getData(File fImage) {
		Vector myData = new Vector(); 
		
		try {
			FileInputStream isDetails = null;
			isDetails = new FileInputStream(fImage);
			
			InputStreamReader isr = new InputStreamReader(isDetails);
			BufferedReader br = new java.io.BufferedReader(isr);
		  
		    //Continue to read lines while there are still some left
			String line = (String) br.readLine();
			while (line != null) {
		         myData.add(getCheckRecord(line));
		         line = (String) br.readLine();
			}
		} catch (Exception e) { 
			flexLog("File input Error " + e);
		}
		return myData;
	}
	
	private Vector getData(String fullFileName) {
		return getData(new File(fullFileName));
	}

	private HashMap getCheckRecord(String line) {
		if (line.length() != 150) throw new RuntimeException("Bad formated line on input file");
		
		HashMap myRecord = new HashMap(); 
		
		myRecord.put("ID", line.substring(0, 15));
		myRecord.put("CHECKNUM", line.substring(15, 23));
		myRecord.put("ABANUMBER", line.substring(23, 27));
		myRecord.put("CHECKBRANCH", line.substring(27, 31));
		myRecord.put("TRANSACTIONKEY", line.substring(31, 33));
		myRecord.put("ACCOUNT", line.substring(33, 43));
		myRecord.put("CHECKTYPE", line.substring(43, 45));
		myRecord.put("CHECKAMOUNT", line.substring(45, 62));
		myRecord.put("SCANDATE", line.substring(62, 70));
		myRecord.put("IMAGEFRONT", line.substring(70, 110).trim());
		myRecord.put("IMAGEBACK", line.substring(110, 150).trim());
		
		return myRecord;
	}
	
	private void importPage(HttpServletRequest req,
			HttpServletResponse res) {

		GregorianCalendar cal = new GregorianCalendar();
		int year = cal.get(GregorianCalendar.YEAR);
		int month = cal.get(GregorianCalendar.MONTH);
		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);

		req.setAttribute("TODAY_DAY", new Integer(day));
		req.setAttribute("TODAY_MON", new Integer(month));
		req.setAttribute("TODAY_YEA", new Integer(year));

		try {
			String page = "ImagesExchangeImportEnter.jsp";
			flexLog("About to call Page: " + LangPath + page);
			callPage(LangPath + page, req, res);
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}

	}
	
	private void importImages(
			HttpServletRequest req, 
			HttpServletResponse res, 
			ESS0030DSMessage user) throws ServletException, IOException {

		Connection cnx = null;
		Vector data = new Vector();
		String zipFileName = null;		
		String filesPath = JSEIBSProp.getImageImportPath();
		String rDay = null, rMonth = null, rYear = null ;
		boolean isNotError = true;
		FileInputStream isFrontImage = null ;
		FileInputStream isBackImage = null ;
		File fFrontImage = null ;
		File fBackImage = null ;
		File fileData = null ;
		String unzipedDirectory = "" ;

		try {
			if ( filesPath.equals("") ) {				
				ELEERRMessage error = new ELEERRMessage();
				error.setERRNUM("1") ;
				error.setERNU01("0001");
				error.setERDS01("Ruta de Importación de Archivos no configurada.") ;
				
				sendErrorResponse(req, res, error, 
						"/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=101") ;
				
				/*
				req.getSession(false).setAttribute("error" , error) ;
				
				try {
					String page = "/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=101";
					flexLog("About to call Page: " + LangPath + page);
					res.sendRedirect(super.srctx + page ) ;
					return ;
				} catch (Exception e) {
					flexLog("Exception calling page " + e);
				}  */
				
			}
			
			
			try {
				
				String seq = "" ;
				
				rDay = req.getParameter("DAY");
				rDay = (rDay.length() == 1 ) ? "0" + rDay : rDay ; 
				rMonth = req.getParameter("MONTH");
				rMonth = (rMonth.length() == 1 ) ? "0" + rMonth : rMonth ; 
				rYear = req.getParameter("YEAR");
				
				
				zipFileName = rYear + rMonth + rDay + ".zip";
				unzipedDirectory = "D" + rYear + rMonth + rDay + ".img";
				
				fileData = new File(unzipImages(filesPath, zipFileName));
				data = getData(fileData);

				cnx = ServiceLocator.getInstance().getDBConn("image-server");
				cnx.setAutoCommit(false);
				String sql =
					"INSERT INTO ATVCHECK "
						+ "(SCANDATE, CODEBANK, CODEBRANCH, USERIDPOD, SEQUENCEITEM, " +
							"CHECKNUM, ABANUMBER, CHECKBRANCH, TRANSACTIONKEY, " +
							"ACCOUNT, CHECKTYPE, CHECKAMOUNT, IMAGEFRONT, IMAGEBACK, " +
							"SEQUENCHK, DOCTYPECODE) "
						+ "VALUES "
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement ps = cnx.prepareStatement(sql);

				for (int i = 0; i < data.size(); i++) {
					HashMap myRecord = (HashMap) data.get(i);

					seq = Integer.toString(i) ;
					seq = Util.addLeftChar( '0' , 4 , seq ) ;
					
					isFrontImage = null;
					fFrontImage = new File(
											filesPath + unzipedDirectory + File.separator + 
											(String) myRecord.get("IMAGEFRONT"));
					isFrontImage = new FileInputStream(fFrontImage);

					isBackImage = null;
					fBackImage = new File(
											filesPath + unzipedDirectory + File.separator + 
											(String) myRecord.get("IMAGEBACK"));
					isBackImage = new FileInputStream(fBackImage);

					ps.clearParameters();						
					
					ps.setString(1, (String) myRecord.get("SCANDATE"));
					ps.setString(2, Util.addLeftChar( '0' , 4 , user.getE01UBK()));
					ps.setString(3, Util.addLeftChar( '0' , 4 , user.getE01UBR()));
					ps.setString(4, user.getH01USR());
					ps.setString(5, seq);
					ps.setString(6, (String) myRecord.get("CHECKNUM"));
					ps.setString(7, (String) myRecord.get("ABANUMBER"));
					ps.setString(8, (String) myRecord.get("CHECKBRANCH"));
					ps.setString(9, (String) myRecord.get("TRANSACTIONKEY"));
					ps.setString(10, (String) myRecord.get("ACCOUNT"));
					ps.setString(11, (String) myRecord.get("CHECKTYPE"));
					ps.setString(12, (String) myRecord.get("CHECKAMOUNT"));	
					ps.setBinaryStream(13, isFrontImage, (int) fFrontImage.length());
					ps.setBinaryStream(14, isBackImage, (int) fBackImage.length());
					ps.setString(15, "000001" );	
					ps.setString(16, "0001" );	

					ps.executeUpdate();
					
					isFrontImage.close();
					isBackImage.close();
					fFrontImage.delete();
					fBackImage.delete();
					
				}
				
				if (isNotError) {
					cnx.commit();
				} else {
					throw new SQLException();
				}				
			
			
			} catch (SQLException e) {
				
				try {

					isFrontImage.close();
					isBackImage.close();
					fFrontImage.delete();
					fBackImage.delete();
					
					isNotError = false ;
					
					e.printStackTrace();
					flexLog("Error: " + e);
					cnx.rollback();

				} catch (Exception e1) {
					flexLog( e1.getMessage() ) ;
				}
				
				ELEERRMessage error = new ELEERRMessage();
				error.setERRNUM("2") ;
				error.setERNU01("0002");
				error.setERDS01("Archivo ya importado. " + filesPath + zipFileName) ;
				
				sendErrorResponse(req, res, error, 
						"/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=101") ;

				
				//throw new SQLException("JDBC Communication Error");  
			} catch (IOException e) {
				e.printStackTrace();
				flexLog("Error: " + e);
				
				try {
					cnx.rollback();
				} catch (Exception e1) {
					flexLog( e1.getMessage() ) ;
				}
				
				
				
				
				ELEERRMessage error = new ELEERRMessage();
				error.setERRNUM("2") ;
				error.setERNU01("0002");
				error.setERDS01("Archivo no existe. " + filesPath + zipFileName) ;
				
				sendErrorResponse(req, res, error, 
						"/servlet/datapro.eibs.tools.JSImagesExchange?SCREEN=101") ;
				
				
				//throw new IOException("IO Error");
				
			//} catch (FileNotFoundException e1){
				
			}finally {
				
				try {
					cnx.close();
					fileData.delete();
					new File(filesPath + unzipedDirectory).delete();
				} catch (Exception e) {
					flexLog( e.getMessage() ) ;
				}

			}
			
			flexLog("Insert Executed for Date(d/m/y):" + rDay + "/" + rMonth + "/" + rYear);
			
			req.setAttribute( "nImgs" , new Integer(data.size()) ) ;
			req.setAttribute( "fileName" , zipFileName ) ;
			req.setAttribute( "pDate" , rYear + "/" + rMonth + "/" + rDay ) ;
			
			try {
				String page = "ImagesExchangeImportRes.jsp";
				flexLog("About to call Page: " + LangPath + page);
				callPage(LangPath + page, req, res);
			} catch (Exception e) {
				flexLog("Exception calling page " + e);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			flexLog("Error: " + e);
			throw new RuntimeException("IO or Communication Error");
		} 
			
	}
	
	private String unzipImagesCached(String filePath, String fileName) throws IOException {

		String imagesDescFileName = "";
		Enumeration entries;
	    ZipFile zipFile;

		zipFile = new ZipFile(filePath + fileName);

		entries = zipFile.entries();

		while(entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry)entries.nextElement();
			File file = new File(filePath + entry.getName());
			
			if (file.getName().toLowerCase().endsWith(".txt")) imagesDescFileName = file.getAbsolutePath();
			
			if(entry.isDirectory()) {
				flexLog("Making directory: " + file.getName());
				file.mkdirs();
				continue;
	        } else {
                String parentName;
                if( (parentName = file.getParent()) != null ) {
                    new File(parentName).mkdirs();
                }
                
    			flexLog("Extracting file : " + entry.getName());
    	        fromZipToOutputStream(
    	        		zipFile.getInputStream(entry),
    					new BufferedOutputStream(new FileOutputStream(file)));
			}
		}
		zipFile.close();
	      
		return imagesDescFileName;

	}
	
	public String unzipImages (String filePath, String fileName) throws IOException {
		String imagesDescFileName = "";

		ZipInputStream zis = new ZipInputStream(
								new BufferedInputStream(
										new FileInputStream(filePath + fileName)));
		ZipEntry entry;
		while((entry = zis.getNextEntry()) != null) {
			File file = new File(filePath + entry.getName());
			
			if (file.getName().toLowerCase().endsWith(".txt")) imagesDescFileName = file.getAbsolutePath();

			if(entry.isDirectory()) {
				flexLog("Making directory: " + file.getName());
				file.mkdirs();
	        } else {
	        	String parentName;
                if( (parentName = file.getParent()) != null ) {
                    new File(parentName).mkdirs();
                }
                
    			flexLog("Extracting file : " + entry);
    			fromZipToOutputStream(
    					zis, 
    					new BufferedOutputStream(new FileOutputStream(file)));
			}

		}
		zis.close();

		return imagesDescFileName;
	}


	public static final void fromZipToOutputStream(
	  		InputStream in, 
			OutputStream out) throws IOException {
	
		byte[] buffer = new byte[1024];
	    int len;

	    while((len = in.read(buffer)) >= 0)
	    	out.write(buffer, 0, len);

	    out.close();
	}

	
	private int getCheckDigit(String code){
		//char[] chars = code.toCharArray();
		int mult = 2;
		int sum = 0;
    	
		// Se multiplica cada digito verificador por su peso
		for (int i=code.length() - 1; i >= 0 ; i--){
			int num = ( Integer.parseInt(code.substring(i,i+1))) * mult;
			sum = sum + num;
			mult++;
			if (mult > 7) mult = 2;
		}
    	
		// Se calcula el residuo de la suma obtenida dividida entre 11
		int res = sum % 11;
    	
		// Se resta de 11 el residuo
    	
		int mod = 11 - res;
        
		// Si el modulo es menor o igual a nueve este es el dígito de control
		// sino se asume que es cero
        
		if (mod == 10) mod = 0;
		else if(mod == 11) mod = 1;
        
		return mod;
	}
	
	
	protected void showERROR(ELEERRMessage m) {
		if (logType != NONE) {

			flexLog("ERROR received.");

			flexLog("ERROR number:" + m.getERRNUM());
			flexLog("ERR001 = " + m.getERNU01() + " desc: " + m.getERDS01());
			flexLog("ERR002 = " + m.getERNU02() + " desc: " + m.getERDS02());
			flexLog("ERR003 = " + m.getERNU03() + " desc: " + m.getERDS03());
			flexLog("ERR004 = " + m.getERNU04() + " desc: " + m.getERDS04());
			flexLog("ERR005 = " + m.getERNU05() + " desc: " + m.getERDS05());
			flexLog("ERR006 = " + m.getERNU06() + " desc: " + m.getERDS06());
			flexLog("ERR007 = " + m.getERNU07() + " desc: " + m.getERDS07());
			flexLog("ERR008 = " + m.getERNU08() + " desc: " + m.getERDS08());
			flexLog("ERR009 = " + m.getERNU09() + " desc: " + m.getERDS09());
			flexLog("ERR010 = " + m.getERNU10() + " desc: " + m.getERDS10());

		}
	}
	
	
	private void sendErrorResponse( HttpServletRequest req, 
			HttpServletResponse res, ELEERRMessage error, String url ){

		req.getSession(false).setAttribute("error" , error) ;
		
		try {
			flexLog("About to call Page: " + LangPath + url );
			res.sendRedirect(super.srctx + url ) ;
			return ;
		} catch (Exception e) {
			flexLog("Exception calling page " + e);
		}	
		
	}
	
}
