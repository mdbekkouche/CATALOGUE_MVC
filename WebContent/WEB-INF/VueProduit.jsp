<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
	function confirmer(url) {
		var rep = confirm("Etes vous sûr de vouloir supprimer ?");
		if (rep == true) {
			document.location = url;
		}
	}
</script>
</head>
<body>
    <div>
    	<form action="controleur.php" method="post">
    		<table>
    			<tr>
    				<td>Mot Clé:</td>
    				<td><input type="text" name="motCle"/></td>
    				<td><input type="submit" value="chercher" name="action"/></td>
    			</tr>
    		</table>
    	</form>
    </div>
    <div>
    	<form action="controleur.php" method="post">
    	<input type="hidden" value="${ model.mode }" name="mode"/>
    		<table>
				<c:if test="${ model.mode == 'ajout' }">
					<tr>
						<td>REF :</td>
						<td><input type="text" name="reference"
							value="${ model.produit.reference }" /></td>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${ model.mode == 'edit' }">
					<tr>
						<td>REF :</td>
						<td>${ model.produit.reference }<input type="hidden" name="reference"
							value="${ model.produit.reference }" /></td>
						<td></td>
					</tr>
				</c:if>
				<tr>
    				<td>Désignation :</td>
    				<td><input type="text" name="designation" value="${ model.produit.designation }"/></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>Prix :</td>
    				<td><input type="text" name="prix" value="${ model.produit.prix }"/></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td>Quantité :</td>
    				<td><input type="text" name="quantite" value="${ model.produit.quantite }"/></td>
    				<td></td>
    			</tr>
    			<td><input type="submit" name="action" value="save"/></td>
    		</table>
    	</form>
    </div>
    <div>
     ${ model.errors }
    </div>
	<div>
		<table class = "table1">
		 <tr>
			<th>REF</th> <th>DES</th> <th>PRIX</th> <th>QUANTITE</th>
		 </tr>
			<c:forEach items="${ model.produits }" var="p">
				<tr>
					<td>${p.reference}</td>
					<td>${p.designation}</td>
					<td>${p.prix}</td>
					<td>${p.quantite}</td>
					<td><a href="javascript:confirmer('controleur.php?action=delete&ref=${ p.reference }')">Supprimer</a> </td>
					<td><a href="controleur.php?action=edit&ref=${ p.reference }">Edit</a> </td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>