<%@page contentType="text/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Shows the use of the JSP 2.0 Expression Language</title>
    </head>

    <body>
        <h1>JSP-EL Calculator</h1>
        <p>Basic math in JSP-EL, enter two numbers:</p>

        <form>
            <input type='text' name='value1' value="${param.value1}"/>
            <input type='text' name='value2' value="${param.value2}" />
            <input type='submit'/>
        </form>

        <p>SUM: ${param.value1}+${param.value2} = ${param.value1 + param.value2}</p>

    </body>
</html>
