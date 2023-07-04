<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Consulta de Chequeras
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.EDD000001Message"  scope="session" />

<jsp:useBean id="rtBasic_01" class="datapro.eibs.beans.ECH030501Message"  scope="session" />

<jsp:useBean id= "checkBooks" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	 builtNewMenu(rt_i_opt);
	
</SCRIPT>

</head>

<body>

<SCRIPT> initMenu(); </SCRIPT>


<h3 align="center">Consulta de Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="check_books_inq.jsp, ECH0565"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0565" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<%
	if ( checkBooks.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getHeader3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<!--  <table  class="tableinfo">
    <tr> 
      <td nowrap> -->
      
<!--        <table cellspacing="0" cellpadding="2" width="100%" border="0">-->
        <BR>
<table  class="tableinfo">
          <tr id="trdark">
            <td>
            <b>Nombre Personalizado :</b></td>
            <td nowrap>
            <INPUT type="text" name="E01ACMCK1" size="35" maxlength="35" value="<%= rtBasic.getE01ACMCK1().trim()%>" readonly></td>
            <td nowrap>
              <b>Oficina de Entrega : </b>
            </td>
            <td nowrap>
              <input type="text" name="E01ACMCN1" size="3" maxlength="3" value="<%= rtBasic.getE01ACMCN1().trim()%>" readonly> </td>
          </tr>
          
          <tr id="trclear">
            <td nowrap align="right">
              <SELECT name="E01ACMRE1">
                 <OPTION value="Y" <%if (!rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">Y</OPTION>
                 <% if (currUser.getE01INT().equals("07")) { %>
                 <OPTION value="O" <%if (rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">O</OPTION>
                 <%} else { %>               
                 <OPTION value="O" <%if (rtBasic.getE01ACMRE1().equals("O")) out.print("selected"); %>="">Y/O</OPTION>
                 <%}%>
              </SELECT>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ACMCK2" size="35" maxlength="35" value="<%= rtBasic.getE01ACMCK2().trim()%>" readonly>
            </td>
            <td nowrap align="left"><b>Stock Actual  :</b>
            </td>
            <td nowrap align="left">
              <INPUT type="text" name="E01ACMNCB" size="3" maxlength="2" value="<%= rtBasic_01.getE01CHPASK().trim()%>" onkeypress="enterInteger()" readonly>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap> 
              <b>Tipo de Chequera :</b>
            </td>
            <td nowrap align=left>
              <INPUT type="text" name="E01ACMTCB" size="3" maxlength="2" value="<%= rtBasic.getE01ACMTCB().trim()%>" onkeypress="enterInteger()" readonly>
            </td>
            
            <td nowrap> 
              <b>Stock Mínimo de Chequeras :</b>
            </td>
            <td nowrap><INPUT type="text" name="E01ACMMSK" size="3" maxlength="2" value="<%= rtBasic.getE01ACMMSK().trim()%>" onkeypress="enterInteger()" readonly> </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <b>Cobro de Cargos por Chequeras :</b>
            </td>
            <td nowrap ><INPUT type="radio" name="E01ACMCBC" value="Y" <% if(!rtBasic.getE01ACMCBC().equals("N")) out.print("checked");%>="" readonly disabled>
              S&iacute; 
              <INPUT type="radio" name="E01ACMCBC" value="N" <% if(rtBasic.getE01ACMCBC().equals("N")) out.print("checked");%>="" readonly disabled>
               No
            </td>
            <td nowrap> 
              <b>Restricción de Entregas de Chequeras :</b>
            </td>
            <td nowrap ><INPUT type="radio" name="E01ACMCON" value="Y" <% if(rtBasic.getE01ACMCON().equals("Y")) out.print("checked");%>="" readonly disabled>
              S&iacute; 
              <INPUT type="radio" name="E01ACMCON" value="N" <% if(!rtBasic.getE01ACMCON().equals("Y")) out.print("checked");%>="" readonly disabled>
              No
            </td>
          </tr>
        </table>
<BR>
<TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER colspan="2">Fecha</TH>
      <TH ALIGN=CENTER rowspan="2">No de<br>
        Chequera</TH>
      <TH ALIGN=CENTER rowspan="2">Status<br>
        Chequera</TH>
      <TH ALIGN=CENTER rowspan="2">No de<br>
        Cheques </TH>
      <TH ALIGN=CENTER rowspan="2">Cheque<br>
        Inicial</TH>
      <TH ALIGN=CENTER rowspan="2">Cheque<br>
        Final</TH>
      <TH ALIGN=CENTER colspan="5">Estado de Chequera</TH>
    </TR>
    <TR id="trdark"> 
      <TH ALIGN=CENTER>Solicitud</TH>
      <TH ALIGN=CENTER>Entrega</TH>
      <TH ALIGN=CENTER>So</TH>
      <TH ALIGN=CENTER>Re</TH>
      <TH ALIGN=CENTER>En</TH>
      <TH ALIGN=CENTER>Rea</TH>
      <TH ALIGN=CENTER>Enc</TH>
    </TR>
    <%
                checkBooks.initRow();
                while (checkBooks.getNextRow()) {
                    // if (checkBooks.getFlag().equals("")) {
                    		out.println(checkBooks.getRecord());
                    // }
                }
    %> 
  </TABLE>

<%
  }
%>

</FORM>

</BODY>
</HTML>
