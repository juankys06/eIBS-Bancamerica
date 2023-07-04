<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Reports Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="beanListRep" class="datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_m_opt);
	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   /*showPopupHelp();*/
   }

</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } else {
  /*out.println("<SCRIPT> initMenu(); </SCRIPT>");*/
  String title = "EOD Reports";
  String rDisabled = "";
  String sMultiple = "";
  int screen = Integer.parseInt(request.getParameter("SCREEN"));
  switch (screen) {
         case 8:
             title = "Agregar Reporte de Fin de Dia";
             sMultiple = "size=\"15\" multiple";
             rDisabled = "";
             break;
	  case 6:
              title = "Cambiar Reporte de Fin de Dia";
              rDisabled = "";
		break;
	  case 4:
		title = "Borrar Reporte Fin de Dia";
              rDisabled = "disabled";
              break;
   }
    
%>

<h3 align="center"> <%=title %> </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEODPDF" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%=request.getParameter("SCREEN") %>">
  <INPUT TYPE=HIDDEN NAME="REPNAMEANT" VALUE="<%=request.getParameter("REPNAME") %>">
 <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Usuario :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="usercode" size="10" maxlength="10" value="<%= request.getParameter("usercode")%>" readonly>
              </div>
            </td>
		  </tr>
		  <tr id="trdark">
            <td nowrap width="16%" > 
              <div align="right"><b>Reporte :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
		<SELECT  <%=sMultiple %> <%=rDisabled %> name="REPNAME" >
		 <% try {
                      beanListRep.initRow();
                      String sel = "";
		        String colData  = "";
			 String reportAnt = request.getParameter("REPNAME");
                      if (reportAnt == null) reportAnt = "";
			 boolean firstTime = true;
                      while (beanListRep.getNextRow()){
                         colData = beanListRep.getRecord();
                         if (colData.substring(0,10).trim().equals(reportAnt.trim()) && screen != 8) sel = "selected";
                            else sel = "";
			     if (firstTime && screen == 8) {sel = "selected";firstTime = false;}
       		     if (beanListRep.getFlag().equals(""))
		              out.println("<OPTION value=\"" + colData.substring(0,10) + "\" " + sel + " >" + colData + "</OPTION>");
                      }
                  } 
		    catch (Exception e){
                         out.println("Exception : " + e);
                  } }%>
              </SELECT>
             </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Submit">
  </p>
</form>
</body>
</html>
