<?php
session_start();
require_once "Usuario.php";
var_dump($_POST);
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $username = $_POST["username"];
    $password = $_POST["password"];

    $api = new Usuario();
    $respuesta = $api->login($username, $password);

    if ($respuesta && $respuesta["status"] == "ok") {

        // Guardar sesión
        $_SESSION["usuario"] = $username;

        // Ir al menú
        header("Location: menu.php");
        exit;

    } else {
        echo "Credenciales incorrectas";
    }
}
