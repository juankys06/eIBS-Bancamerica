<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "stop" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript"> 
function goPrint()
{ if ((navigator.appName == "Netscape")) { window.print() ; 
} 
else
{ 
var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>'; 
document.body.insertAdjacentHTML('beforeEnd', WebBrowser); WebBrowser1.ExecWB(6, -1); WebBrowser1.outerHTML = "";
location= prefix + language + "EDD0190_ptt_stop_pay.jsp";
}
}
</SCRIPT> 
</head>

<body>

<h3 align="center">Protesto Manual<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_stop_pay.jsp , EDD0190"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0190">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">


  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><b><%= userPO.getHeader1().trim()%></b> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getCurrency().trim()%></b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b><%= userPO.getHeader6().trim()%></b> </div>
            </td>
          </tr>
		  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Ejecutivo :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b><%= userPO.getHeader18().trim()%></b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap colspan="3" > 
                      <div align="left"><%= userPO.getHeader19().trim()%></div>
                    </td>
           </tr>
		  <tr id="trclear"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Contable :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"><b>$ <%= userPO.getHeader15().trim()%></b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Disponible : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b>$ <%= userPO.getHeader16().trim()%></b> </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Sdo. Retención: </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b>$ <%= userPO.getHeader17().trim()%></b> </div>
                    </td>
                  </tr>
        </table>
      </td>
    </tr>
  </table>
  
 <TABLE  id="mainTable" class="tableinfo"  NOWRAP>
 <TR> 
   <TD NOWRAP valign="top" width="100%" >
        <TABLE id="headTable"  NOWRAP width="561">
          <TR id="trdark"> 
            <TH ALIGN=CENTER colspan="4" NOWRAP>Cheques</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP width="123">Monto</TH>
            <TH ALIGN=CENTER rowspan="2" NOWRAP width="107">Estado</TH>
     </TR>
     <TR id="trdark">  
            <TH ALIGN=CENTER NOWRAP width="69">Sucursal Origen</TH>
            <TH ALIGN=CENTER NOWRAP width="45">N&uacute;mero</TH>
            <TH ALIGN=CENTER NOWRAP width="35">Oficial</TH>
            <TH ALIGN=CENTER NOWRAP width="140"> 
              <DIV ALIGN=CENTER NOWRAP>Fecha / Hora</DIV>
            </TH>
     </TR>
    </TABLE>
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  NOWRAP>
    <%
               int row;
		  			try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
               stop.initRow();
                int k=1;
               while (stop.getNextRow()) {
     %> 
    <TR> 
      <TD ALIGN=CENTER NOWRAP width="69"><%= Util.formatCell(stop.getRecord(0)) %></TD>
      <TD ALIGN=CENTER NOWRAP width="45"><%= Util.formatCell(stop.getRecord(1)) %></TD>
      <TD ALIGN=CENTER NOWRAP width="35"><%= Util.formatCell(stop.getRecord(2)) %></TD>
      <TD ALIGN=CENTER NOWRAP width="140"><%= Util.formatDate(stop.getRecord(3),stop.getRecord(4),stop.getRecord(5)) %> - <%= Util.formatTime(stop.getRecord(6)) %></TD>
      <TD ALIGN=RIGHT  NOWRAP width="123"><%= Util.fcolorCCY(stop.getRecord(7)) %></TD>
      <TD ALIGN=CENTER NOWRAP width="107"><%= Util.formatCell(stop.getRecord(8)) %></TD>
    </TR>
	
    <%
                k++;
                }
      %>
  </table>
   </div>
   </TD>
  </TR>	
</TABLE>
     
</FORM>
<script language="javascript">
goPrint();
</script>
</BODY>
</HTML>
