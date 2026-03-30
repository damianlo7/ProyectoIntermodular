using System;
using System.Windows.Forms;

namespace chemin
{
    public partial class FormPerfil : Form
    {
        private Api api = new Api();

        public FormPerfil()
        {
            InitializeComponent();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Sesion.Cerrar();
            Form1 form1 = new Form1();
            form1.Show();
            this.Close();
            Application.OpenForms["FormPrincipal"]?.Close();
        }

        private async void button2_Click(object sender, EventArgs e)
        {
            var confirmar = MessageBox.Show("¿Seguro que quieres eliminar tu cuenta?",
                "Confirmar", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);

            if (confirmar == DialogResult.Yes)
            {
                bool exito = await api.EliminarCuentaAsync(Sesion.Id);
                if (exito)
                {
                    Sesion.Cerrar();
                    MessageBox.Show("Cuenta eliminada correctamente");
                    Form1 form1 = new Form1();
                    form1.Show();
                    this.Close();
                    Application.OpenForms["FormPrincipal"]?.Close();
                }
                else
                {
                    MessageBox.Show("Error al eliminar la cuenta");
                }
            }
        }

        private void FormPerfil_Load(object sender, EventArgs e)
        {
        }
    }
}