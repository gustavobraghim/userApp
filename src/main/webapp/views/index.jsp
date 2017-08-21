<html>
    <head>
        <title>Index</title>
    </head>
    <body>
        <p><font color="red">${errorMessage}</font></p>

         <form action="/signin" method="POST">
                Email: <input name="email" type="text" />
                Password: <input name="password" type="password" />
                <input type="submit" value="Logar"/>
         </form>
          <a href="signup">Cadastrar</a>
    </body>
</html>