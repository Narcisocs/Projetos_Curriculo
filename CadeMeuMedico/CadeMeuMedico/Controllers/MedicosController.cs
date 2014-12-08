using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Data;
using CadeMeuMedico.Models;

namespace CadeMeuMedico.Controllers
{
    public class MedicosController : BaseController
    {
        private EntidadesCadeMeuMedicoBD db = new EntidadesCadeMeuMedicoBD();
        //
        // GET: /Medicos/

        public ActionResult Index()
        {
            var medicos = db.Medicos.Include("Cidades").Include("Especialidades").ToList();
            return View(medicos);
        }

        public ActionResult Adicionar()
        {
            ViewBag.IDCidade = new SelectList(db.Cidades, "IDCidade", "Nome");
            ViewBag.IDEspecialidade = new SelectList(db.Especialidades, "IDEspecialidade", "Nome");

            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Adicionar(Medicos medico)
        {
            if (ModelState.IsValid)
            {
                db.Medicos.Add(medico);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.IDCidade = new SelectList(db.Cidades, "IDCidade", "Nome", medico.IDCidade);
            ViewBag.IDEspecialidade = new SelectList(db.Especialidades, "IDEspecialidade", "Nome", medico.IDEspecialidade);

            return View(medico);
        }

        public ActionResult Detalhes(long id = 0)
        {
            Medicos medico = db.Medicos.Find(id);
            if (medico == null)
            {
                return HttpNotFound();
            }

            return View(medico);
        }

        public ActionResult Editar(long id = 0)
        {
            Medicos medico = db.Medicos.Find(id);
            if (medico == null)
            {
                return HttpNotFound();
            }

            ViewBag.IDCidade = new SelectList(db.Cidades, "IDCidade", "Nome", medico.IDCidade);
            ViewBag.IDEspecialidade = new SelectList(db.Especialidades, "IDEspecialidade", "Nome", medico.IDEspecialidade);

            return View(medico);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Editar(Medicos medico)
        {
            if (ModelState.IsValid)
            {
                db.Entry(medico).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.IDCidade = new SelectList(db.Cidades, "IDCidade", "Nome", medico.IDCidade);
            ViewBag.IDEspecialidade = new SelectList(db.Especialidades, "IDEspecialidade", "Nome", medico.IDEspecialidade);

            return View(medico);
        }

        public ActionResult Excluir(long id = 0)
        {
            Medicos medico = db.Medicos.Find(id);
            if (medico == null)
            {
                return HttpNotFound();
            }
            return View(medico);
        }

        //
        // POST: /TesteMedico/Delete/5

        [HttpPost, ActionName("Excluir")]
        [ValidateAntiForgeryToken]
        public ActionResult ExclusaoConfirmada(long id)
        {
            Medicos medico = db.Medicos.Find(id);
            db.Medicos.Remove(medico);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}
