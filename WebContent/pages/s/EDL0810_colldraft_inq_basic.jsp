<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language = "java" %>
<%@ page import = "datapro.eibs.master.Util, java.math.BigDecimal" %>
<html>
<head>
<title>Draft Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "collBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id= "dftList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "lstAcceptors" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "lstDocuments" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript">

</SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 

<h3 align="center">Carta Guia de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="collection_list.jsp,EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1400">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="">

  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Planilla :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
				<input type="text" name="E01DLHNRO" size="12" maxlength="12" value="<%= collBasic.getE01DLHNRO()  %>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01DLHCCY" size="4" maxlength="3" value="<%= collBasic.getE01DLHCCY() %>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Producto :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHPRD" size="4" maxlength="4" value="<%= collBasic.getE01DLHPRD() %>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DLHOP1" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP1() %>" readonly>
              <input type="text" name="E01DLHOP2" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP2() %>" readonly>
              <input type="text" name="E01DLHOP3" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP3() %>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">No. de Documentos :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DLHNDO" size="3" maxlength="2" value="<%= collBasic.getE01DLHNDO() %>" onkeypress="enterInteger()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo Cobranza :</div>
            </td>
            <td nowrap> 
              <select name="E01DLHTCB" disabled="disabled">
		  		<option value="1" <% if (collBasic.getE01DLHTCB().equals("1")) out.print("selected"); %>>Simple</option>
                <option value="2" <% if (collBasic.getE01DLHTCB().equals("2")) out.print("selected"); %>>Garantia</option>
              </select>
            </td>
            <td nowrap> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="E01DLHTAM" size="15" maxlength="15" value="<%= collBasic.getE01DLHTAM() %>" onkeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" ><div align="right">
				<DIV align="right">Tipo de Documento :</DIV>
				
            </td>
            <td nowrap width="23%" > 
            <SELECT name="E01DLHTDO" disabled="disabled">
					<OPTION value="1"
						<% if (collBasic.getE01DLHTDO().equals("1")) out.print("selected"); %>>Letras</OPTION>
					<OPTION value="2"
						<% if (collBasic.getE01DLHTDO().equals("2")) out.print("selected"); %>>Pagares</OPTION>
					<OPTION value="3"
						<% if (collBasic.getE01DLHTDO().equals("3")) out.print("selected"); %>>Facturas</OPTION>
					<OPTION value="4"
						<% if (collBasic.getE01DLHTDO().equals("4")) out.print("selected"); %>>Otros</OPTION>
				</SELECT></td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Abono:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHCAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHCAC() %>" onkeypress="enterInteger()" readonly>
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Instrucciones de Protesto:</div>
            </td>
            <td nowrap colspan=1> 
              <select name="E01DLHTPO" disabled="disabled">
		  		<option value="1" <% if (collBasic.getE01DLHTPO().equals("1")) out.print("selected"); %>>Con Protesto</option>
                <option value="2" <% if (collBasic.getE01DLHTPO().equals("2")) out.print("selected"); %>>Sin Protesto</option>
                <option value="3" <% if (collBasic.getE01DLHTPO().equals("3")) out.print("selected"); %>>C/Prot Por falta de Acepatcion</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Cargo:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHDAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHDAC() %>" onkeypress="enterInteger()" readonly>
            </td>
 
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nro. Carta Guia:</div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01DLHDAC" size="12" maxlength="12" value=" " onkeypress="enterInteger()" readonly>
            </td>
 
          </tr>

         </table>
      </td>
    </tr>
  </table>

<h4>Aceptantes</h4>

<TABLE class="tableinfo">
   <tr > 
      <td nowrap>
            <table cellpadding="2" cellspacing="0" width="100%" border="0">

    
    <tr id="trdark">
      
                        <TD nowrap> 
          <div align="center">Identificación</div>
        </TD>
                        <TD nowrap> 
          <div align="center">Nombre</div>
        </TD>
                        <TD nowrap> 
          <div align="center">Monto</div>
        </TD>
                        <% 
	lstAcceptors.initRow();
	boolean firstTime = true;
	String chk = "";
     	while (lstAcceptors.getNextRow()) {
              if (lstAcceptors.getFlag().equals("")) {
     		      out.print("<tr id=\"trclear\">");
		 
                  if (firstTime) {
                      chk = "checked"; 
                      firstTime = false;
                  } else {
                    chk = ""; 
		          }
		          out.print("<TD nowrap width=\"162\">");
		          out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"IDACCEPTOR\" value=\"" + lstAcceptors.getRecord(0) + "\" readonly style=\"font-weight: bold;\"></div>");
		          out.print("</TD>");
		          out.print("<TD nowrap width=\"151\">");
		          out.print("<div align=\"center\"><INPUT size=\"35\" type=\"text\" name=\"NMEACCEPTOR\" value=\"" + lstAcceptors.getRecord(1) + "\" readonly style=\"font-weight: bold;\"></div>");
		          out.print("</TD>");
		          out.print("<TD nowrap width=\"232\">");
		          out.print("<div align=\"center\"><INPUT size=\"15\" type=\"text\" name=\"TOTACCEPTOR\" value=\"" + lstAcceptors.getRecord(2) + "\" readonly style=\"font-weight: bold;\"></div>");
		          out.print("</TD>");
		          out.print("</tr>");
		          lstDocuments.initRow();
				  firstTime = true;
				  while (lstDocuments.getNextRow()) {
					if (lstDocuments.getFlag().equals("") && lstDocuments.getRecord(8).equals(lstAcceptors.getRecord(0))) {
					    if (firstTime) {
					         out.print("<tr>");
					         out.print("<td colspan=\"3\">");
					         
					         out.print("<table>");
					         firstTime = false;
					         out.print("<tr>");
					         out.print("<TD width=\"190\">");
						     out.print("</td>");
						     out.print("<TD>");
							 out.print("</td>");
						     out.print("<TD>");
						     out.print("</td>");
						     out.print("<TD>");
							 out.print("</td>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Secuencia</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Fecha Vcto</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Monto</div>");
							 out.print("</TD>");
							 
							 out.println("</tr>"); 
					    }
					    
						out.print("<tr id=\"trclear\">");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"13\" type=\"text\" name=\"NDR\" value=\"" + lstDocuments.getRecord(0) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"3\" type=\"text\" name=\"MA1\" value=\"" + lstDocuments.getRecord(1) + "\" readonly>");
						out.print("<INPUT size=\"3\" type=\"text\" name=\"MA2\" value=\"" + lstDocuments.getRecord(2) + "\" readonly>");
						out.print("<INPUT size=\"3\" type=\"text\" name=\"MA3\" value=\"" + lstDocuments.getRecord(3) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"15\" type=\"text\" name=\"OAM\" value=\"" + lstDocuments.getRecord(4) + "\" readonly></div>");
						out.print("</TD>");
						
						out.print("</tr>");
					}
					
				}
				out.print("</table>");
				    out.print("</td>");
				    out.print("</tr>");
		          
              }
       }
%>
    </td>
    </tr>
   </table>
            </tr>
</table>

  
</form>
<script>

</script>
</body>
</html>
