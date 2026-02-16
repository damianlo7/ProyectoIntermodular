<?php
session_start();
require_once "Usuario.php";
var_dump($_POST);

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $username = $_POST["username"];
    $password = $_POST["password"];

    $api = new Usuario();
    $respuesta = $api->login($username, $password);

    if ($respuesta && isset($respuesta["id"])) {

        $_SESSION["usuario"] = $username;

        header("Location: menu.php");
        exit;

    } else {
        $mensaje = $respuesta["message"] ?? "Credenciales incorrectas";
        echo $mensaje;
    }
}
?>
