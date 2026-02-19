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
    <title>Dashboard - Chemin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(to top, #eaf2ff, #f8fbff);
        }
        .toolbar {
            background: #ffffffcc;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .contenedor-publicaciones {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            padding: 20px;
        }
        .card-publicacion {
            width: 90%;
            max-width: 400px;
        }
        .card-img-top {
            width: 100%;
            height: 300px;
            object-fit: cover;
        }
        .toolbar-btn {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
            object-fit: cover;
            transition: transform 0.2s;
        }
        .toolbar-btn:hover {
            transform: scale(1.1);
        }
    </style>
</head>

<body>
    <div class="toolbar">
        <span>Bienvenido, <?php echo $_SESSION["usuario"]; ?></span>
        <div class="d-flex align-items-center gap-2">
            <img src="img/iconos/anadir.png" class="toolbar-btn" alt="Subir imagen" onclick="document.getElementById('inputArchivo').click()">
            <img src="img/iconos/chat.png" class="toolbar-btn" alt="Mensajes" onclick="alert('Próximamente')">
            <img src="img/iconos/usuario.png" class="toolbar-btn" alt="Perfil" onclick="window.location.href='ajustes.php'">
        </div>
    </div>

    <input type="file" id="inputArchivo" accept="image/*" style="display:none" onchange="subirImagen(this.files[0])">

    <div class="contenedor-publicaciones" id="contenedor-publicaciones"></div>

    <script>
        async function cargarTodasPublicaciones() {
            try {
                const res = await fetch('get_publicaciones.php');
                const data = await res.json();
                if (!Array.isArray(data)) return;
                mostrarPublicaciones(data);
            } catch (error) {
                console.error('Error al cargar publicaciones', error);
            }
        }

        function mostrarPublicaciones(publicaciones) {
            const contenedor = document.getElementById('contenedor-publicaciones');
            contenedor.innerHTML = '';
            publicaciones.forEach(pub => {
                const card = document.createElement('div');
                card.className = 'card card-publicacion';
                card.innerHTML = `
                    <img src="data:image/jpeg;base64,${pub.imagen}" class="card-img-top" />
                    <div class="card-body">
                        <p class="card-text">@${pub.username}</p>
                        <button class="btn btn-sm btn-danger" onclick="eliminarPublicacion(${pub.id})">Eliminar</button>
                    </div>
                `;
                contenedor.appendChild(card);
            });
        }

        async function subirImagen(file) {
            if (!file || !file.type.startsWith('image/')) {
                alert('Solo se permiten archivos de imagen');
                return;
            }

            const nombre = prompt("Nombre de la imagen:", file.name);
            if (!nombre) return;

            const reader = new FileReader();
            reader.onload = async function(e) {
                const base64 = e.target.result.split(',')[1];
                try {
                    const res = await fetch('subir_imagen.php', {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ nombre: nombre, imagen: base64 })
                    });
                    if (res.ok) {
                        alert('Imagen subida correctamente');
                        cargarTodasPublicaciones();
                    } else {
                        alert('Error al subir la imagen');
                    }
                } catch (err) {
                    console.error(err);
                    alert('Error al subir la imagen');
                }
            };
            reader.readAsDataURL(file);
        }

        async function eliminarPublicacion(id) {
            if (!confirm('¿Eliminar esta publicación?')) return;
            try {
                const res = await fetch('eliminar_publicacion.php', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ id: id })
                });
                if (res.ok) {
                    cargarTodasPublicaciones();
                } else {
                    alert('Error al eliminar');
                }
            } catch (err) {
                console.error(err);
            }
        }

        window.onload = cargarTodasPublicaciones;
    </script>
</body>

</html>