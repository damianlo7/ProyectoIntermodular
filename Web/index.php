<?php
session_start();

require_once __DIR__ . '/controladores/ControladorSesion.php';
require_once __DIR__ . '/controladores/ControladorRegistro.php';
require_once __DIR__ . '/controladores/ControladorPerfil.php';
require_once __DIR__ . '/controladores/ControladorPublicaciones.php';

$action = isset($_GET['action']) ? $_GET['action'] : 'login';

$ctrlSesion = new ControladorSesion();
$ctrlRegistro = new ControladorRegistro();
$ctrlPerfil = new ControladorPerfil();
$ctrlPub = new ControladorPublicaciones();

switch ($action) {

    case 'login':
        require_once 'vistas/Login.php';
        break;

    case 'registro':
        require_once 'vistas/Registro.php';
        break;

    case 'menu':
        require_once 'vistas/Menu.php';
        break;

    case 'ajustes':
        require_once 'vistas/Ajustes.php';
        break;

    case 'editarPerfil':
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $ctrlPerfil->editar();
        } else {
            require_once 'vistas/EditarPerfil.php';
        }
        break;

    case 'publicarTexto':
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $ctrlPub->publicarTexto();
        } else {
            require_once 'vistas/PublicarTexto.php';
        }
        break;

    case 'validarLogin':
        $ctrlSesion->iniciarSesion();
        break;

    case 'registrar':
        $ctrlRegistro->registrar();
        break;

    case 'logout':
        $ctrlSesion->cerrarSesion();
        break;

    case 'eliminarCuenta':
        $ctrlPerfil->eliminarCuenta();
        break;

    case 'subirImagen':
        $ctrlPub->subirImagen();
        break;

    case 'eliminarPublicacion':
        $ctrlPub->eliminar();
        break;

    default:
        require_once 'vistas/Login.php';
        break;
}
