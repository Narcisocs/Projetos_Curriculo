using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace CadeMeuMedico.Models
{
    [MetadataType(typeof(MedicoMetadado))]
    public partial class Medicos
    {
    }
    
    public class MedicoMetadado
    {
        [Required(ErrorMessage="Obrigatório informar o CRM")]
        [StringLength(30, ErrorMessage="O CRM deve possuir no máximo 30 caracteres")]
        public string CRM { get; set; }

        [Required(ErrorMessage = "Obrigatório informar o Nome")]
        [StringLength(80, ErrorMessage = "O Nome deve possuir no máximo 80 caracteres")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "Obrigatório informar o Endereco")]
        [StringLength(100, ErrorMessage = "O Endereco deve possuir no máximo 100 caracteres")]
        public string Endereco { get; set; }

        [Required(ErrorMessage = "Obrigatório informar o Bairro")]
        [StringLength(60, ErrorMessage = "O CRM deve possuir no máximo 60 caracteres")]
        public string Bairro { get; set; }

        [Required(ErrorMessage = "Obrigatório informar o Email")]
        [StringLength(100, ErrorMessage = "O CRM deve possuir no máximo 100 caracteres")]
        public string Email { get; set; }

        [Required(ErrorMessage = "Obrigatório informar se Atende por Convenio")]
        public bool AtendePorConvenio{ get; set; }

        [Required(ErrorMessage = "Obrigatório informar se Tem Clínica")]
        public bool TemClinica { get; set; }

        [StringLength(80, ErrorMessage = "O WebSite deve possuir no máximo 80 caracteres")]
        public string WebsiteBlog { get; set; }

        [Required(ErrorMessage = "Obrigatório informar a Cidade")]
        public int IDCidade { get; set; }

        [Required(ErrorMessage = "Obrigatório informar a Especialidade")]
        public int IDEspecialidade { get; set; }
    }
}