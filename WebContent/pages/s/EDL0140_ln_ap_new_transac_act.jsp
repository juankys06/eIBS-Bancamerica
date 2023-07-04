
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Transacciones en Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnGenTran" class="datapro.eibs.beans.EDL015230Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(ln_a_opt);

</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

  out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Transacciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ap_new_transac,EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150" >
  <input type=HIDDEN name="SCREEN" value="26">
  <input type=HIDDEN name="E30DEABNK"  value="<%= lnGenTran.getE30DEABNK().trim()%>">
  <input type=HIDDEN name="E30DEACCY"  value="<%= lnGenTran.getE30DEACCY().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" readonly  name="E30DEACUN" size="9" maxlength="9" value="<%= lnGenTran.getE30DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" readonly  name="E30CUSNA1" size="45" maxlength="45" value="<%= lnGenTran.getE30CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly  name="E30DEAACC" size="12" maxlength="12" value="<%= lnGenTran.getE30DEAACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly  name="E30DEACCY2" size="3" maxlength="3" value="<%= lnGenTran.getE30DEACCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly  name="E30DEAPRO" size="4" maxlength="4" value="<%= lnGenTran.getE30DEAPRO().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%
	String flag = lnGenTran.getH30FLGWK3();
%> <%@ include file="ESD0840_reevaluation_inquiry.jsp" %> 
  <h4>Distribuci&oacute;n del Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">VALOR DEL PRESTAMO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNPRI" value="<%= lnGenTran.getE30TRNPRI().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">INTERESES PREPAGADOS :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNINT" value="<%= lnGenTran.getE30TRNINT().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <%
          String name="";
          int j=1;
          for (int i= 1;i<26;i++){
           if (i< 10) name="0"+i; else name="" +i;
		   if(!lnGenTran.getField("E30PDSC"+name).getString().trim().equals("")){
		     j=i-j;
		 %> 
          <tr id="<% if (j==0) out.print("trdark"); else { j=i+1; out.print("trclear");}%>"> 
            <td nowrap colspan="2"> 
              <div align="right"><%= lnGenTran.getField("E30PDSC"+name).getString().trim()%> 
                : </div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30PAMT<%=name%>" value="<%= lnGenTran.getField("E30PAMT"+name).getString().trim()%>">
            </td>
          </tr>
          <%
		    }
		   }
		  %> 
          <tr id="<% if (j==0) out.print("trclear"); else out.print("trdark");%>"> 
            <td nowrap colspan="2"> 
              <div align="right">TOTAL DESEMBOLSO :</div>
            </td>
            <td nowrap colspan="3" width="56%"> 
              <input type="text" readonly  size="15" maxlength="13" name="E30TRNTOT" value="<%= lnGenTran.getE30TRNTOT().trim()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <h4>Cuenta de Desembolso</h4>
  </h4>
  <table id="mainTable" class="tableinfo">
    <tr>
      <td> 
        <table id="headTable" >
          <tr id="trdark"> 
            <td nowrap align="center" > Concepto</td>
            <td nowrap align="center" >Banco </td>
            <td nowrap align="center" >Sucursal</td>
            <td nowrap align="center" >Moneda</td>
            <td nowrap align="center" >Referencia</td>
            <td nowrap align="center" >Monto</td>
          </tr>
        </table>
        <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" > 
          <table id="dataTable" >
            <%
  				   int amount = 9;
 				   name = "";
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center" nowrap> 
                  <input type="text" readonly  name="E30OFFOP<%= name %>" value="<%= lnGenTran.getField("E30OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                  <input type="hidden" name="E30OFFGL<%= name %>" value="<%= lnGenTran.getField("E30OFFGL"+name).getString().trim()%>">
                  <input type="text" readonly  name="E30OFFCO<%= name %>" size="25" maxlength="25" bene value="<%= lnGenTran.getField("E30OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E30DEABNK.value,'','E30OFFGL<%= name %>','E30OFFOP<%= name %>','10')">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFBK<%= name %>" size="2" maxlength="2" value="<%= lnGenTran.getField("E30OFFBK"+name).getString().trim()%>">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFBR<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
                </div>
              </td>
              <td nowrap  > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFCY<%= name %>" size="3" maxlength="3" value="<%= lnGenTran.getField("E30OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E30DEABNK.value,'','','','')">
                </div>
              </td>
              <td nowrap > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFAC<%= name %>" size="12" maxlength="12"  value="<%= lnGenTran.getField("E30OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E30DEABNK.value,'','','','RT')">
                </div>
              </td>
              <td nowrap  > 
                <div align="center"> 
                  <input type="text" readonly  name="E30OFFAM<%= name %>" size="15" maxlength="15"  value="<%= lnGenTran.getField("E30OFFAM"+name).getString().trim()%>" onKeyPress="enterDecimal()">
                </div>
              </td>
            </tr>
            <%
    		}
    		%> 
          </table>
        </div>
        <table id="footTable" >
          <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente Moneda del Préstamo : </b> 
            </td>
            <td nowrap align="center"><b><i><strong> 
              <input type="text" readonly  name="E30OFFEQV" size="15" maxlength="15" bene value="<%= lnGenTran.getE30OFFEQV().trim()%>">
              </strong></i></b> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <script language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize; 
  </script>
</form>


</body>
</html>
