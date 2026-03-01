<?php
require_once __DIR__ . '/../modelos/Usuario.php';

class ControladorSesion
{
    public function iniciarSesion()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $username = $_POST['username'];
            $password = $_POST['password'];

            $usuarioModel = new Usuario();
            $respuesta = $usuarioModel->login($username, $password);

            if ($respuesta && isset($respuesta['id'])) {
                $_SESSION['usuario'] = $username;
                $_SESSION['id']      = $respuesta['id'];
                header('Location: index.php?action=menu');
                exit;
            } else {
                header('Location: index.php?action=login&error=1');
                exit;
            }
        }
    }

    public function cerrarSesion()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();
        session_destroy();
        header('Location: index.php?action=login');
        exit;
    }
}
