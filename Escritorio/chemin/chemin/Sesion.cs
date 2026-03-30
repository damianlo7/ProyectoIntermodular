using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace chemin
{
    internal class Sesion
    {
        public static int Id { get; set; }
        public static string Username { get; set; }
        public static string Nombre { get; set; }
        public static void Cerrar()
        {
            Id = 0;
            Username = null;
            Nombre = null;
        }
    }
}
