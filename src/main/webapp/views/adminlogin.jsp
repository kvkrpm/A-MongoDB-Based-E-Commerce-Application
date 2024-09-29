<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <title>Admin Login</title>
</head>

<body class="bg-dark">

<div class="login-container p-4">
    <div class="jumbotron border p-4">
        <h2 class="text-center">Admin Login</h2>
        <form action="/admin/loginvalidate" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" name="username" id="username" placeholder="Admin username" required class="form-control form-control-lg">
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    </div>
                    <input type="password" class="form-control form-control-lg" placeholder="Admin Password" required name="password" id="password">
                </div>
            </div>
            <input type="submit" value="Login" class="btn btn-primary btn-block mt-4">
            <h3 class="text-center text-danger mt-3">${msg}</h3>
            mv.addObject("msg", "Invalid username or password");
             <!-- This will display the error message -->
        </form>
    </div>
</div>

</body>
</html>
