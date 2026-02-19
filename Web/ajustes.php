<?php
session_start();
if (!isset($_SESSION["usuario"])) {
    header("Location: index.html");
    exit;
}
?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajustes - Chemin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(to top, #eaf2ff, #f8fbff);
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px 20px;
        }

        h2 {
            margin-bottom: 30px;
        }

        .btn-ajuste {
            width: 300px;
            margin-bottom: 15px;
            background-color: #4facde;
            color: white;
            border: none;
        }

        .btn-ajuste:hover {
            background-color: #3d9ac8;
            color: white;
        }
    </style>
</head>

<body>
    <h2>Ajustes</h2>

    <button class="btn btn-ajuste" onclick="window.location.href='editarPerfil.php'">Editar perfil</button>
    <button class="btn btn-ajuste" onclick="alert('Próximamente')">Configuración</button>
    <button class="btn btn-ajuste" onclick="cerrarSesion()">Cerrar sesión</button>
    <button class="btn btn-ajuste" onclick="confirmarEliminar()">Eliminar cuenta</button>

    <script>
        function cerrarSesion() {
            fetch('logout.php').then(() => {
                window.location.href = 'index.html';
            });
        }

        function confirmarEliminar() {
            if (!confirm('¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.')) return;

            fetch('eliminar_cuenta.php')
                .then(res => {
                    if (res.ok) {
                        window.location.href = 'index.html';
                    } else {
                        alert('Error al eliminar la cuenta');
                    }
                })
                .catch(() => alert('Error de conexión'));
        }
    </script>
</body>

</html>