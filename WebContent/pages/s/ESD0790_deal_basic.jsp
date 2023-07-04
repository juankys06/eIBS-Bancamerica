<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Sistema Bancario: Mantencion de Convenios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="deal" class="datapro.eibs.beans.ESD079001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
</head>

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

//  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }
  
  function GetTabla(cod,codtabla)
  {
   page= prefix +language + "EST0005_helpfile_Table.jsp?codtabla=" + codtabla ;
   fieldName=cod;
   var dx = 400;
   var dy = 350;
   var y0 = (screen.height - dy) / 2;
   var x0 = (screen.width - dx) / 2;
   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

   listin = window.open(page,'',attr);

}

function GetTablasComex(name,desc,flag)
{
	page= prefix +language + "EOC0001_helpfiles_TablasComex_Desc.jsp?codeflag=" + flag;
	fieldName=name;
	fieldDesc=desc;		
	CenterWindow(page,400,350,2);
}
function Nada()
{	
}
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<H3 align="center">Nuevo/Mantenimiento de Convenios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="deal_basic.jsp, ESD0790"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0790" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
<H4>Datos B&aacute;sicos</H4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap height="32"> 
              <div align="right">Código de Convenio : </div>
            </td>
            <td colspan="3" nowrap> 
              <input type="text" name="E01COTCDE" size="5" id="txtlabel" readonly maxlength="4" value="<%= deal.getE01COTCDE().trim()%>">
            </td>
           </tr>
          <tr id="trclear"> 
            <td nowrap height="32"> 
              <div align="right">Descripción : </div>
            </td>
            <td  nowrap> 
              <input type="text" name="E01COTDES" size="55" maxlength="50" value="<%= deal.getE01COTDES().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">R.U.T. Empresa (*): </div>
            </td>
            <td nowrap > 
              <INPUT type="text" name="E01COTRUT" size="15" maxlength="15" value="<%= deal.getE01COTRUT().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Productos en Convenio : </div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01COTCTY" size="5" maxlength="4" value="<%= deal.getE01COTCTY().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01COTCTY','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
              <b>9999:Todos</b></td>
            <td nowrap> 
              <div align="right">Cta. Coriente Principal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01COTACC" size="12" maxlength="12" onKeypress="enterInteger()" value="<%= deal.getE01COTACC().trim()%>">
              <a href="javascript:GetAccount('E01COTACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Inicio (**) : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01COTFID" size="2" maxlength="2" value="<%= deal.getE01COTFID().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01COTFIM" size="2" maxlength="2" value="<%= deal.getE01COTFIM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01COTFIA" size="4" maxlength="4" value="<%= deal.getE01COTFIA().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DOBPicker(document.forms[0].E01COTFIM,document.forms[0].E01COTFID,document.forms[0].E01COTFIA)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap> 
              <div align="right">Fecha Vencimiento (**) : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01COTFVD" size="2" maxlength="2" value="<%= deal.getE01COTFVD().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01COTFVM" size="2" maxlength="2" value="<%= deal.getE01COTFVM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01COTFVA" size="4" maxlength="4" value="<%= deal.getE01COTFVA().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DOBPicker(document.forms[0].E01COTFVM,document.forms[0].E01COTFVD,document.forms[0].E01COTFVA)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Estado : </div>
            </td>
            <td nowrap> 
              <SELECT name="E01COTSTA">
                <OPTION value=" " <% if (!(deal.getE01COTSTA().equals("A") ||deal.getE01COTSTA().equals("C"))) out.print("selected"); %>></OPTION>
                <OPTION value="A" <% if (deal.getE01COTSTA().equals("A")) out.print("selected"); %>>Activo</OPTION>
                <OPTION value="C" <% if (deal.getE01COTSTA().equals("C")) out.print("selected"); %>>Cancelado</OPTION>
              </SELECT>
            </td>
            <td nowrap> 
              <div align="right">Referencia de Cargo : </div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01COTRCV" size="4" maxlength="4" onchange="Nada()" value="<%= deal.getE01COTRCV().trim()%>">
              <a href="javascript:GetTablasComex('E01COTRCV','DESCOTRCV','197')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"  ></a><input size="35" type="text" name="DESCOTRCV" id="txtlabel" maxlength="35" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Código Oficial : </div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01COTOFC" size="4" maxlength="4" value="<%= deal.getE01COTOFC()%>">
              <A href="javascript:GetCodeCNOFC('E01COTOFC','15')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A> 
            </td>
            <td nowrap> 
              <div align="right">Código Oficina : </div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01COTBRN" size="4" maxlength="4" value="<%= deal.getE01COTBRN()%>">
              <A href="javascript:GetBranch('E01COTBRN','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <DIV>(*) Rut completo sin gui&oacute;n  -  (**) Formato de fechas : (dd/mm/aaaa)</DIV>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>  
  <p align="center"> 
     <input class="imgfilter" type=image name="Submit" src="<%=request.getContextPath()%>/images/s/bt_enviar.gif" onMouseDown="this.className=''" onMouseUp="this.className='imgfilter'" >
  </p>
  </form>
 </body>
</html>
