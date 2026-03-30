using System;
using System.Drawing;
using System.Windows.Forms;

namespace chemin
{
    public partial class FormRegistro : Form
    {
        private Api api = new Api();

        public FormRegistro()
        {
            InitializeComponent();
        }

        private async void btnRegistrar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombreUsuario.Text.Trim();
            string email = txtCorreo.Text.Trim();
            string password = txtContraseña.Text.Trim();
            string genero = rbH.Checked ? "hombre" : rbM.Checked ? "mujer" : "otro";

            if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                MessageBox.Show("Rellena todos los campos");
                return;
            }

            bool exito = await api.RegistrarAsync(nombre, nombre, email, password, genero);

            if (exito)
            {
                MessageBox.Show("Usuario registrado correctamente");
                Form1 form1 = new Form1();
                form1.Show();
                this.Hide();
            }
            else
            {
                MessageBox.Show("Error al registrarse");
            }
        }

        private void FormRegistro_Load(object sender, EventArgs e)
        {
        }
    }
}