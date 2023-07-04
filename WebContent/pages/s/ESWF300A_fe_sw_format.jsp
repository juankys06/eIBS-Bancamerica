<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="swift" class="datapro.eibs.beans.ESWF10003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
    builtNewMenu(swa_bo_opt);
 </SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>
<h3 align="center">MT 300 - Mensaje de Confirmaci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_sw_format.jsp,ESWF300A"> 
</h3>
<hr size="4">
<form method="post" >
  <table class="tableinfo" width="100%" >
    <tr id="trclear"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> <%= swift.getE03SWFNA1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE03SWFNA2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE03SWFNA3()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap ><%= swift.getE03SWFREF()%></td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Identificaci&oacute;n Enviador :</div>
            </td>
            <td nowrap width="23%" ><%= swift.getE03SWFSID()%> </td>
            <td nowrap width="18%" > 
              <div align="right">Identificaci&oacute;n Recibidor :</div>
            </td>
            <td nowrap width="20%" ><%= swift.getE03SWFRID()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <h4> 
      <h4> 
        <h4> 
          <h4>Secuencia A : Informaci&oacute;n General</h4>
        </h4>
      </h4>
    </h4>
  </h4>
  <table  class="tableinfo" width="534">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Nueva Secuencia 15 A :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="center"></div>
              <%= swift.getE03SWFSQA()%></td>
            <td nowrap width="17%" > 
              <div align="right">Referencia Enviador 20 :</div>
            </td>
            <td nowrap > 
              <div align="center"><%= swift.getE03SWFREF()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Operaci&oacute;n 22 A :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="center"></div>
              <div align="left"> <%= swift.getE03SWFTOP()%></div>
            </td>
            <td nowrap width="17%" > 
              <div align="right">Referencia Relativa 21 :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"><%= swift.getE03SWFRLR()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Entidad A 82 :</div>
            </td>
            <td nowrap > 
              <div align="left"></div>
              <div align="left"> 
                <input type="text" readonly  name="E03SWFPAO" size="4" maxlength="2" value="<%= swift.getE03SWFPAO()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E03SWFPTA" size="40" maxlength="35" value="<%= swift.getE03SWFPTA()%>" 
			  >
            </td>
            <td nowrap width="17%" > 
              <div align="right">Referencia Com&uacute;n 22 C :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"><%= swift.getE03SWFCRF()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Entidad B 87 :</div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" readonly  name="E03SWFPBO" size="4" maxlength="2" value="<%= swift.getE03SWFPBO()%>" 
			  >
              </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly  name="E03SWFPTB" size="40" maxlength="35" value="<%= swift.getE03SWFPTB()%>" 
			  >
            </td>
            <td nowrap colspan="2" > 
              <div align="right"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> 
    <h4> 
      <h4> 
        <h4> 
          <h4>Secuencia B : Detalles de la Transacci&oacute;n</h4>
          <table  class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellpadding=2 cellspacing=0 width="100%" border="0">
                  <tr id="trdark"> 
                    <td nowrap width="17%" > 
                      <div align="right">Nueva Secuencia 15 B :</div>
                    </td>
                    <td nowrap ><%= swift.getE03SWFSQB()%></td>
                    <td nowrap width="17%" > 
                      <div align="right">Fecha del Contrato 30 T :</div>
                    </td>
                    <td nowrap > 
                      <div align="left"><%= Util.formatDate(swift.getE03SWFTD1(),swift.getE03SWFTD2(),swift.getE03SWFTD3())%></div>
                    </td>
                  </tr>
                  <tr id="trclear"> 
                    <td nowrap width="17%" > 
                      <div align="right">Tasa de Cambio 36 :</div>
                    </td>
                    <td nowrap > 
                      <div align="left"><%= swift.getE03SWFRTE()%></div>
                    </td>
                    <td nowrap width="17%" > 
                      <div align="right">Fecha Valor 30 V :</div>
                    </td>
                    <td nowrap > 
                      <div align="left"><%= Util.formatDate(swift.getE03SWFVD1(),swift.getE03SWFVD2(),swift.getE03SWFVD3())%></div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
          <h4>Secuencia C </h4>
        </h4>
      </h4>
    </h4>
  </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="left">Nueva Secuencia 15 C : <%= swift.getE03SWFSQC()%></div>
            </td>
            <td nowrap width="50%" >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" >Monto Comprado 32 B : <%= Util.fcolorCCY(swift.getE03SWFPRI())%> 
              - <%= swift.getE03SWFCCY().trim()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="left">Agente de Entrega 53</div>
            </td>
            <td nowrap width="50%" > 
              <div align="left">Agente Recibidor 57</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFADO" size="4" maxlength="2" value="<%= swift.getE03SWFADO()%>" 
			  >
                <input type="text" readonly  name="E03SWFAD1" size="40" maxlength="35" value="<%= swift.getE03SWFAD1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFARO" size="4" maxlength="2" value="<%= swift.getE03SWFARO()%>" 
			  >
                <input type="text" readonly  name="E03SWFAR1" size="40" maxlength="35" value="<%= swift.getE03SWFAR1()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAD2" size="40" maxlength="35" value="<%= swift.getE03SWFAD2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAR2" size="40" maxlength="35" value="<%= swift.getE03SWFAR2()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAD3" size="40" maxlength="35" value="<%= swift.getE03SWFAD3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAR3" size="40" maxlength="35" value="<%= swift.getE03SWFAR3()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAD4" size="40" maxlength="35" value="<%= swift.getE03SWFAD4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAR4" size="40" maxlength="35" value="<%= swift.getE03SWFAR4()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >Intermediario 56</td>
            <td nowrap width="50%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAIO" size="4" maxlength="2" value="<%= swift.getE03SWFAIO()%>" 
			  >
                <input type="text" readonly  name="E03SWFAI1" size="40" maxlength="35" value="<%= swift.getE03SWFAI1()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI2" size="40" maxlength="35" value="<%= swift.getE03SWFAI2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI3" size="40" maxlength="35" value="<%= swift.getE03SWFAI3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI4" size="40" maxlength="35" value="<%= swift.getE03SWFAI4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="50%" > 
              <div align="right"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Secuencia D</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="left">Nueva Secuencia 15 D :<%= swift.getE03SWFSQD()%></div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" >Monto Vendido 33 B : <%= Util.fcolorCCY(swift.getE03SWFSPR())%> 
              - <%= swift.getE03SWFSCY()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="left">Agente de Entrega 53</div>
            </td>
            <td nowrap > 
              <div align="left">Intermediario 56</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBDO" size="4" maxlength="2" value="<%= swift.getE03SWFBDO()%>" 
			  >
                <input type="text" readonly  name="E03SWFBD1" size="40" maxlength="35" value="<%= swift.getE03SWFBD1()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBIO" size="4" maxlength="2" value="<%= swift.getE03SWFBIO()%>" 
			  >
                <input type="text" readonly  name="E03SWFBI1" size="40" maxlength="35" value="<%= swift.getE03SWFBI1()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBD2" size="40" maxlength="35" value="<%= swift.getE03SWFBD2()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBI2" size="40" maxlength="35" value="<%= swift.getE03SWFBI2()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBD3" size="40" maxlength="35" value="<%= swift.getE03SWFBD3()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBI3" size="40" maxlength="35" value="<%= swift.getE03SWFBI3()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBD4" size="40" maxlength="35" value="<%= swift.getE03SWFBD4()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBI4" size="40" maxlength="35" value="<%= swift.getE03SWFBI4()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >Agente Recibidor 57</td>
            <td nowrap > 
              <div align="left">Instituci&oacute;n Beneficiaria 58</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBRO" size="4" maxlength="2" value="<%= swift.getE03SWFBRO()%>" 
			  >
                <input type="text" readonly  name="E03SWFBR1" size="40" maxlength="35" value="<%= swift.getE03SWFBR1()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBBO" size="4" maxlength="2" value="<%= swift.getE03SWFBBO()%>" 
			  >
                <input type="text" readonly  name="E03SWFBB1" size="40" maxlength="35" value="<%= swift.getE03SWFBB1()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBR2" size="40" maxlength="35" value="<%= swift.getE03SWFBR2()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBB2" size="40" maxlength="35" value="<%= swift.getE03SWFBB2()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBR3" size="40" maxlength="35" value="<%= swift.getE03SWFBR3()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBB3" size="40" maxlength="35" value="<%= swift.getE03SWFBB3()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBR4" size="40" maxlength="35" value="<%= swift.getE03SWFBR4()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFBB4" size="40" maxlength="35" value="<%= swift.getE03SWFBB4()%>" 
			  >
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
