using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CadeMeuMedico.Controllers
{
    public class MensagensController : BaseController
    {
        //
        // GET: /Mensagens/

        public ActionResult BomDia()
        {
            return Content("Bom dia... Hoje você acordou cedo!");
        }

        public ActionResult BoaTarde()
        {
            return Content("Boa Tarde... Não durma na mesa de Trabalho!");
        }
    }
}
