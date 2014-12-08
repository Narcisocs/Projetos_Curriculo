using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using CadeMeuMedico.Models;

namespace CadeMeuMedico.Controllers
{
    public class HomeController : BaseController
    {
        private EntidadesCadeMeuMedicoBD db = new EntidadesCadeMeuMedicoBD();
        //
        // GET: /Home/

        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Login()
        {
            ViewBag.Title = "Seja bem vindo(a)";

            return View();
        }
    }
}
