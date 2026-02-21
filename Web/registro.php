<?php
session_start();
require_once "Usuario.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nombre = $_POST["nombre"];
    $username = $_POST["username"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $genero = $_POST["genero"];

    $api = new Usuario();
    $respuesta = $api->registrar($nombre, $username, $email, $password, $genero);

    var_dump($respuesta); 

    if ($respuesta && isset($respuesta["status"]) && $respuesta["status"] === "ok") {
        header("Location: index.html");
        exit;
    } else {
        $mensaje = $respuesta["message"] ?? "Error al registrarse";
        echo $mensaje;
    }
}
?>