<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

		<script>
			function getLiquor()
			{
				// Makes an AJAX call to the ORders via a REST Service
				$.ajax(
					{
						type: "GET",
						url: "${pageContext.request.contextPath}/inventory/getAllLiquor",
						dataType: "json",
						success: function(data)
						{
							$('#liquorTable').dataTable({
								responsive: true,
								data: data,
								columns: [{
									data: "liquorCode",
									title: "Liqour Code"
								},
								{
									data: "brandName",
									title: "Brand Name"
								},
								{
									data: "alcoholType",
									title: "Alchohol Type"
								},
								{
									data: "liquidVolume",
									title: "Liquid Volume"
								},
								{
									data: "overflow",
									title: "Overflow"	
								},
								{
									data: "alertLevel",
									title: "Alert Level"
								}
							]
						});
							$('#liquorTable').on('click', '#editbutton');
					},
					error: function (xhr, ajaxOptions, thrownError)
					{
						alert(xhr.status);
						alert(thrownError);
					}
				});
			}
			
			$(document).ready(getLiquor);
		</script>
		
		<div style="width:100%">
		<table id="liquorTable" style="width:100%" border="1">
			
			<thead style="background-color:#A0A0A0">
			</thead>
			
			<tbody>
				
			<tbody>
		</table>
		</div>