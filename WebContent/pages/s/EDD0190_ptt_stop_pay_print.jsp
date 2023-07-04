<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "rtStop" class= "datapro.eibs.beans.EDD019001Message"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript"> 
function goPrint()
{ if ((navigator.appName == "Netscape")) { window.print() ; 
} 
else
{ 
var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>'; 
document.body.insertAdjacentHTML('beforeEnd', WebBrowser); WebBrowser1.ExecWB(6, -1); WebBrowser1.outerHTML = "";
window.close();
}
}
</SCRIPT> 

</head>
<body>
<form >
    <%
        int row;
		  try{row = Integer.parseInt(request.getParameter("ROW1"));}catch(Exception e){row = 0;}
		  stop.setCurrentRow(row);
    %>
 
  <table>
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr> 
            <td colspan="3"  valign="center" height="15" > 
              <div align="left"><b>P R O T E S T O</b></div>
            </td>
            <td valign="center" colspan="2" > 
              <div align="center"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td width="86" valign="center" > 
              <div align="center"><%= rtStop.getDHSCHK().trim()%></div>
            </td>
            <td valign="center" colspan="3" > 
              <div align="left"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(rtStop.getDHSAMT().trim())%></div>
            </td>
          </tr>
         <tr> 
            <td colspan="9" valign="center" height="15" > 
              <div align="left"><b>SCOTIABANK NO PAGA EL PRESENTE CHEQUE DE :</b></div>
            </td>
          </tr>
          <tr> 
            <td valign="center" colspan="6" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
            <td valign="center" width="59"> 
              <div align="right"><b>RUT :</b></div>
            </td>
            <td  colspan="2" valign="center"> 
              <div align="left"><%= rtStop.getNRORUT().trim()%>-<%= rtStop.getDIGRUT().trim()%></div>
            </td>
          </tr>
          <tr> 
            <td colspan="9" valign="center" height="15" > 
              <div align="left"><b>CUYO DOMICILIO REGISTRADO EN EL BANCO ES : 
                </b></div>
            </td>
          </tr>
          <tr> 
            <td > </td>
            <td valign="center" colspan="4" > 
              <div align="left"><%= rtStop.getCUSNA2().trim()%></div>
		    </td>
			<td valign="center" colspan="2" > 
              <div align="left"><%= rtStop.getCUSNA3().trim()%></div>
		    </td>
			<td valign="center" colspan="2" > 
              <div align="left"><%= rtStop.getCUSNA4().trim()%></div>
		    </td>
          </tr>
		   
		 <%  if((userPO.getHeader17().equals("1")) || (userPO.getHeader17().equals("2"))) { %> 
          <tr> 
            <td colspan="2" valign="center" height="15" > 
              <div align="left"><b>FIRMADO POR : </b></div>
            </td>
            <td valign="center" colspan="4" > 
              <div align="left"><%= rtStop.getNOGIR1().trim()%></div>
            </td>
            <td valign="center"> 
              <div align="right"><b>RUT :</b></div>
            </td>
            <td valign="center" colspan="2"> 
              <div align="left"><%= rtStop.getRUTGI1().trim()%></div>
            </td>
          </tr>
          <tr> 
            <td colspan="2" valign="center"  > 
              <div align="left"></div>
            </td>
            <td valign="center" colspan="4" > 
              <div align="left"><%= rtStop.getNOGIR2().trim()%></div>
            </td>
            <td valign="center"> 
              <div align="right"></div>
            </td>
            <td valign="center" colspan="2"> 
              <div align="left"><%= rtStop.getRUTGI2().trim()%></div>
            </td>
          </tr>
		  
		 <% } %>
          <tr> 
            <td colspan="2" height="15" valign="center"> 
              <div align="left"><b>MOTIVO : </b></div>
            </td>
            <td valign="center" colspan="5" > 
              <div align="left"><%= rtStop.getCDVDSC().trim()%></div>
            </td>
			<td valign="center" colspan="2" > 
              <div align="left"><%= rtStop.getCIUCAN().trim()%></div>
            </td>
          </tr>
          <tr> 
            <td height="15" valign="center" colspan="2" > 
              <div align="left"><b>FECHA :</b></div>
            </td>
            <td valign="center" colspan="2" > 
			  <div align="left"><%= Util.formatDate(rtStop.getDHSADD(),rtStop.getDHSADM(),rtStop.getDHSADY()) %></div>
            </td>
            <td valign="center" colspan="2" > 
              <div align="rigth"><b>HORA:</b> <% if ( rtStop.getDHSORG().trim().equals("5")) {out.print(" 09:01");} else {out.print(Util.formatTime(userPO.getHeader19()));} %> </div>
            </td>
            <td valign="left" colspan="3" > 
              <div align="left"><b>IMP.DL 3475:</b> $ <%= Util.fcolorCCY(rtStop.getIMPUES().trim()) %></div>
            </td>
          </tr>
          <tr> 
            <td colspan="5" height="20"> </td>
            <td></td>
            <td></td>
            <td width="85"></td>
            <td width="67"></td>
          </tr>
          <tr> 
            <td valign="right" colspan="8" height="30"> 
              <div align="right">________________</div>
              <div align="right"><p>pp. SCOTIABANK</div>
            </td>
            <td  valign="center"> 
              <div align="left"></div>
            </td>
          </tr>
          <tr> 
            <td height="5"></td>
            <td width="58"></td>
            <td width="40"></td>
            <td width="46"></td>
            <td width="31"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table>
	</tr>
  </table>
  
<SCRIPT language="javascript"> 
goPrint();
</SCRIPT> 

</form>
</body>
</html>
