<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(document).ready(function() {
	setCurrentMenu("ordenar");
	$("#quantity").keydown(onlyNumbers);
});
</script>
<style>
.error {
color: #D8000C;
}
</style>

<script type="text/javascript" src="/WebPuestoDeQuipe/js/order.js"></script>
<br />
<br />
<br />


<h1>Ordenar : </h1>

<s:if test="hasActionErrors()">
   <div class="error">
      <s:actionerror />
   </div>
</s:if>
<s:form action="procesarOrden" validate="false" method="POST" theme="css_xhtml">

<table class="normalTable" width="450px" id="specialForm">
<tr>
	<td><s:textfield name="name" label="Nombre" /></td>
	<td></td>
</tr>
<tr>
	<td><s:textfield name="email" label="E-Mail" /></td>
	<td></td>
</tr>
<tr>
	<td><s:textfield name="phone" label="Telefono" /></td>
	<td></td>
</tr>
<tr>
	<td><s:textfield name="address" label="Direccion" /></td>
	<td></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td></td>
</tr>
<tr>
	<td>	
				<s:select name="product"
		list="products"
		id="product"
		listKey="productId"
		listValue="productName"
		headerKey="0" headerValue="" />	
		</td>
		<td rowspan="4"><s:textarea name="bill" id="bill" readonly="true" label="Factura" rows="8" cssStyle="width:150px;" /></td>
		
</tr>
<tr>
	<td><s:textfield name="quantity"
			id="quantity"
			label="Cantidad"
			maxlength="3"
			size="3" /></td>
		<td></td>
</tr>
<tr>
	
</tr>
<tr>
	<td><input type="button" value="Agregar a Factura" onclick="addToBill()" align="left"/></td>
	<td></td>
</tr>
<tr>
	<td colspan="2" align="center"> <s:submit value="%{'Enviar'}" onclick="send()" align="left"/></td>
</tr>
</table>
	<input type="hidden" name="productsSelected"
			id="productsSelected"
			type="hidden" />	
	
</s:form>
