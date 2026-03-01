<?php
require_once __DIR__ . '/../modelos/Publicaciones.php';

class ControladorPublicaciones
{
    public function subirImagen()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        $data = json_decode(file_get_contents('php://input'), true);
        $pubModel = new Publicaciones();
        $resultado = $pubModel->subirImagen($_SESSION['id'], $data['nombre'], $data['imagen']);
        header('Content-Type: application/json');
        echo json_encode($resultado);
        exit;
    }

    public function publicarTexto()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        $data = json_decode(file_get_contents('php://input'), true);
        $pubModel = new Publicaciones();
        $resultado = $pubModel->publicarTexto($_SESSION['id'], $data['texto']);
        header('Content-Type: application/json');
        echo json_encode($resultado);
        exit;
    }

    public function obtenerTodas()
    {
        $pubModel = new Publicaciones();
        return $pubModel->obtenerTodas();
    }

    public function obtener($idUsuario)
    {
        $pubModel = new Publicaciones();
        return $pubModel->obtener($idUsuario);
    }

    public function eliminar()
    {
        if (session_status() === PHP_SESSION_NONE) session_start();

        $data = json_decode(file_get_contents('php://input'), true);
        $pubModel = new Publicaciones();
        $resultado = $pubModel->eliminar($data['id']);
        header('Content-Type: application/json');
        echo json_encode($resultado);
        exit;
    }
}
