using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ComponentModel.DataAnnotations;
using CadeMeuMedico.Repositorios;

namespace CadeMeuMedico.Controllers
{
    public class UsuariosController : Controller
    {
        [HttpGet]
        public JsonResult AutenticacaoDeUsuario(string Login, string Senha)
        {
            if (RepositorioUsuarios.AutenticaUsuario(Login, Senha))
            {
                return Json(new
                {
                    OK = true,
                    Mensagem = "Usuário autenticado. Redirecionando..."
                },
                            JsonRequestBehavior.AllowGet);
            }
            else
            {
                return Json(new
                {
                    OK = false,
                    Mensagem = "Usuário não encontrado. Tente Novamente."
                },
                            JsonRequestBehavior.AllowGet);
            }
        }

        public JsonResult RecuperaSenha(string Email)
        {
            if(!String.IsNullOrEmpty(RepositorioUsuarios.RecuperaSenha(Email)))
            {
                return Json(new
                {
                    OK = true,
                    Mensagem = "Um email com a sua senha foi enviado para seu email."
                },
                            JsonRequestBehavior.AllowGet);
            }
            else
            {
                return Json(new
                {
                    OK = false,
                    Mensagem = "Este email não se encontra cadastrado na nossa base de dados"
                },
                            JsonRequestBehavior.AllowGet);
            }
        }
    }
}
