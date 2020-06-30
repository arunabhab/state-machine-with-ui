<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>

<h1>File Upload</h1>

<form method="POST" action="/payAbbhiDashboard/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
