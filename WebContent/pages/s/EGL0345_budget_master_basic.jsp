<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>>Budget master File Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="budget" class="datapro.eibs.beans.EGL034501Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


</SCRIPT>
</head>

<body bgcolor="#FFFFFF">

<H3 align="center">Mantenimiento de Presupuesto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="budget_master_enter, EGL0345"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEGL0345">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  </p>
  <h4>Datos Basicos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMBNK" size="4" maxlength="3" value="<%= budget.getE01BUMBNK().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Nro.Presupuesto :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMNUM" size="15" maxlength="13" readonly value="<%= budget.getE01BUMNUM().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Oficina :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMBRN" size="5" maxlength="4" value="<%= budget.getE01BUMBRN().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Centro de Costo:</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMCCN" size="10" maxlength="9" readonly value="<%= budget.getE01BUMCCN().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMCCY" size="4" maxlength="3" value="<%= budget.getE01BUMCCY().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b></b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                
              </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Descripcion :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMNME" size="40" maxlength="35" value="<%= budget.getE01BUMNME().trim()%>" >
               </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Presupuesto por Mes</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Enero :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMJAU" size="15" maxlength="14" value="<%= budget.getE01BUMJAU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Febrero :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMFEU" size="15" maxlength="14" value="<%= budget.getE01BUMFEU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Marzo :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMMAU" size="15" maxlength="14" value="<%= budget.getE01BUMMAU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Abril :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMAPU" size="15" maxlength="14" value="<%= budget.getE01BUMAPU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Mayo :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMMYU" size="15" maxlength="14" value="<%= budget.getE01BUMMYU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Junio :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMJUU" size="15" maxlength="14" value="<%= budget.getE01BUMJUU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Julio :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMJLU" size="15" maxlength="14" value="<%= budget.getE01BUMJLU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Agosto :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMAUU" size="15" maxlength="14" value="<%= budget.getE01BUMAUU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Septiembre :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMSEU" size="15" maxlength="14" value="<%= budget.getE01BUMSEU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Octubre :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMOCU" size="15" maxlength="14" value="<%= budget.getE01BUMOCU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Noviembre :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMNOU" size="15" maxlength="14" value="<%= budget.getE01BUMNOU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Diciembre :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01BUMDEU" size="15" maxlength="14" value="<%= budget.getE01BUMDEU().trim()%>" onkeypress="enterInteger()">
               </div>
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </p>  
 
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
  <%
 }
%> 
</form>
</body>
</html>
