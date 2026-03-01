<?php
if (!isset($_SESSION['usuario'])) {
    header('Location: index.php?action=login');
    exit;
}
require_once __DIR__ . '/../controladores/ControladorPublicaciones.php';
$ctrlPub = new ControladorPublicaciones();
$publicaciones = $ctrlPub->obtenerTodas();
$idSesion = $_SESSION['id'];
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú - Chemin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="public/css/styles.css">
    <style>
        body {
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(to top, rgba(42,123,155,0.2), rgba(87,199,133,0.2));
        }
        .toolbar {
            background: #ffffffcc;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .contenedor-publicaciones {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            padding: 20px;
        }
        .card-publicacion { width: 90%; max-width: 400px; }
        .card-img-top { width: 100%; height: 300px; object-fit: cover; }
        .toolbar-btn {
            width: 50px; height: 50px;
            border-radius: 50%; cursor: pointer;
            object-fit: cover; transition: transform 0.2s;
        }
        .toolbar-btn:hover { transform: scale(1.1); }
    </style>
</head>
<body>
    <div class="toolbar">
        <span>Bienvenido, <?php echo htmlspecialchars($_SESSION['usuario']); ?></span>
        <div class="d-flex align-items-center gap-2">
            <img src="public/img/iconos/burbujachat.png" class="toolbar-btn" alt="Post texto"
                 onclick="window.location.href='index.php?action=publicarTexto'">
            <img src="public/img/iconos/anadir.png" class="toolbar-btn" alt="Subir imagen"
                 onclick="document.getElementById('inputArchivo').click()">
            <img src="public/img/iconos/chat.png" class="toolbar-btn" alt="Mensajes"
                 onclick="alert('La opción de mensajes individuales todavía no está disponible!')">
            <img src="public/img/iconos/usuario.png" class="toolbar-btn" alt="Perfil"
                 onclick="window.location.href='index.php?action=ajustes'">
        </div>
    </div>

    <input type="file" id="inputArchivo" accept="image/*" style="display:none"
           onchange="subirImagen(this.files[0])">

    <div class="contenedor-publicaciones" id="contenedor-publicaciones">
        <?php if ($publicaciones && is_array($publicaciones)): ?>
            <?php foreach ($publicaciones as $pub): ?>
                <div class="card card-publicacion">
                    <div style="position:relative">
                        <?php if (!empty($pub['imagen'])): ?>
                            <img src="data:image/jpeg;base64,<?php echo $pub['imagen']; ?>"
                                 class="card-img-top" alt="publicacion">
                        <?php endif; ?>
                        <?php if ($pub['idUsuario'] == $idSesion): ?>
                            <div style="position:absolute;top:8px;right:8px">
                                <button class="btn btn-light btn-sm"
                                        style="border-radius:50%;width:32px;height:32px;padding:0;font-size:16px"
                                        onclick="eliminarPublicacion(<?php echo $pub['id']; ?>)">⋯</button>
                            </div>
                        <?php endif; ?>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><strong>@<?php echo htmlspecialchars($pub['username']); ?></strong></p>
                        <?php if (!empty($pub['texto'])): ?>
                            <p class="card-text"><?php echo htmlspecialchars($pub['texto']); ?></p>
                        <?php endif; ?>
                    </div>
                </div>
            <?php endforeach; ?>
        <?php endif; ?>
    </div>

    <script>
        async function subirImagen(file) {
            if (!file || !file.type.startsWith('image/')) { alert('Solo imágenes'); return; }
            const nombre = prompt('Nombre de la imagen:', file.name);
            if (!nombre) return;
            const reader = new FileReader();
            reader.onload = async function(e) {
                const base64 = e.target.result.split(',')[1];
                const res = await fetch('index.php?action=subirImagen', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ nombre, imagen: base64 })
                });
                if (res.ok) { alert('Imagen subida'); location.reload(); }
                else alert('Error al subir');
            };
            reader.readAsDataURL(file);
        }

        async function eliminarPublicacion(id) {
            if (!confirm('¿Eliminar esta publicación?')) return;
            const res = await fetch('index.php?action=eliminarPublicacion', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id })
            });
            if (res.ok) location.reload();
            else alert('Error al eliminar');
        }
    </script>
</body>
</html>
