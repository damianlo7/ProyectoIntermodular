<?php
require_once __DIR__ . '/../modelos/Usuario.php';

class ControladorRegistro
{
    public function registrar()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $nombre   = $_POST['nombre'];
            $username = $_POST['username'];
            $email    = $_POST['email'];
            $password = $_POST['password'];
            $genero   = $_POST['genero'];

            $usuarioModel = new Usuario();
            $respuesta = $usuarioModel->registrar($nombre, $username, $email, $password, $genero);

            if ($respuesta && isset($respuesta['status']) && $respuesta['status'] === 'ok') {
                header('Location: index.php?action=login&success=1');
            } else {
                header('Location: index.php?action=registro&error=1');
            }
            exit;
        }
    }
}
