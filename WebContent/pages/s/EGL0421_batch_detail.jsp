<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Estado de Cuentas de Cuentas Contables
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "bthList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "dataBTH" class= "datapro.eibs.beans.DataNavTotals"  scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function PrintPreview() {

  <% 
  int iniPos = bthList.getFirstRec() - 1;
  out.println("var pos = " + iniPos + ";");
  %>
	var pg = '<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEGL0421?SCREEN=3&Pos=' + pos;
	CenterWindow(pg,720,500,2);

}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM>
<%
	if ( bthList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
      String ref="";
      String date1="";
	  String date2="";
	  String date3="";
	  String batch="";
      String param="";
      try {
		  ref= request.getParameter("Ref").trim();
		  date1= request.getParameter("Date1").trim();
		  date2= request.getParameter("Date2").trim();
		  date3= request.getParameter("Date3").trim();
		  batch= request.getParameter("Batch").trim();
		}
      catch(Exception e){
      }
%>

  <h3 align="center">Detalle de Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="batch_detail.jsp,EGL0421"></h3> 
  <hr size="4">
  <br>
  <input type="hidden" name="ACR" size="12" maxlength="12" value="<%= ref %>" readonly>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="25%" > 
              <div align="right"><b>No. Lote : </b></div>
            </td>
            <td nowrap align="left" width="25%" > 
              <input type="text" name="BTH" size="6" maxlength="5" value="<%= batch%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha Proceso: </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
   			  <input type="text" name="DT1" size="3" maxlength="2" value="<%= date1%>" readonly>
              <input type="text" name="DT2" size="3" maxlength="2" value="<%= date2%>" readonly>
              <input type="text" name="DT3" size="3" maxlength="2" value="<%= date3%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <TABLE class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER nowrap>Banco</TH>
      <TH ALIGN=CENTER nowrap>Suc.</TH>
      <TH ALIGN=CENTER nowrap>MDA</TH>
      <TH ALIGN=CENTER nowrap>Contabilidad </TH>
      <TH ALIGN=CENTER nowrap>Referencia</TH>
      <TH ALIGN=CENTER nowrap>Centro<BR>
        Costos</TH>
	  <TH ALIGN=CENTER nowrap>CT</TH>
	  <TH ALIGN=CENTER nowrap>Narrativa</TH>
      <TH ALIGN=CENTER nowrap> 
        <div align="right">D&eacute;bito</div>
      </TH>
      <TH ALIGN=CENTER nowrap> 
        <div align="center">Cr&eacute;dito</div>
      </TH>
      <TH ALIGN=CENTER nowrap> 
        <div align="center">Fecha<br>Valor</div>
      </TH>
   </TR>
    <%
                bthList.initRow();
                while (bthList.getNextRow()) {
                    if (bthList.getFlag().equals("")) {
                    		out.println(bthList.getRecord());
                    }
                }
              %> 
  </TABLE>
    
  <BR>
  <%      
        if (!bthList.getShowNext() ) {
  %>
  <TABLE class="tableinfo" WIDTH="98%" ALIGN=CENTER>
  	<TR id="trdark">
   		<TD WIDTH="30%" ALIGN=LEFT><b>Total Transacciones : </b><%= dataBTH.getTotalTrans()%></TD>
		<TD WIDTH="35%" ALIGN=LEFT><b>Total Débitos : </b><%= Util.fcolorCCY(dataBTH.getTotalDebit())%></TD>
		<TD WIDTH="35%" ALIGN=LEFT><b>Total Créditos : </b><%= Util.fcolorCCY(dataBTH.getTotalCredit())%></TD>
  	</TR>
  </TABLE>
  <BR>
  <%} %>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( bthList.getShowPrev() ) {
      			int pos = bthList.getFirstRec() - 51;
				param="?SCREEN=1&Pos=" + pos +"&BTH="+batch+"&DT1="+date1+"&DT2="+date2+"&DT3="+date3+"&ACR="+ref+"&FlagMov=0";
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEGL0421" + param +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
 %> 
   </TD>
 	<TD WIDTH="50%" ALIGN=RIGHT>
 <%      
        if ( bthList.getShowNext() ) {
      			int pos = bthList.getLastRec();
				param="?SCREEN=1&Pos=" + pos +"&BTH="+batch+"&DT1="+date1+"&DT2="+date2+"&DT3="+date3+"&ACR="+ref+"&FlagMov=1";
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSEGL0421" + param +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
%>
  	</TD>
 	</TR>
 	</TABLE>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="PrintPreview()" value="Imprimir">
  </div>
</p>
  <%
  }
%> 
</FORM>

</BODY>
</HTML>
