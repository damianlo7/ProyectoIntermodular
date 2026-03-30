using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace chemin
{
    public class Publicacion
    {
        public int Id { get; set; }
        public int IdUsuario { get; set; }
        public string Username { get; set; }
        public string Imagen { get; set; }
        public string Texto { get; set; }
    }
}
