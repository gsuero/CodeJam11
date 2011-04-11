<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.datepicker.js"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-sp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<script type="text/javascript">
	$(function() {
		setCurrentMenu("especiales");
		$("#expirationDate").datepicker({minDate: 0});
	});
</script>
<br />
<br />
	<s:actionerror theme="css_xhtml" />
	<s:actionmessage theme="css_xhtml" />
	
<s:form action="ProcesarEspeciales" namespace="/admin" method="POST" theme="css_xhtml" validate="false">
<h1>Especiales</h1>
Creación de especiales que pueden ser compartidos via red social y una vez creados, ser impresos.
	
<table class="normalTable" width="450px" id="specialForm">
<tr>
	<td>Descripci&oacute;n: </td>
	<td>&nbsp;</td>
	<td><s:textfield name="description" maxlength="140" /></td>
</tr>
<tr>
	<td>Oferta: </td>
	<td></td>
	<td><s:textarea name="summary" rows="15" cssStyle="width:350px" ></s:textarea></td>
</tr>
<tr>
	<td>Fecha expiraci&oacute;n: </td>
	<td></td>
	<td><s:textfield  required="true" name="expirationDate" id="expirationDate" maxlength="10" /></td>
</tr>
<tr>
	<td>Redes Sociales: </td>
	<td></td>
	<td><s:checkboxlist list="socialNetworks" name="social" /></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td></td>
	<td><button type="submit">Enviar Especial</button></td>
</tr>
</table>

</s:form>


