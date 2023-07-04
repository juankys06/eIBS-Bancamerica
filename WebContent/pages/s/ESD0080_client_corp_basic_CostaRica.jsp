<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Cliente Juridico</TITLE>
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       builtNewMenu(client_corp_opt);

   </SCRIPT>

<%
}
%>
 <SCRIPT language=javascript>

 <%
String str_fecha = client.getH02TIM().trim();

if (Integer.parseInt(str_fecha.substring(0,2)) < 52 )
	str_fecha =  "20" + str_fecha.substring(0,6);
else
	str_fecha =  "19" + str_fecha.substring(0,6);
%>

function enviar()
{
	return true;
}


 function valida_segmento(){
  	var segmento=trim(document.forms[0].E02UC5.value);

  	if(segmento=="" || segmento==" "){
  		alert("Debe ingresar código de segmento");
  		return false;
  	}
  	else
  	{
  		return true;
  	}
  }

 function valida_subsegmento(){
  	var subsegmento=trim(document.forms[0].E02UC6.value);

  	if(subsegmento =="" || subsegmento==" "){
  		alert("Debe ingresar código de subsegmento");
  		return false;
  	}
  	else
  	{
  		return true;
  	}
  }


 function valida_comuna(){
  	var comuna=trim(document.forms[0].E02UC8.value);

  	if(comuna =="" || comuna==" "){
  		alert("Debe ingresar comuna");
  		return false;
  	}
  	else
  	{
  		return true;
  	}
  }


 function valida_ciudad(){
  	var ciudad=trim(document.forms[0].E02UC7.value);

	if(ciudad=="" || ciudad==" " ){
  		alert("Debe ingresar ciudad");
  		return false;
  	}
  	else
  	{
  		return true;
  	}
  }



 </SCRIPT>
</HEAD>

<BODY>

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

 <H3 align="center">Informaci&oacute;n Cliente Jur&iacute;dica  <IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_basic, ESD0080" ></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" onSubmit="return(enviar())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  <INPUT TYPE=HIDDEN NAME="FechaNow" VALUE="<%=client.getH02TIM().trim()%>">
  <H4>Raz&oacute;n Social</H4>
  <TABLE class="tableinfo">
       <TR >
        <TD nowrap>

        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <TR id="trdark">
            <TD nowrap width="39%">
              <DIV align="right">No Cliente :</DIV>
            </TD>
            <TD nowrap >
              <INPUT type="hidden" name="E02CUN" size="10" maxlength="9" value="<%= client.getE02CUN()%>">
              <% if (client.getE02CUN().trim().equals("999999999")) out.print("NUEVO CLIENTE"); else out.print(client.getE02CUN()); %>
            </TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="39%">
              <DIV align="right">Nombre Legal :</DIV>
            </TD>
            <TD nowrap>
              <INPUT type="text" name="E02NA1" size="46" maxlength="45" value="<%= client.getE02NA1().trim()%>">
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
            </TD>
          </TR>
          <TR id="trdark">
                <TD nowrap width="25%">
                  <DIV align="right">Nombre Padron :</DIV>
                </TD>
                <TD nowrap colspan=3>
                <INPUT type="text" name="E02PA1" size="121" maxlength="120" readonly " value="<%= client.getE02PA1().trim()%>">
                </TD>
              </TR>
          <TR id="trdark">
            <TD nowrap width="39%">
              <P align="right">Nombre Anterior :</P>
            </TD>
            <TD nowrap>
              <INPUT type="text" name="E02CP1" size="31" maxlength="30" value="<%= client.getE02CP1().trim()%>">
            </TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="39%">
              <DIV align="right">Nombre Corto :</DIV>
            </TD>
            <TD nowrap>
              <INPUT type="text" name="E02SHN" size="16" maxlength="15" value="<%= client.getE02SHN().trim()%>">
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
            </TD>
          </TR>
          <TR id="trdark">
            <TD nowrap width="39%">
              <DIV align="right">Identificaci&oacute;n de Central de Riesgo :</DIV>
            </TD>
            <TD nowrap>
              <INPUT type="text" name="E02FN2" size="31" maxlength="30" value="<%= client.getE02FN2().trim()%>">
            </TD>
          </TR>
          <TR id="trclear">
            <TD nowrap width="39%">
              <DIV align="right"></DIV>
            </TD>
            <TD nowrap>
              <INPUT type="text" name="E02LN1" size="31" maxlength="30" value="<%= client.getE02LN1().trim()%>">
            </TD>
          </TR>
          <TR id="trdark">
           <TD nowrap width="39%" >
                  <DIV align="right">Nacionalidad :</DIV>
           </TD>
           <TD nowrap >
            	  <SELECT name="E02CCS">
                    <OPTION value=" " <% if (!(client.getE02CCS().trim().equals("1")||client.getE02CCS().trim().equals("2") || client.getE02CCS().trim().equals("3"))) out.print("selected"); %>></OPTION>
                    <OPTION value="1   " <% if (client.getE02CCS().trim().equals("1")) out.print("selected"); %>>Chileno(a)</OPTION>
                    <OPTION value="2   " <% if (client.getE02CCS().trim().equals("2")) out.print("selected"); %>>Extranjero c/Residencia</OPTION>
                    <OPTION value="3   " <% if (client.getE02CCS().trim().equals("3")) out.print("selected"); %>>Extranjero s/Residencia</OPTION>
                  </SELECT>
           </TD>
          </TR>
          <TR id="trclear">
           <TD nowrap>
                <DIV align="right">Residente del Pa&iacute;s :</DIV>
           </TD>
           <TD nowrap >
                <INPUT type="radio" name="E02FL1" value="1" <%if (client.getE02FL1().equals("1")) out.print("checked"); %>>
                S&iacute;
                <INPUT type="radio" name="E02FL1" value="2" <%if (client.getE02FL1().equals("2")) out.print("checked"); %>>
                No
           </TD>
          </TR>
        </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Direcci&oacute;n</H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right">Direcci&oacute;n
                  Principal :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02NA2" size="36" maxlength="35" value="<%= client.getE02NA2().trim()%>">
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="39%">
                <DIV align="right"></DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02NA3" size="36" maxlength="35" value="<%= client.getE02NA3().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right"></DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02NA4" size="36" maxlength="35" value="<%= client.getE02NA4().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="39%">
                <DIV align="right">Comuna :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT readonly type="text" name="E02UC8" size="5" maxlength="4" value="<%= client.getE02UC8().trim()%>">
                <A href="javascript:GetCodeDescCNOFC1('E02UC8','D02UC8','E02UC7','E02CTY','80')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
                <INPUT type="text" readonly name="D02UC8" size="35" maxlength="30" value="<%= client.getD02UC8().trim()%>">
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right">Ciudad :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT readonly type="text" name="E02UC7" size="5" maxlength="4" value="<%= client.getE02UC7().trim()%>">
                <INPUT type="text" readonly name="E02CTY" size="35" maxlength="30" value="<%= client.getE02CTY().trim()%>">

              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="39%">
                <DIV align="right">Pa&iacute;s :</DIV>
              </TD>
              <TD nowrap width="61%">
	              <INPUT type="text" readonly name="E02CTR" size="20" maxlength="20" value="<%= client.getE02CTR().trim()%>">
 				  <!--<input type="text" readonly name="D02CTR" size="20" value="">
					<a href="javascript:GetCodeDescCNOFC('E02CTR','D02CTR','03')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" >
					</a>-->
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
            </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right">Apartado Postal :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02POB" size="11" maxlength="10" value="<%= client.getE02POB().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="39%">
                <DIV align="right">C&oacute;digo Postal :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02ZPC" size="16" maxlength="15" value="<%= client.getE02ZPC().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right">Tipo de Correo :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02MLC" size="5" maxlength="4" value="<%= client.getE02MLC().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="39%">
                <DIV align="right">Direcci&oacute;n E-Mail :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02IAD" style="text-transform : none;" size="41" maxlength="40" value="<%= client.getE02IAD().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="39%">
                <DIV align="right">Direcci&oacute;n Internet :</DIV>
              </TD>
              <TD nowrap width="61%">
                <INPUT type="text" name="E02WEB" style="text-transform : none;" size="41" maxlength="40" value="<%= client.getE02WEB().trim()%>">
                </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Identificaci&oacute;n </H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TR id="trdark">
              <TD nowrap width="21%">
                <DIV align="right">No. de Identificaci&oacute;n :</DIV>
              </TD>

            <TD nowrap width="21%">
              <INPUT readonly type="text" name="E02IDN" size="20" maxlength="15" value="<%= client.getE02IDN().trim()%>">
            </TD>

            <TD nowrap width="9%">
              <DIV align="right">Tipo :</DIV>
            </TD>

            <TD nowrap width="17%">
              <INPUT readonly type="text" name="E02TID" size="5" maxlength="4" value="<%= client.getE02TID().trim()%>">
   <%--             <a href="javascript:GetCodeCNOFC('E02TID','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>  --%>
             </TD>

            <TD nowrap width="12%">
              <DIV align="right">Pa&iacute;s :</DIV>
              </TD>

            <TD nowrap width="20%">
              <INPUT readonly type="text" name="E02PID" size="5" maxlength="4" value="<%= client.getE02PID().trim()%>">
<%--                <a href="javascript:GetCodeCNOFC('E02PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> --%>
            </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="21%">
                <DIV align="right">No. de Identificaci&oacute;n :</DIV>
              </TD>

            <TD nowrap width="21%">
              <INPUT type="text" name="E02IDF" size="16" maxlength="15" value="<%= client.getE02IDF().trim()%>">
                </TD>

            <TD nowrap width="9%">
              <DIV align="right">Tipo :</DIV>
              </TD>

            <TD nowrap width="17%">
              <INPUT type="text" name="E02TIF" size="5" maxlength="4" value="<%= client.getE02TIF().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02TIF','34')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A></TD>

            <TD nowrap width="12%">
              <DIV align="right">Pa&iacute;s :</DIV>
              </TD>

            <TD nowrap width="20%">
              <INPUT type="text" name="E02PIF" size="5" maxlength="4" value="<%= client.getE02PIF().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02PIF','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="21%">
                <DIV align="right" nowrap>No. de Identificaci&oacute;n :</DIV>
              </TD>

            <TD nowrap width="21%">
              <INPUT type="text" name="E02ID3" size="16" maxlength="15" value="<%= client.getE02ID3().trim()%>">
                </TD>

            <TD nowrap width="9%">
              <DIV align="right">Tipo :</DIV>
              </TD>

            <TD nowrap width="17%">
              <INPUT type="text" name="E02TI3" size="5" maxlength="4" value="<%= client.getE02TI3().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02TI3','34')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A></TD>

            <TD nowrap width="12%">
              <DIV align="right">Pa&iacute;s :</DIV>
              </TD>

            <TD nowrap width="20%">
              <INPUT type="text" name="E02PI3" size="5" maxlength="4" value="<%= client.getE02PI3().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02PI3','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="21%">
                <DIV align="right">No. de Registro Fiscal :</DIV>
              </TD>

            <TD nowrap width="21%">
              <INPUT type="text" name="E02ID4" size="16" maxlength="15" value="<%= client.getE02ID4().trim()%>">
                </TD>

            <TD nowrap width="9%">
              <DIV align="right">Tipo :</DIV>
              </TD>

            <TD nowrap width="17%">
              <INPUT type="text" name="E02TI4" size="5" maxlength="4" value="<%= client.getE02TI4().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02TI4','34')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A></TD>

            <TD nowrap width="12%">
              <DIV align="right">Pa&iacute;s :</DIV>
              </TD>

            <TD nowrap width="20%">
              <INPUT type="text" name="E02PI4" size="5" maxlength="4" value="<%= client.getE02PI4().trim()%>">
                 <A href="javascript:GetCodeCNOFC('E02PI4','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A></TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Fechas</H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TR id="trdark">
              <TD nowrap>
                <DIV align="right">Fecha de Constituci&oacute;n :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02BDD" size="2" maxlength="2" value="<%= client.getE02BDD().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02BDM" size="2" maxlength="2" value="<%= client.getE02BDM().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02BDY" size="4" maxlength="4" value="<%= client.getE02BDY().trim()%>" onKeyPress="enterInteger()">
              	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
              </TD>
              <TD nowrap>
                <DIV align="right">Fecha Inicio de Operaciones
                  :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02IEM" size="2" maxlength="2" value="<%= client.getE02IEM().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IED" size="2" maxlength="2" value="<%= client.getE02IED().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IEY" size="2" maxlength="2" value="<%= client.getE02IEY().trim()%>" onKeyPress="enterInteger()">
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap>
                <DIV align="right">Fecha de Ingreso :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02IDM" size="2" maxlength="2" value="<%= client.getE02IDM().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IDD" size="2" maxlength="2" value="<%= client.getE02IDD().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IDY" size="2" maxlength="2" value="<%= client.getE02IDY().trim()%>" onKeyPress="enterInteger()">
              </TD>
              <TD nowrap>
                <DIV align="right">Fecha de Registro :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02REM" size="2" maxlength="2" value="<%= client.getE02REM().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02RED" size="2" maxlength="2" value="<%= client.getE02RED().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02REY" size="2" maxlength="2" value="<%= client.getE02REY().trim()%>" onKeyPress="enterInteger()">
              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap>
                <DIV align="right">Fecha Ultimo Balance :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02IA1" size="2" maxlength="2" value="<%= client.getE02IA1().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IA2" size="2" maxlength="2" value="<%= client.getE02IA2().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02IA3" size="2" maxlength="2" value="<%= client.getE02IA3().trim()%>" onKeyPress="enterInteger()">
              </TD>
              <TD nowrap>
                <DIV align="right">Fecha Mov/Comercial :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02FA1" size="2" maxlength="2" value="<%= client.getE02FA1().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02FA2" size="2" maxlength="2" value="<%= client.getE02FA2().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02FA3" size="2" maxlength="2" value="<%= client.getE02FA3().trim()%>" onKeyPress="enterInteger()">
              </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Tel&eacute;fonos</H4>
    <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TR id="trdark">
              <TD nowrap >
                <DIV align="right">Tel&eacute;fono Oficina 1 :</DIV>
              </TD>
              <TD nowrap >
                <INPUT type="text" name="E02HPN" size="12" maxlength="11" value="<%= client.getE02HPN().trim()%>" onKeyPress="enterInteger()">
                </TD>
              <TD nowrap >
                <DIV align="right">Tel&eacute;fono de Fax :</DIV>
              </TD>
              <TD nowrap >
                <INPUT type="text" name="E02FAX" size="12" maxlength="11" value="<%= client.getE02FAX().trim()%>" onKeyPress="enterInteger()">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap>
                <DIV align="right">Tel&eacute;fono Oficina 2 :</DIV>
              </TD>
              <TD nowrap >
                <INPUT type="text" name="E02PHN" size="12" maxlength="11" value="<%= client.getE02PHN().trim()%>" onKeyPress="enterInteger()">
                </TD>
               <TD nowrap >
                <DIV align="right">Tel&eacute;fono Celular :</DIV>
              </TD>
              <TD nowrap >
                <INPUT type="text" name="E02PH1" size="12" maxlength="11" value="<%= client.getE02PH1().trim()%>" onKeyPress="enterInteger()">
                </TD>
            </TR>

          </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>C&oacute;digo Especiales</H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">Ejecutivo Principal :</DIV>
              </TD>
              <TD nowrap width="18%">
                  <INPUT type="text" name="E02OFC" size="5" maxlength="4" value="<%= client.getE02OFC().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02OFC','15')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
               </TD>
              <TD nowrap  width="23%">
                <DIV align="right">C&oacute;digo Oficina :</DIV>
              </TD>
              <TD nowrap  width="20%">
                 <INPUT readonly type="text" name="E02BRA" size="4" maxlength="3" value="<%= client.getE02BRA().trim()%>"> </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">Sectores Económicos SBIF :</DIV>
              </TD>
              <TD nowrap width="18%">
                <INPUT type="text" name="E02INC" size="5" maxlength="4" value="<%= client.getE02INC().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02INC','09')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
               </TD>
              <TD nowrap width="29%">
                <DIV align="right">Actividades Económicas SBIF :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02BUC" size="5" maxlength="4" value="<%= client.getE02BUC().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02BUC','12')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
             </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">Pa&iacute;s de Residencia :</DIV>
              </TD>
              <TD nowrap width="18%">
                <INPUT type="text" name="E02GEC" size="5" maxlength="4" value="<%= client.getE02GEC().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02GEC','03')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
                </TD>
              <TD nowrap width="29%">
                <DIV align="right">Categoria Deudor :</DIV>
              </TD>
              <TD nowrap width="31%" >
                <INPUT type="text" name="E02UC1" size="5" maxlength="4" value="<%= client.getE02UC1().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC1','82')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></A></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">C&oacute;digo de Usuario 2 :</DIV>
              </TD>
              <TD nowrap width="18%">
                <INPUT type="text" name="E02UC2" size="5" maxlength="4" value="<%= client.getE02UC2().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC2','2B')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
                </TD>
              <TD nowrap width="29%">
                <DIV align="right">C&oacute;digo de Segmento :</DIV>
              </TD>
              <TD nowrap width="31%" >
                <INPUT type="text" name="E02UC5" size="5" maxlength="4" value="<%= client.getE02UC5().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC5','2E')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
</TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">C&oacute;digo de Usuario 3 :</DIV>
              </TD>
              <TD nowrap width="18%" >
                <INPUT type="text" name="E02UC3" size="5" maxlength="6" value="<%= client.getE02UC3().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC3','2C')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A></TD>
              <TD nowrap width="29%">
                <DIV align="right">C&oacute;digo de Subsegmento :</DIV>
              </TD>
              <TD nowrap width="31%" >
                <INPUT type="text" name="E02UC6" size="5" maxlength="4" value="<%= client.getE02UC6().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC6','2F')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" >
                </TD>
            </TR>

            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">Unidad de Negocio :</DIV>
              </TD>
              <TD nowrap width="18%" >
                <INPUT type="text" name="E02UC4" size="7" maxlength="6" value="<%= client.getE02UC4().trim()%>" readonly>
                </TD>
              <TD nowrap width="29%">
                <DIV align="right">Canal de Ventas :</DIV>
              </TD>
              <TD nowrap width="31%" >
                <INPUT type="text" name="E02SCH" size="5" maxlength="4" value="<%= client.getE02SCH().trim()%>" readonly>
                <!--<a href="javascript:GetCodeCNOFC('E02SCH','62')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>-->
            </TR>
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">Fuente de Información :</DIV>
              </TD>
              <TD nowrap width="18%"><INPUT type="text" name="E02SST" size="5" maxlength="4" value="<%= client.getE02SST().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02SST','65')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
                </TD>
              <TD nowrap width="29%">
                <DIV align="right">Naturaleza del Cliente :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02UC9" size="5" maxlength="4" value="<%= client.getE02UC9().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02UC9','2I')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
              </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>


    <H4>Datos Adicionales</H4>


     <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">No. de Registro
                  :</DIV>
              </TD>
              <TD nowrap width="22%">
                <INPUT type="text" name="E02REN" size="16" maxlength="15" value="<%= client.getE02REN().trim()%>">
                </TD>
              <TD nowrap width="25%">
                <DIV align="right">A&ntilde;os Establecidos
                  :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02NSO" size="3" maxlength="2" value="<%= client.getE02NSO().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">No. de Acciones
                  :</DIV>
              </TD>
              <TD nowrap width="22%">
                <INPUT type="text" name="E02NST" size="8" maxlength="7" value="<%= client.getE02NST().trim()%>">
                </TD>
              <TD nowrap width="25%">
                <DIV align="right">No. de Accionistas (miles)
                  :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02NSH" size="8" maxlength="7" value="<%= client.getE02NSH().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">Capital Inicial
                  :</DIV>
              </TD>
              <TD nowrap width="22%">
                <INPUT type="text" name="E02CAI" size="14" maxlength="12" value="<%= client.getE02CAI().trim()%>">
                </TD>
              <TD nowrap width="25%">
                <DIV align="right">Capital Suscrito
                  :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02CAS" size="14" maxlength="12" value="<%= client.getE02CAS().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">Capital Pagado
                  :</DIV>
              </TD>
              <TD nowrap width="22%">
                <INPUT type="text" name="E02CAP" size="14" maxlength="12" value="<%= client.getE02CAP().trim()%>">
                </TD>
              <TD nowrap width="25%">
                <DIV align="right">Nivel de Ventas
                :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02INL" size="2" maxlength="1" value="<%= client.getE02INL().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="22%">
                <DIV align="right">Tipo Empresa :</DIV>
              </TD>
              <TD nowrap width="22%">
                <SELECT NAME="E02SEX" SIZE="1">
 					<OPTION VALUE="" <% if (!(client.getE02SEX().equals("L") || client.getE02SEX().equals("I") || client.getE02SEX().equals("T") || client.getE02SEX().equals("S") || client.getE02SEX().equals("H") || client.getE02SEX().equals("O"))) out.print("SELECTED");%>>
					<OPTION VALUE="L" <% if (client.getE02SEX().equals("L")) out.print("SELECTED");%>>Local
					<OPTION VALUE="I" <% if (client.getE02SEX().equals("I")) out.print("SELECTED");%>>Internacional
					<OPTION VALUE="T" <% if (client.getE02SEX().equals("T")) out.print("SELECTED");%>>Multinacional
					<OPTION VALUE="S" <% if (client.getE02SEX().equals("S")) out.print("SELECTED");%>>Pequeño Negocio
					<OPTION VALUE="H" <% if (client.getE02SEX().equals("H")) out.print("SELECTED");%>>Holding Company
					<OPTION VALUE="O" <% if (client.getE02SEX().equals("O")) out.print("SELECTED");%>>Otras
				</SELECT>
                <!--<input type="text" name="E02SEX" size="2" maxlength="1" value="<%= client.getE02SEX().trim()%>">-->
               </TD>
              <TD nowrap width="25%">
                <DIV align="right">Area de Negocio
                  :</DIV>
              </TD>
              <TD nowrap width="31%">
                <INPUT type="text" name="E02FL3" size="2" maxlength="1" value="<%= client.getE02FL3().trim()%>">
                </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="22%">
                <DIV align="right">Fuentes de Ingresos
                  :</DIV>
              </TD>
              <TD nowrap width="22%">
                <INPUT type="text" name="E02SOI" size="5" maxlength="4" value="<%= client.getE02SOI().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02SOI','30')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
                </TD>
              <TD nowrap>
                <DIV align="right">C&oacute;digo del Vendedor :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02F01" size="5" maxlength="4" value="<%= client.getE02F01().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02F01','02')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></A>
              </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Datos Operativos</H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap>
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TBODY><TR id="trdark">

            <TD nowrap width="10%">
              <DIV align="right">Estado del Cliente :</DIV>
            </TD>

            <TD nowrap width="15%">
              <SELECT name="E02STS">
                  <% boolean flag = false; %>
                  <OPTION value="2" <%if (client.getE02STS().equals("2")) { flag = true; out.print("selected"); }%>>Inactivo</OPTION>
                  <OPTION value="1" <%if (client.getE02STS().equals("1")) { flag = true; out.print("selected"); }%>>Activo</OPTION>
                  <OPTION value="3" <%if (client.getE02STS().equals("3")) { flag = true; out.print("selected"); }%>>Lista Negra</OPTION>
                  <OPTION value=" " <%if ( flag == false ) out.print("selected");  %>></OPTION>
                </SELECT>
                 <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom">
            </TD>

            <TD nowrap width="8%">
              <DIV align="right">Clase de Cliente :</DIV>
            </TD>

            <TD nowrap width="">
              <INPUT type="text" name="E02CCL" size="2" maxlength="1" value="<%= client.getE02CCL().trim()%>">
              <A href="javascript:GetCode('E02CCL','STATIC_client_help_class.htm')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
            </TD>

          </TR>
          <TR id="trclear">

            <TD nowrap width="10%">
              <DIV align="right">Tipo de Cliente :</DIV>
            </TD>

            <TD nowrap width="15%">
              <INPUT type="radio" name="E02TYP" value="R" <% if (!(client.getE02TYP().equals("M") || client.getE02TYP().equals("G"))) out.print("checked"); %>>
                Regular
                <INPUT type="radio" name="E02TYP" value="M" <% if (client.getE02TYP().equals("M")) out.print("checked"); %>>
                Master
                <INPUT type="radio" name="E02TYP" value="G" <% if (client.getE02TYP().equals("G")) out.print("checked"); %>>
              Grupo <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"></TD>

            <TD nowrap width="8%">
              <DIV align="right">No. de Grupo :</DIV>
              </TD>

            <TD nowrap width="">
              <INPUT type="text" name="E02GRP" size="10" maxlength="9" value="<%= client.getE02GRP().trim()%>">
                </TD>
            </TR>
            <TR id="trdark">

            <TD nowrap width="10%">
              <DIV align="right">Idioma :</DIV>
              </TD>

            <TD nowrap width="15%">
              <INPUT type="radio" name="E02LIF" value="S" <%if (client.getE02LIF().equals("S")) out.print("checked"); %>>
                Espa&ntilde;ol
              <INPUT type="radio" name="E02LIF" value="E" <%if (!client.getE02LIF().equals("S")) out.print("checked"); %>>
                Ingles

                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"></TD>

            <TD nowrap width="8%">
              <DIV align="right">Calificaci&oacute;n
                  :</DIV>
              </TD>

            <TD nowrap width="">
              <INPUT readonly type="text" name="E02FL2" size="2" maxlength="1" value="<%= client.getE02FL2().trim()%>">

             </TD>
            </TR>
            <TR id="trclear">

            <TD nowrap width="10%">
              <DIV align="right">Impuestos/Retenciones
                  :</DIV>
              </TD>

            <TD nowrap width="15%">
              <INPUT type="text" name="E02TAX" size="2" maxlength="1" value="<%= client.getE02TAX().trim()%>">
              <A href="javascript:GetCode('E02TAX','STATIC_client_help_tax_instructions.htm')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"></TD>

             <TD nowrap>
              <DIV align="right">Personeria :</DIV>
             </TD>

             <TD nowrap>
              <INPUT type="text" name="E02FL7" size="2" maxlength="1" value="<%= client.getE02FL7().trim()%>">
              <A href="javascript:GetCode('E02FL7','STATIC_client_help_personeria.htm')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
             </TD>

<!--            <td nowrap width="8%">
              <div align="right">Tabla de Previsi&oacute;n:</div>
             </td>-->

<!--            <td nowrap width=""> -->
              <INPUT type="HIDDEN" name="E02PRV" size="4" maxlength="2" value="<%= client.getE02PRV().trim()%>">
<!--              <a href="javascript:GetPrevTable('E02PRV')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>
            </td>-->

            </TR>
            <TR id="trdark">
             <TD nowrap>
              <DIV align="right">Firma Contrato Uso Canal Electrónico :</DIV>
              </TD>

             <TD nowrap>
              <INPUT type="radio" name="E02FL8" value="1" <%if (!client.getE02FL8().equals("0")) out.print("checked"); %>> Firmado
              <INPUT type="radio" name="E02FL8" value="0" <%if (client.getE02FL8().equals("0")) out.print("checked"); %>> Sin Firmar
             </TD>

             <TD nowrap>
              <DIV align="right">Tipo Banco Corresponsal :</DIV>
             </TD><TD nowrap>
					<SELECT name="E02CRF" size="1">
					<OPTION value=""> </OPTION>
					<OPTION value="1" <%if (client.getE02FL8().equals("1")) out.print("checked"); %>>Banco Coreesponsal</OPTION>
					<OPTION value="4" <%if (client.getE02FL8().equals("4")) out.print("checked"); %>>Banco Corresponsal Línea</OPTION>
					<OPTION value="5" <%if (client.getE02FL8().equals("5")) out.print("checked"); %>>Banco Reembolsador</OPTION>
					<OPTION value="6" <%if (client.getE02FL8().equals("6")) out.print("checked"); %>>Banco Corresponsal Línea/Reembolsador</OPTION>
			<!--	<OPTION value="2">Otros Bancos</OPTION>
					<OPTION value="3">Otras Instituciones Financieras</OPTION>-->
				</SELECT></TD>


             </TR>
          </TBODY></TABLE>
        </TD>
      </TR>
    </TABLE>
    <H4>Miscelaneos</H4>
  <TABLE class="tableinfo">
      <TR >
        <TD nowrap >
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TR id="trdark">
              <TD nowrap  width="18%">
                <DIV align="right">Referido por :</DIV>
              </TD>
              <TD nowrap  width="39%">
                <INPUT type="text" name="E02RBY" size="5" maxlength="4" value="<%= client.getE02RBY().trim()%>">
                <INPUT type="text" name="E02RBN" size="16" maxlength="15" value="<%= client.getE02RBN().trim()%>">
                <A href="javascript:GetCodeDescCNOFC('E02RBY','E02RBN','28')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
                </TD>
              <TD nowrap>&nbsp;</TD>
              <TD nowrap>&nbsp;</TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="18%" >
                <DIV align="right">Nivel de Consulta
                  :</DIV>
              </TD>
              <TD nowrap width="39%" >
                <INPUT type="text" name="E02ILV" size="1" maxlength="1" value="<%= client.getE02ILV().trim()%>">
                </TD>
              <TD nowrap width="23%" >
                <DIV align="right">No. de Tarjetas ATM :</DIV>
              </TD>
              <TD nowrap width="20%" >
                <INPUT type="text" name="E02ATM" size="2" maxlength="1" value="<%= client.getE02ATM().trim()%>" readonly>
                </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap>
                <DIV align="right">Firma Mandato o Carta Poder :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="radio" name="E02FL5" value="1" <%if (client.getE02FL5().equals("1")) out.print("checked"); %>> Firmado
                <INPUT type="radio" name="E02FL5" value="0" <%if (!client.getE02FL5().equals("1")) out.print("checked"); %>> Sin Firmar
              </TD>
              <TD nowrap>
                <DIV align="right">Fecha Subscripción Firma :</DIV>
              </TD>
              <TD nowrap>
                <INPUT type="text" name="E02D11" size="2" maxlength="2" value="<%= client.getE02D11().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02D12" size="2" maxlength="2" value="<%= client.getE02D12().trim()%>" onKeyPress="enterInteger()">
                <INPUT type="text" name="E02D13" size="2" maxlength="2" value="<%= client.getE02D13().trim()%>" onKeyPress="enterInteger()">
               </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
    </TABLE>
<P align=center>
    <INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar">
	</P>


</FORM>
</BODY>
</HTML>
