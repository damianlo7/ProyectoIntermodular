<?php
require_once __DIR__ . '/../modelos/Usuario.php';

class ControladorPerfil
{
    public function editar()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $nombre   = $_POST['nombre'];
            $email    = $_POST['email'];
            $password = $_POST['password'];
            $genero   = $_POST['genero'];
            $username = $_SESSION['usuario'];

            $usuarioModel = new Usuario();
            $respuesta = $usuarioModel->editarPerfil($nombre, $username, $email, $password, $genero);

            if ($respuesta && isset($respuesta['status']) && $respuesta['status'] === 'ok') {
                $_SESSION['usuario'] = $username;
                header('Location: index.php?action=ajustes&success=1');
            } else {
                header('Location: index.php?action=editarPerfil&error=1');
            }
            exit;
        }
    }

    public function eliminarCuenta()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        $usuarioModel = new Usuario();
        $usuarioModel->eliminarCuenta($_SESSION['id']);
        session_destroy();
        header('Location: index.php?action=login');
        exit;
    }
}
