<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Cargos x Servicios</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id= "rates" class= "datapro.eibs.beans.EDL011002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
 %> 
 
 
<h3 align="center">Tasas Lideres Mantenimiento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_tables_maint,EDL0110L" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0110L">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="PAG_SCR" VALUE="MAINT">
  
<h4>INFORMACI&oacute;N  NUEVA</h4>
<TABLE  class="tableinfo">
  <TR> 
     <TD nowrap> 
  
         <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">N&uacute;mero de Tabla :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02PRRTBL" size="3" maxlength="2" value="<%= rates.getE02PRRTBL()%>" >
             
              </div>
            </td>
            <td nowrap>
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap>                                                                         
              <input type="text" name="E02PRRNME" size="36" maxlength="35" value="<%= rates.getE02PRRNME()%>" >              
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Rango Inicial :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02PRRTFR" size="3" maxlength="2" value="<%= rates.getE02PRRTFR()%>" >
             
              </div>
            </td>
            <td nowrap>
              <div align="right">Rango Final :</div>
            </td>
            <td nowrap>                                                                         
              <input type="text" name="E02PRRTTO" size="3" maxlength="2" value="<%= rates.getE02PRRTTO()%>" >              
            </td>
          </tr>
          
          
          <tr id="trdark"> 
          
          
            
            <td nowrap width="22%"> 
              <div align="right">Tipo  Selecci&oacute;n:</div>
            </td>
            <td nowrap width="18%"> 
              <select name="E02PRRTSL">
                <option value="1" <% if (rates.getE02PRRTSL().equals("1")) out.print("selected"); %>>Usar la mas Alta</option>
                <option value="2" <% if (rates.getE02PRRTSL().equals("2")) out.print("selected"); %>>Usar la mas Baja</option>
              </select>
            </td>
            
            
            
            
            <td nowrap width="29%"> 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="31%"> 
              <div align="left">         
                <input type="text" name="E02PRREFM" size="3" maxlength="2" value="<%= rates.getE02PRREFM()%>" >
                <input type="text" name="E02PRREFD" size="3" maxlength="2" value="<%= rates.getE02PRREFD()%>" >
                <input type="text" name="E02PRREFY" size="3" maxlength="2" value="<%= rates.getE02PRREFY()%>" >
              </div>  
            </td>
          </tr>       
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Primaria :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02PRRPRT" size="10" maxlength="10" value="<%= rates.getE02PRRPRT()%>" >
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Tasa Secundaria : </div>
            </td>
            <td nowrap width="31%" > 
                 <input type="text" name="E02PRRSRT" size="10" maxlength="10" value="<%= rates.getE02PRRSRT()%>" >  
            </td>
          </tr>           
        </table>
        
     </TD>   
  </TR>    
</TABLE>   
  
  
  
  <h4>INFORMACI&oacute;N  ANTERIOR</h4>
  
<TABLE  class="tableinfo">
  <TR> 
     <TD nowrap> 
   
  
         <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">N&uacute;mero de Tabla :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                 <%= rates.getE02PRRTBL()%> 
              </div>
            </td>
            <td nowrap>
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap>                                                                         
               <%= rates.getE02PRRNME()%>            
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Rango Inicial :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <%= rates.getE02PRRTFR()%> 
              </div>
            </td>
            <td nowrap>
              <div align="right">Rango Final :</div>
            </td>
            <td nowrap>                                                                         
               <%= rates.getE02PRRTTO()%>             
            </td>
          </tr>
          
          
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tipo  Selecci&oacute;n:</div>
            </td>
            
<%           
    if  (  rates.getE02PRRTSL().equals("1")   )
    {       
%>             
            
            <td nowrap width="18%"> 
               Usar la mas Baja
            </td>
            
<% 
    }
    else   
    {
%>

            <td nowrap width="18%"> 
               Usar la mas Alta
            </td>
 
<% 
    }  
%>             
            
            <td nowrap width="29%"> 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="31%"> 
              <div align="left">         
                 <%= rates.getE02PRREFM()%> &nbsp; &nbsp;
                 <%= rates.getE02PRREFD()%> &nbsp; &nbsp;
                 <%= rates.getE02PRREFY()%> 
              </div>  
            </td>
          </tr>       
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Primaria :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                 <%= rates.getE02PRRPRT()%> 
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Tasa Secundaria : </div>
            </td>
            <td nowrap width="31%" > 
                 <%= rates.getE02PRRSRT()%>  
            </td>
          </tr>           
        </table>
      </TD>   
  </TR> 
</TABLE> 




 
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>
