<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Location</title>
</head>
<body>
	<form action="updateLoc" method="post">
		<pre>
Id:    <input type="text" name="id" value ="${location.id}" readOnly />
Code:  <input type="text" name="code" value ="${location.code}"/>
Name:  <input type="text" name="name" value ="${location.name}"/>
Type: 
      Urban <input type="Radio" name="type" value="URBAN" ${location.type=="URBAN"? "checked":""} />
      Rural <input type="Radio" name="type" value="RURAL" ${location.type=="RURAL"? "checked":""} />
	        <input type="submit" value="Save" />

</pre>

	</form>

</body>
</html>