<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Chemin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="public/css/styles.css">
</head>
<body>
    <div class="centrado">
        <img src="public/img/logos/logo1.png" alt="Logo de inicio" id="logoInicio">

        <?php if (isset($_GET['success'])): ?>
            <div class="alert alert-success">¡Registro exitoso! Ya puedes iniciar sesión.</div>
        <?php endif; ?>
        <?php if (isset($_GET['error'])): ?>
            <div class="alert alert-danger">Credenciales incorrectas. Inténtalo de nuevo.</div>
        <?php endif; ?>

        <form class="form-login" method="POST" action="index.php?action=validarLogin">
            <div class="mb-3">
                <input type="text" name="username" class="form-control" placeholder="Nombre de usuario" required>
            </div>
            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="Contraseña" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Iniciar sesión</button>
        </form>

        <p class="registro-texto">
            ¿No tienes cuenta? <a href="index.php?action=registro" class="registro-enlace">Regístrate</a>
        </p>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
