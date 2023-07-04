<html>
<head>
<title>Untitled Document</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "com" class= "datapro.eibs.beans.ESD000007Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT Language="Javascript">

     <% 
   if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_personal_opt);
  <%      
   }
   else
   {
   %>
		builtNewMenu(client_corp_opt);
   <%
   }
   %>

</SCRIPT>

<body bgcolor="#FFFFFF">

 

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

<h3 align="center">Comunicaciones 
  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_both_com, ESD0080"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="38">
    

<table class="tableinfo">
  <tr > 
    <td nowrap> 
      <table cellspacing="0" cellpadding="2" class="tbhead" width="100%"   align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>


<h4>Datos Principales</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id=trdark> 
            <td nowrap width="29%"> 
              <div align="right">N&uacute;mero de A.B.A. 
                :</div>
            </td>
            <td nowrap width="24%" > 
              <input type="text" name="E07ABA" size="13" maxlength="12" value="<%= com.getE07ABA().trim()%>">
            </td>
            <td nowrap width="47%" > 
              <input type="text" name="E07ANM" size="19" maxlength="18" value="<%= com.getE07ANM().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="29%" > 
              <div align="right"> Identificaci&oacute;n del SWIFT 
                :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E07SWI" size="13" maxlength="12" value="<%= com.getE07SWI().trim()%>">
            </td>
            <td nowrap width="47%" >&nbsp;</td>
          </tr>
          <tr id=trdark> 
            <td nowrap width="29%"> 
              <div align="right">DDA :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" name="E07DDA" size="13" maxlength="12" value="<%= com.getE07DDA().trim()%>">
            </td>
            <td nowrap width="47%">&nbsp;</td>
          </tr>
        </table>
        </td>
    </tr>
  </table>
  <h4>N&uacute;meros de Telex</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id=trdark> 
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="27%"> 
              <div align="left">N&uacute;meros de Telex</div>
            </td>
            <td nowrap width="44%"> 
              <div align="left">Respuesta</div>
            </td>
          </tr>
          <tr> 
            <td nowrap width="29%" > 
              <div align="right">Carta de Cr&eacute;dito 
                :</div>
            </td>
            <td nowrap width="27%" > 
              <div align="left"> 
                <input type="text" name="E07TN1" size="16" maxlength="15" value="<%= com.getE07TN1().trim()%>">
              </div>
            </td>
            <td nowrap width="44%"> 
              <div align="left"> 
                <input type="text" name="E07AB1" size="18" maxlength="17" value="<%= com.getE07AB1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trdark> 
            <td nowrap width="29%"> 
              <div align="right">Operaciones :</div>
            </td>
            <td nowrap width="27%"> 
              <div align="left"> 
                <input type="text" name="E07TN2" size="16" maxlength="15" value="<%= com.getE07TN2().trim()%>">
              </div>
            </td>
            <td nowrap width="44%"> 
              <div align="left"> 
                <input type="text" name="E07AB2" size="18" maxlength="17" value="<%= com.getE07AB2().trim()%>">
              </div>
            </td>
          </tr>
          <tr> 
            <td nowrap width="29%"> 
              <div align="right">Inversiones :</div>
            </td>
            <td nowrap width="27%"> 
              <div align="left"> 
                <input type="text" name="E07TN3" size="16" maxlength="15" value="<%= com.getE07TN3().trim()%>">
              </div>
            </td>
            <td nowrap width="44%"> 
              <div align="left"> 
                <input type="text" name="E07AB3" size="18" maxlength="17" value="<%= com.getE07AB3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trdark> 
            <td nowrap width="29%"> 
              <div align="right">Moneda Extranjera 
                :</div>
            </td>
            <td nowrap width="27%"> 
              <div align="left"> 
                <input type="text" name="E07TN4" size="16" maxlength="15" value="<%= com.getE07TN4().trim()%>">
              </div>
            </td>
            <td nowrap width="44%"> 
              <div align="left"> 
                <input type="text" name="E07AB4" size="18" maxlength="17" value="<%= com.getE07AB4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trclear> 
            <td nowrap width="29%"> 
              <div align="right">Por Omisi&oacute;n :</div>
            </td>
            <td nowrap width="27%"> 
              <div align="left"> 
                <input type="text" name="E07TLN" size="16" maxlength="15" value="<%= com.getE07TLN().trim()%>">
              </div>
            </td>
            <td nowrap width="44%"> 
              <div align="left"> 

              </div>
            </td>
          </tr>
        </table>
        </td>
    </tr>
  </table>
  <h4>N&uacute;meros de Fax</h4>
  <table class="tableinfo">
    <tr  > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id=trdark> 
            <td nowrap width="46%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> N&uacute;meros de Fax</div>
            </td>
          </tr>
          <tr id=trclear> 
            <td nowrap width="46%"> 
              <div align="right">Carta de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="54%" > 
              <div align="left"> 
                <input type="text" name="E07FX1" size="16" maxlength="15" value="<%= com.getE07FX1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trdark> 
            <td nowrap width="46%"> 
              <div align="right">Operaciones :</div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> 
                <input type="text" name="E07FX2" size="16" maxlength="15" value="<%= com.getE07FX2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trclear> 
            <td nowrap width="46%"> 
              <div align="right">Inversiones :</div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> 
                <input type="text" name="E07FX3" size="16" maxlength="15" value="<%= com.getE07FX3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trdark> 
            <td nowrap width="46%"> 
              <div align="right">Moneda Extranjera 
                :</div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> 
                <input type="text" name="E07FX4" size="16" maxlength="15" value="<%= com.getE07FX4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id=trclear> 
            <td nowrap width="46%"> 
              <div align="right">Por Omision 
                :</div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> 
              <input type="text" name="E07FAX" size="16" maxlength="15" value="<%= com.getE07FAX().trim()%>">
              </div>
            </td>
          </tr>
          
         </table>
        </td>
    </tr>
  </table>
  <h4>Cuenta de Contrapartida</h4>
  <table class="tableinfo">
    <tr  > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr> 
            <td nowrap width="46%"> 
              <div align="right">Cuenta Contable:</div>
            </td>
            <td nowrap width="54%"> 
              <div align="left"> 
                <input type="text" name="E07ABK" size="18" maxlength="16" value="<%= com.getE07ABK().trim()%>">
                <a href="javascript:GetLedger('E07ABK','<%=userPO.getBank().trim()%>','<%= userPO.getCurrency().trim()%>','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
              </div>
            </td>
          </tr>
        </table>
        </td>
    </tr>
  </table>  
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  </form>
</body>
</html>
