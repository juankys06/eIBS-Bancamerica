<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Account Holders</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cdTit" class= "datapro.eibs.beans.ESD000006Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cdm_m_opt); 
	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

  function ClearRow(idx){
    document.forms[0]["E06CU"+idx].value = "0";
    document.forms[0]["E06MA"+idx].value = "";
	document.forms[0]["E06ID"+idx].value = "";
	document.forms[0]["E06TY"+idx].value = "";				
  }
  
  function DeleteHolderSel(){
    var okdel=false;
    var posDel=0;
    var posSel=0;
    okdel = confirm("All selected Holders will be deleted");
    if ( okdel ) { 
       for (var i=0;i<8;i++){
         if (document.forms[0].HOLDER[i].checked){
           var j;
           posDel=i+1;
           posSel=0;
           for ( j=i+1;j<9;j++) {
             if (!document.forms[0].HOLDER[j].checked){
                posSel=j+1;
                document.forms[0]["E06CU"+posDel].value = document.forms[0]["E06CU"+posSel].value;
             	document.forms[0]["E06MA"+posDel].value = document.forms[0]["E06MA"+posSel].value;
				document.forms[0]["E06ID"+posDel].value = document.forms[0]["E06ID"+posSel].value;
				document.forms[0]["E06TY"+posDel].value = document.forms[0]["E06TY"+posSel].value;
				document.forms[0].HOLDER[j].checked=true;
				break;
			 }
           }
           if (posSel==0) ClearRow(posDel);
         }
       }
       if (document.forms[0].HOLDER[8].checked) ClearRow(9);
       for (var i=0;i<9;i++){
        document.forms[0].HOLDER[i].checked=false;
       } 
    }
  } 
</SCRIPT>

</head>


<body bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>
<h3 align="center">Mantenimiento de Titulares <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_tit.jsp,EDL0130M"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E06ACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table class="tbenter">
   <tr>
    <td>
        <h4>Titulares</h4>
      </td>
      <td align=center class=TDBKG width><a href="javascript:DeleteHolderSel()">Borrar 
        Selecci&oacute;n</a></td>
   </tr>
  </table>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
         
        <table id="headTable" width="100%">
          <tr id="trdark">
            <td nowrap width="2%"> 
              <div align="center"></div>
            </td> 
            <td nowrap width="10%"> 
              <div align="center">N&uacute;mero</div>
            </td>
            <td nowrap width="45%"> 
              <div align="center">Primer y Segundo Nombre </div>
            </td>
            <td nowrap width="25%"> 
              <div align="center"> Identificaci&oacute;n </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center">Relaci&oacute;n</div>
            </td>
          </tr>
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap width="2%"> 
              <div align="center"> 
                <input type="checkbox" name="HOLDER" value="1">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E06CU<%= name %>" size="9" maxlength="9" value="<%= cdTit.getField("E06CU"+name).getString().trim()%>" 
				oncontextmenu="showPopUp(custdescidHelp,this.name,'','','E06MA<%= name %>','E06ID<%= name %>','')" onkeypress="enterInteger()">
              </div>
            </td>
            <td nowrap width="45%"> 
              <div align="center">
                <input type="text" name="E06MA<%= name %>" size="40" maxlength="45" value="<%= cdTit.getField("E06MA"+name).getString().trim()%>" readonly
                oncontextmenu="showPopUp(custdescidHelp,'E06CU<%= name %>','','','E06MA<%= name %>','E06ID<%= name %>','')">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="center"> 
                <input type="text" name="E06ID<%= name %>" size="15" maxlength="15" value="<%= cdTit.getField("E06ID"+name).getString().trim()%>" readonly
                oncontextmenu="showPopUp(custdescidHelp,'E06CU<%= name %>','','','E06MA<%= name %>','E06ID<%= name %>','')">
              </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center"> 
                <input type="text" name="E06TY<%= name %>" size="2"  maxlength="1"
				oncontextmenu="showPopUp(accholdersHelp,this.name,'','','','','')"  value="<%= cdTit.getField("E06TY"+name).getString().trim()%>" >
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
        </table>
      </td>
    </tr>
  </table>
 <br>
  <div align="center"> 
	   
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>
