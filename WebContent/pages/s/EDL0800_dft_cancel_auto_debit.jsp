<html>
<head>
<title>Suspensión de Débito Automático</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnAutoDebit" class= "datapro.eibs.beans.EDL015210Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(dft_m_opt);
  

</SCRIPT>


<script language="JavaScript">
  function valDates(){
    
    var curDate=addLeftChar('0',2,'<%=currUser.getE01RDY()%>')+ 
                addLeftChar('0',2,'<%=currUser.getE01RDM()%>') +
                addLeftChar('0',2,'<%=currUser.getE01RDD()%>') ; 
                
    var inDate=addLeftChar('0',2,document.getElementById('E10DLASDY').value)+
               addLeftChar('0',2,document.getElementById('E10DLASDD').value)+
               addLeftChar('0',2,document.getElementById('E10DLASDM').value);
               
               
    var endDate=addLeftChar('0',2,document.getElementById('E10DLAMAY').value)+
                addLeftChar('0',2,document.getElementById('E10DLAMAD').value)+
                addLeftChar('0',2,document.getElementById('E10DLAMAM').value);
                           
     
    if (inDate < curDate){
      alert ('Fecha de Inicio debe ser mayor o igual a la fecha del sistema');
      
    }
    else if (endDate < curDate){
      alert('Fecha de Vencimiento debe ser mayor o igual a la fecha del sistema');
      
    }
    else if(endDate < inDate){
      alert ('Fecha de Vencimiento no puede ser menor a fecha de inicio');
      
    }
    else
      document.forms[0].submit();
      
   
  }
</script>

</head>

<body>



<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Suspensión de Débito Automático<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_cancel_auto_debit.jsp, EDL0800"></h3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="42">
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
                <input type="text" name="E10DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E10CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E10DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E10DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E10DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Fecha Inicio :</div>
            </td>
            <td width="61%">
              <input type="text" id="E10DLASDM" name="E10DLASDM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDM().trim()%>" onkeypress="enterInteger()">
              <input type="text" id="E10DLASDD" name="E10DLASDD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDD().trim()%>" onkeypress="enterInteger()">
              <input type="text" id="E10DLASDY" name="E10DLASDY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLASDY().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td width="61%">
              <input type="text"  id="E10DLAMAM" name="E10DLAMAM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAM().trim()%>" onkeypress="enterInteger()">
              <input type="text"  id="E10DLAMAD" name="E10DLAMAD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAD().trim()%>" onkeypress="enterInteger()">
              <input type="text"  id="E10DLAMAY" name="E10DLAMAY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLAMAY().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Motivo :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E10DLAMO1" size="30" maxlength="30" value="<%= lnAutoDebit.getE10DLAMO1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"></div>
            </td>
            <td width="61%"> 
              <input type="text" name="E10DLAMO2" size="30" maxlength="30" value="<%= lnAutoDebit.getE10DLAMO2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Canal de Restricción :</div>
            </td>
            <td width="61%"> 
              <select name="E10DLACNL">
               <option value=" " <% if (!(lnAutoDebit.getE10DLACNL().equals("C") || lnAutoDebit.getE10DLACNL().equals("D")) ) out.print("SELECTED"); %>></option>
               <option value="C" <% if (lnAutoDebit.getE10DLACNL().equals("C")) out.print("SELECTED"); %>>Cobro cuotas on-line</option>
               <option value="D" <% if (lnAutoDebit.getE10DLACNL().equals("D")) out.print("SELECTED"); %>>Deducciones con cargo al banco</option>
              </select>
            </td>
          </tr>

          <tr id="trdark">             
            <td width="39%"> 
              <div align="right">Status :</div>
            </td>
            <td width="61%"> 
              <select name="E10DLASTS">
               <option value=" " <% if (!(lnAutoDebit.getE10DLASTS().equals("A") || lnAutoDebit.getE10DLASTS().equals("I") || lnAutoDebit.getE10DLASTS().equals("M")) ) out.print("SELECTED"); %>></option>
               <option value="A" <% if (lnAutoDebit.getE10DLASTS().equals("A")) out.print("SELECTED"); %>>Activo</option>
               <option value="I" <% if (lnAutoDebit.getE10DLASTS().equals("I")) out.print("SELECTED"); %>>Inactivo</option>
               <option value="M" <% if (lnAutoDebit.getE10DLASTS().equals("M")) out.print("SELECTED"); %>>Vencido</option>
              </select>
            </td>
          </tr>
          <!-- <tr id="trclear"> 
          <td width="39%"> 
              <div align="right">Tipo de Restricción :</div>
            </td>
            <td width="61%"> 
              <select name="E10DLATYP">
               <option value=" " <% if (!(lnAutoDebit.getE10DLATYP().equals("1") || lnAutoDebit.getE10DLATYP().equals("2") || lnAutoDebit.getE10DLATYP().equals("3") || lnAutoDebit.getE10DLATYP().equals("4") || lnAutoDebit.getE10DLATYP().equals("5") || lnAutoDebit.getE10DLATYP().equals("6")) ) out.print("SELECTED"); %>></option>
               <option value="1" <% if (lnAutoDebit.getE10DLATYP().equals("1")) out.print("SELECTED"); %>>Activo</option>
               <option value="2" <% if (lnAutoDebit.getE10DLATYP().equals("2")) out.print("SELECTED"); %>>Inactivo</option>
               <option value="3" <% if (!(lnAutoDebit.getE10DLATYP().equals("1") || lnAutoDebit.getE10DLATYP().equals("2"))) out.print("SELECTED");%>>Vencido</option>
              </select>
            </td>
           </tr> --> 
        </table>
      </td>
    </tr>
  </table>
  <h4>Ultima Modificacion</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="25%"> 
              <div align="right">Usuario :</div>
            </td>
            <td width="25%">
              <input type="text" name="E10DLAUSR" size="12" maxlength="10" value="<%= lnAutoDebit.getE10DLAUSR().trim()%>" readonly>
            </td>
            <td width="25%"> 
              <div align="right">Fecha :</div>
            </td>
            <td width="25%">
              <input type="text" name="E10DLALDM" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDM().trim()%>" readonly>
              <input type="text" name="E10DLALDD" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDD().trim()%>" readonly>
              <input type="text" name="E10DLALDY" size="2" maxlength="2" value="<%= lnAutoDebit.getE10DLALDY().trim()%>" readonly>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="valDates();" >
  </p>
</form>
</body>
</html>
