<?php
class Usuario
{
    private $baseUrl;

    public function __construct()
    {
        $this->baseUrl = "http://localhost:8080/tema5maven/rest";
    }

    private function request($method, $url, $data = null)
    {
        $ch = curl_init($this->baseUrl . $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);

        if ($data) {
            curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
        }

        $response = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        if (curl_errno($ch)) {
            error_log("cURL error: " . curl_error($ch));
        }

        curl_close($ch);
        return json_decode($response, true);
    }

    public function login($username, $password)
    {
        $data = [
            "username"    => $username,
            "contrasenha" => $password
        ];
        return $this->request("POST", "/usuario/login", $data);
    }

    public function registrar($nombre, $username, $email, $password, $genero)
    {
        $data = [
            "nombreCompleto" => $nombre,
            "username"       => $username,
            "email"          => $email,
            "contrasenha"    => $password,
            "genero"         => $genero
        ];
        return $this->request("POST", "/usuario/registro", $data);
    }

    public function editarPerfil($nombre, $username, $email, $password, $genero)
    {
        $data = [
            "nombreCompleto" => $nombre,
            "username"       => $username,
            "email"          => $email,
            "contrasenha"    => $password,
            "genero"         => $genero
        ];
        return $this->request("POST", "/usuario/actualizar", $data);
    }

    public function eliminarCuenta($id)
    {
        return $this->request("DELETE", "/usuario/eliminar/{$id}");
    }
}
