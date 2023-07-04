<html>
<head>
<title>Entrada Transacciones Reconciliacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import = "datapro.eibs.beans.ERC006001Message" %>

<jsp:useBean id= "glList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<script language="JavaScript">
function confirm(param)
{
	fieldName=name;
        page = webapp + param;
	listin = CenterWindow(page,500,250,8);

}

</SCRIPT>
<%

if(request.getParameter("conf")!=null){
	String a = request.getParameter("E01RCTFR1");
	String b = request.getParameter("E01RCTFR2");
	String c = request.getParameter("E01RCTFR3");
	String d = request.getParameter("E01RCTTO1");
	String e = request.getParameter("E01RCTTO2");
	String f = request.getParameter("E01RCTTO3");
	String g = request.getParameter("E01RCTRF1");
	String h = request.getParameter("E01RCTRF2");
	String i = request.getParameter("E01RCTAM1");
	String j = request.getParameter("E01RCTAM2");
	response.sendRedirect(request.getContextPath()+"/servlet/datapro.eibs.products.JSERC0060?SCREEN=400&E01RCTFR1="+a+"&E01RCTFR2="+b+"&E01RCTFR3="+c+"&E01RCTTO1="+d+"&E01RCTTO2="+e+"&E01RCTTO3="+f+"&E01RCTRF1="+g+"&E01RCTRF2="+h+"&E01RCTAM1="+i+"&E01RCTAM2="+j);
	
}

%>
</head>

<BODY>
<%
ERC006001Message msgList =  new ERC006001Message();
if(request.getSession().getAttribute("msgList")!=null){
	msgList=(ERC006001Message)request.getSession().getAttribute("msgList");
}
%>
<h3 align="center">Transacciones Pendientes Reconciliaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="search_list_ent.jsp, ERC0060"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060?SCREEN=400" > 
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap  >Banco</th>
            <th align=CENTER nowrap "> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">Cuenta Contable</div>
            </th>
            <th align=CENTER nowrap  > 
              <div align="center">N&uacute;mero Cuenta</div>
            </th>
          </tr>
          
          <tr> 
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTBNK()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTCCY()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTGLN()) %></td>
            <td NOWRAP align="CENTER"><%= Util.formatCell(msgList.getE01RCTACC()) %></td>
          </tr>
          <input type="hidden" value="<%=msgList.getE01RCTBNK()%>" name="E01RCTBNK">
          <input type="hidden" value="<%=msgList.getE01RCTCCY()%>" name="E01RCTCCY">
          <input type="hidden" value="<%=msgList.getE01RCTGLN()%>" name="E01RCTGLN">
          <input type="hidden" value="<%=msgList.getE01RCTACC()%>" name="E01RCTACC">
        </table>
  </table>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
         
          <tr id="trdark">
	            <td nowrap width="25%"> 
              		<div align="right"><b>Fecha Desde :</b></div>
            	</td>
           		 <td nowrap width="23%" align="left">
             
	              <input type="text" name="E01RCTFR1" size="2" maxlength="2" value="" onkeypress="enterInteger()">
	              <input type="text" name="E01RCTFR2" size="2" maxlength="2" value="" onkeypress="enterInteger()">
	              <input type="text" name="E01RCTFR3" size="2" maxlength="2" value="" onkeypress="enterInteger()">
	              <a href="javascript:DatePicker(document.forms[0].E01RCTFR1,document.forms[0].E01RCTFR2,document.forms[0].E01RCTFR3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
	            </td>
	            <td nowrap width="25%"> 
              		<div align="right"><b>Fecha Hasta :</b></div>
            	</td>
           		 <td nowrap width="23%" align="left">
             
	              <input type="text" name="E01RCTTO1" size="2" maxlength="2" value="" onkeypress="enterInteger()">
	              <input type="text" name="E01RCTTO2" size="2" maxlength="2" value="" onkeypress="enterInteger()">
	              <input type="text" name="E01RCTTO3" size="2" maxlength="2" value="" onkeypress="enterInteger()"> 
	              <a href="javascript:DatePicker(document.forms[0].E01RCTTO1,document.forms[0].E01RCTTO2,document.forms[0].E01RCTTO3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a> 
	            </td>
             
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Referencia Desde : </b></div>
            </td>
            <td nowrap align="left" width="20%" height="17">         
          		<input type="text" name="E01RCTRF1" size="6" maxlength="6" >           
      		</td>
      		<td nowrap width="23%">
              <div align="right"><b>Referencia Hasta : </b></div>
            </td>
            <td nowrap align="left" width="20%" height="17">         
          		<input type="text" name="E01RCTRF2" size="6" maxlength="6">           
      		</td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Valor Desde : </b></div>
            </td>
            <td nowrap align="left" width="27%" height="17">
         		<input type="text" name="E01RCTAM1" size="15" maxlength="13" >
      		</td>
      		<td nowrap width="23%">
              <div align="right"><b>Valor Hasta : </b></div>
            </td>
            <td nowrap align="left" width="27%" height="17">
         		<input type="text" name="E01RCTAM2" size="15" maxlength="13" >
      		</td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
	    <td align="center"> 
	    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
	  	</td>
  	</tr>
  </table>
<SCRIPT language="JavaScript">
	showChecked("CURRCODE");
</SCRIPT>
<%
if(request.getParameter("showList")!=null){
	if ( glList.getNoResult() ) {%>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <TABLE class="tbenter" width="100%" id=cfTable>
	    <TR>
	        <div align="center"> 
	          <p><b>No hay resultados para su criterio de busqueda</b></p>
	        </div>
		 
		</TR>
	    </TABLE>
		<%} else {
			if ( !error.getERRNUM().equals("0")  ) {
	     		error.setERRNUM("0");
	     		out.println("<SCRIPT Language=\"Javascript\">");
		     	out.println("       showErrors()");
	     		out.println("</SCRIPT>");
	     	}
		%>
			</form>
			<FORM name="form2" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060?SCREEN=500" >
				
				<table  id=cfTable class="tableinfo">
					
				    <tr > 
				      <td NOWRAP valign="top" width="88%">
		<table id="headTable" width="98%">
			<tr id="trdark">
				<th align=CENTER nowrap width="3%">&nbsp;</th>

				<th align=CENTER nowrap width="17%">
				<div align="center">Fecha</div>
				</th>
				<th align=CENTER nowrap width="16%">
				<div align="center">Referencia</div>
				</th>

				<th align=CENTER nowrap width="24%">
				<div align="center">Debitos<BR>
				Cargos</div>
				</th>

				<th align=CENTER nowrap width="24%">
				<div align="center">Creditos<BR>
				Depositos</div>
				</th>

				<th align=CENTER nowrap width="21%">
				<div align="center">Origen</div>
				</th>
			</tr>
			<%
				          glList.initRow();
				          boolean firstTime = true;
				          String chk = "";
				          int count=0;
				          while (glList.getNextRow()) {
				                 
				                datapro.eibs.beans.ERC006001Message msgList1 = (datapro.eibs.beans.ERC006001Message) glList.getRecord();
						 %>
			<tr>

				<td NOWRAP align=CENTER width="3%"><input type="checkbox"
					name="H01FLGWK1<%=count%>" value="<%= glList.getCurrentRow() %>"
					<%=chk%>></td>

				<TD NOWRAP ALIGN="CENTER" width="129"><%= Util.formatDate(msgList1.getE01RCTBD1(),msgList1.getE01RCTBD2(),msgList1.getE01RCTBD3()) %></TD>
				<td NOWRAP align="CENTER" width="123"><%= Util.formatCell(msgList1.getE01RCTCKN()) %></td>
				<td NOWRAP align="CENTER" width="145"><%= Util.formatCell(msgList1.getE01RCTAMD()) %></td>
				<td NOWRAP align="CENTER" width="257"><%= Util.formatCell(msgList1.getE01RCTAMC()) %></td>
				<td NOWRAP align="CENTER" width="229"><%= Util.formatCell(msgList1.getE01DSCPRC()) %></td>

			</tr>
			<input type="hidden" name="E01RCTBD1<%=count%>"
				value="<%=msgList1.getE01RCTBD1()%>">
			<input type="hidden" name="E01RCTBD2<%=count%>"
				value="<%=msgList1.getE01RCTBD2()%>">
			<input type="hidden" name="E01RCTBD3<%=count%>"
				value="<%=msgList1.getE01RCTBD3()%>">
			<input type="hidden" name="E01RCTCKN<%=count%>"
				value="<%=msgList1.getE01RCTCKN()%>">
			<input type="hidden" name="E01RCTAMD<%=count%>"
				value="<%=msgList1.getE01RCTAMD()%>">
			<input type="hidden" name="E01RCTAMC<%=count%>"
				value="<%=msgList1.getE01RCTAMC()%>">
			<input type="hidden" name="E01DSCPRC<%=count%>"
				value="<%=msgList1.getE01DSCPRC()%>">
			<%
				          	count++;
				          }
			%>
			<%
			if(request.getParameter("balance")!=null){
				String deb = request.getParameter("deb");
				String cred = request.getParameter("cred");
				String ok = request.getParameter("ok");
			%>
			</form>
			<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060?SCREEN=700">
			<INPUT TYPE=HIDDEN NAME="SCREEN">
			<table class="tableinfo">
				<tr>
					<td nowrap>
					<table cellspacing=0 cellpadding=2 width="100%" border="0">

						<tr id="trdark">
							<td nowrap width="23%">
							<div align="right"><b>Total Debitos : </b></div>
							</td>
							<td nowrap width="32%"><input type="text" readonly size="20"
								maxlength="16" value="<%=deb%>"> </a></td>
						</tr>
						<tr id="trdark">
							<td nowrap width="23%">
							<div align="right"><b>Total Creditos: </b></div>
							</td>
							<td nowrap width="32%"><input type="text" readonly size="20"
								maxlength="16" value="<%=cred%>"> </a></td>
						</tr>
						<tr id="trdark">
							<td nowrap width="32%" align="center"><input id="EIBSBTN"
								type=submit name="cancel" value="Cancelar"> </a></td>
							<td nowrap width="32%" align="center"><input id="EIBSBTN"
								type=submit name="accept" value="Aceptar"
								<%if(ok.trim().equals("Nook")){%> disabled <%}%>></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<%
			if(request.getAttribute("E01RCTFR1")!=null && request.getAttribute("E01RCTFR2")!=null &&
					request.getAttribute("E01RCTFR3")!=null && request.getAttribute("E01RCTTO1")!=null &&
					request.getAttribute("E01RCTTO2")!=null && request.getAttribute("E01RCTTO3")!=null &&
					request.getAttribute("E01RCTRF1")!=null && request.getAttribute("E01RCTRF2")!=null &&
					request.getAttribute("E01RCTAM1")!=null && request.getAttribute("E01RCTAM2")!=null ){
			%>
				
				<input type="hidden" name="E01RCTFR1" value="<%=request.getAttribute("E01RCTFR1")%>">
				<input type="hidden" name="E01RCTFR2" value="<%=request.getAttribute("E01RCTFR2")%>">
				<input type="hidden" name="E01RCTFR3" value="<%=request.getAttribute("E01RCTFR3")%>">
				<input type="hidden" name="E01RCTTO1" value="<%=request.getAttribute("E01RCTTO1")%>">
				<input type="hidden" name="E01RCTTO2" value="<%=request.getAttribute("E01RCTTO2")%>">
				<input type="hidden" name="E01RCTTO3" value="<%=request.getAttribute("E01RCTTO3")%>">
				<input type="hidden" name="E01RCTRF1" value="<%=request.getAttribute("E01RCTRF1")%>">
				<input type="hidden" name="E01RCTRF2" value="<%=request.getAttribute("E01RCTRF2")%>">
				<input type="hidden" name="E01RCTAM1" value="<%=request.getAttribute("E01RCTAM1")%>">
				<input type="hidden" name="E01RCTAM2" value="<%=request.getAttribute("E01RCTAM2")%>">
			<%
			}
			
			%>
			</form>
			<%
		}else{
		%>
			<tr>
				<input align="center" id="EIBSBTN" type=submit name="Submit" value="Enviar">
			</tr>
		</table>
		<%
			if(request.getAttribute("E01RCTFR1")!=null && request.getAttribute("E01RCTFR2")!=null &&
					request.getAttribute("E01RCTFR3")!=null && request.getAttribute("E01RCTTO1")!=null &&
					request.getAttribute("E01RCTTO2")!=null && request.getAttribute("E01RCTTO3")!=null &&
					request.getAttribute("E01RCTRF1")!=null && request.getAttribute("E01RCTRF2")!=null &&
					request.getAttribute("E01RCTAM1")!=null && request.getAttribute("E01RCTAM2")!=null ){
			
			%>
				
				<input type="hidden" name="E01RCTFR1" value="<%=request.getAttribute("E01RCTFR1")%>">
				<input type="hidden" name="E01RCTFR2" value="<%=request.getAttribute("E01RCTFR2")%>">
				<input type="hidden" name="E01RCTFR3" value="<%=request.getAttribute("E01RCTFR3")%>">
				<input type="hidden" name="E01RCTTO1" value="<%=request.getAttribute("E01RCTTO1")%>">
				<input type="hidden" name="E01RCTTO2" value="<%=request.getAttribute("E01RCTTO2")%>">
				<input type="hidden" name="E01RCTTO3" value="<%=request.getAttribute("E01RCTTO3")%>">
				<input type="hidden" name="E01RCTRF1" value="<%=request.getAttribute("E01RCTRF1")%>">
				<input type="hidden" name="E01RCTRF2" value="<%=request.getAttribute("E01RCTRF2")%>">
				<input type="hidden" name="E01RCTAM1" value="<%=request.getAttribute("E01RCTAM1")%>">
				<input type="hidden" name="E01RCTAM2" value="<%=request.getAttribute("E01RCTAM2")%>">
			<%
			}
			%>
		<input type="hidden" name="total" value="<%=count%>">
<form>
<%
		}
	}
}
%>
</body>
</html>
