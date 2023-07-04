<html>
<head>
<title>Codigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">C&oacute;digos de Referencia del Sistema <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_generic_short_codes_details.jsp, ESD0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" id="form1" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="E02CNOBNK" value="<%= currUser.getE01UBK()%>">
  <INPUT TYPE=HIDDEN NAME="DUMMY" value="<%= currUser.getE01UBK()%>">
   
   
   
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>

          <tr id="trdark" >
            <td nowrap width="16%" height="23"> 
              <div align="right">Estado :</div>
            </td>

            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="STATECODE" size="4" maxlength="2" value="" >
                <input type="hidden" name="STATEDSC" size="37" maxlength="35" value="" onchange="document.all.STATEDSCL.innerHTML = form1.STATEDSC.value" >
			    <a href="javascript:GetCodeDescCNOFC('STATECODE','STATEDSC','27')" onblur="document.all.STATEDSCL.innerHTML = form1.STATEDSC.value" ><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                <SPAN id="STATEDSCL" ></SPAN>

              </div>
            </td>
          </tr>

          <tr id="trclear" >
            <td nowrap width="16%" height="23"> 
              <div align="right">Municipio :</div>
            </td>

            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="MNCODE" size="4" maxlength="2" value="">

                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >

                <input type="hidden" name="E02CNOMID" size="8" maxlength="6" value="<%= refCodes.getE02CNORCD().trim()%>">
                <input type="hidden" name="E02CNORCD" size="8" maxlength="6" value="">
                <input type="hidden" name="E02SETRCD" size="8" maxlength="6" value="<%= refCodes.getE02CNORCD().trim()%>">


              </div>
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCG" size="17" maxlength="16" value="<%= refCodes.getE02CNOSCG().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar" onclick="javascript:setValues()" >
  </div>
  </form>
  
<SCRIPT language="javascript" >


function parseValues(){
	var longCode = "<%= refCodes.getE02SETRCD()%>" ;
	var stCode = longCode.substring(0,2) ;
	
	if( longCode.length >= 4 ){
		if( stCode == "00"){
			form1.STATECODE.value = "  " ;
		}else {
			form1.STATECODE.value = stCode ;
		}
		form1.MNCODE.value = longCode.substring(2,4);
	}
}

parseValues();

function setValues(){

	if( form1.STATECODE.value == "" ){ form1.STATECODE.value = "00" ; }
	if( form1.MNCODE.value == "" ){ form1.MNCODE.value = "  " ; }

	form1.E02CNOMID.value = form1.STATECODE.value + form1.MNCODE.value ;
	form1.E02SETRCD.value = form1.E02CNOMID.value ;
	form1.E02CNORCD.value = form1.E02CNOMID.value ;

}

</SCRIPT>
  
</body>
</html>
