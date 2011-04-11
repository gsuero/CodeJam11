<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script language="javascript">
$(document).bind("load", function(){
	$("#loginDiv").corner();
});
</script>
	<s:actionerror theme="css_xhtml" />
	<s:actionmessage theme="css_xhtml" />
	<s:form action="procesarLogin" namespace="/admin" method="POST" theme="css_xhtml" cssClass="topSpace">
	<div id="loginDiv" align="center">
	<br />
		<div id="infoMessage" class="ui-widget" align="left">
		<div class="ui-state-highlight ui-corner-all" style="padding: 0pt 0.7em;">
	
		<h3>Iniciar sesi&oacute;n</h3>
		<table class="normalTable" width="300" cellspacing="0" cellpadding="3">
		<tr>
			<td width="150">Nombre de Usuario : </td>
			<td width="150">
				<s:textfield name="login" /><br />
			</td>
		</tr>
		<tr>
			<td>Contrase&ntilde;a : </td>
			<td>
				<s:password name="password"/>
			</td>
		</tr>
		<tr> 
			<td align="center">
				<button aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-primary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Iniciar Sesi&oacute;n</span></button>
			</td>
			<td align="center">
				&nbsp;<button onClick="return false" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-secondary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Cancelar</span></button>
			</td>
		</table>
	</div>
	</div>
	</div>
	</s:form>
