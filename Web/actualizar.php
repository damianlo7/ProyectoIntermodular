<?php
session_start();
require_once "Usuario.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nombre = $_POST["nombre"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $genero = $_POST["genero"];
    $username = $_SESSION["usuario"];

    $api = new Usuario();
    $respuesta = $api->editarPerfil($nombre, $username, $email, $password, $genero);

    if ($respuesta && isset($respuesta["status"]) && $respuesta["status"] === "ok") {
        header("Location: menu.php");
        exit;
    } else {
        $mensaje = $respuesta["message"] ?? "Error al actualizar";
        echo $mensaje;
    }
}
?>