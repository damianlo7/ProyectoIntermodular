<?php
if (!isset($_SESSION['usuario'])) {
    header('Location: index.php?action=login');
    exit;
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil - Chemin</title>
    <link rel="stylesheet" href="public/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="centrado">
        <img src="public/img/logos/logo1.png" alt="Logo" id="logoInicio">

        <?php if (isset($_GET['error'])): ?>
            <div class="alert alert-danger">Error al actualizar el perfil.</div>
        <?php endif; ?>

        <form class="form-login" method="POST" action="index.php?action=editarPerfil">
            <input type="text"     name="nombre"   class="form-control mb-2" placeholder="Nombre completo" required>
            <input type="email"    name="email"    class="form-control mb-2" placeholder="Correo" required>
            <input type="password" name="password" class="form-control mb-3" placeholder="Contraseña" required>

            <div class="mb-3">
                <small>Género:</small><br>
                <input type="radio" name="genero" value="hombre" required> Hombre
                <input type="radio" name="genero" value="mujer"> Mujer
                <input type="radio" name="genero" value="otro"> Otro
            </div>

            <button class="btn btn-success w-100">Confirmar</button>
        </form>
    </div>
</body>
</html>
