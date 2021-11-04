package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * @author nemur
 * 管理者登録画面を表示する処理を記述する。
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	// 管理者情報を登録する。
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		// Administratorドメインをインスタンス化する。
		Administrator administrator = new Administrator();
		
		// insertAdministratorFormオブジェクトの中身をAdministratorオブジェクトにコピーする。
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		
		administratorService.insert(administrator);
		return "redirect:/";
	}
}
