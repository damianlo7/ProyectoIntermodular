using System;
using System.Windows.Forms;

namespace chemin
{
    public partial class FormEnviarTexto : Form
    {
        private Api api = new Api();

        public FormEnviarTexto()
        {
            InitializeComponent();
        }

        private async void btnEnviar_Click(object sender, EventArgs e)
        {
            string texto = textBox1.Text.Trim();

            if (string.IsNullOrEmpty(texto))
            {
                MessageBox.Show("Escribe algo primero");
                return;
            }

            bool exito = await api.PublicarTextoAsync(Sesion.Id, texto);

            if (exito)
            {
                MessageBox.Show("Publicado correctamente");
                this.Close();
            }
            else
            {
                MessageBox.Show("Error al publicar");
            }
        }

        private void FormEnviarTexto_Load(object sender, EventArgs e)
        {
        }
    }
}