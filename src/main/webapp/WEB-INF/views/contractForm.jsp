<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Ajouter un Contrat</title>
</head>
<body>
<h2>Ajouter un Contrat</h2>
<form:form method="POST" action="/Amappli/contracts/add" modelAttribute="contract">
    Nom du contrat : <form:input path="contractName" /><br>
    Description : <form:textarea path="contractDescription" /><br>
    Poids : 
    <form:select path="contractWeight">
        <form:option value="PETIT" label="Petit"/>
        <form:option value="MOYEN" label="Moyen"/>
        <form:option value="GRAND" label="Grand"/>
    </form:select><br>
    Prix : <form:input path="contractPrice" type="number" step="0.01"/><br>
    Date de début : <form:input path="startDate" type="date" /><br>
    Date de fin : <form:input path="endDate" type="date" /><br>
    Image URL : <form:input path="imageUrl" /><br>
    <input type="submit" value="Ajouter Contrat" />
</form:form>
</body>
</html>
