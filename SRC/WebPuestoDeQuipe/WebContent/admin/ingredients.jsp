<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-sp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<s:form action="grabarIngredientes" namespace="/admin" method="POST" theme="css_xhtml">

<script>
$.jgrid.useJSON = true;
var ingredientsPage = CONTEXT_PATH + "/admin/Ingredientes.do";
var allIngredients = "";

<s:iterator value="ingredientsList">allIngredients += '<s:property value="ingredientId" />:<s:property value="ingredientName" />;';</s:iterator>
if (allIngredients.length > 0)
	allIngredients = allIngredients.substring(0, allIngredients.length-1);
	
$(document).ready(function() {
	
	var lastsel;
	jQuery("#rowed3").jqGrid(
			{
				url : CONTEXT_PATH+'/ajax/admin/ingredientesPorProducto.do?productId=<s:property value="productId" />',
				datatype: "json",
			    ajaxGridOptions: { contentType: "application/json" },
			    jsonReader : { 
			        root: "rows", 
			        page: "page", 
			        total: "total", 
			        records: "records", 
			        repeatitems: false
			    },
			    headertitles: true,
				colNames : [ 'ID', 'Ingrediente', 'Cantidad'],
				colModel : [ {
					name : 'ingredientId',
					index : 'ingredientId',
					align : "right",
					width : 20, 
					editable : false,
					hidden: true, 
					editable: true, 
					editrules: { edithidden: false }, 
					hidedlg: true
				}, {
					name : 'ingredientName',
					index : 'ingredientName',
					width : 190,
					editable : true,
					required : true,
					sortable:false, 
					search:false,
					edittype:"select", 
					editoptions:{value: allIngredients}
				}, {
					name : 'quantity',
					index : 'quantity',
					width : 70,
					editable : true,
					editrules:{number:true },
					required : true,
					sortable:false, 
					search:false
				} ],
				rowNum : 20,
				rowList : [ 20, 40, 60, 80 ],
				pager : '#prowed3',
				sortname : 'ingredientId',
				viewrecords : true,
				sortorder : "desc",
				onSelectRow : function(id) {
					if (id && id !== lastsel) {
						jQuery('#rowed3').jqGrid('restoreRow', lastsel);
						jQuery('#rowed3').jqGrid('editRow', id, true);
						lastsel = id;
					}
				},
				editurl : CONTEXT_PATH+'/ajax/admin/grabarIngredientes.do?productId=<s:property value="productId" />',
				caption : "Ingredientes"
			});
	jQuery("#rowed3").jqGrid('navGrid', "#prowed3", {
		edit : false,
		add : true,
		del : true
	});
})
</script>
	<h1>Ingredientes del <s:property value="product" /></h1>

	<table id="centerTableFull">
		<tr>
			<td align="center">
				<table id="rowed3"></table> <div id="prowed3"></div> <br /> 
			</td>
		</tr>
	</table>
	
	</s:form>