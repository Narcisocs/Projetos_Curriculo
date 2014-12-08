using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace CadeMeuMedico.Models
{
    [MetadataType(typeof(UsuarioMetadado))]
    public partial class Usuario
    {
    }

    public class UsuarioMetadado
    {
        [Required(ErrorMessage="Obrigatório infomar o Nome")]
        [StringLength(80, ErrorMessage="O Nome deve possuir no máximo 80 caracteres")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "Obrigatório infomar o Login")]
        [StringLength(30, ErrorMessage = "O Login deve possuir no máximo 30 caracteres")]
        public string Login { get; set; }

        [Required(ErrorMessage = "Obrigatório infomar o Senha")]
        [StringLength(100, ErrorMessage = "A Senha deve possuir no máximo 100 caracteres")]
        public string Senha { get; set; }

        [Required(ErrorMessage = "Obrigatório infomar o Email")]
        [StringLength(100, ErrorMessage = "O Email deve possuir no máximo 100 caracteres")]
        public string Email { get; set; }
    }
}