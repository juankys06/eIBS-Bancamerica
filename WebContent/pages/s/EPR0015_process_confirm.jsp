<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<!--meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/pages/background.jsp'"-->
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="payments" class="datapro.eibs.beans.EPR0015DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 
 }
 setTimeout("document.forms[0].submit();", 7000);
 
</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Confirmación Archivo Procesamiento de Pagos </h3>
<hr size="4">
<form method="post">
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center">La operación ha sido satisfactoriamente procesada, 
                por favor espere ... </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br><table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">No. Acuerdo :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRCAGR" size="12" maxlength="9" value="<%= payments.getE01PRCAGR()%>">
            </td>
            <td nowrap align="right">Referencia :</td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRCREF" size="30" maxlength="30" value="<%= payments.getE01PRCREF()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Cliente : </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRCACC" size="15" maxlength="12" value="<%= payments.getE01PRCACC()%>">
            </td>
            <td nowrap > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap> 
              <input type="hidden" name="E01PRCSTS" value="<%= payments.getE01PRCSTS()%>">
              <input type="radio" name="CE01PRCSTS" value="A" 
			  <%if(payments.getE01PRCSTS().equals("A")) out.print("checked");%> disabled>
              Activo 
              <input type="radio" name="CE01PRCSTS" value="C" 
			  <%if(payments.getE01PRCSTS().equals("C")) out.print("checked");%> disabled>
              Inactivo </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01DESNA1" size="30" maxlength="30" value="<%= payments.getE01DESNA1()%>" >
            </td>
            <td nowrap align="right">Ultima Actualización :</td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRCTD1" size="3" maxlength="2" value="<%= payments.getE01PRCTD1()%>" >
              <input type="text" readonly  name="E01PRCTD2" size="3" maxlength="2" value="<%= payments.getE01PRCTD2()%>" >
              <input type="text" readonly  name="E01PRCTD3" size="3" maxlength="2" value="<%= payments.getE01PRCTD3()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Ultima Vez Procesado:</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRPLTM" size="10" maxlength="8" value="<%= payments.getE01PRPLTM()%>" >
            </td>
            <td nowrap > 
              <div align="right">Ultimo Procesamiento :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01PRPLD1" size="3" maxlength="2" value="<%= payments.getE01PRPLD1()%>" readonly>
              <input type="text" name="E01PRPLD2" size="3" maxlength="2" value="<%= payments.getE01PRPLD2()%>" readonly>
              <input type="text" name="E01PRPLD3" size="3" maxlength="2" value="<%= payments.getE01PRPLD3()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Ultimo Monto Total :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRPAMT" size="17" maxlength="15" value="<%= payments.getE01PRPAMT()%>" >
            </td>
            <td nowrap > 
              <div align="right">No. de Pagos :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRPRCD" size="6" maxlength="4" value="<%= payments.getE01PRPRCD()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Usuario :</div>
            </td>
            <td nowrap colspan="3" >
              <div align="left">
                <input type="text" readonly  name="E01PRPUSR" size="14" maxlength="12" value="<%= payments.getE01PRPUSR()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>
