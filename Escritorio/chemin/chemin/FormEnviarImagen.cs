using System;
using System.Drawing;
using System.Windows.Forms;

using System;
using System.Drawing;
using System.IO;
using System.Net.Http;
using System.Text;
using System.Windows.Forms;

namespace chemin
{
    public partial class FormEnviarImagen : Form
    {
        private Api api = new Api();
        private Image imagenSeleccionada = null;

        public FormEnviarImagen()
        {
            InitializeComponent();
        }

        private void btnSeleccionar_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog ofd = new OpenFileDialog())
            {
                ofd.Title = "Seleccionar imagen";
                ofd.Filter = "Imágenes|*.jpg;*.jpeg;*.png";
                if (ofd.ShowDialog() == DialogResult.OK)
                {
                    using (var temp = Image.FromFile(ofd.FileName))
                    {
                        imagenSeleccionada = new Bitmap(temp);
                    }
                    pbPreview.Image = imagenSeleccionada;
                    pbPreview.SizeMode = PictureBoxSizeMode.Zoom;
                }
            }
        }

        private async void btnSubir_Click(object sender, EventArgs e)
        {
            if (imagenSeleccionada == null)
            {
                MessageBox.Show("Selecciona una imagen primero");
                return;
            }

            string base64 = api.ImageToBase64(imagenSeleccionada);
            string nombre = "img_" + Sesion.Id + "_" + DateTime.Now.Ticks + ".jpg";

            bool exito = await api.SubirImagenAsync(Sesion.Id, nombre, base64);

            if (exito)
            {
                MessageBox.Show("Imagen subida correctamente");
                this.Close();
            }
            else
            {
                MessageBox.Show("Error al subir la imagen");
            }
        }

        private void FormEnviarImagen_Load(object sender, EventArgs e)
        {
        }
    }
}